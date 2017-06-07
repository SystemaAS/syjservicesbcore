package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import no.systema.jservices.common.dao.SvewDao;
import no.systema.jservices.common.dao.services.Svtx03fDaoService;
import no.systema.jservices.common.dao.services.Svtx10fDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.main.util.MessageSourceHelper;

//TODO

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
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public SVEW_U(HttpServletRequest request, Svtx03fDaoService svtx03fDaoService, Svtx10fDaoService svtx10fDaoService, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.svtx03fDaoService = svtx03fDaoService;
		this.svtx10fDaoService = svtx10fDaoService;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(SvewDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			if ( dao.getSvew_knnr() > 0 && StringUtils.hasValue(dao.getSvew_knso())) 	{
				//Ursprungsland.
				if ( StringUtils.hasValue(dao.getSvew_ulkd()) &&  !existInSvtx03f(dao.getSvew_ulkd()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.svew.error.svew_ulkd",new Object[] { dao.getSvew_ulkd() }),"error", dbErrors));
					retval = false;
				}
				//Taricnr
				if ( StringUtils.hasValue(dao.getSvew_vata()) &&  !existInSvtx10f(dao.getSvew_vata()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.svew.error.svew_vata",new Object[] { dao.getSvew_vata() }),"error", dbErrors));
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

	private boolean existInSvtx03f(String svew_ulkd) {
		boolean exists = svtx03fDaoService.landKodeExist(svew_ulkd);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean existInSvtx10f(String svew_vata) {
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
