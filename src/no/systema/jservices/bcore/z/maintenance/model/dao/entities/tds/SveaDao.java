package no.systema.jservices.bcore.z.maintenance.model.dao.entities.tds;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.*;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jun 08, 2017
 * 
 */

public class SveaDao implements Serializable, IDao {
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private String svea_syav = "";     
	public String getSvea_syavPropertyName (){ return "svea_syav"; }
	public void setSvea_syav (String value){ this.svea_syav = value;   }   	
	public String getSvea_syav (){ return this.svea_syav;   }  
	
	private String svea_syop = "";   
	public String getSvea_syopPropertyName (){ return "svea_syop"; }
	public void setSvea_syop (String value){ this.svea_syop = value;   }   
	public String getSvea_syop (){ return this.svea_syop;   }  
	
	private String svea_omeo = ""; 
	public String getSvea_omeoPropertyName (){ return "svea_omeo"; }
	public void setSvea_omeo (String value){ this.svea_omeo = value;   }   
	public String getSvea_omeo (){ return this.svea_omeo;   }  
	
	private String svea_omha = "";  
	public String getSvea_omhaPropertyName (){ return "svea_omha"; }
	public void setSvea_omha (String value){ this.svea_omha = value;   }   
	public String getSvea_omha (){ return this.svea_omha;   }  
	
	private String svea_omtl = "";  
	public String getSvea_omtlPropertyName (){ return "svea_omtl"; }
	public void setSvea_omtl (String value){ this.svea_omtl = value;   }   
	public String getSvea_omtl (){ return this.svea_omtl;   }  
	
	private String svea_omty = "";
	public String getSvea_omtyPropertyName (){ return "svea_omty"; }
	public void setSvea_omty (String value){ this.svea_omty = value;   }   
	public String getSvea_omty (){ return this.svea_omty;   }  
	
	private String svea_0035 = ""; 
	public String getSvea_0035PropertyName (){ return "svea_0035"; }
	public void setSvea_0035 (String value){ this.svea_0035 = value;   }   
	public String getSvea_0035 (){ return this.svea_0035;   }  
	
}
