package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author Fredrik Möller
 * @date Nov 5, 2016
 * 
 */
public interface ArkvedkDaoServices extends IDaoServices { 

	
	/**
	 * Retrieve a IDao
	 * 
	 * @param qDao populated with query params.
	 * @param errorStackTrace
	 * @return a {@link IDao} if found, else returning null
	 */
	public IDao get(Object qDao , StringBuffer errorStackTrace);
}
