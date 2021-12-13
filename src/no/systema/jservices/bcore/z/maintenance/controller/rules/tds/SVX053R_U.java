package no.systema.jservices.bcore.z.maintenance.controller.rules.tds;

import java.util.*;

import org.apache.logging.log4j.*;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.tds.SvnstdDao;
import no.systema.jservices.model.dao.services.EdiiDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.SvxkodfDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date jun 26, 2017
 */
public class SVX053R_U {
	private static Logger logger = LogManager.getLogger(SVX053R_U.class.getName());
	private EdiiDaoServices ediiDaoServices;
	private SvxkodfDaoServices svxkodfDaoServices;
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	/**
	 * 
	 * @param ediiDaoServices
	 */
	public SVX053R_U(EdiiDaoServices ediiDaoServices, SvxkodfDaoServices svxkodfDaoServices){
		this.ediiDaoServices = ediiDaoServices;
		//this service is common for both NCTS. SKAT - NCTS Eksport and NCTS Import
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
	public boolean isValidInput(SvnstdDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTiavd()!=null && !"".equals(dao.getTiavd())) ){	
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
			if(dao.getTitsb()!=null && !"".equals(dao.getTitsb())){
				retval = this.vaidateTullkontorId(dao);
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
	public boolean isValidInputForDelete(SvnstdDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTiavd()!=null && !"".equals(dao.getTiavd())) ){	
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
	public void adjustNumericFields(SvnstdDao dao){
		String ZERO = "0";
		//Decimals
		if(dao.getTivkb()!=null && !"".equals(dao.getTivkb())){
			String tmp = dao.getTivkb().replace(",", ".");
			dao.setTivkb(tmp);
		}else{	
			dao.setTivkb(ZERO);
		}
		
		
		//Integers
		if(dao.getTidt()==null || "".equals(dao.getTidt())){
			dao.setTidt(ZERO);
		}
		if(dao.getTikn()==null || "".equals(dao.getTikn())){
			dao.setTikn(ZERO);
		}
		if(dao.getTikna()==null || "".equals(dao.getTikna())){
			dao.setTikna(ZERO);
		}
		if(dao.getTitdn()==null || "".equals(dao.getTitdn())){
			dao.setTitdn(ZERO);
		}
		if(dao.getTikns()==null || "".equals(dao.getTikns())){
			dao.setTikns(ZERO);
		}
		if(dao.getTiknk()==null || "".equals(dao.getTiknk())){
			dao.setTiknk(ZERO);
		}
		if(dao.getTitrdt()==null || "".equals(dao.getTitrdt())){
			dao.setTitrdt(ZERO);
		}
		if(dao.getTilstl()==null || "".equals(dao.getTilstl())){
			dao.setTilstl(ZERO);
		}
		if(dao.getTivpos()==null || "".equals(dao.getTivpos())){
			dao.setTivpos(ZERO);
		}
		if(dao.getTintk()==null || "".equals(dao.getTintk())){
			dao.setTintk(ZERO);
		}
		
		if(dao.getTidant()==null || "".equals(dao.getTidant())){
			dao.setTidant(ZERO);
		}
		if(dao.getTibyte()==null || "".equals(dao.getTibyte())){
			dao.setTibyte(ZERO);
		}
		
	}
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 */
	public boolean vaidateExchangesId(SvnstdDao dao){
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
				this.validatorStackTrace.append(" UtvekslingsId Avd. er ugyldig /" );
			}
			
			if(s0010List!=null && s0010List.size()==1){
				//OK
			}else{
				this.validatorStackTrace.append(" UtvekslingsId Tollvesenet er ugyldig " );
				
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
	public boolean vaidateTullkontorId(SvnstdDao dao){
		boolean retval = true;
		/* TODO OSCAR ...
		//String UNIQUE_CODE_TULLKONTOR = "106";
		
		List list = this.dknstdDaoServices.findById(UNIQUE_CODE_TULLKONTOR, dao.getTitsb(), this.validatorStackTrace);
		
		if( list!=null && list.size()==1 ){
			//OK
			//logger.info("AAAA");
		}else{
			//logger.info("AAA");
			this.validatorStackTrace.append(" Freml.tollsted er ugyldig " );
			retval = false;
		}	
		*/
		return retval;
	}
}
