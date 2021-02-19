package no.systema.cw1.jservices.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.cw1.jservices.dao.SadvDao;
/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 */
public class SADV_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SadvDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getSvavd()) && StringUtils.isNotEmpty(dao.getSvtdn()) && StringUtils.isNotEmpty(dao.getSvli()) ){
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
