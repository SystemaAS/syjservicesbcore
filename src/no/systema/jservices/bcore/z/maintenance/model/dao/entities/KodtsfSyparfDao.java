package no.systema.jservices.bcore.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Okt 17, 2016
 * 
 */
public class KodtsfSyparfDao implements Serializable, IDao {
	
	//KODTSF table
	private String kosfst = "";                                
	public String getKosfstPropertyName (){ return "kosfst"; }
	public void setKosfst (String value){ this.kosfst = value;   }   
	public String getKosfst (){ return this.kosfst;   }  

	private String kosfun = "SF";                                
	public String getKosfunPropertyName (){ return "kosfun"; }
	public void setKosfun (String value){ this.kosfun = value;   }   
	public String getKosfun (){ return this.kosfun;   }  

	private String kosfsi = "";                                
	public String getKosfsiPropertyName (){ return "kosfsi"; }
	public void setKosfsi (String value){ this.kosfsi = value;   }   
	public String getKosfsi (){ return this.kosfsi;   }  

	private String kosfnv = "";                                
	public String getKosfnvPropertyName (){ return "kosfnv"; }
	public void setKosfnv (String value){ this.kosfnv = value;   }   
	public String getKosfnv (){ return this.kosfnv;   }  

	private String kosfxx = "";                                
	public String getKosfxxPropertyName (){ return "kosfxx"; }
	public void setKosfxx (String value){ this.kosfxx = value;   }   
	public String getKosfxx (){ return this.kosfxx;   }  

	
	//SYPARF table
	private String syrecn = "";                             
	public String getSyrecnPropertyName (){ return "syrecn"; }
	public void setSyrecn (String value){ this.syrecn = value;   }   
	public String getSyrecn (){ return this.syrecn;   }  
	
	private String syuser = "";                                
	public String getSyuserPropertyName (){ return "syuser"; }
	public void setSyuser (String value){ this.syuser = value;   }   
	public String getSyuser (){ return this.syuser;   }  
	    
	private String sykunr = "0";                                
	public String getSykunrPropertyName (){ return "sykunr"; }
	public void setSykunr (String value){ this.sykunr = value;   }   
	public String getSykunr (){ return this.sykunr;   }  
	
	private String syavd = "0";                                
	public String getSyavdPropertyName (){ return "syavd"; }
	public void setSyavd (String value){ this.syavd = value;   }   
	public String getSyavd (){ return this.syavd;   }  
	
	private String sypaid = "USRID";                                
	public String getSypaidPropertyName (){ return "sypaid"; }
	public void setSypaid (String value){ this.sypaid = value;   }   
	public String getSypaid (){ return this.sypaid;   }  
	
	private String sysort = "0";                                
	public String getSysortPropertyName (){ return "sysort"; }
	public void setSysort (String value){ this.sysort = value;   }   
	public String getSysort (){ return this.sysort;   }  
	
	private String syvrdn = "0";                                
	public String getSyvrdnPropertyName (){ return "syvrdn"; }
	public void setSyvrdn (String value){ this.syvrdn = value;   }   
	public String getSyvrdn (){ return this.syvrdn;   }  
	
	private String syvrda = "";                                
	public String getSyvrdaPropertyName (){ return "syvrda"; }
	public void setSyvrda (String value){ this.syvrda = value;   }   
	public String getSyvrda (){ return this.syvrda;   }  

	
}
