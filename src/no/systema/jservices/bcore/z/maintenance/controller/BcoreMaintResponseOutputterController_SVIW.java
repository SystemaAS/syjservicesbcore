package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.controller.rules.SVIW_U;
import no.systema.jservices.common.dao.SviwDao;
import no.systema.jservices.common.dao.services.SviwDaoService;
import no.systema.jservices.common.dao.services.SvtproDaoService;
import no.systema.jservices.common.dao.services.Svtx03fDaoService;
import no.systema.jservices.common.dao.services.Svtx10fDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SVIW {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_SVIW.class.getName());

	/**
	 * File: SVIW
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSVIW.do?user=OSCAR&sviw_knnr=1&sviw_knso=Jane
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVIW.do?user=OSCAR&sviw_knnr=1
	 * 
	 */
	@RequestMapping(value = "syjsSVIW.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doSviw(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SviwDao> jsonWriter = new JsonResponseWriter2<SviwDao>();
		StringBuffer sb = new StringBuffer();
		List<SviwDao> sviwDaoList = new ArrayList<SviwDao>();
		String sviw_knnr = request.getParameter("sviw_knnr");
		String sviw_knso = request.getParameter("sviw_knso");

		try {
			logger.info("Inside syjsSVIW.do");
			String user = request.getParameter("user");
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if ((userName != null && !"".equals(userName)) && (sviw_knnr != null && !"".equals(sviw_knnr))) {
				if (sviw_knso != null && !"".equals(sviw_knso)) {
					SviwDao dao = fetchRecord(sviw_knnr, sviw_knso);
					sviwDaoList.add(dao);
				} else {
					sviwDaoList = fetchList(sviw_knnr);
				}
				if (sviwDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, sviwDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find SviwDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user> and <sviw_knnr> ");
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
	 * Update Database DML operations File: SVIW
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVIW_U.do?user=OSCAR&sviw_knnr=1&sviw_knso=Tarzan&...and many more...&mode=U/A/D
	 *
	 */
	@RequestMapping(value = "syjsSVIW_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsSVIW_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SviwDao> jsonWriter = new JsonResponseWriter2<SviwDao>();
		StringBuffer sb = new StringBuffer();
		String userName = null;
		String errMsg = null;
		String status = null;
		StringBuffer dbErrorStackTrace = null;

		try {
			logger.info("Inside syjsSVIW_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user);
			errMsg = "";
			status = "ok";
			dbErrorStackTrace = new StringBuffer();
			SviwDao dao = new SviwDao();
			SviwDao resultDao = new SviwDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);
			
			SVIW_U rulerLord = new SVIW_U(request, svtx03fDaoService ,svtx10fDaoService, svtproDaoService , sb, dbErrorStackTrace);
			if (userName != null && !"".equals(userName)) {
				if ("D".equals(mode)) {
					if (rulerLord.isValidInputForDelete(dao, userName, mode)) {
						sviwDaoService.delete(dao);
					}
				} else {
					if (rulerLord.isValidInput(dao, userName, mode)) {
						if ("A".equals(mode)) {
							resultDao = sviwDaoService.create(dao);
						} else if ("U".equals(mode)) {
							resultDao = sviwDaoService.update(dao);
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

	private SviwDao fetchRecord(String sviw_knnr, String sviw_knso) {
		int knnr = Integer.parseInt(sviw_knnr);
		SviwDao dao = (SviwDao) sviwDaoService.find(knnr, sviw_knso);
		return dao;
	}
	
	private List<SviwDao> fetchList(String sviw_knnr) {
		List<SviwDao> sviwDaoList = null;
		sviwDaoList = sviwDaoService.findAll(sviw_knnr);
		return sviwDaoList;
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

	@Qualifier("sviwDaoService")
	private SviwDaoService sviwDaoService;
	@Autowired
	@Required
	public void setSviwDaoService(SviwDaoService value) {
		this.sviwDaoService = value;
	}
	public SviwDaoService getSvwwDaoService() {
		return this.sviwDaoService;
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
