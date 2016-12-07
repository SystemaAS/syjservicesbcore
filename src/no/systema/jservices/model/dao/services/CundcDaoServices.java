package no.systema.jservices.model.dao.services;

import java.util.List;

import no.systema.jservices.model.dao.entities.CundcDao;

public interface CundcDaoServices extends IDaoServices { 
	public List<CundcDao> getList(StringBuffer errorStackTrace);
	/**
	 * Retrieve list of kontaktpersoner for kunde.
	 * 
	 * @param ccompn, kundnummer
	 * @param cfirma, firmanummer
	 * @param errorStackTrace
	 * @return a List of {@link CundcDao}
	 */

	public List<CundcDao> findById(String ccompn, String cfirma, StringBuffer errorStackTrace);
	/**
	 * Retrieve specific record of CUNDC.
	 * 
	 * @param ccompn, kundnummer
	 * @param cfirma, firmanummer
	 * @param ccconta, kontakt-id
	 * @param errorStackTrace
	 * @return
	 */
	public List<CundcDao> findById(String ccompn, String cfirma, String ccconta, StringBuffer errorStackTrace);
	
	
	/**
	 * Exist control on cfirma, ccompn and cconta
	 * 
	 * @param cfirma
	 * @param ccompn
	 * @param cconta
	 * @param errorStackTrace
	 * @return true or false
	 */
	public boolean exists(String cfirma, String ccompn, String cconta, StringBuffer errorStackTrace);
}
