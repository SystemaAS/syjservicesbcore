package no.systema.jservices.bcore.z.maintenance.controller.rules;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaHodeDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 17, 2016
 */
public class SYFA63R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtaHodeDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKoaavd()!=null && !"".equals(dao.getKoaavd())) ){
					
				
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
	public boolean isValidInputForDelete(KodtaHodeDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( dao.getKoaavd()!=null && !"".equals(dao.getKoaavd()) ){
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
	public void updateNumericFieldsIfNull(KodtaHodeDao dao){
		String ZERO = "0";
		//HODEL1 N/A 
		/*if(dao.getKovlkg()==null || "".equals(dao.getKovlkg())){
			dao.setKovlkg(ZERO);
		}*/
	}
	
}
