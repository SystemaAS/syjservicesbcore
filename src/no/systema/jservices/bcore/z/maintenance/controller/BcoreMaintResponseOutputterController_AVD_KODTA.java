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
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KodtaDaoServices;

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
//rules
import no.systema.jservices.bcore.z.maintenance.controller.rules.SYFA14R_U;





/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Aug 1, 2016
 * 
 */

@Controller
public class BcoreMaintResponseOutputterController_AVD_KODTA {
	private static Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_AVD_KODTA.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		KODTA
	 * 	 PGM:		SYFA14
	 * 	 Member: 	MAINT - AVD - Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R.do?user=OSCAR&koaavd=1
	 * 
	 */
	@RequestMapping(value="syjsSYFA14R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFA14R.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String sadImportAvdList = request.getParameter("sialist"); //SadImportAvdlist
			String sadExportAvdList = request.getParameter("sealist"); //SadExportAvdlist
			String sadNctsImportAvdList = request.getParameter("nialist"); //SadNctsImportAvdlist
			String sadNctsExportAvdList = request.getParameter("nealist"); //SadNctsExportAvdlist
			//
			String skatImportAvdList = request.getParameter("ssialist"); //SkatImportAvdlist / TdsImport
			String skatExportAvdList = request.getParameter("ssealist"); //SkatExportAvdlist / TdsExport
			String skatNctsImportAvdList = request.getParameter("snialist"); //SkatNctsImportAvdlist
			String skatNctsExportAvdList = request.getParameter("snealist"); //SkatNctsExportAvdlist
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				KodtaDao dao = new KodtaDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				if(dao.getKoaavd()!=null && !"".equals(dao.getKoaavd())){
					list = this.kodtaDaoServices.findById(dao.getKoaavd(), dbErrorStackTrace);
				}else{
					//TVINN 
					if(sadImportAvdList!=null && !"".equals(sadImportAvdList)){
						logger.info("Before getListForAvailableAvdTvinnSadImport ...");
						list = this.kodtaDaoServices.getListForAvailableAvdTvinnSadImport(dbErrorStackTrace);
					
					}else if(sadExportAvdList!=null && !"".equals(sadExportAvdList)){
						logger.info("Before getListForAvailableAvdTvinnSadExport ...");
						list = this.kodtaDaoServices.getListForAvailableAvdTvinnSadExport(dbErrorStackTrace);
					
					}else if(sadNctsImportAvdList!=null && !"".equals(sadNctsImportAvdList)){
						logger.info("Before getListForAvailableAvdTvinnSadNctsImport ...");
						list = this.kodtaDaoServices.getListForAvailableAvdTvinnSadNctsImport(dbErrorStackTrace);
					
					}else if(sadNctsExportAvdList!=null && !"".equals(sadNctsExportAvdList)){
						logger.info("Before getListForAvailableAvdTvinnSadNctsExport ...");
						list = this.kodtaDaoServices.getListForAvailableAvdTvinnSadNctsExport(dbErrorStackTrace);
						
					//SKAT	
					}else if(skatNctsImportAvdList!=null && !"".equals(skatNctsImportAvdList)){
						logger.info("Before getListForAvailableAvdSkatNctsImport ...");
						list = this.kodtaDaoServices.getListForAvailableAvdSkatNctsImport(dbErrorStackTrace);
					
					}else if(skatNctsExportAvdList!=null && !"".equals(skatNctsExportAvdList)){
						logger.info("Before getListForAvailableAvdSkatNctsExport ...");
						list = this.kodtaDaoServices.getListForAvailableAvdSkatNctsExport(dbErrorStackTrace);
					
					}else{
						logger.info("Before getList ...");
						list = this.kodtaDaoServices.getList(dbErrorStackTrace);
					}
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
	 * File: 	KODTA, subtables: NAVAVD, KODTASID
	 * PGM:		SYFA14
	 * Member: 	MAINT AVD Maintenance - SELECT LIST or SELECT SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSYFA4R_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSYFA14R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFA14R_U.do");
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
			KodtaDao dao = new KodtaDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SYFA14R_U rulerLord = new SYFA14R_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					logger.info("Before DELETE ...");
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.kodtaDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode)){
						List<KodtaDao> list = new ArrayList<KodtaDao>();
						//must complete numeric values to avoid <null> on those
						rulerLord.updateNumericFieldsIfNull(dao);
						
						//do ADD
						if("A".equals(mode)){
							logger.info("Before INSERT ...");
							list = this.kodtaDaoServices.findById(dao.getKoaavd(), dbErrorStackTrace);
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								//write JSON error output
								errMsg = "ERROR on UPDATE: Record exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								dmlRetval = this.kodtaDaoServices.insert(dao, dbErrorStackTrace);
							}
						}else if("U".equals(mode)){
							logger.info("Before UPDATE ...");
							dmlRetval = this.kodtaDaoServices.update(dao, dbErrorStackTrace);
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
	@Qualifier ("kodtaDaoServices")
	private KodtaDaoServices kodtaDaoServices;
	@Autowired
	@Required
	public void setKodtaDaoServices (KodtaDaoServices value){ this.kodtaDaoServices = value; }
	public KodtaDaoServices getKodtaDaoServices(){ return this.kodtaDaoServices; }


	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

