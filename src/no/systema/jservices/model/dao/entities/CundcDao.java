package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.ArkvedkDao;

@SuppressWarnings("serial")
public class CundcDao implements Serializable, IDao {

	private String rownum = "";
	private String ccompn = "";   //key                  
	private String cfirma = "";	  //key    
	private String cconta = "";  //key
	private String ccontaorg = "";
	private String ctype =""; //key
	private String ctypeorg ="";
	private String cphone = "";
	private String cmobil = "";
	private String cfax = "";
	private String cemail = "";
	private String clive = "";
	private String cprint = "";
	private String sonavn = "";
	private String cemne = "";
	private String cavd = "";
	private String cavdio = "";
	private String copd = "";
	private String copdio = "";
	private String cmerge = "";
	private ArkvedkDao arkvedkDao = null;
	
	/**
	 * Cust.No
	 * 
	 * @return
	 */
	public String getCcompn() {
		return ccompn;
	}
	public void setCcompn(String ccompn) {
		this.ccompn = ccompn;
	}
	/**
	 * Firmcode
	 * 
	 * @return
	 */
	public String getCfirma() {
		return cfirma;
	}
	public void setCfirma(String cfirma) {
		this.cfirma = cfirma;
	}
	/**
	 * Contact.ID
	 *
	 * @return
	 */
	public String getCconta() {
		return cconta;
	}
	public void setCconta(String ccconta) {
		this.cconta = ccconta;
	}
	/**
	 * Contact.ID Orginal
	 * 
	 * Used in where-clause, since cconta is updateable.
	 *
	 * @return
	 */
	public String getCcontaorg() {
		return ccontaorg;
	}
	public void setCcontaorg(String ccontaorg) {
		this.ccontaorg = ccontaorg;
	}

	/**
	 * Contact.Type
	 * 
	 * @return
	 */
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	/**
	 * Phone.No
	 * 
	 * @return
	 */
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	/**
	 * Mobil.No
	 * 
	 * @return
	 */
	public String getCmobil() {
		return cmobil;
	}
	public void setCmobil(String cmobil) {
		this.cmobil = cmobil;
	}
	/**
	 * Fax.No
	 * 
	 * @return
	 */
	public String getCfax() {
		return cfax;
	}
	public void setCfax(String cfax) {
		this.cfax = cfax;
	}
	/**
	 * E-Mail.Addr
	 * 
	 * @return
	 */
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	/**
	 * Link/attachment
	 * 
	 * @return
	 */
	public String getClive() {
		return clive;
	}
	public void setClive(String clive) {
		this.clive = clive;
	}
	/**
	 * Also print
	 * 
	 * @return
	 */
	public String getCprint() {
		return cprint;
	}
	public void setCprint(String cprint) {
		this.cprint = cprint;
	}
	/**
	 * Searchname
	 * 
	 * @return
	 */
	public String getSonavn() {
		return sonavn;
	}
	public void setSonavn(String sonavn) {
		this.sonavn = sonavn;
	}
	/**
	 * Subject /Email
	 * 
	 * @return
	 */
	public String getCemne() {
		return cemne;
	}
	public void setCemne(String cemne) {
		this.cemne = cemne;
	}
	/**
	 * Department
	 * 
	 * @return
	 */
	public String getCavd() {
		return cavd;
	}
	public void setCavd(String cavd) {
		this.cavd = cavd;
	}
	/**
	 * Inc/Omit Departmens
	 * 
	 * @return
	 */
	public String getCavdio() {
		return cavdio;
	}
	public void setCavdio(String cavdio) {
		this.cavdio = cavdio;
	}
	/**
	 * Ordertypes 
	 * 
	 * @return
	 */
	public String getCopd() {
		return copd;
	}
	public void setCopd(String copd) {
		this.copd = copd;
	}
	/**
	 * Inc/Omit Ordertypes
	 * 
	 * @return
	 */
	public String getCopdio() {
		return copdio;
	}
	public void setCopdio(String copdio) {
		this.copdio = copdio;
	}
	/**
	 * Merge PDF
	 * 
	 * @return
	 */
	public String getCmerge() {
		return cmerge;
	}
	public void setCmerge(String cmerge) {
		this.cmerge = cmerge;
	}
	public ArkvedkDao getArkvedkDao() {
		return arkvedkDao;
	}
	public void setArkvedkDao(ArkvedkDao arkvedkDao) {
		this.arkvedkDao = arkvedkDao;
	}
	public String getCtypeorg() {
		return ctypeorg;
	}
	public void setCtypeorg(String ctypeorg) {
		this.ctypeorg = ctypeorg;
	}
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	
	

}
