package no.systema.jservices.bcore.z.maintenance.model.dao.entities.tds;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.*;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jun 13, 2017
 * 
 */

public class SviaDao implements Serializable, IDao {
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private String svia_syav = "";     
	public String getSvia_syavPropertyName (){ return "svia_syav"; }
	public void setSvia_syav (String value){ this.svia_syav = value;   }   	
	public String getSvia_syav (){ return this.svia_syav;   }  
	
	private String svia_syop = "";   
	public String getSvia_syopPropertyName (){ return "svia_syop"; }
	public void setSvia_syop (String value){ this.svia_syop = value;   }   
	public String getSvia_syop (){ return this.svia_syop;   }  
	
	private String svia_omeo = ""; 
	public String getSvia_omeoPropertyName (){ return "svia_omeo"; }
	public void setSvia_omeo (String value){ this.svia_omeo = value;   }   
	public String getSvia_omeo (){ return this.svia_omeo;   }  
	
	private String svia_omha = "";  
	public String getSvia_omhaPropertyName (){ return "svia_omha"; }
	public void setSvia_omha (String value){ this.svia_omha = value;   }   
	public String getSvia_omha (){ return this.svia_omha;   }  
	
	private String svia_omtl = "";  
	public String getSvia_omtlPropertyName (){ return "svia_omtl"; }
	public void setSvia_omtl (String value){ this.svia_omtl = value;   }   
	public String getSvia_omtl (){ return this.svia_omtl;   }  
	
	private String svia_omty = "";
	public String getSvia_omtyPropertyName (){ return "svia_omty"; }
	public void setSvia_omty (String value){ this.svia_omty = value;   }   
	public String getSvia_omty (){ return this.svia_omty;   }  
	
	private String svia_0035 = ""; 
	public String getSvia_0035PropertyName (){ return "svia_0035"; }
	public void setSvia_0035 (String value){ this.svia_0035 = value;   }   
	public String getSvia_0035 (){ return this.svia_0035;   }  
	
}
