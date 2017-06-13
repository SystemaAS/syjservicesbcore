package no.systema.jservices.bcore.z.maintenance.model.dao.services.tds;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2017
 * 
 */
public interface SviaDaoServices extends IDaoServices {
	public List findById(Object dao, StringBuffer errorStackTrace);
}
