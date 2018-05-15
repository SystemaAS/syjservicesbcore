package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Maj 15, 2018
 * 
 */
public class BridfPasswordDao implements Serializable, IDao {
	
	private String biakt= ""; //always as default                               
	public String getBiaktPropertyName (){ return "biakt"; }
	public void setBiakt (String value){ this.biakt = value;   }   
	public String getBiakt (){ return this.biakt;   }  
	
	private String bibrid = "";                                
	public String getBibridPropertyName (){ return "bibrid"; }
	public void setBibrid (String value){ this.bibrid = value;   }   
	public String getBibrid (){ return this.bibrid;   }  
	
	private String bipo = ""; 
	public String getBipoPropertyName (){ return "bipo"; }
	public void setBipo (String value){ this.bipo = value;   }   
	public String getBipo (){ return this.bipo;   }              

	
}
