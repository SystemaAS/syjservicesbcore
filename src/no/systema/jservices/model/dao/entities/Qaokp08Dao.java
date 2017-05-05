package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 05, 2017
 * 
 */
public class Qaokp08Dao implements Serializable, IDao {
	
	private String wos8dden = "";                              
	public String getWos8ddenPropertyName (){ return "wos8dden"; }
	public void setWos8dden (String value){ this.wos8dden = value;   }   
	public String getWos8dden (){ return this.wos8dden;   }  
	
	private String wos8ddgn = "";                                
	public String getWos8ddgnPropertyName (){ return "wos8ddgn"; }
	public void setWos8ddgn (String value){ this.wos8ddgn = value;   }   
	public String getWos8ddgn (){ return this.wos8ddgn;   }  
	
	private String wos8desc = ""; 
	public String getWos8descPropertyName (){ return "wos8desc"; }
	public void setWos8desc (String value){ this.wos8desc = value;   }   
	public String getWos8desc (){ return this.wos8desc;   }              


}
