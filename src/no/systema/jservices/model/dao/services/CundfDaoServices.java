package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.CundfDao;

public interface CundfDaoServices extends IDaoServices { 
	public List<CundfDao> getList();
	public List findById(String id, String firm,  StringBuffer errorStackTrace);
	public List findByName(String name, String firm,  StringBuffer errorStackTrace);
	public List findByName(String name, StringBuffer errorStackTrace);
}
