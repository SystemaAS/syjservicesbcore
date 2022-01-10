package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.controller.rules.SVLTU_U;
import no.systema.jservices.common.dao.SvltuDao;
import no.systema.jservices.common.dao.services.SvltuDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SVLTU {
	private static final Logger logger = LoggerFactory.getLogger(BcoreMaintResponseOutputterController_SVLTU.class.getName());

	
	/**
	 * Search in SVLTU
	 * 
	 * Example :
	 * http://localhost:8080/syjservicesbcore/syjsSVLTU?user=SYSTEMA&svlth_igl=BJO&svlth_ign=19-003&svlth_pos=1
	 */
	@RequestMapping(path = "/syjsSVLTU", method = RequestMethod.GET)
	@ResponseBody
	public String syjsSVLTH(HttpSession session, 
			@RequestParam(value = "user", required = true) String user,
			@RequestParam(value = "svlth_igl", required = true) String svlth_igl,
			@RequestParam(value = "svlth_ign", required = true) String svlth_ign,
			@RequestParam(value = "svlth_pos", required = true) String svlth_pos,
			@RequestParam(value = "DO_NOT_LOAD", required = false) String DO_NOT_LOAD) {

		logger.info("INSIDE /syjsSVLTU...");
		
		logger.info("svlth_igl="+svlth_igl);
		logger.info("svlth_ign="+svlth_ign);
		logger.info("svlth_pos="+svlth_pos);
		
		JsonResponseWriter2<SvltuDao> jsonWriter = new JsonResponseWriter2<SvltuDao>();		
		String errMsg = "";
		String status = "ok";
		StringBuffer dbErrorStackTrace = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		List<SvltuDao> svlthDtoList = new ArrayList<SvltuDao>();	
		try {
			String userName = bridfDaoServices.findNameById(user);
			if (StringUtils.hasValue(userName)) {

				if (DO_NOT_LOAD != null) {  //datatables trick, due to autoload
					//do nothing
				} else {
					svlthDtoList = svltuDaoService.findAll(svlth_igl, svlth_ign, svlth_pos);
				}

				sb.append(jsonWriter.setJsonResult_Common_GetList(userName, svlthDtoList));
				
			} else {
				errMsg = "ERROR on SELECT ";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user> ");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			
			}
		} catch (Throwable e) {
			logger.info("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		return sb.toString();

	}
	
	
	@RequestMapping(value = "syjsSVLTU_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsSVLTU_U(HttpSession session, HttpServletRequest request) {	
		logger.info("INSIDE syjsSVLT_U.do");
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		String userName = null;
		String errMsg;
		String status;
		StringBuffer dbErrorStackTrace  = new StringBuffer();
		String user = request.getParameter("user");
		String mode = request.getParameter("mode");
	
		logger.info("user="+user);
		logger.info("mode="+mode);
		
		
		try {
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user);
			errMsg = "";
			status = "ok";
			SvltuDao dao = new SvltuDao();
			SvltuDao resultDao = new SvltuDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);

	        SVLTU_U rulerLord = new SVLTU_U(request, svltuDaoService,sb, dbErrorStackTrace); 
	        
			if (rulerLord.isValidInput(dao, userName)) {
				if (StringUtils.hasValue(userName)) {
						resultDao = svltuDaoService.create(dao);
						logger.info("SVLTU created, dao="+ReflectionToStringBuilder.toString(resultDao));
					if (resultDao == null) {
						errMsg = "Could not add dao=" + ReflectionToStringBuilder.toString(dao);
						status = "error ";
						dbErrorStackTrace.append("Could not add dao=" + ReflectionToStringBuilder.toString(dao));
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					} else {
						// OK UPDATE
						sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
					}
				} else {
					logger.error("user: "+user+ " not found in bridf.");
					// write JSON error output
					errMsg = "ERROR on DML for SVLTU";
					status = "error";
					dbErrorStackTrace.append("request input parameters are invalid: <user>");
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				// write JSON error output
				errMsg = "ERROR on ADD for SVLTU: invalid rulerLord, error="+sb.toString();
				status = "error";
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				logger.error(sb.toString());
			}
			

		} catch (Throwable e) {
			logger.error("::ERROR::", e);
			errMsg = "ERROR on add SVLTU: "+e.getMessage();
			status = "error ";
			dbErrorStackTrace.append(e.getMessage());
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,dbErrorStackTrace));
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

	@Qualifier ("svltuDaoService")
	private SvltuDaoService svltuDaoService;
	@Autowired
	@Required
	public void setSvltuDaoService(SvltuDaoService value){ this.svltuDaoService = value; }
	public SvltuDaoService getSvltuDaoService(){ return this.svltuDaoService; }		

		
}
