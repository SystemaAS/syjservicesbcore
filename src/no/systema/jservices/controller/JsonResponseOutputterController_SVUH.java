package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import no.systema.jservices.common.dao.SvuhDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.common.dao.services.SvuhDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;


@Controller
public class JsonResponseOutputterController_SVUH {
	private static final Logger logger = LogManager.getLogger(JsonResponseOutputterController_SVUH.class.getName());
	/**
	 * File: TRACKF
	 * 
	 * @Example SELECT specific:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVUH.do?user=OSCAR&doc_1004=tullid
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVUH.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value = "syjsSVUH.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsTRAN(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SvuhDao> jsonWriter = new JsonResponseWriter2<SvuhDao>();
		StringBuffer sb = new StringBuffer();
		List<SvuhDao> svuhDaoList = new ArrayList<SvuhDao>();

		try {
			logger.info("Inside syjsSVUH");
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if (StringUtils.hasValue(userName)) {
				SvuhDao resultDao = new SvuhDao();
				SvuhDao dao = new SvuhDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);

				if (StringUtils.hasValue(dao.getDoc_1004())) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("doc_1004", dao.getDoc_1004());
					svuhDaoList = svuhDaoService.findAll(params);
					
				}
				
				if (svuhDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, svuhDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find SvuhDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>");
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
	 *  Update Database DML operations File: TRACKF
	 * 
	 * @Example UPDATE:
	 * 	http://gw.systema.no:8080/syjservicesbcore/syjsSVUH_U.do?user=OSCAR&mode=A/D...
	 *
	 *
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "syjsSVUH_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsHEADF_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SvuhDao> jsonWriter = new JsonResponseWriter2<SvuhDao>();
		StringBuffer sb = new StringBuffer();
		String userName = null;
		String errMsg = null;
		String status = null;
		StringBuffer dbErrorStackTrace = null;

		try {
			logger.warn("Inside syjsSVUH_U.do");
			String user = request.getParameter("user");
			logger.info("user:" + user);
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			userName = this.getBridfDaoServices().findNameById(user);
			logger.warn("userName:" + userName);
			errMsg = "";
			status = "ok";
			dbErrorStackTrace = new StringBuffer();
			SvuhDao dao = new SvuhDao();
			SvuhDao resultDao = new SvuhDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);
			
			//Set keys
			if (userName != null && !"".equals(userName)) {
				if ("D".equals(mode)) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("doc_1004", dao.getDoc_1004());
					this.svuhDaoService.delete(dao);
				} else if ("A".equals(mode)) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("doc_1004", dao.getDoc_1004());
					//we must use this method since there will never be an Update...
					resultDao = this.svuhDaoService.createWithDelete(dao);
					
				} 
				if (resultDao == null) {
					errMsg = "ERROR on UPDATE ";
					status = "error ";
					dbErrorStackTrace.append("Could not add/update dao=" + ReflectionToStringBuilder.toString(dao));
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				} else {
					// OK UPDATE
					sb.append(jsonWriter.setJsonResult_Common_GetComposite(userName, resultDao));	
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
			logger.error("Error:",e);
			dbErrorStackTrace.append(e.getMessage());
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,dbErrorStackTrace));
		}
		session.invalidate();
		return sb.toString();

	}
	
	
	@Qualifier("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices(BridfDaoServices value) { this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices() { return this.bridfDaoServices; }

	
	@Qualifier("svuhDaoService")
	private SvuhDaoService svuhDaoService;
	@Autowired
	@Required
	public void setSvuhDaoService(SvuhDaoService value) { this.svuhDaoService = value; }
	public SvuhDaoService getSvuhDaoService() { return this.svuhDaoService; }

}
