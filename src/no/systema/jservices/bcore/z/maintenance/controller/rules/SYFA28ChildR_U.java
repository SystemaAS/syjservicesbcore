package no.systema.jservices.bcore.z.maintenance.controller.rules;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtpUtskrsDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 5, 2016
 */
public class SYFA28ChildR_U {

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
			if( (dao.getKopavd()!=null && !"".equals(dao.getKopavd())) ){
					
				
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
	public boolean isValidInputForDelete(KodtpUtskrsDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( dao.getKopavd()!=null && !"".equals(dao.getKopavd()) ){
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
	public void updateNumericFieldsIfNull(KodtpUtskrsDao dao){
		String ZERO = "0";
		
		if(dao.getKopcpl()==null || "".equals(dao.getKopcpl())){
			dao.setKopcpl(ZERO);
		}
		if(dao.getKopcpi()==null || "".equals(dao.getKopcpi())){
			dao.setKopcpi(ZERO);
		}
		if(dao.getKoplpp()==null || "".equals(dao.getKoplpp())){
			dao.setKoplpp(ZERO);
		}
		if(dao.getKoplpi()==null || "".equals(dao.getKoplpi())){
			dao.setKoplpi(ZERO);
		}
		//
		if(dao.getUtpcpl()==null || "".equals(dao.getUtpcpl())){
			dao.setUtpcpl(ZERO);
		}
		if(dao.getUtpcpi()==null || "".equals(dao.getUtpcpi())){
			dao.setUtpcpi(ZERO);
		}
		if(dao.getUtplpp()==null || "".equals(dao.getUtplpp())){
			dao.setUtplpp(ZERO);
		}
		if(dao.getUtplpi()==null || "".equals(dao.getUtplpi())){
			dao.setUtplpi(ZERO);
		}
	}
}
