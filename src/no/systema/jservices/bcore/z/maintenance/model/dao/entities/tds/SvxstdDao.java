/**
 * 
 */
package no.systema.jservices.bcore.z.maintenance.model.dao.entities.tds;

import no.systema.jservices.model.dao.entities.IDao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

import org.slf4j.*;

/**
 * @author oscardelatorre
 * @date Jun 15, 2017
 *
 */
public class SvxstdDao implements Serializable, IDao {
	private static final Logger logger = LoggerFactory.getLogger(SvxstdDao.class.getName());
	
	//This record is used in special occasions when the session object is impossible to use
	//Typically in a Validator when we want to check further into RPG and we need the user name in a parameter (user=Oscar)
	private String applicationUser = "";
	public void setApplicationUser(String value) {  this.applicationUser = value; }
	public String getApplicationUser() {return this.applicationUser;}
	
	//thavd Avdeling 
	private String thavd = "";
	public String getThavdPropertyName (){ return "thavd"; }
	public void setThavd(String value) {  this.thavd = value; }
	public String getThavd() {return this.thavd;}
	
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
	
	//thtdn Oppdragsnr
	private String thtdn = "";
	public String getThtdnPropertyName (){ return "thtdn"; }
	public void setThtdn(String value) {  this.thtdn = value; }
	public String getThtdn() {return this.thtdn;}
	//status
	private String thst = "";
	public String getThstPropertyName (){ return "thst"; }
	public void setThst(String value) {  this.thst = value; }
	public String getThst() {return this.thst;}
	//signatur
	private String thsg = "";
	public String getThsgPropertyName (){ return "thsg"; }
	public void setThsg(String value) {  this.thsg = value; }
	public String getThsg() {return this.thsg;}
	//Date YMD
	private String thdt = "";
	public String getThdtPropertyName (){ return "thdt"; }
	public void setThdt(String value) {  this.thdt = value; }
	public String getThdt() {return this.thdt;}
	
	//lrnNr
	private String lrnNr = "";
	public String getLrnNrPropertyName (){ return "lrnNr"; }
	public void setLrnNr(String value) {  this.lrnNr = value; }
	public String getLrnNr() {return this.lrnNr;}
	
	//MRN-nr.
	private String thtrnr = "";
	public String getThtrnrPropertyName (){ return "thtrnr"; }
	public void setThtrnr(String value) {  this.thtrnr = value; }
	public String getThtrnr() { return this.thtrnr;}
		
	
	//projektnr
	private String thpro = "";
	public String getThproPropertyName (){ return "thpro"; }
	public void setThpro(String value) {  this.thpro = value; }
	public String getThpro() {return this.thpro;}
	
	//Förenklad procedur
	private String thenkl = "";
	public String getThenklPropertyName (){ return "thenkl"; }
	public void setThenkl(String value) {  this.thenkl = value; }
	public String getThenkl() {return this.thenkl;}
	
	private String thdk = "";
	public String getThdkPropertyName (){ return "thdk"; }
	public void setThdk(String value) {  this.thdk = value; }
	public String getThdk() {return this.thdk;}
	
	private String thkna = "";
	public String getThknaPropertyName (){ return "thkna"; } 
	public void setThkna(String value) {  this.thkna = value; }
	public String getThkna() {return this.thkna;}
	
	private String thnaa = "";
	public String getThnaaPropertyName (){ return "thnaa"; }
	public void setThnaa(String value) {  this.thnaa = value; }
	public String getThnaa() {return this.thnaa;}
	
	private String thada1 = "";
	public String getThada1PropertyName (){ return "thada1"; }
	public void setThada1(String value) {  this.thada1 = value; }
	public String getThada1() {return this.thada1;}
	
	private String thpna = "";
	public String getThpnaPropertyName (){ return "thpna"; }
	public void setThpna(String value) {  this.thpna = value; }
	public String getThpna() {return this.thpna;}
	
	
	
	private String thpsa = "";
	public String getThpsaPropertyName (){ return "thpsa"; }
	public void setThpsa(String value) {  this.thpsa = value; }
	public String getThpsa() {return this.thpsa;}
	
