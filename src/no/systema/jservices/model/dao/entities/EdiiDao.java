package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

public class EdiiDao implements Serializable, IDao {

	private String inid = "";                                
	public void setInid (String value){ this.inid = value;   }   
	public String getInid (){ return this.inid;   }              

	private String inna = "";                                
	public void setInna (String value){ this.inna = value;   }   
	public String getInna (){ return this.inna;   }              

	private String innetw = "";                                
	public void setInnetw (String value){ this.innetw = value;   }   
	public String getInnetw (){ return this.innetw;   }              

	private String inex = "";                                
	public void setInex (String value){ this.inex = value;   }   
	public String getInex (){ return this.inex;   }              

	//TODO many more since the table has many columns

}
