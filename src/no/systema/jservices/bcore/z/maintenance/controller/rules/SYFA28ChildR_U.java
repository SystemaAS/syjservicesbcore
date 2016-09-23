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
			if( (dao.getKopavd()!=null && !"".equals(dao.getKopavd())) && (dao.getKoplnr()!=null && !"".equals(dao.getKoplnr())) ){
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
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDelete(KodtpUtskrsDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKopavd()!=null && !"".equals(dao.getKopavd())) && (dao.getKoplnr()!=null && !"".equals(dao.getKoplnr())) ){
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
		}else{
			dao.setKopcpl(dao.getKopcpl().trim());
		}
		
		if(dao.getKopcpi()==null || "".equals(dao.getKopcpi())){
			dao.setKopcpi(ZERO);
		}else{
			dao.setKopcpi(dao.getKopcpi().trim());
		}
		
		if(dao.getKoplpp()==null || "".equals(dao.getKoplpp())){
			dao.setKoplpp(ZERO);
		}else{
			dao.setKoplpp(dao.getKoplpp().trim());
		}
		
		if(dao.getKoplpi()==null || "".equals(dao.getKoplpi())){
			dao.setKoplpi(ZERO);
		}else{
			dao.setKoplpi(dao.getKoplpi().trim());
		}

		if(dao.getUtpcpl()==null || "".equals(dao.getUtpcpl())){
			dao.setUtpcpl(ZERO);
		}else{
			dao.setUtpcpl(dao.getUtpcpl().trim());
		}
		
		if(dao.getUtpcpi()==null || "".equals(dao.getUtpcpi())){
			dao.setUtpcpi(ZERO);
		}else{
			dao.setUtpcpi(dao.getUtpcpi().trim());
		}
		
		if(dao.getUtplpp()==null || "".equals(dao.getUtplpp())){
			dao.setUtplpp(ZERO);
		}else{
			dao.setUtplpp(dao.getUtplpp().trim());
		}
		
		if(dao.getUtplpi()==null || "".equals(dao.getUtplpi())){
			dao.setUtplpi(ZERO);
		}else{
			dao.setUtplpi(dao.getUtplpi().trim());
		}
	}
	
}
