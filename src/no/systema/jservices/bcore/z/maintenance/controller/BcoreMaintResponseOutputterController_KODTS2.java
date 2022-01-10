package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.common.dao.Kodts2Dao;
import no.systema.jservices.common.dao.services.Kodts2DaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_KODTS2 {
	private static final Logger logger = LoggerFactory.getLogger(BcoreMaintResponseOutputterController_KODTS2.class.getName());

	
	/**
	 * File: 	KODTS2
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsKODTS2.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsKODTS2.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<Kodts2Dao> jsonWriter = new JsonResponseWriter2<Kodts2Dao>();
		StringBuffer sb = new StringBuffer();
		List<Kodts2Dao> kodts7DaoList = null;
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				kodts7DaoList = kodts2DaoService.getS2Koder();
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

	@Qualifier ("kodts2DaoService")
	private Kodts2DaoService kodts2DaoService;
	@Autowired
	@Required
	public void setKodts2DaoService (Kodts2DaoService value){ this.kodts2DaoService = value; }
	public Kodts2DaoService getKodts2DaoService(){ return this.kodts2DaoService; }		
	
	
}
