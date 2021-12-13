package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.controller.rules.VADR_U;
import no.systema.jservices.common.dao.VadrDao;
import no.systema.jservices.common.dao.services.KodtlkDaoService;
import no.systema.jservices.common.dao.services.VadrDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_VADR {
	private static final Logger logger = LogManager.getLogger(BcoreMaintResponseOutputterController_VADR.class.getName());

	
	/**
	 * File: 	VADR
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsVADR.do?user=OSCAR&kundnr=600006
	 * 
	 */
	@RequestMapping(value="syjsVADR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsVadr(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<VadrDao> jsonWriter = new JsonResponseWriter2<VadrDao>();
		StringBuffer sb = new StringBuffer();
		List<VadrDao> vadrDaoList = null;
		
		try {
			String user = request.getParameter("user");
			String kundnr = request.getParameter("kundnr");
			String vadrnr = request.getParameter("vadrnr");
			String firma = request.getParameter("firma");
			
			// Check ALWAYS user in BRIDF
			String userName = bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if (StringUtils.hasValue(userName) && StringUtils.hasValue(kundnr)) {
				if(StringUtils.hasValue(vadrnr) && StringUtils.hasValue(firma)){
					vadrDaoList = vadrDaoService.getList(Integer.parseInt(kundnr), Integer.parseInt(vadrnr), firma);
				}else{
					if(StringUtils.hasValue(firma)){
						vadrDaoList = vadrDaoService.getList(Integer.parseInt(kundnr), firma);
					}else{
						vadrDaoList = vadrDaoService.getList(Integer.parseInt(kundnr));
					}
				}
				if (vadrDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, vadrDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find VadrDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user> and/or <kundnr1>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			logger.info("Error :", e);
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
	 * This method is responsible for checking mode of record.
	 * 
	 * Using {@link VadrDaoService} for check if exist
	 * 
	 * 
	 * Update Database DML operations File: VADR
	 * 
	 * @Example UPDATE:
	 *          http://localhost:8080/syjservicesbcore/syjsVADR_U.do?user=OSCAR&...
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value = "syjsVADR_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsVADR_U(HttpSession session, HttpServletRequest request) {	
		logger.info("INSIDE syjsVADR_U.do");
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		String userName = null;
		String errMsg;
		String status;
		StringBuffer dbErrorStackTrace  = new StringBuffer();
		String user = request.getParameter("user");
		String mode = request.getParameter("mode");
	
		logger.info("user="+user);
		logger.info("mode="+mode);
		
		
		try {
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user);
			errMsg = "";
			status = "ok";
			VadrDao dao = new VadrDao();
			VadrDao resultDao = new VadrDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);

	        VADR_U rulerLord = new VADR_U(request, kodtlkDaoService, sb, dbErrorStackTrace); 
	        
			if (rulerLord.isValidInput(dao, userName)) {
				if (StringUtils.hasValue(userName)) {
					if ("D".equals(mode)) {
						vadrDaoService.delete(dao);
					} else {
						if ("A".equals(mode)) {
							resultDao = vadrDaoService.create(dao);
							logger.info("VADR created, dao="+ReflectionToStringBuilder.toString(resultDao));
						} else if ("U".equals(mode)) {
							resultDao = vadrDaoService.updateNr1(dao);
							logger.info("VADR updated, dao="+ReflectionToStringBuilder.toString(resultDao));
						}
					}
					if (resultDao == null) {
						errMsg = "Could not add/update dao=" + ReflectionToStringBuilder.toString(dao);
						status = "error ";
						dbErrorStackTrace.append("Could not add/update dao=" + ReflectionToStringBuilder.toString(dao));
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					} else {
						// OK UPDATE
						sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
					}

				} else {
					logger.error("user: "+user+ " not found in bridf.");
					// write JSON error output
					errMsg = "ERROR on DML for VADR";
					status = "error";
					dbErrorStackTrace.append("request input parameters are invalid: <user>");
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				// write JSON error output
				errMsg = "ERROR on ADD/UPDATE for VADR: invalid rulerLord, error="+sb.toString();
				status = "error";
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				logger.error(sb);
			}
			

		} catch (Exception e) {
			logger.error("::ERROR::", e);
			errMsg = "ERROR on add/update VADR: "+e.getMessage();
			status = "error ";
			dbErrorStackTrace.append(e.getMessage());
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,dbErrorStackTrace));
		}
		session.invalidate();
		return sb.toString();
		
	}
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }	

	@Qualifier ("vadrDaoService")
	private VadrDaoService vadrDaoService;
	@Autowired
	@Required
	public void setVadrDaoService(VadrDaoService value){ this.vadrDaoService = value; }
	public VadrDaoService getVadrDaoService(){ return this.vadrDaoService; }		

	@Qualifier ("kodtlkDaoService")
	private KodtlkDaoService kodtlkDaoService;
	@Autowired
	@Required
	public void setKodtlkDaoService(KodtlkDaoService value){ this.kodtlkDaoService = value; }
	public KodtlkDaoService getKodtlkDaoService(){ return this.kodtlkDaoService; }		
	
	
}
