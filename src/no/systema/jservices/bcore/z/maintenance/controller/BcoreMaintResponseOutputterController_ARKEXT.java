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

import no.systema.jservices.common.dao.ArkextDao;
import no.systema.jservices.common.dao.services.ArkextDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_ARKEXT {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_ARKEXT.class.getName());

	/**
	 * FreeForm Source: File: ARKEXT
	 * 
	 * @return
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsARKEXT.do?user=OSCAR&arcext=E1
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsARKEXT.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value = "syjsARKEXT.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doArktxt(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<ArkextDao> jsonWriter = new JsonResponseWriter2<ArkextDao>();
		StringBuffer sb = new StringBuffer();
		List<ArkextDao> arktxtDtoList = new ArrayList<ArkextDao>();
		String arcext = request.getParameter("arcext");

		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if ((userName != null && !"".equals(userName))) {
				if (arcext != null && !"".equals(arcext)) {
					ArkextDao dao = fetchRecord(arcext);
					arktxtDtoList.add(dao);				
				} else {
					arktxtDtoList = fetchList();
				}
				if (arktxtDtoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, arktxtDtoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find ArkextDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append(" request input parameters are invalid: <user>");
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

	
	private ArkextDao fetchRecord(String  arcext) {
		ArkextDao qDao = new ArkextDao();
		qDao.setArcext(arcext);
		ArkextDao resultDao = arkextDaoService.find(qDao);

		return resultDao;
	}

	private List<ArkextDao> fetchList() {
		List<ArkextDao> list = null;
		list = arkextDaoService.findAll(null);

		return list;
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


	@Qualifier("arkextDaoService")
	private ArkextDaoService arkextDaoService;

	@Autowired
	@Required
	public void setArkextDaoServicee(ArkextDaoService value) {
		this.arkextDaoService = value;
	}

	public ArkextDaoService getArkextDaoService() {
		return this.arkextDaoService;
	}
	
		
	
	
	
}