	private String thlka = "";
	public String getThlkaPropertyName (){ return "thlka"; }
	public void setThlka(String value) {  this.thlka = value; }
	public String getThlka() {return this.thlka;}
	
	private String thska = "";
	public String getThskaPropertyName (){ return "thska"; }
	public void setThska(String value) {  this.thska = value; }
	public String getThska() {return this.thska;}
	
	
	private String thtina = "";
	public String getThtinaPropertyName (){ return "thtina"; }
	public void setThtina(String value) {  this.thtina = value; }
	public String getThtina() {return this.thtina;}
	
	private String thkns = "";
	public String getThknsPropertyName (){ return "thkns"; }
	public void setThkns(String value) {  this.thkns = value; }
	public String getThkns() {return this.thkns;}
	
	private String thnas = "";
	public String getThnasPropertyName (){ return "thnas"; }
	public void setThnas(String value) {  this.thnas = value; }
	public String getThnas() {return this.thnas;}
	
	private String thads1 = "";
	public String getThads1PropertyName (){ return "thads1"; }
	public void setThads1(String value) {  this.thads1 = value; }
	public String getThads1() {return this.thads1;}
	
	private String thpns = "";
	public String getThpnsPropertyName (){ return "thpns"; }
	public void setThpns(String value) {  this.thpns = value; }
	public String getThpns() {return this.thpns;}
	
	private String thpss = "";
	public String getThpssPropertyName (){ return "thpss"; }
	public void setThpss(String value) {  this.thpss = value; }
	public String getThpss() {return this.thpss;}
	
	private String thlks = "";
	public String getThlksPropertyName (){ return "thlks"; }
	public void setThlks(String value) {  this.thlks = value; }
	public String getThlks() {return this.thlks;}
	
	private String thsks = "";
	public String getThsksPropertyName (){ return "thsks"; }
	public void setThsks(String value) {  this.thsks = value; }
	public String getThsks() {return this.thsks;}
	
	private String thtins = "";
	public String getThtinsPropertyName (){ return "thtins"; }
	public void setThtins(String value) {  this.thtins = value; }
	public String getThtins() {return this.thtins;}
	
	private String thknk = "";
	public String getThknkPropertyName (){ return "thknk"; }
	public void setThknk(String value) {  this.thknk = value; }
	public String getThknk() {return this.thknk;}
	
	
	private String thnak = "";
	public String getThnakPropertyName (){ return "thnak"; }
	public void setThnak(String value) {  this.thnak = value; }
	public String getThnak() { return this.thnak;}
	
	private String thadk1 = "";
	public String getThadk1PropertyName (){ return "thadk1"; }
	public void setThadk1(String value) {  this.thadk1 = value; }
	public String getThadk1() { return this.thadk1;}
	
	private String thpnk = "";
	public String getThpnkPropertyName (){ return "thpnk"; }
	public void setThpnk(String value) {  this.thpnk = value; }
	public String getThpnk() { return this.thpnk;}
	
	private String thpsk = "";
	public String getThpskPropertyName (){ return "thpsk"; }
	public void setThpsk(String value) {  this.thpsk = value; }
	public String getThpsk() { return this.thpsk;}
	
	private String thlkk = "";
	public String getThlkkPropertyName (){ return "thlkk"; }
	public void setThlkk(String value) {  this.thlkk = value; }
	public String getThlkk() { return this.thlkk;}
	
	private String thskk = "";
	public String getThskkPropertyName (){ return "thskk"; }
	public void setThskk(String value) {  this.thskk = value; }
	public String getThskk() { return this.thskk;}
	
	private String thtink = "";
	public String getThtinkPropertyName (){ return "thtink"; }
	public void setThtink(String value) {  this.thtink = value; }
	public String getThtink() { return this.thtink;}
	
	private String thblk = "";
	public String getThblkPropertyName (){ return "thblk"; }
	public void setThblk(String value) {  this.thblk = value; }
	public String getThblk() { return this.thblk;}
	
	private String thlsd = "";
	public void setThlsd(String value) {  this.thlsd = value; }
	public String getThlsd() { return this.thlsd;}
	
