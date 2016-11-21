package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KofastDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.FasteKoder;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.CundcDaoServices;

/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer. All
 * communication to the outside world is done through this gateway.
 * 
 * KOFAST is a generic fastekode handler for e.g. Funksjoner in Kontaktpersoner, when funksjon(and not person) is used.
 * 
 * @author Fredrik Möller
 * @date Nov 21, 2016
 * 
 */

@Controller
public class BcoreMaintResponseOutputterController_KOFAST {
	private static Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_KOFAST.class.getName());

	/**
	 * FreeForm Source:
	 * File: 	KOFAST
	 * 
	 * @return
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsKOFAST.do?user=OSCAR&kftyp=FUNKSJON&kfkod=ad
	 * 
	 */
	@RequestMapping(value="syjsKOFAST.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsKOFAST.do");
			String user = request.getParameter("user");
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				KofastDao dao = new KofastDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            List list = null;
				logger.info("Before SELECT ...");
				logger.info("dao="+ReflectionToStringBuilder.toString(dao));
				
				if ((dao.getKftyp() != null && !"".equals(dao.getKftyp())) && (dao.getKfkod() != null && !"".equals(dao.getKfkod()))) {
					logger.info("findById...");
					list = kofastDaoServices.findById(FasteKoder.valueOf(dao.getKftyp()), dao.getKfkod(), dbErrorStackTrace);
				} else {
					logger.info("getList...");
					list = kofastDaoServices.getList(dbErrorStackTrace);
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


	// ----------------
	// WIRED SERVICES
	// ----------------
	@Qualifier("kofastDaoServices")
	private KofastDaoServices kofastDaoServices;

	@Autowired
	@Required
	public void setKofastDaoServices(KofastDaoServices value) {
		this.kofastDaoServices = value;
	}

	public KofastDaoServices getKofastDaoServices() {
		return this.kofastDaoServices;
	}

	@Qualifier("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;

	@Autowired
	@Required
	public void setBridfDaoServices(BridfDaoServices value) {
		this.bridfDaoServices = value;
	}

	public BridfDaoServices getBridfDaoServices() {
		return this.bridfDaoServices;
	}

}
