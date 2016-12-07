package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author Fredrik Möller
 * @date Dec 6, 2016
 * 
 */
public class ArkvedkDao implements Serializable, IDao {
	
	private String avfirm = "";
	private String avkund = "";
	private String avtype = "";
	private String avkved = "";
	
	
	public String getAvfirm() {
		return avfirm;
	}
	public void setAvfirm(String avfirm) {
		this.avfirm = avfirm;
	}
	public String getAvkund() {
		return avkund;
	}
	public void setAvkund(String avkund) {
		this.avkund = avkund;
	}
	public String getAvtype() {
		return avtype;
	}
	public void setAvtype(String avtype) {
		this.avtype = avtype;
	}
	public String getAvkved() {
		return avkved;
	}
	public void setAvkved(String avkved) {
		this.avkved = avkved;
	}

	
	
}
