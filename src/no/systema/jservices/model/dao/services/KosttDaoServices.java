package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.KosttDao;

public interface KosttDaoServices extends IDaoServicesReadOnly { 
	public List<KosttDao> getList();
	public List findByName(String name, StringBuffer errorStackTrace);
	
}