	private String thlasd = "";
	public String getThlasdPropertyName (){ return "thlasd"; }
	public void setThlasd(String value) {  this.thlasd = value; }
	public String getThlasd() { return this.thlasd;}
	
	private String thlas2 = "";
	public String getThlas2PropertyName (){ return "thlas2"; }
	public void setThlas2(String value) {  this.thlas2 = value; }
	public String getThlas2() { return this.thlas2;}
	
	private String thalk = "";
	public String getThalkPropertyName (){ return "thalk"; }
	public void setThalk(String value) {  this.thalk = value; }
	public String getThalk() { return this.thalk;}
	
	private String thtrm = "";
	public String getThtrmPropertyName (){ return "thtrm"; }
	public void setThtrm(String value) {  this.thtrm = value; }
	public String getThtrm() { return this.thtrm;}
	
	private String thtrmi = "";
	public String getThtrmiPropertyName (){ return "thtrmi"; }
	public void setThtrmi(String value) {  this.thtrmi = value; }
	public String getThtrmi() { return this.thtrmi;}
	
	private String thtaid = "";
	public String getThtaidPropertyName (){ return "thtaid"; }
	public void setThtaid(String value) {  this.thtaid = value; }
	public String getThtaid() { return this.thtaid;}
	
	private String thtask = "";
	public String getThtaskPropertyName (){ return "thtask"; }
	public void setThtask(String value) {  this.thtask = value; }
	public String getThtask() { return this.thtask;}
	
	private String thtalk = "";
	public String getThtalkPropertyName (){ return "thtalk"; }
	public void setThtalk(String value) {  this.thtalk = value; }
	public String getThtalk() { return this.thtalk;}
	
	private String thtgid = "";
	public String getThtgidPropertyName (){ return "thtgid"; }
	public void setThtgid(String value) {  this.thtgid = value; }
	public String getThtgid() { return this.thtgid;}
	
	private String thtgsk = "";
	public String getThtgskPropertyName (){ return "thtgsk"; }
	public void setThtgsk(String value) {  this.thtgsk = value; }
	public String getThtgsk() { return this.thtgsk;}
	
	private String thtglk = "";
	public String getThtglkPropertyName (){ return "thtglk"; }
	public void setThtglk(String value) {  this.thtglk = value; }
	public String getThtglk() { return this.thtglk;}
	
	private String thskfd = "";
	public String getThskfdPropertyName (){ return "thskfd"; }
	public void setThskfd(String value) {  this.thskfd = value; }
	public String getThskfd() { return this.thskfd;}
	
	private String thcats = "";
	public String getThcatsPropertyName (){ return "thcats"; }
	public void setThcats(String value) {  this.thcats = value; }
	public String getThcats() { return this.thcats;}
	
	private String thtsd1 = "";
	public String getThtsd1PropertyName (){ return "thtsd1"; }
	public void setThtsd1(String value) {  this.thtsd1 = value; }
	public String getThtsd1() { return this.thtsd1;}
	
	private String thtsd2 = "";
	public String getThtsd2PropertyName (){ return "thtsd2"; }
	public void setThtsd2(String value) {  this.thtsd2 = value; }
	public String getThtsd2() { return this.thtsd2;}
	
	private String thtsd3 = "";
	public String getThtsd3PropertyName (){ return "thtsd3"; }
	public void setThtsd3(String value) {  this.thtsd3 = value; }
	public String getThtsd3() { return this.thtsd3;}
	
	private String thtsd4 = "";
	public String getThtsd4PropertyName (){ return "thtsd4"; }
	public void setThtsd4(String value) {  this.thtsd4 = value; }
	public String getThtsd4() { return this.thtsd4;}
	
	private String thtsd5 = "";
	public String getThtsd5PropertyName (){ return "thtsd5"; }
	public void setThtsd5(String value) {  this.thtsd5 = value; }
	public String getThtsd5() { return this.thtsd5;}
	
	private String thtsd6 = "";
	public String getThtsd6PropertyName (){ return "thtsd6"; }
	public void setThtsd6(String value) {  this.thtsd6 = value; }
	public String getThtsd6() { return this.thtsd6;}
	
