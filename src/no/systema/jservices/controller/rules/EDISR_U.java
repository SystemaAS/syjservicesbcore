package no.systema.jservices.controller.rules;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import no.systema.jservices.model.dao.entities.EdisDao;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2021
 */
public class EDISR_U {
	private static Logger logger = Logger.getLogger(EDISR_U.class.getName());

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputInsert(EdisDao dao, String user, String mode){
		
		boolean retval = true;
		if( (StringUtils.isNotEmpty(user)) && StringUtils.isNotEmpty(mode) ){
			
			if( StringUtils.isNotEmpty(dao.getS0004()) && StringUtils.isNotEmpty(dao.getS0010()) && 
				StringUtils.isNotEmpty(dao.getS0020()) && StringUtils.isNotEmpty(dao.getS0026()) && 
				StringUtils.isNotEmpty(dao.getS0036()) && 
				StringUtils.isNotEmpty(dao.getSsn()) && StringUtils.isNotEmpty(dao.getSsr()) && 
				StringUtils.isNotEmpty(dao.getSst()) && StringUtils.isNotEmpty(dao.getSdt()) && 
				StringUtils.isNotEmpty(dao.getStm()) && StringUtils.isNotEmpty(dao.getSlib()) && 
				StringUtils.isNotEmpty(dao.getSfile()) && StringUtils.isNotEmpty(dao.getSmbr()) && 
				StringUtils.isNotEmpty(dao.getSmbrs())  ){
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
