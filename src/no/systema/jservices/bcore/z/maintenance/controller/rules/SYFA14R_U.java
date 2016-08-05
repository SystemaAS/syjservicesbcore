package no.systema.jservices.bcore.z.maintenance.controller.rules;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 2, 2016
 */
public class SYFA14R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtaDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKoaavd()!=null && !"".equals(dao.getKoaavd())) &&
				(dao.getKoanvn()!=null && !"".equals(dao.getKoanvn())) && 
				(dao.getKoaknr()!=null && !"".equals(dao.getKoaknr())) &&
				(dao.getKoafir()!=null && !"".equals(dao.getKoafir())) ){
					
				
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
	public boolean isValidInputForDelete(KodtaDao dao, String user, String mode){
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
	public void updateNumericFieldsIfNull(KodtaDao dao){
		String ZERO = "0";
		if(dao.getKoabaer()==null || "".equals(dao.getKoabaer())){
			dao.setKoabaer(ZERO);
		}
		if(dao.getKoakon()==null || "".equals(dao.getKoakon())){
			dao.setKoakon(ZERO);
		}
		if(dao.getKoaiat()==null || "".equals(dao.getKoaiat())){
			dao.setKoaiat(ZERO);
		}
		if(dao.getKoaia2()==null || "".equals(dao.getKoaia2())){
			dao.setKoaia2(ZERO);
		}
	}
}
