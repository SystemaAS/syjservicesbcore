package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Aug 17, 2016
 * 
 */
public class KodtaHodeDao implements Serializable, IDao {
	
	//KODTA table
	private String koaavd = "";                                
	public String getKoaavdPropertyName (){ return "koaavd"; }
	public void setKoaavd (String value){ this.koaavd = value;   }   
	public String getKoaavd (){ return this.koaavd;   }  
	
	private String koanvn = "";                                
	public String getKoanvnPropertyName (){ return "koanvn"; }
	public void setKoanvn (String value){ this.koanvn = value;   }   
	public String getKoanvn (){ return this.koanvn;   }  
	
	private String hoavd = "";                                
	public String getHoavdPropertyName (){ return "hoavd"; }
	public void setHoavd (String value){ this.hoavd = value;   }   
	public String getHoavd (){ return this.hoavd;   }  
	
	private String honet = "";                                
	public String getHonetPropertyName (){ return "honet"; }
	public void setHonet (String value){ this.honet = value;   }   
	public String getHonet (){ return this.honet;   }  
	
	private String hostfr = "";                                
	public String getHostfrPropertyName (){ return "hostfr"; }
	public void setHostfr (String value){ this.hostfr = value;   }   
	public String getHostfr (){ return this.hostfr;   }  
	
	private String kopty = "";                                
	public String getKoptyPropertyName (){ return "kopty"; }
	public void setKopty (String value){ this.kopty = value;   }   
	public String getKopty (){ return this.kopty;   }  
	
	private String hobt1 = "";                                
	public String getHobt1PropertyName (){ return "hobt1"; }
	public void setHobt1 (String value){ this.hobt1 = value;   }   
	public String getHobt1 (){ return this.hobt1;   }  
	
	private String hobt2 = "";                                
	public String getHobt2PropertyName (){ return "hobt2"; }
	public void setHobt2 (String value){ this.hobt2 = value;   }   
	public String getHobt2 (){ return this.hobt2;   }  
	
	private String hobt3 = "";                                
	public String getHobt3PropertyName (){ return "hobt3"; }
	public void setHobt3 (String value){ this.hobt3 = value;   }   
	public String getHobt3 (){ return this.hobt3;   }  
	
	private String hoht1 = "";                                
	public String getHoht1PropertyName (){ return "hoht1"; }
	public void setHoht1 (String value){ this.hoht1 = value;   }   
	public String getHoht1 (){ return this.hoht1;   }  
	
	private String hoht2 = "";                                
	public String getHoht2PropertyName (){ return "hoht2"; }
	public void setHoht2 (String value){ this.hoht2 = value;   }   
	public String getHoht2 (){ return this.hoht2;   }  
	
	private String hoht3 = "";                                
	public String getHoht3PropertyName (){ return "hoht3"; }
	public void setHoht3 (String value){ this.hoht3 = value;   }   
	public String getHoht3 (){ return this.hoht3;   }  
	
	private String hoht4 = "";                                
	public String getHoht4PropertyName (){ return "hoht4"; }
	public void setHoht4 (String value){ this.hoht4 = value;   }   
	public String getHoht4 (){ return this.hoht4;   }  
	
	private String hoht5 = "";                                
	public String getHoht5PropertyName (){ return "hoht5"; }
	public void setHoht5 (String value){ this.hoht5 = value;   }   
	public String getHoht5 (){ return this.hoht5;   }  
	
	private String hoht6 = "";                                
	public String getHoht6PropertyName (){ return "hoht6"; }
	public void setHoht6 (String value){ this.hoht6 = value;   }   
	public String getHoht6 (){ return this.hoht6;   }  
	
	private String hoht7 = "";                                
	public String getHoht7PropertyName (){ return "hoht7"; }
	public void setHoht7 (String value){ this.hoht7 = value;   }   
	public String getHoht7 (){ return this.hoht7;   }  
	
	
}
