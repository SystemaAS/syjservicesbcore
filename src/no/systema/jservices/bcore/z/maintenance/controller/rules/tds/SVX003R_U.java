package no.systema.jservices.bcore.z.maintenance.controller.rules.tds;

import java.util.*;

import org.apache.logging.log4j.*;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.tds.SvxstdDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.tds.SvxstdfvDao;
import no.systema.jservices.model.dao.services.EdiiDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.SvxkodfDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date Jun 16, 2017
 */
public class SVX003R_U {
	private static Logger logger = LogManager.getLogger(SVX003R_U.class.getName());
	private EdiiDaoServices ediiDaoServices;
	private SvxkodfDaoServices svxkodfDaoServices;
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	/**
	 * 
	 * @param ediiDaoServices
	 */
	public SVX003R_U(EdiiDaoServices ediiDaoServices, SvxkodfDaoServices svxkodfDaoServices){
		this.ediiDaoServices = ediiDaoServices;
		this.svxkodfDaoServices = svxkodfDaoServices;
	}

	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @param errorStackTrace
	 * @return
	 */
	public boolean isValidInput(SvxstdDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getThavd()!=null && !"".equals(dao.getThavd())) ){	
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		//-----------------------
		//now some logical tests
		//-----------------------
		//(1) Validity of Exchanges I
		retval = this.vaidateExchangesId(dao);
		if(retval){
			//(2) Validity Toldsted (tullkontor)
			if(dao.getThcats()!=null && !"".equals(dao.getThcats())){
				retval = this.vaidateTullkontorId(dao.getThcats(), " Avgångstullkontor är ogiltigt ");
			}
			if(retval){
				if(dao.getThtsb()!=null && !"".equals(dao.getThtsb())){
					retval = this.vaidateTullkontorId(dao.getThtsb	(), " 53. Bestämmelsetullkontor är ogiltigt ");
				}
			}
			if(retval){
				if(dao.getThtsd1()!=null && !"".equals(dao.getThtsd1())){
					retval = this.vaidateTullkontorId(dao.getThtsd1(), " 51. .Planerade transittullkontor (1) är ogiltigt ");
				}
			}
			if(retval){
				if(dao.getThtsd2()!=null && !"".equals(dao.getThtsd2())){
					retval = this.vaidateTullkontorId(dao.getThtsd2(), " 51. .Planerade transittullkontor (2) är ogiltigt ");
				}
			}
			if(retval){
				if(dao.getThtsd3()!=null && !"".equals(dao.getThtsd3())){
					retval = this.vaidateTullkontorId(dao.getThtsd3(), " 51. Planerade transittullkontor (3) är ogiltig ");
				}
			}
			if(retval){
				if(dao.getThtsd4()!=null && !"".equals(dao.getThtsd4())){
					retval = this.vaidateTullkontorId(dao.getThtsd4(), " 51. Planerade transittullkontor (4) är ogiltig");
				}
			}
			if(retval){
				if(dao.getThtsd5()!=null && !"".equals(dao.getThtsd5())){
					retval = this.vaidateTullkontorId(dao.getThtsd5(), " 51. Planerade transittullkontor (5) är ogiltig ");
				}
			}
			if(retval){
				if(dao.getThtsd6()!=null && !"".equals(dao.getThtsd6())){
					retval = this.vaidateTullkontorId(dao.getThtsd6(), " 51. Planerade transittullkontor (6) är ogiltig ");
				}
			}
			if(retval){
				if(dao.getThtsd7()!=null && !"".equals(dao.getThtsd7())){
					retval = this.vaidateTullkontorId(dao.getThtsd7(), " 51. Planerade transittullkontor (7) är ogiltig ");
				}
			}
			if(retval){
				if(dao.getThtsd8()!=null && !"".equals(dao.getThtsd8())){
					retval = this.vaidateTullkontorId(dao.getThtsd8(), " 51. Planerade transittullkontor (8) är ogiltig ");
				}
			}
			
		}
		
		//TODO ... more validations here
		
		return retval;
	}

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDelete(SvxstdDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getThavd()!=null && !"".equals(dao.getThavd())) ){	
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param dao
	 */
	public void adjustNumericFields(SvxstdDao dao){
		String ZERO = "0";
		//TRUSTD
		//Decimals
		
		if(dao.getThvkb()!=null && !"".equals(dao.getThvkb())){
			String tmp = dao.getThvkb().replace(",", ".");
			dao.setThvkb(tmp);
		}else{
			dao.setThvkb(ZERO);
		}
		
		//Integers
		if(dao.getThtdn()==null || "".equals(dao.getThtdn())){
			dao.setThtdn(ZERO);
		}
		if(dao.getThkna()==null || "".equals(dao.getThkna())){
			dao.setThkna(ZERO);
		}
		if(dao.getThkns()==null || "".equals(dao.getThkns())){
			dao.setThkns(ZERO);
		}
		if(dao.getThknk()==null || "".equals(dao.getThknk())){
			dao.setThknk(ZERO);
		}
		if(dao.getThtrm()==null || "".equals(dao.getThtrm())){
			dao.setThtrm(ZERO);
		}
		if(dao.getThgbl()==null || "".equals(dao.getThgbl())){
			dao.setThgbl(ZERO);
		}
		if(dao.getThdant()==null || "".equals(dao.getThdant())){
			dao.setThdant(ZERO);
		}
		if(dao.getThgkd()==null || "".equals(dao.getThgkd())){
			dao.setThgkd(ZERO);
		}
		if(dao.getThgbgi()==null || "".equals(dao.getThgbgi())){
			dao.setThgbgi(ZERO);
		}
		if(dao.getThlstl()==null || "".equals(dao.getThlstl())){
			dao.setThlstl(ZERO);
		}
		if(dao.getThvpos()==null || "".equals(dao.getThvpos())){
			dao.setThvpos(ZERO);
		}
		if(dao.getThntk()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		if(dao.getThomd()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		if(dao.getThtet()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		
		if(dao.getThntk()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		
		
		//Dates
		if(dao.getThdt()==null || "".equals(dao.getThdt())){
			dao.setThdt(ZERO);
		}
		if(dao.getThddt()==null || "".equals(dao.getThddt())){
			dao.setThddt(ZERO);
		}
		if(dao.getThddtk()==null || "".equals(dao.getThddtk())){
			dao.setThddtk(ZERO);
		}
		if(dao.getThtrdt()==null || "".equals(dao.getThtrdt())){
			dao.setThtrdt(ZERO);
		}
		
		
		
		
	}
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 */
	public boolean vaidateExchangesId(SvxstdDao dao){
		boolean retval = true;
	
		List s0004List = this.ediiDaoServices.findById(dao.getS0004(), this.validatorStackTrace);
		List s0010List = this.ediiDaoServices.findById(dao.getS0010(), this.validatorStackTrace);
		
		if( (s0004List!=null && s0004List.size()==1) && (s0010List!=null && s0010List.size()==1)){
			//OK
		}else{
			//populate errorStackTrace
			if(s0004List!=null && s0004List.size()==1){ 
				//OK 
			}else{
				this.validatorStackTrace.append(" UtväxlingsId Avd. är ogiltig /" );
			}
			
			if(s0010List!=null && s0010List.size()==1){
				//OK
			}else{
				this.validatorStackTrace.append(" UtväxlingsId Tullverket är ogiltig " );
				
			}
			retval = false;
			
		}
		
		return retval;
	}
	/**
	 * 
	 * @param dao
	 * @return
	 */
	public boolean vaidateTullkontorId(String value, String errorMsg){
		boolean retval = true;
		
		String UNIQUE_CODE_TULLKONTOR = "106";
		List list = this.svxkodfDaoServices.findById(UNIQUE_CODE_TULLKONTOR, value, this.validatorStackTrace);
		
		if( list!=null && list.size()==1 ){
			//OK
			//logger.info("AAAA");
		}else{
			//logger.info("AAA");
			this.validatorStackTrace.append(errorMsg);
			retval = false;
		}	
		
		return retval;
	}
	

	/**
	 * Child record Säkerhet
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * 
	 * @return
	 */
	
	public boolean isValidInput(SvxstdfvDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getThavd()!=null && !"".equals(dao.getThavd())) ){	
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	
	public boolean isValidInputForDelete(SvxstdfvDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getThavd()!=null && !"".equals(dao.getThavd())) ){	
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param dao
	 */
	public void adjustNumericFields(SvxstdfvDao dao){
		String ZERO = "0";
		//SVXSTDFV
		
		//Integers
		if(dao.getThsik()==null || "".equals(dao.getThsik())){
			dao.setThsik(ZERO);
		}
		
		if(dao.getThtma()==null || "".equals(dao.getThtma())){
			dao.setThtma(ZERO);
		}
		if(dao.getThknss()==null || "".equals(dao.getThknss())){
			dao.setThknss(ZERO);
		}
		if(dao.getThknks()==null || "".equals(dao.getThknks())){
			dao.setThknks(ZERO);
		}
		if(dao.getThknt()==null || "".equals(dao.getThknt())){
			dao.setThknt(ZERO);
		}
		
		//Dates
		if(dao.getThdta()==null || "".equals(dao.getThdta())){
			dao.setThdta(ZERO);
		}
	}
	
}
