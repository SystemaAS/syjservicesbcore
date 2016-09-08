package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.EdiiDao;

public interface EdiiDaoServices extends IDaoServicesReadOnly { 
	public List<EdiiDao> getList();
	public List findByName(String name, StringBuffer errorStackTrace);
	
}
