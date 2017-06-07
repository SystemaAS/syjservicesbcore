package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import no.systema.jservices.common.dao.SvewDao;
import no.systema.jservices.common.dao.services.SvtproDaoService;
import no.systema.jservices.common.dao.services.Svtx03fDaoService;
import no.systema.jservices.common.dao.services.Svtx10fDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Mai 22, 2017
 */
public class SVEW_U {
	private static Logger logger = Logger.getLogger(SVEW_U.class.getName());
	private JsonResponseWriter2<SvewDao> jsonWriter = new JsonResponseWriter2<SvewDao>();
	private MessageSourceHelper messageSourceHelper = null;
	private Svtx03fDaoService svtx03fDaoService = null;
	private Svtx10fDaoService svtx10fDaoService = null;
	private SvtproDaoService svtproDaoService = null;
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public SVEW_U(HttpServletRequest request, Svtx03fDaoService svtx03fDaoService, Svtx10fDaoService svtx10fDaoService, SvtproDaoService svtproDaoService, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.svtx03fDaoService = svtx03fDaoService;
		this.svtx10fDaoService = svtx10fDaoService;
		this.svtproDaoService = svtproDaoService;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(SvewDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			if ( dao.getSvew_knnr() > 0 && StringUtils.hasValue(dao.getSvew_knso())) 	{
				//Ursprungsland.
				if ( StringUtils.hasValue(dao.getSvew_ulkd()) &&  !existAsUlkdInSvtx03f(dao.getSvew_ulkd()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.svew.error.svew_ulkd",new Object[] { dao.getSvew_ulkd() }),"error", dbErrors));
					retval = false;
				}
				//Taricnr
				if ( StringUtils.hasValue(dao.getSvew_vata()) &&  !existAsVataInSvtx10f(dao.getSvew_vata()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.svew.error.svew_vata",new Object[] { dao.getSvew_vata() }),"error", dbErrors));
					retval = false;
				}
				//Förfarande 37:1
				if ( StringUtils.hasValue(dao.getSvew_eup1()) &&  !existInSvtpro(dao.getSvew_eup1()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.svew.error.svew_eup1",new Object[] { dao.getSvew_eup1() }),"error", dbErrors));
					retval = false;
				}				
				//Förfarande 37:1
				if ( StringUtils.hasValue(dao.getSvew_eup2()) &&  !existAsEup2InSvtx03f(dao.getSvew_eup2()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.svew.error.svew_eup2",new Object[] { dao.getSvew_eup2() }),"error", dbErrors));
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



	private boolean existInSvtpro(String svew_eup1) {
		boolean exists = svtproDaoService.svpr_prExist(svew_eup1);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isValidInputForDelete(SvewDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dto
			if ( dao.getSvew_knnr() > 0 && StringUtils.hasValue(dao.getSvew_knso())) 	{
				// OK
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}

	private boolean existAsUlkdInSvtx03f(String svew_ulkd) {
		boolean exists = svtx03fDaoService.landKodeExist(svew_ulkd);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}

	private boolean existAsEup2InSvtx03f(String svew_eup2) {
		boolean exists = svtx03fDaoService.eup2Exist(svew_eup2);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}	
	
	private boolean existAsVataInSvtx10f(String svew_vata) {
		String trailingZeros = "00";
		String svew_vataToValidate = svew_vata + trailingZeros;
		boolean exists = svtx10fDaoService.tariExportNrExist(svew_vataToValidate);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
