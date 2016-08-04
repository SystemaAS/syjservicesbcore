package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Aug 4, 2016
 * 
 */
public class FirmDao implements Serializable, IDao {
	
	private String fifirm = "";                             
	public String getFifirmPropertyName (){ return "fifirm"; }
	public void setFifirm (String value){ this.fifirm = value;   }   
	public String getFifirm (){ return this.fifirm;   }  
	
	private String fift = "";                                
	public String getFiftPropertyName (){ return "fift"; }
	public void setFift (String value){ this.fift = value;   }   
	public String getFift (){ return this.fift;   }  
	    
	
	
}