	private String thtsd7 = "";
	public String getThtsd7PropertyName (){ return "thtsd7"; }
	public void setThtsd7(String value) {  this.thtsd7 = value; }
	public String getThtsd7() { return this.thtsd7;}
	
	private String thtsd8 = "";
	public String getThtsd8PropertyName (){ return "thtsd8"; }
	public void setThtsd8(String value) {  this.thtsd8 = value; }
	public String getThtsd8() { return this.thtsd8;}
	
	private String thtsb = "";
	public String getThtsbPropertyName (){ return "thtsb"; }
	public void setThtsb(String value) {  this.thtsb = value; }
	public String getThtsb() { return this.thtsb;}
	
	private String thddt = "";
	public String getThddtPropertyName (){ return "thddt"; }
	public void setThddt(String value) {  this.thddt = value; }
	public String getThddt() { return this.thddt;}
	
	private String thdfkd = "";
	public String getThdfkdPropertyName (){ return "thdfkd"; }
	public void setThdfkd(String value) {  this.thdfkd = value; }
	public String getThdfkd() { return this.thdfkd;}
	
	private String thdfsk = "";
	public String getThdfskPropertyName (){ return "thdfsk"; }
	public void setThdfsk(String value) {  this.thdfsk = value; }
	public String getThdfsk() { return this.thdfsk;}
	
	private String thdkr = "";
	public String getThdkrPropertyName (){ return "thdkr"; }
	public void setThdkr(String value) {  this.thdkr = value; }
	public String getThdkr() { return this.thdkr;}
	
	private String thdant = "";
	public String getThdantPropertyName (){ return "thdant"; }
	public void setThdant(String value) {  this.thdant = value; }
	public String getThdant() { return this.thdant;}
	
	private String thddtk = "";
	public String getThddtkPropertyName (){ return "thddtk"; }
	public void setThddtk(String value) {  this.thddtk = value; }
	public String getThddtk() { return this.thddtk;}
	
	private String thdats = "";
	public String getThdatsPropertyName (){ return "thdats"; }
	public void setThdats(String value) {  this.thdats = value; }
	public String getThdats() { return this.thdats;}
	
	private String thdkav = "";
	public String getThdkavPropertyName (){ return "thdkav"; }
	public void setThdkav(String value) {  this.thdkav = value; }
	public String getThdkav() { return this.thdkav;}
	
	private String thdksk = "";
	public String getThdkskPropertyName (){ return "thdksk"; }
	public void setThdksk(String value) {  this.thdksk = value; }
	public String getThdksk() { return this.thdksk;}
	
	private String thgkd = "";
	public String getThgkdPropertyName (){ return "thgkd"; }
	public void setThgkd(String value) {  this.thgkd = value; }
	public String getThgkd() { return this.thgkd;}
	
	private String thgft1 = "";
	public String getThgft1PropertyName (){ return "thgft1"; }
	public void setThgft1(String value) {  this.thgft1 = value; }
	public String getThgft1() { return this.thgft1;}
	
	private String thgft2 = "";
	public String getThgft2PropertyName (){ return "thgft2"; }
	public void setThgft2(String value) {  this.thgft2 = value; }
	public String getThgft2() { return this.thgft2;}
	
	private String thgadk = "";
	public String getThgadkPropertyName (){ return "thgadk"; }
	public void setThgadk(String value) {  this.thgadk = value; }
	public String getThgadk() { return this.thgadk;}
	
	
	private String thgpr = "";                                
	public String getThgprPropertyName (){ return "thgpr"; }
	public void setThgpr (String value){ this.thgpr = value;   }   
	public String getThgpr (){ return this.thgpr;   }  
	
	private String thgbl = "";
	public String getThgblPropertyName (){ return "thgbl"; }
	public void setThgbl(String value) {  this.thgbl = value; }
	public String getThgbl() { 
		String retval = this.thgbl;
		//insert a decimal comma since the field is wrongly formatted from RPG
		/*String value = new String(this.thgbl);
		if(value!="" && value.length()>3){
			if(!value.contains(",")){
				int x = value.length();
				String delInt = value.substring(0,x-2);
				String delDec = value.substring(x-2);
				
				String formattedValue = delInt + "," + delDec;
				retval = formattedValue;
			}
		}*/
		return retval;
	}
	
