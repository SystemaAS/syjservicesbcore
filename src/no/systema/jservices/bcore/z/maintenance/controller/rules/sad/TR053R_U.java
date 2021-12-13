package no.systema.jservices.bcore.z.maintenance.controller.rules.sad;

import java.util.*;

import org.apache.logging.log4j.*;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.TristdDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.TrkodfDaoServices;
import no.systema.jservices.model.dao.services.EdiiDaoServices;



/**
 * 
 * @author oscardelatorre
 * @date Sep 16, 2016
 */
public class TR053R_U {
	private static Logger logger = LogManager.getLogger(TR053R_U.class.getName());
	private EdiiDaoServices ediiDaoServices;
	private TrkodfDaoServices trkodfDaoServices;
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	/**
	 * 
	 * @param ediiDaoServices
	 */
	public TR053R_U(EdiiDaoServices ediiDaoServices, TrkodfDaoServices trkodfDaoServices){
		this.ediiDaoServices = ediiDaoServices;
		this.trkodfDaoServices = trkodfDaoServices;
	}

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @param errorStackTrace
	 * @return
	 */
	public boolean isValidInput(TristdDao dao, String user, String mode ){
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
	public boolean isValidInputForDelete(TristdDao dao, String user, String mode){
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
	public void adjustNumericFields(TristdDao dao){
		String ZERO = "0";
		//STANDE
		//Decimals
		/*N/A
		if(dao.getSebel1()!=null && !"".equals(dao.getSebel1())){
			String tmp = dao.getSebel1().replace(",", ".");
			dao.setSebel1(tmp);
		}else{
			dao.setSebel1(ZERO);
		}
		*/
		
		//Integers
		if(dao.getTidt()==null || "".equals(dao.getTidt())){
			dao.setTidt(ZERO);
		}
		if(dao.getTikn()==null || "".equals(dao.getTikn())){
			dao.setTikn(ZERO);
		}
		if(dao.getTitdn()==null || "".equals(dao.getTitdn())){
			dao.setTitdn(ZERO);
		}
		
		
	}
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 */
	public boolean vaidateExchangesId(TristdDao dao){
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
	public boolean vaidateTullkontorId(TristdDao dao){
		boolean retval = true;
		String UNIQUE_CODE_TULLKONTOR = "106";
		List list = this.trkodfDaoServices.findById(UNIQUE_CODE_TULLKONTOR, dao.getTitsb(), this.validatorStackTrace);
		
		if( list!=null && list.size()==1 ){
			//OK
			//logger.info("AAAA");
		}else{
			//logger.info("AAA");
			this.validatorStackTrace.append(" Freml.tollsted er ugyldig " );
			retval = false;
		}	
		
		return retval;
	}
}
