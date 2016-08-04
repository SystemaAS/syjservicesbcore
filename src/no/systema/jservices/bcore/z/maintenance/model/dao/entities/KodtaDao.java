package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Aug 1, 2016
 * 
 */
public class KodtaDao implements Serializable, IDao {
	
	private String koauni = "A"; //always as default                               
	public String getKoauniPropertyName (){ return "koauni"; }
	public void setKoauni (String value){ this.koauni = value;   }   
	public String getKoauni (){ return this.koauni;   }  
	
	private String koaavd = "";                                
	public String getKoaavdPropertyName (){ return "koaavd"; }
	public void setKoaavd (String value){ this.koaavd = value;   }   
	public String getKoaavd (){ return this.koaavd;   }  
	
	private String koaknr = ""; 
	public String getKoaknrPropertyName (){ return "koaknr"; }
	public void setKoaknr (String value){ this.koaknr = value;   }   
	public String getKoaknr (){ return this.koaknr;   }              

	private String koabaer = ""; 
	public String getKoabaerPropertyName (){ return "koabaer"; }
	public void setKoabaer (String value){ this.koabaer = value;   }   
	public String getKoabaer (){ return this.koabaer;   }              

	private String koakon = ""; 
	public String getKoakonPropertyName (){ return "koakon"; }
	public void setKoakon (String value){ this.koakon = value;   }   
	public String getKoakon (){ return this.koakon;   }              

	private String koafir = ""; 
	public String getKoafirPropertyName (){ return "koafir"; }
	public void setKoafir (String value){ this.koafir = value;   }   
	public String getKoafir (){ return this.koafir;   }              

	private String koanvn = ""; 
	public String getKoanvnPropertyName (){ return "koanvn"; }
	public void setKoanvn (String value){ this.koanvn = value;   }   
	public String getKoanvn (){ return this.koanvn;   }              

	private String koaiat = ""; 
	public String getKoaiatPropertyName (){ return "koaiat"; }
	public void setKoaiat (String value){ this.koaiat = value;   }   
	public String getKoaiat (){ return this.koaiat;   }              

	private String koaia2 = ""; 
	public String getKoaia2PropertyName (){ return "koaia2"; }
	public void setKoaia2 (String value){ this.koaia2 = value;   }   
	public String getKoaia2 (){ return this.koaia2;   }              

	private String koaie = ""; 
	public String getKoaiePropertyName (){ return "koaie"; }
	public void setKoaie (String value){ this.koaie = value;   }   
	public String getKoaie (){ return this.koaie;   }              

	private String koapos = ""; 
	public String getKoaposPropertyName (){ return "koapos"; }
	public void setKoapos (String value){ this.koapos = value;   }   
	public String getKoapos (){ return this.koapos;   }              

	private String koalk = ""; 
	public String getKoalkPropertyName (){ return "koalk"; }
	public void setKoalk (String value){ this.koalk = value;   }   
	public String getKoalk (){ return this.koalk;   }              

	
	//external fields from joins
	private String navsg = ""; 
	public String getNavsgPropertyName (){ return "navsg"; }
	public void setNavsg (String value){ this.navsg = value;   }   
	public String getNavsg (){ return this.navsg;   }              

	private String ksidnr = ""; 
	public String getKsidnrPropertyName (){ return "ksidnr"; }
	public void setKsidnr (String value){ this.ksidnr = value;   }   
	public String getKsidnr (){ return this.ksidnr;   }              
    //DUP
	private String kodus1 = ""; 
	public String getKodus1PropertyName (){ return "kodus1"; }
	public void setKodus1 (String value){ this.kodus1 = value;   }   
	public String getKodus1 (){ return this.kodus1;   }              
    
	private String kodus2 = ""; 
	public String getKodus2PropertyName (){ return "kodus2"; }
	public void setKodus2 (String value){ this.kodus2 = value;   }   
	public String getKodus2 (){ return this.kodus2;   }              
    
	private String kodus3 = ""; 
	public String getKodus3PropertyName (){ return "kodus3"; }
	public void setKodus3 (String value){ this.kodus3 = value;   }   
	public String getKodus3 (){ return this.kodus3;   }              
    
	private String kodus4 = ""; 
	public String getKodus4PropertyName (){ return "kodus4"; }
	public void setKodus4 (String value){ this.kodus4 = value;   }   
	public String getKodus4 (){ return this.kodus4;   }              
    
	private String kodus5 = ""; 
	public String getKodus5PropertyName (){ return "kodus5"; }
	public void setKodus5 (String value){ this.kodus5 = value;   }   
	public String getKodus5 (){ return this.kodus5;   }              
    
	private String kodus6 = ""; 
	public String getKodus6PropertyName (){ return "kodus6"; }
	public void setKodus6 (String value){ this.kodus6 = value;   }   
	public String getKodus6 (){ return this.kodus6;   }              
    
	
	
}
