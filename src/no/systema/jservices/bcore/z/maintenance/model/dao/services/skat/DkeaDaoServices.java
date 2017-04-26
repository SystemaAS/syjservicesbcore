package no.systema.jservices.bcore.z.maintenance.model.dao.services.skat;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Apr 26, 2017
 * 
 */
public interface DkeaDaoServices extends IDaoServices {
	public List findById(Object dao, StringBuffer errorStackTrace);
}
