package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.jservices.controller.rules.TVINFR_U;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.TvinfDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.TvinfDaoServices;



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
public class JsonResponseOutputterController_TVINF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_TVINF.class.getName());
	
	/**
	 * Source:
	 * 	 File: 		TVINF
	 * 	 
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsTVINFR.do?user=OSCAR...
	 * 
	 */
	@RequestMapping(value="syjsTVINFR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsEDIMR( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsTVINFR");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				TvinfDao dao = new TvinfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            
	           	//normal process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//phantom return
					logger.info("phantom return...");
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, new ArrayList()));
				}
            
			}else{
				//write JSON error output
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
			
		}catch(Exception e){
			//write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
		return sb.toString();
	}
	
	
	/**
	 * 
	 * Update Database DML operations
	 * 	 File: 	TVINF
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsTVINFR_U.do?user=OSCAR&mode=U&xxx=yyy...
	 * 
	 * @Example ADD:
	 * 			http://gw.systema.no:8080/syjservicesbcore/syjsTVINFR_U.do?user=OSCAR&mode=A&xxx=yyy....
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsTVINFR_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsTvinf_U( HttpSession session, HttpServletRequest request, Locale locale) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();	

		StringBuffer dbErrorStackTrace = new StringBuffer();
        String errMsg = "";
		String status = "ok";
		String userName = null;
		
		try{
			logger.warn("Inside syjsTVINFR_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            userName = this.bridfDaoServices.findNameById(user);
			
			//bind attributes is any
            TvinfDao dao = new TvinfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            
            logger.warn("DAO="+ReflectionToStringBuilder.toString(dao));
   
            //rules
            TVINFR_U rulerLord = new TVINFR_U(); 
			//Start processing now
			if (userName != null) {
				int dmlRetval = 0;
				
				if (rulerLord.isValidInput( userName, mode)) {
					if ("A".equals(mode)) {
						if(rulerLord.isValidInputInsert(dao, user, mode)){
							dmlRetval = tvinfDaoServices.insert(dao, dbErrorStackTrace);
						}else {
							// write JSON error output
							errMsg = "ERROR on INSERT: invalid rulerLord error";
							status = "error";
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							logger.error(sb.toString());
						}
					} else if ("U".equals(mode)) {
						/*
						if(rulerLord.isValidInputUpdate(dao, user, mode)){
							//dmlRetval = edisDaoServices.update(dao, dbErrorStackTrace);
						}else {
							// write JSON error output
							errMsg = "ERROR on UPDATE: invalid rulerLord error";
							status = "error";
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							logger.error(sb.toString());
						}*/
						errMsg = "ERROR on UPDATE: not implemented";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
						logger.error(sb.toString());
				        
					}
				} else {
					// write JSON error output
					errMsg = "ERROR on INSERT/UPDATE: invalid rulerLord, error";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					logger.error(sb.toString());
				}
				
				// ----------------------------------
				// check returns from dml operations
				// ----------------------------------
				if (dmlRetval < 0) {
					// write JSON error output
					errMsg = "ERROR on ADD/UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					logger.error(sb.toString());
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
				logger.error(sb.toString());
			}
			
		}catch(Exception e){
			//write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			logger.info(sb.toString());
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
	private TvinfDaoServices tvinfDaoServices;
	public void setTvinfDaoServices (TvinfDaoServices value){ this.tvinfDaoServices = value; }
	public TvinfDaoServices getTvinfDaoServices(){ return this.tvinfDaoServices; }
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

