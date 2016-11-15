package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
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
import no.systema.jservices.controller.rules.SYCUNDFR_U;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.CundfDao;
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.CundfDaoServices;


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * 
 * Listing Kunder and adding/updating/delete of Kunder int table: CUNDF
 * 
 * @author oscardelatorre
 * @date Aug 15, 2016
 * 
 */

@Controller
public class JsonResponseOutputterController_CUNDF {
	private static Logger logger = Logger.getLogger(JsonResponseOutputterController_CUNDF.class.getName());
	
	/**
	 *
	 * 	 File: 		CUNDF
	 * 	 PGM:		?		
	 * 	 Member: 	MAINT - AVD - Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR&kundnr=1
	 * @Example SELECT specific with unique firm: http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR&kundnr=1&firma=SY
	 * @Example SELECT specific with name: http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR&knavn=SYS&firma=SY
	 * 
	 */
	@RequestMapping(value="syjsSYCUNDFR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYCUNDFR.do");
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
				CundfDao dao = new CundfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				if(dao.getKundnr()!=null && !"".equals(dao.getKundnr())){
					if(dao.getFirma()!=null && !"".equals(dao.getFirma())){
						logger.info("Before findById ...");
						list = this.cundfDaoServices.findById(dao.getKundnr(), dao.getFirma(), dbErrorStackTrace);
					}else{
						logger.info("Before findById ...");
						list = this.cundfDaoServices.findById(dao.getKundnr(), dbErrorStackTrace);
					}
				}else if (dao.getKnavn()!=null && !"".equals(dao.getKnavn())){
					if(dao.getFirma()!=null && !"".equals(dao.getFirma())){
						logger.info("Before findByName ...");
						list = this.cundfDaoServices.findByName(dao.getKnavn(), dao.getFirma(), dbErrorStackTrace);
					}else{
						logger.info("Before findByName ...");
						list = this.cundfDaoServices.findByName(dao.getKnavn(), dbErrorStackTrace);
					}
				}else{
					logger.info("Before getList ...");
					list = this.cundfDaoServices.getList(dbErrorStackTrace);
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
	 * 	 File: 		CUNDF
	 * 	 PGM:		VKIND
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicestn/syjsSYCUNDFR_U.do?user=OSCAR&mode=U&xxx=yyy...
	 * 
	 * @Example ADD:
	 * 			http://gw.systema.no:8080/syjservicestn/syjsSYCUNDFR_U.do?user=OSCAR&mode=A&xxx=yyy....
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsSYCUNDFR_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsCundf_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();	

		//TODO: Mycket kvar!!!
		
		
		try{
			logger.info("Inside syjsSYCUNDFR_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			CundfDao dao = new CundfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SYCUNDFR_U rulerLord = new SYCUNDFR_U();  //TODO: evolve....
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){ 
						dmlRetval = this.cundfDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
					if (rulerLord.isValidInput(dao, userName, mode)) {
						rulerLord.updateNumericFieldsIfNull(dao); 
						if ("A".equals(mode)) {
							dmlRetval = this.cundfDaoServices.insert(dao, dbErrorStackTrace);
						} else if ("U".equals(mode)) {
							dmlRetval = this.cundfDaoServices.update(dao, dbErrorStackTrace);
						}
					} else {
						// write JSON error output
						errMsg = "ERROR on ADD/UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}
				//----------------------------------
				//check returns from dml operations
				//----------------------------------
				if(dmlRetval<0){
					//write JSON error output
					errMsg = "ERROR on ADD/UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}else{
					//OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}
				
			}else{
				//write JSON error output
				errMsg = "ERROR on ADD/UPDATE";
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
	@Qualifier ("cundfDaoServices")
	private CundfDaoServices cundfDaoServices;
	@Autowired
	@Required
	public void setCundfDaoServices (CundfDaoServices value){ this.cundfDaoServices = value; }
	public CundfDaoServices getCundfDaoServices(){ return this.cundfDaoServices; }


	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

