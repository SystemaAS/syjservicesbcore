package no.systema.jservices.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Aug 2020
 * 
 */
public interface ArkivpDaoServices extends IDaoServices { 
	public List find(Object obj,StringBuffer errorStackTrace);
}
