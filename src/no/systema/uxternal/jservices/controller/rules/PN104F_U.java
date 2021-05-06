package no.systema.uxternal.jservices.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.uxternal.jservices.dao.Pn104fDao;
/**
 * 
 * @author oscardelatorre
 * @date Abr, 2021
 */
public class PN104F_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(Pn104fDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getPnkn()) && StringUtils.isNotEmpty(dao.getPnefn()) && StringUtils.isNotEmpty(dao.getPnon())){
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
