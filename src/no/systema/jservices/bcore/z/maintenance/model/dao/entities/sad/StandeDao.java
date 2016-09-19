package no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.*;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Sep 16, 2016
 * 
 */
public class StandeDao implements Serializable, IDao {
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private String sest = "";                             
	public String getSestPropertyName (){ return "sest"; }
	public void setSest (String value){ this.sest = value;   }   
	public String getSest (){ return this.sest;   }  
	
	private String seavd = "";                                
	public String getSeavdPropertyName (){ return "seavd"; }
	public void setSeavd (String value){ this.seavd = value;   }   
	public String getSeavd (){ return this.seavd;   }  
	
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
	
	private String setdn = "";                                
	public String getSetdnPropertyName (){ return "setdn"; }
	public void setSetdn (String value){ this.setdn = value;   }   
	public String getSetdn (){ return this.setdn;   }  
	
	private String sedty = "";                                
	public String getSedtyPropertyName (){ return "sedty"; }
	public void setSedty (String value){ this.sedty = value;   }   
	public String getSedty (){ return this.sedty;   }  
	
	private String sedp = "";                                
	public String getSedpPropertyName (){ return "sedp"; }
	public void setSedp (String value){ this.sedp = value;   }   
	public String getSedp (){ return this.sedp;   }  
	
	private String sekns = "";                                
	public String getSeknsPropertyName (){ return "sekns"; }
	public void setSekns (String value){ this.sekns = value;   }   
	public String getSekns (){ return this.sekns;   }  
	
	private String senas = "";                                
	public String getSenasPropertyName (){ return "senas"; }
	public void setSenas (String value){ this.senas = value;   }   
	public String getSenas (){ return this.senas;   }  
	
	private String seads1 = "";                                
	public String getSeads1PropertyName (){ return "seads1"; }
	public void setSeads1 (String value){ this.seads1 = value;   }   
	public String getSeads1 (){ return this.seads1;   }  
	
	private String seads2 = "";                                
	public String getSeads2PropertyName (){ return "seads2"; }
	public void setSeads2 (String value){ this.seads2 = value;   }   
	public String getSeads2 (){ return this.seads2;   }  
	
	private String seads3 = "";                                
	public String getSeads3PropertyName (){ return "seads3"; }
	public void setSeads3 (String value){ this.seads3 = value;   }   
	public String getSeads3 (){ return this.seads3;   }  
	
	private String sentk = "";                                
	public String getSentkPropertyName (){ return "sentk"; }
	public void setSentk (String value){ this.sentk = value;   }   
	public String getSentk (){ return this.sentk;   }  
	
	
	private String sevkb = "";                                
	public String getSevkbPropertyName (){ return "sevkb"; }
	public void setSevkb (String value){ this.sevkb = value;   }   
	public String getSevkb (){ return this.sevkb;   }  
	
	private String sekdc = "";                                
	public String getSekdcPropertyName (){ return "sekdc"; }
	public void setSekdc (String value){ this.sekdc = value;   }   
	public String getSekdc (){ return this.sekdc;   }  
	
	private String sesg = "";                                
	public String getSesgPropertyName (){ return "sesg"; }
	public void setSesg (String value){ this.sesg = value;   }   
	public String getSesg (){ return this.sesg;   }  
	
	private String seknk = "";                                
	public String getSeknkPropertyName (){ return "seknk"; }
	public void setSeknk (String value){ this.seknk = value;   }   
	public String getSeknk (){ return this.seknk;   }  
	
	private String serg = "";     
	public String getSergPropertyName (){ return "serg"; }
	public void setSerg (String value){ this.serg = value;   }   
	public String getSerg (){ return this.serg;   }  
	
	private String senak = "";                                
	public String getSenakPropertyName (){ return "senak"; }
	public void setSenak (String value){ this.senak = value;   }   
	public String getSenak (){ return this.senak;   }  
	
	private String seadk1 = "";                                
	public String getSeadk1PropertyName (){ return "seadk1"; }
	public void setSeadk1 (String value){ this.seadk1 = value;   }   
	public String getSeadk1 (){ return this.seadk1;   }  
	
	private String seadk2 = "";                                
	public String getSeadk2PropertyName (){ return "seadk2"; }
	public void setSeadk2 (String value){ this.seadk2 = value;   }   
	public String getSeadk2 (){ return this.seadk2;   }  
	
