package no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.*;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Sep 30, 2016
 * 
 */
public class TrustdDao implements Serializable, IDao {
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private String thst = "";                             
	public String getThstPropertyName (){ return "thst"; }
	public void setThst (String value){ this.thst = value;   }   
	public String getThst (){ return this.thst;   }  
	
	private String thavd = "";                                
	public String getThavdPropertyName (){ return "thavd"; }
	public void setThavd (String value){ this.thavd = value;   }   
	public String getThavd (){ return this.thavd;   }  
	
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
	
	
	private String thtdn = "";                                
	public String getThtdnPropertyName (){ return "thtdn"; }
	public void setThtdn (String value){ this.thtdn = value;   }   
	public String getThtdn (){ return this.thtdn;   }  
	
	private String thsg = "";                                
	public String getThsgPropertyName (){ return "thsg"; }
	public void setThsg (String value){ this.thsg = value;   }   
	public String getThsg (){ return this.thsg;   }  
	
	private String thdt = "";                                
	public String getThdtPropertyName (){ return "thdt"; }
	public void setThdt (String value){ this.thdt = value;   }   
	public String getThdt (){ return this.thdt;   }  
	
	private String thenkl = "";                                
	public String getThenklPropertyName (){ return "thenkl"; }
	public void setThenkl (String value){ this.thenkl = value;   }   
	public String getThenkl (){ return this.thenkl;   }  
	
	private String thdk = "";                                
	public String getThdkPropertyName (){ return "thdk"; }
	public void setThdk (String value){ this.thdk = value;   }   
	public String getThdk (){ return this.thdk;   }  
	
	private String thkna = "";                                
	public String getThknaPropertyName (){ return "thkna"; }
	public void setThkna (String value){ this.thkna = value;   }   
	public String getThkna (){ return this.thkna;   }  
	
	private String thnaa = "";                                
	public String getThnaaPropertyName (){ return "thnaa"; }
	public void setThnaa (String value){ this.thnaa = value;   }   
	public String getThnaa (){ return this.thnaa;   }  
	
	private String thada1 = "";                                
	public String getThada1PropertyName (){ return "thada1"; }
	public void setThada1 (String value){ this.thada1 = value;   }   
	public String getThada1 (){ return this.thada1;   }  
	
	private String thpna = "";                                
	public String getThpnaPropertyName (){ return "thpna"; }
	public void setThpna (String value){ this.thpna = value;   }   
	public String getThpna (){ return this.thpna;   }  
	
	private String thpsa = "";                                
	public String getThpsaPropertyName (){ return "thpsa"; }
	public void setThpsa (String value){ this.thpsa = value;   }   
	public String getThpsa (){ return this.thpsa;   }  
	
	private String thlka = "";                                
	public String getThlkaPropertyName (){ return "thlka"; }
	public void setThlka (String value){ this.thlka = value;   }   
	public String getThlka (){ return this.thlka;   }  
	
	private String thska = "";                                
	public String getThskaPropertyName (){ return "thska"; }
	public void setThska (String value){ this.thska = value;   }   
	public String getThska (){ return this.thska;   }  
	
	private String thtina = "";                                
	public String getThtinaPropertyName (){ return "thtina"; }
	public void setThtina (String value){ this.thtina = value;   }   
	public String getThtina (){ return this.thtina;   }  
	
	private String thkns = "";     
	public String getThknsPropertyName (){ return "thkns"; }
	public void setThkns (String value){ this.thkns = value;   }   
	public String getThkns (){ return this.thkns;   }  
	
	private String thnas = "";                                
	public String getThnasPropertyName (){ return "thnas"; }
	public void setThnas (String value){ this.thnas = value;   }   
	public String getThnas (){ return this.thnas;   }  
	
	private String thads1 = "";                                
	public String getThads1PropertyName (){ return "thads1"; }
	public void setThads1 (String value){ this.thads1 = value;   }   
	public String getThads1 (){ return this.thads1;   }  
	
