package no.systema.jservices.bcore.z.maintenance.controller.rules.sad;

import java.util.*;

import org.apache.logging.log4j.*;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.StandeDao;
import no.systema.jservices.model.dao.services.EdiiDaoServices;
import no.systema.jservices.model.dao.entities.EdiiDao;


/**
 * 
 * @author oscardelatorre
 * @date Sep 16, 2016
 */
public class SYFTAAAER_U {
	private static Logger logger = LogManager.getLogger(SYFTAAAER_U.class.getName());
	private EdiiDaoServices ediiDaoServices;
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	/**
	 * 
	 * @param ediiDaoServices
	 */
	public SYFTAAAER_U(EdiiDaoServices ediiDaoServices){
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
	public boolean isValidInput(StandeDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSeavd()!=null && !"".equals(dao.getSeavd())) ){	
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
		if("J".equals(dao.getSetolk())){
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
	public boolean isValidInputForDelete(StandeDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSeavd()!=null && !"".equals(dao.getSeavd())) ){	
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
	public void adjustNumericFields(StandeDao dao){
		String ZERO = "0";
		//STANDE
		//Decimals
		if(dao.getSebel1()!=null && !"".equals(dao.getSebel1())){
			String tmp = dao.getSebel1().replace(",", ".");
			dao.setSebel1(tmp);
		}else{
			dao.setSebel1(ZERO);
		}
		
		if(dao.getSebel2()!=null && !"".equals(dao.getSebel2())){
			String tmp = dao.getSebel2().replace(",", ".");
			dao.setSebel2(tmp);
		}else{
			dao.setSebel2(ZERO);
		}
		
		if(dao.getSevku()!=null && !"".equals(dao.getSevku())){
			String tmp = dao.getSevku().replace(",", ".");
			dao.setSevku(tmp);
		}else{
			dao.setSevku(ZERO);
		}
		if(dao.getSetst()!=null && !"".equals(dao.getSetst())){
			String tmp = dao.getSetst().replace(",", ".");
			dao.setSetst(tmp);
		}else{
			dao.setSetst(ZERO);
		}
		
		//Integers
		if(dao.getSedp()==null || "".equals(dao.getSedp())){
			dao.setSedp(ZERO);
		}
		if(dao.getSekns()==null || "".equals(dao.getSekns())){
			dao.setSekns(ZERO);
		}
		if(dao.getSentk()==null || "".equals(dao.getSentk())){
			dao.setSentk(ZERO);
		}
		
		if(dao.getSevkb()==null || "".equals(dao.getSevkb())){
			dao.setSevkb(ZERO);
		}
		
		if(dao.getSeknk()==null || "".equals(dao.getSeknk())){
			dao.setSeknk(ZERO);
		}
		
		
		if(dao.getSekdh()==null || "".equals(dao.getSekdh())){
			dao.setSekdh(ZERO);
		}
		if(dao.getSetrm()==null || "".equals(dao.getSetrm())){
			dao.setSetrm(ZERO);
		}
		if(dao.getSefid()==null || "".equals(dao.getSefid())){
			dao.setSefid(ZERO);
		}
		if(dao.getSekta()==null || "".equals(dao.getSekta())){
			dao.setSekta(ZERO);
		}
		if(dao.getSektb()==null || "".equals(dao.getSektb())){
			dao.setSektb(ZERO);
		}
		if(dao.getSedt()==null || "".equals(dao.getSedt())){
			dao.setSedt(ZERO);
		}
		
		if(dao.getSegkd()==null || "".equals(dao.getSegkd())){
			dao.setSegkd(ZERO);
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
	public boolean vaidateExchangesId(StandeDao dao){
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
