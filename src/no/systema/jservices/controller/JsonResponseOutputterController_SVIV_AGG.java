package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.SvivRflnDao;
import no.systema.jservices.model.dao.entities.Sviv_aggDao;
import no.systema.jservices.model.dao.entities.Sviv_aggWrapper;
import no.systema.jservices.model.dao.entities.Sviva_aggDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.Sviv_aggDaoServices;

@RestController
public class JsonResponseOutputterController_SVIV_AGG {
	private static final Logger logger = LogManager.getLogger(JsonResponseOutputterController_SVIV_AGG.class.getName());

	/**
	 * File: SVIV_AGG
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSVIV_AGG.do?user=OSCAR&avd=1&opd=253&lin=1
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVIV_AGG.do?user=OSCAR&avd=1&opd=253
	 * 
	 */
	
	@RequestMapping(value = "syjsSVIV_AGG.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doSviv(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		String user = request.getParameter("user");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		String lin = request.getParameter("lin");
		
		

		try {
			
			String userName = this.bridfDaoServices.findNameById(user);
			//logger.warn(userName + avd + opd);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			logger.warn("Inside syjsSVIV_AGG.do");
			if (StringUtils.isNotEmpty(userName) && (StringUtils.isNotEmpty(avd) && StringUtils.isNotEmpty(opd)) ) {
				Sviv_aggDao dao = new Sviv_aggDao();
				dao.setSviv_syav(avd);
				dao.setSviv_syop(opd);
				dao.setSviv_syli(lin);
				List list = null;
				if (lin != null && !"".equals(lin)) {
					list = this.sviv_aggDaoServices.findById(dao, dbErrorStackTrace);
					
				} else {
					logger.warn("getting list (avd/opd)");
					list = this.sviv_aggDaoServices.getList(dao, dbErrorStackTrace);
				}
				
				if (list != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				} else {
					errMsg = "ERROR on SELECT: Can not find SVIV_AGG list";
					status = "error";
					logger.warn(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT: request input parameters are invalid: <user> and <avd/opd>";
				status = "error";
				dbErrorStackTrace.append(errMsg);
				logger.warn(status + errMsg);
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			logger.warn("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		return sb.toString();

	}


	/**
	 * Update Database DML operations File: SVIV_AGG
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVIV_AGG_U.do?user=OSCAR&mode=U/A/D...
	 *          
	 * TEST = OK from curl (to test the rest lists only)         
	 * a)curl -H 'Content-Type:application/json;;charset=utf-8' -X POST 'http://localhost:8080/syjservicesbcore/syjsSVIV_AGG_U.do?user=OSCAR&mode=A' -d '{"dataAggr":[{"sviv_syav":"1","sviv_syop":"253","sviv_syli":"1","sviv_vata":"111"}, {"sviv_syav":"1","sviv_syop":"253","sviv_syli":"2","sviv_vata":"222"}], "dataRfln":[{"sviv_syav":"1","sviv_syop":"253","sviv_syli":"1","sviv_vata":"111","sviv_rfln":"1"}, {"sviv_syav":"1","sviv_syop":"253","sviv_syli":"2","sviv_vata":"222", "sviv_rfln":"2"}], "dataAggrAvg":[], "dataSviv":[]}'
	 * b)curl -H 'Content-Type:application/json;;charset=utf-8' -X POST 'http://localhost:8080/syjservicesbcore/syjsSVIV_AGG_U.do?user=OSCAR&mode=D&avd=1&opd=253' -d '{"dataAggr":[], "dataRfln":[], "dataAggrAvg":[], "dataSviv":[]}'
	 * 
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "syjsSVIV_AGG_U.do", method = { RequestMethod.GET, RequestMethod.POST } )
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String syjsSVIV_AGG_U(@RequestParam("avd") String avd, @RequestParam("opd") String opd,
							 	 @RequestBody Sviv_aggWrapper wrapper, HttpSession session, HttpServletRequest request) {
		
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();	

		StringBuffer dbErrorStackTrace = new StringBuffer();
        String errMsg = "";
		String status = "ok";
		String userName = null;
		
		try{
			logger.warn("Inside syjsSVIV_AGG_U.do");
			//debug
            if(wrapper.getDataAggr()!=null) { logger.warn("WRAPPER-rest-service--> dataAggr-list:" + wrapper.getDataAggr().size()); }
    		if(wrapper.getDataRfln()!=null) { logger.warn("WRAPPER-rest-service--> dataRfln-list:" + wrapper.getDataRfln().size()); }
    		if(wrapper.getDataAggrAvg()!=null) { logger.warn("WRAPPER-rest-service--> dataAggrAvg-list:" + wrapper.getDataAggrAvg().size()); }
    		if(wrapper.getDataSviv()!=null) { logger.warn("WRAPPER-rest-service--> dataSviv-list:" + wrapper.getDataSviv().size()); }
            
    		String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			Collection<Sviv_aggDao> itemListSviv_agg = wrapper.getDataAggr();
			Collection<SvivRflnDao> itemListRfln = wrapper.getDataRfln();
			Collection<Sviva_aggDao> itemListAvgifter = wrapper.getDataAggrAvg();
			Collection<SvivRflnDao> itemListSviv = wrapper.getDataSviv();
			
			
			//Check ALWAYS user in BRIDF
            userName = this.bridfDaoServices.findNameById(user);
            
			//Start processing now
			if (userName != null) {
				int dmlRetval = 0;
				
				if ( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)) {
					if ("A".equals(mode)) {
						logger.warn("update vano");
						dmlRetval = this.sviv_aggDaoServices.updateVanoSviv((List)itemListSviv, dbErrorStackTrace);
						if(dmlRetval>=0) {
							dmlRetval = this.sviv_aggDaoServices.insertSviv_agg((List)itemListSviv_agg, (List)itemListAvgifter, dbErrorStackTrace);
							if(dmlRetval>=0) {
								//update SVIV (set sviv_rfln)
								dmlRetval = this.sviv_aggDaoServices.updateRflnSviv((List)itemListRfln, dbErrorStackTrace);
							}
							sb.append("INSERT(OK) ");
						}
						
					}else if (mode.equals("D")) {
						logger.warn("DELETE mode... cleaning up (sviv_agg/sviva_agg/sviv(rfln) since there is no aggregation requirement in this operation");
						Sviv_aggDao dao = new Sviv_aggDao();
						dao.setSviv_syav(avd);
						dao.setSviv_syop(opd);
						
						dmlRetval = this.sviv_aggDaoServices.delete(dao, dbErrorStackTrace);
						if(dmlRetval>=0) {
							logger.warn("update vano");
							dmlRetval = this.sviv_aggDaoServices.updateVanoSviv((List)itemListSviv, dbErrorStackTrace);
							logger.warn("blank rfln");
							dmlRetval = this.sviv_aggDaoServices.blankRfln(avd, opd, dbErrorStackTrace);
						}
						sb.append("DELETE Sviv_agg and UPDATE vano/rfln(OK) ");
						
					}else {
						// write JSON error output
						errMsg = "ERROR on INSERT: invalid itemList size or mode. Inspect log files ...";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
						logger.error(sb);
					}
				}else {
					// write JSON error output
					errMsg = "ERROR on INSERT/UPDATE: invalid rulerLord, error";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					logger.error(sb);
				}
				
				// ----------------------------------
				// check returns from dml operations
				// ----------------------------------
				if (dmlRetval < 0) {
					// write JSON error output
					errMsg = "ERROR on ADD: invalid after executing the INSERT. Try to check: <DaoServices>.insert";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					logger.error(sb);
				} else {
					// OK 
					Sviv_aggDao response = null;
					if(itemListSviv_agg!=null && itemListSviv_agg.size()>0) {
						response = itemListSviv_agg.stream().findFirst().get();
					}else {
						response = new Sviv_aggDao();
						response.setSviv_syav(avd);
						response.setSviv_syop(opd);
					}
					logger.warn( response );
					//return web service ...
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, response, status));
				}

			} else {
				// write JSON error output
				errMsg = "ERROR on ADD/UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				logger.error(sb);
			}
			
			
		}catch(Exception e){
			//write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			logger.info(sb);
			logger.error(":::ERROR:::",e);
			errMsg = "ERROR on ADD/UPDATE:  error="+e.getMessage();
			status = "error";
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));

		}

		session.invalidate();
		return sb.toString();

	}
	/**
	 * Child table - secondary
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "syjsSVIVA_AGG.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doSviva(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		String user = request.getParameter("user");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		String lin = request.getParameter("lin");
		
		

		try {
			
			String userName = this.bridfDaoServices.findNameById(user);
			//logger.warn(userName + avd + opd);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			logger.warn("Inside syjsSVIVA_AGG.do");
			if (StringUtils.isNotEmpty(userName) && (StringUtils.isNotEmpty(avd) && StringUtils.isNotEmpty(opd)) ) {
				Sviv_aggDao dao = new Sviv_aggDao();
				dao.setSviv_syav(avd);
				dao.setSviv_syop(opd);
				dao.setSviv_syli(lin);
				List list = null;
				list = this.sviv_aggDaoServices.findByIdSviva(avd,opd,lin, dbErrorStackTrace);
				

				if (list != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				} else {
					errMsg = "ERROR on SELECT: Can not find SVIV_AGG list";
					status = "error";
					logger.warn(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT: request input parameters are invalid: <user> and <avd/opd>";
				status = "error";
				dbErrorStackTrace.append(errMsg);
				logger.warn(status + errMsg);
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			logger.warn("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		return sb.toString();

	}

	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private Sviv_aggDaoServices sviv_aggDaoServices;
	public void setSviv_aggDaoServices (Sviv_aggDaoServices value){ this.sviv_aggDaoServices = value; }
	public Sviv_aggDaoServices getSviv_aggDaoServices(){ return this.sviv_aggDaoServices; }
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }

}
