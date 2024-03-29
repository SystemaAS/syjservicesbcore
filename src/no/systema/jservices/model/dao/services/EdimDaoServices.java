package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.CundfDao;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2021
 * 
 */
public interface EdimDaoServices extends IDaoServices { 
	
	public Integer getCounterEdicCsn();
	public int insertWhenInboundFile(Object daoObj, StringBuffer errorStackTrace);
	public List findByUuid(String uuid, StringBuffer errorStackTrace);
	public List findByTuid(String tuid, StringBuffer errorStackTrace);
	public List findByTuidAll(String tuid, StringBuffer errorStackTrace);
	public List findByMrn(String mrn, StringBuffer errorStackTrace);
}
