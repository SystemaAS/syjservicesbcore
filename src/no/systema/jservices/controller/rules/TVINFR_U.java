package no.systema.jservices.controller.rules;


import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.*;

import no.systema.jservices.model.dao.entities.TvinfDao;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2021
 */
public class TVINFR_U {
	private static Logger logger = LogManager.getLogger(TVINFR_U.class.getName());

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputInsert(TvinfDao dao, String user, String mode){
		
		boolean retval = true;
		if( (StringUtils.isNotEmpty(user)) && StringUtils.isNotEmpty(mode) ){
			
			if( StringUtils.isNotEmpty(dao.getFmn()) && StringUtils.isNotEmpty(dao.getFln()) && 
				StringUtils.isNotEmpty(dao.getF0078a()) && StringUtils.isNotEmpty(dao.getF0078b()) ){
				//OK	
				
			}else{
				logger.fatal("Ruler lord INVALID:" + dao.toString());
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
