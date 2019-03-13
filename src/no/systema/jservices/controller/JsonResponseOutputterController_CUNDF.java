package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.FirmDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.FirkuDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.FirmDaoServices;
import no.systema.jservices.common.dao.Cum3lmDao;
import no.systema.jservices.common.dao.FirkuDao;
import no.systema.jservices.common.dao.ViskundeDao;
import no.systema.jservices.common.dao.VispnrDao;
import no.systema.jservices.common.dao.services.FirkuDaoService;
import no.systema.jservices.common.dao.services.FirmvisDaoService;
import no.systema.jservices.common.dao.services.KodtftDaoService;
import no.systema.jservices.common.dao.services.KodtlikDaoService;
import no.systema.jservices.common.dao.services.KodtlkDaoService;
import no.systema.jservices.common.dao.services.KodtotyDaoService;
import no.systema.jservices.common.dao.services.ValufDaoService;
import no.systema.jservices.common.dao.services.ViskundeDaoService;
import no.systema.jservices.common.dao.services.VispnrDaoService;
import no.systema.jservices.common.elma.proxy.EntryRequest;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.controller.rules.SYCUNDFR_U;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.CundfDaoServices;
import no.systema.jservices.model.dao.services.EdiiDaoServices;


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * 
 * Listing Kunder and adding/updating/delete of Kunder int table: CUNDF
 * 
 * @author oscardelatorre
 * @date Aug 15, 2016
 * 
 */

@Controller
public class JsonResponseOutputterController_CUNDF {
	private static Logger logger = Logger.getLogger(JsonResponseOutputterController_CUNDF.class.getName());
	
	/**
	 *
	 * 	 File: 		CUNDF
	 * 	 PGM:		?		
	 * 	 Member: 	MAINT - AVD - Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR&kundnr=1
	 * @Example SELECT specific with unique firm: http://localhost:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR&kundnr=1&firma=SY
	 * @Example SELECT specific with name: http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR&knavn=SYS&firma=SY
	 * 
	 */
	@RequestMapping(value="syjsSYCUNDFR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuilder sb = new StringBuilder();
		
		try{
			logger.info("Inside syjsSYCUNDFR.do");
			String user = request.getParameter("user");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
            List list = null;
			
			if( StringUtils.hasValue(userName) ){
				CundfDao dao = new CundfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);

	            if (isEmpty(dao)) {
	            	list = cundfDaoServices.findFetchFirstRowsOnly(100, dbErrorStackTrace);
	            } else {
		            if( StringUtils.hasValue(dao.getKundnr()) ){
						logger.info("cundfDaoServices.findById");
						if( StringUtils.hasValue(dao.getFirma()) ){
							list = this.cundfDaoServices.findById(dao.getKundnr(), dao.getFirma(), dbErrorStackTrace);
						}else{
							list = this.cundfDaoServices.findById(dao.getKundnr(), dbErrorStackTrace);
						}
					} else {
						logger.info("cundfDaoServices.findAll");
						list = cundfDaoServices.findAll(dao, dbErrorStackTrace);
					}
	            }
	            
//	            if(dao.getKundnr()!=null && !"".equals(dao.getKundnr())){
//					logger.info("cundfDaoServices.findById");
//					if(dao.getFirma()!=null && !"".equals(dao.getFirma())){
//						list = this.cundfDaoServices.findById(dao.getKundnr(), dao.getFirma(), dbErrorStackTrace);
//					}else{
//						list = this.cundfDaoServices.findById(dao.getKundnr(), dbErrorStackTrace);
//					}
//				}else if (dao.getKnavn()!=null && !"".equals(dao.getKnavn())){
//					logger.info("cundfDaoServices.findByName");
//					if(dao.getFirma()!=null && !"".equals(dao.getFirma())){
//						list = this.cundfDaoServices.findByName(dao.getKnavn(), dao.getFirma(), dbErrorStackTrace);
//					}else{
//						list = this.cundfDaoServices.findByName(dao.getKnavn(), dbErrorStackTrace);
//					}
//				}else if (dao.getSyrg()!=null && !"".equals(dao.getSyrg())){
//					if(dao.getFirma()!=null && !"".equals(dao.getFirma())){
//						list = this.cundfDaoServices.findByOrgnr(dao.getSyrg(), dao.getFirma(), dbErrorStackTrace);
//					}else{
//						list = this.cundfDaoServices.findByOrgnr(dao.getSyrg(), dbErrorStackTrace);
//					}
//				}else{
//					list = this.cundfDaoServices.findFetchFirstRowsOnly(100, dbErrorStackTrace);
//				}
				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetCompositeList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getList";
					status = "error";
					logger.info("After SELECT:" + " " + status + errMsg );
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			}else{
				//write JSON error output
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}

		}catch(Exception e){
			//write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
		return sb.toString();
	}
	
