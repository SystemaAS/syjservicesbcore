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
import no.systema.jservices.common.dao.services.FaktDaoService;
import no.systema.jservices.common.dto.FaktDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
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
		CSVOutputter<FaktDto> csvOutputter = new CSVOutputter<FaktDto>();
		JsonResponseWriter2<FaktDto> jsonWriter = new JsonResponseWriter2<FaktDto>();
		StringBuffer sb = new StringBuffer();
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
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, faktDtoList));
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

	
	private FaktDto getDto(HttpServletRequest request) {
		FaktDto qDto = new FaktDto();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(qDto);
        binder.bind(request);	
        
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
