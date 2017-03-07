package no.systema.jservices.bcore.z.maintenance.controller.rules;

import java.util.List;

import org.apache.log4j.Logger;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KofastDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.dto.SyparfDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Mar 6, 2017
 */
public class SYPARF_U {
	private static Logger logger = Logger.getLogger(SYPARF_U.class.getName());
	private JsonResponseWriter2<SyparfDto> jsonWriter = new JsonResponseWriter2<SyparfDto>();
	private MessageSourceHelper messageSourceHelper = new MessageSourceHelper();
	private KofastDaoServices kofastDaoServices = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public SYPARF_U(KofastDaoServices kofastDaoServices, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		this.kofastDaoServices = kofastDaoServices;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(SyparfDto dto, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dto
			if ( (dto.getSypaid() != null && !"".equals(dto.getSypaid()) ) 
				&& (dto.getSyvrdn() != null && !"".equals(dto.getSyvrdn()) )  
				&& (dto.getSyvrda() != null && !"".equals(dto.getSyvrda()) ))  {
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
			// check dao
			if ( (dto.getSypaid() != null && !"".equals(dto.getSypaid()) ) 
					&& (dto.getSyvrdn() != null && !"".equals(dto.getSyvrdn()) )  
					&& (dto.getSyvrda() != null && !"".equals(dto.getSyvrda()) ))  {
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

	
}
