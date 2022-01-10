package no.systema.jservices.bcore.z.maintenance.controller.rules.tds;

import java.util.*;

import org.slf4j.*;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.tds.SviaDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.tds.SviaDaoServices;

/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2017
 */
public class SVI051R_U {
	private static Logger logger = LoggerFactory.getLogger(SVI051R_U.class.getName());
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SviaDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSvia_syav()!=null && !"".equals(dao.getSvia_syav())) &&
				(dao.getSvia_syop()!=null && !"".equals(dao.getSvia_syop()))	){	
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
	public boolean isValidInputForDelete(SviaDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSvia_syav()!=null && !"".equals(dao.getSvia_syav())) &&
				(dao.getSvia_syop()!=null && !"".equals(dao.getSvia_syop()))	){	
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
	public void adjustNumericFields(SviaDao dao){
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
