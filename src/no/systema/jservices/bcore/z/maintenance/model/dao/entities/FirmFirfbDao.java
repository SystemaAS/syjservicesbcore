package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Nov 3, 2016
 * 
 */
public class FirmFirfbDao implements Serializable, IDao {
	
	private String fifirm = "";                             
	public String getFifirmPropertyName (){ return "fifirm"; }
	public void setFifirm (String value){ this.fifirm = value;   }   
	public String getFifirm (){ return this.fifirm;   }  
	
	private String fifbnr = "";                                
	public String getFifbnrPropertyName (){ return "fifbnr"; }
	public void setFifbnr (String value){ this.fifbnr = value;   }   
	public String getFifbnr (){ return this.fifbnr;   }  
	
	private String fitpnr = "";                                
	public String getFitpnrPropertyName (){ return "fitpnr"; }
	public void setFitpnr (String value){ this.fitpnr = value;   }   
	public String getFitpnr (){ return this.fitpnr;   }  
	
	private String firecn = "";                                
	public String getFirecnPropertyName (){ return "firecn"; }
	public void setFirecn (String value){ this.firecn = value;   }   
	public String getFirecn (){ return this.firecn;   }  
	
	private String firecm = "";                                
	public String getFirecmPropertyName (){ return "firecm"; }
	public void setFirecm (String value){ this.firecm = value;   }   
	public String getFirecm (){ return this.firecm;   }  
	
	private String fisnla = "";                                
	public String getFisnlaPropertyName (){ return "fisnla"; }
	public void setFisnla (String value){ this.fisnla = value;   }   
	public String getFisnla (){ return this.fisnla;   }  
	
	private String fisnle = "";                                
	public String getFisnlePropertyName (){ return "fisnle"; }
	public void setFisnle (String value){ this.fisnle = value;   }   
	public String getFisnle (){ return this.fisnle;   }  
	
	private String fiidla = "";                                
	public String getFiidlaPropertyName (){ return "fiidla"; }
	public void setFiidla (String value){ this.fiidla = value;   }   
	public String getFiidla (){ return this.fiidla;   }  
	
	private String fiidle = "";                                
	public String getFiidlePropertyName (){ return "fiidle"; }
	public void setFiidle (String value){ this.fiidle = value;   }   
	public String getFiidle (){ return this.fiidle;   }  
	
	private String fiidnr = "";                                
	public String getFiidnrPropertyName (){ return "fiidnr"; }
	public void setFiidnr (String value){ this.fiidnr = value;   }   
	public String getFiidnr (){ return this.fiidnr;   }  
	
	private String fiidmx = "";                                
	public String getFiidmxPropertyName (){ return "fiidmx"; }
	public void setFiidmx (String value){ this.fiidmx = value;   }   
	public String getFiidmx (){ return this.fiidmx;   }  
	
	
	
}
