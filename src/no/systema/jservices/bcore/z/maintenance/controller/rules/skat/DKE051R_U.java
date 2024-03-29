package no.systema.jservices.bcore.z.maintenance.controller.rules.skat;

import java.util.*;

import org.slf4j.*;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.skat.DkeaDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.skat.DkeaDaoServices;

/**
 * 
 * @author oscardelatorre
 * @date Apr 26, 2017
 */
public class DKE051R_U {
	private static Logger logger = LoggerFactory.getLogger(DKE051R_U.class.getName());
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DkeaDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getDkea_syav()!=null && !"".equals(dao.getDkea_syav())) &&
				(dao.getDkea_syop()!=null && !"".equals(dao.getDkea_syop()))	){	
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
	public boolean isValidInputForDelete(DkeaDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getDkea_syav()!=null && !"".equals(dao.getDkea_syav())) &&
					(dao.getDkea_syop()!=null && !"".equals(dao.getDkea_syop()))	){	
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
	public void adjustNumericFields(DkeaDao dao){
		String ZERO = "0";
		//Integers
		/* N/A since these both fields are key fields (mandatory)
		if(dao.getDkea_syav()==null || "".equals(dao.getDkea_syav())){
			dao.setDkea_syav(ZERO);
		}
		if(dao.getDkea_syop()==null || "".equals(dao.getDkea_syop())){
			dao.setDkea_syop(ZERO);
		}
		*/
	}
}
