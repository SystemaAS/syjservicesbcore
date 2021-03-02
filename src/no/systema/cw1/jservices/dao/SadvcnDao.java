package no.systema.cw1.jservices.dao;

import java.io.Serializable;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadvcnDao implements Serializable, IDao{
	private String svavd = "";//     	4	0	avdeling                                          	avd       	avdeling            	s
	private String svtdn = "";//     	7	0	tolldeklarasjonsnr                                	tdn       	tolldeklarasjonsnr  	s
	private String svli = "";//      	5	0	line_no.                                          	li5	line_no.            	s
	private String svcnr = "";//        17	0	containernr                                   	   lka       	landk. oppr.land    	a
		
}
