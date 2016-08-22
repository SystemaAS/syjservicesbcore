package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Aug 22, 2016
 * 
 */
public class KodtaTellDao implements Serializable, IDao {
	
	//TELL table
	
	private String teavd = "";                                
	public String getTeavdPropertyName (){ return "teavd"; }
	public void setTeavd (String value){ this.teavd = value;   }   
	public String getTeavd (){ return this.teavd;   }  
	
	private String teopdn = "";                                
	public String getTeopdnPropertyName (){ return "teopdn"; }
	public void setTeopdn (String value){ this.teopdn = value;   }   
	public String getTeopdn (){ return this.teopdn;   }  
	
	private String teturn = "";                                
	public String getTeturnPropertyName (){ return "teturn"; }
	public void setTeturn (String value){ this.teturn = value;   }   
	public String getTeturn (){ return this.teturn;   }  
	
	private String tetmin = "";                                
	public String getTetminPropertyName (){ return "tetmin"; }
	public void setTetmin (String value){ this.tetmin = value;   }   
	public String getTetmin (){ return this.tetmin;   }  
	
	
	
}
