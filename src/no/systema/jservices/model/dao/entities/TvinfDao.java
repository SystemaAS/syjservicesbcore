package no.systema.jservices.model.dao.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class TvinfDao implements Serializable, IDao  {

	private String fmn = "";  // 		 sonet          9      Message_no         
	private String fln = "";  //         sonet          5      Line_no      
	private String f0077 = "";  //       tegn           3               
	private String f4815 = "";  //       tegn           2                   
	private String f0078a = "";  //      tegn           70      Free text               
	private String f0078b = "";  //      tegn           70      Free text               
	private String f0078c = "";  //      tegn           70      Free text               
	private String f0078d = "";  //      tegn           70      Free text               
	private String f0078e = "";  //      tegn           70      Free text               

}
