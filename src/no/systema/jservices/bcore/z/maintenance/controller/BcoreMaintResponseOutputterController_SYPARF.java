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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.common.dao.SyparfDao;
import no.systema.jservices.common.dao.services.FratxtDaoService;
import no.systema.jservices.common.dao.services.SyparfDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SYPARF {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_SYPARF.class.getName());

	
	/**
	 * FreeForm Source:
	 * File: 	SYPARF
	 * 
	 * @return
	 * @Example SELECT specific:http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF.do?user=OSCAR&sykunr=1&syrecn=15
	 * @Example SELECT list: 	http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF.do?user=OSCAR&sykunr=1
	 * 
	 */
	@RequestMapping(value="syjsSYPARF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String doSyparf(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SyparfDao> jsonWriter = new JsonResponseWriter2<SyparfDao>();
		StringBuffer sb = new StringBuffer();
		List<SyparfDao> syparfDaoList = new ArrayList<SyparfDao>();
		String sykunr = request.getParameter("sykunr");
		String syrecn = request.getParameter("syrecn");
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ( (userName != null && !"".equals(userName) ) && (sykunr != null && !"".equals(sykunr)) ) {
				if (syrecn != null && !"".equals(syrecn)) {
					SyparfDao dao = syparfDaoService.find(sykunr, syrecn);
					syparfDaoList.add(dao);
				} else {
					syparfDaoList = syparfDaoService.findAll(sykunr);
				}
				if (syparfDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, syparfDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find SyparfDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>");
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
	 * Using {@link FratxtDaoService} for check if exist
	 * 
	 * 
	 * Update Database DML operations File: FRATXT
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsFRATXT_U.do?user=OSCAR&fxknr=24&fxlnr=20&delsys=A&fxtxt=updatedtext&mode=U
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
/*	@RequestMapping(value = "syjsFRATXT_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsFRATXT_U(HttpSession session, HttpServletRequest request) {	
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();

		try {
			logger.info("Inside syjsFRATXT_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			FratxtDao dao = new FratxtDao();
			FratxtDao resultDao = new FratxtDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);
			
			if (userName != null && !"".equals(userName)) {
				if ("D".equals(mode)) {
					fratxtDaoService.delete(dao);
				} else if ("A".equals(mode)) {
					if (fratxtDaoService.exist(dao)) {
						resultDao = fratxtDaoService.update(dao);
					} else {
						resultDao = fratxtDaoService.create(dao);
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
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }	

	@Qualifier ("syparfDaoService")
	private SyparfDaoService syparfDaoService;
	@Autowired
	@Required
	public void setSyparfDaoService(SyparfDaoService value){ this.syparfDaoService = value; }
	public SyparfDaoService getSyparfDaoService(){ return this.syparfDaoService; }		
	
	
}
