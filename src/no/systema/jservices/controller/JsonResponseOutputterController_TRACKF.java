package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.common.dao.TrackfDao;
import no.systema.jservices.common.dao.services.BridfDaoService;
import no.systema.jservices.common.dao.services.TrackfDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;

@Controller
public class JsonResponseOutputterController_TRACKF {
	private static final Logger logger = Logger.getLogger(JsonResponseOutputterController_TRACKF.class.getName());

	/**
	 * File: TRACKF
	 * 
	 * @Example SELECT specific:
	 *          http://gw.systema.no:8080/syjservicestror/syjsTRACKF.do?user=OSCAR&ttavd=1&ttopd=184...
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicestror/syjsTRACKF.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value = "syjsTRACKF.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsTRAN(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<TrackfDao> jsonWriter = new JsonResponseWriter2<TrackfDao>();
		StringBuffer sb = new StringBuffer();
		List<TrackfDao> trackDaoList = new ArrayList<TrackfDao>();

		try {
			logger.info("Inside syjsTRACKF");
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = bridfDaoService.getUserName(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if (StringUtils.hasValue(userName)) {
				TrackfDao resultDao = new TrackfDao();
				TrackfDao dao = new TrackfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);

				if (dao.getTtavd() > 0 && dao.getTtopd() > 0 ) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("ttavd", dao.getTtavd());
					params.put("ttopd", dao.getTtopd());
					//get specific 
					if(dao.getTtdate() > 0 && dao.getTttime() > 0){
						params.put("ttdate", dao.getTtdate());
						params.put("tttime", dao.getTttime());
						trackDaoList = trackfDaoService.findAll(params);
					}else{
						trackDaoList = trackfDaoService.findAll(params);
					}
					
				} else {
					//could be enormous-->trackDaoList = trackfDaoService.findAll(null);
					//empty list ...
				}
				if (trackDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, trackDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find TrackfDao list";
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

	@Qualifier("bridfDaoService")
	private BridfDaoService bridfDaoService;

	@Autowired
	@Required
	public void setBridfDaoService(BridfDaoService value) {
		this.bridfDaoService = value;
	}

	public BridfDaoService getBridfDaoService() {
		return this.bridfDaoService;
	}

	@Qualifier("trackDaoService")
	private TrackfDaoService trackfDaoService;

	@Autowired
	@Required
	public void setTrackfDaoService(TrackfDaoService value) {
		this.trackfDaoService = value;
	}

	public TrackfDaoService getTrackfDaoService() {
		return this.trackfDaoService;
	}

}
