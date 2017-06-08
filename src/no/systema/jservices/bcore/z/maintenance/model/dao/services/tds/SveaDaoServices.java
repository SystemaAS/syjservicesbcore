package no.systema.jservices.bcore.z.maintenance.model.dao.services.tds;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Jun 08, 2017
 * 
 */
public interface SveaDaoServices extends IDaoServices {
	public List findById(Object dao, StringBuffer errorStackTrace);
}
