package no.systema.jservices.controller.rules;


import no.systema.jservices.model.dao.entities.CusdfDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 4, 2016
 */
public class SYCUNDFR_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(CusdfDao dao, String user, String mode){
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
	public boolean isValidInputForDelete(CusdfDao dao, String user, String mode){
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
