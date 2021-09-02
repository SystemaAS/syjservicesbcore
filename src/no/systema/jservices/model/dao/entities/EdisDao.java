package no.systema.jservices.model.dao.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class EdisDao implements Serializable, IDao  {

	private String s0004 = "";  // 		 tegn           35      35         1        begge    interchange_id sender         
	private String s0010 = "";  //       tegn           35      35        36        begge    interchange_id receiver       
	private String s0020 = "";  //       tegn           14      14        71        begge    interchange_ref.                 
	private String s0026 = "";  //       tegn           14      14        85        begge    application_ref.             
	private String s0035 = "";  //       tegn            1       1        99        begge    testindicator                
	private String s0036 = "";  //       sonet        7  0       7       100        begge    no_of_messages                 
	private String ssn = "";  //         sonet        9  0       9       107        begge    interchange_no.                
	private String ssr = "";  //         tegn            1       1       116        begge    send/recive                
	private String sst = "";  //         tegn            1       1       117        begge    status                     
	private String sdt = "";  //         sonet        8  0       8       118        begge    status_date                
	private String stm = "";  //         sonet        6  0       6       126        begge    status_time                
	private String slib = "";  //        tegn           10      10       132        begge     library                    
	private String sfile = "";  //       tegn           10      10       142        begge    file                      
	private String smbr = "";  //        tegn           10      10       152        begge    member    
	private String smbrs = "";  //       pakket      13  0       7       162        begge    mbr size-bytes
	private String sifs = "";  //        tegn          256     256       169        begge    ifs filnavn   
	
}
