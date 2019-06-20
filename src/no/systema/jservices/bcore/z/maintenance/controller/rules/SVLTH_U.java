package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

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
		boolean isRattelse = dao.getSvlth_h().equals(EventTypeEnum.RATTELSE.getValue());

		if (isInlagg) {
			if (svlthDaoService.exist(EventTypeEnum.INLAGG, dao.getSvlth_ign(), dao.getSvlth_pos())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.exist", new Object[] {dao.getSvlth_ign(), dao.getSvlth_pos()}), "error", dbErrors));
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
			if (!svlthDaoService.validUttagQuantity(dao.getSvlth_unt(), dao.getSvlth_ign(), dao.getSvlth_pos())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.invalid.quantity", new Object[] { dao.getSvlth_unt(), dao.getSvlth_ign(), dao.getSvlth_pos()}), "error", dbErrors));
				retval = false;					
			}		
			if (!validateTullidLenght(dao.getSvlth_uti())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.svlth_uti.invalid.length",new Object[] { dao.getSvlth_uti() }),"error", dbErrors));
				retval = false;
			}
			
		}
		if (isRattelse) {
			if (StringUtils.hasValue(dao.getSvlth_rsl())  && !svtx03fDaoService.kollislagExist(dao.getSvlth_rsl())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.svlth_isl.exist",new Object[] { dao.getSvlth_rsl() }),"error", dbErrors));
				retval = false;
			}
			if (!validateTullidLenght(dao.getSvlth_ruti())) {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.accounting.error.svlth_uti.invalid.length",new Object[] { dao.getSvlth_ruti() }),"error", dbErrors));
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

	private boolean validateTullidLenght(@NonNull String svlth_uti) {
		return svlth_uti.length() >= 10;
	}	
	
	
}
