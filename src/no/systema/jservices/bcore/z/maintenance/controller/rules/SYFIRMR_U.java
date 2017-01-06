package no.systema.jservices.bcore.z.maintenance.controller.rules;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.FirmDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 4, 2016
 */
public class SYFIRMR_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(FirmDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			
			if( (dao.getFifirm()!=null && !"".equals(dao.getFifirm())) &&
				(dao.getFift()!=null && !"".equals(dao.getFift()))  ){
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
	public boolean isValidInputForDelete(FirmDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			/*
			if( dao.getFifirm()!=null && !"".equals(dao.getFifirm()) ){
				//OK
			}else{
				retval = false;
			}*/			  
		}else{
			retval = false;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param dao
	 */
	public void updateNumericFieldsIfNull(FirmDao dao){
		String ZERO = "0";
		
		//-----------
		//FIRM-table
		//-----------
		if(dao.getFiups()==null || "".equals(dao.getFiups())){
			dao.setFiups(ZERO);
		}else{
			dao.setFiups(dao.getFiups().replace(",", "."));
		}
		if(dao.getFiupm()==null || "".equals(dao.getFiupm())){
			dao.setFiupm(ZERO);
		}
		if(dao.getFikdt()==null || "".equals(dao.getFikdt())){
			dao.setFikdt(ZERO);
		}
		if(dao.getFiatk()==null || "".equals(dao.getFiatk())){
			dao.setFiatk(ZERO);
		}
		
		if(dao.getFimvas()==null || "".equals(dao.getFimvas())){
			dao.setFimvas(ZERO);
		}else{
			dao.setFimvas(dao.getFimvas().replace(",", "."));
		}
		
		if(dao.getFitax()==null || "".equals(dao.getFitax())){
			dao.setFitax(ZERO);
		}else{
			dao.setFitax(dao.getFitax().replace(",", "."));
			Double dbl = Double.valueOf(dao.getFitax());
			dbl = dbl/100;
			dao.setFitax(String.valueOf(dbl));
			
		}
		
		if(dao.getFitax2()==null || "".equals(dao.getFitax2())){
			dao.setFitax2(ZERO);
		}else{
			dao.setFitax2(dao.getFitax2().replace(",", "."));
			Double dbl = Double.valueOf(dao.getFitax2());
			dbl = dbl/100;
			dao.setFitax2(String.valueOf(dbl));
		}
		
		if(dao.getFitaxd()==null || "".equals(dao.getFitaxd())){
			dao.setFitaxd(ZERO);
		}
		if(dao.getFidtfm()==null || "".equals(dao.getFidtfm())){
			dao.setFidtfm(ZERO);
		}
		if(dao.getFideci()==null || "".equals(dao.getFideci())){
			dao.setFideci(ZERO);
		}
		
		//------------
		//FIRFB-table
		//------------
		if(dao.getFirecn()==null || "".equals(dao.getFirecn())){
			dao.setFirecn(ZERO);
		}
		if(dao.getFirecm()==null || "".equals(dao.getFirecm())){
			dao.setFirecm(ZERO);
		}
		if(dao.getFisnla()==null || "".equals(dao.getFisnla())){
			dao.setFisnla(ZERO);
		}
		if(dao.getFisnle()==null || "".equals(dao.getFisnle())){
			dao.setFisnle(ZERO);
		}
		if(dao.getFiidla()==null || "".equals(dao.getFiidla())){
			dao.setFiidla(ZERO);
		}
		if(dao.getFiidle()==null || "".equals(dao.getFiidle())){
			dao.setFiidle(ZERO);
		}
		if(dao.getFiidnr()==null || "".equals(dao.getFiidnr())){
			dao.setFiidnr(ZERO);
		}
		if(dao.getFiidmx()==null || "".equals(dao.getFiidmx())){
			dao.setFiidmx(ZERO);
		}
		//----------------
		//FIRMKOS - table
		//----------------
		if(dao.getInterr()==null || "".equals(dao.getInterr())){
			dao.setInterr(ZERO);
		}
		
	}
	
}
