package no.systema.jservices.bcore.z.maintenance.controller.rules;

import org.apache.log4j.Logger;

import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.CundcDao;
import no.systema.jservices.model.dao.services.CundcDaoServices;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Nov 7, 2016
 */
public class CUNDC_U {
	private static Logger logger = Logger.getLogger(CUNDC_U.class.getName());
	private JsonResponseWriter jsonWriter = new JsonResponseWriter();
	private MessageSourceHelper messageSourceHelper = new MessageSourceHelper();
	private CundcDaoServices cundcDaoServices = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public CUNDC_U(CundcDaoServices cundcDaoServices, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		this.cundcDaoServices = cundcDaoServices;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(CundcDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ((dao.getCfirma() != null && !"".equals(dao.getCfirma())) && (dao.getCcompn() != null && !"".equals(dao.getCcompn()))
					&& (dao.getCconta() != null && !"".equals(dao.getCconta()))) {
				// Check duplicate
				if ("A".equals(mode) && existInCundc(user, dao.getCfirma() , dao.getCcompn(), dao.getCconta())) {
					// TODO; kanske returnera false, return false;
				}
				
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		
		return retval;
	}

	public boolean isValidInputForDelete(CundcDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ((dao.getCfirma() != null && !"".equals(dao.getCfirma())) && (dao.getCcompn() != null && !"".equals(dao.getCcompn()))
					&& (dao.getCconta() != null && !"".equals(dao.getCconta()))) {
				// OK
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}

	private boolean existInCundc(String userName, String cfirma, String ccompn, String cconta) {
		boolean exists = this.cundcDaoServices.exists(cfirma, ccompn, cconta);
		if (!exists) {
			return false;
		} else {
/*			errors.append(jsonWriter.setJsonSimpleErrorResult(userName,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.warning.cconta", new Object[] { cconta, ccompn }), "warning", dbErrors));
*/			
			errors.append(jsonWriter.setJsonSimpleErrorResult(userName, "Kalle Anka", "error", dbErrors));
//TODO: messageSourceHelper har ballat ur...?
			return true;
			
		}
	}	
	

	
}
