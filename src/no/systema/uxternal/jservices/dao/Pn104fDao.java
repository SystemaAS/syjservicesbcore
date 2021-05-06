package no.systema.uxternal.jservices.dao;

import java.io.Serializable;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class Pn104fDao implements Serializable, IDao {
									//Type       Lengde  Lengde  Posisjon                     
	
	private String pnst = "";   //    tegn            1       1         1        begge    status           
	  private String pnkne = "";   //      tegn           17      17         2        begge    eksterin kundenr 
	  private String pnkn = "";   //       sonet        8  0       8        19        begge    kundenr          
	  private String pnifn = "";   //      tegn           17      17        27        begge    fakturanr import 
	  private String pnefn = "";   //      tegn           17      17        44        begge    fakturanr eksport
	  private String pnon = "";   //      tegn           15      15        61        begge    ordrenr          
	  private String pnan = "";   //       tegn           20      20        76        begge    artikkelnr        
	  private String pnevn = "";   //      sonet       11  3      11        96        begge    nettovekt eksport 
	  private String pnent = "";   //      sonet        7  0       7       107        begge    stykk eksport     
	  private String pnepr = "";   //      sonet       11  2      11       114        begge    pris eksport      
	  private String pnetn = "";   //      sonet        8  0       8       125        begge    tariffnr eksport  
	  private String pnedt = "";   //      sonet        8  0       8       133        begge    dato eksport      
	  private String pnetd = "";   //      sonet        6  0       6       141        begge    tid eksport       
	  private String pneav = "";   //      sonet        4  0       4       147        begge    avdeling eksport  
	  private String pneop = "";   //      sonet        7  0       7       151        begge    oppdragsnr eksport
	  private String pneli = "";   //      sonet        5  0       5       158        begge    linjenr eksport  
	  private String pniav = "";   //      sonet        4  0       4       163        begge    avdeling import  
	  private String pniop = "";   //      sonet        7  0       7       167        begge    oppdragsnr import
	  private String pnili = "";   //      sonet        5  0       5       174        begge    linjenr import                        
}
