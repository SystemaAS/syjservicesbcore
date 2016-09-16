package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Aug 1, 2016
 * 
 */
public interface KodtaDaoServices extends IDaoServices { 
	public List getListForAvailableAvdTvinnSadImport(StringBuffer errorStackTrace);
	public List getListForAvailableAvdTvinnSadExport(StringBuffer errorStackTrace);
}
