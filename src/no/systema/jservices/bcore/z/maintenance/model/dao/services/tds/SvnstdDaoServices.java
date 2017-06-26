package no.systema.jservices.bcore.z.maintenance.model.dao.services.tds;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Jun 26, 2017
 * 
 */
public interface SvnstdDaoServices extends IDaoServices {
	/**
	 * Retrieve rows for selection targeting <b>Avdelningar TDS NCTS Export</b> <br> 
	 * 
	 * @param errorStackTrace
	 * @return List for avd ncts export
	 */
	public List getNctsImportList(StringBuffer errorStackTrace);
	
	
}
