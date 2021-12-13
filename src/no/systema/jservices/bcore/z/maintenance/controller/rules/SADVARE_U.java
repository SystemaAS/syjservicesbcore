package no.systema.jservices.bcore.z.maintenance.controller.rules;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.*;

import no.systema.jservices.common.dao.Kodts5Dao;
import no.systema.jservices.common.dao.Kodts8Dao;
import no.systema.jservices.common.dao.KodtsaDao;
import no.systema.jservices.common.dao.KodtsbDao;
import no.systema.jservices.common.dao.SadvareDao;
import no.systema.jservices.common.dao.TariDao;
import no.systema.jservices.common.dao.services.Kodts2DaoService;
import no.systema.jservices.common.dao.services.Kodts5DaoService;
import no.systema.jservices.common.dao.services.Kodts6DaoService;
import no.systema.jservices.common.dao.services.Kodts7DaoService;
import no.systema.jservices.common.dao.services.Kodts8DaoService;
import no.systema.jservices.common.dao.services.KodtsaDaoService;
import no.systema.jservices.common.dao.services.KodtsbDaoService;
import no.systema.jservices.common.dao.services.KodtvalfDaoService;
import no.systema.jservices.common.dao.services.TariDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.main.util.MessageSourceHelper;

/**
 * 
 * @author Fredrik Möller
 * @date Mar 27, 2017
 */
