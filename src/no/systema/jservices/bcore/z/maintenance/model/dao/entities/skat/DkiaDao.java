package no.systema.jservices.bcore.z.maintenance.model.dao.entities.skat;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.*;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Apr 26, 2017
 * 
 */

public class DkiaDao implements Serializable, IDao {
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private String dkia_syav = "";                             
	public String getDkia_syavPropertyName (){ return "dkia_syav"; }
	public void setDkia_syav (String value){ this.dkia_syav = value;   }   	
	public String getDkia_syav (){ return this.dkia_syav;   }  
	
	private String dkia_syop = "";                                
	public String getDkia_syopPropertyName (){ return "dkia_syop"; }
	public void setDkia_syop (String value){ this.dkia_syop = value;   }   
	public String getDkia_syop (){ return this.dkia_syop;   }  
	
	private String dkia_14a = "";                                
	public String getDkia_14aPropertyName (){ return "dkia_14a"; }
	public void setDkia_14a (String value){ this.dkia_14a = value;   }   
	public String getDkia_14a (){ return this.dkia_14a;   }  
	
	private String dkia_14b = "";                                
	public String getDkia_14bPropertyName (){ return "dkia_14b"; }
	public void setDkia_14b (String value){ this.dkia_14b = value;   }   
	public String getDkia_14b (){ return this.dkia_14b;   }  
	
	private String dkia_14c = "";                                
	public String getDkia_14cPropertyName (){ return "dkia_14c"; }
	public void setDkia_14c (String value){ this.dkia_14c = value;   }   
	public String getDkia_14c (){ return this.dkia_14c;   }  
	
	private String dkia_14d = "";                                
	public String getDkia_14dPropertyName (){ return "dkia_14d"; }
	public void setDkia_14d (String value){ this.dkia_14d = value;   }   
	public String getDkia_14d (){ return this.dkia_14d;   }  
	
	private String dkia_14e = "";                                
	public String getDkia_14ePropertyName (){ return "dkia_14e"; }
	public void setDkia_14e (String value){ this.dkia_14e = value;   }   
	public String getDkia_14e (){ return this.dkia_14e;   }  
	
	private String dkia_14f = "";                                
	public String getDkia_14fPropertyName (){ return "dkia_14f"; }
	public void setDkia_14f (String value){ this.dkia_14f = value;   }   
	public String getDkia_14f (){ return this.dkia_14f;   }  
	
	private String dkia_0035 = "";                                
	public String getDkia_0035PropertyName (){ return "dkia_0035"; }
	public void setDkia_0035 (String value){ this.dkia_0035 = value;   }   
	public String getDkia_0035 (){ return this.dkia_0035;   }  
	
}