	private String seadk3 = "";                                
	public String getSeadk3PropertyName (){ return "seadk3"; }
	public void setSeadk3 (String value){ this.seadk3 = value;   }   
	public String getSeadk3 (){ return this.seadk3;   }  
		
	private String selka = "";                                
	public String getSelkaPropertyName (){ return "selka"; }
	public void setSelka (String value){ this.selka = value;   }   
	public String getSelka (){ return this.selka;   }  
	
	private String setlf = "";                                
	public String getSetlfPropertyName (){ return "setlf"; }
	public void setSetlf (String value){ this.setlf = value;   }   
	public String getSetlf (){ return this.setlf;   }  
	
	private String senad = "";                                
	public String getSenadPropertyName (){ return "senad"; }
	public void setSenad (String value){ this.senad = value;   }   
	public String getSenad (){ return this.senad;   }  
	
	private String selv = "";                                
	public String getSelvPropertyName (){ return "selv"; }
	public void setSelv (String value){ this.selv = value;   }   
	public String getSelv (){ return this.selv;   }  
	
	private String selvt = "";                                
	public String getSelvtPropertyName (){ return "selvt"; }
	public void setSelvt (String value){ this.selvt = value;   }   
	public String getSelvt (){ return this.selvt;   }  
	
	private String setrid = "";                                
	public String getSetridPropertyName (){ return "setrid"; }
	public void setSetrid (String value){ this.setrid = value;   }   
	public String getSetrid (){ return this.setrid;   }  
	
	private String selkt = "";                                
	public String getSelktPropertyName (){ return "selkt"; }
	public void setSelkt (String value){ this.selkt = value;   }   
	public String getSelkt (){ return this.selkt;   }  
	
	private String seval1 = "";                                
	public String getSeval1PropertyName (){ return "seval1"; }
	public void setSeval1 (String value){ this.seval1 = value;   }   
	public String getSeval1 (){ return this.seval1;   }  
	
	private String sebel1 = "";                                
	public String getSebel1PropertyName (){ return "sebel1"; }
	public void setSebel1 (String value){ this.sebel1 = value;   }   
	public String getSebel1 (){ return this.sebel1;   }  
	
	private String sebel2 = "";                                
	public String getSebel2PropertyName (){ return "sebel2"; }
	public void setSebel2 (String value){ this.sebel2 = value;   }   
	public String getSebel2 (){ return this.sebel2;   }  
	
	private String sevku = "";                                
	public String getSevkuPropertyName (){ return "sevku"; }
	public void setSevku (String value){ this.sevku = value;   }   
	public String getSevku (){ return this.sevku;   }  
	
	private String sekdh = "";                                
	public String getSekdhPropertyName (){ return "sekdh"; }
	public void setSekdh (String value){ this.sekdh = value;   }   
	public String getSekdh (){ return this.sekdh;   }  
	
	private String setst = "";                                
	public String getSetstPropertyName (){ return "setst"; }
	public void setSetst (String value){ this.setst = value;   }   
	public String getSetst (){ return this.setst;   }  
	
	private String setrt = "";                                
	public String getSetrtPropertyName (){ return "setrt"; }
	public void setSetrt (String value){ this.setrt = value;   }   
	public String getSetrt (){ return this.setrt;   }  
	
	private String setrm = "";                                
	public String getSetrmPropertyName (){ return "setrm"; }
	public void setSetrm (String value){ this.setrm = value;   }   
	public String getSetrm (){ return this.setrm;   }  
	
	private String sefif = "";                                
	public String getSefifPropertyName (){ return "sefif"; }
	public void setSefif (String value){ this.sefif = value;   }   
	public String getSefif (){ return this.sefif;   }  
	
	private String sefid = "";                                
	public String getSefidPropertyName (){ return "sefid"; }
	public void setSefid (String value){ this.sefid = value;   }   
	public String getSefid (){ return this.sefid;   }  
	
	private String sebelt = "";                                
	public String getSebeltPropertyName (){ return "sebelt"; }
	public void setSebelt (String value){ this.sebelt = value;   }   
	public String getSebelt (){ return this.sebelt;   }  
	
	private String sekta = "";                                
	public String getSektaPropertyName (){ return "sekta"; }
	public void setSekta (String value){ this.sekta = value;   }   
	public String getSekta (){ return this.sekta;   }  
	
