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

import no.systema.jservices.model.dao.services.FirmArcDaoServices;
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.model.dao.services.FirmLoginDaoServices;
import no.systema.jservices.model.dao.entities.FirmArcDao;
import no.systema.jservices.model.dao.entities.FirmDao;
import no.systema.jservices.jsonwriter.JsonResponseWriter;






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
public class JsonResponseOutputterController_FIRMARC {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_FIRMARC.class.getName());
	
	/**
	 *
	 * 	 File: 		FIRMARC
	 * 	 PGM:		?		
	 * 	 
	 * This class is used for archive definition purposes
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYFIRMARC.do
	 * 
	 */
	@RequestMapping(value="syjsSYFIRMARC.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFIRMARC.do");
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			//if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				FirmArcDao dao = new FirmArcDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            String archiveBasePath = null;
				//do SELECT
				logger.info("Before SELECT ...");
				archiveBasePath  = this.firmArcDaoServices.getArchiveBasePath();
				//process result
				if (archiveBasePath!=null){
					sb.append(jsonWriter.setJsonResult_Common_GetField_Container("arcane", archiveBasePath.trim()));
					
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: ?  Try to check: <DaoServices>.getArchiveBasePath";
					status = "error";
					logger.info("After SELECT:" + " " + status + errMsg );
					sb.append(jsonWriter.setJsonSimpleErrorResult("none User", errMsg, status, dbErrorStackTrace));
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
	private FirmArcDaoServices firmArcDaoServices;
		
}

