package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import no.systema.jservices.common.dao.VadrDao;
import no.systema.jservices.common.dao.services.KodtlkDaoService;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.main.util.MessageSourceHelper;
/**
 * 
 * @author fredrikmoller
 * @date 2019-03-26
 */
public class VADR_U {

	private JsonResponseWriter jsonWriter = new JsonResponseWriter();
	private MessageSourceHelper messageSourceHelper = null;
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;
	private KodtlkDaoService kodtlkDaoService = null;

	
	public VADR_U(HttpServletRequest request, KodtlkDaoService kodtlkDaoService,StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
		this.kodtlkDaoService = kodtlkDaoService;
	}
	
	public boolean isValidInput(VadrDao dao, String user){
		boolean retval = true;
		if ( StringUtils.hasValue(dao.getValand())  && !existInKodtlk(dao.getValand())) {
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.valand", new Object[] { dao.getValand()}), "error", dbErrors));
			retval = false;					
		}	

		return retval;

	}

	private boolean existInKodtlk(String syland) {
		boolean exists = kodtlkDaoService.landKodeExist(syland);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}		
	
}