	private String sektb = "";                                
	public String getSektbPropertyName (){ return "sektb"; }
	public void setSektb (String value){ this.sektb = value;   }   
	public String getSektb (){ return this.sektb;   }  
	
	private String segn = "";                                
	public String getSegnPropertyName (){ return "segn"; }
	public void setSegn (String value){ this.segn = value;   }   
	public String getSegn (){ return this.segn;   }  
	
	private String seft1 = "";                                
	public String getSeft1PropertyName (){ return "seft1"; }
	public void setSeft1 (String value){ this.seft1 = value;   }   
	public String getSeft1 (){ return this.seft1;   }  
	
	private String seft2 = "";                                
	public String getSeft2PropertyName (){ return "seft2"; }
	public void setSeft2 (String value){ this.seft2 = value;   }   
	public String getSeft2 (){ return this.seft2;   }  
	
	private String seft3 = "";                                
	public String getSeft3PropertyName (){ return "seft3"; }
	public void setSeft3 (String value){ this.seft3 = value;   }   
	public String getSeft3 (){ return this.seft3;   }  
	
	private String sedst = "";                                
	public String getSedstPropertyName (){ return "sedst"; }
	public void setSedst (String value){ this.sedst = value;   }   
	public String getSedst (){ return this.sedst;   }  
	
	private String sedtg = "";                                
	public String getSedtgPropertyName (){ return "sedtg"; }
	public void setSedtg (String value){ this.sedtg = value;   }   
	public String getSedtg (){ return this.sedtg;   }  
	
	private String setll = "";                                
	public String getSetllPropertyName (){ return "setll"; }
	public void setSetll (String value){ this.setll = value;   }   
	public String getSetll (){ return this.setll;   }  
	
	private String setle = "";                                
	public String getSetlePropertyName (){ return "setle"; }
	public void setSetle (String value){ this.setle = value;   }   
	public String getSetle (){ return this.setle;   }  
	
	private String seski = "";                                
	public String getSeskiPropertyName (){ return "seski"; }
	public void setSeski (String value){ this.seski = value;   }   
	public String getSeski (){ return this.seski;   }  
	
	private String sels = "";                                
	public String getselsPropertyName (){ return "sels"; }
	public void setSels (String value){ this.sels = value;   }   
	public String getSels (){ return this.sels;   }  
	
	
	private String sekdls = "";                                
	public String getSekdlsPropertyName (){ return "sekdls"; }
	public void setSekdls (String value){ this.sekdls = value;   }   
	public String getSekdls (){ return this.sekdls;   }  
	
	private String sedt = "";                                
	public String getSedtPropertyName (){ return "sedt"; }
	public void setSedt (String value){ this.sedt = value;   }   
	public String getSedt (){ return this.sedt;   }  
	
	private String selv2 = "";                                
	public String getSelv2PropertyName (){ return "selv2"; }
	public void setSelv2 (String value){ this.selv2 = value;   }   
	public String getSelv2 (){ return this.selv2;   }  
	
	private String sekddk = "";                                
	public String getSekddkPropertyName (){ return "sekddk"; }
	public void setSekddk (String value){ this.sekddk = value;   }   
	public String getSekddk (){ return this.sekddk;   }  
	
	private String segkd = "";                                
	public String getSegkdPropertyName (){ return "segkd"; }
	public void setSegkd (String value){ this.segkd = value;   }   
	public String getSegkd (){ return this.segkd;   }  
	
	
	private String segft1 = "";                                
	public String getSegft1PropertyName (){ return "segft1"; }
	public void setSegft1 (String value){ this.segft1 = value;   }   
	public String getSegft1 (){ return this.segft1;   }  
	
	private String segft2 = "";                                
	public String getSegft2PropertyName (){ return "segft2"; }
	public void setSegft2 (String value){ this.segft2 = value;   }   
	public String getSegft2 (){ return this.segft2;   }  
	
	private String sepos = "";                                
	public String getSeposPropertyName (){ return "sepos"; }
	public void setSepos (String value){ this.sepos = value;   }   
	public String getSepos (){ return this.sepos;   }  
	
	private String seftb = "";                                
	public String getSeftbPropertyName (){ return "seftb"; }
	public void setSeftb (String value){ this.seftb = value;   }   
	public String getSeftb (){ return this.seftb;   }  
	
