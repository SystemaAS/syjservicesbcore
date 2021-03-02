package no.systema.cw1.jservices.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.cw1.jservices.dao.SadwDao;
/**
 * 
 * @author oscardelatorre
 * @date Mar, 2021
 */
public class SADW_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SadwDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getSwavd()) && StringUtils.isNotEmpty(dao.getSwtdn()) && StringUtils.isNotEmpty(dao.getSwli()) ){
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
