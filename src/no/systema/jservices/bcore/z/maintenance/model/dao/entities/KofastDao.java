package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author Fredrik Möller
 * @date Nov 21, 2016
 * 
 */
public class KofastDao implements Serializable, IDao {
	
	private String kftyp = "";
	private String kfkod = "";
	private String kftxt = "";
	private String kftxte = "";
	
	public String getKftyp() {
		return kftyp;
	}
	public void setKftyp(String kftyp) {
		this.kftyp = kftyp;
	}
	public String getKfkod() {
		return kfkod;
	}
	public void setKfkod(String kfkod) {
		this.kfkod = kfkod;
	}
	public String getKftxt() {
		return kftxt;
	}
	public void setKftxt(String kftxt) {
		this.kftxt = kftxt;
	}
	public String getKftxte() {
		return kftxte;
	}
	public void setKftxte(String kftxte) {
		this.kftxte = kftxte;
	}
	
	
	
}
