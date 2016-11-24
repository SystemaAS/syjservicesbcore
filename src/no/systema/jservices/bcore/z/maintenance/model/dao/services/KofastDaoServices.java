package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.util.List;

import no.systema.jservices.common.values.FasteKoder;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * This Dao service also exists i bcode
 * 
 * 
 * @author Fredrik Möller
 * @date Okt 6, 2016
 * 
 */
public interface KofastDaoServices extends IDaoServices { 
	
	/**
	 * Retrieve unique tuple from selection using FasteKoder
	 * 
	 * 
	 * @param kftyp
	 * @param kfkod
	 * @param errorStackTrace
	 * @return
	 */
	public List findById(FasteKoder kftyp, String kfkod, StringBuffer errorStackTrace);

	/**
	 * Retrieve  ktyp selection from FasteKoder
	 * 
	 * 
	 * @param kftyp
	 * @param errorStackTrace
	 * @return
	 */
	public List findById(FasteKoder kftyp,StringBuffer errorStackTrace);

	/**
	 * Exist control on FasteKoder (kftyp) and code
	 * 
	 * @param FasteKoder kftyp
	 * @param String code
	 * @return true or false
	 */
	public boolean exists(FasteKoder kftyp, String code);
	
}
