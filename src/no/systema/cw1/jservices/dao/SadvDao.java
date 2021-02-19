package no.systema.cw1.jservices.dao;

import java.io.Serializable;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadvDao implements Serializable, IDao  {

	//Internt  feltnavn	Felt-  lengde  i byte	Desimal-  posisjoner	Beskrivelse av  felt	Felt-  type
	private String sv01 = "";// 	1	0	*blanks                                           	a
	private String svavd = "";//      	4	0	avdeling                                          	s
	private String svtdn = "";//      	7	0	tolldeklarasjonsnr                                	s
	private String svli = "";//       	5	0	line_no.                                          	s
	private String svdp = "";//       	2	0	dekl.prosedyre                                    	s
	private String svlk = "";//       	2	0	landk. oppr.land                                  	a
	private String svvnt = "";//      	8	0	varenr tolltariff                                 	s
	private String svtn = "";//       	1	0	tollnedsettelse                                   	a
	private String svpre = "";//      	1	0	preferanse                                        	a
	private String svas = "";//       	7	2	tollsats                                          	s
	private String svpva = "";//      	1	0	enhet pvaf                                        	a
	private String svmfr = "";//      	1	0	mva fri kod                                       	a
	private String svvf = "";//       	1	0	verdi fasts.kode                                  	a
	private String svvktb = "";//     	12	3	vekt brutto                                       	s
	private String svntm = "";//      	9	0	mengde                                            	s
	private String svbelt = "";//     	13	2	beløp tollverdi                                   	s
	private String svbels = "";//     	13	2	stat. tollverdi                                   	s
	private String svvktn = "";//     	12	3	vekt netto                                        	s
	private String svft01 = "";// 	28	0	merke & nr 1	a
	private String svft02 = "";// 	28	0	merke & nr 2	a
	private String svft03 = "";// 	28	0	merke & nr 3	a
	private String svft04 = "";// 	28	0	merke & nr 4	a
	private String svft05 = "";// 	28	0	merke & nr 5	a
	private String svft06 = "";// 	28	0	merke & nr 6	a
	private String svft07 = "";// 	28	0	merke & nr 7	a
	private String svnt01 = "";// 	6	0	antall 1	s
	private String svnt02 = "";// 	6	0	antall 2	s
	private String svnt03 = "";// 	6	0	antall 3	s
	private String svnt04 = "";// 	6	0	antall 4	s
	private String svnt05 = "";// 	6	0	antall 5	s
	private String svnt06 = "";// 	6	0	antall 6	s
	private String svnt07 = "";// 	6	0	antall 7	s
	private String sveh01 = "";// 	4	0	enhet                                             	a
	private String sveh02 = "";// 	4	0	enhet                                             	a
	private String sveh03 = "";// 	4	0	enhet                                             	a
	private String sveh04 = "";// 	4	0	enhet                                             	a
	private String sveh05 = "";// 	4	0	enhet                                             	a
	private String sveh06 = "";// 	4	0	enhet                                             	a
	private String sveh07 = "";// 	4	0	enhet                                             	a
	private String svvt01 = "";// 	30	0	varetekst 1	a
	private String svvt02 = "";// 	30	0	varetekst 2	a
	private String svvt03 = "";// 	30	0	varetekst 3	a
	private String svvt04 = "";// 	30	0	varetekst 4	a
	private String svvt05 = "";// 	30	0	varetekst 5	a
	private String svcre1 = "";// 	3	0	kommunikasjonref. 1	a
	private String svcre2 = "";// 	3	0	kommunikasjonref. 2	a
	private String svcre3 = "";// 	3	0	kommunikasjonref. 3	a
	private String svcre4 = "";// 	3	0	kommunikasjonref. 4	a
	private String svcre5 = "";// 	3	0	kommunikasjonref. 5	a
	private String svcre6 = "";// 	3	0	kommunikasjonref. 6	a
	private String svcre7 = "";// 	3	0	kommunikasjonref. 7	a
	private String svcre8 = "";// 	3	0	kommunikasjonref. 8	a
	private String svcre9 = "";// 	3	0	kommunikasjonref. 9	a
	private String svcre10 = "";// 	3	0	kommunikasjonref. 10	a
	private String svtoa = "";//      	22	0	tilleggsopplysninger                              	a
	private String svtob = "";//      	23	0	tilleggsopplysninger                              	a
	private String svtop1 = "";// 	17	0	komm.ref. 1	a
	private String svtop2 = "";// 	17	0	komm.ref. 2	a
	private String svtop3 = "";// 	17	0	komm.ref. 3	a
	private String svtop4 = "";// 	17	0	komm.ref. 4	a
	private String svtop5 = "";// 	17	0	komm.ref. 5	a
	private String svtop6 = "";// 	17	0	komm.ref. 6	a
	private String svtop7 = "";// 	17	0	komm.ref. 7	a
	private String svtop8 = "";// 	17	0	komm.ref. 8	a
	private String svtop9 = "";// 	17	0	komm.ref. 9	a
	private String svtop10 = "";// 	17	0	komm.ref. 10	a
	private String svakd1 = "";// 	2	0	avgiftskode                                       	a
	private String svakd2 = "";// 	2	0	avgiftskode                                       	a
	private String svakd3 = "";// 	2	0	avgiftskode                                       	a
	private String svakd4 = "";// 	2	0	avgiftskode                                       	a
	private String svakd5 = "";// 	2	0	avgiftskode                                       	a
	private String svakd6 = "";// 	2	0	avgiftskode                                       	a
	private String svakd7 = "";// 	2	0	avgiftskode                                       	a
	private String svakd8 = "";// 	2	0	avgiftskode                                       	a
	private String svasv1 = "";// 	3	0	avgifts sekvens 1	a
	private String svasv2 = "";// 	3	0	avgifts sekvens 2	a
	private String svasv3 = "";// 	3	0	avgifts sekvens 3	a
	private String svasv4 = "";// 	3	0	avgifts sekvens 4	a
	private String svasv5 = "";// 	3	0	avgifts sekvens 5	a
	private String svasv6 = "";// 	3	0	avgifts sekvens 6	a
	private String svasv7 = "";// 	3	0	avgifts sekvens 7	a
	private String svasv8 = "";// 	3	0	avgifts sekvens 7	a
	private String svabl1 = "";// 	13	2	avgift 1	s
	private String svabl2 = "";// 	13	2	avgift 2	s
	private String svabl3 = "";// 	13	2	avgift 3	s
	private String svabl4 = "";// 	13	2	avgift 4	s
	private String svabl5 = "";// 	13	2	avgift 5	s
	private String svabl6 = "";// 	13	2	avgift 6	s
	private String svabl7 = "";// 	13	2	avgift 7	s
	private String svabl8 = "";// 	13	2	avgift 8	s
	private String svagr1 = "";// 	13	2	avgiftsgrunnlag 1	s
	private String svagr2 = "";// 	13	2	avgiftsgrunnlag 2	s
	private String svagr3 = "";// 	13	2	avgiftsgrunnlag 3	s
	private String svagr4 = "";// 	13	2	avgiftsgrunnlag 4	s
	private String svagr5 = "";// 	13	2	avgiftsgrunnlag 5	s
	private String svagr6 = "";// 	13	2	avgiftsgrunnlag 6	s
	private String svagr7 = "";// 	13	2	avgiftsgrunnlag 7	s
	private String svagr8 = "";// 	13	2	avgiftsgrunnlag 8	s
	private String svasa1 = "";// 	7	2	toll og avg.sats 1	s
	private String svasa2 = "";// 	7	2	toll og avg.sats 2	s
	private String svasa3 = "";// 	7	2	toll og avg.sats 3	s
	private String svasa4 = "";// 	7	2	toll og avg.sats 4	s
	private String svasa5 = "";// 	7	2	toll og avg.sats 5	s
	private String svasa6 = "";// 	7	2	toll og avg.sats 6	s
	private String svasa7 = "";// 	7	2	toll og avg.sats 7	s
	private String svasa8 = "";// 	7	2	toll og avg.sats 8	s
	private String sveh21 = "";// 	1	0	satsenhet 1	a
	private String sveh22 = "";// 	1	0	satsenhet 2	a
	private String sveh23 = "";// 	1	0	satsenhet 3	a
	private String sveh24 = "";// 	1	0	satsenhet 4	a
	private String sveh25 = "";// 	1	0	satsenhet 5	a
	private String sveh26 = "";// 	1	0	satsenhet 6	a
	private String sveh27 = "";// 	1	0	satsenhet 7	a
	private String sveh28 = "";// 	1	0	satsenhet 8	a
	private String svpreæ = "";//   	1	0	omber. j=ny/s=slett                               	a
	private String sveh2æ = "";// 	1	0	satsenhet                                         	a
	private String svln = "";//       	5	0	linjenr                                           	s
	private String svrefl = "";//     	5	0	ant. till.linjer                                  	s
	private String svexr01 = "";// 	5	0	system                                            	a
	private String svexr02 = "";// 	17	0	ekstern ref.                                      	a
	private String svexr03 = "";// 	35	0	andre ref.                                        	a
	private String svexr04 = "";// 	9	0	linjenr                                           	s
	private String svexr05 = "";// 	5	0	underlinjenr                                      	s
	private String svexr06 = "";// 	3	0	valuta                                            	a
	private String svexr07 = "";// 	11	2	beløp                                             	s
	private String svexr08 = "";// 	10	0	member                                            	a
	private String sverr = "";//      	1	0	error flag                                        	a
	
}
