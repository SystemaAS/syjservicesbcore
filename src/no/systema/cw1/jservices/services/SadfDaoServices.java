package no.systema.cw1.jservices.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 * 
 */
public interface SadfDaoServices extends IDaoServices { 
	public List getList(String avd, String opd, StringBuffer errorStackTrace);
	public List findById (String avd, String opd, String lin, StringBuffer errorStackTrace );
}
