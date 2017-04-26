package no.systema.jservices.bcore.z.maintenance.controller.skat;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//rules
import no.systema.jservices.bcore.z.maintenance.controller.rules.skat.DKI051R_U;
//Application
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.skat.DkiaDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.skat.DkiaDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.EdiiDaoServices;


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Apr 26, 2017
 * 
 */

@Controller
public class BcoreMaintResponseOutputterControllerSkatImport_AVD_DKIA {
	private static Logger logger = Logger.getLogger(BcoreMaintResponseOutputterControllerSkatImport_AVD_DKIA.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		Dkia
	 * 	 PGM:		DKI051R 
	 * 	 Member: 	MAINT - SKAT IMPORT AVD - Dkia  - Maintenance - SELECT LIST  or SELECT SPECIFIC 
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsDKI051R.do?user=OSCAR&
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsDKI051R.do?user=OSCAR&dkea_syav=1...
	 * 
	 */
	@RequestMapping(value="syjsDKI051R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsDKI051R.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			//String validAvd = request.getParameter("va");
			String id = request.getParameter("id");  //DKXSTD or DKXSTD_FHV, for correct selection
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				DkiaDao dao = new DkiaDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				if ( (dao.getDkia_syav() != null && !"".equals(dao.getDkia_syav())) && 
					 (dao.getDkia_syop() != null && !"".equals(dao.getDkia_syop())) ) {
					 logger.info("findById...");
					 list = this.dkiaDaoServices.findById(dao, dbErrorStackTrace);
				} else {
					logger.info("getList...");
					list = this.dkiaDaoServices.getList(dbErrorStackTrace);
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
	 * File: 	Dkia
	 * PGM:		Dki051R_U 
	 * Member: 	MAINT SKAT IMPORT - AVD , Maintenance - DML operations
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicesbcore/syjsDKI051R_U.do?user=OSCAR&dkia_syav=1&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsDKI051R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsDKI051R_U.do");
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
			DkiaDao dao = new DkiaDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            
            //rules
            DKI051R_U rulerLord = new DKI051R_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					logger.info("Before DELETE ...");
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.dkiaDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode )){
						List<DkiaDao> list = new ArrayList<DkiaDao>();
						//must complete numeric values to avoid <null> on those
						// NA rulerLord.adjustNumericFields(dao);
						//do ADD
						if("A".equals(mode)){
							logger.info("Before INSERT ...");
							list = this.dkiaDaoServices.findById(dao, dbErrorStackTrace);
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								//write JSON error output
								errMsg = "ERROR on UPDATE: Record exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								logger.info("Before INSERT ...");
								dmlRetval = this.dkiaDaoServices.insert(dao, dbErrorStackTrace);
							}
							
						}else if("U".equals(mode)){
							logger.info("Before UPDATE ...");
							dmlRetval = this.dkiaDaoServices.update(dao, dbErrorStackTrace);
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
	@Qualifier ("dkiaDaoServices")
	private DkiaDaoServices dkiaDaoServices;
	@Autowired
	@Required
	public void setDkiaDaoServices (DkiaDaoServices value){ this.dkiaDaoServices = value; }
	public DkiaDaoServices getDkiaDaoServices(){ return this.dkiaDaoServices; }

	
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

