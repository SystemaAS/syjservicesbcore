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

import no.systema.jservices.common.dao.services.FortollingDaoService;
import no.systema.jservices.common.dto.FortollingDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_FORTOLLING_DATA {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_FORTOLLING_DATA.class.getName());

	/**
	 * File: SADH (fortolling no)
	 * 
	 * @Example SELECT
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsFORTOLLING_DB.do?user=OSCAR&registreringsdato=2016
	 * 
	 */
	@RequestMapping(value = "syjsFORTOLLING_DB.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doFortollingReportDashboard(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<FortollingDto> jsonWriter = new JsonResponseWriter2<FortollingDto>();
		StringBuffer sb = new StringBuffer();
		List<FortollingDto> fortollingDtoList = null;

		logger.info("inside syjsFORTOLLING_DB.do");
		
		try {
			String user = request.getParameter("user");
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			FortollingDto qDto = null;
            qDto = getDto(request);  
			
			if (StringUtils.hasValue(userName) && StringUtils.hasValue(qDto.getRegistreringsdato())) {
				logger.info("Retrieving data...");
				fortollingDtoList = fortollingDaoService.getStats(qDto);
				if (fortollingDtoList != null) {
					logger.info("fortollingDtoList.size()=" + fortollingDtoList.size());
					logger.info("appendar till sb.");
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, fortollingDtoList));
					logger.info("appendat till sb!");
				} else {
					errMsg = "ERROR on SELECT: Can not find FortollingDto list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(errMsg);
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append(" request input parameters are invalid: <user> and <year>");
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

	private FortollingDto getDto(HttpServletRequest request) {
		FortollingDto qDto = new FortollingDto();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(qDto);
        binder.bind(request);	
        
        if (qDto.getRegistreringsdato() != null) {
        	qDto.setRegistreringsdato(qDto.getRegistreringsdato() + "0000");
        }
        
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
        
        
        logger.info("qDto="+ReflectionToStringBuilder.toString(qDto));
        
        return qDto;
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

	@Qualifier("fortollingDaoService")
	private FortollingDaoService fortollingDaoService;

	@Autowired
	@Required
	public void setFortollingDaoService(FortollingDaoService value) {
		this.fortollingDaoService = value;
	}

	public FortollingDaoService getFortollingDaoService() {
		return this.fortollingDaoService;
	}

}