	private String thpns = "";                                
	public String getThpnsPropertyName (){ return "thpns"; }
	public void setThpns (String value){ this.thpns = value;   }   
	public String getThpns (){ return this.thpns;   }  
	
	private String thpss = "";                                
	public String getThpssPropertyName (){ return "thpss"; }
	public void setThpss (String value){ this.thpss = value;   }   
	public String getThpss (){ return this.thpss;   }  
		
	private String thlks = "";                                
	public String getThlksPropertyName (){ return "thlks"; }
	public void setThlks (String value){ this.thlks = value;   }   
	public String getThlks (){ return this.thlks;   }  
	
	private String thsks = "";                                
	public String getThsksPropertyName (){ return "thsks"; }
	public void setThsks (String value){ this.thsks = value;   }   
	public String getThsks (){ return this.thsks;   }  
	
	private String thtins = "";                                
	public String getThtinsPropertyName (){ return "thtins"; }
	public void setThtins (String value){ this.thtins = value;   }   
	public String getThtins (){ return this.thtins;   }  
	
	private String thknk = "";                                
	public String getThknkPropertyName (){ return "thknk"; }
	public void setThknk (String value){ this.thknk = value;   }   
	public String getThknk (){ return this.thknk;   }  
	
	private String thnak = "";                                
	public String getThnakPropertyName (){ return "thnak"; }
	public void setThnak (String value){ this.thnak = value;   }   
	public String getThnak (){ return this.thnak;   }  
	
	private String thadk1 = "";                                
	public String getThadk1PropertyName (){ return "thadk1"; }
	public void setThadk1 (String value){ this.thadk1 = value;   }   
	public String getThadk1 (){ return this.thadk1;   }  
	
	private String thpnk = "";                                
	public String getThpnkPropertyName (){ return "thpnk"; }
	public void setThpnk (String value){ this.thpnk = value;   }   
	public String getThpnk (){ return this.thpnk;   }  
	
	private String thpsk = "";                                
	public String getThpskPropertyName (){ return "thpsk"; }
	public void setThpsk (String value){ this.thpsk = value;   }   
	public String getThpsk (){ return this.thpsk;   }  
	
	private String thlkk = "";                                
	public String getThlkkPropertyName (){ return "thlkk"; }
	public void setThlkk (String value){ this.thlkk = value;   }   
	public String getThlkk (){ return this.thlkk;   }  
	
	private String thskk = "";                                
	public String getThskkPropertyName (){ return "thskk"; }
	public void setThskk (String value){ this.thskk = value;   }   
	public String getThskk (){ return this.thskk;   }  
	
	private String thtink = "";                                
	public String getThtinkPropertyName (){ return "thtink"; }
	public void setThtink (String value){ this.thtink = value;   }   
	public String getThtink (){ return this.thtink;   }  
	
	private String thblk = "";                                
	public String getThblkPropertyName (){ return "thblk"; }
	public void setThblk (String value){ this.thblk = value;   }   
	public String getThblk (){ return this.thblk;   }  

	private String thlsd = "";                                
	public String getThlsdPropertyName (){ return "thlsd"; }
	public void setThlsd (String value){ this.thlsd = value;   }   
	public String getThlsd (){ return this.thlsd;   }  
	
	private String thalk = "";                                
	public String getThalkPropertyName (){ return "thalk"; }
	public void setThalk (String value){ this.thalk = value;   }   
	public String getThalk (){ return this.thalk;   }  
	
	private String thtrm = "";                                
	public String getThtrmPropertyName (){ return "thtrm"; }
	public void setThtrm (String value){ this.thtrm = value;   }   
	public String getThtrm (){ return this.thtrm;   }  

	private String thtaid = "";                                
	public String getThtaidPropertyName (){ return "thtaid"; }
	public void setThtaid (String value){ this.thtaid = value;   }   
	public String getThtaid (){ return this.thtaid;   }  
	
