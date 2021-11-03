package no.systema.jservices.model.dao.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class SvivRflnDao implements Serializable, IDao  {

	private String sviv_syav = "0";          
	private String sviv_syop = "0";        
	private String sviv_syli = "0";
	private String sviv_vano = "0";
	private String sviv_rfln = "0";
	private String sviv_ulkd = "";
	private String sviv_vata = "";
	
	
	
}
