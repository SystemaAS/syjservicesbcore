package no.systema.jservices.bcore.z.maintenance.controller.rules;

import org.apache.log4j.Logger;

import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.CundcDto;
import no.systema.jservices.model.dao.services.CundcDaoServices;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Nov 7, 2016
 */
public class CUNDC_U {
	private static Logger logger = Logger.getLogger(CUNDC_U.class.getName());
	private JsonResponseWriter jsonWriter = new JsonResponseWriter();
	private MessageSourceHelper messageSourceHelper = new MessageSourceHelper();
	private CundcDaoServices cundcDaoServices = null;
	private KofastDaoServices kofastDaoServices = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public CUNDC_U(CundcDaoServices cundcDaoServices, KofastDaoServices kofastDaoServices, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		this.cundcDaoServices = cundcDaoServices;
		this.kofastDaoServices = kofastDaoServices;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(CundcDto dto, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dto
			if ( (dto.getCfirma() != null && !"".equals(dto.getCfirma())) && (dto.getCcompn() != null && !"".equals(dto.getCcompn()))
					&& (dto.getCconta() != null && !"".equals(dto.getCconta())) && (dto.getCtype() != null && !"".equals(dto.getCtype())) ) {
				// Check duplicate
				if ("A".equals(mode)  &&  existInCundc(user, dto.getCfirma(), dto.getCcompn(), dto.getCconta(), dto.getCtype())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.cconta", new Object[] { dto.getCconta(), dto.getCcompn() }), "error", dbErrors));
					retval = false;
				}
				// Check funksjon (if prefixed with *)
				if (dto.getCtype() != null && !"".equals(dto.getCtype())) {
					if (dto.getCtype().startsWith("*") && !existInKofast(user, dto.getCtype())) {
						errors.append(jsonWriter.setJsonSimpleErrorResult(user,
								messageSourceHelper.getMessage("systema.bcore.kunderegister.kontaktpersoner.error.ctype", new Object[] { dto.getCtype() }), "error", dbErrors));
						retval = false;
					}
				}

			} else {
				retval = false;
			}
		} else {
			retval = false;
		}

		return retval;
	}


	public boolean isValidInputForDelete(CundcDto dto, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ( (dto.getCfirma() != null && !"".equals(dto.getCfirma())) && (dto.getCcompn() != null && !"".equals(dto.getCcompn()))
					&& (dto.getCconta() != null && !"".equals(dto.getCconta())) && (dto.getCtype() != null && !"".equals(dto.getCtype())) ) {
				// OK
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}

	private boolean existInCundc(String userName, String cfirma, String ccompn, String cconta, String ctype) {
		boolean exists = this.cundcDaoServices.exists(cfirma, ccompn, cconta, ctype ,dbErrors);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean existInKofast(String userName, String ctype) {
		boolean exists = this.kofastDaoServices.exists(FasteKoder.FUNKSJON, ctype.substring(1), dbErrors);
		if (!exists) {
			return false;
		} else {
			return true;
		}

	}

}
