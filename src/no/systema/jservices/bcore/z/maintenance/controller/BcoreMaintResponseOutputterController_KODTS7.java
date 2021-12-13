package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.common.dao.Kodts7Dao;
import no.systema.jservices.common.dao.services.Kodts7DaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_KODTS7 {
	private static final Logger logger = LogManager.getLogger(BcoreMaintResponseOutputterController_KODTS7.class.getName());

	
	/**
	 * File: 	KODTS7
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsKODTS7.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsKODTS7.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<Kodts7Dao> jsonWriter = new JsonResponseWriter2<Kodts7Dao>();
		StringBuffer sb = new StringBuffer();
		List<Kodts7Dao> kodts7DaoList = null;
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				kodts7DaoList = kodts7DaoService.getS7Koder();
				if (kodts7DaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, kodts7DaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find Kodts7Dao list";
					status = "error";
					logger.info( status + errMsg);
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

	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }	

	@Qualifier ("kodts7DaoService")
	private Kodts7DaoService kodts7DaoService;
	@Autowired
	@Required
	public void setKodts7DaoService (Kodts7DaoService value){ this.kodts7DaoService = value; }
	public Kodts7DaoService getKodts7DaoService(){ return this.kodts7DaoService; }		
	
	
}
