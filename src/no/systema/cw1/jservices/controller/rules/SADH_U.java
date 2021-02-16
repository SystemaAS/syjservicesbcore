package no.systema.cw1.jservices.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.cw1.jservices.dao.SadhDao;
/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 */
public class SADH_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SadhDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getSiavd()) && StringUtils.isNotEmpty(dao.getSitdn()) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	
	
}