	private String thtask = "";                                
	public String getThtaskPropertyName (){ return "thtask"; }
	public void setThtask (String value){ this.thtask = value;   }   
	public String getThtask (){ return this.thtask;   }  
	
	private String thtalk = "";                                
	public String getThtalkPropertyName (){ return "thtalk"; }
	public void setThtalk (String value){ this.thtalk = value;   }   
	public String getThtalk (){ return this.thtalk;   }  

	private String thtgid = "";                                
	public String getThtgidPropertyName (){ return "thtgid"; }
	public void setThtgid (String value){ this.thtgid = value;   }   
	public String getThtgid (){ return this.thtgid;   }  
	
	private String thtgsk = "";                                
	public String getThtgskPropertyName (){ return "thtgsk"; }
	public void setThtgsk (String value){ this.thtgsk = value;   }   
	public String getThtgsk (){ return this.thtgsk;   }  
	
	private String thtglk = "";                                
	public String getThtglkPropertyName (){ return "thtglk"; }
	public void setThtglk (String value){ this.thtglk = value;   }   
	public String getThtglk (){ return this.thtglk;   }  

	private String thskfd = "";                                
	public String getThskfdPropertyName (){ return "thskfd"; }
	public void setThskfd (String value){ this.thskfd = value;   }   
	public String getThskfd (){ return this.thskfd;   }  
	
	private String thcats = "";                                
	public String getThcatsPropertyName (){ return "thcats"; }
	public void setThcats (String value){ this.thcats = value;   }   
	public String getThcats (){ return this.thcats;   }  
	
	private String thcdt = "";                                
	public String getThcdtPropertyName (){ return "thcdt"; }
	public void setThcdt (String value){ this.thcdt = value;   }   
	public String getThcdt (){ return this.thcdt;   }  

	
	private String thcna = "";                                
	public String getThcnaPropertyName (){ return "thcna"; }
	public void setThcna (String value){ this.thcna = value;   }   
	public String getThcna (){ return this.thcna;   }  
	
	private String thcnr = "";                                
	public String getThcnrPropertyName (){ return "thcnr"; }
	public void setThcnr (String value){ this.thcnr = value;   }   
	public String getThcnr (){ return this.thcnr;   }  
	
	private String thtsd1 = "";                                
	public String getThtsd1PropertyName (){ return "thtsd1"; }
	public void setThtsd1 (String value){ this.thtsd1 = value;   }   
	public String getThtsd1 (){ return this.thtsd1;   }  

	private String thtsd2 = "";                                
	public String getThtsd2PropertyName (){ return "thtsd2"; }
	public void setThtsd2 (String value){ this.thtsd2 = value;   }   
	public String getThtsd2 (){ return this.thtsd2;   }  
	
	private String thtsd3 = "";                                
	public String getThtsd3PropertyName (){ return "thtsd3"; }
	public void setThtsd3 (String value){ this.thtsd3 = value;   }   
	public String getThtsd3 (){ return this.thtsd3;   }  
	
	private String thtsd4 = "";                                
	public String getThtsd4PropertyName (){ return "thtsd4"; }
	public void setThtsd4 (String value){ this.thtsd4 = value;   }   
	public String getThtsd4 (){ return this.thtsd4;   }  
	
	private String thtsd5 = "";                                
	public String getThtsd5PropertyName (){ return "thtsd5"; }
	public void setThtsd5 (String value){ this.thtsd5 = value;   }   
	public String getThtsd5 (){ return this.thtsd5;   }  

	private String thtsd6 = "";                                
	public String getThtsd6PropertyName (){ return "thtsd6"; }
	public void setThtsd6 (String value){ this.thtsd6 = value;   }   
	public String getThtsd6 (){ return this.thtsd6;   }  
	
	private String thtsd7 = "";                                
	public String getThtsd7PropertyName (){ return "thtsd7"; }
	public void setThtsd7 (String value){ this.thtsd7 = value;   }   
	public String getThtsd7 (){ return this.thtsd7;   }  
	
