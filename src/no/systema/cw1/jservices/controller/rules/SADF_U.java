package no.systema.cw1.jservices.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.cw1.jservices.dao.SadfDao;
/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 */
public class SADF_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SadfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getSfavd()) && StringUtils.isNotEmpty(dao.getSfopdn())){
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
