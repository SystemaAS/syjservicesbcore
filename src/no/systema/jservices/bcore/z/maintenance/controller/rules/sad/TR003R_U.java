package no.systema.jservices.bcore.z.maintenance.controller.rules.sad;

import java.util.*;

import org.apache.log4j.Logger;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.TrustdDao;
import no.systema.jservices.model.dao.services.EdiiDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.sad.Trkodl01DaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.sad.TrustdDaoServices;




/**
 * 
 * @author oscardelatorre
 * @date Sep 30, 2016
 */
public class TR003R_U {
	private static Logger logger = Logger.getLogger(TR003R_U.class.getName());
	private EdiiDaoServices ediiDaoServices;
	private Trkodl01DaoServices trkodl01DaoServices;
	
	private StringBuffer validatorStackTrace = new StringBuffer();
	public String getValidatorStackTrace (){ return this.validatorStackTrace.toString(); }
	
	/**
	 * 
	 * @param ediiDaoServices
	 */
	public TR003R_U(EdiiDaoServices ediiDaoServices, Trkodl01DaoServices trkodl01DaoServices){
		this.ediiDaoServices = ediiDaoServices;
		this.trkodl01DaoServices = trkodl01DaoServices;
	}

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @param errorStackTrace
	 * @return
	 */
	public boolean isValidInput(TrustdDao dao, String user, String mode ){
		boolean retval = true;
		
		//starting point
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getThavd()!=null && !"".equals(dao.getThavd())) ){	
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
		//(1) Validity of Exchanges I
		retval = this.vaidateExchangesId(dao);
		if(retval){
			//(2) Validity Toldsted (tullkontor)
			/* TODO
			if(dao.getTitsb()!=null && !"".equals(dao.getTitsb())){
				retval = this.vaidateTullkontorId(dao);
			}
			*/
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
	public boolean isValidInputForDelete(TrustdDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getThavd()!=null && !"".equals(dao.getThavd())) ){	
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
	public void adjustNumericFields(TrustdDao dao){
		String ZERO = "0";
		//TRUSTD
		//Decimals
		if(dao.getThgpr()!=null && !"".equals(dao.getThgpr())){
			String tmp = dao.getThgpr().replace(",", ".");
			dao.setThgpr(tmp);
		}else{
			dao.setThgpr(ZERO);
		}
		if(dao.getThvkb()!=null && !"".equals(dao.getThvkb())){
			String tmp = dao.getThvkb().replace(",", ".");
			dao.setThvkb(tmp);
		}else{
			dao.setThvkb(ZERO);
		}
		
		//Integers
		if(dao.getThtdn()==null || "".equals(dao.getThtdn())){
			dao.setThtdn(ZERO);
		}
		if(dao.getThkna()==null || "".equals(dao.getThkna())){
			dao.setThkna(ZERO);
		}
		if(dao.getThkns()==null || "".equals(dao.getThkns())){
			dao.setThkns(ZERO);
		}
		if(dao.getThknk()==null || "".equals(dao.getThknk())){
			dao.setThknk(ZERO);
		}
		if(dao.getThtrm()==null || "".equals(dao.getThtrm())){
			dao.setThtrm(ZERO);
		}
		if(dao.getThgbl()==null || "".equals(dao.getThgbl())){
			dao.setThgbl(ZERO);
		}
		if(dao.getThdant()==null || "".equals(dao.getThdant())){
			dao.setThdant(ZERO);
		}
		if(dao.getThgkd()==null || "".equals(dao.getThgkd())){
			dao.setThgkd(ZERO);
		}
		if(dao.getThgbgi()==null || "".equals(dao.getThgbgi())){
			dao.setThgbgi(ZERO);
		}
		if(dao.getThlstl()==null || "".equals(dao.getThlstl())){
			dao.setThlstl(ZERO);
		}
		if(dao.getThvpos()==null || "".equals(dao.getThvpos())){
			dao.setThvpos(ZERO);
		}
		if(dao.getThntk()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		if(dao.getThomd()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		if(dao.getThtet()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		if(dao.getThnttd()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		if(dao.getThntll()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		if(dao.getThntk()==null || "".equals(dao.getThntk())){
			dao.setThntk(ZERO);
		}
		
		//Dates
		if(dao.getThdt()==null || "".equals(dao.getThdt())){
			dao.setThdt(ZERO);
		}
		if(dao.getThddt()==null || "".equals(dao.getThddt())){
			dao.setThddt(ZERO);
		}
		if(dao.getThddtk()==null || "".equals(dao.getThddtk())){
			dao.setThddtk(ZERO);
		}
		if(dao.getThtrdt()==null || "".equals(dao.getThtrdt())){
			dao.setThtrdt(ZERO);
		}
		
		
		
		
	}
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 */
	public boolean vaidateExchangesId(TrustdDao dao){
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
				this.validatorStackTrace.append(" UtvekslingsId Avd. er ugyldig /" );
			}
			
			if(s0010List!=null && s0010List.size()==1){
				//OK
			}else{
				this.validatorStackTrace.append(" UtvekslingsId Tollvesenet er ugyldig " );
				
			}
			retval = false;
			
		}
		
		return retval;
	}
	/**
	 * 
	 * @param dao
	 * @return
	 */
	public boolean vaidateTullkontorId(TrustdDao dao){
		boolean retval = true;
		/* TODO
		
		String UNIQUE_CODE_TULLKONTOR = "106";
		List list = this.trkodl01DaoServices.findById(UNIQUE_CODE_TULLKONTOR, dao.getTitsb(), this.validatorStackTrace);
		
		if( list!=null && list.size()==1 ){
			//OK
			//logger.info("AAAA");
		}else{
			//logger.info("AAA");
			this.validatorStackTrace.append(" Freml.tollsted er ugyldig " );
			retval = false;
		}	
		*/
		return retval;
	}
}
