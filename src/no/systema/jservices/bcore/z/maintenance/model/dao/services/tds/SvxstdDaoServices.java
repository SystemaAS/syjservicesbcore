package no.systema.jservices.bcore.z.maintenance.model.dao.services.tds;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Jun 16, 2017
 * 
 */
public interface SvxstdDaoServices extends IDaoServices {
	/**
	 * Retrieve rows for selection targeting <b>Avdelningar TVINN NCTS Export</b> <br>
	 * 
	 * thdk is where-claused on <i>NOT</i> ENTRY, EXIT and SS. 
	 * 
	 * @param errorStackTrace
	 * @return List for avd ncts export
	 */
	public List getNctsExportList(StringBuffer errorStackTrace);
	/**
	 * Retrieve rows for selection targeting <b>Avdelningar TDS NCTS Forhandsvarsling</b> <br>
	 * 
	 * thdk is where-claused on ENTRY, EXIT and SS. 
	 * 
	 * @param errorStackTrace
	 * @return List for avd ncts forhandsvarsling
	 */
	public List getNctsForhandsvarslingList(StringBuffer errorStackTrace);
	
	
}
