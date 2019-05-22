package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import lombok.NonNull;
import no.systema.jservices.common.dao.SvlthDao;
import no.systema.jservices.common.dao.SvltuDao;
import no.systema.jservices.common.dao.services.SvlthDaoService;
import no.systema.jservices.common.dao.services.SvltuDaoService;
import no.systema.jservices.common.dao.services.Svtx03fDaoService;
import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.common.values.EventTypeEnum;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.main.util.MessageSourceHelper;
/**
 * 
 * @author fredrikmoller
 * @date 2019-04-01
 */
public class SVLTU_U {
	private static Logger logger = Logger.getLogger(SVLTH_U.class.getName());
	private JsonResponseWriter jsonWriter = new JsonResponseWriter();
	private MessageSourceHelper messageSourceHelper = null;
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;
	private SvltuDaoService svltuDaoService = null;

	
	public SVLTU_U(HttpServletRequest request, SvltuDaoService svltuDaoService, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
		this.svltuDaoService = svltuDaoService;
	}
	
	public boolean isValidInput(SvltuDao dao, String user){
		boolean retval = true;

//		if (svltuDaoService.exist(dao.getSvltu_igl(),  dao.getSvltu_ign(), dao.getSvltu_pos())) {
//			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
//					messageSourceHelper.getMessage("systema.bcore.accounting.error.exist", new Object[] {dao.getSvltu_ign(), dao.getSvltu_pos()}), "error", dbErrors));
//			retval = false;					
//		}			
			
		return retval;

	}

	
	
}
