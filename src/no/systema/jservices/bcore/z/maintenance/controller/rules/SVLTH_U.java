package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import no.systema.jservices.common.dao.SvlthDao;
import no.systema.jservices.common.dao.services.SvlthDaoService;
import no.systema.jservices.common.values.EventTypeEnum;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.main.util.MessageSourceHelper;
/**
 * 
 * @author fredrikmoller
 * @date 2019-04-01
 */
public class SVLTH_U {
	private static Logger logger = Logger.getLogger(SVLTH_U.class.getName());
	private JsonResponseWriter jsonWriter = new JsonResponseWriter();
	private MessageSourceHelper messageSourceHelper = null;
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;
	private SvlthDaoService svlthDaoService = null;

	
	public SVLTH_U(HttpServletRequest request, SvlthDaoService svlthDaoService,StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
		this.svlthDaoService = svlthDaoService;
	}
	
	public boolean isValidInput(SvlthDao dao, String user){
		boolean retval = true;
		boolean isInlagg = dao.getSvlth_h().equals(EventTypeEnum.INLAGG.getValue());
		if (isInlagg  && svlthDaoService.exist(EventTypeEnum.INLAGG, dao.getSvlth_irn(), dao.getSvlth_id2())) {
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.accounting.error.exist", new Object[] { EventTypeEnum.INLAGG, dao.getSvlth_irn(), dao.getSvlth_id2()}), "error", dbErrors));
			retval = false;					
		}	

		return retval;

	}

}
