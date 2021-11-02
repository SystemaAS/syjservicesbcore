package no.systema.jservices.model.dao.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class Sviva_aggDao implements Serializable, IDao  {

	private String sviva_sav = "0"; // SONET        4  0       4         1        Begge    SYSPED Avdeling g         
	private String sviva_sop = "0"; //SONET        7  0       7         5        Begge    SYSPED Oppdragsn       
	private String sviva_sli = "0"; //SONET        5  0       5        12        Begge    SYSPED Linjenr
	private String sviva_abk = ""; //TEGN            3       3        17        Begge    Avgber.avgkod
	private String sviva_abg = "0"; // SONET       11  3      11        20        Begge    Avgber.besk.gr  
	private String sviva_abs = "0"; // SONET        9  4       9        31        Begge    Avgber.avg.sats
	private String sviva_abx = ""; // TEGN            4       4        40        Begge    Avgber.x.menhet
	private String sviva_abb = "0"; // SONET        9  0       9        44        Begge    Belopp 
	
	
	
}
