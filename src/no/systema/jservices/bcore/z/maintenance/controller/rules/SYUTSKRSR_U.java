package no.systema.jservices.bcore.z.maintenance.controller.rules;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtpUtskrsDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 12, 2016
 */
public class SYUTSKRSR_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtpUtskrsDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			/*TDO
			if( (dao.getKoaavd()!=null && !"".equals(dao.getKoaavd())) &&
				(dao.getKoanvn()!=null && !"".equals(dao.getKoanvn())) && 
				(dao.getKoaknr()!=null && !"".equals(dao.getKoaknr())) &&
				(dao.getKoafir()!=null && !"".equals(dao.getKoafir())) ){
					
				
			}else{
				retval = false;
			}
			*/
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
	public boolean isValidInputForDelete(KodtpUtskrsDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			/*
			if( dao.getKoaavd()!=null && !"".equals(dao.getKoaavd()) ){
				//OK
			}else{
				retval = false;
			}*/			  
		}else{
			retval = false;
		}
		return retval;
	}
	
}
