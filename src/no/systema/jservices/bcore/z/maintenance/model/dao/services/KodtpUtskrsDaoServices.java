package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtpUtskrsDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 9, 2016
 * 
 */
public interface KodtpUtskrsDaoServices extends IDaoServices { 
	public List getList(String id, StringBuffer errorStackTrace);
	public List findById(String id, String lnr, StringBuffer errorStackTrace);
	//children record operations
	public int updateChild(Object daoObj, StringBuffer errorStackTrace);
	public int insertBatch(final List<KodtpUtskrsDao> utskrsListTarget, StringBuffer errorStackTrace);
	
}
