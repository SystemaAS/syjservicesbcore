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

import no.systema.jservices.common.dao.KunkoDao;
import no.systema.jservices.common.dao.services.KunkoDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_KUNKO {
	private static final Logger logger = LoggerFactory.getLogger(BcoreMaintResponseOutputterController_KUNKO.class.getName());

	
	/**
	 * File: 	KUNKO
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsKUNKO.do?user=OSCAR&kukun1=24
	 * 
	 */
	@RequestMapping(value="syjsKUNKO.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsKunko(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<KunkoDao> jsonWriter = new JsonResponseWriter2<KunkoDao>();
		StringBuffer sb = new StringBuffer();
		List<KunkoDao> kunkoDaoList = null;
		
		try {
			String user = request.getParameter("user");
			String kukun1 = request.getParameter("kukun1");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if (StringUtils.hasValue(userName) && StringUtils.hasValue(kukun1)) {
				kunkoDaoList = kunkoDaoService.getList(Integer.parseInt(kukun1));
				if (kunkoDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, kunkoDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find KunkoDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user> and/or <kukun1>");
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

	@Qualifier ("kunkoDaoService")
	private KunkoDaoService kunkoDaoService;
	@Autowired
	@Required
	public void setKunkoDaoService(KunkoDaoService value){ this.kunkoDaoService = value; }
	public KunkoDaoService getKunkoDaoService(){ return this.kunkoDaoService; }		
	
	
}