	private String thgvk = "";
	public String getThgvkPropertyName (){ return "thgvk"; }
	public void setThgvk(String value) {  this.thgvk = value; }
	public String getThgvk() { return this.thgvk;}
	
	private String thgbgi = "";
	public String getThgbiPropertyName (){ return "thgbi"; }
	public void setThgbgi(String value) {  this.thgbgi = value; }
	public String getThgbgi() { return this.thgbgi;}
	
	private String thgbgu = "";
	public String getThgbguPropertyName (){ return "thgbgu"; }
	public void setThgbgu(String value) {  this.thgbgu = value; }
	public String getThgbgu() { return this.thgbgu;}
	
	private String thkdc = "";
	public String getThkdcPropertyName (){ return "thkdc"; }
	public void setThkdc(String value) {  this.thkdc = value; }
	public String getThkdc() { return this.thkdc;}
	
	private String thtssd = "";
	public String getThtssdPropertyName (){ return "thtssd"; }
	public void setThtssd(String value) {  this.thtssd = value; }
	public String getThtssd() { return this.thtssd;}
	
	private String thtsn1 = "";
	public String getThtsn1PropertyName (){ return "thtsn1"; }
	public void setThtsn1(String value) {  this.thtsn1 = value; }
	public String getThtsn1() { return this.thtsn1;}
	
	private String thtsn2 = "";
	public String getThtsn2PropertyName (){ return "thtsn2"; }
	public void setThtsn2(String value) {  this.thtsn2 = value; }
	public String getThtsn2() { return this.thtsn2;}
	
	private String thtspn = "";
	public String getThtspnPropertyName (){ return "thtspn"; }
	public void setThtspn(String value) {  this.thtspn = value; }
	public String getThtspn() { return this.thtspn;}
	
	private String thtsps = "";
	public String getThtspsPropertyName (){ return "thtsps"; }
	public void setThtsps(String value) {  this.thtsps = value; }
	public String getThtsps() { return this.thtsps;}
	
	private String thtslk = "";
	public String getThtslkPropertyName (){ return "thtslk"; }
	public void setThtslk(String value) {  this.thtslk = value; }
	public String getThtslk() { return this.thtslk;}
	
	private String thtssk = "";
	public String getThtsskPropertyName (){ return "thtssk"; }
	public void setThtssk(String value) {  this.thtssk = value; }
	public String getThtssk() { return this.thtssk;}
	
	private String thlstl = "";
	public String getThlstlPropertyName (){ return "thlstl"; }
	public void setThlstl(String value) {  this.thlstl = value; }
	public String getThlstl() { return this.thlstl;}
	
	private String thvpos = "";
	public String getThvposPropertyName (){ return "thvpos"; }
	public void setThvpos(String value) {  this.thvpos = value; }
	public String getThvpos() { return this.thvpos;}
	
	private String thntk = "";
	public String getThntkPropertyName (){ return "thntk"; }
	public void setThntk(String value) {  this.thntk = value; }
	public String getThntk() { return this.thntk;}
	
	private String thvkb = "";
	public String getThvkbPropertyName (){ return "thvkb"; }
	public void setThvkb(String value) {  this.thvkb = value; }
	public String getThvkb() { return this.thvkb;}
	
	private String thdst = "";
	public String getThdstPropertyName (){ return "thdst"; }
	public void setThdst(String value) {  this.thdst = value; }
	public String getThdst() { return this.thdst;}
	
	private String thdsk = "";
	public String getThdskPropertyName (){ return "thdsk"; }
	public void setThdsk(String value) {  this.thdsk = value; }
	public String getThdsk() { return this.thdsk;}
	
	private String thtarf = "";
	public String getThtarfPropertyName (){ return "thtarf"; }
	public void setThtarf(String value) {  this.thtarf = value; }
	public String getThtarf() { return this.thtarf;}
	
