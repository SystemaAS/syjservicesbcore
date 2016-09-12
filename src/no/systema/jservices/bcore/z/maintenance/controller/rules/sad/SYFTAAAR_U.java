package no.systema.jservices.bcore.z.maintenance.controller.rules.sad;

import java.util.*;

import org.apache.log4j.Logger;

import no.systema.jservices.bcore.z.maintenance.controller.sad.BcoreMaintResponseOutputterControllerSadImport_AVD_STANDI;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.StandiDao;
import no.systema.jservices.model.dao.services.EdiiDaoServices;
import no.systema.jservices.model.dao.entities.EdiiDao;


/**
 * 
 * @author oscardelatorre
 * @date Aug 23, 2016
 */
public class SYFTAAAR_U {
	private static Logger logger = Logger.getLogger(SYFTAAAR_U.class.getName());
	private EdiiDaoServices ediiDaoServices;
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	/**
	 * 
	 * @param ediiDaoServices
	 */
	public SYFTAAAR_U(EdiiDaoServices ediiDaoServices){
		this.ediiDaoServices = ediiDaoServices;
	}

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @param errorStackTrace
	 * @return
	 */
	public boolean isValidInput(StandiDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSiavd()!=null && !"".equals(dao.getSiavd())) ){	
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
		//(1) Validity of Exchanges ID
		if("J".equals(dao.getSitolk())){
			retval = this.vaidateExchangesId(dao);
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
	public boolean isValidInputForDelete(StandiDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSiavd()!=null && !"".equals(dao.getSiavd())) ){	
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
	public void updateNumericFieldsIfNull(StandiDao dao){
		String ZERO = "0";
		//STANDI
		/*
		if(dao.getTeopdn()==null || "".equals(dao.getTeopdn())){
			dao.setTeopdn(ZERO);
		}
		if(dao.getTeturn()==null || "".equals(dao.getTeturn())){
			dao.setTeturn(ZERO);
		}
		if(dao.getTetmin()==null || "".equals(dao.getTetmin())){
			dao.setTetmin(ZERO);
		}*/
		
	}
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 */
	public boolean vaidateExchangesId(StandiDao dao){
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
				this.validatorStackTrace.append(" UtvekslingsId Avd. er feil /" );
			}
			
			if(s0010List!=null && s0010List.size()==1){
				//OK
			}else{
				this.validatorStackTrace.append(" UtvekslingsId Tollvesenet er feil " );
				
			}
			retval = false;
			
		}
		
		return retval;
	}
}
