package no.systema.jservices.bcore.z.maintenance.controller.rules;

import java.util.List;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtvKodtwDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.Kodtot2DaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Aug 5, 2016
 */
public class SYFA28R_U {
	private Kodtot2DaoServices kodtot2DaoServices;
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtvKodtwDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKovavd()!=null && !"".equals(dao.getKovavd())) ){
				//OK	
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		
		//-----------------------
		//now some logical tests
		//-----------------------
		if(retval){
			//TODO ... more validations here
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
	public boolean isValidInputForDelete(KodtvKodtwDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( dao.getKovavd()!=null && !"".equals(dao.getKovavd()) ){
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
	public void updateNumericFieldsIfNull(KodtvKodtwDao dao){
		String ZERO = "0";
		//KODTV
		if(dao.getKovlkg()==null || "".equals(dao.getKovlkg())){
			dao.setKovlkg(ZERO);
		}
		if(dao.getKovkkg()==null || "".equals(dao.getKovkkg())){
			dao.setKovkkg(ZERO);
		}
		if(dao.getKovk1()==null || "".equals(dao.getKovk1())){
			dao.setKovk1(ZERO);
		}
		if(dao.getKovk2()==null || "".equals(dao.getKovk2())){
			dao.setKovk2(ZERO);
		}
		if(dao.getKovk3()==null || "".equals(dao.getKovk3())){
			dao.setKovk3(ZERO);
		}
		if(dao.getKovk4()==null || "".equals(dao.getKovk4())){
			dao.setKovk4(ZERO);
		}
		if(dao.getKovk5()==null || "".equals(dao.getKovk5())){
			dao.setKovk5(ZERO);
		}
		if(dao.getKovk6()==null || "".equals(dao.getKovk6())){
			dao.setKovk6(ZERO);
		}
		if(dao.getKovk7()==null || "".equals(dao.getKovk7())){
			dao.setKovk7(ZERO);
		}
		if(dao.getKovk8()==null || "".equals(dao.getKovk8())){
			dao.setKovk8(ZERO);
		}
		if(dao.getKovk9()==null || "".equals(dao.getKovk9())){
			dao.setKovk9(ZERO);
		}
		if(dao.getKovk10()==null || "".equals(dao.getKovk10())){
			dao.setKovk10(ZERO);
		}
		if(dao.getKovk11()==null || "".equals(dao.getKovk11())){
			dao.setKovk11(ZERO);
		}
		if(dao.getKovomr()==null || "".equals(dao.getKovomr())){
			dao.setKovomr(ZERO);
		}
		
		if(dao.getAvutpr()==null || "".equals(dao.getAvutpr())){
			dao.setAvutpr(ZERO);
		}else{
			dao.setAvutpr(this.updateDecimalFields(dao.getAvutpr()));
		}
		
		if(dao.getAvutmi()==null || "".equals(dao.getAvutmi())){
			dao.setAvutmi(ZERO);
		}
		//KODTW
		if(dao.getKowmm()==null || "".equals(dao.getKowmm())){
			dao.setKowmm(ZERO);
		}
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String updateDecimalFields(String value){
		String retval = value;
		if(value!=null && !"".equals(value)){
			String tmp = value.replace(",", ".");
			retval = tmp;
		}
		return retval;
	}
	
}
