package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.dao.SviwDao;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.main.util.MessageSourceHelper;

//TODO

/**
 * 
 * @author Fredrik Möller
 * @date Mai 19, 2017
 */
public class SVIW_U {
	private static Logger logger = Logger.getLogger(SVIW_U.class.getName());
	private JsonResponseWriter2<SviwDao> jsonWriter = new JsonResponseWriter2<SviwDao>();
	private MessageSourceHelper messageSourceHelper = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public SVIW_U(HttpServletRequest request, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(SviwDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ( dao.getSviw_knnr() > 0 && StringUtils.hasValue(dao.getSviw_knso())) 	{
				
//				if ("U".equals(mode) && (dao.getSyrecn() == null && "".equals(dao.getSyrecn())) ) {
//					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
//							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.mandatory", null), "error", dbErrors));
//					retval = false;
//				}
				
				
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

	public void updateNumericFieldsIfNull(SviwDao dao) {
		String ZERO = "0";
//		if(dao.getSysort()==null || "".equals(dao.getSysort())){
//			dao.setSysort(ZERO);
//		}
//		if(dao.getSyavd()==null || "".equals(dao.getSyavd())){
//			dao.setSyavd(ZERO);
//		}		
//		if(dao.getSyvrdn()==null || "".equals(dao.getSyvrdn())){
//			dao.setSyvrdn(ZERO);
//		}
	}

	
}
