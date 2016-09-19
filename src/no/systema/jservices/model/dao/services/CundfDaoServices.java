package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.CusdfDao;

public interface CundfDaoServices extends IDaoServicesReadOnly { 
	public List<CusdfDao> getList();
	public List findById(String id, String firm,  StringBuffer errorStackTrace);
	public List findByName(String name, String firm,  StringBuffer errorStackTrace);
	public List findByName(String name, StringBuffer errorStackTrace);
}
