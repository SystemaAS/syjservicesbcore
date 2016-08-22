package no.systema.jservices.bcore.z.maintenance.controller.rules;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaTellDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 19, 2016
 */
public class SYFA26R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtaTellDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTeavd()!=null && !"".equals(dao.getTeavd())) &&
				(dao.getTeopdn()!=null && !"".equals(dao.getTeopdn())) &&	
				(dao.getTeturn()!=null && !"".equals(dao.getTeturn())) ){	
				
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
	public boolean isValidInputForDelete(KodtaTellDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( dao.getTeavd()!=null && !"".equals(dao.getTeavd()) ){
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
	public void updateNumericFieldsIfNull(KodtaTellDao dao){
		String ZERO = "0";
		//TELL
		if(dao.getTeopdn()==null || "".equals(dao.getTeopdn())){
			dao.setTeopdn(ZERO);
		}
		if(dao.getTeturn()==null || "".equals(dao.getTeturn())){
			dao.setTeturn(ZERO);
		}
		if(dao.getTetmin()==null || "".equals(dao.getTetmin())){
			dao.setTetmin(ZERO);
		}
		
	}
	
}