public class SADVARE_U {
	private static Logger logger = LogManager.getLogger(SADVARE_U.class.getName());
	private JsonResponseWriter2<SadvareDao> jsonWriter = new JsonResponseWriter2<SadvareDao>();
	private MessageSourceHelper messageSourceHelper = null;
	private Kodts7DaoService kodts7DaoService = null;
	private Kodts2DaoService kodts2DaoService = null;
	private Kodts5DaoService kodts5DaoService = null;
	private Kodts6DaoService kodts6DaoService = null;
	private Kodts8DaoService kodts8DaoService = null;
	private KodtsaDaoService kodtsaDaoService = null;
	private KodtsbDaoService kodtsbDaoService = null;
	private KodtvalfDaoService kodtvalfDaoService = null;
	private TariDaoService tariDaoService = null;

	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public SADVARE_U(HttpServletRequest request, Kodts7DaoService kodts7DaoService, Kodts2DaoService kodts2DaoService,
			TariDaoService tariDaoService, Kodts5DaoService kodts5DaoService, Kodts6DaoService kodts6DaoService,
			Kodts8DaoService kodts8DaoService, KodtsaDaoService kodtsaDaoService, KodtsbDaoService kodtsbDaoService, KodtvalfDaoService kodtvalfDaoService, StringBuffer sb,
			StringBuffer dbErrorStackTrace) {
		messageSourceHelper = new MessageSourceHelper(request);
		this.kodts7DaoService = kodts7DaoService;
		this.kodts2DaoService = kodts2DaoService;
		this.kodts5DaoService = kodts5DaoService;
		this.kodts6DaoService = kodts6DaoService;
		this.kodts8DaoService = kodts8DaoService;
		this.kodtsaDaoService = kodtsaDaoService;
		this.kodtsbDaoService = kodtsbDaoService;
		this.kodtvalfDaoService = kodtvalfDaoService;
		this.tariDaoService = tariDaoService;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	public boolean isValidInput(SadvareDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			if ((dao.getLevenr() != null && !"".equals(dao.getLevenr()))
					&& (dao.getVarenr() != null && !"".equals(dao.getVarenr()))
					&& (dao.getVarebe() != null && !"".equals(dao.getVarebe()))) {
				//Verdi fast.
				if ( (dao.getW2vf() != null && !"".equals(dao.getW2vf()) ) &&  !existInKodts7(dao.getW2vf()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.w2vf",new Object[] { dao.getW2vf() }),"error", dbErrors));
					retval = false;
				}
				//Land
				if ( (dao.getW2lk() != null && !"".equals(dao.getW2lk()) ) &&  !existInKodts2(dao.getW2lk()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.w2lk",new Object[] { dao.getW2lk() }),"error", dbErrors));
					retval = false;
				}
				//Varenr
				if ( (dao.getW2vnti() != null && !"".equals(dao.getW2vnti()) ) &&  !existInTari(dao.getW2vnti()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.w2vnti",new Object[] { dao.getW2vnti() }),"error", dbErrors));
					retval = false;
				}			
				//Tollned
				if ( (dao.getW2tn() != null && !"".equals(dao.getW2tn()) ) &&  !existInKodts5(dao.getW2tn()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.w2tn",new Object[] { dao.getW2tn() }),"error", dbErrors));
					retval = false;
				}	
				//Preferense
				if ( (dao.getW2pre() != null && !"".equals(dao.getW2pre()) ) &&  !existInKodts6(dao.getW2pre()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.w2pre",new Object[] { dao.getW2pre() }),"error", dbErrors));
					retval = false;
				}	
				//Enhet 1..7
				if ( (dao.getW2eh01() != null && !"".equals(dao.getW2eh01()) ) &&  !existInKodtsa(dao.getW2eh01()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.enhet",new Object[] { dao.getW2eh01() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2eh02() != null && !"".equals(dao.getW2eh02()) ) &&  !existInKodtsa(dao.getW2eh02()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.enhet",new Object[] { dao.getW2eh02() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2eh03() != null && !"".equals(dao.getW2eh03()) ) &&  !existInKodtsa(dao.getW2eh03()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.enhet",new Object[] { dao.getW2eh03() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2eh04() != null && !"".equals(dao.getW2eh04()) ) &&  !existInKodtsa(dao.getW2eh04()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.enhet",new Object[] { dao.getW2eh04() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2eh05() != null && !"".equals(dao.getW2eh05()) ) &&  !existInKodtsa(dao.getW2eh05()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.enhet",new Object[] { dao.getW2eh05() }),"error", dbErrors));
					retval = false;
				}	if ( (dao.getW2eh06() != null && !"".equals(dao.getW2eh06()) ) &&  !existInKodtsa(dao.getW2eh06()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.enhet",new Object[] { dao.getW2eh06() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2eh07() != null && !"".equals(dao.getW2eh07()) ) &&  !existInKodtsa(dao.getW2eh07()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.enhet",new Object[] { dao.getW2eh07() }),"error", dbErrors));
					retval = false;
				}	
				//Avgkode 1..8  //sekvens(asv) valideras i espedsg validator
				if ( (dao.getW2akd1() != null && !"".equals(dao.getW2akd1()) ) &&  !existInKodts8(dao.getW2akd1(), dao.getW2asv1()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.avgkode",new Object[] { dao.getW2akd1(), dao.getW2asv1() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2akd2() != null && !"".equals(dao.getW2akd2()) ) &&  !existInKodts8(dao.getW2akd2(), dao.getW2asv2()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.avgkode",new Object[] { dao.getW2akd2(), dao.getW2asv2() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2akd3() != null && !"".equals(dao.getW2akd3()) ) &&  !existInKodts8(dao.getW2akd3(), dao.getW2asv3()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.avgkode",new Object[] { dao.getW2akd3(), dao.getW2asv3() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2akd4() != null && !"".equals(dao.getW2akd4()) ) &&  !existInKodts8(dao.getW2akd4(), dao.getW2asv4()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.avgkode",new Object[] { dao.getW2akd4(), dao.getW2asv4() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2akd5() != null && !"".equals(dao.getW2akd5()) ) &&  !existInKodts8(dao.getW2akd5(), dao.getW2asv5()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.avgkode",new Object[] { dao.getW2akd5(), dao.getW2asv5() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2akd6() != null && !"".equals(dao.getW2akd6()) ) &&  !existInKodts8(dao.getW2akd6(), dao.getW2asv6()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.avgkode",new Object[] { dao.getW2akd6(), dao.getW2asv6() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2akd7() != null && !"".equals(dao.getW2akd7()) ) &&  !existInKodts8(dao.getW2akd7(), dao.getW2asv7()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.avgkode",new Object[] { dao.getW2akd7(), dao.getW2asv7() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2akd8() != null && !"".equals(dao.getW2akd8()) ) &&  !existInKodts8(dao.getW2akd8(), dao.getW2asv8()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.avgkode",new Object[] { dao.getW2akd8(), dao.getW2asv8() }),"error", dbErrors));
					retval = false;
				}	
				//Kommref 1..10
				if ( (dao.getW2cre1() != null && !"".equals(dao.getW2cre1()) ) &&  !existInKodtsb(dao.getW2cre1()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre1() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2cre2() != null && !"".equals(dao.getW2cre2()) ) &&  !existInKodtsb(dao.getW2cre2()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre2() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2cre3() != null && !"".equals(dao.getW2cre3()) ) &&  !existInKodtsb(dao.getW2cre3()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre3() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2cre4() != null && !"".equals(dao.getW2cre4()) ) &&  !existInKodtsb(dao.getW2cre4()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre4() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2cre5() != null && !"".equals(dao.getW2cre5()) ) &&  !existInKodtsb(dao.getW2cre5()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre5() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2cre6() != null && !"".equals(dao.getW2cre6()) ) &&  !existInKodtsb(dao.getW2cre6()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre6() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2cre7() != null && !"".equals(dao.getW2cre7()) ) &&  !existInKodtsb(dao.getW2cre7()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre7() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2cre8() != null && !"".equals(dao.getW2cre8()) ) &&  !existInKodtsb(dao.getW2cre8()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre8() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2cre9() != null && !"".equals(dao.getW2cre9()) ) &&  !existInKodtsa(dao.getW2cre9()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre9() }),"error", dbErrors));
					retval = false;
				}	
				if ( (dao.getW2cre10() != null && !"".equals(dao.getW2cre10()) ) &&  !existInKodtsb(dao.getW2cre10()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.kommref",new Object[] { dao.getW2cre10() }),"error", dbErrors));
					retval = false;
				}	
				//Valutakod
				if ( (dao.getW2val() != null && !"".equals(dao.getW2val()) ) &&  !existInKodtvalf(dao.getW2val()) ) {
					errors.append(jsonWriter.setJsonSimpleErrorResult(user,
							messageSourceHelper.getMessage("systema.bcore.kunderegister.sadvare.error.w2val",new Object[] { dao.getW2val() }),"error", dbErrors));
					retval = false;
				}	
				
				
			} else {
				errors.append(jsonWriter.setJsonSimpleErrorResult(user,
						messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.mandatory", null),"error", dbErrors));
				retval = false;
			}
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					messageSourceHelper.getMessage("systema.bcore.kunderegister.kunde.error.mandatory", null),"error", dbErrors));
			retval = false;
		}

		return retval;
	}


	public boolean isValidInputForDelete(SadvareDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ((dao.getVarenr() != null && !"".equals(dao.getVarenr()))
					&& (dao.getLevenr() != null && !"".equals(dao.getLevenr()))) {
				// OK
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}

	/**
	 * Adding 0 to empty Integer and replacing . with , when Float.
	 * 
	 * @param dao
	 */
	public void updateNumericFieldsIfNull(SadvareDao dao) {
		// TODO Add replace when float
		String ZERO = "0";
		if (dao.getW2belt() == null || "".equals(dao.getW2belt())) {
			dao.setW2belt(ZERO);
		}
		if (dao.getW2vktb() == null || "".equals(dao.getW2vktb())) {
			dao.setW2vktb(ZERO);
		}
		if (dao.getW2vktn() == null || "".equals(dao.getW2vktn())) {
			dao.setW2vktn(ZERO);
		}

		if (dao.getW2as() == null || "".equals(dao.getW2as())) {
			dao.setW2as(ZERO);
		}
		if (dao.getW2asa1() == null || "".equals(dao.getW2asa1())) {
			dao.setW2asa1(ZERO);
		}
		if (dao.getW2agr1() == null || "".equals(dao.getW2agr1())) {
			dao.setW2agr1(ZERO);
		}
		if (dao.getW2abl1() == null || "".equals(dao.getW2abl1())) {
			dao.setW2abl1(ZERO);
		}
		if (dao.getW2bel() == null || "".equals(dao.getW2bel())) {
			dao.setW2bel(ZERO);
		}
		if (dao.getW2asa2() == null || "".equals(dao.getW2asa2())) {
			dao.setW2asa2(ZERO);
		}
		if (dao.getW2agr2() == null || "".equals(dao.getW2agr2())) {
			dao.setW2agr2(ZERO);
		}
		if (dao.getW2abl2() == null || "".equals(dao.getW2abl2())) {
			dao.setW2abl2(ZERO);
		}
		if (dao.getW2pros() == null || "".equals(dao.getW2pros())) {
			dao.setW2pros(ZERO);
		}
		if (dao.getW2asa3() == null || "".equals(dao.getW2asa3())) {
			dao.setW2asa3(ZERO);
		}
		if (dao.getW2agr3() == null || "".equals(dao.getW2agr3())) {
			dao.setW2agr3(ZERO);
		}
		if (dao.getW2abl3() == null || "".equals(dao.getW2abl3())) {
			dao.setW2abl3(ZERO);
		}
		if (dao.getW2asa4() == null || "".equals(dao.getW2asa4())) {
			dao.setW2asa4(ZERO);
		}
		if (dao.getW2agr4() == null || "".equals(dao.getW2agr4())) {
			dao.setW2agr4(ZERO);
		}
		if (dao.getW2abl4() == null || "".equals(dao.getW2abl4())) {
			dao.setW2abl4(ZERO);
		}
		if (dao.getW2beln() == null || "".equals(dao.getW2beln())) {
			dao.setW2beln(ZERO);
		}
		if (dao.getW2asa5() == null || "".equals(dao.getW2asa5())) {
			dao.setW2asa5(ZERO);
		}
		if (dao.getW2agr5() == null || "".equals(dao.getW2agr5())) {
			dao.setW2agr5(ZERO);
		}
		if (dao.getW2abl5() == null || "".equals(dao.getW2abl5())) {
			dao.setW2abl5(ZERO);
		}
		if (dao.getW2asa6() == null || "".equals(dao.getW2asa6())) {
			dao.setW2asa6(ZERO);
		}
		if (dao.getW2agr6() == null || "".equals(dao.getW2agr6())) {
			dao.setW2agr6(ZERO);
		}
		if (dao.getW2abl6() == null || "".equals(dao.getW2abl6())) {
			dao.setW2abl6(ZERO);
		}
		if (dao.getW2asa7() == null || "".equals(dao.getW2asa7())) {
			dao.setW2asa7(ZERO);
		}
		if (dao.getW2agr7() == null || "".equals(dao.getW2agr7())) {
			dao.setW2agr7(ZERO);
		}
		if (dao.getW2abl7() == null || "".equals(dao.getW2abl7())) {
			dao.setW2abl7(ZERO);
		}
		if (dao.getW2asa8() == null || "".equals(dao.getW2asa8())) {
			dao.setW2asa8(ZERO);
		}
		if (dao.getW2agr8() == null || "".equals(dao.getW2agr8())) {
			dao.setW2agr8(ZERO);
		}
		if (dao.getW2nt01() == null || "".equals(dao.getW2nt01())) {
			dao.setW2nt01(ZERO);
		}
		if (dao.getW2nt02() == null || "".equals(dao.getW2nt02())) {
			dao.setW2nt02(ZERO);
		}
		if (dao.getW2nt03() == null || "".equals(dao.getW2nt03())) {
			dao.setW2nt03(ZERO);
		}
		if (dao.getW2nt04() == null || "".equals(dao.getW2nt04())) {
			dao.setW2nt04(ZERO);
		}
		if (dao.getW2nt05() == null || "".equals(dao.getW2nt05())) {
			dao.setW2nt05(ZERO);
		}
		if (dao.getW2nt06() == null || "".equals(dao.getW2nt06())) {
			dao.setW2nt06(ZERO);
		}
		if (dao.getW2nt07() == null || "".equals(dao.getW2nt07())) {
			dao.setW2nt07(ZERO);
		}
		if (dao.getW2ntm() == null || "".equals(dao.getW2ntm())) {
			dao.setW2ntm(ZERO);
		}
		if (dao.getW2abl8() == null || "".equals(dao.getW2abl8())) {
			dao.setW2abl8(ZERO);
		}

	}

	
	private boolean existInKodts7(String w2vf) {
		int w2vfInt = Integer.parseInt(w2vf);
		boolean exists = kodts7DaoService.vfKodeExist(w2vfInt);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean existInKodts2(String w2lk) {
		boolean exists = kodts2DaoService.lkKodeExist(w2lk);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean existInKodts6(String w2pre) {
		boolean exists = kodts6DaoService.preKodeExist(w2pre);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean existInKodtsa(String enhet) {
		KodtsaDao qDao = new KodtsaDao();
		qDao.setKsakd(enhet);
		boolean exists = kodtsaDaoService.exist(qDao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}		

	private boolean existInKodtsb(String kommref) {
		KodtsbDao qDao = new KodtsbDao();
		qDao.setKsbkd(kommref);
		boolean exists = kodtsbDaoService.exist(qDao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}	
	
	private boolean existInTari(String w2vnti) {
		TariDao qDao = new TariDao();
		qDao.setTatanr(Integer.valueOf(w2vnti));
		boolean exists = tariDaoService.exist(qDao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}		
	
	private boolean existInKodts5(String w2tn) {
		Kodts5Dao qDao = new Kodts5Dao();
		qDao.setKs5uni("S5");
		qDao.setKs5tln(w2tn);
		boolean exists = kodts5DaoService.exist(qDao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}		

	private boolean existInKodts8(String avgkode, String sekvens) {
		Kodts8Dao qDao = new Kodts8Dao();
		qDao.setKs8avg(avgkode);
		qDao.setKs8skv(sekvens);
		boolean exists = kodts8DaoService.exist(qDao);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}		
	
	private boolean existInKodtvalf(String kvakod) {
		boolean exists = kodtvalfDaoService.kvaKodeExist(kvakod);
		if (!exists) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
