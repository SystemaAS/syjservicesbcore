package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Application
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtpUtskrsDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KodtpUtskrsDaoServices;

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
//rules
import no.systema.jservices.bcore.z.maintenance.controller.rules.SYFA28ChildR_U;


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Aug 9, 2016
 * 
 */

@Controller
public class BcoreMaintResponseOutputterController_AVD_KODTPUTSKRS_FASTDATA {
	private static Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_AVD_KODTPUTSKRS_FASTDATA.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		KODTP/UTSKRS
	 * 	 PGM:		SYFA28 Child (Del-2)
	 * 	 Member: 	MAINT - AVD - FASTE DATA - Maintenance - SELECT LIST (per avd) or SELECT SPECIFIC (per avd & lnr)
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28ChildR.do?user=OSCAR&kopavd=1
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28ChildR.do?user=OSCAR&kopavd=1&koplnr=1
	 * 
	 */
	@RequestMapping(value="syjsSYFA28ChildR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFA28ChildR.do");
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
				KodtpUtskrsDao dao = new KodtpUtskrsDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				if( (dao.getKopavd()!=null && !"".equals(dao.getKopavd())) && (dao.getKoplnr()!=null && !"".equals(dao.getKoplnr())) ){
					logger.info("findById...");
					list = this.kodtpUtskrsDaoServices.findById(dao.getKopavd(), dao.getKoplnr(), dbErrorStackTrace);
				}else{
					logger.info("getList...");
					list = this.kodtpUtskrsDaoServices.getList(dao.getKopavd(), dbErrorStackTrace);
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
	 * File: 	KODTP/UTSKRS
	 * PGM:		SYFA28 Child (Del-2)
	 * Member: 	MAINT AVD - Del-2 Faste data, Maintenance - SELECT LIST or SELECT SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28ChildR_U.do?user=OSCAR&kopavd=1&koplnr=1&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSYFA28ChildR_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFA28ChildR_U.do");
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
			KodtpUtskrsDao dao = new KodtpUtskrsDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SYFA28ChildR_U rulerLord = new SYFA28ChildR_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					//N/A for this child
					
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode)){
						//must complete numeric values to avoid <null> on those
						rulerLord.updateNumericFieldsIfNull(dao);
						//do ADD
						if("A".equals(mode)){
							//N/A for this child
						
						}else if("U".equals(mode)){
							logger.info("Before UPDATE Child ...");
							dmlRetval = this.kodtpUtskrsDaoServices.updateChild(dao, dbErrorStackTrace);
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
	/**
	 * 
	 * Update Database DML operations
	 * File: 	KODTP/UTSKRS
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28DPTAvdR_U.do?user=OSCAR&=1&originalAvd=1&originalLnr=1&fromAvd=333&toAvd=333&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsSYFA28DPTAvdR_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsDuplicateR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFA28DPTAvdR_U.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Avd information
			String originalAvd = request.getParameter("originalAvd");
			String originalLnr = request.getParameter("originalLnr");
			String fromAvd = request.getParameter("fromAvd");
			String toAvd = request.getParameter("toAvd");
			//get list of avd to update
			List<String> targetAvdList = this.getTargetAvdList(fromAvd, toAvd);
			
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			KodtpUtskrsDao dao = new KodtpUtskrsDao();
			//rules
            SYFA28ChildR_U rulerLord = new SYFA28ChildR_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				//Get the source record to get all values
				logger.info("Before SELECT ...");
				if( (originalAvd!=null && !"".equals(originalAvd)) && (originalLnr!=null && !"".equals(originalLnr)) ){
					logger.info("findById...");
					List<KodtpUtskrsDao> tmpList = this.kodtpUtskrsDaoServices.findById(originalAvd, originalLnr, dbErrorStackTrace);
					for (KodtpUtskrsDao record : tmpList){
						dao = record;
					}
				}
				//validate
				if(rulerLord.isValidInput(dao, userName, mode)){
					//must complete numeric values to avoid <null> on those
					rulerLord.updateNumericFieldsIfNull(dao);
					//update each avd
					for(String targetAvd: targetAvdList){
						//key values
						dao.setKopavd(targetAvd);
						dao.setKoplnr(originalLnr);
						//UPDATE
						dmlRetval = this.kodtpUtskrsDaoServices.updateChild(dao, dbErrorStackTrace);
					}
				}else{
					//write JSON error output
					errMsg = "ERROR on UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
					status = "error";
					dbErrorStackTrace.append(errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
				//----------------------------------
				//check returns from dml operations
				//----------------------------------
				if(dmlRetval<0){
					//write JSON error output
					errMsg = "ERROR on UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					dbErrorStackTrace.append(errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}else{
					//OK UPDATE
					logger.info("UPDATE on Dupliser = OK");
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
	/**
	 * 
	 * @param fromAvd
	 * @param toAvd
	 * @return
	 */
	private List<String> getTargetAvdList(String fromAvd, String toAvd){
		List<String> list = new ArrayList();
		int lLimit = 0;
		int uLimit = 0;
		try{
			lLimit = Integer.valueOf(fromAvd);
			uLimit = Integer.valueOf(toAvd);
			for (Integer x = lLimit; x<=uLimit; x++){
				list.add(x.toString());
			}
			
		}catch(Exception e){
			//TODO
		}
		
		return list;
	}
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("kodtpUtskrsDaoServices")
	private KodtpUtskrsDaoServices kodtpUtskrsDaoServices;
	@Autowired
	@Required
	public void setKodtpUtskrsDaoServices (KodtpUtskrsDaoServices value){ this.kodtpUtskrsDaoServices = value; }
	public KodtpUtskrsDaoServices getKodtpUtskrsDaoServices(){ return this.kodtpUtskrsDaoServices; }


	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