	private boolean isEmpty(CundfDao qDao) {
		if (   StringUtils.hasValue(qDao.getKundnr()) 
			|| StringUtils.hasValue(qDao.getKnavn()) 
			|| StringUtils.hasValue(qDao.getSyrg()) 
			|| StringUtils.hasValue(qDao.getSonavn()) 
			|| StringUtils.hasValue(qDao.getSyland()) 
			|| StringUtils.hasValue(qDao.getPostnr()) 
			|| StringUtils.hasValue(qDao.getFirma())) {
			return false;
		} else {
			return true;
		}
	}


	/**
	 * 
	 * Update Database DML operations
	 * 	 File: 		CUNDF
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicestn/syjsSYCUNDFR_U.do?user=OSCAR&mode=U&xxx=yyy...
	 * 
	 * @Example ADD:
	 * 			http://gw.systema.no:8080/syjservicestn/syjsSYCUNDFR_U.do?user=OSCAR&mode=A&xxx=yyy....
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsSYCUNDFR_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsCundf_U( HttpSession session, HttpServletRequest request, Locale locale) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();	

		StringBuffer dbErrorStackTrace = new StringBuffer();
        String errMsg = "";
		String status = "ok";
		String userName = null;
		
		try{
			logger.info("Inside syjsSYCUNDFR_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            userName = this.bridfDaoServices.findNameById(user);
			
			//bind attributes is any
			CundfDao dao = new CundfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //Since no composite-support
            String mllm = request.getParameter("mllm");
            String m3m3 = request.getParameter("m3m3");

            logger.info("DAO="+ReflectionToStringBuilder.toString(dao));
   
            //rules
            SYCUNDFR_U rulerLord = new SYCUNDFR_U(vispnrDaoService, firkuDaoServices,request,entryRequest,ediiDaoServices,cundfDaoServices, valufDaoService, kodtlkDaoService , kodtotyDaoService , kodtlikDaoService, kodtftDaoService,sb, dbErrorStackTrace); 
			//Start processing now
			if (userName != null) {
				int dmlRetval = 0;
				if ("D".equals(mode)) {
					if (rulerLord.isValidInputForDelete(dao, userName, mode)) {
						dmlRetval = this.cundfDaoServices.cascadeDelete(dao, dbErrorStackTrace);
					} else {
						// write JSON error output
						errMsg = "ERROR on DELETE: invalid rulerLord";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				} else {
					if (rulerLord.isValidInput(dao, userName, mode)) {
						rulerLord.updateNumericFieldsIfNull(dao);
						if ("A".equals(mode)) {
							addFieldsToDaoWhenNew(dao, dbErrorStackTrace);
					        addCum3LmToDao(dao,m3m3,mllm );
	
					        dmlRetval = cundfDaoServices.insert(dao, dbErrorStackTrace);

					        manageVismaIntegration(dao, "INSERT");
					        
					        
						} else if ("U".equals(mode)) {
							addFieldsToDaoWhenEdit(dao, dbErrorStackTrace);
							addCum3LmToDao(dao,m3m3,mllm );
							
					        dmlRetval = cundfDaoServices.update(dao, dbErrorStackTrace);
			
					        manageVismaIntegration(dao, "UPDATE");
					        
						}
					} else {
						// write JSON error output
						errMsg = "ERROR on ADD/UPDATE: invalid rulerLord, error="+sb.toString();
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
						logger.error(sb);
					}
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
	 * Get info about if valid to register invoice customer
	 * 
	 * Example :
	 * http://localhost:8080/syjservicesbcore/syjsSYCUNDFR_INVOICE?user=SYSTEMA
	 */	
	@RequestMapping(path = "/syjsSYCUNDFR_INVOICE", method = RequestMethod.GET)
	@ResponseBody
	public String getInvoiceCustomerAllowed(HttpSession session,
									@RequestParam(value = "user", required = true) String user) {

		checkUser(user);
		StringBuffer errorStackTrace = new StringBuffer();
        
		logger.info("/syjsSYCUNDFR_INVOICE");
		boolean enabled = firkuDaoServices.invoiceCustomerEnabled(errorStackTrace);
		
		session.invalidate();
		
		if (enabled) {
			return "J";
		} else {
			return "N";
		}
		
	}	
	
