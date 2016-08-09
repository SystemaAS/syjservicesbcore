package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Aug 9, 2016
 * 
 */
public interface KodtpUtskrsDaoServices extends IDaoServices { 
	public List getList(String id, StringBuffer errorStackTrace);
	public List findById(String id, String lnr, StringBuffer errorStackTrace);
}