	private String thws = "";
	public String getThwsPropertyName (){ return "thws"; }
	public void setThws(String value) {  this.thws = value; }
	public String getThws() { return this.thws;}
	
	
	private String thtrdt = "";
	public String getThtrdtPropertyName (){ return "thtrdt"; }
	public void setThtrdt(String value) {  this.thtrdt = value; }
	public String getThtrdt() { return this.thtrdt;}
	
	private String thomd = "";
	public String getThomdPropertyName (){ return "thomd"; }
	public void setThomd(String value) {  this.thomd = value; }
	public String getThomd() { return this.thomd;}
	
	private String thbind = "";
	public String getThbindPropertyName (){ return "thbind"; }
	public void setThbind(String value) {  this.thbind = value; }
	public String getThbind() { return this.thbind;}
	
	private String thtet = "";
	public String getThtetPropertyName (){ return "thtet"; }
	public void setThtet(String value) {  this.thtet = value; }
	public String getThtet() { return this.thtet;}
	
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
	
	//Std value for NCTS export. Needed when Signering (send to) is used
	//This value is valid ONLY for NCTS export
	private String thmf = "015";
	public String getThmfPropertyName (){ return "thmf"; }
	public void setThmf(String value) {  this.thmf = value; }
	public String getThmf() { return this.thmf;}
	
	private String thmp = "";
	public String getThmpPropertyName (){ return "thmp"; }
	public void setThmp(String value) {  this.thmp = value; }
	public String getThmp() { return this.thmp;}
	
	private String thpkl = "";
	public String getThpklPropertyName (){ return "thpkl"; }
	public void setThpkl(String value) {  this.thpkl = value; }
	public String getThpkl() { return this.thpkl;}
	
	private String thkdv = "";
	public String getThkdvPropertyName (){ return "thkdv"; }
	public void setThkdv(String value) {  this.thkdv = value; }
	public String getThkdv() { return this.thkdv;}
	
	private String th0035 = "";
	public String getTh0035PropertyName (){ return "th0035"; }
	public void setTh0035(String value) {  this.th0035 = value; }
	public String getTh0035() { return this.th0035;}
	
	//LRN-nr
	String thtuid = "";
	public String getThtuidPropertyName (){ return "thtuid"; }
	public void setThtuid(String value) {  this.thtuid = value; }
	public String getThtuid() { return this.thtuid;}
	
	private String thsgdk = "";
	public String getThsgdkPropertyName (){ return "thsgdk"; }
	public void setThsgdk(String value) {  this.thsgdk = value; }
	public String getThsgdk() { return this.thsgdk;}
	
	private String thsgme = "";
	public String getThsgmePropertyName (){ return "thsgme"; }
	public void setThsgme(String value) {  this.thsgme = value; }
	public String getThsgme() { return this.thsgme;}
	
	private String thsgut = "";
	public String getThsgutPropertyName (){ return "thsgut"; }
	public void setThsgut(String value) {  this.thsgut = value; }
	public String getThsgut() { return this.thsgut;}
	
	private String thsgid = "";
	public String getThsgidPropertyName (){ return "thsgid"; }
	public void setThsgid(String value) {  this.thsgid = value; }
	public String getThsgid() { return this.thsgid;}
	
	private String thsgdt = "";
	public String getThsgdtPropertyName (){ return "thsgdt"; }
	public void setThsgdt(String value) {  this.thsgdt = value; }
	public String getThsgdt() { return this.thsgdt;}
	
	private String thbyte = "";
	public String getThbytePropertyName (){ return "thbyte"; }
	public void setThbyte(String value) {  this.thbyte = value; }
	public String getThbyte() { return this.thbyte;}
	
	private String tggbl = "";
	public String getTggblPropertyName (){ return "tggbl"; }
	public void setTggbl(String value) {  this.tggbl = value; }
	public String getTggbl() { return this.tggbl;}
	
	private String tggblb = "";
	public String getTggblbPropertyName (){ return "tggblb"; }
	public void setTggblb(String value) {  this.tggblb = value; }
	public String getTggblb() { return this.tggblb;}
	
	private String tgprm = "";
	public String getTgprmPropertyName (){ return "tgprm"; }
	public void setTgprm(String value) {  this.tgprm = value; }
	public String getTgprm() { return this.tgprm;}
	
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
	
	
	
	
	
	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
