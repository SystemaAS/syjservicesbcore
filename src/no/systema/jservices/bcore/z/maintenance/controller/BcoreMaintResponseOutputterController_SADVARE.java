package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.controller.rules.SADVARE_U;
import no.systema.jservices.common.dao.SadvareDao;
import no.systema.jservices.common.dao.services.Kodts2DaoService;
import no.systema.jservices.common.dao.services.Kodts5DaoService;
import no.systema.jservices.common.dao.services.Kodts6DaoService;
import no.systema.jservices.common.dao.services.Kodts7DaoService;
import no.systema.jservices.common.dao.services.SadvareDaoService;
import no.systema.jservices.common.dao.services.TariDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SADVARE {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_SADVARE.class.getName());

	/**
	 * File: SADVARE
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE.do?user=OSCAR&levenr=1&varenr=9004901000
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE.do?user=OSCAR&levenr=1
	 * 
	 */
	@RequestMapping(value = "syjsSADVARE.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doSadvare(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SadvareDao> jsonWriter = new JsonResponseWriter2<SadvareDao>();
		StringBuffer sb = new StringBuffer();
		List<SadvareDao> sadvareDaoList = new ArrayList<SadvareDao>();
		String levenr = request.getParameter("levenr");
		String varenr = request.getParameter("varenr");

		try {
			String user = request.getParameter("user");
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if ((userName != null && !"".equals(userName)) && (levenr != null && !"".equals(levenr))) {
				if (varenr != null && !"".equals(varenr)) {
					SadvareDao dao = fetchRecord(levenr, varenr);
					sadvareDaoList.add(dao);
				} else {
					sadvareDaoList = fetchList(levenr);
				}
				if (sadvareDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, sadvareDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find SadvareDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user> and <levenr> ");
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
	 * Update Database DML operations File: SADVARE
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE_U.do?user=OSCAR&levenr=24&varenr=59&varebe=alfaupdate...and many more...&mode=U/A/D
	 *
	 */
	@RequestMapping(value = "syjsSADVARE_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsSADVARE_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SadvareDao> jsonWriter = new JsonResponseWriter2<SadvareDao>();
		StringBuffer sb = new StringBuffer();
		String userName = null;
		String errMsg = null;
		String status = null;
		StringBuffer dbErrorStackTrace = null;

		try {
			logger.info("Inside syjsSADVARE_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user);
			errMsg = "";
			status = "ok";
			dbErrorStackTrace = new StringBuffer();
			SadvareDao dao = new SadvareDao();
			SadvareDao resultDao = new SadvareDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);
			
			SADVARE_U rulerLord = new SADVARE_U(kodts7DaoService, kodts2DaoService,tariDaoService,kodts5DaoService,kodts6DaoService ,sb, dbErrorStackTrace);
			if (userName != null && !"".equals(userName)) {
				if ("D".equals(mode)) {
					if (rulerLord.isValidInputForDelete(dao, userName, mode)) {
						sadvareDaoService.delete(dao);
					}
				} else {
					if (rulerLord.isValidInput(dao, userName, mode)) {
						rulerLord.updateNumericFieldsIfNull(dao);
						if ("A".equals(mode)) {
							resultDao = sadvareDaoService.create(dao);
						} else if ("U".equals(mode)) {
							resultDao = sadvareDaoService.update(dao);
						}
					} 
				}
				if (resultDao == null) {
					errMsg = "ERROR on UPDATE";
					status = "error";
					dbErrorStackTrace.append("Could not add/update dao=" + ReflectionToStringBuilder.toString(dao));
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				} else {
					// OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}

			} else {
				// write JSON error output
				errMsg = "ERROR on UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}

		} catch (Exception e) {
			if (e instanceof BadSqlGrammarException) {
				errMsg = "ERROR on UPDATE";
				status = "error";
				logger.info("getLocalizedMessage="+e.getCause().getLocalizedMessage());
				logger.info("getMessage="+e.getCause().getMessage());
				dbErrorStackTrace.append(e.getCause());
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,dbErrorStackTrace));
			} else {
				logger.info("Error:", e);
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				return "ERROR [JsonResponseOutputterController]" + writer.toString();
			}
		}
		session.invalidate();
		return sb.toString();

	}

	private SadvareDao fetchRecord(String levenr, String varenr) {
		SadvareDao dao = (SadvareDao) sadvareDaoService.find(levenr, varenr);
		return dao;
	}

	private List<SadvareDao> fetchList(String levenr) {
		List<SadvareDao> sadvareDaoList = null;
		sadvareDaoList = sadvareDaoService.findAll(levenr);
		return sadvareDaoList;
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

	@Qualifier("sadvareDaoService")
	private SadvareDaoService sadvareDaoService;

	@Autowired
	@Required
	public void setSadvareDaoService(SadvareDaoService value) {
		this.sadvareDaoService = value;
	}

	public SadvareDaoService getSadvareDaoService() {
		return this.sadvareDaoService;
	}
	
	@Qualifier("kodts7DaoService")
	private Kodts7DaoService kodts7DaoService;

	@Autowired
	@Required
	public void setKodts7DaoService(Kodts7DaoService value) {
		this.kodts7DaoService = value;
	}

	public Kodts7DaoService getKodts7DaoService() {
		return this.kodts7DaoService;
	}	
	
	@Qualifier("kodts2DaoService")
	private Kodts2DaoService kodts2DaoService;

	@Autowired
	@Required
	public void setKodts2DaoService(Kodts2DaoService value) {
		this.kodts2DaoService = value;
	}

	public Kodts2DaoService getKodts2DaoService() {
		return this.kodts2DaoService;
	}
	
	@Qualifier("kodts5DaoService")
	private Kodts5DaoService kodts5DaoService;

	@Autowired
	@Required
	public void setKodts5DaoService(Kodts5DaoService value) {
		this.kodts5DaoService = value;
	}

	public Kodts5DaoService getKodts5DaoService() {
		return this.kodts5DaoService;
	}
	
	@Qualifier("kodts6DaoService")
	private Kodts6DaoService kodts6DaoService;

	@Autowired
	@Required
	public void setKodts6DaoService(Kodts6DaoService value) {
		this.kodts6DaoService = value;
	}

	public Kodts6DaoService getKodts6DaoService() {
		return this.kodts6DaoService;
	}		

	@Qualifier("tariDaoService")
	private TariDaoService tariDaoService;

	@Autowired
	@Required
	public void setTariDaoService(TariDaoService value) {
		this.tariDaoService = value;
	}

	public TariDaoService getTariDaoService() {
		return this.tariDaoService;
	}		
	
	
	
}
