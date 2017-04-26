package no.systema.jservices.bcore.z.maintenance.controller.rules.skat;

import java.util.*;

import org.apache.log4j.Logger;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.skat.DkiaDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.skat.DkiaDaoServices;

/**
 * 
 * @author oscardelatorre
 * @date Apr 26, 2017
 */
public class DKI051R_U {
	private static Logger logger = Logger.getLogger(DKI051R_U.class.getName());
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DkiaDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getDkia_syav()!=null && !"".equals(dao.getDkia_syav())) &&
				(dao.getDkia_syop()!=null && !"".equals(dao.getDkia_syop()))	){	
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
	public boolean isValidInputForDelete(DkiaDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getDkia_syav()!=null && !"".equals(dao.getDkia_syav())) &&
				(dao.getDkia_syop()!=null && !"".equals(dao.getDkia_syop()))	){	
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
	public void adjustNumericFields(DkiaDao dao){
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