	private String thtsd8 = "";                                
	public String getThtsd8PropertyName (){ return "thtsd8"; }
	public void setThtsd8 (String value){ this.thtsd8 = value;   }   
	public String getThtsd8 (){ return this.thtsd8;   }  
	
	
	private String thtsb = "";                                
	public String getThtsbPropertyName (){ return "thtsb"; }
	public void setThtsb (String value){ this.thtsb = value;   }   
	public String getThtsb (){ return this.thtsb;   }  

	private String thddt = "";                                
	public String getThddtPropertyName (){ return "thddt"; }
	public void setThddt (String value){ this.thddt = value;   }   
	public String getThddt (){ return this.thddt;   }  
	
	private String thdfkd = "";                                
	public String getThdfkdPropertyName (){ return "thdfkd"; }
	public void setThdfkd (String value){ this.thdfkd = value;   }   
	public String getThdfkd (){ return this.thdfkd;   }  
	
	private String thdfsk = "";                                
	public String getThdfskPropertyName (){ return "thdfsk"; }
	public void setThdfsk (String value){ this.thdfsk = value;   }   
	public String getThdfsk (){ return this.thdfsk;   }
	
	private String thdkr = "";                                
	public String getThdkrPropertyName (){ return "thdkr"; }
	public void setThdkr (String value){ this.thdkr = value;   }   
	public String getThdkr (){ return this.thdkr;   }  

	private String thdant = "";                                
	public String getThdantPropertyName (){ return "thdant"; }
	public void setThdant (String value){ this.thdant = value;   }   
	public String getThdant (){ return this.thdant;   }  
	
	private String thddtk = "";                                
	public String getThddtkPropertyName (){ return "thddtk"; }
	public void setThddtk (String value){ this.thddtk = value;   }   
	public String getThddtk (){ return this.thddtk;   }  
	
	private String thdats = "";                                
	public String getThdatsPropertyName (){ return "thdats"; }
	public void setThdats (String value){ this.thdats = value;   }   
	public String getThdats (){ return this.thdats;   }
	
	private String thdkav = "";                                
	public String getThdkavPropertyName (){ return "thdkav"; }
	public void setThdkav (String value){ this.thdkav = value;   }   
	public String getThdkav (){ return this.thdkav;   }  

	private String thdksk = "";                                
	public String getThdkskPropertyName (){ return "thdksk"; }
	public void setThdksk (String value){ this.thdksk = value;   }   
	public String getThdksk (){ return this.thdksk;   }  
	
	private String thgkd = "";                                
	public String getThgkdPropertyName (){ return "thgkd"; }
	public void setThgkd (String value){ this.thgkd = value;   }   
	public String getThgkd (){ return this.thgkd;   }  
	
	private String thgft1 = "";                                
	public String getThgft1PropertyName (){ return "thgft1"; }
	public void setThgft1 (String value){ this.thgft1 = value;   }   
	public String getThgft1 (){ return this.thgft1;   }
	
	private String thgft2 = "";                                
	public String getThgft2PropertyName (){ return "thgft2"; }
	public void setThgft2 (String value){ this.thgft2 = value;   }   
	public String getThgft2 (){ return this.thgft2;   }  

	private String thgadk = "";                                
	public String getThgadkPropertyName (){ return "thgadk"; }
	public void setThgadk (String value){ this.thgadk = value;   }   
	public String getThgadk (){ return this.thgadk;   }  
	
	private String thgbl = "";                                
	public String getThgblPropertyName (){ return "thgbl"; }
	public void setThgbl (String value){ this.thgbl = value;   }   
	public String getThgbl (){ return this.thgbl;   }  
	
	private String thgpr = "";                                
	public String getThgprPropertyName (){ return "thgpr"; }
	public void setThgpr (String value){ this.thgpr = value;   }   
	public String getThgpr (){ return this.thgpr;   }
	
