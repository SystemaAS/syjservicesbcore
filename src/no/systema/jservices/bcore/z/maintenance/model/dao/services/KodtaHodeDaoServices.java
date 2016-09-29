package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.util.*;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtpUtskrsDao;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Aug 17, 2016
 * 
 */
public interface KodtaHodeDaoServices extends IDaoServices { 
	public List findById (String id, String lang, StringBuffer errorStackTrace );
	public int deleteAllAvd(Object daoObj, StringBuffer errorStackTrace);
}
