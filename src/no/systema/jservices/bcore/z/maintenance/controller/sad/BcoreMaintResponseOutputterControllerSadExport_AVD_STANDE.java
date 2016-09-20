package no.systema.jservices.bcore.z.maintenance.controller.sad;

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
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.StandeDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.sad.StandeDaoServices;

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.EdiiDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
//rules
import no.systema.jservices.bcore.z.maintenance.controller.rules.sad.SYFTAAAER_U;


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Sep 16, 2016
 * 
 */

@Controller
public class BcoreMaintResponseOutputterControllerSadExport_AVD_STANDE {
	private static Logger logger = Logger.getLogger(BcoreMaintResponseOutputterControllerSadExport_AVD_STANDE.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		STANDE
	 * 	 PGM:		SYFTAAA - SYFTAE 
	 * 	 Member: 	MAINT - SAD EKSPORT AVD - STANDE  - Maintenance - SELECT LIST  or SELECT SPECIFIC 
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYFTAAAER.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsSYFTAAAER.do?user=OSCAR&seavd=1
	 * 
	 */
	@RequestMapping(value="syjsSYFTAAAER.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFTAAAR.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String validAvd = request.getParameter("va");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				StandeDao dao = new StandeDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				if( (dao.getSeavd()!=null && !"".equals(dao.getSeavd())) ){
					logger.info("findById...");
					list = this.standeDaoServices.findById(dao.getSeavd(), dbErrorStackTrace);
				}else{
					logger.info("getList...");
					list = this.standeDaoServices.getList(dbErrorStackTrace);
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
	 * File: 	STANDE
	 * PGM:		SYFTAAAER_U 
	 * Member: 	MAINT SAD EXPORT - AVD , Maintenance - DML operations
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSYFTAAAER_U.do?user=OSCAR&seavd=1&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSYFTAAAER_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFTAAAER_U.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			StringBuffer validatorStackTrace = new StringBuffer();
			
			
			//bind attributes is any
			StandeDao dao = new StandeDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            
            //rules
            SYFTAAAER_U rulerLord = new SYFTAAAER_U(this.ediiDaoServices);
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					logger.info("Before DELETE ...");
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.standeDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode )){
						List<StandeDao> list = new ArrayList<StandeDao>();
						//must complete numeric values to avoid <null> on those
						rulerLord.adjustNumericFields(dao);
						//do ADD
						if("A".equals(mode)){
							logger.info("Before INSERT ...");
							list = this.standeDaoServices.findById(dao.getSeavd(), dbErrorStackTrace);
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								//write JSON error output
								errMsg = "ERROR on UPDATE: Record exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								logger.info("Before INSERT ...");
								dmlRetval = this.standeDaoServices.insert(dao, dbErrorStackTrace);
							}
							
						}else if("U".equals(mode)){
							logger.info("Before UPDATE ...");
							dmlRetval = this.standeDaoServices.update(dao, dbErrorStackTrace);
						}
						
				  }else{
						//write JSON error output
						errMsg = "ERROR RULER LORD:" +  "<b>" + rulerLord.getValidatorStackTrace() + "</br>" ;
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg , status, dbErrorStackTrace));
						logger.info(sb.toString());
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
	@Qualifier ("standeDaoServices")
	private StandeDaoServices standeDaoServices;
	@Autowired
	@Required
	public void setStandiDaoServices (StandeDaoServices value){ this.standeDaoServices = value; }
	public StandeDaoServices getStandiDaoServices(){ return this.standeDaoServices; }

	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
	@Qualifier ("ediiDaoServices")
	private EdiiDaoServices ediiDaoServices;
	@Autowired
	@Required
	public void setEdiiDaoServices (EdiiDaoServices value){ this.ediiDaoServices = value; }
	public EdiiDaoServices getEdiiDaoServices(){ return this.ediiDaoServices; }

	
}

