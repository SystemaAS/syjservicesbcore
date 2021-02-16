package no.systema.cw1.jservices.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 * 
 */
public interface SadhDaoServices extends IDaoServices { 
	public List getList(String status, StringBuffer errorStackTrace);
	public List findById (String avd, String opd, StringBuffer errorStackTrace );
}
