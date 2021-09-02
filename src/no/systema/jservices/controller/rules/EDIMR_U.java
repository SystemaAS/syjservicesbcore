package no.systema.jservices.controller.rules;


import org.apache.commons.lang3.StringUtils;
import no.systema.jservices.model.dao.entities.EdimDao;

/**
 * 
 * @author oscardelatorre
 * @date Nov 2020
 */
public class EDIMR_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputInsert(EdimDao dao, String user, String mode){
		boolean retval = true;
		if( (StringUtils.isNotEmpty(user)) && StringUtils.isNotEmpty(mode) ){
			//check dao
			
			if( StringUtils.isNotEmpty(dao.getMtdn()) && StringUtils.isNotEmpty(dao.getMavd()) &&
				StringUtils.isNotEmpty(dao.getM0062()) && StringUtils.isNotEmpty(dao.getM1001()) &&
				StringUtils.isNotEmpty(dao.getM1225()) && StringUtils.isNotEmpty(dao.getM1004()) &&
				StringUtils.isNotEmpty(dao.getMsr()) && StringUtils.isNotEmpty(dao.getM1n07()) &&
				StringUtils.isNotEmpty(dao.getM0065()) && StringUtils.isNotEmpty(dao.getMdt()) &&	StringUtils.isNotEmpty(dao.getMtm()) ) {		
				//OK	
				
			}else{
				retval = false;
			}
			
		}
		return retval;
	}
	public boolean isValidInputUpdate(EdimDao dao, String user, String mode){
		boolean retval = true;
		if( (StringUtils.isNotEmpty(user)) && StringUtils.isNotEmpty(mode) ){
			//check dao
			
			if( StringUtils.isNotEmpty(dao.getMtdn()) && StringUtils.isNotEmpty(dao.getMavd()) &&
				StringUtils.isNotEmpty(dao.getM0062()) && StringUtils.isNotEmpty(dao.getM1001()) &&
				StringUtils.isNotEmpty(dao.getM1225()) && StringUtils.isNotEmpty(dao.getM1004()) &&
				StringUtils.isNotEmpty(dao.getMsr()) && StringUtils.isNotEmpty(dao.getM1n07()) &&
				StringUtils.isNotEmpty(dao.getM0065()) && StringUtils.isNotEmpty(dao.getMdt()) &&	
				StringUtils.isNotEmpty(dao.getMtm()) &&  
				//previously empty fields when insert
				StringUtils.isNotEmpty(dao.getMst())
				
					) {		
				//OK	
				
			}else{
				retval = false;
			}
			
		}
		return retval;
	}
	
	public boolean isValidInput(EdimDao dao, String user, String mode){
		boolean retval = true;
		if( (StringUtils.isNotEmpty(user)) && StringUtils.isNotEmpty(mode) ){
			//OK
		}else{
			retval = false;
		}
			
		
		return retval;
	}
}
