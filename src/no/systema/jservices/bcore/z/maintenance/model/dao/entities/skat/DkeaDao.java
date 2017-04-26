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

public class DkeaDao implements Serializable, IDao {
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private String dkea_syav = "";                             
	public String getDkea_syavPropertyName (){ return "dkea_syav"; }
	public void setDkea_syav (String value){ this.dkea_syav = value;   }   	
	public String getDkea_syav (){ return this.dkea_syav;   }  
	
	private String dkea_syop = "";                                
	public String getDkea_syopPropertyName (){ return "dkea_syop"; }
	public void setDkea_syop (String value){ this.dkea_syop = value;   }   
	public String getDkea_syop (){ return this.dkea_syop;   }  
	
	private String dkea_14a = "";                                
	public String getDkea_14aPropertyName (){ return "dkea_14a"; }
	public void setDkea_14a (String value){ this.dkea_14a = value;   }   
	public String getDkea_14a (){ return this.dkea_14a;   }  
	
	private String dkea_14b = "";                                
	public String getDkea_14bPropertyName (){ return "dkea_14b"; }
	public void setDkea_14b (String value){ this.dkea_14b = value;   }   
	public String getDkea_14b (){ return this.dkea_14b;   }  
	
	private String dkea_14c = "";                                
	public String getDkea_14cPropertyName (){ return "dkea_14c"; }
	public void setDkea_14c (String value){ this.dkea_14c = value;   }   
	public String getDkea_14c (){ return this.dkea_14c;   }  
	
	private String dkea_14d = "";                                
	public String getDkea_14dPropertyName (){ return "dkea_14d"; }
	public void setDkea_14d (String value){ this.dkea_14d = value;   }   
	public String getDkea_14d (){ return this.dkea_14d;   }  
	
	private String dkea_14e = "";                                
	public String getDkea_14ePropertyName (){ return "dkea_14e"; }
	public void setDkea_14e (String value){ this.dkea_14e = value;   }   
	public String getDkea_14e (){ return this.dkea_14e;   }  
	
	private String dkea_14f = "";                                
	public String getDkea_14fPropertyName (){ return "dkea_14f"; }
	public void setDkea_14f (String value){ this.dkea_14f = value;   }   
	public String getDkea_14f (){ return this.dkea_14f;   }  
	
	private String dkea_0035 = "";                                
	public String getDkea_0035PropertyName (){ return "dkea_0035"; }
	public void setDkea_0035 (String value){ this.dkea_0035 = value;   }   
	public String getDkea_0035 (){ return this.dkea_0035;   }  
	
	private String dkea_ftip = "";                                
	public String getDkea_ftipPropertyName (){ return "dkea_ftip"; }
	public void setDkea_ftip (String value){ this.dkea_ftip = value;   }   
	public String getDkea_ftip (){ return this.dkea_ftip;   }  
	
	private String dkea_us = "";                                
	public String getDkea_usPropertyName (){ return "dkea_us"; }
	public void setDkea_us (String value){ this.dkea_us = value;   }   
	public String getDkea_us (){ return this.dkea_us;   }  
	
	private String dkea_pw = "";                                
	public String getDkea_pwPropertyName (){ return "dkea_pw"; }
	public void setDkea_pw (String value){ this.dkea_pw = value;   }   
	public String getDkea_pw (){ return this.dkea_pw;   }  
	
	private String dkea_prtf = "";                                
	public String getDkea_prtfPropertyName (){ return "dkea_prtf"; }
	public void setDkea_prtf (String value){ this.dkea_prtf = value;   }   
	public String getDkea_prtf (){ return this.dkea_prtf;   }  
	
}
