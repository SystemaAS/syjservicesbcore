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

	private String navsg = ""; 
	public String getNavsgPropertyName (){ return "navsg"; }
	public void setNavsg (String value){ this.navsg = value;   }   
	public String getNavsg (){ return this.navsg;   }              

	
	
}
