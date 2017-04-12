package no.systema.jservices.bcore.z.maintenance.controller.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
	private static Logger logger = Logger.getLogger(ARKTXT_U.class.getName());
	private JsonResponseWriter2<ArktxtDto> jsonWriter = new JsonResponseWriter2<ArktxtDto>();
	private MessageSourceHelper messageSourceHelper = new MessageSourceHelper();
	private ArktxtDaoService arktxtDaoService = null;
	private ArkextDaoService arkextDaoService = null;
	private KofastDaoServices kofastDaoServices = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public ARKTXT_U(ArktxtDaoService arktxtDaoService, ArkextDaoService arkextDaoService, KofastDaoServices kofastDaoServices, StringBuffer sb, StringBuffer dbErrorStackTrace) {
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
				if (dao.getArkved() != null && !"".equals(dao.getArkved()) && !existInKofast(dao.getArkved(),arkvedResult)) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,messageSourceHelper.getMessage("systema.bcore.kunderegister.arktxt.error.arkved",new Object[] { arkvedResult }),"error", dbErrors));
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


	private boolean existInKofast(String arkved, StringBuilder arkvedResult) {
		boolean exists = false;
		List<String> arkvedList = splitEqually(arkved, 2);
		List list = null;
		for (String value : arkvedList) {
			value = value.trim();
			if (value.length() > 0) {
				list = kofastDaoServices.findById(FasteKoder.ARKIVU, value, dbErrors);
				if (list != null && list.size() > 0) {
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
	
	private List<String> splitEqually(String text, int size) {
		List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);
		for (int start = 0; start < text.length(); start += size) {
			ret.add(text.substring(start, Math.min(text.length(), start + size)));
		}

		return ret;
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
