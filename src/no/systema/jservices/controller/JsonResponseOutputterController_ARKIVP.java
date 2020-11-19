package no.systema.jservices.controller;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.jservices.model.dao.services.ArkivpDaoServices;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.controller.rules.ARKIVP_U;
import no.systema.jservices.common.dao.ArkivpDao;
import no.systema.jservices.jsonwriter.JsonResponseWriterAux;

/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Nov 2020
 * 
 */

@Controller
public class JsonResponseOutputterController_ARKIVP {
	private static Logger logger = Logger.getLogger(JsonResponseOutputterController_ARKIVP.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		ARKIVP
	 * 	 Member: 	SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsARKIVP.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsARKIVP.do?user=OSCAR&arlink=2350cab2-98f0-4b54-a4f7-a2ae453e61bd
	 * 
	 */
	@RequestMapping(value="syjsARKIVP.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriterAux jsonWriter = new JsonResponseWriterAux();
		StringBuffer sb = new StringBuffer();

		try{
			logger.info("Inside syjsARKIVP");
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
				ArkivpDao dao = new ArkivpDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( StringUtils.isNotEmpty(dao.getArlink()) ){
					logger.warn("findById");
					list = this.arkivpDaoServices.findById(dao.getArlink(), dbErrorStackTrace);
				
	            }else if( this.isDoFind(dao) ){
					logger.warn("find");
					list = this.arkivpDaoServices.find(dao, dbErrorStackTrace);
				
	            }else{
					logger.warn("getList (all)");
					list = this.arkivpDaoServices.getList(dbErrorStackTrace);
				}

				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getList";
					status = "error";
					logger.warn("After SELECT:" + " " + status + errMsg );
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
	 * @param dao
	 * @return
	 */
	private boolean isDoFind(ArkivpDao dao){
		boolean retval = false;
		if(StringUtils.isNotEmpty(dao.getAruser()) || StringUtils.isNotEmpty(dao.getArrfk()) || 
				StringUtils.isNotEmpty(dao.getArbhis())  || !"0".equals(dao.getArdate()) ){
			retval = true;
		}
		
		return retval;
	}
	
	/**
	 * 
	 * Update Database DML operations
	 * File: 	ARKIVP
	 * Member: 	ARKIVP - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsARKIVP_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsARKIVP_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriterAux jsonWriter = new JsonResponseWriterAux();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsARKIVP_U.do");
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
			ArkivpDao dao = new ArkivpDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            ARKIVP_U rulerLord = new ARKIVP_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					//N/A
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode)){
						logger.info("Before UPDATE ...");
						//do ADD
						if("A".equals(mode)){
							dmlRetval = this.arkivpDaoServices.insert(dao, dbErrorStackTrace);
							
						}else if("U".equals(mode)){
							 //N/A
							 //dmlRetval = this.arkivpDaoServices.update(dao, dbErrorStackTrace);
							 
						}
						
				  }else{
						//write JSON error output
						errMsg = "ERROR on UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				  }
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
	private ArkivpDaoServices arkivpDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

