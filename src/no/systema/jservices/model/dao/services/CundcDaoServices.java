package no.systema.jservices.model.dao.services;

import java.util.List;

import no.systema.jservices.model.dao.entities.CundcDao;
import no.systema.jservices.model.dao.entities.IDao;

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
	 * Retrieve a IDao
	 * 
	 * @param qDao populated with query params.
	 * @param errorStackTrace
	 * @return a {@link IDao} if found, else returning null
	 */
	public IDao get(Object qDao , StringBuffer errorStackTrace);	
	
	
	/**
	 * Exist control on cfirma, ccompn, cconta and ctype
	 * 
	 * @param cfirma
	 * @param ccompn
	 * @param cconta
	 * @param ctype 
	 * @param errorStackTrace
	 * @return true or false
	 */
	public boolean exists(String cfirma, String ccompn, String cconta, String ctype, StringBuffer errorStackTrace);
	
	
	/**
	 * Delete all kontakts for firma and kunde
	 * 
	 * @param cfirma
	 * @param ccompn
	 * @param errorStackTrace
	 */
	public void deleteAll(String cfirma, String ccompn, StringBuffer errorStackTrace);
	
	
	/**
	 * 
	 * Retriece the latest registered EMMA-XML info.
	 * 
	 * @param cfirma firma
	 * @param errorStackTrace
	 * @return Object with latest info on EMMA-XML
	 */
	public CundcDao getLastRegisteredEmmaXmlInfo(String cfirma, StringBuffer errorStackTrace);
}
