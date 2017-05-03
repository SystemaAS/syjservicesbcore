package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.EdiiDao;

public interface EdiiDaoServices extends IDaoServicesReadOnly { 
	static final String INTERNAL = "I";
	static final String EXTERNAL = "E";	
	public List<EdiiDao> getList();
	public List findByName(String name, StringBuffer errorStackTrace);
	public List<EdiiDao> findByIdAndInex(String inex, String id, StringBuffer errorStackTrace);	
	
}
