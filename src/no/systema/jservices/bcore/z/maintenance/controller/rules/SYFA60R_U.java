package no.systema.jservices.bcore.z.maintenance.controller.rules;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtsfSyparfDao;
/**
 * 
 * @author oscardelatorre
 * @date Okt 17, 2016
 */
public class SYFA60R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtsfSyparfDao dao, String user, String mode){
		boolean retval = true;
		/*
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
		*/
		return retval;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDelete(KodtsfSyparfDao dao, String user, String mode){
		boolean retval = true;
		/*
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
		*/
		return retval;
		
	}
	
	/**
	 * 
	 * @param dao
	 */
	public void updateNumericFieldsIfNull(KodtsfSyparfDao dao){
		String ZERO = "0";
		//Syparl3
		if(dao.getSyrecn()==null || "".equals(dao.getSyrecn())){
			dao.setSyrecn(ZERO);
		}
		if(dao.getSykunr()==null || "".equals(dao.getSykunr())){
			dao.setSykunr(ZERO);
		}
		if(dao.getSyavd()==null || "".equals(dao.getSyavd())){
			dao.setSyavd(ZERO);
		}
		if(dao.getSysort()==null || "".equals(dao.getSysort())){
			dao.setSysort(ZERO);
		}
		if(dao.getSyvrdn()==null || "".equals(dao.getSyvrdn())){
			dao.setSyvrdn(ZERO);
		}
		
	}
	
}
