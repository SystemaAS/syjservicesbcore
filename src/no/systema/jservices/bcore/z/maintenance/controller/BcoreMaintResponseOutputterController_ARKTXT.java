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

	
	private ArktxtDto fetchRecord(String  artype) {
		ArktxtDao qDao = new ArktxtDao();
		qDao.setArtype(artype);
		ArktxtDao resultDao = arktxtDaoService.find(qDao);
		ArktxtDto dto = getDto(resultDao);

		return dto;
	}

	private List<ArktxtDto> fetchList() {
		List<ArktxtDao> arktxtDaoList = new ArrayList<ArktxtDao>(); // from DaoServcie
		List<ArktxtDto> arktxtDtoList = new ArrayList<ArktxtDto>(); // dto to GUI
		ArktxtDto arktxtDto = null; // dto to GUI
		arktxtDaoList = (List<ArktxtDao>) arktxtDaoService.findAll(null);
		for (ArktxtDao arktxtDao : arktxtDaoList) {
			arktxtDto = getDto(arktxtDao);
			arktxtDtoList.add(arktxtDto);
		}

		return arktxtDtoList;
	}

	private ArktxtDto getDto(ArktxtDao dao) {
		ArktxtDto dto = new ArktxtDto();
		dto.setArtype(dao.getArtype());
		dto.setArtxt(dao.getArtxt());
		dto.setArkjn(dao.getArkjn());
		dto.setArksnd(dao.getArksnd());
		dto.setArklag(dao.getArklag());
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

	/* TODO
	private String getArklagDesc(String arklag) {
		List<KofastDao> list = kofastDaoServices.findById(FasteKoder.SYPAR, sypaid, null);
		KofastDao dao = null;
		if (list != null && list.size() == 1) {
			dao = list.get(0);
			return dao.getKftxt();
		} else {
			logger.info("Error: Something wrong when selecting kftxt from KOFAST on SYPAR");
		}
		return null;
	}	
	
	*/
	
	
	
	
	

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
//	@RequestMapping(value = "syjsARKTXT_U.do", method = { RequestMethod.GET, RequestMethod.POST })
//	@ResponseBody
//	public String syjsSYPARF_U(HttpSession session, HttpServletRequest request) {
//		JsonResponseWriter2<SyparfDao> jsonWriter = new JsonResponseWriter2<SyparfDao>();
//		StringBuffer sb = new StringBuffer();
//
//		try {
//			logger.info("Inside syjsARKTXT_U.do");
//			String user = request.getParameter("user");
//			String mode = request.getParameter("mode");
//			// Check ALWAYS user in BRIDF
//			String userName = this.bridfDaoServices.findNameById(user);
//			String errMsg = "";
//			String status = "ok";
//			StringBuffer dbErrorStackTrace = new StringBuffer();
//
//			SyparfDto dto = new SyparfDto();
//			SyparfDao resultDao = new SyparfDao();
//			ServletRequestDataBinder binder = new ServletRequestDataBinder(dto);
//			binder.bind(request);
//
//			SYPARF_U rulerLord = new SYPARF_U(kofastDaoServices, sb, dbErrorStackTrace);
//			
//			if (userName != null && !"".equals(userName)) {
//				if ("D".equals(mode)) {
//					if (rulerLord.isValidInputForDelete(dto, userName, mode)) {
//						arktxtDaoService.delete(dto);
//					}
//				} else {
//					if (rulerLord.isValidInput(dto, userName, mode)) {
//						rulerLord.updateNumericFieldsIfNull(dto);
//						if ("A".equals(mode)) {
//							resultDao = syparfDaoService.create(dto);
//						} else if ("U".equals(mode)) {
//							resultDao = syparfDaoService.update(dto);
//						}
//					} 
//				}
//				if (resultDao == null) {
//					errMsg = "ERROR on UPDATE";
//					status = "error";
//					dbErrorStackTrace.append("Could not add/update dao=" + ReflectionToStringBuilder.toString(dto));
//					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
//				} else {
//					// OK UPDATE
//					//TODO sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
//				}
//
//			} else {
//				// write JSON error output
//				errMsg = "ERROR on UPDATE";
//				status = "error";
//				dbErrorStackTrace.append("request input parameters are invalid: <user>");
//				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
//			}
//
//		} catch (Exception e) {
//			// write std.output error output
//			Writer writer = new StringWriter();
//			PrintWriter printWriter = new PrintWriter(writer);
//			e.printStackTrace(printWriter);
//			return "ERROR [JsonResponseOutputterController]" + writer.toString();
//		}
//		session.invalidate();
//		return sb.toString();
//
//	}


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
	
	
	
	
	
	
	
}
