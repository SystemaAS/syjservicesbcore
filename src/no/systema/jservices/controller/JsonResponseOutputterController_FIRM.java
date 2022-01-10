package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


 
import org.slf4j.*;
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
import no.systema.jservices.model.dao.services.FirmLoginDaoServices;
import no.systema.jservices.model.dao.entities.FirmDao;
import no.systema.jservices.jsonwriter.JsonResponseWriter;






/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Jan 26, 2017
 * 
 */

@Controller
public class JsonResponseOutputterController_FIRM {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_FIRM.class.getName());
	
	/**
	 *
	 * 	 File: 		FIRM
	 * 	 PGM:		?		
	 * 	 Member: 	SELECT Code for LOGIN
	 * 
	 * This class is used before an espedsg login. We must have a valid company code to send to the login service
	 * Here is where we get it.
	 * This class will be necessary as long as we use AS400 service for login. Otherwise this will not be necessary
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYFIRMLOGIN.do
	 * 
	 */
	@RequestMapping(value="syjsSYFIRMLOGIN.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFIRMLOGIN.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			//String user = request.getParameter("user");
			
			//Check ALWAYS user in BRIDF
            //String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			//if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				FirmDao dao = new FirmDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            String companyCode = null;
	            String tradevisionFlag = null;
				//do SELECT
				logger.info("Before SELECT ...");
				companyCode  = this.firmDaoServices.getCompanyCode();
				tradevisionFlag = this.firmDaoServices.getTradevisionFlag();
				//process result
				if (companyCode!=null){
					//write the final JSON output
					//sb.append(jsonWriter.setJsonResult_Common_GetField("fifirm", companyCode));
					//if(tradevisionFlag==null){ tradevisionFlag = ""; }
					sb.append(jsonWriter.setJsonResult_Common_GetField("fifirm", companyCode, "fitdvi", tradevisionFlag));
					
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getCompanyCode";
					status = "error";
					logger.info("After SELECT:" + " " + status + errMsg );
					sb.append(jsonWriter.setJsonSimpleErrorResult("none User", errMsg, status, dbErrorStackTrace));
				}
			
			/*	
			}else{
				//write JSON error output
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}*/

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
	@Qualifier ("firmDaoServices")
	private FirmLoginDaoServices firmDaoServices;
	@Autowired
	@Required
	public void setFirmDaoServices (FirmLoginDaoServices value){ this.firmDaoServices = value; }
	public FirmLoginDaoServices getFirmDaoServices(){ return this.firmDaoServices; }


	
}

