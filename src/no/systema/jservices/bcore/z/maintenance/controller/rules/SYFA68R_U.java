package no.systema.jservices.bcore.z.maintenance.controller.rules;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaHodeDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaKodthDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 19, 2016
 */
public class SYFA68R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtaKodthDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKohavd()!=null && !"".equals(dao.getKohavd())) ){
					
				
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
	public boolean isValidInputForDelete(KodtaKodthDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( dao.getKohavd()!=null && !"".equals(dao.getKohavd()) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	public void updateNumericFieldsIfNull(KodtaKodthDao dao){
		String ZERO = "0";
		//KODTH N/A 
		
	}
	
}
