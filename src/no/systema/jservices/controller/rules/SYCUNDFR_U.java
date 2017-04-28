package no.systema.jservices.controller.rules;


import javax.servlet.http.HttpServletRequest;

import no.systema.jservices.common.dao.KodtlikDao;
import no.systema.jservices.common.dao.ValufDao;
import no.systema.jservices.common.dao.services.KodtftDaoService;
import no.systema.jservices.common.dao.services.KodtlikDaoService;
import no.systema.jservices.common.dao.services.KodtlkDaoService;
import no.systema.jservices.common.dao.services.KodtotyDaoService;
import no.systema.jservices.common.dao.services.ValufDaoService;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.services.CundfDaoServices;
import no.systema.main.util.MessageSourceHelper;
/**
 * 
 * @author oscardelatorre
 * @date Aug 4, 2016
 */
public class SYCUNDFR_U {

	private JsonResponseWriter jsonWriter = new JsonResponseWriter();
	private MessageSourceHelper messageSourceHelper = null;
	private CundfDaoServices cundfDaoServices = null;
	private ValufDaoService valufDaoService = null;
	private KodtlkDaoService kodtlkDaoService = null;
	private KodtlikDaoService kodtlikDaoService = null;
	private KodtotyDaoService kodtotyDaoService = null;
	private KodtftDaoService kodtftDaoService = null;
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;


	public SYCUNDFR_U(HttpServletRequest request, CundfDaoServices cundfDaoServices,  ValufDaoService valufDaoService, KodtlkDaoService kodtlkDaoService, KodtotyDaoService kodtotyDaoService, KodtlikDaoService kodtlikDaoService, KodtftDaoService kodtftDaoService, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.cundfDaoServices = cundfDaoServices;
		this.valufDaoService = valufDaoService;
		this.kodtlkDaoService = kodtlkDaoService;
		this.kodtlikDaoService = kodtlikDaoService;
		this.kodtotyDaoService = kodtotyDaoService;
		this.kodtftDaoService = kodtftDaoService;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}	
	
