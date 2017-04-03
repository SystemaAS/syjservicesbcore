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

import no.systema.jservices.common.dao.TariDao;
import no.systema.jservices.common.dao.services.TariDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_TARI {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_TARI.class.getName());

	
	/**
	 * File: 	TARI
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsTARI.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsTARI.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<TariDao> jsonWriter = new JsonResponseWriter2<TariDao>();
		StringBuffer sb = new StringBuffer();
		List<TariDao> tariDaoList = null;
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				tariDaoList = tariDaoService.findAll(null);
				if (tariDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, tariDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find TariDao list";
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

	@Qualifier ("tariDaoService")
	private TariDaoService tariDaoService;
	@Autowired
	@Required
	public void setTariDaoService (TariDaoService value){ this.tariDaoService = value; }
	public TariDaoService getTariDaoService(){ return this.tariDaoService; }		
	
	
}
