package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.CundfDao;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2021
 * 
 */
public interface SvihDaoServices extends IDaoServices { 
	public int updateStatus(Object daoObj, StringBuffer errorStackTrace);
}
