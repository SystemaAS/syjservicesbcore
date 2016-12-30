package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.CundfDao;

public interface CundfDaoServices extends IDaoServices { 
	public List<CundfDao> getList();
	public List findById(String id, String firm,  StringBuffer errorStackTrace);
	public List findByName(String name, String firm,  StringBuffer errorStackTrace);
	public List findByName(String name, StringBuffer errorStackTrace);
	
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
	
}
