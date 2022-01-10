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

import no.systema.jservices.common.dao.Kodts6Dao;
import no.systema.jservices.common.dao.services.Kodts6DaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_KODTS6 {
	private static final Logger logger = LoggerFactory.getLogger(BcoreMaintResponseOutputterController_KODTS6.class.getName());

	
	/**
	 * File: 	KODTS6
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsKODTS6.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsKODTS6.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<Kodts6Dao> jsonWriter = new JsonResponseWriter2<Kodts6Dao>();
		StringBuffer sb = new StringBuffer();
		List<Kodts6Dao> kodts6DaoList = null;
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				kodts6DaoList = kodts6DaoService.getS6Koder();
				if (kodts6DaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, kodts6DaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find Kodts6Dao list";
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

	@Qualifier ("kodts6DaoService")
	private Kodts6DaoService kodts6DaoService;
	@Autowired
	@Required
	public void setKodts6DaoService(Kodts6DaoService value){ this.kodts6DaoService = value; }
	public Kodts6DaoService getKodts6DaoService(){ return this.kodts6DaoService; }		
	
	
}
