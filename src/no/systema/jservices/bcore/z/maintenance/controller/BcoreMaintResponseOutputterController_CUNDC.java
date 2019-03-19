package no.systema.jservices.bcore.z.maintenance.controller;

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

import no.systema.jservices.bcore.z.maintenance.controller.rules.CUNDC_U;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.dao.services.KodafDaoService;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.CundcDao;
import no.systema.jservices.model.dao.entities.CundcDto;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.CundcDaoServices;
import no.systema.jservices.model.dao.services.EdiiDaoServices;

/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer. All
 * communication to the outside world is done through this gateway.
 * 
 * CUNDC is kontaktperson/funksjon associated many-to-one to CUNDF Kunde
 * 
 * @author Fredrik Möller
 * @date Nov 3, 2016
 * 
 */

@Controller
public class BcoreMaintResponseOutputterController_CUNDC {
	private static Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_CUNDC.class.getName());

	/**
	 * FreeForm Source:
	 * 	 File: 		CUNDC and ARKVEDK
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsCUNDC.do?user=OSCAR&cfirma=SY&ccompn=1
	 * @Example SELECT specific: http://localhost:8080/syjservicesbcore/syjsCUNDC.do?user=OSCAR&cfirma=SY&ccompn=10&cconta=EMMA-XML&ctype=*Advisering SjøX
	 * 
	 */
	@RequestMapping(value="syjsCUNDC.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsCUNDC.do");
			String user = request.getParameter("user");
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				CundcDao queryDao = new CundcDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(queryDao);
	            binder.bind(request);
	            List list = null;
	            
				if (  StringUtils.hasValue(queryDao.getCfirma())  &&  StringUtils.hasValue(queryDao.getCcompn())) {
					if ( StringUtils.hasValue(queryDao.getCconta()) && queryDao.getCtype() != null ) { //ctype is key, kan be null:-)
						logger.info("cundcDaoServices.get");
						CundcDao dao = (CundcDao) cundcDaoServices.get(queryDao, dbErrorStackTrace);
						if (dao != null) {
							logger.info("dao="+dao);
							list = new ArrayList<CundcDao>();
							list.add(dao);
						}

					} else {
						logger.info("cundcDaoServices.findById");
						list = cundcDaoServices.findById(queryDao.getCcompn(), queryDao.getCfirma(), dbErrorStackTrace);
					}

				} else {
					logger.info("cundcDaoServices.getList");
					list = cundcDaoServices.getList(dbErrorStackTrace);
				}
				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetCompositeList(userName, list));
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
	 * Update Database DML operations File: CUNDC
	 * 
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value = "syjsCUNDC_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsR_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();

		StringBuffer dbErrorStackTrace = new StringBuffer();
        String errMsg = "";
		String status = "ok";
		String userName = null;		

		logger.info("Inside syjsCUNDC_U.do");
		
		try {
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user);

			CundcDto dto = new CundcDto();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dto);
			binder.bind(request);
			// rules
			CUNDC_U rulerLord = new CUNDC_U(request,cundcDaoServices, kofastDaoServices, ediiDaoServices,sb, dbErrorStackTrace);
			// Start processing now
			if (userName != null && !"".equals(userName)) {
				int dmlRetval = 0;
				if ("D".equals(mode)) {
					if (rulerLord.isValidInputForDelete(dto, userName, mode)) {
						logger.info("delete...");
						dmlRetval = cundcDaoServices.delete(dto, dbErrorStackTrace);
					} else {
						// write JSON error output
						errMsg = "ERROR on DELETE: invalid rulerLord  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				} else {
					if (rulerLord.isValidInput(dto, userName, mode)) {
						adjustDao(dto);
						if ("A".equals(mode)) {
							logger.info("insert...");
							dmlRetval = cundcDaoServices.insert(dto, dbErrorStackTrace);
						} else if ("U".equals(mode)) {
							logger.info("update...");
							dmlRetval = cundcDaoServices.update(dto, dbErrorStackTrace);
						}
					} else {
						logger.error("ERROR on ADD/UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update");
						// write JSON error output
						errMsg = "ERROR on ADD/UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}
				// ----------------------------------
				// check returns from dml operations
				// ----------------------------------
				if (dmlRetval < 0) {
					// write JSON error output
					logger.error("dbErrorStackTrace="+dbErrorStackTrace);
					errMsg = "ERROR on ADD/UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				} else {
					// OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}

			} else {
				// write JSON error output
				logger.error("dbErrorStackTrace="+dbErrorStackTrace);
				errMsg = "ERROR on UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}

		} catch (Exception e) {
			// write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			logger.info(sb);
			logger.error(":::ERROR:::",e);
			errMsg = "ERROR on ADD/UPDATE:  error="+e.getMessage();
			status = "error";
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
		}
		session.invalidate();
		return sb.toString();
	}

	/**
	 * FreeForm Source:
	 * 	 File: 		CUNDC
	 * 
	 * @return
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsCUNDC_LATEST_EMMA_XML_INFO.do?user=OSCAR&cfirma=SY
	 * 
	 */
	@RequestMapping(value="syjsCUNDC_LATEST_EMMA_XML_INFO.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsCUNDC_GetLateestEmmaXmLInfo( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		try {
			logger.info("Inside syjsCUNDC_EMMA_XML_INFO.do");
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if (userName != null && !"".equals(userName)) {
				CundcDao queryDao = new CundcDao();
				CundcDao resultDao = new CundcDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(queryDao);
				binder.bind(request);
				List list = new ArrayList();
				if (queryDao.getCfirma() != null && !"".equals(queryDao.getCfirma())) {
					resultDao = cundcDaoServices.getLastRegisteredEmmaXmlInfo(queryDao.getCfirma(), dbErrorStackTrace);
					list.add(resultDao);
				}
				if (list != null && list.size() > 0) {
					// write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetCompositeList(userName, list));
				} else {
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getList";
					status = "error";
					logger.info("After SELECT:" + " " + status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
		return sb.toString();
	}	
	

	private void adjustDao(CundcDto dto) {
		StringBuilder cavdDefault = new StringBuilder("00000000000000000000");
		cavdDefault.append("00000000000000000000");
		cavdDefault.append("00000000000000000000");
		cavdDefault.append("00000000000000000000"); // 80
		if (dto.getCavd() == null || "".equals(dto.getCavd())) {
			dto.setCavd(cavdDefault.toString());
		}
		
		if ("EMMA-XML".equals(dto.getCconta())) {
			dto.setCmerge(""); //Disable
		}
	}	
	
	
	// ----------------
	// WIRED SERVICES
	// ----------------
	@Qualifier("cundcDaoServices")
	private CundcDaoServices cundcDaoServices;

	@Autowired
	@Required
	public void setCundcDaoServices(CundcDaoServices value) {
		this.cundcDaoServices = value;
	}

	public CundcDaoServices getCundcDaoServices() {
		return this.cundcDaoServices;
	}

	@Qualifier("kofastDaoServices")
	@Autowired
	private KofastDaoServices kofastDaoServices;
	
	
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

	@Qualifier("ediiDaoServices")
	private EdiiDaoServices ediiDaoServices;

	@Autowired
	@Required
	public void setEdiiDaoServices(EdiiDaoServices value) {
		this.ediiDaoServices = value;
	}

	public EdiiDaoServices getEdiiDaoServices() {
		return this.ediiDaoServices;
	}	
	

	@Qualifier("kodafDaoService")
	private KodafDaoService kodafDaoServices;

	@Autowired
	@Required
	public void setKodafDaoService(KodafDaoService value) {
		this.kodafDaoServices = value;
	}

	public KodafDaoService getKodafDaoService() {
		return this.kodafDaoServices;
	}	
	
}
