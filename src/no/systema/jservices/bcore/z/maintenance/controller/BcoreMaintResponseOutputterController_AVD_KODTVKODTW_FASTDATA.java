package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


 
import org.apache.logging.log4j.*;
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
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtvKodtwDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtpUtskrsDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KodtpUtskrsDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KodtvKodtwDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.UtskrsDaoServices;

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
//rules
import no.systema.jservices.bcore.z.maintenance.controller.rules.SYFA28R_U;



/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Aug 5, 2016
 * 
 */

@Controller
public class BcoreMaintResponseOutputterController_AVD_KODTVKODTW_FASTDATA {
	private static Logger logger = LogManager.getLogger(BcoreMaintResponseOutputterController_AVD_KODTVKODTW_FASTDATA.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		KODTW
	 * 	 PGM:		SYFA28
	 * 	 Member: 	MAINT - AVD - Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28R.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28R.do?user=OSCAR&kowavd=1
	 * 
	 */
	@RequestMapping(value="syjsSYFA28R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFA28R.do");
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
				KodtvKodtwDao dao = new KodtvKodtwDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				if(dao.getKovavd()!=null && !"".equals(dao.getKovavd())){
					logger.info("findById...");
					list = this.kodtvKodtwDaoServices.findById(dao.getKovavd(), dbErrorStackTrace);
				}else{
					
					list = this.kodtvKodtwDaoServices.getList(dbErrorStackTrace);
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
	 * File: 	KODTW
	 * PGM:		SYFA28
	 * Member: 	MAINT AVD Maintenance - SELECT LIST or SELECT SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28R_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSYFA28R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFA28R_U.do");
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
			KodtvKodtwDao dao = new KodtvKodtwDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SYFA28R_U rulerLord = new SYFA28R_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					/* N/A
					logger.info("Before DELETE ...");
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.kodtvKodtwDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}*/
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode)){
						List<KodtvKodtwDao> list = new ArrayList<KodtvKodtwDao>();
						//must complete numeric values to avoid <null> on those
						rulerLord.updateNumericFieldsIfNull(dao);
						
						//do ADD
						if("A".equals(mode)){
							logger.info("Before INSERT ...");
							list = this.kodtvKodtwDaoServices.findById(dao.getKovavd(), dbErrorStackTrace);
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								//write JSON error output
								errMsg = "ERROR on UPDATE: Record exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								dmlRetval = this.kodtvKodtwDaoServices.insert(dao, dbErrorStackTrace);
								if(dmlRetval>=0){
									//create list of UTSKRS-table-records in KODTP-table
									List<KodtpUtskrsDao> utskrsListSource = this.utskrsDaoServices.getList(dbErrorStackTrace);
									logger.info("starting to create records for batchUpdate in KODTP-table...");
									List <KodtpUtskrsDao> utskrsListTarget = new ArrayList<KodtpUtskrsDao>();
									for(KodtpUtskrsDao record : utskrsListSource){
										record.setKopavd(dao.getKovavd());
										record.setKopnvn("*JOB");
										utskrsListTarget.add(record);
										//logger.info("X" + record.getUtpnr() + "X");
										//create record N/A-->this.kodtpUtskrsDaoServices.insert(record, dbErrorStackTrace);
									}
									//now do the batch insert
									this.kodtpUtskrsDaoServices.insertBatch(utskrsListTarget, dbErrorStackTrace);
								}
								
							}
						}else if("U".equals(mode)){
							logger.info("Before UPDATE ...");
							dmlRetval = this.kodtvKodtwDaoServices.update(dao, dbErrorStackTrace);
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
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
	@Qualifier ("kodtvKodtwDaoServices")
	private KodtvKodtwDaoServices kodtvKodtwDaoServices;
	@Autowired
	@Required
	public void setKodtvKodtwDaoServices (KodtvKodtwDaoServices value){ this.kodtvKodtwDaoServices = value; }
	public KodtvKodtwDaoServices getKodtvKodtwDaoServices(){ return this.kodtvKodtwDaoServices; }

	@Qualifier ("utskrsDaoServices")
	private UtskrsDaoServices utskrsDaoServices;
	@Autowired
	@Required
	public void setUtskrsDaoServices (UtskrsDaoServices value){ this.utskrsDaoServices = value; }
	public UtskrsDaoServices getUtskrsDaoServices(){ return this.utskrsDaoServices; }

	@Qualifier ("kodtpUtskrsDaoServices")
	private KodtpUtskrsDaoServices kodtpUtskrsDaoServices;
	@Autowired
	@Required
	public void setKodtpUtskrsDaoServices (KodtpUtskrsDaoServices value){ this.kodtpUtskrsDaoServices = value; }
	public KodtpUtskrsDaoServices getKodtpUtskrsDaoServices(){ return this.kodtpUtskrsDaoServices; }

	
}

