package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.controller.rules.SVEW_U;
import no.systema.jservices.common.dao.SvewDao;
import no.systema.jservices.common.dao.services.SvewDaoService;
import no.systema.jservices.common.dao.services.SvtproDaoService;
import no.systema.jservices.common.dao.services.Svtx03fDaoService;
import no.systema.jservices.common.dao.services.Svtx10fDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SVEW {
	private static final Logger logger = LoggerFactory.getLogger(BcoreMaintResponseOutputterController_SVEW.class.getName());

	/**
	 * File: SVEW
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSVEW.do?user=OSCAR&svew_knnr=1&svew_knso=Tarzan
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVEW.do?user=OSCAR&svew_knnr=1
	 * 
	 */
	@RequestMapping(value = "syjsSVEW.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doSadvare(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SvewDao> jsonWriter = new JsonResponseWriter2<SvewDao>();
		StringBuffer sb = new StringBuffer();
		List<SvewDao> svewDaoList = new ArrayList<SvewDao>();
		String svew_knnr = request.getParameter("svew_knnr");
		String svew_knso = request.getParameter("svew_knso");

		try {
			logger.info("Inside syjsSVEW.do");
			String user = request.getParameter("user");
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if ((userName != null && !"".equals(userName)) && (svew_knnr != null && !"".equals(svew_knnr))) {
				if (svew_knso != null && !"".equals(svew_knso)) {
					SvewDao dao = fetchRecord(svew_knnr, svew_knso);
					svewDaoList.add(dao);
				} else {
					svewDaoList = fetchList(svew_knnr);
				}
				if (svewDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, svewDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find SvewDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user> and <levenr> ");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			logger.info("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		return sb.toString();

	}

	/**
	 * Update Database DML operations File: SVEW
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVEW_U.do?user=OSCAR&svew_knnr=1&svew_knso=Tarzan&...and many more...&mode=U/A/D
	 *
	 */
	@RequestMapping(value = "syjsSVEW_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsSVEW_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SvewDao> jsonWriter = new JsonResponseWriter2<SvewDao>();
		StringBuffer sb = new StringBuffer();
		String userName = null;
		String errMsg = null;
		String status = null;
		StringBuffer dbErrorStackTrace = null;

		try {
			logger.info("Inside syjsSVEW_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user);
			errMsg = "";
			status = "ok";
			dbErrorStackTrace = new StringBuffer();
			SvewDao dao = new SvewDao();
			SvewDao resultDao = new SvewDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);
			
			SVEW_U rulerLord = new SVEW_U(request, svtx03fDaoService ,svtx10fDaoService, svtproDaoService , sb, dbErrorStackTrace);
			if (userName != null && !"".equals(userName)) {
				if ("D".equals(mode)) {
					if (rulerLord.isValidInputForDelete(dao, userName, mode)) {
						svewDaoService.delete(dao);
					}
				} else {
					if (rulerLord.isValidInput(dao, userName, mode)) {
						if ("A".equals(mode)) {
							resultDao = svewDaoService.create(dao);
						} else if ("U".equals(mode)) {
							resultDao = svewDaoService.update(dao);
						}
					} 
				}
				if (resultDao == null) {
					errMsg = "ERROR on UPDATE ";
					status = "error ";
					dbErrorStackTrace.append("Could not add/update dao=" + ReflectionToStringBuilder.toString(dao));
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				} else {
					// OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}

			} else {
				// write JSON error output
				errMsg = "ERROR on UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}

		} catch (Exception e) {
			errMsg = "ERROR on UPDATE ";
			status = "error ";
			logger.info("Error:",e);
			dbErrorStackTrace.append(e.getMessage());
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,dbErrorStackTrace));
		}
		session.invalidate();
		return sb.toString();

	}

	private SvewDao fetchRecord(String svew_knnr, String svew_knso) {
		int knnr = Integer.parseInt(svew_knnr);
		SvewDao dao = (SvewDao) svewDaoService.find(knnr, svew_knso);
		return dao;
	}
	
	private List<SvewDao> fetchList(String sviw_knnr) {
		List<SvewDao> svewDaoList = null;
		svewDaoList = svewDaoService.findAll(sviw_knnr);
		return svewDaoList;
	}
	
	
	@Qualifier("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices(BridfDaoServices value) {
		this.bridfDaoServices = value;
	}
	public BridfDaoServices getBridfDaoServices() {
		return this.bridfDaoServices;
	}

	@Qualifier("svewDaoService")
	private SvewDaoService svewDaoService;
	@Autowired
	@Required
	public void setSvewDaoService(SvewDaoService value) {
		this.svewDaoService = value;
	}
	public SvewDaoService getSvwwDaoService() {
		return this.svewDaoService;
	}

	@Qualifier("svtx03fDaoService")
	private Svtx03fDaoService svtx03fDaoService;
	@Autowired
	@Required
	public void setSvtx03fDaoService(Svtx03fDaoService value) {
		this.svtx03fDaoService = value;
	}
	public Svtx03fDaoService getSvtx03fDaoService() {
		return this.svtx03fDaoService;
	}	

	@Qualifier("svtx10fDaoService")
	private Svtx10fDaoService svtx10fDaoService;
	@Autowired
	@Required
	public void setSvtx10fDaoService(Svtx10fDaoService value) {
		this.svtx10fDaoService = value;
	}
	public Svtx10fDaoService getSvtx10fDaoService() {
		return this.svtx10fDaoService;
	}		
	
	@Qualifier("svtproDaoService")
	private SvtproDaoService svtproDaoService;
	@Autowired
	@Required
	public void setSvtproDaoService(SvtproDaoService value) {
		this.svtproDaoService = value;
	}
	public SvtproDaoService getSvtproDaoService() {
		return this.svtproDaoService;
	}		
	
	
}
