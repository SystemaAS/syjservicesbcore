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

//rules
import no.systema.jservices.bcore.z.maintenance.controller.rules.SYFA63R_U;
//Application
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaHodeDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KodtaHodeDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.CundcDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.CundcDaoServices;

/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer. All
 * communication to the outside world is done through this gateway.
 * 
 * CUNDC is kontaktperson assiciated many-to-one to CUNDF Kunde
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
	 * 	 File: 		CUNDC
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsCUNDC.do?user=OSCAR&cfirma=SY&ccompn=1
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsCUNDC.do?user=OSCAR&cfirma=SY&ccompn=10&ccconta=1
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
				CundcDao dao = new CundcDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				if ((dao.getCfirma() != null && !"".equals(dao.getCfirma())) && (dao.getCcompn() != null && !"".equals(dao.getCcompn()))) {
					if ((dao.getCconta() != null && !"".equals(dao.getCconta()))) {
						logger.info("findById: ccompn, cfirma, cconta");
						list = this.cundcDaoServices.findById(dao.getCcompn(), dao.getCfirma(), dao.getCconta(), dbErrorStackTrace);

					} else {
						logger.info("findById: ccompn, cfirma");
						list = this.cundcDaoServices.findById(dao.getCcompn(), dao.getCfirma(), dbErrorStackTrace);
					}

				} else {
					logger.info("getList...");
					list = this.cundcDaoServices.getList(dbErrorStackTrace);
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

/*	*//**
	 * TODO
	 * Update Database DML operations File: CUNDC
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicestn/syjsSYFA63R_U.do?user=OSCAR&koaavd=1&honet=E&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 *//*

	@RequestMapping(value = "syjsCUNDC_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsR_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();

		try {
			logger.info("Inside syjsCUNDC_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			// bind attributes is any
			CundcDao dao = new CundcDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);
			// rules
			SYFA63R_U rulerLord = new SYFA63R_U();  //TODO:
			// Start processing now
			if (userName != null && !"".equals(userName)) {
				int dmlRetval = 0;
				if ("D".equals(mode)) {
					logger.info("Before DELETE ...");
					if (rulerLord.isValidInputForDelete(dao, userName, mode)) {
						dmlRetval = this.cundcDaoServices.delete(dao, dbErrorStackTrace);
					} else {
						// write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				} else {
					if (rulerLord.isValidInput(dao, userName, mode)) {
						List<KodtaHodeDao> list = new ArrayList<KodtaHodeDao>();
						// must complete numeric values to avoid <null> on those
						rulerLord.updateNumericFieldsIfNull(dao);
						// do ADD
						if ("A".equals(mode)) {
							logger.info("Before INSERT ...");
							list = this.cundcDaoServices.findById(dao.getKoaavd(), dao.getHonet(), dbErrorStackTrace);
							// check if there is already such a code. If it
							// does, stop the update
							if (list != null && list.size() > 0) {
								// write JSON error output
								errMsg = "ERROR on UPDATE: Record exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							} else {
								logger.info("Before INSERT ...");
								dmlRetval = this.cundcDaoServices.insert(dao, dbErrorStackTrace);
							}

						} else if ("U".equals(mode)) {
							logger.info("Before UPDATE ...");
							dmlRetval = this.cundcDaoServices.update(dao, dbErrorStackTrace);
						}

					} else {
						// write JSON error output
						errMsg = "ERROR on UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}
				// ----------------------------------
				// check returns from dml operations
				// ----------------------------------
				if (dmlRetval < 0) {
					// write JSON error output
					errMsg = "ERROR on UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				} else {
					// OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}

			} else {
				// write JSON error output
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
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
		return sb.toString();
	}
*/
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
