package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

public class CusdfDao implements Serializable, IDao {

	private String kundnr = "";                                
	public void setKundnr (String value){ this.kundnr = value;   }   
	public String getKundnr (){ return this.kundnr;   }              

	private String firma = "";                                
	public void setFirma (String value){ this.firma = value;   }   
	public String getFirma (){ return this.firma;   }              

	private String knavn = "";                                
	public void setKnavn (String value){ this.knavn = value;   }   
	public String getKnavn (){ return this.knavn;   }              

	private String adr1 = "";                                
	public void setAdr1 (String value){ this.adr1 = value;   }   
	public String getAdr1 (){ return this.adr1;   }              

	private String adr2 = "";                                
	public void setAdr2 (String value){ this.adr2 = value;   }   
	public String getAdr2 (){ return this.adr2;   }              

	private String adr3 = "";                                
	public void setAdr3 (String value){ this.adr3 = value;   }   
	public String getAdr3 (){ return this.adr3;   }              

	private String postnr = "";                                
	public void setPostnr (String value){ this.postnr = value;   }   
	public String getPostnr (){ return this.postnr;   }              

	private String syrg = "";                                
	public void setSyrg (String value){ this.syrg = value;   }   
	public String getSyrg (){ return this.syrg;   }              

	private String syland = "";                                
	public void setSyland (String value){ this.syland = value;   }   
	public String getSyland (){ return this.syland;   }              

}
