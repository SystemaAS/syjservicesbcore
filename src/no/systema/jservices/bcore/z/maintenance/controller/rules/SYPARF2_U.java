package no.systema.jservices.bcore.z.maintenance.controller.rules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.*;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KofastDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.dto.SyparfDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author oscardelatorre
 * @date May 9, 2017
 */
public class SYPARF2_U {
	private static Logger logger = LogManager.getLogger(SYPARF2_U.class.getName());
	private JsonResponseWriter2<SyparfDto> jsonWriter = new JsonResponseWriter2<SyparfDto>();
	private MessageSourceHelper messageSourceHelper = null;
	private KofastDaoServices kofastDaoServices = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public SYPARF2_U(HttpServletRequest request, KofastDaoServices kofastDaoServices, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.kofastDaoServices = kofastDaoServices;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(SyparfDto dto, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dto
			if ( (dto.getSypaid() != null && !"".equals(dto.getSypaid()) ) 
				&& (dto.getSyuser() != null && !"".equals(dto.getSyuser()) )  	
				&& (dto.getSyvrdn() != null && !"".equals(dto.getSyvrdn()) )  
				&& (dto.getSyvrda() != null && !"".equals(dto.getSyvrda()) ))  {
				
				if ("U".equals(mode) && (dto.getSyrecn() == null && "".equals(dto.getSyrecn())) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.saksbehandlere.user.error.mandatory", null), "error", dbErrors));
					retval = false;
				}
				
				if (!existInKofast(dto.getSypaid())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.params.error.sypaid", new Object[] { dto.getSypaid() }), "error",
							dbErrors));
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



	public boolean isValidInputForDelete(SyparfDto dto, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dto
			if ( (dto.getSyuser() != null && !"".equals(dto.getSyuser()) ) 
					&& (dto.getSyrecn() != null && !"".equals(dto.getSyrecn()) )) { 
				// OK
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}

	private boolean existInKofast(String value) {
		List<KofastDao> kofastList = kofastDaoServices.findById(FasteKoder.SYPAR, value, dbErrors);
		if (kofastList != null && kofastList.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void updateNumericFieldsIfNull(SyparfDto dto) {
		String ZERO = "0";
		if(dto.getSysort()==null || "".equals(dto.getSysort())){
			dto.setSysort(ZERO);
		}
		if(dto.getSyavd()==null || "".equals(dto.getSyavd())){
			dto.setSyavd(ZERO);
		}		
		if(dto.getSyvrdn()==null || "".equals(dto.getSyvrdn())){
			dto.setSyvrdn(ZERO);
		}
	}

	
}
