package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.common.dao.SadvareDao;
import no.systema.jservices.common.dao.services.FratxtDaoService;
import no.systema.jservices.common.dao.services.SadvareDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SADVARE {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_SADVARE.class.getName());

	/**
	 * File: SADVARE
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE.do?user=OSCAR&levenr=1&varenr=9004901000
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE.do?user=OSCAR&levenr=1
	 * 
	 */
	@RequestMapping(value = "syjsSADVARE.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doSadvare(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SadvareDao> jsonWriter = new JsonResponseWriter2<SadvareDao>();
		StringBuffer sb = new StringBuffer();
		List<SadvareDao> sadvareDaoList = new ArrayList<SadvareDao>();
		String levenr = request.getParameter("levenr");
		String varenr = request.getParameter("varenr");

		try {
			String user = request.getParameter("user");
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if ((userName != null && !"".equals(userName)) && (levenr != null && !"".equals(levenr))) {
				if (varenr != null && !"".equals(varenr)) {
					SadvareDao dao = fetchRecord(levenr, varenr);
					sadvareDaoList.add(dao);
				} else {
					sadvareDaoList = fetchList(levenr);
				}
				if (sadvareDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, sadvareDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find SadvareDao list";
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
	 * 
	 * This method is responsible for checking mode of record.
	 * 
	 * Using {@link FratxtDaoService} for check if exist
	 * 
	 * 
	 * Update Database DML operations File: FRATXT
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF_U.do?user=OSCAR&sykunr=24&syrecn=59&sysort=15&syvrdn=1,5&syvrda=alfaupdate&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	/*
	@RequestMapping(value = "syjsSYPARF_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsSYPARF_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SyparfDao> jsonWriter = new JsonResponseWriter2<SyparfDao>();
		StringBuffer sb = new StringBuffer();

		try {
			logger.info("Inside syjsSYPARF_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			SyparfDto dto = new SyparfDto();
			SyparfDao resultDao = new SyparfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dto);
			binder.bind(request);

			SYPARF_U rulerLord = new SYPARF_U(kofastDaoServices, sb, dbErrorStackTrace);
			
			if (userName != null && !"".equals(userName)) {
				if ("D".equals(mode)) {
					if (rulerLord.isValidInputForDelete(dto, userName, mode)) {
						syparfDaoService.delete(dto);
					}
				} else {
					if (rulerLord.isValidInput(dto, userName, mode)) {
						rulerLord.updateNumericFieldsIfNull(dto);
						if ("A".equals(mode)) {
							resultDao = syparfDaoService.create(dto);
						} else if ("U".equals(mode)) {
							resultDao = syparfDaoService.update(dto);
						}
					} 
				}
				if (resultDao == null) {
					errMsg = "ERROR on UPDATE";
					status = "error";
					dbErrorStackTrace.append("Could not add/update dao=" + ReflectionToStringBuilder.toString(dto));
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				} else {
					// OK UPDATE
					//TODO sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}

			} else {
				// write JSON error output
				errMsg = "ERROR on UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}

		} catch (Exception e) {
			// write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
		return sb.toString();

	}

*/
	private SadvareDao fetchRecord(String levenr, String varenr) {
		SadvareDao dao = (SadvareDao) sadvareDaoService.find(levenr, varenr);
		return dao;
	}

	private List<SadvareDao> fetchList(String levenr) {
		List<SadvareDao> sadvareDaoList = null;
		sadvareDaoList = sadvareDaoService.findAll(levenr);
		return sadvareDaoList;
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

	@Qualifier("sadvareDaoService")
	private SadvareDaoService sadvareDaoService;

	@Autowired
	@Required
	public void setSadvareDaoService(SadvareDaoService value) {
		this.sadvareDaoService = value;
	}

	public SadvareDaoService getSadvareDaoService() {
		return this.sadvareDaoService;
	}

}
