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
import no.systema.jservices.common.dao.SadhDao;
import no.systema.jservices.common.dao.services.SadhDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SADH {
	private static final Logger logger = LogManager.getLogger(BcoreMaintResponseOutputterController_SADH.class.getName());

	
	/**
	 * File: 	SADH (fortolling import)
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsSADH.do?user=OSCAR&siavd=1&sitdn=54946
	 * 
	 */
	@RequestMapping(value="syjsSADH.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getSadh(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SadhDao> jsonWriter = new JsonResponseWriter2<SadhDao>();
		StringBuffer sb = new StringBuffer();
		SadhDao resultDao = null;
		
		try {
			String siavd = request.getParameter("siavd");
			String sitdn = request.getParameter("sitdn");
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				resultDao = fetchRecord(siavd, sitdn);
				if (resultDao != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetComposite(userName, resultDao));
				} else {
					errMsg = "ERROR on SELECT: Can not find SadhDao";
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

	private SadhDao fetchRecord(String  siavd, String sitdn) {
		int avd = Integer.parseInt(siavd);
		int tdn = Integer.parseInt(sitdn);
		SadhDao qDao = new SadhDao();
		qDao.setSiavd(avd);
		qDao.setSitdn(tdn);
		SadhDao resultDao = sadhDaoService.find(qDao);

		return resultDao;
	}
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }	

	@Qualifier ("sadhDaoService")
	private SadhDaoService sadhDaoService;
	@Autowired
	@Required
	public void setSadhDaoService (SadhDaoService value){ this.sadhDaoService = value; }
	public SadhDaoService getSadhDaoService(){ return this.sadhDaoService; }		
	
	
}
