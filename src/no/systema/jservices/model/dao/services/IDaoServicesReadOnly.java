package no.systema.jservices.model.dao.services;

import java.util.List;

/**
 * Grandfather for all normal read only Dao services
 * @author oscardelatorre
 * @date Apr 15, 2016
 */
public interface IDaoServicesReadOnly {
	public List getList(StringBuffer errorStackTrace);
	public List findById(String id, StringBuffer errorStackTrace);
	
}