	/**
	 * Get info about if orgnr exist.
	 * 
	 * Example :
	 * http://localhost:8080/syjservicesbcore/syjsSYCUNDFR_ORGNR_EXIST?user=SYSTEMA&syrg=936809219&kundnr=10
	 */	
	@RequestMapping(path = "/syjsSYCUNDFR_ORGNR_EXIST", method = RequestMethod.GET)
	@ResponseBody
	public String getOrgnrExist(HttpSession session,
									@RequestParam(value = "user", required = true) String user,
									@RequestParam(value = "syrg", required = true) String syrg,
									@RequestParam(value = "kundnr", required = false) String kundnr) {

		checkUser(user);
		StringBuffer errorStackTrace = new StringBuffer();
        
		logger.info("/syjsSYCUNDFR_ORGNR_EXIST");
		boolean enabled = cundfDaoServices.orgNrExist(syrg, kundnr ,errorStackTrace);
		
		session.invalidate();
		
		if (enabled) {
			return "J";
		} else {
			return "N";
		}
		
	}	


	/**
	 * Get info about if orgnr exist multiple times
	 * 
	 * Example :
	 * http://localhost:8080/syjservicesbcore/syjsSYCUNDFR_ORGNR_MULTI?user=SYSTEMA&syrg=936809219
	 */	
	@RequestMapping(path = "/syjsSYCUNDFR_ORGNR_MULTI", method = RequestMethod.GET)
	@ResponseBody
	public String getOrgnrMulti(HttpSession session,
									@RequestParam(value = "user", required = true) String user,
									@RequestParam(value = "syrg", required = true) String syrg) {

		checkUser(user);
		StringBuffer errorStackTrace = new StringBuffer();
        
		logger.info("/syjsSYCUNDFR_ORGNR_MULTI");
		int count = cundfDaoServices.orgNrCount(syrg, errorStackTrace);
		
		session.invalidate();
		
		if (count > 1) {
			return "J";
		} else {
			return "N";
		}
		
	}		
	
	
	/**
	 * Get info about if valid to register invoice customer
	 * 
	 * Example :
	 * http://localhost:8080/syjservicesbcore/syjsSYCUNDFR_FIRKU?user=SYSTEMA
	 */	
	@RequestMapping(path = "/syjsSYCUNDFR_FIRKU", method = RequestMethod.GET)
	@ResponseBody
	public String getFirku(HttpSession session,
									@RequestParam(value = "user", required = true) String user) {

		checkUser(user);
   
		logger.info("/syjsSYCUNDFR_FIRKU");
		JsonResponseWriter2<FirkuDao> jsonWriter2 = new JsonResponseWriter2<FirkuDao>();
		FirkuDao dao = firkuDaoService.get();

		StringBuilder sb = new StringBuilder(jsonWriter2.setJsonResult_Common_GetComposite(user, dao));

		session.invalidate();
			
		return sb.toString();
		
	}		
	
	private void manageVismaIntegration(CundfDao cundfDao, String dml) {
		if (!hasVismaNetIntegration() || "I".equals(cundfDao.getAktkod())) {
			return;
		}
		logger.info("manageVismaIntegration on dao="+cundfDao);
		ViskundeDao dao = getViskundeDao(cundfDao);		

		if(dml.equals("INSERT")) {
			viskundeDaoService.create(dao);
			logger.info("VISKUNDE created, dao="+dao);
		}

		if(dml.equals("UPDATE")) {
			if (viskundeDaoService.exist(dao)) {
				viskundeDaoService.update(dao);
				logger.info("VISKUNDE updated, dao="+dao);
			} else {
				if ("A".equals(dao.getAktkod())) { //Fakturakunde
					viskundeDaoService.create(dao);
					logger.info("VISKUNDE created, dao="+dao);				
				}
			}
		}
		
	}

