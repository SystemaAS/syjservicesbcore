package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.services.IDaoServicesReadOnly;
/**
 * 
 * @author oscardelatorre
 * @date Apr 11, 2016
 * 
 */
public interface DkxkodfDaoServices extends IDaoServicesReadOnly { 
	public List getList(String code, StringBuffer errorStackTrace);
	public List findById(String code, String id, StringBuffer errorStackTrace);
}
