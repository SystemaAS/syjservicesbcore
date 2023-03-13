package no.systema.jservices.model.dao.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class EdimEdisAs4SenderDao implements Serializable, IDao  {

	private String msn = "";           
	private String sifs = "";  
	//
	private String m0004 = "";
	private String m0010 = "";
	private String m0035 = "";
	private String m1001 = "";
	private String m1225 = "";
	private String m1004 = "";
	private String m0065 = "";
	private String msr = "";
	
	
}
