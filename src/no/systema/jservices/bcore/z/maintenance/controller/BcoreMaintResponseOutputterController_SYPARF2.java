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

import no.systema.jservices.bcore.z.maintenance.controller.rules.SYPARF2_U;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KofastDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.dao.SyparfDao;
import no.systema.jservices.common.dao.services.FratxtDaoService;
import no.systema.jservices.common.dao.services.Syparf2DaoService;
import no.systema.jservices.common.dto.SyparfDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SYPARF2 {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_SYPARF2.class.getName());

	/**
	 * FreeForm Source: File: SYPARF, based on syuser as key (USER)
	 * 
	 * @return
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF2.do?user=OSCAR&syuser=1&syrecn=15
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF2.do?user=OSCAR&syuser=1
	 * 
	 */
	@RequestMapping(value = "syjsSYPARF2.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doSyparf2(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SyparfDto> jsonWriter = new JsonResponseWriter2<SyparfDto>();
		StringBuffer sb = new StringBuffer();
		List<SyparfDto> syparfDtoList = new ArrayList<SyparfDto>();
		String syuser = request.getParameter("syuser");
		String syrecn = request.getParameter("syrecn");

		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if (userName != null && !"".equals(userName) && (syuser != null && !"".equals(syuser)) ) {
				if (syrecn != null && !"".equals(syrecn)) {
					SyparfDto dto = fetchRecord(syuser, syrecn);
					syparfDtoList.add(dto);
				} else {
					syparfDtoList = fetchList(syuser);
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
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF2_U.do?user=OSCAR&syuser=BIRGIT&syrecn=59&sysort=15&syvrdn=1,5&syvrda=alfaupdate&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value = "syjsSYPARF2_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsSYPARF2_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SyparfDao> jsonWriter = new JsonResponseWriter2<SyparfDao>();
		StringBuffer sb = new StringBuffer();
		
		try {
			logger.info("Inside syjsSYPARF2_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			String syuser = request.getParameter("syuser");
			
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			SyparfDto dto = new SyparfDto();
			SyparfDao resultDao = new SyparfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dto);
			binder.bind(request);
			//Special edition for USER
			resultDao.setKeys(syuser);
			//
			SYPARF2_U rulerLord = new SYPARF2_U(request, kofastDaoServices, sb, dbErrorStackTrace);
			
			if (userName != null && !"".equals(userName)) {
				if ("D".equals(mode)) {
					if (rulerLord.isValidInputForDelete(dto, userName, mode)) {
						syparf2DaoService.delete(dto);
					}
				} else {
					if (rulerLord.isValidInput(dto, userName, mode)) {
						rulerLord.updateNumericFieldsIfNull(dto);
						if ("A".equals(mode)) {
							resultDao = syparf2DaoService.create(dto);
						} else if ("U".equals(mode)) {
							resultDao = syparf2DaoService.update(dto);
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
	
	private SyparfDto fetchRecord(String syuser, String syrecn) {
		logger.info("SYUSER:" + syuser);
		logger.info("SYRECN:" + syrecn);
		
		SyparfDao dao = (SyparfDao) syparf2DaoService.find(syuser, syrecn);
		SyparfDto dto = getDto(dao);

		return dto;
	}
	private List<SyparfDto> fetchList(String syuser) {
		List<SyparfDao> syparfDaoList = new ArrayList<SyparfDao>(); // from DaoServcie
		List<SyparfDto> syparfDtoList = new ArrayList<SyparfDto>(); // dto to GUI
		SyparfDto syparfDto = null; // dto to GUI
		syparfDaoList = (List<SyparfDao>) syparf2DaoService.findAll(syuser);
		for (SyparfDao syparfDao : syparfDaoList) {
			syparfDto = getDto(syparfDao);
			syparfDtoList.add(syparfDto);
		}

		return syparfDtoList;
	}
	private SyparfDto getDto(SyparfDao dao) {
		SyparfDto dto = new SyparfDto();
		dto.setSyuser(dao.getSyuser());
		dto.setSyrecn(dao.getSyrecn());
		dto.setSykunr(dao.getSykunr());
		dto.setSyavd(dao.getSyavd());
		dto.setSypaid(dao.getSypaid());
		dto.setSypaidDesc(getSytpaidDesc(dao.getSypaid()));
		dto.setSysort(dao.getSysort());
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

	@Qualifier("syparf2DaoService")
	private Syparf2DaoService syparf2DaoService;
	@Autowired
	@Required
	public void setSyparf2DaoService(Syparf2DaoService value) { this.syparf2DaoService = value; }
	public Syparf2DaoService getSyparf2DaoService() { return this.syparf2DaoService; }

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
