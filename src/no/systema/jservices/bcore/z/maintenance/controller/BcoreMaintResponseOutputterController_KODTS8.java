package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
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

import no.systema.jservices.common.dao.Kodts8Dao;
import no.systema.jservices.common.dao.services.Kodts8DaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_KODTS8 {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_KODTS8.class.getName());

	
	/**
	 * File: 	KODTS8
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsKODTS8.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsKODTS8.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getKodts8(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<Kodts8Dao> jsonWriter = new JsonResponseWriter2<Kodts8Dao>();
		StringBuffer sb = new StringBuffer();
		List<Kodts8Dao> kodts8DaoList = null;
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				kodts8DaoList = kodts8DaoService.findAll(null);
				if (kodts8DaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, kodts8DaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find Kodts8Dao list";
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

	@Qualifier ("kodts8DaoService")
	private Kodts8DaoService kodts8DaoService;
	@Autowired
	@Required
	public void setKodts8DaoService(Kodts8DaoService value){ this.kodts8DaoService = value; }
	public Kodts8DaoService getKodts8DaoService(){ return this.kodts8DaoService; }		
	
	
}