	private String thgvk = "";                                
	public String getThgvkPropertyName (){ return "thgvk"; }
	public void setThgvk (String value){ this.thgvk = value;   }   
	public String getThgvk (){ return this.thgvk;   }  

	private String thgbgi = "";                                
	public String getThgbgiPropertyName (){ return "thgbgi"; }
	public void setThgbgi (String value){ this.thgbgi = value;   }   
	public String getThgbgi (){ return this.thgbgi;   }  
	
	private String thgbgu = "";                                
	public String getThgbguPropertyName (){ return "thgbgu"; }
	public void setThgbgu (String value){ this.thgbgu = value;   }   
	public String getThgbgu (){ return this.thgbgu;   }  
	
	private String thkdc = "";                                
	public String getThkdcPropertyName (){ return "thkdc"; }
	public void setThkdc (String value){ this.thkdc = value;   }   
	public String getThkdc (){ return this.thkdc;   }
	
	private String thtssd = "";                                
	public String getThtssdPropertyName (){ return "thtssd"; }
	public void setThtssd (String value){ this.thtssd = value;   }   
	public String getThtssd (){ return this.thtssd;   }  

	private String thtsn1 = "";                                
	public String getThtsn1PropertyName (){ return "thtsn1"; }
	public void setThtsn1 (String value){ this.thtsn1 = value;   }   
	public String getThtsn1 (){ return this.thtsn1;   }  
	
	private String thtsn2 = "";                                
	public String getThtsn2PropertyName (){ return "thtsn2"; }
	public void setThtsn2 (String value){ this.thtsn2 = value;   }   
	public String getThtsn2 (){ return this.thtsn2;   }  
	
	private String thtspn = "";                                
	public String getThtspnPropertyName (){ return "thtspn"; }
	public void setThtspn (String value){ this.thtspn = value;   }   
	public String getThtspn (){ return this.thtspn;   }
	
	private String thtsps = "";                                
	public String getThtspsPropertyName (){ return "thtsps"; }
	public void setThtsps (String value){ this.thtsps = value;   }   
	public String getThtsps (){ return this.thtsps;   }  

	private String thtslk = "";                                
	public String getThtslkPropertyName (){ return "thtslk"; }
	public void setThtslk (String value){ this.thtslk = value;   }   
	public String getThtslk (){ return this.thtslk;   }  
	
	private String thtssk = "";                                
	public String getThtsskPropertyName (){ return "thtssk"; }
	public void setThtssk (String value){ this.thtssk = value;   }   
	public String getThtssk (){ return this.thtssk;   }  
	
	
	
	
	private String thlstl = "";                                
	public String getThlstlPropertyName (){ return "thlstl"; }
	public void setThlstl (String value){ this.thlstl = value;   }   
	public String getThlstl (){ return this.thlstl;   }  
	
	private String thvpos = "";                                
	public String getThvposPropertyName (){ return "thvpos"; }
	public void setThvpos (String value){ this.thvpos = value;   }   
	public String getThvpos (){ return this.thvpos;   }  
	
	private String thntk = "";                                
	public String getThntkPropertyName (){ return "thntk"; }
	public void setThntk (String value){ this.thntk = value;   }   
	public String getThntk (){ return this.thntk;   }  
	
	private String thvkb = "";                                
	public String getThvkbPropertyName (){ return "thvkb"; }
	public void setThvkb (String value){ this.thvkb = value;   }   
	public String getThvkb (){ return this.thvkb;   }  
	
	private String thdst = "";                                
	public String getThdstPropertyName (){ return "thdst"; }
	public void setThdst (String value){ this.thdst = value;   }   
	public String getThdst (){ return this.thdst;   }  
	
	
	private String thdsk = "";                                
	public String getThdskPropertyName (){ return "thdsk"; }
	public void setThdsk (String value){ this.thdsk = value;   }   
	public String getThdsk (){ return this.thdsk;   }
	
