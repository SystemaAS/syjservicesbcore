package no.systema.jservices.bcore.z.maintenance.model.dao.services.sad;
import java.util.*;

import no.systema.jservices.model.dao.services.IDaoServicesReadOnly;
/**
 * 
 * @author oscardelatorre
 * @date Sep 20, 2016
 * 
 */
public interface Trkodl01DaoServices extends IDaoServicesReadOnly { 
	public List getList(String code, StringBuffer errorStackTrace);
	public List findById(String code, String id, StringBuffer errorStackTrace);
}