	/**
	 * Validate null values and exist controls i db.
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(CundfDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			if ( (dao.getKnavn() != null && !"".equals(dao.getKnavn())) && (dao.getAdr3() != null && !"".equals(dao.getAdr3()))) {
				if ("A".equals(mode)  &&  existInCundf(dao.getKundnr() )) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.kundnr", new Object[] { dao.getKundnr()}), "error", dbErrors));
					retval = false;
				}
				if ( (dao.getValkod() != null  && !"".equals(dao.getValkod()) ) && !existInValuf(dao.getValkod())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.valkod", new Object[] { dao.getValkod()}), "error", dbErrors));
					retval = false;					
				}

				if ( (dao.getSyland() != null  && !"".equals(dao.getSyland()) ) && !existInKodtlk(dao.getSyland())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.syland", new Object[] { dao.getSyland()}), "error", dbErrors));
					retval = false;					
				}	
				
				if ( (dao.getSyopdt() != null  && !"".equals(dao.getSyopdt()) ) && !existInKodtoty(dao.getSyopdt())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.syopdt", new Object[] { dao.getSyopdt()}), "error", dbErrors));
					retval = false;					
				}	

				if ( (dao.getSylikv() != null  && !"".equals(dao.getSylikv()) ) && !existInKodtlik(dao.getSylikv())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.sylikv", new Object[] { dao.getSylikv()}), "error", dbErrors));
					retval = false;					
				}	

				if (StringUtils.hasValue(dao.getFmot()) && dao.getFmot().equals(dao.getKundnr())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.fmot.equals", new Object[] { dao.getFmot()}), "error", dbErrors));
					retval = false;							
					
				}
				if ( (StringUtils.hasValue(dao.getFmot())) && !existInCundf(dao.getFmot())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.fmot", new Object[] { dao.getFmot()}), "error", dbErrors));
					retval = false;					
				}					
				if ( (StringUtils.hasValue(dao.getSyfr03())) && !existInKodtft(dao.getSyfr03())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.syfr03", new Object[] { dao.getSyfr03()}), "error", dbErrors));
					retval = false;					
				}					
				
			} else{ 
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.mandatory", null), "error", dbErrors));
				retval = false; 
			 }
			 
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.mandatory", null), "error", dbErrors));
			retval = false;
		}
		return retval;
	}


	public boolean isValidInputForDelete(CundfDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			/*
			if( dao.getKoaavd()!=null && !"".equals(dao.getKoaavd()) ){
				//OK
			}else{
				retval = false;
			}*/			  
		}else{
			retval = false;
		}
		return retval;
	}

	/**
	 * Conversion of data to fit in DB2-file
	 * 
	 * <li>Setting num to 0</li>
	 * <li>Setting dec separator from , to . </li>
	 * 
	 * 
	 * @param dao
	 */
	public void updateNumericFieldsIfNull(CundfDao dao){
		String ZERO = "0";
		if(dao.getFmot()==null || "".equals(dao.getFmot())){
			dao.setFmot(ZERO);
		}
		if(dao.getBetbet()==null || "".equals(dao.getBetbet())){
			dao.setBetbet(ZERO);
		}
		if(dao.getPostnr()==null || "".equals(dao.getPostnr())){
			dao.setPostnr(ZERO);
		}
		if(dao.getXxinm3()==null || "".equals(dao.getXxinm3())){
			dao.setXxinm3(ZERO);
		}
		if(dao.getXxinlm()==null || "".equals(dao.getXxinlm())){
			dao.setXxinlm(ZERO);
		}
		if(dao.getAknrku()==null || "".equals(dao.getAknrku())){
			dao.setAknrku(ZERO);
		}
		if(dao.getSyregn()==null || "".equals(dao.getSyregn())){
			dao.setSyregn(ZERO);
		}
		if(dao.getSyiat1()==null || "".equals(dao.getSyiat1())){
			dao.setSyiat1(ZERO);
		}
		if(dao.getSyiat2()==null || "".equals(dao.getSyiat2())){
			dao.setSyiat2(ZERO);
		}
		if(dao.getKgrens()==null || "".equals(dao.getKgrens())){ //Pakket
			dao.setKgrens(ZERO);
		}
		if(dao.getSykont()==null || "".equals(dao.getSykont())){ //Pakket
			dao.setSykont(ZERO);
		}
		if(dao.getSysalu()==null || "".equals(dao.getSysalu())){ //Pakket
			dao.setSysalu(ZERO);
		}
		if(dao.getSyminu()!=null && !"".equals(dao.getSyminu())){ //Tegn, decimal
			String tmp = dao.getSyminu().replace(",", ".");
			dao.setSyminu(tmp);
		}else{
			dao.setSyminu(ZERO);
		}		
		if(dao.getSyutlp()!=null && !"".equals(dao.getSyutlp())){ //Tegn, decimal
			String tmp = dao.getSyutlp().replace(",", ".");
			dao.setSyutlp(tmp);
		}else{
			dao.setSyutlp(ZERO);
		}		
		if(dao.getXxbre()!=null && !"".equals(dao.getXxbre())){ //Tegn, decimal
			String tmp = dao.getXxbre().replace(",", ".");
			dao.setXxbre(tmp);
		}else{
			dao.setXxbre(ZERO);
		}		
		if(dao.getXxlen()!=null && !"".equals(dao.getXxlen())){ //Tegn, decimal
			String tmp = dao.getXxlen().replace(",", ".");
			dao.setXxlen(tmp);
		}else{
			dao.setXxlen(ZERO);
		}	
	}


	private boolean existInCundf(String kundNr) {
		boolean exists = this.cundfDaoServices.exists(kundNr ,dbErrors);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	
	private boolean existInValuf(String valkod) {
		ValufDao qDao = new ValufDao();
		qDao.setValkod(valkod);
		boolean exists = valufDaoService.exist(qDao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}	

	private boolean existInKodtlk(String syland) {
		boolean exists = kodtlkDaoService.landKodeExist(syland);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}		

	private boolean existInKodtoty(String syopdt) {
		boolean exists = kodtotyDaoService.oppdragsTypeExist(syopdt);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}		
	
	private boolean existInKodtlik(String sylikv) {
		KodtlikDao qDao = new KodtlikDao();
		qDao.setKlikod(sylikv);
		boolean exists = kodtlikDaoService.exist(qDao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}	

	private boolean existInKodtft(String syfr03) {
		boolean exists = kodtftDaoService.kfttypExist(syfr03);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}		
	
}
