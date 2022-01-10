package no.systema.jservices.bcore.z.maintenance.controller.rules.sad;

import java.util.*;

import org.slf4j.*;

import no.systema.jservices.bcore.z.maintenance.controller.sad.BcoreMaintResponseOutputterControllerSadImport_AVD_STANDI;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.StandiDao;
import no.systema.jservices.model.dao.services.EdiiDaoServices;
import no.systema.jservices.model.dao.entities.EdiiDao;


/**
 * 
 * @author oscardelatorre
 * @date Aug 23, 2016
 */
public class SYFTAAAR_U {
	private static Logger logger = LoggerFactory.getLogger(SYFTAAAR_U.class.getName());
	private EdiiDaoServices ediiDaoServices;
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	/**
	 * 
	 * @param ediiDaoServices
	 */
	public SYFTAAAR_U(EdiiDaoServices ediiDaoServices){
		this.ediiDaoServices = ediiDaoServices;
	}

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @param errorStackTrace
	 * @return
	 */
	public boolean isValidInput(StandiDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSiavd()!=null && !"".equals(dao.getSiavd())) ){	
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
		//(1) Validity of Exchanges ID
		if("J".equals(dao.getSitolk())){
			retval = this.vaidateExchangesId(dao);
		}
		//TODO ... more validations here

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
			if( (dao.getSiavd()!=null && !"".equals(dao.getSiavd())) ){	
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
		//-----------------
		//Decimal amounts
		//-----------------
		if(dao.getSibel1()!=null && !"".equals(dao.getSibel1())){
			String tmp = dao.getSibel1().replace(",", ".");
			dao.setSibel1(tmp);
		}else{
			dao.setSibel1(ZERO);	
		}
		if(dao.getSibel2()!=null && !"".equals(dao.getSibel2())){
			String tmp = dao.getSibel2().replace(",", ".");
			dao.setSibel2(tmp);
		}else{
			dao.setSibel2(ZERO);	
		}
		if(dao.getSibel3()!=null && !"".equals(dao.getSibel3())){
			String tmp = dao.getSibel3().replace(",", ".");
			dao.setSibel3(tmp);
		}else{
			dao.setSibel3(ZERO);	
		}
		if(dao.getSivku()!=null && !"".equals(dao.getSivku())){
			String tmp = dao.getSivku().replace(",", ".");
			dao.setSivku(tmp);
		}else{
			dao.setSivku(ZERO);	
		}
		if(dao.getSias()!=null && !"".equals(dao.getSias())){
			String tmp = dao.getSias().replace(",", ".");
			dao.setSias(tmp);
		}else{
			dao.setSias(ZERO);	
		}
		if(dao.getSibel8()!=null && !"".equals(dao.getSibel8())){
			String tmp = dao.getSibel8().replace(",", ".");
			dao.setSibel8(tmp);
		}else{
			dao.setSibel8(ZERO);	
		}
		if(dao.getSibel9()!=null && !"".equals(dao.getSibel9())){
			String tmp = dao.getSibel9().replace(",", ".");
			dao.setSibel9(tmp);
		}else{
			dao.setSibel9(ZERO);	
		}
		if(dao.getSibelb()!=null && !"".equals(dao.getSibelb())){
			String tmp = dao.getSibelb().replace(",", ".");
			dao.setSibelb(tmp);
		}else{
			dao.setSibelb(ZERO);	
		}
		if(dao.getSibelr()!=null && !"".equals(dao.getSibelr())){
			String tmp = dao.getSibelr().replace(",", ".");
			dao.setSibelr(tmp);
		}else{
			dao.setSibelr(ZERO);	
		}
		
		//Integers
		if(dao.getSidp()==null || "".equals(dao.getSidp())){
			dao.setSidp(ZERO);
		}
		if(dao.getSikns()==null || "".equals(dao.getSikns())){
			dao.setSikns(ZERO);
		}
		if(dao.getSintk()==null || "".equals(dao.getSintk())){
			dao.setSintk(ZERO);
		}
		
		if(dao.getSintk()==null || "".equals(dao.getSintk())){
			dao.setSintk(ZERO);
		}
		if(dao.getSivkb()==null || "".equals(dao.getSivkb())){
			dao.setSivkb(ZERO);
		}
		if(dao.getSiknk()==null || "".equals(dao.getSiknk())){
			dao.setSiknk(ZERO);
		}
		
		if(dao.getSitst()==null || "".equals(dao.getSitst())){
			dao.setSitst(ZERO);
		}
		if(dao.getSitrt()==null || "".equals(dao.getSitrt())){
			dao.setSitrt(ZERO);
		}
		if(dao.getSitrm()==null || "".equals(dao.getSitrm())){
			dao.setSitrm(ZERO);
		}
		if(dao.getSifid()==null || "".equals(dao.getSifid())){
			dao.setSifid(ZERO);
		}
		if(dao.getSibelt()==null || "".equals(dao.getSibelt())){
			dao.setSibelt(ZERO);
		}
		
		if(dao.getSikta()==null || "".equals(dao.getSikta())){
			dao.setSikta(ZERO);
		}
		
		if(dao.getSiktb()==null || "".equals(dao.getSiktb())){
			dao.setSiktb(ZERO);
		}
		
		if(dao.getSibel4()==null || "".equals(dao.getSibel4())){
			dao.setSibel4(ZERO);
		}
		if(dao.getSikdh()==null || "".equals(dao.getSikdh())){
			dao.setSikdh(ZERO);
		}
		
		if(dao.getSidt()==null || "".equals(dao.getSidt())){
			dao.setSidt(ZERO);
		}
		if(dao.getSibel5()==null || "".equals(dao.getSibel5())){
			dao.setSibel5(ZERO);
		}
		if(dao.getSibel6()==null || "".equals(dao.getSibel6())){
			dao.setSibel6(ZERO);
		}
		if(dao.getSibel7()==null || "".equals(dao.getSibel7())){
			dao.setSibel7(ZERO);
		}
		
		if(dao.getSibela()==null || "".equals(dao.getSibela())){
			dao.setSibela(ZERO);
		}
		
		if(dao.getSimp()==null || "".equals(dao.getSimp())){
			dao.setSimp(ZERO);
		}
		if(dao.getSibeld()==null || "".equals(dao.getSibeld())){
			dao.setSibeld(ZERO);
		}
		if(dao.getSiopd()==null || "".equals(dao.getSiopd())){
			dao.setSiopd(ZERO);
		}
		if(dao.getSifrbn()==null || "".equals(dao.getSifrbn())){
			dao.setSifrbn(ZERO);
		}
		
		if(dao.getS3039d()==null || "".equals(dao.getS3039d())){
			dao.setS3039d(ZERO);
		}
		if(dao.getS3039e()==null || "".equals(dao.getS3039e())){
			dao.setS3039e(ZERO);
		}
		if(dao.getS3039eo1()==null || "".equals(dao.getS3039eo1())){
			dao.setS3039eo1(ZERO);
		}
		if(dao.getS3039eo2()==null || "".equals(dao.getS3039eo2())){
			dao.setS3039eo2(ZERO);
		}
		
	}
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 */
	public boolean vaidateExchangesId(StandiDao dao){
		boolean retval = true;
		
		List s0004List = this.ediiDaoServices.findById(dao.getS0004(), this.validatorStackTrace);
		List s0010List = this.ediiDaoServices.findById(dao.getS0010(), this.validatorStackTrace);
		
		if( (s0004List!=null && s0004List.size()==1) && (s0010List!=null && s0010List.size()==1)){
			//OK
		}else{
			//populate errorStackTrace
			if(s0004List!=null && s0004List.size()==1){ 
				//OK 
			}else{
				this.validatorStackTrace.append(" UtvekslingsId Avd. er feil /" );
			}
			
			if(s0010List!=null && s0010List.size()==1){
				//OK
			}else{
				this.validatorStackTrace.append(" UtvekslingsId Tollvesenet er feil " );
				
			}
			retval = false;
			
		}
		
		return retval;
	}
}
