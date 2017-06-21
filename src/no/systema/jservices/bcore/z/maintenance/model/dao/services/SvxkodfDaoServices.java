package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.services.IDaoServicesReadOnly;
/**
 * 
 * @author oscardelatorre
 * @date Jun 21, 2017
 * 
 */
public interface SvxkodfDaoServices extends IDaoServicesReadOnly { 
	public List getList(String code, StringBuffer errorStackTrace);
	public List findById(String code, String id, StringBuffer errorStackTrace);
}
