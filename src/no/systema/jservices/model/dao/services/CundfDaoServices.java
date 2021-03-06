package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.CundfDao;

public interface CundfDaoServices extends IDaoServices { 
	public List<CundfDao> getList();
	public List findById(String id, String firm,  StringBuffer errorStackTrace);
	public List findByName(String name, String firm,  StringBuffer errorStackTrace);
	public List findByName(String name, StringBuffer errorStackTrace);
	public List findByOrgnr(String orgnr, StringBuffer errorStackTrace);
	public List findByOrgnr(String orgnr, String firm, StringBuffer errorStackTrace);
	/**
	 * Exist control on kundNr
	 * 
	 * @param kundNr
	 * @param errorStackTrace
	 * @return true or false
	 */
	public boolean exists(String kundNr, StringBuffer errorStackTrace);
	
	
	/**
	 * Cascade delete, with Transaction support. Using {@link TransactionTemplate}
	 * 
	 * @param daoObj, dao to be deleted
	 * @param errorStackTrace
	 * @return int, if < 0 , somethings wrong
	 */
	public int cascadeDelete(Object daoObj, StringBuffer errorStackTrace);
	
	/**Fetch first rows only
	 * 
	 * @param nrOfRows
	 * @param errorStackTrace
	 * @return List CundfDao
	 */
	public List findFetchFirstRowsOnly(int nrOfRows, StringBuffer errorStackTrace);
	
	/**
	 * Exist control on orgnr
	 * excluded actual kundnr
	 * 
	 * @param orgnr
	 * @kundnr kundnr
	 * @param errorStackTrace
	 * @return true or false
	 */
	public boolean orgNrExist(String orgnr, String kundnr, StringBuffer errorStackTrace);

	/**
	 * Count on orgnr
	 * 
	 * @param orgnr
	 * @param errorStackTrace
	 * @return true or false
	 */
	public int orgNrCount(String orgnr, StringBuffer errorStackTrace);
	

	/**
	 * Find all on where-clause from populated qDao
	 * Support a subset from CundfDao.
	 * 
	 * @param qDao, used in where-clause
	 * @param errorStackTrace
	 * @return  List CundfDao
	 */
	public List<CundfDao> findAll(CundfDao qDao, StringBuffer errorStackTrace);
	
}
