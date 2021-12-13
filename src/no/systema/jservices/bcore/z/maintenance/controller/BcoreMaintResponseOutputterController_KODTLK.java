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

import no.systema.jservices.common.dao.KodtlkDao;
import no.systema.jservices.common.dao.services.KodtlkDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_KODTLK {
	private static final Logger logger = LogManager.getLogger(BcoreMaintResponseOutputterController_KODTLK.class.getName());

	
	/**
	 * FreeForm Source:
	 * File: 	KODTLK
	 * 
	 * @return
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsKODTLK.do?user=OSCAR
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTLK.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsKODTLK.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<KodtlkDao> jsonWriter = new JsonResponseWriter2<KodtlkDao>();
		StringBuffer sb = new StringBuffer();
		List<KodtlkDao> kodtlkDaoList = null;
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				kodtlkDaoList = kodtlkDaoService.getLandKoder();
				if (kodtlkDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, kodtlkDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find KodtlkDao list";
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

	@Qualifier ("kodtlkDaoService")
	private KodtlkDaoService kodtlkDaoService;
	@Autowired
	@Required
	public void setKodtlkDaoService (KodtlkDaoService value){ this.kodtlkDaoService = value; }
	public KodtlkDaoService getKodtlkDaoService(){ return this.kodtlkDaoService; }		
	
	
}
