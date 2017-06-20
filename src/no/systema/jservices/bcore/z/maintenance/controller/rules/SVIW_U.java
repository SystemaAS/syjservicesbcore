package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import no.systema.jservices.common.dao.SviwDao;
import no.systema.jservices.common.dao.services.SvtproDaoService;
import no.systema.jservices.common.dao.services.Svtx03fDaoService;
import no.systema.jservices.common.dao.services.Svtx10fDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Jun 20, 2017
 */
public class SVIW_U {
	private static Logger logger = Logger.getLogger(SVIW_U.class.getName());
	private JsonResponseWriter2<SviwDao> jsonWriter = new JsonResponseWriter2<SviwDao>();
	private MessageSourceHelper messageSourceHelper = null;
	private Svtx03fDaoService svtx03fDaoService = null;
	private Svtx10fDaoService svtx10fDaoService = null;
	private SvtproDaoService svtproDaoService = null;
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public SVIW_U(HttpServletRequest request, Svtx03fDaoService svtx03fDaoService, Svtx10fDaoService svtx10fDaoService, SvtproDaoService svtproDaoService, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.svtx03fDaoService = svtx03fDaoService;
		this.svtx10fDaoService = svtx10fDaoService;
		this.svtproDaoService = svtproDaoService;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(SviwDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			if ( dao.getSviw_knnr() > 0 && StringUtils.hasValue(dao.getSviw_knso())) 	{
				//Ursprungsland.
				if ( StringUtils.hasValue(dao.getSviw_ulkd()) &&  !svtx03fDaoService.landKodeExist(dao.getSviw_ulkd()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_ulkd",new Object[] { dao.getSviw_ulkd() }),"error", dbErrors));
					retval = false;
				}
				//Taricnr
				if ( StringUtils.hasValue(dao.getSviw_vata()) &&  !existAsVataInSvtx10f(dao.getSviw_vata()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_vata",new Object[] { dao.getSviw_vata() }),"error", dbErrors));
					retval = false;
				}
				//Förfarande 37:1
				if ( StringUtils.hasValue(dao.getSviw_eup1()) &&  !svtproDaoService.svpr_prExist(dao.getSviw_eup1()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_eup1",new Object[] { dao.getSviw_eup1() }),"error", dbErrors));
					retval = false;
				}				
				//Förfarande 37:1
				if ( StringUtils.hasValue(dao.getSviw_eup2()) &&  !svtx03fDaoService.eup2Exist(dao.getSviw_eup2()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_eup2",new Object[] { dao.getSviw_eup2() }),"error", dbErrors));
					retval = false;
				}
				//Kollislag 1
				if ( StringUtils.hasValue(dao.getSviw_kosl()) &&  !svtx03fDaoService.kollislagExist(dao.getSviw_kosl())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_kosl",new Object[] { dao.getSviw_kosl() }),"error", dbErrors));
					retval = false;
				}
				//Kollislag 2
				if ( StringUtils.hasValue(dao.getSviw_kos2()) &&  !svtx03fDaoService.kollislagExist(dao.getSviw_kos2())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_kosl",new Object[] { dao.getSviw_kos2() }),"error", dbErrors));
					retval = false;
				}
				//Kollislag 3
				if ( StringUtils.hasValue(dao.getSviw_kos3()) &&  !svtx03fDaoService.kollislagExist(dao.getSviw_kos3())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_kosl",new Object[] { dao.getSviw_kos3() }),"error", dbErrors));
					retval = false;
				}
				//Kollislag 4
				if ( StringUtils.hasValue(dao.getSviw_kos4()) &&  !svtx03fDaoService.kollislagExist(dao.getSviw_kos4())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_kosl",new Object[] { dao.getSviw_kos4() }),"error", dbErrors));
					retval = false;
				}
				//Kollislag 5
				if ( StringUtils.hasValue(dao.getSviw_kos5()) &&  !svtx03fDaoService.kollislagExist(dao.getSviw_kos5())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_kosl",new Object[] { dao.getSviw_kos5() }),"error", dbErrors));
					retval = false;
				}
				//Bilagd handling 1
				if ( StringUtils.hasValue(dao.getSviw_bit1()) &&  !svtx03fDaoService.bilagdHandlingExist(dao.getSviw_bit1())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_bit",new Object[] { dao.getSviw_bit1() }),"error", dbErrors));
					retval = false;
				}
				//Bilagd handling 2
				if ( StringUtils.hasValue(dao.getSviw_bit2()) &&  !svtx03fDaoService.bilagdHandlingExist(dao.getSviw_bit2())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_bit",new Object[] { dao.getSviw_bit2() }),"error", dbErrors));
					retval = false;
				}
				//Bilagd handling 3
				if ( StringUtils.hasValue(dao.getSviw_bit3()) &&  !svtx03fDaoService.bilagdHandlingExist(dao.getSviw_bit3())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_bit",new Object[] { dao.getSviw_bit3() }),"error", dbErrors));
					retval = false;
				}
				//Bilagd handling 4
				if ( StringUtils.hasValue(dao.getSviw_bit4()) &&  !svtx03fDaoService.bilagdHandlingExist(dao.getSviw_bit4())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_bit",new Object[] { dao.getSviw_bit4() }),"error", dbErrors));
					retval = false;
				}
				//Bilagd handling 5
				if ( StringUtils.hasValue(dao.getSviw_bit5()) &&  !svtx03fDaoService.bilagdHandlingExist(dao.getSviw_bit5())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_bit",new Object[] { dao.getSviw_bit5() }),"error", dbErrors));
					retval = false;
				}
				//Bilagd handling 6
				if ( StringUtils.hasValue(dao.getSviw_bit6()) &&  !svtx03fDaoService.bilagdHandlingExist(dao.getSviw_bit6())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_bit",new Object[] { dao.getSviw_bit6() }),"error", dbErrors));
					retval = false;
				}
				//Bilagd handling 7
				if ( StringUtils.hasValue(dao.getSviw_bit7()) &&  !svtx03fDaoService.bilagdHandlingExist(dao.getSviw_bit7())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_bit",new Object[] { dao.getSviw_bit7() }),"error", dbErrors));
					retval = false;
				}
				//Bilagd handling 8
				if ( StringUtils.hasValue(dao.getSviw_bit8()) &&  !svtx03fDaoService.bilagdHandlingExist(dao.getSviw_bit8())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_bit",new Object[] { dao.getSviw_bit8() }),"error", dbErrors));
					retval = false;
				}
				//Bilagd handling 9
				if ( StringUtils.hasValue(dao.getSviw_bit9()) &&  !svtx03fDaoService.bilagdHandlingExist(dao.getSviw_bit9())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_bit",new Object[] { dao.getSviw_bit9() }),"error", dbErrors));
					retval = false;
				}
				//Tidigare handling 1
				if ( StringUtils.hasValue(dao.getSviw_tit1()) &&  !svtx03fDaoService.tidigareHandlingExist(dao.getSviw_tit1())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_tit",new Object[] { dao.getSviw_tit1() }),"error", dbErrors));
					retval = false;
				}
				//Tidigare handling 2
				if ( StringUtils.hasValue(dao.getSviw_tit2()) &&  !svtx03fDaoService.tidigareHandlingExist(dao.getSviw_tit2())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_tit",new Object[] { dao.getSviw_tit2() }),"error", dbErrors));
					retval = false;
				}
				//Tidigare handling 3
				if ( StringUtils.hasValue(dao.getSviw_tit3()) &&  !svtx03fDaoService.tidigareHandlingExist(dao.getSviw_tit3())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_tit",new Object[] { dao.getSviw_tit3() }),"error", dbErrors));
					retval = false;
				}
				//Tidigare handling 4
				if ( StringUtils.hasValue(dao.getSviw_tit4()) &&  !svtx03fDaoService.tidigareHandlingExist(dao.getSviw_tit4())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_tit",new Object[] { dao.getSviw_tit4() }),"error", dbErrors));
					retval = false;
				}
				//Tidigare handling 5
				if ( StringUtils.hasValue(dao.getSviw_tit5()) &&  !svtx03fDaoService.tidigareHandlingExist(dao.getSviw_tit5())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_tit",new Object[] { dao.getSviw_tit5() }),"error", dbErrors));
					retval = false;
				}
				//Tidigare handling 6
				if ( StringUtils.hasValue(dao.getSviw_tit6()) &&  !svtx03fDaoService.tidigareHandlingExist(dao.getSviw_tit6())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_tit",new Object[] { dao.getSviw_tit6() }),"error", dbErrors));
					retval = false;
				}
				//Tidigare handling 7
				if ( StringUtils.hasValue(dao.getSviw_tit7()) &&  !svtx03fDaoService.tidigareHandlingExist(dao.getSviw_tit7())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_tit",new Object[] { dao.getSviw_tit7() }),"error", dbErrors));
					retval = false;
				}
				//Tidigare handling 8
				if ( StringUtils.hasValue(dao.getSviw_tit8()) &&  !svtx03fDaoService.tidigareHandlingExist(dao.getSviw_tit8())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_tit",new Object[] { dao.getSviw_tit8() }),"error", dbErrors));
					retval = false;
				}
				//Tidigare handling 9
				if ( StringUtils.hasValue(dao.getSviw_tit9()) &&  !svtx03fDaoService.tidigareHandlingExist(dao.getSviw_tit9())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_tit",new Object[] { dao.getSviw_tit9() }),"error", dbErrors));
					retval = false;
				}
				//Landkode
				if ( StringUtils.hasValue(dao.getSviw_lagl()) &&  !svtx03fDaoService.landKodeExist(dao.getSviw_lagl()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sviw.error.sviw_lagl",new Object[] { dao.getSviw_lagl() }),"error", dbErrors));
					retval = false;
				}
				
				
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}

		return retval;
	}

	public boolean isValidInputForDelete(SviwDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dto
			if ( dao.getSviw_knnr() > 0 && StringUtils.hasValue(dao.getSviw_knso())) 	{
				// OK
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}

	//TODO ta bort när import ska göras, lägg i specific exist.
	private boolean existAsVataInSvtx10f(String sviw_vata) {
		String trailingZeros = "00";
		String sviw_vataToValidate = sviw_vata + trailingZeros;
		boolean exists = svtx10fDaoService.tariExportNrExist(sviw_vataToValidate);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
