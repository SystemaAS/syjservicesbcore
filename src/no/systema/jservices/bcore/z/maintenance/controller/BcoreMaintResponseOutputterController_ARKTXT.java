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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.controller.rules.ARKTXT_U;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.dao.ArkextDao;
import no.systema.jservices.common.dao.ArktxtDao;
import no.systema.jservices.common.dao.services.ArkextDaoService;
import no.systema.jservices.common.dao.services.ArktxtDaoService;
import no.systema.jservices.common.dto.ArktxtDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_ARKTXT {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_ARKTXT.class.getName());

	/**
	 * FreeForm Source: File: ARKTXT
	 * 
	 * @return 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsARKTXT.do?user=OSCAR&artype=fa
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsARKTXT.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value = "syjsARKTXT.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doArktxt(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<ArktxtDto> jsonWriter = new JsonResponseWriter2<ArktxtDto>();
		StringBuffer sb = new StringBuffer();
		List<ArktxtDto> arktxtDtoList = new ArrayList<ArktxtDto>();
		String artype = request.getParameter("artype");

		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if ((userName != null && !"".equals(userName))) {
				if (artype != null && !"".equals(artype)) {
					ArktxtDto dto = fetchRecord(artype);
					arktxtDtoList.add(dto);				
				} else {
					arktxtDtoList = fetchList();
				}
				if (arktxtDtoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, arktxtDtoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find ArktxtDao list";
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

	
	/**
	 * 
	 * Update Database DML operations File: ARKTXT
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsARKTXT_U.do?user=OSCAR&artype=ZH&artxt=kalle&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value = "syjsARKTXT_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsARKTXT_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<ArktxtDto> jsonWriter = new JsonResponseWriter2<ArktxtDto>();
		StringBuffer sb = new StringBuffer();
		String userName = null;
		String errMsg = null;
		String status = null;
		StringBuffer dbErrorStackTrace = null;
		
		try {
			logger.info("Inside syjsARKTXT_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user);
			errMsg = "";
			status = "ok";
			dbErrorStackTrace = new StringBuffer();

			ArktxtDao dao = new ArktxtDao();
			ArktxtDao resultDao = null;
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);
			
			ARKTXT_U rulerLord = new ARKTXT_U(request,arktxtDaoService, arkextDaoService, kofastDaoServices, sb, dbErrorStackTrace);
			
			if (userName != null && !"".equals(userName)) {
				if ("D".equals(mode)) {
					if (rulerLord.isValidInputForDelete(dao, userName, mode)) {
						arktxtDaoService.delete(dao);
					}
				} else {
					if (rulerLord.isValidInput(dao, userName, mode)) {
						if ("A".equals(mode)) {
							resultDao = arktxtDaoService.create(dao);
						} else if ("U".equals(mode)) {
							resultDao = arktxtDaoService.update(dao);
						}
						if (resultDao == null) {
							errMsg = "ERROR on UPDATE ";
							status = "error";
							dbErrorStackTrace.append("Could not create/update dao=" + ReflectionToStringBuilder.toString(dao));
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
						} 
					} 
				}
			} else {
				// write JSON error output
				errMsg = "ERROR on UPDATE ";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}

		} catch (Exception e) {
			logger.info("Error:", e);
			errMsg = "ERROR on UPDATE ";
			status = "error";
			dbErrorStackTrace.append(e.getLocalizedMessage());
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,dbErrorStackTrace));
		}
		session.invalidate();
		return sb.toString();

	}

	private ArktxtDto fetchRecord(String  artype) {
		ArktxtDao qDao = new ArktxtDao();
		qDao.setArtype(artype);
		ArktxtDao resultDao = arktxtDaoService.find(qDao);
		ArktxtDto dto = getDto(resultDao, true);

		return dto;
	}

	private List<ArktxtDto> fetchList() {
		List<ArktxtDao> arktxtDaoList = new ArrayList<ArktxtDao>(); // from DaoServcie
		List<ArktxtDto> arktxtDtoList = new ArrayList<ArktxtDto>(); // dto to GUI
		ArktxtDto arktxtDto = null; // dto to GUI
		arktxtDaoList = (List<ArktxtDao>) arktxtDaoService.findAll(null);
		for (ArktxtDao arktxtDao : arktxtDaoList) {
			arktxtDto = getDto(arktxtDao, false);
			arktxtDtoList.add(arktxtDto);
		}

		return arktxtDtoList;
	}

	private ArktxtDto getDto(ArktxtDao dao, boolean getDesc) {
		ArktxtDto dto = new ArktxtDto();
		dto.setArtype(dao.getArtype());
		dto.setArtxt(dao.getArtxt());
		dto.setArkjn(dao.getArkjn());
		dto.setArksnd(dao.getArksnd());
		dto.setArklag(dao.getArklag());
		dto.setArkdag(dao.getArkdag());
		if (getDesc) {
			dto.setArklagDesc(getArklagDesc(dao.getArklag()));
		}
		dto.setArkved(dao.getArkved());
		dto.setArslab(dao.getArslab());
		dto.setArsban(dao.getArsban());
		dto.setArmrg(dao.getArmrg());
		dto.setArvedl(dao.getArvedl());	
		dto.setArsfsk(dao.getArsfsk());	
		dto.setArscts(dao.getArscts());	
		dto.setArsrle(dao.getArsrle());	
		dto.setArsrst(dao.getArsrst());	
		dto.setArsrpa(dao.getArsrpa());	
		dto.setArsrno(dao.getArsrno());	
		
		return dto;
	}

	
	private String getArklagDesc(String arklag) {
		ArkextDao dao = new ArkextDao();
		dao.setArcext(arklag);
		ArkextDao resultDao  = arkextDaoService.find(dao);
		if (resultDao != null) {
			return resultDao.getArcane();
		} else {
			return null;
			
		}
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

	@Qualifier("arktxtDaoService")
	private ArktxtDaoService arktxtDaoService;
	@Autowired
	@Required
	public void setArktxtDaoService(ArktxtDaoService value) {
		this.arktxtDaoService = value;
	}
	public ArktxtDaoService getArktxtDaoService() {
		return this.arktxtDaoService;
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
	
}
