package no.systema.jservices.controller;

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


//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.HedummyDaoServices;
import no.systema.jservices.model.dao.entities.HedummyDao;
import no.systema.jservices.model.dao.entities.IDao;


import no.systema.jservices.jsonwriter.JsonResponseWriter;
//rules
import no.systema.jservices.controller.rules.SYEDIIR_U;



/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Dec 20, 2017
 * 
 */

@Controller
public class JsonResponseOutputterController_HEDUMMY {
	private static Logger logger = Logger.getLogger(JsonResponseOutputterController_HEDUMMY.class.getName());
	
	/**
	 *
	 * 	 File: 		SYSPEDF.HEDUMMY with MEMBER: URA (BIL IMPORT) 
	 * 	 PGM:		?		
	 * 	 Member: 	URA - Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYHEDUMMY_URAR.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsSYHEDUMMY_URAR.do?user=OSCAR&heavd=1
	 * 
	 */
	@RequestMapping(value="syjsSYHEDUMMY_URAR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYHEDUMMY_URAR.do");
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
				HedummyDao dao = new HedummyDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List<IDao> list = null;
				//-----------------------------
	            //prepare service (mandatory)
	            //-----------------------------
	            this.hedummyDaoServices.setLibrary("syspedf");
	            this.hedummyDaoServices.setAlias("ura_alias");
	            this.hedummyDaoServices.setMemberTable("hedummy");
	            this.hedummyDaoServices.setMember("ura");
	            
	            //do SELECT
				logger.info("Before SELECT ...");
				if(dao.getHeavd() != null && !"".equals(dao.getHeavd())){
					logger.info("Before findById ...");
					list = this.hedummyDaoServices.findById(dao.getHeavd(), dbErrorStackTrace);
					//logger.info("SIZE:" + list.size());
				}else{
					logger.info("Before getList ...");
					list = this.hedummyDaoServices.getList(dbErrorStackTrace);
					//logger.info("SIZE:" + list.size());
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
	 * 	 File: 		SYSPEDF.HEDUMMY with MEMBER: URB (BIL EXPORT) 
	 * 	 PGM:		?		
	 * 	 Member: 	URA - Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYHEDUMMY_URBR.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsSYHEDUMMY_URBR.do?user=OSCAR&heavd=1
	 * 
	 */
	
	@RequestMapping(value="syjsSYHEDUMMY_URBR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList_URB( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYHEDUMMY_URBR.do");
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
				HedummyDao dao = new HedummyDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List<IDao> list = null;
				//-----------------------------
	            //prepare service (mandatory)
	            //-----------------------------
	            this.hedummyDaoServices.setLibrary("syspedf");
	            this.hedummyDaoServices.setAlias("urb_alias");
	            this.hedummyDaoServices.setMemberTable("hedummy");
	            this.hedummyDaoServices.setMember("urb");
	            
	            //do SELECT
				logger.info("Before SELECT ...");
				if(dao.getHeavd() != null && !"".equals(dao.getHeavd())){
					logger.info("Before findById ...");
					list = this.hedummyDaoServices.findById(dao.getHeavd(), dbErrorStackTrace);
					//logger.info("SIZE:" + list.size());
				}else{
					logger.info("Before getList ...");
					list = this.hedummyDaoServices.getList(dbErrorStackTrace);
					//logger.info("SIZE:" + list.size());
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
	
	
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("hedummyDaoServices")
	private HedummyDaoServices hedummyDaoServices;
	@Autowired
	@Required
	public void setHedummyDaoServices (HedummyDaoServices value){ this.hedummyDaoServices = value; }
	public HedummyDaoServices getHedummyDaoServices(){ return this.hedummyDaoServices; }
	

	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

