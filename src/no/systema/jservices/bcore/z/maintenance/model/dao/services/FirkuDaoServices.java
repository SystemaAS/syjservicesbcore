package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author Fredrik Möller
 * @date Dec 27, 2016
 * 
 */
public interface FirkuDaoServices extends IDaoServices { 

	/**
	 * Retrieve a IDao
	 * 
	 * @param errorStackTrace
	 * @return a {@link IDao} if found, else returning null
	 */
	public IDao get(StringBuffer errorStackTrace);
	
	/**
	 * Retrieve the next available fikune, and increment with one.
	 * 
	 * @param errorStackTrace
	 * @return next valid fikune as String, and incrementing with one, for next
	 */
	public String getFikune(StringBuffer errorStackTrace);
	
	/**
	 * Retrieve the next available fikufn, and increment with one.
	 * 
	 * @param errorStackTrace
	 * @return next valid fikufn as String, and incrementing with one, for next
	 */
	public String getFikufn(StringBuffer errorStackTrace);	
	
	/**
	 * Check if it is valid to register invoice customer.
	 * If FIKUFN is not 0, then it is ok.
	 * 
	 * @param errorStackTrace
	 * @return true if FIKUFN is not 0, else false.
	 */
	public boolean invoiceCustomerEnabled(StringBuffer errorStackTrace);
	
	
}
