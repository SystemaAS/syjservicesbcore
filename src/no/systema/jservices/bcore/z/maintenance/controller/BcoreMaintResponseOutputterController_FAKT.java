package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
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

import no.systema.jservices.common.dao.FaktDao;
import no.systema.jservices.common.dao.Kodts2Dao;
import no.systema.jservices.common.dao.services.FaktDaoService;
import no.systema.jservices.common.dto.FaktDWDto;
import no.systema.jservices.common.dto.FaktDto;
import no.systema.jservices.common.dto.SingleValueDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.json.WrapperDto;
import no.systema.jservices.common.util.CSVOutputter;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_FAKT {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_FAKT.class.getName());

	/**
	 * File: 	FAKT
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsFAKT.do?user=OSCAR&csv=true
	 * 
	 */
	@RequestMapping(value="syjsFAKT.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String doFakt(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<FaktDao> jsonWriter = new JsonResponseWriter2<FaktDao>();
		CSVOutputter<FaktDao> csvOutputter = new CSVOutputter<FaktDao>();
		StringBuffer sb = new StringBuffer();
		List<FaktDao> faktDaoList = null;
		
		try {
			String user = request.getParameter("user");
			String csv = request.getParameter("csv");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				faktDaoList = faktDaoService.findAll(null);
				if (faktDaoList != null) {
					if (StringUtils.hasValue(csv)) {
						sb.append(csvOutputter.writeAsString(faktDaoList));
					} else {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, faktDaoList));
					}
				} else {
					errMsg = "ERROR on SELECT: Can not find FaktDao list";
					status = "error";
					logger.info( status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}

			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append(" request input parameters are invalid: <user>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			logger.error("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		return sb.toString();

	}

	/**
	 * File: 	HEADF/FAKT/TURER
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsFAKT_DB.do?user=OSCAR&registreringsdato=2016
	 * 
	 */
	@RequestMapping(value="syjsFAKT_DB.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String doFaktReportDashboard(HttpSession session, HttpServletRequest request) {
		//CSVOutputter<FaktDto> csvOutputter = new CSVOutputter<FaktDto>();
		//JsonResponseWriter2<FaktDto> jsonWriter = new JsonResponseWriter2<FaktDto>();
		JsonResponseWriter2<WrapperDto<FaktDto>> jsonWriter = new JsonResponseWriter2<WrapperDto<FaktDto>>();
		StringBuffer sb = new StringBuffer();
		WrapperDto<FaktDto> wrapperDto = new WrapperDto<FaktDto>();
		List<FaktDto> faktDtoList = null;
		
		logger.info("inside syjsFAKT_DB.do");
		
		try {
			String user = request.getParameter("user");
			//String year = request.getParameter("year");
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			FaktDto qDto = null;
            qDto = getDto(request);  
			
			
			if (StringUtils.hasValue(userName)) {
				logger.info("Retrieving data...");
				faktDtoList = faktDaoService.getStats(qDto);
				if (faktDtoList != null) {
					//sb.append(csvOutputter.writeAsString(faktDtoList));
					logger.info("faktDtoList.size()="+faktDtoList.size());
					logger.info("appendar till sb.");
					wrapperDto.setDtoList(faktDtoList);
					//sb.append(jsonWriter.setJsonResult_Common_GetList(userName, faktDtoList));
					sb.append(jsonWriter.setJsonResult_Common_GetComposite_No_Container(wrapperDto));
					logger.info("appendat till sb!");
				} else {
					errMsg = "ERROR on SELECT: Can not find FaktDao list";
					status = "error";
					logger.info( status + errMsg);
					sb.append(errMsg);
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append(" request input parameters are invalid: <user>");
				sb.append(userName + errMsg + status + dbErrorStackTrace);
			}
		} catch (Exception e) {
			logger.info("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		logger.info("About to return...");
		return sb.toString();

	}

	/**
	 * File: 	DWSUMPER
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsFAKT_DB_DW.do?user=FREDRIK&registreringsdato=2016
	 * 
	 */
	@RequestMapping(value="syjsFAKT_DB_DW.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String doFaktReportDashboardDw(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<WrapperDto<FaktDWDto>> jsonWriter = new JsonResponseWriter2<WrapperDto<FaktDWDto>>();
		StringBuffer sb = new StringBuffer();
		WrapperDto<FaktDWDto> wrapperDto = new WrapperDto<FaktDWDto>();
		List<FaktDWDto> faktDwDtoList = null;
		
		logger.info("inside syjsFAKT_DB_DW.do");
		
		try {
			String user = request.getParameter("user");
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			FaktDto qDto = null;
            qDto = getDto(request);  
			
			
			if (StringUtils.hasValue(userName)) {
				logger.info("Retrieving data...");
				faktDwDtoList = faktDaoService.getStatsFromDW(qDto);
				if (faktDwDtoList != null) {
					logger.info("faktDtoList.size()="+faktDwDtoList.size());
					logger.info("appendar till sb.");
					wrapperDto.setDtoList(faktDwDtoList);
					sb.append(jsonWriter.setJsonResult_Common_GetComposite_No_Container(wrapperDto));
					logger.info("appendat till sb!");
				} else {
					errMsg = "ERROR on SELECT: Can not find FaktDwDao list";
					status = "error";
					logger.info( status + errMsg);
					sb.append(errMsg);
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append(" request input parameters are invalid: <user>");
				sb.append(userName + errMsg + status + dbErrorStackTrace);
			}
		} catch (Exception e) {
			logger.info("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		logger.info("About to return...");
		return sb.toString();

	}	
	
	/**
	 * 
	 * @Example SELECT http://gw.systema.no:8080/syjservicesbcore/syjsFAKT_DB_DISTINCT_YEAR.do?user=FREDRIK
	 * 
	 */
	@RequestMapping(value="syjsFAKT_DB_DISTINCT_YEAR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SingleValueDto> jsonWriter = new JsonResponseWriter2<SingleValueDto>();
		StringBuffer sb = new StringBuffer();
		List<SingleValueDto> years = null;
		
		try {
			String user = request.getParameter("user");
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				years = faktDaoService.getAvailableYears();
				if (years != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, years));
				} else {
					errMsg = "ERROR on SELECT: Can not find SingleValueObject list";
					status = "error";
					logger.info( status + errMsg);
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

	/*
	 * Serve both overview and details.
	 * 
	 * in overview year is delivered, e.g. 2017, adding trailing month and day, e.g into 20170100
	 * In detail year and month is delivered, 201703, adding days, e.g. into  20170300
	 */
	private FaktDto getDto(HttpServletRequest request) {
		FaktDto qDto = new FaktDto();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(qDto);
        binder.bind(request);	
        
        if (qDto.getAvdelings() != null) {
        	String[] avd = qDto.getAvdelings().split(",");
        	for (int i = 0; i < avd.length; i++) {
        		qDto.getAvdelingList().add(avd[i]);
        	}
        }
        
        if (qDto.getSignatur() != null) {
        	String[] sign = qDto.getSignatur().split(",");
        	for (int i = 0; i < sign.length; i++) {
        		qDto.getSignaturList().add(sign[i]);
        	}
        }    
        
        if (qDto.getFavk() != null) {
        	String[] favk = qDto.getFavk().split(",");
        	for (int i = 0; i < favk.length; i++) {
        		qDto.getFavkList().add(favk[i]);
        	}
        }        
        
        logger.info("qDto="+ReflectionToStringBuilder.toString(qDto));
          
        
        return qDto;
	}	
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }	

	@Qualifier ("faktDaoService")
	private FaktDaoService faktDaoService;
	@Autowired
	@Required
	public void setFirmDaoService(FaktDaoService value){ this.faktDaoService = value; }
	public FaktDaoService getFirmDaoService(){ return this.faktDaoService; }		
	
	
}
