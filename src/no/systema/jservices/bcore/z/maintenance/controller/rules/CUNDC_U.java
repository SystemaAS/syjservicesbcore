package no.systema.jservices.bcore.z.maintenance.controller.rules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.*;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.util.EmailValidator;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.CundcDto;
import no.systema.jservices.model.dao.entities.EdiiDao;
import no.systema.jservices.model.dao.services.CundcDaoServices;
import no.systema.jservices.model.dao.services.EdiiDaoServices;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Nov 7, 2016
 */
public class CUNDC_U {
	private static Logger logger = LoggerFactory.getLogger(CUNDC_U.class.getName());
	private JsonResponseWriter jsonWriter = new JsonResponseWriter();
	private MessageSourceHelper messageSourceHelper = null;
	private CundcDaoServices cundcDaoServices = null;
	private KofastDaoServices kofastDaoServices = null;
	private EdiiDaoServices ediiDaoServices = null;
	private EmailValidator emailValidator = new EmailValidator();
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;
	private boolean ID_CHECK = true;


	public CUNDC_U(HttpServletRequest request, CundcDaoServices cundcDaoServices, KofastDaoServices kofastDaoServices, EdiiDaoServices ediiDaoServices, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.cundcDaoServices = cundcDaoServices;
		this.kofastDaoServices = kofastDaoServices;
		this.ediiDaoServices = ediiDaoServices;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(CundcDto dto, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dto
			if ( (dto.getCfirma() != null && !"".equals(dto.getCfirma())) && (dto.getCcompn() != null && !"".equals(dto.getCcompn()))
					&& (dto.getCconta() != null && !"".equals(dto.getCconta())) && (dto.getCtype() != null && !"".equals(dto.getCtype())) ) {
				// Check duplicate
				//Removed duplicate control because JOVOs requirement for TOTEN. Ref.mail: May 28--> allow duplicates
				/*
				if (  "A".equals(mode)   &&  existInCundc(dto.getCfirma(), dto.getCcompn(), dto.getCconta(), dto.getCtype())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.cconta", new Object[] { dto.getCconta(), dto.getCcompn() }), "error", dbErrors));
					retval = false;
				}
				if (  "U".equals(mode)   &&  existInCundc(dto)) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.cconta", new Object[] { dto.getCconta(), dto.getCcompn() }), "error", dbErrors));
					retval = false;
				}*/
				// Check funksjon (if prefixed with *)
				if (dto.getCtype() != null && !"".equals(dto.getCtype())) {
					if (dto.getCtype().startsWith("*") && !existInKofast(dto.getCtype().substring(1), !ID_CHECK)) {
						errors.append(jsonWriter.setJsonSimpleErrorResult(user,
								messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.ctype", new Object[] { dto.getCtype() }), "error", dbErrors));
						retval = false;
					}
				}
				// Check valid mail (if ctype is prefixed with *)
				if (StringUtils.hasValue(dto.getCemail())) {
					if (StringUtils.hasValue(dto.getCtype())) {
						if (dto.getCtype().startsWith("*") && !validEmail(dto.getCemail())) {
							errors.append(jsonWriter.setJsonSimpleErrorResult(user,
									messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.email", new Object[] { dto.getCemail() }), "error", dbErrors));
							retval = false;
						}
					}
				}
				//If ctype = EMMA-XML, 3 mandatory fields, cmobil, cfax, cphone = Shapeshifters
				if ("EMMA-XML".equals(dto.getCconta())) {
					if (!StringUtils.hasValue(dto.getCphone()) ) {
						errors.append(jsonWriter.setJsonSimpleErrorResult(user,
								messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.mandatory.cphone", new Object[] { null }), "error", dbErrors));
						retval = false;
						
					}
					//Mottakers utv. id
					if (!StringUtils.hasValue(dto.getCfax()) ) {
						errors.append(jsonWriter.setJsonSimpleErrorResult(user,
								messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.mandatory.cfax", new Object[] { null }), "error", dbErrors));
						retval = false;
						
					} else if (!existInEdii(EdiiDaoServices.EXTERNAL, dto.getCfax())) { //Shapeshifter
						errors.append(jsonWriter.setJsonSimpleErrorResult(user,
								messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.mandatory.external.inex", new Object[] { dto.getCfax() }), "error", dbErrors));						
						retval = false;					
					}
					//Avsenders utv. id
					if (!StringUtils.hasValue(dto.getCmobil()) ) {
						errors.append(jsonWriter.setJsonSimpleErrorResult(user,
								messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.mandatory.cmobil", new Object[] { null }), "error", dbErrors));
						retval = false;
					} else if (!existInEdii(EdiiDaoServices.INTERNAL, dto.getCmobil())) { //Shapeshifter
						errors.append(jsonWriter.setJsonSimpleErrorResult(user,
								messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.mandatory.internal.inex", new Object[] { dto.getCmobil() }), "error", dbErrors));						
						retval = false;
					}					
				}
				// Check Faste Koder (KOFAST)
				if (!existInKofast(user, dto)) {
					retval = false;
				}				
				
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}

		logger.info("isValidInput, retval="+retval+" , errors="+errors);
		
		return retval;
	}





	private boolean validEmail(String cemail) {
		if (emailValidator.validateEmail(cemail)) {
			return true;
		} else {
			//Special case 1
			if(cemail.equals("@")) {
				return true;
			}
			//Special case 2
			if(cemail.equals("@user")) {
				return true;
			}
			return false;
		}
	}

	private boolean existInEdii(String inex, String inid) {
		boolean exists = false;
		List<EdiiDao> list = ediiDaoServices.findByIdAndInex(inex,inid, dbErrors);
		if (list != null && list.size() > 0) {
			exists = true;
		}		
		return exists;
	}

	public boolean isValidInputForDelete(CundcDto dto, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ( StringUtils.hasValue(dto.getCfirma()) && StringUtils.hasValue(dto.getCcompn()) && StringUtils.hasValue(dto.getCconta()) && dto.getCtype() != null ) {
				// OK
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}

	private boolean existInCundc(String cfirma, String ccompn, String cconta, String ctype) {
		boolean exists = this.cundcDaoServices.exists(cfirma, ccompn, cconta, ctype ,dbErrors);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}

	private boolean existInCundc(CundcDto dto) {
		if (!dto.getCcontaorg().equals(dto.getCconta()) || !dto.getCtypeorg().equals(dto.getCtype())) {
			return existInCundc(dto.getCfirma(), dto.getCcompn(), dto.getCconta(), dto.getCtype());
			
		}
		return false;
	}	
	
	private boolean existInKofast(String value, boolean idCheck) {
		boolean exists = false;
		if (idCheck) {
			List list = kofastDaoServices.findById(FasteKoder.FUNKSJON, value, dbErrors);
			if (value.length() == 0 || value.contains(" ")) { //Abort
				return true;
			}
			if (list != null && list.size() > 0) {
				exists = true;
			}
		} else {
			exists = this.kofastDaoServices.exists(FasteKoder.FUNKSJON, value, dbErrors);
		}

		if (!exists) {
			return false;
		} else {
			return true;
		}

	}

	
	private boolean existInKofast(String user, CundcDto dto) {
		boolean exist = true;
		
		if ( (dto.getAvkved1() != null && !"".equals(dto.getAvkved1()) ) && !existInKofast(dto.getAvkved1(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved1() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved2() != null && !"".equals(dto.getAvkved2()) ) && !existInKofast(dto.getAvkved2(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved2() }), "error",
					dbErrors));		
			exist = false;
		}

		if ((dto.getAvkved3() != null && !"".equals(dto.getAvkved3()) ) && !existInKofast(dto.getAvkved3(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved3() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved4() != null && !"".equals(dto.getAvkved4()) ) && !existInKofast(dto.getAvkved4(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved4() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved5() != null && !"".equals(dto.getAvkved5()) ) && !existInKofast(dto.getAvkved5(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved5() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved6() != null && !"".equals(dto.getAvkved6()) ) && !existInKofast(dto.getAvkved6(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved6() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved7() != null && !"".equals(dto.getAvkved7()) ) && !existInKofast(dto.getAvkved7(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved7() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved8() != null && !"".equals(dto.getAvkved8()) ) && !existInKofast(dto.getAvkved8(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved8() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved9() != null && !"".equals(dto.getAvkved9()) ) && !existInKofast(dto.getAvkved9(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved9() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved10() != null && !"".equals(dto.getAvkved10()) ) && !existInKofast(dto.getAvkved10(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved10() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved11() != null && !"".equals(dto.getAvkved11()) ) && !existInKofast(dto.getAvkved11(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved11() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved12() != null && !"".equals(dto.getAvkved12()) ) && !existInKofast(dto.getAvkved12(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved12() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved13() != null && !"".equals(dto.getAvkved13()) ) && !existInKofast(dto.getAvkved13(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved13() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved14() != null && !"".equals(dto.getAvkved14()) ) && !existInKofast(dto.getAvkved14(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved14() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved15() != null && !"".equals(dto.getAvkved15()) ) && !existInKofast(dto.getAvkved15(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved15() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved16() != null && !"".equals(dto.getAvkved16()) ) && !existInKofast(dto.getAvkved16(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved16() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved17() != null && !"".equals(dto.getAvkved17()) ) && !existInKofast(dto.getAvkved17(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved17() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved18() != null && !"".equals(dto.getAvkved18()) ) && !existInKofast(dto.getAvkved18(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved18() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved19() != null && !"".equals(dto.getAvkved19()) ) && !existInKofast(dto.getAvkved19(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved19() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved20() != null && !"".equals(dto.getAvkved20()) ) && !existInKofast(dto.getAvkved20(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved20() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved21() != null && !"".equals(dto.getAvkved21()) ) && !existInKofast(dto.getAvkved21(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved21() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved22() != null && !"".equals(dto.getAvkved22()) ) && !existInKofast(dto.getAvkved22(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved22() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved23() != null && !"".equals(dto.getAvkved23()) ) && !existInKofast(dto.getAvkved23(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved23() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved24() != null && !"".equals(dto.getAvkved24()) ) && !existInKofast(dto.getAvkved24(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved24() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved25() != null && !"  ".equals(dto.getAvkved25()) ) && !existInKofast(dto.getAvkved25(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved25() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved26() != null && !"".equals(dto.getAvkved26()) ) && !existInKofast(dto.getAvkved26(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved26() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved27() != null && !"".equals(dto.getAvkved27()) ) && !existInKofast(dto.getAvkved27(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved27() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved28() != null && !"".equals(dto.getAvkved28()) ) && !existInKofast(dto.getAvkved28(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved28() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved29() != null && !"".equals(dto.getAvkved29()) ) && !existInKofast(dto.getAvkved29(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved29() }), "error",
					dbErrors));		
			exist = false;
		}
		if ((dto.getAvkved30() != null && !"".equals(dto.getAvkved30()) ) && !existInKofast(dto.getAvkved30(), ID_CHECK)){
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.avkved", new Object[] { dto.getAvkved30() }), "error",
					dbErrors));		
			exist = false;
		}
		
		return exist;
	}
	
	
}
