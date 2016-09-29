package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Aug 5, 2016
 * 
 */
public interface KodtvKodtwDaoServices extends IDaoServices { 
	public int deleteChildKodtv(Object daoObj, StringBuffer errorStackTrace);
	public int deleteChildKodtw(Object daoObj, StringBuffer errorStackTrace);
}
