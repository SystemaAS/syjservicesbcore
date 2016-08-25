package no.systema.jservices.bcore.z.maintenance.controller.rules.sad;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.StandiDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 23, 2016
 */
public class SYFTAAAR_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(StandiDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSiavd()!=null && !"".equals(dao.getSiavd())) ){	
				
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
	public boolean isValidInputForDelete(StandiDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( dao.getSiavd()!=null && !"".equals(dao.getSiavd()) ){
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
	public void updateNumericFieldsIfNull(StandiDao dao){
		String ZERO = "0";
		//STANDI
		/*
		if(dao.getTeopdn()==null || "".equals(dao.getTeopdn())){
			dao.setTeopdn(ZERO);
		}
		if(dao.getTeturn()==null || "".equals(dao.getTeturn())){
			dao.setTeturn(ZERO);
		}
		if(dao.getTetmin()==null || "".equals(dao.getTetmin())){
			dao.setTetmin(ZERO);
		}*/
		
	}
	
}
