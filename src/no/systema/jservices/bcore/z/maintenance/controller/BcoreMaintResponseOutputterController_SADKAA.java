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

import no.systema.jservices.common.dao.SadkaaDao;
import no.systema.jservices.common.dao.services.SadkaaDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SADKAA {
	private static final Logger logger = LogManager.getLogger(BcoreMaintResponseOutputterController_SADKAA.class.getName());

	
	/**
	 * File: 	SADKAA
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsSADKAA.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsSADKAA.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SadkaaDao> jsonWriter = new JsonResponseWriter2<SadkaaDao>();
		StringBuffer sb = new StringBuffer();
		List<SadkaaDao> sadkaaDaoList = null;
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				sadkaaDaoList = sadkaaDaoService.findAll(null);
				if (sadkaaDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, sadkaaDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find SadkaaDao list";
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

	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }	

	@Qualifier ("sadkaaDaoService")
	private SadkaaDaoService sadkaaDaoService;
	@Autowired
	@Required
	public void setSadkaaDaoService (SadkaaDaoService value){ this.sadkaaDaoService = value; }
	public SadkaaDaoService getSadkaaDaoService(){ return this.sadkaaDaoService; }		
	
	
}
