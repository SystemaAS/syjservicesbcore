package no.systema.cw1.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//Application
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.cw1.jservices.controller.rules.SADW_U;
import no.systema.cw1.jservices.dao.SadwDao;
import no.systema.cw1.jservices.services.SadwDaoServices;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
//rules


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date March, 2021
 * 
 */

@Controller
public class CwEdiResponseOutputterController_SADW {
	private static Logger logger = Logger.getLogger(CwEdiResponseOutputterController_SADW.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		SADW (alternative to SADV when motor vehicles are involved). Either this file is used or std SADV (item lines). Not both.
	 * 	 PGM:		N/A
	 * 	 Member: 	SADW - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/cw1SADW.do?user=OSCAR&swavd ... 
	 * 
	 */
	@RequestMapping(value="cw1SADW.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside cw1SADW");
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
				SadwDao dao = new SadwDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.warn("Before SELECT ...");
	            if( StringUtils.isNotEmpty(dao.getSwavd()) && StringUtils.isNotEmpty(dao.getSwtdn())){
	            	logger.warn("getList");
	            	list = this.sadwDaoServices.getList(dao.getSwavd(), dao.getSwtdn(), dbErrorStackTrace);
				}
				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getList";
					status = "error";
					logger.info("After SELECT:" + " " + status + errMsg );
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
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
	 * File: 	SADW
	 * PGM:		
	 * Member: 	CW1 EDI - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicesbcore/syjsSADW_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="cw1SADW_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside cw1SADW_U");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			SadwDao dao = new SadwDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("AVD:" + dao.getSwavd());
            logger.warn("OPD:" + dao.getSwtdn());
            //rules
            SADW_U rulerLord = new SADW_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("U".equals(mode)){
					if(rulerLord.isValidInput(dao, userName, mode)){
						logger.warn("calling update...");
						dmlRetval = this.sadwDaoServices.update(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE ALL: invalid?  Try to check: <DaoServices>.deleteAll";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  //N/A
				}
				//----------------------------------
				//check returns from dml operations
				//----------------------------------
				if(dmlRetval<0){
					//write JSON error output
					errMsg = "ERROR on UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}else{
					//OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}
				
			}else{
				//write JSON error output
				errMsg = "ERROR on UPDATE";
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
	
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private SadwDaoServices sadwDaoServices;
	public void setSadwDaoServices (SadwDaoServices value){ this.sadwDaoServices = value; }
	public SadwDaoServices getSadwDaoServices(){ return this.sadwDaoServices; }
	
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