	private String selkb = "";                                
	public String getSelkbPropertyName (){ return "selkb"; }
	public void setSelkb (String value){ this.selkb = value;   }   
	public String getSelkb (){ return this.selkb;   }  
	
	private String seftm = "";                                
	public String getSeftmPropertyName (){ return "seftm"; }
	public void setSeftm (String value){ this.seftm = value;   }   
	public String getSeftm (){ return this.seftm;   }  
	
	private String selkm = "";                                
	public String getSelkmPropertyName (){ return "selkm"; }
	public void setSelkm (String value){ this.selkm = value;   }   
	public String getSelkm (){ return this.selkm;   }  
	
	private String sekdft = "";                                
	public String getSekdftPropertyName (){ return "sekdft"; }
	public void setSekdft (String value){ this.sekdft = value;   }   
	public String getSekdft (){ return this.sekdft;   }  
	
	private String setarf = "";                                
	public String getSetarfPropertyName (){ return "setarf"; }
	public void setSetarf (String value){ this.setarf = value;   }   
	public String getSetarf (){ return this.setarf;   }  
	
	
	private String selkat = "";                                
	public String getSelkatPropertyName (){ return "selkat"; }
	public void setSelkat (String value){ this.selkat = value;   }   
	public String getSelkat (){ return this.selkat;   }  
	
	private String seval2 = "";                                
	public String getSeval2PropertyName (){ return "seval2"; }
	public void setSeval2 (String value){ this.seval2 = value;   }   
	public String getSeval2 (){ return this.seval2;   }  
	
	private String sebel3 = "";                                
	public String getSebel3PropertyName (){ return "sebel3"; }
	public void setSebel3 (String value){ this.sebel3 = value;   }   
	public String getSebel3 (){ return this.sebel3;   }  
	
	private String sews = "";                                
	public String getSewsPropertyName (){ return "sews"; }
	public void setSews (String value){ this.sews = value;   }   
	public String getSews (){ return this.sews;   }  
	
	private String sedl = "";                                
	public String getSedlPropertyName (){ return "sedl"; }
	public void setSedl (String value){ this.sedl = value;   }   
	public String getSedl (){ return this.sedl;   }  
	
	private String setolk = "";                                
	public String getSetolkPropertyName (){ return "setolk"; }
	public void setSetolk (String value){ this.setolk = value;   }   
	public String getSetolk (){ return this.setolk;   }  
	
	private String sea4 = "";                                
	public String getSea4PropertyName (){ return "sea4"; }
	public void setSea4 (String value){ this.sea4 = value;   }   
	public String getSea4 (){ return this.sea4;   }  
	
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
	
	private String s3039d = "";                                
	public String getS3039dPropertyName (){ return "s3039d"; }
	public void setS3039d (String value){ this.s3039d = value;   }   
	public String getS3039d (){ return this.s3039d;   }  
	
	private String s3039e = "";                                
	public String getS3039ePropertyName (){ return "s3039e"; }
	public void setS3039e (String value){ this.s3039e = value;   }   
	public String getS3039e (){ return this.s3039e;   }  
	
	private String s3039eo1 = "";                                
	public String getS3039eo1PropertyName (){ return "s3039eo1"; }
	public void setS3039eo1 (String value){ this.s3039eo1 = value;   }   
	public String getS3039eo1 (){ return this.s3039eo1;   }  
	
	private String s3039eo2 = "";                                
	public String getS3039eo2PropertyName (){ return "s3039eo2"; }
	public void setS3039eo2 (String value){ this.s3039eo2 = value;   }   
	public String getS3039eo2 (){ return this.s3039eo2;   }  
	
	private String s0026 = "";                                
	public String getS0026PropertyName (){ return "s0026"; }
	public void setS0026 (String value){ this.s0026 = value;   }   
	public String getS0026 (){ return this.s0026;   }  
	
	private String sektc = "";                                
	public String getSektcPropertyName (){ return "sektc"; }
	public void setSektc (String value){ this.sektc = value;   }   
	public String getSektc (){ return this.sektc;   }  
	
	private String seekst = "";                                
	public String getSeekstPropertyName (){ return "seekst"; }
	public void setSeekst (String value){ this.seekst = value;   }   
	public String getSeekst (){ return this.seekst;   }  
	
	
	
}
