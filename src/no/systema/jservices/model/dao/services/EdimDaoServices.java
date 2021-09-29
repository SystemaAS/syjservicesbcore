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
}
