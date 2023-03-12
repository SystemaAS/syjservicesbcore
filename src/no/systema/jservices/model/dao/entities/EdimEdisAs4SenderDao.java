package no.systema.jservices.model.dao.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class EdimEdisAs4SenderDao implements Serializable, IDao  {

	private String msn = "";           
	private String sifs = "";        
	
}