	private String thtarf = "";                                
	public String getThtarfPropertyName (){ return "thtarf"; }
	public void setThtarf (String value){ this.thtarf = value;   }   
	public String getThtarf (){ return this.thtarf;   }  

	private String thws = "";                                
	public String getThwsPropertyName (){ return "thws"; }
	public void setThws (String value){ this.thws = value;   }   
	public String getThws (){ return this.thws;   }  
	
	private String thtrnr = "";                                
	public String getThtrnrPropertyName (){ return "thtrnr"; }
	public void setThtrnr (String value){ this.thtrnr = value;   }   
	public String getThtrnr (){ return this.thtrnr;   }  
	
	private String thtrdt = "";                                
	public String getThtrdtPropertyName (){ return "thtrdt"; }
	public void setThtrdt (String value){ this.thtrdt = value;   }   
	public String getThtrdt (){ return this.thtrdt;   }
	
	private String thomd = "";                                
	public String getThomdPropertyName (){ return "thomd"; }
	public void setThomd (String value){ this.thomd = value;   }   
	public String getThomd (){ return this.thomd;   }  

	private String thtet = "";                                
	public String getThtetPropertyName (){ return "thtet"; }
	public void setThtet (String value){ this.thtet = value;   }   
	public String getThtet (){ return this.thtet;   }  
	
	private String thnttd = "";                                
	public String getThnttdPropertyName (){ return "thnttd"; }
	public void setThnttd (String value){ this.thnttd = value;   }   
	public String getThnttd (){ return this.thnttd;   }  
	
	private String thntll = "";                                
	public String getThntllPropertyName (){ return "thntll"; }
	public void setThntll (String value){ this.thntll = value;   }   
	public String getThntll (){ return this.thntll;   }
	
	
	private String thfmll = "";                                
	public String getThfmllPropertyName (){ return "thfmll"; }
	public void setThfmll (String value){ this.thfmll = value;   }   
	public String getThfmll (){ return this.thfmll;   }  

	private String thnpns = "";                                
	public String getThnpnsPropertyName (){ return "thnpns"; }
	public void setThnpns (String value){ this.thnpns = value;   }   
	public String getThnpns (){ return this.thnpns;   }  
	
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
	
	private String thekst = "";                                
	public String getThekstPropertyName (){ return "thekst"; }
	public void setThekst (String value){ this.thekst = value;   }   
	public String getThekst (){ return this.thekst;   }  
	
	//Sikkerhed
	private String thsik = "";                                
	public String getThsikPropertyName (){ return "thsik"; }
	public void setThsik (String value){ this.thsik = value;   }   
	public String getThsik (){ return this.thsik;   }  
	
	private String thdta = "";                                
	public String getThdtaPropertyName (){ return "thdta"; }
	public void setThdta (String value){ this.thdta = value;   }   
	public String getThdta (){ return this.thdta;   }  
	
	private String thtma = "";                                
	public String getThtmaPropertyName (){ return "thtma"; }
	public void setThtma (String value){ this.thtma = value;   }   
	public String getThtma (){ return this.thtma;   }  
	
	private String thspom= "";                                
	public String getThspomPropertyName (){ return "thspom"; }
	public void setThspom (String value){ this.thspom = value;   }   
	public String getThspom (){ return this.thspom;   }  
	
	private String thtkbm = "";                                
	public String getThtkbmPropertyName (){ return "thtkbm"; }
	public void setThtkbm (String value){ this.thtkbm = value;   }   
	public String getThtkbm (){ return this.thtkbm;   }  
	
	private String thkref = "";                                
	public String getThkrefPropertyName (){ return "thkref"; }
	public void setThkref (String value){ this.thkref = value;   }   
	public String getThkref (){ return this.thkref;   }  
	
	private String thtref = "";                                
	public String getThtrefPropertyName (){ return "thtref"; }
	public void setThtref (String value){ this.thtref = value;   }   
	public String getThtref (){ return this.thtref;   }  
	
