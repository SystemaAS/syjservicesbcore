package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the
 * db-table
 * 
 * 
 * FIFIRM     Firmakode                                1    2     2         A   
 * FIKUFR     FRA KUNDENUMMER                          3   10     8   8   0 S   
 * FIKUTI     TIL KUNDENUMMER                         11   18     8   8   0 S   
 * FIKUNE     NESTE KUNDENR                           19   26     8   8   0 S   
 * 
 * 
 * @author Fredrik Möller
 * @date Dec 6, 2016
 * 
 */
@SuppressWarnings("serial")
public class FirkuDao implements Serializable, IDao {
	
	private String fifirm = "";
	private String fikufr = "";
	private String fikuti = "";
	private String fikune = "";

	public String getFifirm() {
		return fifirm;
	}

	public void setFifirm(String fifirm) {
		this.fifirm = fifirm;
	}

	public String getFikufr() {
		return fikufr;
	}

	public void setFikufr(String fikufr) {
		this.fikufr = fikufr;
	}

	public String getFikuti() {
		return fikuti;
	}

	public void setFikuti(String fikuti) {
		this.fikuti = fikuti;
	}

	public String getFikune() {
		return fikune;
	}

	public void setFikune(String fikune) {
		this.fikune = fikune;
	}

	
}
