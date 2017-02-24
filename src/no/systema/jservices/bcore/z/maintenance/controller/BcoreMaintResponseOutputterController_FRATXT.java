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

import no.systema.jservices.common.dao.FratxtDao;
import no.systema.jservices.common.dao.KodtlkDao;
import no.systema.jservices.common.dao.services.FratxtDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_FRATXT {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_FRATXT.class.getName());

	
	/**
	 * FreeForm Source:
	 * File: 	KODTLK
	 * 
	 * @return
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsFRATXT.do?user=OSCAR&fxknr=24&delsys=A
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTLK.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsFRATXT.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<FratxtDao> jsonWriter = new JsonResponseWriter2<FratxtDao>();
		StringBuffer sb = new StringBuffer();
		List<FratxtDao> fratxtDaoList = null;
		String fxknr = request.getParameter("fxknr");
		String delsys = request.getParameter("delsys");
		
		logger.info("fxknr="+fxknr+", delsys="+delsys);
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ( (userName != null && !"".equals(userName) ) && (fxknr != null && !"".equals(fxknr)) ) {
				fratxtDaoList = fratxtDaoService.getDelsys(fxknr, delsys);
				if (fratxtDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, fratxtDaoList));
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

	@Qualifier ("fratxtDaoService")
	private FratxtDaoService fratxtDaoService;
	@Autowired
	@Required
	public void setFratxtDaoService (FratxtDaoService value){ this.fratxtDaoService = value; }
	public FratxtDaoService getFratxtDaoService(){ return this.fratxtDaoService; }		
	
	
}
