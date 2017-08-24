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

import no.systema.jservices.common.dao.VadrDao;
import no.systema.jservices.common.dao.services.VadrDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_VADR {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_VADR.class.getName());

	
	/**
	 * File: 	VADR
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsVADR.do?user=OSCAR&kundnr=600006
	 * 
	 */
	@RequestMapping(value="syjsVADR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsVadr(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<VadrDao> jsonWriter = new JsonResponseWriter2<VadrDao>();
		StringBuffer sb = new StringBuffer();
		List<VadrDao> vadrDaoList = null;
		
		try {
			String user = request.getParameter("user");
			String kundnr = request.getParameter("kundnr");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if (StringUtils.hasValue(userName) && StringUtils.hasValue(kundnr)) {
				vadrDaoList = vadrDaoService.getList(Integer.parseInt(kundnr));
				if (vadrDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, vadrDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find VadrDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user> and/or <kundnr1>");
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

	@Qualifier ("vadrDaoService")
	private VadrDaoService vadrDaoService;
	@Autowired
	@Required
	public void setVadrDaoService(VadrDaoService value){ this.vadrDaoService = value; }
	public VadrDaoService getVadrDaoService(){ return this.vadrDaoService; }		
	
	
}
