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

import no.systema.jservices.common.dao.Svtx10fDao;
import no.systema.jservices.common.dao.services.Svtx10fDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SVTX10F {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_SVTX10F.class.getName());

	/**
	 * File: 	SVTX10F
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsSVTX10F.do?user=OSCAR&varukod=01
	 * 
	 */
	@RequestMapping(value="syjsSVTX10F.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<Svtx10fDao> jsonWriter = new JsonResponseWriter2<Svtx10fDao>();
		StringBuffer sb = new StringBuffer();
		List<Svtx10fDao> svtx10fDaoList = null;
		String varukod = request.getParameter("varukod");
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ( StringUtils.hasValue(userName) ) {
				if (varukod != null && varukod.length() > 1) {
					svtx10fDaoList = svtx10fDaoService.findByLikeId(varukod);
				} else {
					errMsg = "ERROR on SELECT: Full scan of Svtx10f is not supported. Please provide a least two char of varukod.";
					status = "error";
					logger.info( status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
				if (svtx10fDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, svtx10fDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find Svtx10fDao list";
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

	@Qualifier ("svtx10fDaoService")
	private Svtx10fDaoService svtx10fDaoService;
	@Autowired
	@Required
	public void setSvtx10fDaoService(Svtx10fDaoService value){ this.svtx10fDaoService = value; }
	public Svtx10fDaoService getSvtx10fDaoService(){ return this.svtx10fDaoService; }	
	
}
