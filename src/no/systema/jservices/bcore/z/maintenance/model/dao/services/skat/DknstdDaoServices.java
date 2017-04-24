package no.systema.jservices.bcore.z.maintenance.model.dao.services.skat;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Apr 24, 2017
 * 
 */
public interface DknstdDaoServices extends IDaoServices {
	/**
	 * Retrieve rows for selection targeting <b>Avdelningar SKAT NCTS Export</b> <br> 
	 * 
	 * @param errorStackTrace
	 * @return List for avd ncts export
	 */
	public List getNctsImportList(StringBuffer errorStackTrace);
	
	
}
