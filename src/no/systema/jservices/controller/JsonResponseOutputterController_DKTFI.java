package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;


 
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.jservices.model.dao.services.BridfDaoServices;
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.model.dao.services.DktfiDaoServices;
import no.systema.jservices.model.dao.entities.DktfiDao;
import no.systema.jservices.jsonwriter.JsonResponseWriter;






/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Mar 26, 2023
 * 
 */

@Controller
public class JsonResponseOutputterController_DKTFI {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_DKTFI.class.getName());
	
	/**
	 * 
	 * This class has been copied from edi_tess_fbc. UNB-id's for XML-outbound SCTS_ENV - tullverket
	 * Will not be used in the same way for the SKAT - Danish XML outbound but 
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSVTFI.do
	 * 
	 */
	@RequestMapping(value="syjsDKTFI.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		String user = request.getParameter("user");
		//Check ALWAYS user in BRIDF
        String userName = this.bridfDaoServices.findNameById(user);
		
		try{
			logger.info("Inside syjsDKTFI.do");
			
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				//SvtfiDao dao = new SvtfiDao();
				//ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            //binder.bind(request);
	            //do SELECT
				logger.info("Before SELECT ...");
				List list  = this.dktfiDaoServices.getList(dbErrorStackTrace);
				
				if(list!=null && list.size()>0) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(user, list));
					
				}else {
					//phantom return
					logger.warn("phantom return...");
					sb.append(jsonWriter.setJsonResult_Common_GetList(user, new ArrayList()));
				}
			}else {
				// write JSON error output
				errMsg = "ERROR on ADD/UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				logger.error(sb.toString());
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
	private DktfiDaoServices dktfiDaoServices;
	public void setDktfiDaoServices (DktfiDaoServices value){ this.dktfiDaoServices = value; }
	public DktfiDaoServices getDktfiDaoServices(){ return this.dktfiDaoServices; }


	@Autowired
	private BridfDaoServices bridfDaoServices;
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

