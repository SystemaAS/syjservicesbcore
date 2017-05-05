package no.systema.jservices.model.dao.services;
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date May 05, 2017
 * 
 */
public interface Qaokp08aDaoServices {
	public List getList(StringBuffer errorStackTrace);
	public List findById(String id, StringBuffer errorStackTrace);
	
}
