package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import no.systema.jservices.bcore.z.maintenance.controller.rules.SADVARE_U;
import no.systema.jservices.common.dao.SadvareDao;
import no.systema.jservices.common.dao.services.Kodts2DaoService;
import no.systema.jservices.common.dao.services.Kodts5DaoService;
import no.systema.jservices.common.dao.services.Kodts6DaoService;
import no.systema.jservices.common.dao.services.Kodts7DaoService;
import no.systema.jservices.common.dao.services.Kodts8DaoService;
import no.systema.jservices.common.dao.services.KodtsaDaoService;
import no.systema.jservices.common.dao.services.KodtsbDaoService;
import no.systema.jservices.common.dao.services.KodtvalfDaoService;
import no.systema.jservices.common.dao.services.SadvareDaoService;
import no.systema.jservices.common.dao.services.TariDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.controller.rules.EDIMR_U;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.EdimDao;
import no.systema.jservices.model.dao.entities.Sviv_aggDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.EdimDaoServices;
import no.systema.jservices.model.dao.services.Sviv_aggDaoServices;

@RestController
public class JsonResponseOutputterController_SVIV_AGG {
	private static final Logger logger = Logger.getLogger(JsonResponseOutputterController_SVIV_AGG.class.getName());

	/**
	 * File: SVIV_AGG
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE.do?user=OSCAR&levenr=1&varenr=9004901000
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE.do?user=OSCAR&levenr=1
	 * 
	 */
	/*
	@RequestMapping(value = "syjsSADVARE.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doSadvare(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SadvareDao> jsonWriter = new JsonResponseWriter2<SadvareDao>();
		StringBuffer sb = new StringBuffer();
		List<SadvareDao> sadvareDaoList = new ArrayList<SadvareDao>();
		String levenr = request.getParameter("levenr");
		String varenr = request.getParameter("varenr");

		try {
			String user = request.getParameter("user");
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			if ((userName != null && !"".equals(userName)) && (levenr != null && !"".equals(levenr))) {
				if (varenr != null && !"".equals(varenr)) {
					SadvareDao dao = fetchRecord(levenr, varenr);
					sadvareDaoList.add(dao);
				} else {
					sadvareDaoList = fetchList(levenr);
				}
				if (sadvareDaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, sadvareDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find SadvareDao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user> and <levenr> ");
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
*/
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/foo.do",  method=RequestMethod.POST )
	@ResponseStatus(HttpStatus.OK)
	public Map update(@RequestBody Collection<Object> input, HttpServletRequest request) throws Exception{
		logger.warn("remote host:" + request.getRemoteHost() + " " + "remote ipadress:" + request.getRemoteAddr());
		
		Map responseMap = new HashMap();
		responseMap.put("data", input.toString());
		
		
		return responseMap;
		}

	/**
	 * Update Database DML operations File: SVIV_AGG
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVIV_AGG_U.do?user=OSCAR&mode=U/A/D...
	 *
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "syjsSVIV_AGG_U.do", method = { RequestMethod.GET, RequestMethod.POST } )
	@ResponseBody
	public String syjsSVIV_AGG_U(@RequestBody List<Sviv_aggDao> itemList, HttpSession session, HttpServletRequest request) {
		
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();	

		StringBuffer dbErrorStackTrace = new StringBuffer();
        String errMsg = "";
		String status = "ok";
		String userName = null;
		for(Sviv_aggDao dao: itemList) {
			logger.warn("VATA:" + dao.getSviv_vata());
			logger.warn("VANO:" + dao.getSviv_vano());
			
		}
			
		
		try{
			logger.info("Inside syjsSVIV_AGG_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			
			
			//Check ALWAYS user in BRIDF
            userName = this.bridfDaoServices.findNameById(user);
            
            int retval = this.sviv_aggDaoServices.insert(itemList, dbErrorStackTrace);
            if(retval>=0) {
            	logger.warn(itemList);
            	sb.append(itemList.size());
            }
            /*
            int retval = 0;
            for(Sviv_aggDao dao : itemList) {
            	retval = this.sviv_aggDaoServices.insert(dao, dbErrorStackTrace);
            	sb.append(itemList.size());
            }*/
            
            
			/*
			//bind attributes is any
			EdimDao dao = new EdimDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            
            logger.info("DAO="+ReflectionToStringBuilder.toString(dao));
   
            //rules
            EDIMR_U rulerLord = new EDIMR_U(); 
			//Start processing now
			if (userName != null) {
				int dmlRetval = 0;
				
				if (rulerLord.isValidInput(dao, userName, mode)) {
					if ("A".equals(mode)) {
						if(rulerLord.isValidInputInsert(dao, user, mode)){
							if(StringUtils.isNotEmpty(specialInsert)) {
								dmlRetval = edimDaoServices.insertWhenInboundFile(dao, dbErrorStackTrace);
							}else {
								dmlRetval = edimDaoServices.insert(dao, dbErrorStackTrace);
							}
						}else {
							// write JSON error output
							errMsg = "ERROR on INSERT: invalid rulerLord error";
							status = "error";
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							logger.error(sb);
						}
					} else if ("U".equals(mode)) {
						if(rulerLord.isValidInputUpdate(dao, user, mode)){
							dmlRetval = edimDaoServices.update(dao, dbErrorStackTrace);
						}else {
							// write JSON error output
							errMsg = "ERROR on UPDATE: invalid rulerLord error";
							status = "error";
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							logger.error(sb);
						}
				        
					}
				} else {
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
					errMsg = "ERROR on ADD/UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					logger.error(sb);
				} else {
					// OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, dao, status));
				}

			} else {
				// write JSON error output
				errMsg = "ERROR on ADD/UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				logger.error(sb);
			}
			*/
			
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
