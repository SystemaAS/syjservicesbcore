package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

public class KosttDao implements Serializable, IDao {

	private String kttyp = "";                                
	public void setKttyp (String value){ this.kttyp = value;   }   
	public String getKttyp (){ return this.kttyp;   }              

	private String ktnr = "";                                
	public void setKtnr (String value){ this.ktnr = value;   }   
	public String getKtnr (){ return this.ktnr;   }              

	private String ktna = "";                                
	public void setKtna (String value){ this.ktna = value;   }   
	public String getKtna (){ return this.ktna;   }              


}
