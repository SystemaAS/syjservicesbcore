package no.systema.jservices.bcore.z.maintenance.controller.rules;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.*;

import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.dao.ArkextDao;
import no.systema.jservices.common.dao.ArktxtDao;
import no.systema.jservices.common.dao.services.ArkextDaoService;
import no.systema.jservices.common.dao.services.ArktxtDaoService;
import no.systema.jservices.common.dto.ArktxtDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 11, 2017
 */
public class ARKTXT_U {
	private static Logger logger = LogManager.getLogger(ARKTXT_U.class.getName());
	private JsonResponseWriter2<ArktxtDto> jsonWriter = new JsonResponseWriter2<ArktxtDto>();
	private MessageSourceHelper messageSourceHelper = null;
	private ArktxtDaoService arktxtDaoService = null;
	private ArkextDaoService arkextDaoService = null;
	private KofastDaoServices kofastDaoServices = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public ARKTXT_U(HttpServletRequest request, ArktxtDaoService arktxtDaoService, ArkextDaoService arkextDaoService, KofastDaoServices kofastDaoServices, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.arktxtDaoService = arktxtDaoService;
		this.arkextDaoService = arkextDaoService;
		this.kofastDaoServices = kofastDaoServices;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(ArktxtDao dao, String user, String mode) {
		boolean retval = true;
		StringBuilder arkvedResult = new StringBuilder();
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			if ((dao.getArtype() != null && !"".equals(dao.getArtype())) && (dao.getArtxt() != null && !"".equals(dao.getArtxt()))) {
				//Check exist
				if ("A".equals(mode) && exist(dao)) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,messageSourceHelper.getMessage("systema.bcore.kunderegister.arktxt.error.artype.exist",new Object[] { dao.getArtype() }),"error", dbErrors));
					retval = false;
				}
				//Mappe
				if ((dao.getArklag() != null && !"".equals(dao.getArklag())) && !existInArkext(dao.getArklag())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,messageSourceHelper.getMessage("systema.bcore.kunderegister.arktxt.error.arklag",new Object[] { dao.getArklag() }),"error", dbErrors));
					retval = false;
				}
				//Vedlegg
				if (hasValue(dao.getArkved()) && !existInArktxt(dao.getArkved(),arkvedResult)) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,messageSourceHelper.getMessage("systema.bcore.kunderegister.arktxt.error.arkved",new Object[] { arkvedResult }),"error", dbErrors));
					retval = false;				
				}
				//Opplastningsbane
				if (hasValue(dao.getArsban()) && !existInKofast(dao.getArsban())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,messageSourceHelper.getMessage("systema.bcore.kunderegister.arktxt.error.arsban",new Object[] { dao.getArsban() }),"error", dbErrors));
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

	private boolean hasValue(String arkved) {
		if (arkved != null) {
			arkved = arkved.trim();
			if (arkved.length() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	private boolean existInKofast(String arsban) {
		boolean	exists = this.kofastDaoServices.exists(FasteKoder.ARKIVU, arsban, dbErrors);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean existInArktxt(String arkved, StringBuilder arkvedResult) {
		boolean exists = true;
		List<String> arkvedList = splitEqually(arkved, 2);
		ArktxtDao qDao = null;
		for (String value : arkvedList) {
			value = value.trim();
			if (value.length() > 0) {
				qDao = new ArktxtDao();
				qDao.setArtype(value);
				if (exist(qDao)) {
					exists = true;
				} else {
					exists = false;
					arkvedResult.append(" " + value);
				}
			} 
		}
		if (!exists) {
			return false;
		} else {
			return true;
		}

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

	private boolean exist(ArktxtDao dao) {
		boolean exists = arktxtDaoService.exist(dao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	private List<String> splitEqually(String text, int size) {
		List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);
		for (int start = 0; start < text.length(); start += size) {
			ret.add(text.substring(start, Math.min(text.length(), start + size)));
		}
		
		return ret;
	}	
}
