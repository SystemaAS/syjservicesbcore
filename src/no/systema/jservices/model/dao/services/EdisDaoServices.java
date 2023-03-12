package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.CundfDao;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2021
 * 
 */
public interface EdisDaoServices extends IDaoServices { 
	
	public List findFilePathByOpp(String avd, String opd, String path, StringBuffer errorStackTrace);
}
