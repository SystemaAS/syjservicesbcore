package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import no.systema.jservices.common.dao.KodafDao;
import no.systema.jservices.common.dao.services.KodafDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

/**
 * Betalingsbetingelse
 * 
 * @author fredrikmoller
 * @date 2019-03-06
 */
@Controller
public class BcoreMaintResponseOutputterController_KODAF {
	private static final Logger logger = LoggerFactory.getLogger(BcoreMaintResponseOutputterController_KODAF.class.getName());

	
	/**
	 * @Example SELECT list: http://localhost:8080/syjservicesbcore/syjsKODAF.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsKODAF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getKodaf(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<KodafDao> jsonWriter = new JsonResponseWriter2<KodafDao>();
		StringBuffer sb = new StringBuffer();
		List<KodafDao> kodafDaoList = null;
		
		String user = request.getParameter("user");
		String userName = null;
		String errMsg = "";
		String status = "ok";
		StringBuffer dbErrorStackTrace = new StringBuffer();
		
		try {
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user); 

			if ((userName != null && !"".equals(userName))) {
				kodafDaoList = kodafDaoService.findAll(null);
				if (kodafDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, kodafDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find KodafDao list";
					status = "error";
					logger.error("kodafDaoService.findAll(null) :" + " " + status + errMsg);
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
			logger.info(sb.toString());
			logger.error(":::ERROR:::",e);
			errMsg = "ERROR SELECT:  error="+e.getMessage();
			status = "error";
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
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

	@Qualifier ("kodafDaoService")
	private KodafDaoService kodafDaoService;
	@Autowired
	@Required
	public void setKodafDaoService (KodafDaoService value){ this.kodafDaoService = value; }
	public KodafDaoService getKodafDaoService(){ return this.kodafDaoService; }		
	
	
}
