package no.systema.jservices.bcore.z.maintenance.controller.rules;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtvKodtwDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 5, 2016
 */
public class SYFA28R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtvKodtwDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKovavd()!=null && !"".equals(dao.getKovavd())) ){
					
				
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
	public boolean isValidInputForDelete(KodtvKodtwDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( dao.getKovavd()!=null && !"".equals(dao.getKovavd()) ){
				//OK
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
	 */
	public void updateNumericFieldsIfNull(KodtvKodtwDao dao){
		String ZERO = "0";
		/*
		if(dao.getKowmm()==null || "".equals(dao.getKowmm())){
			dao.setKowmm(ZERO);
		}*/
	}
}