	private ViskundeDao getViskundeDao(CundfDao cundfDao) {
		ViskundeDao dao = new ViskundeDao();
	
		dao.setFirma(cundfDao.getFirma()); //private String firma;
		dao.setKundnr(Integer.parseInt(cundfDao.getKundnr())); //private int kundnr; //key
		dao.setAktkod(cundfDao.getAktkod());  // private String aktkod;
		dao.setDkund(cundfDao.getDkund()); //private String dkund;
		dao.setKnavn(cundfDao.getKnavn()); //;private String knavn;
		dao.setAdr1(cundfDao.getAdr1()); //;private String adr1;
		dao.setAdr2(cundfDao.getAdr2()); //private String adr2;
		dao.setPostnr(Integer.parseInt(cundfDao.getPostnr()));  //private int postnr;
		dao.setAdr3(cundfDao.getAdr3()); //private String adr3;
		dao.setKpers(cundfDao.getKpers()); //private String kpers;
		dao.setTlf(cundfDao.getTlf());//private String tlf;
		dao.setValkod(cundfDao.getValkod()); //;private String valkod;
		dao.setSpraak(cundfDao.getSpraak()); //private String spraak;
		dao.setBankg(cundfDao.getBankg());//private String bankg;
		dao.setPostg(cundfDao.getPostg()); //private String postg;
		dao.setFmot(Integer.parseInt(cundfDao.getFmot())); //private int fmot;
		dao.setBetbet(cundfDao.getBetbet()); //private String betbet;
		dao.setBetmat(cundfDao.getBetmat()); //private String betmat;
		dao.setSfakt(cundfDao.getSfakt()); //private String sfakt;
		dao.setKgrens(Integer.parseInt(cundfDao.getKgrens()));  //private int kgrens;
		dao.setTfaxnr(cundfDao.getTfaxnr()); //private String tfaxnr;
		dao.setSyregn(Integer.parseInt(cundfDao.getSyregn())); //private int syregn;
		dao.setSykont(Integer.parseInt(cundfDao.getSykont())); //private int sykont;
		dao.setSylikv(cundfDao.getSylikv()); //private String sylikv;
		dao.setSyopdt(cundfDao.getSyopdt()); //private String syopdt;
		dao.setSyminu(new BigDecimal(cundfDao.getSyminu())); //;private BigDecimal syminu = new BigDecimal(0);
		dao.setSyutlp(new BigDecimal(cundfDao.getSyutlp())); //private BigDecimal syutlp = new BigDecimal(0);
		dao.setSyrg(cundfDao.getSyrg()); //private String syrg;
		dao.setSypoge(cundfDao.getSypoge()); //private String sypoge;
		dao.setSystat(cundfDao.getSystat()); //private String systat;
		dao.setSyland(cundfDao.getSyland()); //private String syland;
		dao.setSyselg(cundfDao.getSyselg()); //private String syselg;
		dao.setSyiat1(Integer.parseInt(cundfDao.getSyiat1())); //private int syiat1;
		dao.setSyiat2(Integer.parseInt(cundfDao.getSyiat2())); //private int syiat2;
		dao.setSycoty(cundfDao.getSycoty()); //private String sycoty;
		dao.setSyfr01(cundfDao.getSyfr01()); //private String syfr01;
		dao.setSyfr02(cundfDao.getSyfr02()); //private String syfr02;
		dao.setSyfr03(cundfDao.getSyfr03()); //private String syfr03;
		dao.setSyfr04(cundfDao.getSyfr04()); //private String syfr04;
		dao.setSyfr05(cundfDao.getSyfr05()); //private String syfr05;
		dao.setSyfr06(cundfDao.getSyfr06()); //private String syfr06;
		dao.setSysalu(Integer.parseInt(cundfDao.getSysalu())); //private int sysalu;
		dao.setSyepos(cundfDao.getSyepos()); //private String syepos;
		dao.setAknrku(Integer.parseInt(cundfDao.getAknrku())); //;private int aknrku;
		dao.setVatkku(cundfDao.getVatkku()); //private String vatkku;
		dao.setXxbre(new BigDecimal(cundfDao.getXxbre())); //private BigDecimal xxbre = new BigDecimal(0);
		dao.setXxlen(new BigDecimal(cundfDao.getXxlen())); //private BigDecimal xxlen = new BigDecimal(0);
		dao.setXxinm3(new BigDecimal(cundfDao.getXxinm3())); //private BigDecimal xxinm3 = new BigDecimal(0);
		dao.setXxinlm(new BigDecimal(cundfDao.getXxinlm())); //private BigDecimal xxinlm = new BigDecimal(0);
		dao.setRnraku(cundfDao.getRnraku()); //private String rnraku;
		dao.setGolk(cundfDao.getGolk()); //private String golk;
		dao.setKundgr(cundfDao.getKundgr()); //private String kundgr;
		dao.setPnpbku(cundfDao.getPnpbku()); //private String pnpbku;
		dao.setAdr21(cundfDao.getAdr21()); //private String adr21;
		dao.setEori(cundfDao.getEori()); //private String eori;
		dao.setSymvjn(cundfDao.getSymvjn()); //private String symvjn;
		dao.setSymvsp(cundfDao.getSymvsp()); //private String symvsp;	
		dao.setSyminu(new BigDecimal(cundfDao.getSyminu())); 
		
		return dao;
	}


