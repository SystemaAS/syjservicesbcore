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

import no.systema.jservices.common.dao.KodtsaDao;
import no.systema.jservices.common.dao.services.KodtsaDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_KODTSA {
	private static final Logger logger = LoggerFactory.getLogger(BcoreMaintResponseOutputterController_KODTSA.class.getName());

	
	/**
	 * File: 	KODTSA
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsKODTSA.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsKODTSA.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<KodtsaDao> jsonWriter = new JsonResponseWriter2<KodtsaDao>();
		StringBuffer sb = new StringBuffer();
		List<KodtsaDao> kodtsaDaoList = null;
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				kodtsaDaoList = kodtsaDaoService.findAll(null);
				if (kodtsaDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, kodtsaDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find KodtsaDao list";
					status = "error";
					logger.info( status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}

			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append(" request input parameters are invalid: <user>");
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

	@Qualifier ("kodtsaDaoService")
	private KodtsaDaoService kodtsaDaoService;
	@Autowired
	@Required
	public void setKodtsaDaoService(KodtsaDaoService value){ this.kodtsaDaoService = value; }
	public KodtsaDaoService getKodtsaDaoService(){ return this.kodtsaDaoService; }		
	
	
}
