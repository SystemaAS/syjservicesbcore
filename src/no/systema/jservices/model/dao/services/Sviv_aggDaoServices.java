package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.entities.Sviv_aggDao;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2021
 * 
 */
public interface Sviv_aggDaoServices extends IDaoServices { 
	
	public int insert(List<Sviv_aggDao> items, StringBuffer errorStackTrace);
}