	private boolean hasVismaNetIntegration() {
		logger.info(":::hasVismaNetIntegration:::");
		boolean hasVismaNet = false;

		if (firmvisDaoService.countAll() > 0) {
			hasVismaNet = true;
		}

		return hasVismaNet;
	}

	private void addCum3LmToDao(CundfDao dao, String m3m3, String mllm) {
		logger.info(":::addCum3LmToDao:::");
		Cum3lmDao cum3lmDao = new Cum3lmDao();
		cum3lmDao.setM3kund(Integer.parseInt(dao.getKundnr()));
		cum3lmDao.setM3firm(dao.getFirma());
		if (StringUtils.hasValue(m3m3)) {
			cum3lmDao.setM3m3(Integer.parseInt(m3m3));
		}
		if (StringUtils.hasValue(mllm)) {
			cum3lmDao.setMllm(Integer.parseInt(mllm));
		}

		dao.setCum3lmDao(cum3lmDao);
	}

	private void addFieldsToDaoWhenNew(CundfDao dao, StringBuffer dbErrorStackTrace) {
		logger.info(":::addFieldsToDaoWhenNew:::");
		if (!StringUtils.hasValue(dao.getSonavn())) {
			addDefaultSonavn(dao);
		}
		
		List<FirmDao> firmList = firmDaoServices.getList(dbErrorStackTrace);
		FirmDao firmDao = null;
		if (firmList.size() == 1) {
			firmDao = firmList.get(0);
		} else {
			logger.error("ERROR: Incorrect number of rows i Firma!");
			throw new IllegalArgumentException("Incorrect number of rows i Firma!");
		}

		dao.setFirma(firmDao.getFifirm());
		
		if (dao.getKundnr() != null && dao.getKundnr().length() == 0) {
			String kundNr = null;

			if(dao.getKundetype() ==  null || dao.getKundetype().length() == 0 || dao.getKundetype().equals("A")) { //A=Adressekunde
				kundNr = firkuDaoServices.getFikune(dbErrorStackTrace);
				logger.info("kundnr from FIKUNE, kundnr="+kundNr);
				dao.setAktkod("I");  //Always set to Adressekunde when new.
			} 
			else if (dao.getKundetype().equals("F")) { //F=Fakturakunde
				kundNr = String.valueOf(firkuDaoServices.getFikufn(dbErrorStackTrace));
				logger.info("kundnr from FIKUFN, kundnr="+kundNr);
				if (!firkuDaoServices.invoiceCustomerEnabled(dbErrorStackTrace)) {
					logger.error("ERROR: Not allowed to create invoice customer!");
					throw new IllegalArgumentException("Not allowed to create invoice customer!");
				}
			} else { //something wrong
				throw new RuntimeException("Kundetype not valid!, kundetype="+dao.getKundetype());
			}
			
			dao.setKundnr(kundNr);
		
		}
		
		if (hasVismaNetIntegration()) {
			addLandkodeIfPostnrIsNorsk(dao);
		}
		
		
	}

	private void addLandkodeIfPostnrIsNorsk(CundfDao dao) {
		logger.info(":::addLandkodeIfPostnrIsNorsk:::");
		if (!StringUtils.hasValue(dao.getPostnr())) {
			return;
		}
		VispnrDao qDao = new VispnrDao();
		qDao.setViponr(dao.getPostnr());
		qDao.setVilk("NO");
		
		if (vispnrDaoService.exist(qDao)) {
			dao.setSyland("NO");
		}
	}

