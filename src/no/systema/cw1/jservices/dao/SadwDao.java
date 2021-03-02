package no.systema.cw1.jservices.dao;

import java.io.Serializable;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadwDao implements Serializable, IDao{
	//Internt  feltnavn	Felt-  lengde  i byte	Desimal-  posisjoner	Beskrivelse av  felt	Referanse-  felt	Kolonneoverskrift 1	Felt-  type
	private String swavd = "";//     	4	0	avdeling                                          	avd       	avdeling            	s
	
	private String swtdn = "";//     	7	0	tolldeklarasjonsnr                                	tdn       	tolldeklarasjonsnr  	s
	private String swli = "";//      	5	0	line_no.                                          	li5	line_no.            	s
	private String swlk = "";//      	2	0	landk. oppr.land                                  	lka       	landk. oppr.land    	a
	private String swvnt = "";//     	8	0	varenr tolltariff                                 	vnt       	varenr tolltariff   	s
	private String swtn = "";//      	1	0	tollnedsettelse                                   	tn        	tollnedsettelse     	a
	private String swpre = "";//     	1	0	preferanse                                        	pre       	preferanse          	a
	private String swas = "";//      	3	2	avgiftssats                                       	as52	avgiftssats         	p
	private String swpva = "";//     	1	0	enhet pvaf                                        	pva       	enhet pvaf          	a
	private String swmfr = "";//     	1	0	mva fri kod                                       	mfr       	mva fri kod         	a
	private String swvf = "";//      	1	0	verdi fasts.kode                                  	vf        	verdi fasts.kode    	a
	private String swvktb = "";//    	5	0	vekt brutto                                       	vkt9	vekt brutto         	p
	private String swntm = "";//     	4	0	mengde                                            	nt        	mengde              	p
	private String swbelt = "";//    	6	2	verdi val,ex.just                                 	bel11	verdi val,ex.just   	p
	private String swbels = "";//    	6	0	stat. tollverdi                                   	          	stat. tollverdi     	p
	private String swvktn = "";//    	5	0	vekt netto                                        	vkt9	vekt netto          	p
	private String swwjus = "";//    	6	0	justeringsbeløp                                   	          	justeringsbeløp     	p
	private String swwftg = "";//    	1	0	fortegn jus.beløp                                 	          	fortegn jus.beløp   	a
	private String swwmva = "";//    	5	0	moms                                              	          	moms                	p
	private String swwmvg = "";//    	5	0	momsgrunnlag                                      	          	momsgrunnlag        	p
	private String swwtol = "";//    	5	0	toll                                              	          	toll                	p
	private String swwtlg = "";//    	5	0	tollgrunnlag                                      	          	tollgrunnlag        	p
	private String swwtra = "";//    	5	0	trafikkavgift                                     	          	trafikkavgift       	p
	private String swwsat = "";//    	3	2	sats trafikkavg.                                  	          	sats trafikkavg.    	p
	private String swwsum = "";//    	6	0	sum avg. exl. mva                                 	          	sum avg. exl. mva   	p
	private String swwty1 = "";//	2	0	avgifttype-1	          	avgifttype-1	a
	private String swwty2 = "";//	2	0	avgifttype-2	          	avgifttype-2	a
	private String swwty3 = "";//	2	0	avgifttype-3	          	avgifttype-3	a
	private String swwty4 = "";//	2	0	avgifttype-4	          	avgifttype-4	a
	private String swwty5 = "";//	2	0	avgifttype-5	          	avgifttype-5	a
	private String swwst1 = "";//	4	2	sats avgift-1	          	sats avgift-1	p
	private String swwst2 = "";//	4	2	sats avgift-2	          	sats avgift-2	p
	private String swwst3 = "";//	4	2	sats avgift-3	          	sats avgift-3	p
	private String swwst4 = "";//	4	2	sats avgift-4	          	sats avgift-4	p
	private String swwst5 = "";//	4	2	sats avgift-5	          	sats avgift-5	p
	private String swwgl1 = "";//	5	2	grunnlag avgift-1	          	grunnlag avgift-1	p
	private String swwgl2 = "";//	5	2	grunnlag avgift-2	          	grunnlag avgift-2	p
	private String swwgl3 = "";//	5	2	grunnlag avgift-3	          	grunnlag avgift-3	p
	private String swwgl4 = "";//	5	2	grunnlag avgift-4	          	grunnlag avgift-4	p
	private String swwgl5 = "";//	5	2	grunnlag avgift-5	          	grunnlag avgift-5	p
	private String swwpr1 = "";//	1	0	type for sats-1	          	type for sats-1	a
	private String swwpr2 = "";//	1	0	type for sats-2	          	type for sats-2	a
	private String swwpr3 = "";//	1	0	type for sats-3	          	type for sats-3	a
	private String swwpr4 = "";//	1	0	type for sats-4	          	type for sats-4	a
	private String swwpr5 = "";//	1	0	type for sats-5	          	type for sats-5	a
	private String swwbl1 = "";//	4	0	beløp avgift-1	          	beløp avgift-1	p
	private String swwbl2 = "";//	4	0	beløp avgift-2	          	beløp avgift-2	p
	private String swwbl3 = "";//	4	0	beløp avgift-3	          	beløp avgift-3	p
	private String swwbl4 = "";//	4	0	beløp avgift-4	          	beløp avgift-4	p
	private String swwbl5 = "";//	4	0	beløp avgift-5	          	beløp avgift-5	p
	private String swwpro = "";//    	1	0	prosenttegn                                       	          	prosenttegn         	a
	private String swpaca = "";//    	3	0	ant. (alltid 1?	          	ant. (alltid 1?	p
	private String swpacb = "";//    	15	0	merke                                             	          	merke               	a
	private String swtx1a = "";//	4	1	vekt                                              	          	vekt                	p
	private String swtx1b = "";//	4	0	vekt-avgift                                       	          	vekt-avgift         	p
	private String swvekm = "";//    	1	0	vkt-avg manuelt satt                              	          	vkt-avg manuelt satt	a
	private String swtx1c = "";//	10	0	modellbetegn.1	          	modellbetegn.1	a
	private String swtx2a = "";//	4	2	effekt                                            	          	effekt              	p
	private String swtx2b = "";//	4	0	effekt-avgift                                     	          	effekt-avgift       	p
	private String sweffm = "";//    	1	0	eff-avg manuelt satt                              	          	eff-avg manuelt satt	a
	private String swtx2c = "";//	10	0	modellbetegn.2	          	modellbetegn.2	a
	private String swtx3a = "";//	4	0	kubikk                                            	          	kubikk              	p
	private String swtx3b = "";//	4	0	kubikk-avgift                                     	          	kubikk-avgift       	p
	private String swkubm = "";//    	1	0	kub-avg manuelt satt                              	          	kub-avg manuelt satt	a
	private String swtx3c = "";//	13	0	typegodkjenning                                   	          	typegodkjenning     	a
	private String swpc1a = "";//	4	0	cif-verdi                                         	          	cif-verdi           	p
	private String swpc1b = "";//	4	0	verdi-avgift                                      	          	verdi-avgift        	p
	private String swcifm = "";//    	1	0	cif-avg manuelt satt                              	          	cif-avg manuelt satt	a
	private String swpc1c = "";//	1	0	avg.grp.                                          	          	avg.grp.            	a
	private String swpc1d = "";//	2	0	kalusul                                           	          	kalusul             	a
	private String swpc2a = "";//	3	2	% av total                                        	          	% av total          	p
	private String swprom = "";//    	1	0	%/tot   manuelt satt                              	          	%/tot   manuelt satt	a
	private String swpc2b = "";//	4	0	total avgifts-grl.                                	          	total avgifts-grl.  	p
	private String swvrak = "";//    	3	0	vrakpant                                          	          	vrakpant            	p
	private String swvram = "";//    	1	0	vrakp. manuelt satt                               	          	vrakp. manuelt satt 	a
	private String swpc2c = "";//	4	0	eng.avg. som betales                              	          	eng.avg. som betales	p
	private String swengm = "";//    	1	0	eng.avg manuelt satt                              	          	eng.avg manuelt satt	a
	private String swr441 = "";//	17	0	rubr.44 lin.1	          	rubr.44 lin.1	a
	private String swref1 = "";//	3	0	rubr.44 lin.1	          	rubr.44 lin.1	a
	private String swr442 = "";//	17	0	rubr.44 lin.2	          	rubr.44 lin.2	a
	private String swref2 = "";//	3	0	rubr.44 lin.2	          	rubr.44 lin.2	a
	private String swr443 = "";//	17	0	rubr.44 lin.3	          	rubr.44 lin.3	a
	private String swref3 = "";//	3	0	rubr.44 lin.3	          	rubr.44 lin.3	a
	private String swr444 = "";//	17	0	rubr.44 lin.4	          	rubr.44 lin.4	a
	private String swref4 = "";//	3	0	rubr.44 lin.4	          	rubr.44 lin.4	a
	private String swr445 = "";//	17	0	rubr.44 lin.5	          	rubr.44 lin.5	a
	private String swref5 = "";//	3	0	rubr.44 lin.5	          	rubr.44 lin.5	a
	private String swwsk1 = "";//	3	0	avg.sekvens-1	          	avg.sekvens-1	a
	private String swwsk2 = "";//	3	0	avg.sekvens-2	          	avg.sekvens-2	a
	private String swwsk3 = "";//	3	0	avg.sekvens-3	          	avg.sekvens-3	a
	private String swwsk4 = "";//	3	0	avg.sekvens-4	          	avg.sekvens-4	a
	private String swwsk5 = "";//	3	0	avg.sekvens-5	          	avg.sekvens-5	a
	private String swunnr = "";//    	17	0	understellsnr                                     	          	understellsnr       	a
	private String swunnm  = "";//   	1	0	und.nr manu (<17 ch)	          	und.nr manu (<17 ch)	a
	private String swmeko  = "";//   	10	0	merke kode                                        	          	merke kode          	a
	private String swmoty = "";//    	10	0	modell type                                       	          	modell type         	a
	private String swktgr = "";//    	30	0	kjøretøy-grp                                      	          	kjøretøy-grp        	a
	private String swktko  = "";//   	5	0	kjøretøy-kode                                     	          	kjøretøy-kode       	a
	private String swktfa = "";//    	10	0	kjøretøy-farge                                    	          	kjøretøy-farge      	a
	
}
