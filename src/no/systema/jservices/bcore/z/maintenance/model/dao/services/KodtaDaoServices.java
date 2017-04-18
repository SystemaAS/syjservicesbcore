package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Aug 1, 2016
 * 
 */
public interface KodtaDaoServices extends IDaoServices { 
	public List getListForAvailableAvdTvinnSadImport(StringBuffer errorStackTrace);
	public List getListForAvailableAvdTvinnSadExport(StringBuffer errorStackTrace);
	public List getListForAvailableAvdTvinnSadNctsImport(StringBuffer errorStackTrace);
	public List getListForAvailableAvdTvinnSadNctsExport(StringBuffer errorStackTrace);
	//
	public List getListForAvailableAvdSkatNctsImport(StringBuffer errorStackTrace);
	public List getListForAvailableAvdSkatNctsExport(StringBuffer errorStackTrace);
}
