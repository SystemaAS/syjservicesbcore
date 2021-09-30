package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.jservices.controller.rules.EDIMR_U;
import no.systema.jservices.controller.rules.SVIH_U;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.EdimDao;
import no.systema.jservices.model.dao.entities.SvihDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.EdimDaoServices;
import no.systema.jservices.model.dao.services.SvihDaoServices;



/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Sep 2021
 * 
 */

@Controller
public class JsonResponseOutputterController_SVIH {
	private static Logger logger = Logger.getLogger(JsonResponseOutputterController_SVIH.class.getName());
	
	
	/**
	 * 
	 * Update Database DML operations
	 * 	 File: 	SVIH
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVIH_U.do?user=OSCAR&mode=U&xxx=yyy...
	 * 
	 * @Example ADD:
	 * 			http://gw.systema.no:8080/syjservicesbcore/syjsSVIH_U.do?user=OSCAR&mode=A&xxx=yyy....
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsSVIH_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsCundf_U( HttpSession session, HttpServletRequest request, Locale locale) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();	

		StringBuffer dbErrorStackTrace = new StringBuffer();
        String errMsg = "";
		String status = "ok";
		String userName = null;
		
		try{
			logger.info("Inside syjsSVIH_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			logger.warn("mode:" + mode);
			logger.warn("xx" + request.getParameter("svih_syst"));
			
			//Check ALWAYS user in BRIDF
            userName = this.bridfDaoServices.findNameById(user);
			
			//bind attributes is any
			SvihDao dao = new SvihDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.info("DAO="+ReflectionToStringBuilder.toString(dao));
            
            //adjust status since '+' was problematic at the caller restTemplate. UrlEncoding issues
            if("%2B".equals(dao.getSvih_syst())) {
            	dao.setSvih_syst(dao.getSvih_syst().replace("%2B", "+"));
            }
            //rules
            SVIH_U rulerLord = new SVIH_U(); 
			//Start processing now
			if (userName != null) {
				int dmlRetval = 0;
				
				if (rulerLord.isValidInput( userName, mode)) {
					if ("US".equals(mode)) {
						if(rulerLord.isValidInputUpdateStatus(dao, user, mode)){
							logger.warn("syst:" + dao.getSvih_syst());
							dmlRetval = svihDaoServices.updateStatus(dao, dbErrorStackTrace);
						}else {
							// write JSON error output
							errMsg = "ERROR on UPDATE: invalid rulerLord error - update status";
							status = "error";
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							logger.error(sb);
						}
						
				        
					}else if ("UL".equals(mode)) {
						if(rulerLord.isValidInputUpdateLight(dao, user, mode)){
							logger.warn("syst:" + dao.getSvih_syst());
							logger.warn("syst2:" + dao.getSvih_syst2());
							logger.warn("mrn:" + dao.getSvih_mrn());
							logger.warn("lrn:" + dao.getSvih_lrn());
							dmlRetval = svihDaoServices.updateLight(dao, dbErrorStackTrace);
						}else {
							// write JSON error output
							errMsg = "ERROR on UPDATE: invalid rulerLord error - update light";
							status = "error";
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							logger.error(sb);
						}
					}else {
						// write JSON error output
						errMsg = "ERROR on UPDATE mode: " + mode + " is not implemented yet...";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
						logger.error(sb);
					}
				} else {
					// write JSON error output
					errMsg = "ERROR on INSERT/UPDATE: invalid rulerLord, error";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					logger.error(sb);
				}
				
				// ----------------------------------
				// check returns from dml operations
				// ----------------------------------
				if (dmlRetval < 0) {
					// write JSON error output
					errMsg = "ERROR on ADD/UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					logger.error(sb);
				} else {
					// OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, dao, status));
				}

			} else {
				// write JSON error output
				errMsg = "ERROR on ADD/UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				logger.error(sb);
			}
			
		}catch(Exception e){
			//write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			logger.info(sb);
			logger.error(":::ERROR:::",e);
			errMsg = "ERROR on ADD/UPDATE:  error="+e.getMessage();
			status = "error";
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));

		}

		session.invalidate();
		return sb.toString();

	}

	
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private SvihDaoServices svihDaoServices;
	public void setSvihDaoServices (SvihDaoServices value){ this.svihDaoServices = value; }
	public SvihDaoServices getSvihDaoServices(){ return this.svihDaoServices; }
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

