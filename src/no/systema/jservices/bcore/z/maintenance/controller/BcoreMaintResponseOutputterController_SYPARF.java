package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.controller.rules.SYPARF_U;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KofastDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.dao.SyparfDao;
import no.systema.jservices.common.dao.services.FratxtDaoService;
import no.systema.jservices.common.dao.services.SyparfDaoService;
import no.systema.jservices.common.dto.SyparfDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SYPARF {
	private static final Logger logger = LoggerFactory.getLogger(BcoreMaintResponseOutputterController_SYPARF.class.getName());

	/**
	 * FreeForm Source: File: SYPARF
	 * 
	 * @return
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF.do?user=OSCAR&sykunr=1&syrecn=15
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF.do?user=OSCAR&sykunr=1
	 * 
	 */
	@RequestMapping(value = "syjsSYPARF.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doSyparf(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SyparfDto> jsonWriter = new JsonResponseWriter2<SyparfDto>();
		StringBuffer sb = new StringBuffer();
		List<SyparfDto> syparfDtoList = new ArrayList<SyparfDto>();
		String sykunr = request.getParameter("sykunr");
		String syrecn = request.getParameter("syrecn");

		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if ((userName != null && !"".equals(userName)) && (sykunr != null && !"".equals(sykunr))) {
				if (syrecn != null && !"".equals(syrecn)) {
					SyparfDto dto = fetchRecord(sykunr, syrecn);
					syparfDtoList.add(dto);
				} else {
					syparfDtoList = fetchList(sykunr);
				}
				if (syparfDtoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, syparfDtoList));
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
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF_U.do?user=OSCAR&sykunr=24&syrecn=59&sysort=15&syvrdn=1,5&syvrda=alfaupdate&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value = "syjsSYPARF_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsSYPARF_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SyparfDao> jsonWriter = new JsonResponseWriter2<SyparfDao>();
		StringBuffer sb = new StringBuffer();

		try {
			logger.info("Inside syjsSYPARF_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			SyparfDto dto = new SyparfDto();
			SyparfDao resultDao = new SyparfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dto);
			binder.bind(request);

			SYPARF_U rulerLord = new SYPARF_U(request, kofastDaoServices, sb, dbErrorStackTrace);
			
			if (userName != null && !"".equals(userName)) {
				if ("D".equals(mode)) {
					if (rulerLord.isValidInputForDelete(dto, userName, mode)) {
						syparfDaoService.delete(dto);
					}
				} else {
					if (rulerLord.isValidInput(dto, userName, mode)) {
						rulerLord.updateNumericFieldsIfNull(dto);
						if ("A".equals(mode)) {
							resultDao = syparfDaoService.create(dto);
						} else if ("U".equals(mode)) {
							resultDao = syparfDaoService.update(dto);
						}
					} 
				}
				if (resultDao == null) {
					errMsg = "ERROR on UPDATE";
					status = "error";
					dbErrorStackTrace.append("Could not add/update dao=" + ReflectionToStringBuilder.toString(dto));
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				} else {
					// OK UPDATE
					//TODO sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
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


	
	private SyparfDto fetchRecord(String sykunr, String syrecn) {
		SyparfDao dao = (SyparfDao) syparfDaoService.find(sykunr, syrecn);
		SyparfDto dto = getDto(dao);

		return dto;
	}
	private List<SyparfDto> fetchList(String sykunr) {
		List<SyparfDao> syparfDaoList = new ArrayList<SyparfDao>(); // from DaoServcie
		List<SyparfDto> syparfDtoList = new ArrayList<SyparfDto>(); // dto to GUI
		SyparfDto syparfDto = null; // dto to GUI
		syparfDaoList = (List<SyparfDao>) syparfDaoService.findAll(sykunr);
		for (SyparfDao syparfDao : syparfDaoList) {
			syparfDto = getDto(syparfDao);
			syparfDtoList.add(syparfDto);
		}

		return syparfDtoList;
	}
	private SyparfDto getDto(SyparfDao dao) {
		SyparfDto dto = new SyparfDto();
		dto.setSykunr(dao.getSykunr());
		dto.setSyavd(dao.getSyavd());
		dto.setSypaid(dao.getSypaid());
		dto.setSypaidDesc(getSytpaidDesc(dao.getSypaid()));
		dto.setSyrecn(dao.getSyrecn());
		dto.setSysort(dao.getSysort());
		dto.setSyuser(dao.getSyuser());
		dto.setSyvrda(dao.getSyvrda());
		dto.setSyvrdn(dao.getSyvrdn());
		return dto;
	}

	private String getSytpaidDesc(String sypaid) {
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

	@Qualifier("syparfDaoService")
	private SyparfDaoService syparfDaoService;

	@Autowired
	@Required
	public void setSyparfDaoService(SyparfDaoService value) {
		this.syparfDaoService = value;
	}

	public SyparfDaoService getSyparfDaoService() {
		return this.syparfDaoService;
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
