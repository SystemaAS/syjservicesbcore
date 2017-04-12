package no.systema.jservices.bcore.z.maintenance.controller.rules;

import org.apache.log4j.Logger;

import no.systema.jservices.common.dao.ArkextDao;
import no.systema.jservices.common.dao.ArktxtDao;
import no.systema.jservices.common.dao.services.ArkextDaoService;
import no.systema.jservices.common.dao.services.ArktxtDaoService;
import no.systema.jservices.common.dto.ArktxtDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 11, 2017
 */
public class ARKTXT_U {
	private static Logger logger = Logger.getLogger(ARKTXT_U.class.getName());
	private JsonResponseWriter2<ArktxtDto> jsonWriter = new JsonResponseWriter2<ArktxtDto>();
	private MessageSourceHelper messageSourceHelper = new MessageSourceHelper();
	private ArktxtDaoService arktxtDaoService = null;
	private ArkextDaoService arkextDaoService = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public ARKTXT_U(ArktxtDaoService arktxtDaoService, ArkextDaoService arkextDaoService, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		this.arktxtDaoService = arktxtDaoService;
		this.arkextDaoService = arkextDaoService;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(ArktxtDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			if ((dao.getArtype() != null && !"".equals(dao.getArtype())) && (dao.getArtxt() != null && !"".equals(dao.getArtxt()))) {
				//Check exist
				if ("A".equals(mode) && exist(dao)) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,messageSourceHelper.getMessage("systema.bcore.kunderegister.arktxt.error.artype.exist",new Object[] { dao.getArtype() }),"error", dbErrors));
					retval = false;
				}
				if ((dao.getArklag() != null && !"".equals(dao.getArklag())) && !existInArkext(dao.getArklag())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,messageSourceHelper.getMessage("systema.bcore.kunderegister.arktxt.error.arklag",new Object[] { dao.getArklag() }),"error", dbErrors));
					retval = false;
				}
				if (dao.getArkved() != null && !"".equals(dao.getArkved()) && !existInXXX(dao.getArkved())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,messageSourceHelper.getMessage("systema.bcore.kunderegister.arktxt.error.arkved",new Object[] { dao.getArkved() }),"error", dbErrors));
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


	public boolean isValidInputForDelete(ArktxtDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			if ( dao.getArtype() != null && !"".equals(dao.getArtype()) ) {
				// OK
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}

	private boolean existInArkext(String arklag) {
		ArkextDao qDao = new ArkextDao();
		qDao.setArcext(arklag);
		boolean exists = arkextDaoService.exist(qDao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}

	private boolean existInXXX(String yyy) {
		//TODO
		return true;
	}	

	private boolean exist(ArktxtDao dao) {
		boolean exists = arktxtDaoService.exist(dao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
}
