package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import lombok.NonNull;
import no.systema.jservices.common.dao.SvlthDao;
import no.systema.jservices.common.dao.services.SvlthDaoService;
import no.systema.jservices.common.dao.services.Svtx03fDaoService;
import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.common.util.StringUtils;
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
	private Svtx03fDaoService svtx03fDaoService = null;

	
	public SVLTH_U(HttpServletRequest request, SvlthDaoService svlthDaoService,Svtx03fDaoService svtx03fDaoService, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
		this.svlthDaoService = svlthDaoService;
		this.svtx03fDaoService = svtx03fDaoService;
	}
	
	public boolean isValidInput(SvlthDao dao, String user){
		boolean retval = true;
		boolean isInlagg = dao.getSvlth_h().equals(EventTypeEnum.INLAGG.getValue());
		boolean isUttag = dao.getSvlth_h().equals(EventTypeEnum.UTTAG.getValue());

		if (isInlagg) {
			if (svlthDaoService.exist(EventTypeEnum.INLAGG, dao.getSvlth_irn())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.exist", new Object[] { EventTypeEnum.INLAGG, dao.getSvlth_irn()}), "error", dbErrors));
				retval = false;					
			}			
			if (!validateMrnLenght(dao.getSvlth_irn())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.mrn.invalid.lenght", new Object[] {  dao.getSvlth_irn()}), "error", dbErrors));
				retval = false;					
			}
			if (!svtx03fDaoService.kollislagExist(dao.getSvlth_isl())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.svlth_isl.exist",new Object[] { dao.getSvlth_isl() }),"error", dbErrors));
				retval = false;
			}
			
			if (!validDateFormat(dao.getSvlth_id2())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.svlth_id2.invalid",new Object[] { dao.getSvlth_id2() }),"error", dbErrors));
				retval = false;
			}
			
			
			
		}
		if (isUttag) {
			if (!svlthDaoService.validUttagQuantity(dao.getSvlth_unt(), dao.getSvlth_irn())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.invalid.quantity", new Object[] { dao.getSvlth_unt(), dao.getSvlth_irn()}), "error", dbErrors));
				retval = false;					
			}			
			
		}
		
		return retval;

	}

	private boolean validDateFormat(@NonNull Integer svlth_id2) {
		return DateTimeManager.isValidDate(svlth_id2.toString());
	}

	private boolean validateMrnLenght(@NonNull String svlth_irn) {
		return svlth_irn.length() == 18;
	}

}
