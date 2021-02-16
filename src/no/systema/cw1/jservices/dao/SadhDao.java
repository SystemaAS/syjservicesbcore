package no.systema.cw1.jservices.dao;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;

import lombok.Data;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 * 
 * Sadh = SAD Import
 * 
 * This dao has been created in order extract all fields from SADH in order to provide db-data as a service 
 * to a CargoWise1-eHubConnect consumer (client) 
 * 
 */
@Data
public class SadhDao implements Serializable, IDao {
	private String sist = "";//      	1	0	status                      	a
	private String siavd = "";//     	4	0	avdeling                                          	s
	private String sitdn = "";//     	7	0	tolldeklarasjonsnr                                	s
	private String sisg = "";//      	3	0	signatur                                          	a
	private String sidt = "";//      	8	0	registreringsdato                                 	s
	private String siur = "";//      	1	0	oppr.kode                                         	a
	private String sidty = "";//     	2	0	deklarasjonstype                                  	a
	private String sidp = "";//      	2	0	dekl.prosedyre                                    	s
	private String sikns = "";//     	8	0	kundenr selger                                    	s
	private String sinas = "";//     	30	0	navn selger                                       	a
	private String siads1 = "";//	30	0	adresse-1 selger	a
	private String siads2 = "";//	30	0	adresse-2 selger	a
	private String siads3 = "";//	30	0	adresse-3 selger	a
	private String sintk = "";//     	4	0	antall kolli                                      	p
	private String siski = "";//     	1	0	selg,kjøper,ingen                                 	a
	private String sikddk = "";//    	1	0	dagsoppgjør/kontant                               	a
	private String sivkb = "";//     	5	0	bruttovekt                                        	p
	private String sikdc = "";//     	1	0	container 0/1	a
	private String siknk = "";//     	8	0	kundenr kjøper                                    	s
	private String sirg = "";//      	11	0	foretaksnr.                                       	a
	private String simva = "";//     	1	0	j/n mva-registrert                                	a
	private String sinak = "";//     	30	0	navn kjøper                                       	a
	private String siadk1 = "";//	30	0	adresse-1 kjøper	a
	private String siadk2 = "";//	30	0	adresse-2 kjøper	a
	private String siadk3 = "";//	30	0	adresse-3 kjøper	a
	private String sival1 = "";//	3	0	valutakode frakt                                  	a
	private String sibel1 = "";//	6	2	beløp tollb.frakt                                 	p
	private String sival2 = "";//	3	0	val.kode a. kost                                  	a
	private String sibel2 = "";//	6	2	beløp a. kost                                     	p
	private String siftg2 = "";//	1	0	fortegn +/- a.kost                                	a
	private String silka = "";//     	2	0	landk avs/utf-land                                	a
	private String sitlf = "";//     	12	0	telefon deklarant                                 	a
	private String sinad = "";//     	30	0	navn deklarant                                    	a
	private String silv = "";//      	3	0	leveringsvilkår kod                               	a
	private String silvt = "";//     	17	0	leveringsvilkår txt                               	a
	private String sitrid = "";//    	17	0	transport id                                      	a
	private String silkt = "";//     	2	0	landk tr.middel                                   	a
	private String sival3 = "";//	3	0	val.kode faktsum                                  	a
	private String sibel3 = "";//	7	2	beløp faktsum                                     	p
	private String sivku = "";//     	4	3	valutakurs                                        	p
	private String sitst = "";//     	1	0	transaksjonstype                                  	s
	private String sitrt = "";//     	7	0	transporttype                                     	s
	private String sitrm = "";//     	2	0	transportmåte                                     	s
	private String sifif = "";//    	17	0	finans oppl.faknr                                 	a
	private String sifid = "";//    	8	0	finans oppl.fakdat                                	s
	private String sibelt = "";//    	11	0	beløp trafikkavg                                  	s
	private String si07 = "";//	7	0	*blanks                                           	a
	private String sikta = "";//     	5	0	tollkontonr-1	s
	private String siktb = "";//     	2	0	tollkontonr-2	s
	private String sign = "";//      	15	0	godsnr.                                           	a
	private String sift1 = "";//	45	0	fritekst-1	a
	private String sift2 = "";//	45	0	fritekst-2	a
	private String sift3 = "";//	45	0	fritekst-3	a
	private String sift4 = "";//	45	0	fritekst-4	a
	private String sidst = "";//     	15	0	deklarantens sted                                 	a
	private String sibel4 = "";//	11	0	beløp cifsum                                      	s
	private String sidtg = "";//     	10	0	dekl. godkj.dato                                  	a
	private String sitll = "";//     	10	0	tollnr løpenr                                     	a
	private String sitle = "";//     	6	0	tollnr ekspsted                                   	a
	private String sils = "";//      	16	0	lagringssted tekst                                	a
	private String sikdls = "";//    	2	0	lagringssted kode                                 	a
	private String sikdh = "";//     	4	0	havnekode                                         	s
	private String sikdtr = "";//    	1	0	transportavgift kode                              	a
	private String sias = "";//      	5	1	avgiftssats transp                                	s
	private String sibel5 = "";//	11	0	beløp tot toll                                    	s
	private String sibel6 = "";//	11	0	beløp tot mva                                     	s
	private String sibel7 = "";//	6	0	beløp tot avg                                     	p
	private String sibel8 = "";//	6	2	frakt omberegnet                                  	p
	private String sibel9 = "";//	6	2	andre kost.omber                                  	p
	private String sibela = "";//    	9	0	beløp forsikring                                  	s
	private String silv2 = "";//	3	0	leveringsvilkår kod                               	a
	private String sipos = "";//     	9	0	posisjon                                          	a
	private String sitarf = "";//    	16	0	tarifførsnavn                                     	a
	private String sivalb = "";//    	3	0	valutakode work                                   	a
	private String sibelb = "";//    	5	2	beløp work                                        	s
	private String simid = "";//     	2	0	meldings id                                       	a
	private String simidk = "";//    	2	0	meldings id korr                                  	a
	private String simf = "";//      	3	0	meldingsfunksjon                                  	a
	private String simp = "";//      	1	0	meldingsprioritet                                 	s
	private String sidtø = "";//     	8	0	ønsket beh.dato                                   	s
	private String simi = "";//      	1	0	midlertidig  /i                                   	a
	private String sibeld = "";//    	10	0	beløp depositum                                   	s
	private String sipkl = "";//     	1	0	print ved linje ?                                 	a
	private String sikdv = "";//     	1	0	ventekode tad                                     	a
	private String si0035 = "";//	1	0	testindicator                                     	a
	private String si27 = "";//	27	0	*blanks                                           	a
	private String siopd = "";//     	7	0	oppdragsnummer                                    	s
	private String sifrbn = "";//    	3	0	fraktbrevnummer                                   	s
	private String siws = "";//      	10	0	arbeidsstasjons id                                	a
	private String siktc = "";//     	1	0	ikke tollkreditt                                  	a
	private String sivalr = "";//    	3	0	valutakode                                        	a
	private String sibelr = "";//    	6	2	bearbeidskostnad                                  	p
	private String sibels = "";//    	5	0	cifsum pros=64/65	p
	
		
}
