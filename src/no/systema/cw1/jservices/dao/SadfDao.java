package no.systema.cw1.jservices.dao;

import java.io.Serializable;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadfDao implements Serializable, IDao {
					//Felt       Type       Lengde  Lengde  Posisjon        Bruk     Overskrift             
	private String sfavd = ""; //sonet        4  0       4         1        begge    avdeling               
	private String sfopdn = ""; //     sonet        7  0       7         5        begge    oppdragsnr.            
	private String sftxt = ""; //      tegn           17      17        12        begge    txt av finans. oppl.   
	private String sfdt = ""; //       sonet        8  0       8        29        begge    dato av rubr.28        
	private String sfbl28 = ""; //     sonet       12  2      12        37        begge    beløp for rubr.28      
	private String sfvk28 = ""; //     tegn            3       3        49        begge    valutakode rubr.28     
	private String sfkr28 = ""; //     sonet        7  3       7        52        begge    kurs på rubr.28        
	private String sfom28 = ""; //     sonet        3  0       3        59        begge    omregn.fakt. rubr.28   
	private String sfxxx = ""; //      tegn            8       8        62        begge    ledige plasser
}
