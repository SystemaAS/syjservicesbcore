package no.systema.jservices.controller.rules;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;

import no.systema.jservices.controller.JsonResponseOutputterController_EDIM;
import no.systema.jservices.model.dao.entities.EdimDao;
import no.systema.jservices.model.dao.entities.SvihDao;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2021
 */
public class SVIH_U {
	private static Logger logger = LoggerFactory.getLogger(SVIH_U.class.getName());

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputUpdateStatus(SvihDao dao, String user, String mode){
		
		boolean retval = true;
		if( (StringUtils.isNotEmpty(user)) && StringUtils.isNotEmpty(mode) ){
			//check dao
			
			if( StringUtils.isNotEmpty(dao.getSvih_syst()) && StringUtils.isNotEmpty(dao.getSvih_syav()) &&
				StringUtils.isNotEmpty(dao.getSvih_syop()) && StringUtils.isNotEmpty(dao.getSvih_tuid()) &&
				StringUtils.isNotEmpty(dao.getSvih_sysg()) ) {		
				//OK	
				
			}else{
				logger.error("Ruler lord INVALID:" + dao.toString());
				retval = false;
			}
			
		}
		return retval;
	}
	public boolean isValidInputUpdateLight(SvihDao dao, String user, String mode){
		
		boolean retval = true;
		if( (StringUtils.isNotEmpty(user)) && StringUtils.isNotEmpty(mode) ){
			//check dao
			
			if( StringUtils.isNotEmpty(dao.getSvih_syst()) && StringUtils.isNotEmpty(dao.getSvih_syav()) &&
				StringUtils.isNotEmpty(dao.getSvih_syop()) && StringUtils.isNotEmpty(dao.getSvih_tuid()) &&
				StringUtils.isNotEmpty(dao.getSvih_sysg()) ) {		
				//OK	
				
			}else{
				logger.error("Ruler lord INVALID:" + dao.toString());
				retval = false;
			}
			
		}
		return retval;
	}
	
	public boolean isValidInput(String user, String mode){
		boolean retval = true;
		if( (StringUtils.isNotEmpty(user)) && StringUtils.isNotEmpty(mode) ){
			//OK
		}else{
			retval = false;
		}
			
		
		return retval;
	}
}