	private void addFieldsToDaoWhenEdit(CundfDao dao, StringBuffer dbErrorStackTrace) {
		logger.info(":::addFieldsToDaoWhenEdit:::");
		if (!StringUtils.hasValue(dao.getSonavn())) {
			addDefaultSonavn(dao);
		}	
		
		if (hasVismaNetIntegration()) {
			addLandkodeIfPostnrIsNorsk(dao);
		}
		
	}
	
	private void addDefaultSonavn(CundfDao dao) {
		int knavnLength = dao.getKnavn().length();
		if (knavnLength > 10) {
			dao.setSonavn(dao.getKnavn().substring(0, 10));
		} else {
			dao.setSonavn(dao.getKnavn());
		}
	}

	private void checkUser(String user) {
		if (bridfDaoServices.findNameById(user) == null) {
			throw new RuntimeException("ERROR: parameter, user, is not valid!");
		}
	}	
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("cundfDaoServices")
	private CundfDaoServices cundfDaoServices;
	@Autowired
	@Required
	public void setCundfDaoServices (CundfDaoServices value){ this.cundfDaoServices = value; }
	public CundfDaoServices getCundfDaoServices(){ return this.cundfDaoServices; }

	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }

	@Qualifier ("firmDaoServices")
	private FirmDaoServices firmDaoServices;
	@Autowired
	@Required
	public void setFirmDaoServices (FirmDaoServices value){ this.firmDaoServices = value; }
	public FirmDaoServices getFirmDaoServices(){ return this.firmDaoServices; }	

	@Qualifier ("firkuDaoServices")
	private FirkuDaoServices firkuDaoServices;
	@Autowired
	@Required
	public void setFirkuDaoServices (FirkuDaoServices value){ this.firkuDaoServices = value; }
	public FirkuDaoServices getFirkuDaoServices(){ return this.firkuDaoServices; }	

	@Qualifier ("valufDaoService")
	private ValufDaoService valufDaoService;
	@Autowired
	@Required
	public void setValufDaoService (ValufDaoService value){ this.valufDaoService = value; }
	public ValufDaoService getValufDaoService(){ return this.valufDaoService; }		
	
	@Qualifier ("kodtlkDaoService")
	private KodtlkDaoService kodtlkDaoService;
	@Autowired
	@Required
	public void setKodtlkDaoService (KodtlkDaoService value){ this.kodtlkDaoService = value; }
	public KodtlkDaoService getKodtlkDaoService(){ return this.kodtlkDaoService; }		

	@Qualifier ("kodtlikDaoService")
	private KodtlikDaoService kodtlikDaoService;
	@Autowired
	@Required
	public void setKodtlikDaoService (KodtlikDaoService value){ this.kodtlikDaoService = value; }
	public KodtlikDaoService getKodtlikDaoService(){ return this.kodtlikDaoService; }			
	
	@Qualifier ("kodtotyDaoService")
	private KodtotyDaoService kodtotyDaoService;
	@Autowired
	@Required
	public void setKodtotyDaoService (KodtotyDaoService value){ this.kodtotyDaoService = value; }
	public KodtotyDaoService getKodtotyDaoService(){ return this.kodtotyDaoService; }			

	@Qualifier ("kodtftDaoService")
	private KodtftDaoService kodtftDaoService;
	@Autowired
	@Required
	public void setKodtftDaoService (KodtftDaoService value){ this.kodtftDaoService = value; }
	public KodtftDaoService getKodtftDaoService(){ return this.kodtftDaoService; }		

	private EdiiDaoServices ediiDaoServices;
	@Autowired
	@Required
	public void setEdiiDaoServices (EdiiDaoServices value){ this.ediiDaoServices = value; }
	public EdiiDaoServices getEdiiDaoServices(){ return this.ediiDaoServices; }		
	
	@Autowired
	ViskundeDaoService viskundeDaoService;

	@Autowired
	FirmvisDaoService firmvisDaoService;
	
	@Autowired
	EntryRequest entryRequest;
	
	@Autowired
	FirkuDaoService firkuDaoService;
	
	@Autowired
	VispnrDaoService vispnrDaoService;	
	
	
}

