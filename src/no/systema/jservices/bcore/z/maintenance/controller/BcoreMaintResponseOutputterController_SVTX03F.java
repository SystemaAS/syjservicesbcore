package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.common.dao.Svtx03fDao;
import no.systema.jservices.common.dao.services.Svtx03fDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.common.values.Svtx03fKodTyper;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SVTX03F {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_SVTX03F.class.getName());

	/**
	 * File: 	SVTX03F
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsSVTX03F.do?user=OSCAR&02=GCY..FFK..
	 * 
	 */
	@RequestMapping(value="syjsSVTX03F.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<Svtx03fDao> jsonWriter = new JsonResponseWriter2<Svtx03fDao>();
		StringBuffer sb = new StringBuffer();
		List<Svtx03fDao> svtx03fDaoList = null;
		String type02 = request.getParameter("02");
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ( StringUtils.hasValue(userName) && (StringUtils.hasValue(type02))) {
				if (type02.equals(Svtx03fKodTyper.GCY.toString())) {
					svtx03fDaoList = svtx03fDaoService.getLandKoder();
				}
				if (type02.equals(Svtx03fKodTyper.FFK.toString())) {
					svtx03fDaoList = svtx03fDaoService.getEup2Koder();
				}
				if (svtx03fDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, svtx03fDaoList));
				} else {
					errMsg = " ERROR on SELECT: Can not find Svtx03fDao list on 02="+type02+ " only "+ReflectionToStringBuilder.toString(Svtx03fKodTyper.values())+" available";
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

	@Qualifier ("svtx03fDaoService")
	private Svtx03fDaoService svtx03fDaoService;
	@Autowired
	@Required
	public void setSvtx03fDaoService(Svtx03fDaoService value){ this.svtx03fDaoService = value; }
	public Svtx03fDaoService getSvtx03fDaoService(){ return this.svtx03fDaoService; }	
	
}
