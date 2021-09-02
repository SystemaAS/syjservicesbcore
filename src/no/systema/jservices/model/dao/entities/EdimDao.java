package no.systema.jservices.model.dao.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class EdimDao implements Serializable, IDao  {

	private String m0004 = ""; //    tegn           35      35         1        begge    interchange_id --> från svtfi.svtf_0004  interchange_id sender         
	private String m0010 = ""; //          tegn           35      35        36        begge    interchange_id --> från svtfi.svtf_0010  interchange_id receiver       
	private String m0035 = ""; //          tegn            1       1        71        begge    testindicator  --> svih_0035 testindicator                     
	private String m0062 = ""; //          tegn           14      14        72        begge    message_reference         
	private String m0065 = ""; //          tegn            6       6        86        begge    message_id                
	private String m0068 = ""; //          tegn           35      35        92        begge    common access ref                
	private String m1001  = ""; //         tegn            3       3       127        begge    message name                     
	private String m1004  = ""; //         tegn           35      35       130        begge    message number                  
	private String m1225  = ""; //         tegn            3       3       165        begge    message function                
	private String msn  = ""; //           sonet        9  0       9       168        begge    interchange_no.               
	private String mmn  = ""; //           sonet        9  0       9       177        begge    message_no.                
	private String msr  = ""; //           tegn            1       1       186        begge    send/recive                
	private String mst  = ""; //           tegn            1       1       187        begge    status                     
	private String mdt  = ""; //           sonet        8  0       8       188        begge    status_date                
	private String mtm   = ""; //          sonet        6  0       6       196        begge    status_time                
	private String mven   = ""; //         tegn            1       1       202        begge    delay_code                 
	private String m0068a  = ""; //        sonet        8  0       8       203        begge    dekl.dato                     
	private String m0068b  = ""; //        sonet        6  0       6       211        begge    dekl.sekvensnr                
	private String m0068c  = ""; //        sonet        2  0       2       217        begge    dekl.versjonsnr                
	private String m0068d  = ""; //        sonet        8  0       8       219        begge    dekl.dato                      
	private String m0068e  = ""; //        sonet        6  0       6       227        begge    dekl.sekvensnr                 
	private String m0068f  = ""; //        sonet        2  0       2       233        begge    dekl.versjonsnr                
	private String m2005b  = ""; //        sonet        8  0       8       235        begge    ønsket behandlingsdato          
	private String m3039d  = ""; //        sonet        8  0       8       243        begge    deklarantens referanse         
	private String m3039e  = ""; //        sonet        6  0       6       251        begge    ekspidisjons enhet             
	private String m5004d  = ""; //        sonet       10  0      10       257        begge    depositum beløp                 
	private String m1n07   = ""; //        tegn            3       3       267        begge    meldingsfunksjon                
	private String m1n08   = ""; //        tegn            2       2       270        begge    meldingsfunksjon korrigert      
	private String m9n01  = ""; //         sonet        1  0       1       272        begge    ekspidisjons prioritet         
	private String mavd   = ""; //         sonet        4  0       4       273        begge    avdeling                       
	private String mtdn   = ""; //         sonet        7  0       7       277        begge    tolldeklarasjonsnr                
	private String mffbnr  = ""; //        sonet        3  0       3       284        begge    fraktbrevnummer                   
	

}
