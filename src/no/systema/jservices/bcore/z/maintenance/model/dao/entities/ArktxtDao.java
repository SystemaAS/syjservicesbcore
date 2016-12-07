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
public class ArktxtDao implements Serializable, IDao {
	
	private String artype = "";
	private String artxt = "";
	private String arkjn = "";
	private String arksnd = "";
	private String arklag = "";
	private String arkdag = "";
	private String arkved = "";
	private String arslab = "";
	private String arsban = "";
	private String armrg = "";
	private String arvedl = "";
	
	public String getArtype() {
		return artype;
	}
	public void setArtype(String artype) {
		this.artype = artype;
	}
	public String getArtxt() {
		return artxt;
	}
	public void setArtxt(String artxt) {
		this.artxt = artxt;
	}
	public String getArkjn() {
		return arkjn;
	}
	public void setArkjn(String arkjn) {
		this.arkjn = arkjn;
	}
	public String getArksnd() {
		return arksnd;
	}
	public void setArksnd(String arksnd) {
		this.arksnd = arksnd;
	}
	public String getArklag() {
		return arklag;
	}
	public void setArklag(String arklag) {
		this.arklag = arklag;
	}
	public String getArkdag() {
		return arkdag;
	}
	public void setArkdag(String arkdag) {
		this.arkdag = arkdag;
	}
	public String getArkved() {
		return arkved;
	}
	public void setArkved(String arkved) {
		this.arkved = arkved;
	}
	public String getArslab() {
		return arslab;
	}
	public void setArslab(String arslab) {
		this.arslab = arslab;
	}
	public String getArsban() {
		return arsban;
	}
	public void setArsban(String arsban) {
		this.arsban = arsban;
	}
	public String getArmrg() {
		return armrg;
	}
	public void setArmrg(String armrg) {
		this.armrg = armrg;
	}
	public String getArvedl() {
		return arvedl;
	}
	public void setArvedl(String arvedl) {
		this.arvedl = arvedl;
	}
	

}