	private String thlosd = "";                                
	public String getThlosdPropertyName (){ return "thlosd"; }
	public void setThlosd (String value){ this.thlosd = value;   }   
	public String getThlosd (){ return this.thlosd;   }  
	
	private String thlosdsk = "";                                
	public String getThlosdskPropertyName (){ return "thlosdsk"; }
	public void setThlosdsk (String value){ this.thlosdsk = value;   }   
	public String getThlosdsk (){ return this.thlosdsk;   }  
	
	private String thlkr1 = "";                                
	public String getThlkr1PropertyName (){ return "thlkr1"; }
	public void setThlkr1 (String value){ this.thlkr1 = value;   }   
	public String getThlkr1 (){ return this.thlkr1;   }  
	
	private String thlkr2 = "";                                
	public String getThlkr2PropertyName (){ return "thlkr2"; }
	public void setThlkr2 (String value){ this.thlkr2 = value;   }   
	public String getThlkr2 (){ return this.thlkr2;   }  
	
	private String thlkr3 = "";                                
	public String getThlkr3PropertyName (){ return "thlkr3"; }
	public void setThlkr3 (String value){ this.thlkr3 = value;   }   
	public String getThlkr3 (){ return this.thlkr3;   }  
	
	private String thlkr4 = "";                                
	public String getThlkr4PropertyName (){ return "thlkr4"; }
	public void setThlkr4 (String value){ this.thlkr4 = value;   }   
	public String getThlkr4 (){ return this.thlkr4;   }  
	
	private String thlkr5 = "";                                
	public String getThlkr5PropertyName (){ return "thlkr5"; }
	public void setThlkr5 (String value){ this.thlkr5 = value;   }   
	public String getThlkr5 (){ return this.thlkr5;   }  
	
	private String thlkr6 = "";                                
	public String getThlkr6PropertyName (){ return "thlkr6"; }
	public void setThlkr6 (String value){ this.thlkr6 = value;   }   
	public String getThlkr6 (){ return this.thlkr6;   }  
	
	private String thlkr7 = "";                                
	public String getThlkr7PropertyName (){ return "thlkr7"; }
	public void setThlkr7 (String value){ this.thlkr7 = value;   }   
	public String getThlkr7 (){ return this.thlkr7;   }  
	
	private String thlkr8 = "";                                
	public String getThlkr8PropertyName (){ return "thlkr8"; }
	public void setThlkr8 (String value){ this.thlkr8 = value;   }   
	public String getThlkr8 (){ return this.thlkr8;   }  
	
	private String thknss = "";                                
	public String getThknssPropertyName (){ return "thknss"; }
	public void setThknss (String value){ this.thknss = value;   }   
	public String getThknss (){ return this.thknss;   }  
	
	private String thnass = "";                                
	public String getThnassPropertyName (){ return "thnass"; }
	public void setThnass (String value){ this.thnass = value;   }   
	public String getThnass (){ return this.thnass;   }  
	
	private String thadss1 = "";                                
	public String getThadss1PropertyName (){ return "thadss1"; }
	public void setThadss1 (String value){ this.thadss1 = value;   }   
	public String getThadss1 (){ return this.thadss1;   }  
	
	private String thpnss = "";                                
	public String getThpnssPropertyName (){ return "thpnss"; }
	public void setThpnss (String value){ this.thpnss = value;   }   
	public String getThpnss (){ return this.thpnss;   }  
	
	private String thpsss = "";                                
	public String getThpsssPropertyName (){ return "thpsss"; }
	public void sethpsss (String value){ this.thpsss = value;   }   
	public String getThpsss (){ return this.thpsss;   }  
	
	private String thllss = "";                                
	public String getThllssPropertyName (){ return "thllss"; }
	public void setThllss (String value){ this.thllss = value;   }   
	public String getThllss (){ return this.thllss;   }  
	
