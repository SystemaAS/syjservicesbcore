package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

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
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.EdimDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.EdimDaoServices;



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
public class JsonResponseOutputterController_EDIM {
	private static Logger logger = Logger.getLogger(JsonResponseOutputterController_EDIM.class.getName());
	
	/**
	 * Source:
	 * 	 File: 		EDIM
	 * 	 
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsEDIMR.do?user=OSCAR...
	 * 
	 */
	@RequestMapping(value="syjsEDIMR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsEDI42R( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsEDIMR");
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
				EdimDao dao = new EdimDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            /*if( dao.getSssn()!=null && !"".equals(dao.getSssn()) ){
					logger.info("findById");
					list = this.edimDaoServices.findById(dao.getSssn(), dbErrorStackTrace);
				}else{
					logger.info("getList (all)");
					list = this.edimDaoServices.getList(dbErrorStackTrace);
				}*/
				//process result
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
	 * 	 File: 	EDIM
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsEDIMR_U.do?user=OSCAR&mode=U&xxx=yyy...
	 * 
	 * @Example ADD:
	 * 			http://gw.systema.no:8080/syjservicesbcore/syjsEDIMR_U.do?user=OSCAR&mode=A&xxx=yyy....
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsEDIMR_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsCundf_U( HttpSession session, HttpServletRequest request, Locale locale) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();	

		StringBuffer dbErrorStackTrace = new StringBuffer();
        String errMsg = "";
		String status = "ok";
		String userName = null;
		
		try{
			logger.info("Inside syjsEDIMR_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            userName = this.bridfDaoServices.findNameById(user);
			
			//bind attributes is any
			EdimDao dao = new EdimDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            
            logger.info("DAO="+ReflectionToStringBuilder.toString(dao));
   
            //rules
            EDIMR_U rulerLord = null; 
			//Start processing now
			if (userName != null) {
				int dmlRetval = 0;
				
				if (rulerLord.isValidInput(dao, userName, mode)) {
					if ("A".equals(mode)) {
						dmlRetval = edimDaoServices.insert(dao, dbErrorStackTrace);
				        
					} else if ("U".equals(mode)) {
				        dmlRetval = edimDaoServices.update(dao, dbErrorStackTrace);
				        
					}
				} else {
					// write JSON error output
					errMsg = "ERROR on ADD/UPDATE: invalid rulerLord, error="+sb.toString();
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
	private EdimDaoServices edimDaoServices;
	public void setEdimDaoServices (EdimDaoServices value){ this.edimDaoServices = value; }
	public EdimDaoServices getEdimDaoServices(){ return this.edimDaoServices; }
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

