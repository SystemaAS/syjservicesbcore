package no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.*;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Sep 20, 2016
 * 
 */
public class TristdDao implements Serializable, IDao {
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private String tist = "";                             
	public String getTistPropertyName (){ return "tist"; }
	public void setTist (String value){ this.tist = value;   }   
	public String getTist (){ return this.tist;   }  
	
	private String tiavd = "";                                
	public String getTiavdPropertyName (){ return "tiavd"; }
	public void setTiavd (String value){ this.tiavd = value;   }   
	public String getTiavd (){ return this.tiavd;   }  
	
	private String koanvn = "";                                
	public String getKoanvnPropertyName (){ return "koanvn"; }
	public void setKoanvn (String value){ this.koanvn = value;   }   
	public String getKoanvn (){ return this.koanvn;   }  
	
	private String koaknr = "";                                
	public String getKoaknrPropertyName (){ return "koaknr"; }
	public void setKoaknr (String value){ this.koaknr = value;   }   
	public String getKoaknr (){ return this.koaknr;   }  
	
	private String syrg = "";                                
	public String getSyrgPropertyName (){ return "syrg"; }
	public void setSyrg (String value){ this.syrg = value;   }   
	public String getSyrg (){ return this.syrg;   }  
	
	
	private String titdn = "";                                
	public String getTitdnPropertyName (){ return "titdn"; }
	public void setTitdn (String value){ this.titdn = value;   }   
	public String getTitdn (){ return this.titdn;   }  
	
	private String tisg = "";                                
	public String getTisgPropertyName (){ return "tisg"; }
	public void setTisg (String value){ this.tisg = value;   }   
	public String getTisg (){ return this.tisg;   }  
	
	private String tidt = "";                                
	public String getTidtPropertyName (){ return "tidt"; }
	public void setTidt (String value){ this.tidt = value;   }   
	public String getTidt (){ return this.tidt;   }  
	
	private String tienkl = "";                                
	public String getTienklPropertyName (){ return "tienkl"; }
	public void setTienkl (String value){ this.tienkl = value;   }   
	public String getTienkl (){ return this.tienkl;   }  
	
	private String titrnr = "";                                
	public String getTitrnrPropertyName (){ return "titrnr"; }
	public void setTitrnr (String value){ this.titrnr = value;   }   
	public String getTitrnr (){ return this.titrnr;   }  
	
	private String tign = "";                                
	public String getTignPropertyName (){ return "tign"; }
	public void setTign (String value){ this.tign = value;   }   
	public String getTign (){ return this.tign;   }  
	
	private String tignsk = "";                                
	public String getTignskPropertyName (){ return "tignsk"; }
	public void setTignsk (String value){ this.tignsk = value;   }   
	public String getTignsk (){ return this.tignsk;   }  
	
	private String tialsk = "";                                
	public String getTialskPropertyName (){ return "tialsk"; }
	public void setTialsk (String value){ this.tialsk = value;   }   
	public String getTialsk (){ return this.tialsk;   }  
	
	private String tials = "";                                
	public String getTialsPropertyName (){ return "tials"; }
	public void setTials (String value){ this.tials = value;   }   
	public String getTials (){ return this.tials;   }  
	
	
	private String tialss = "";                                
	public String getTialssPropertyName (){ return "tialss"; }
	public void setTialss (String value){ this.tialss = value;   }   
	public String getTialss (){ return this.tialss;   }  
	
	private String tiglsk = "";                                
	public String getTiglskPropertyName (){ return "tiglsk"; }
	public void setTiglsk (String value){ this.tiglsk = value;   }   
	public String getTiglsk (){ return this.tiglsk;   }  
	
	private String tiacts = "";                                
	public String getTiactsPropertyName (){ return "tiacts"; }
	public void setTiacts (String value){ this.tiacts = value;   }   
	public String getTiacts (){ return this.tiacts;   }  
	
	private String tiskb = "";                                
	public String getTiskbPropertyName (){ return "tiskb"; }
	public void setTiskb (String value){ this.tiskb = value;   }   
	public String getTiskb (){ return this.tiskb;   }  
	
	private String titsb = "";     
	public String getTitsbPropertyName (){ return "titsb"; }
	public void setTitsb (String value){ this.titsb = value;   }   
	public String getTitsb (){ return this.titsb;   }  
	
	private String titarf = "";                                
	public String getTitarfPropertyName (){ return "titarf"; }
	public void setTitarf (String value){ this.titarf = value;   }   
	public String getTitarf (){ return this.titarf;   }  
	
	private String tiws = "";                                
	public String getTiwsPropertyName (){ return "tiws"; }
	public void setTiws (String value){ this.tiws = value;   }   
	public String getTiws (){ return this.tiws;   }  
	
	private String tikn = "";                                
	public String getTiknPropertyName (){ return "tikn"; }
	public void setTikn (String value){ this.tikn = value;   }   
	public String getTikn (){ return this.tikn;   }  
	
	private String tina = "";                                
	public String getTinaPropertyName (){ return "tina"; }
	public void setTina (String value){ this.tina = value;   }   
	public String getTina (){ return this.tina;   }  
		
	private String tiad1 = "";                                
	public String getTiad1PropertyName (){ return "tiad1"; }
	public void setTiad1 (String value){ this.tiad1 = value;   }   
	public String getTiad1 (){ return this.tiad1;   }  
	
	private String tipn = "";                                
	public String getTipnPropertyName (){ return "tipn"; }
	public void setTipn (String value){ this.tipn = value;   }   
	public String getTipn (){ return this.tipn;   }  
	
	private String tips = "";                                
	public String getTipsPropertyName (){ return "tips"; }
	public void setTips (String value){ this.tips = value;   }   
	public String getTips (){ return this.tips;   }  
	
	private String tilk = "";                                
	public String getTilkPropertyName (){ return "tilk"; }
	public void setTilk (String value){ this.tilk = value;   }   
	public String getTilk (){ return this.tilk;   }  
	
	private String tisk = "";                                
	public String getTiskPropertyName (){ return "tisk"; }
	public void setTisk (String value){ this.tisk = value;   }   
	public String getTisk (){ return this.tisk;   }  
	
	private String titin = "";                                
	public String getTitinPropertyName (){ return "titin"; }
	public void setTitin (String value){ this.titin = value;   }   
	public String getTitin (){ return this.titin;   }  
	
	private String s0004 = "";                                
	public String getS0004PropertyName (){ return "s0004"; }
	public void setS0004 (String value){ this.s0004 = value;   }   
	public String getS0004 (){ return this.s0004;   }  
	
	private String s0010 = "";                                
	public String getS0010PropertyName (){ return "s0010"; }
	public void setS0010 (String value){ this.s0010 = value;   }   
	public String getS0010 (){ return this.s0010;   }  
	
	private String s0035 = "";                                
	public String getS0035PropertyName (){ return "s0035"; }
	public void setS0035 (String value){ this.s0035 = value;   }   
	public String getS0035 (){ return this.s0035;   }  
	
	private String s0026 = "";                                
	public String getS0026PropertyName (){ return "s0026"; }
	public void setS0026 (String value){ this.s0026 = value;   }   
	public String getS0026 (){ return this.s0026;   }  
	
	private String tidk = "";                                
	public String getTidkPropertyName (){ return "tidk"; }
	public void setTidk (String value){ this.tidk = value;   }   
	public String getTidk (){ return this.tidk;   }  
	
	private String tialk = "";                                
	public String getTialkPropertyName (){ return "tialk"; }
	public void setTialk (String value){ this.tialk = value;   }   
	public String getTialk (){ return this.tialk;   }  
	
	
}