	private String thskss = "";                                
	public String getThskssPropertyName (){ return "thskss"; }
	public void setThskss (String value){ this.thskss = value;   }   
	public String getThskss (){ return this.thskss;   }  
	
	private String thtinss = "";                                
	public String getThtinssPropertyName (){ return "thtinss"; }
	public void setThtinss (String value){ this.thtinss = value;   }   
	public String getThtinss (){ return this.thtinss;   }  
	
	private String thknks = "";                                
	public String getThknksPropertyName (){ return "thknks"; }
	public void setThknks (String value){ this.thknks = value;   }   
	public String getThknks (){ return this.thknks;   } 
	
	private String thnaks = "";                                
	public String getThnaksPropertyName (){ return "thnaks"; }
	public void setThnaks (String value){ this.thnaks = value;   }   
	public String getThnaks (){ return this.thnaks;   }  
	
	private String thadks1 = "";                                
	public String getThadks1PropertyName (){ return "thadks1"; }
	public void setThadks1 (String value){ this.thadks1 = value;   }   
	public String getThadks1 (){ return this.thadks1;   }  
	
	private String thpnks = "";                                
	public String getThpnksPropertyName (){ return "thpnks"; }
	public void setThpnks (String value){ this.thpnks = value;   }   
	public String getThpnks (){ return this.thpnks;   }  
	
	private String thpsks = "";                                
	public String getThpsksPropertyName (){ return "thpsks"; }
	public void setThpsks (String value){ this.thpsks = value;   }   
	public String getThpsks (){ return this.thpsks;   }  
	
	private String thlkks = "";                                
	public String getThlkksPropertyName (){ return "thlkks"; }
	public void setThlkks (String value){ this.thlkks = value;   }   
	public String getThlkks (){ return this.thlkks;   }  
	
	private String thskks = "";                                
	public String getThskksPropertyName (){ return "thskks"; }
	public void setThskks (String value){ this.thskks = value;   }   
	public String getThskks (){ return this.thskks;   }  
	
	private String thtinks = "";                                
	public String getThtinksPropertyName (){ return "thtinks"; }
	public void setThtinks (String value){ this.thtinks = value;   }   
	public String getThtinks (){ return this.thtinks;   }  
	
	private String thknt = "";                                
	public String getThkntPropertyName (){ return "thknt"; }
	public void setThknt (String value){ this.thknt = value;   }   
	public String getThknt (){ return this.thknt;   }  
	
	private String thnat = "";                                
	public String getThnatPropertyName (){ return "thnat"; }
	public void setThnat (String value){ this.thnat = value;   }   
	public String getThnat (){ return this.thnat;   }  
	
	private String thadt1 = "";                                
	public String getThadt1PropertyName (){ return "thadt1"; }
	public void setThadt1 (String value){ this.thadt1 = value;   }   
	public String getThadt1 (){ return this.thadt1;   }  
	
	private String thpnt = "";                                
	public String getThpntPropertyName (){ return "thpnt"; }
	public void setThpnt (String value){ this.thpnt = value;   }   
	public String getThpnt (){ return this.thpnt;   }  
	
	private String thpst = "";                                
	public String getThpstPropertyName (){ return "thpst"; }
	public void setThpst (String value){ this.thpst = value;   }   
	public String getThpst (){ return this.thpst;   }  
	
	private String thlkt = "";                                
	public String getThlktPropertyName (){ return "thlkt"; }
	public void setThlkt (String value){ this.thlkt = value;   }   
	public String getThlkt (){ return this.thlkt;   }  
	
	private String thskt = "";                                
	public String getThsktPropertyName (){ return "thskt"; }
	public void setThskt (String value){ this.thskt = value;   }   
	public String getThskt (){ return this.thskt;   }  
	
	private String thtint = "";                                
	public String getThtintPropertyName (){ return "thtint"; }
	public void setThtint (String value){ this.thtint = value;   }   
	public String getThtint (){ return this.thtint;   }  
	
	
}
