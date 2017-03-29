package no.systema.jservices.bcore.z.maintenance.controller.rules;

import java.util.List;

import org.apache.log4j.Logger;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KofastDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.dao.SadvareDao;
import no.systema.jservices.common.dto.SyparfDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Mar 27, 2017
 */
public class SADVARE_U {
	private static Logger logger = Logger.getLogger(SADVARE_U.class.getName());
	private JsonResponseWriter2<SadvareDao> jsonWriter = new JsonResponseWriter2<SadvareDao>();
	private MessageSourceHelper messageSourceHelper = new MessageSourceHelper();
	private KofastDaoServices kofastDaoServices = null;  //TODO Some services
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public SADVARE_U(KofastDaoServices kofastDaoServices, StringBuffer sb, StringBuffer dbErrorStackTrace) {
		this.kofastDaoServices = kofastDaoServices;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(SadvareDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ( (dao.getLevenr() != null && !"".equals(dao.getLevenr()) ) 
				&& (dao.getVarenr() != null && !"".equals(dao.getVarenr()) )  	
				&& (dao.getVarebe() != null && !"".equals(dao.getVarebe()) ) ) {
				
/*   //TODO
				if ("U".equals(mode) && (dto.getSyrecn() == null && "".equals(dto.getSyrecn())) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.mandatory", null), "error", dbErrors));
					retval = false;
				}
				
				if (!existInKofast(dto.getSypaid())) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.params.error.sypaid", new Object[] { dto.getSypaid() }), "error",
							dbErrors));
					retval = false;
				}
*/				
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}

		return retval;
	}



	public boolean isValidInputForDelete(SadvareDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ( (dao.getVarenr() != null && !"".equals(dao.getVarenr()) ) 
					&& (dao.getLevenr() != null && !"".equals(dao.getLevenr()) )) { 
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

	/**
	 * Adding 0 to empty Integer and replacing . with , when Float.
	 * 
	 * @param dao
	 */
	public void updateNumericFieldsIfNull(SadvareDao dao) {
		//TODO Add replace when float
		String ZERO = "0";
		if(dao.getW2belt()==null || "".equals(dao.getW2belt())){
			dao.setW2belt(ZERO);
		}
		if(dao.getW2vktb()==null || "".equals(dao.getW2vktb())){
			dao.setW2vktb(ZERO);
		}		
		if(dao.getW2vktn()==null || "".equals(dao.getW2vktn())){
			dao.setW2vktn(ZERO);
		}
		
		if(dao.getW2as()==null || "".equals(dao.getW2as())){
			dao.setW2as(ZERO);
		}
		if(dao.getW2asa1()==null || "".equals(dao.getW2asa1())){
			dao.setW2asa1(ZERO);
		}
		if(dao.getW2agr1()==null || "".equals(dao.getW2agr1())){
			dao.setW2agr1(ZERO);
		}
		if(dao.getW2abl1()==null || "".equals(dao.getW2abl1())){
			dao.setW2abl1(ZERO);
		}
		if(dao.getW2bel()==null || "".equals(dao.getW2bel())){
			dao.setW2bel(ZERO);
		}
		if(dao.getW2asa2()==null || "".equals(dao.getW2asa2())){
			dao.setW2asa2(ZERO);
		}
		if(dao.getW2agr2()==null || "".equals(dao.getW2agr2())){
			dao.setW2agr2(ZERO);
		}
		if(dao.getW2abl2()==null || "".equals(dao.getW2abl2())){
			dao.setW2abl2(ZERO);
		}
		if(dao.getW2pros()==null || "".equals(dao.getW2pros())){
			dao.setW2pros(ZERO);
		}
		if(dao.getW2asa3()==null || "".equals(dao.getW2asa3())){
			dao.setW2asa3(ZERO);
		}
		if(dao.getW2agr3()==null || "".equals(dao.getW2agr3())){
			dao.setW2agr3(ZERO);
		}
		if(dao.getW2abl3()==null || "".equals(dao.getW2abl3())){
			dao.setW2abl3(ZERO);
		}
		if(dao.getW2asa4()==null || "".equals(dao.getW2asa4())){
			dao.setW2asa4(ZERO);
		}
		if(dao.getW2agr4()==null || "".equals(dao.getW2agr4())){
			dao.setW2agr4(ZERO);
		}
		if(dao.getW2abl4()==null || "".equals(dao.getW2abl4())){
			dao.setW2abl4(ZERO);
		}
		if(dao.getW2beln()==null || "".equals(dao.getW2beln())){
			dao.setW2beln(ZERO);
		}
		if(dao.getW2asa5()==null || "".equals(dao.getW2asa5())){
			dao.setW2asa5(ZERO);
		}
		if(dao.getW2agr5()==null || "".equals(dao.getW2agr5())){
			dao.setW2agr5(ZERO);
		}
		if(dao.getW2abl5()==null || "".equals(dao.getW2abl5())){
			dao.setW2abl5(ZERO);
		}
		if(dao.getW2asa6()==null || "".equals(dao.getW2asa6())){
			dao.setW2asa6(ZERO);
		}
		if(dao.getW2agr6()==null || "".equals(dao.getW2agr6())){
			dao.setW2agr6(ZERO);
		}
		if(dao.getW2abl6()==null || "".equals(dao.getW2abl6())){
			dao.setW2abl6(ZERO);
		}
		if(dao.getW2asa7()==null || "".equals(dao.getW2asa7())){
			dao.setW2asa7(ZERO);
		}
		if(dao.getW2agr7()==null || "".equals(dao.getW2agr7())){
			dao.setW2agr7(ZERO);
		}
		if(dao.getW2abl7()==null || "".equals(dao.getW2abl7())){
			dao.setW2abl7(ZERO);
		}
		if(dao.getW2asa8()==null || "".equals(dao.getW2asa8())){
			dao.setW2asa8(ZERO);
		}
		if(dao.getW2agr8()==null || "".equals(dao.getW2agr8())){
			dao.setW2agr8(ZERO);
		}
		if(dao.getW2nt01()==null || "".equals(dao.getW2nt01())){
			dao.setW2nt01(ZERO);
		}
		if(dao.getW2nt02()==null || "".equals(dao.getW2nt02())){
			dao.setW2nt02(ZERO);
		}
		if(dao.getW2nt03()==null || "".equals(dao.getW2nt03())){
			dao.setW2nt03(ZERO);
		}
		if(dao.getW2nt04()==null || "".equals(dao.getW2nt04())){
			dao.setW2nt04(ZERO);
		}
		if(dao.getW2nt05()==null || "".equals(dao.getW2nt05())){
			dao.setW2nt05(ZERO);
		}
		if(dao.getW2nt06()==null || "".equals(dao.getW2nt06())){
			dao.setW2nt06(ZERO);
		}
		if(dao.getW2nt07()==null || "".equals(dao.getW2nt07())){
			dao.setW2nt07(ZERO);
		}
		if(dao.getW2ntm()==null || "".equals(dao.getW2ntm())){
			dao.setW2ntm(ZERO);
		}		
		if(dao.getW2abl8()==null || "".equals(dao.getW2abl8())){
			dao.setW2abl8(ZERO);
		}
		
	}

	
}
