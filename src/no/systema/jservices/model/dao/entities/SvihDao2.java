package no.systema.jservices.model.dao.entities;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


/**
 * 
 * Header db-file (Parent) SVIH
 * @author oscardelatorre
 * @date Nov 2021
 * 
 */

@Data
public class SvihDao2 implements Serializable, IDao  {
	
	
	private String svih_syav = "";//   sonet        4  0       4         1        begge    sysped avdeling  
	private String svih_syop = "";//  sonet        7  0       7         5        begge    sysped oppdragsnr
	private String svih_sysg = "";//  tegn            3       3        12        begge    sysped signatur  
	private String svih_syst = "";//  tegn            1       1        15        begge    sysped status    
	private String svih_sydt = "";//  sonet        8  0       8        16        begge    sysped datum     
	private String svih_mtyp = "";//  tegn            3       3        24        begge    meddelandetyp    
	private String svih_tuid = "";//  tegn           10      10        27        begge    tullid           
	private String svih_kenh = "";//  tegn           35      35        37        begge    klareringsenhet
	private String svih_dek1 = "";//  tegn            2       2        72        begge    deklaration1   
	private String svih_dek2 = "";//  tegn            1       1        74        begge    deklaration2   
	private String svih_tart = "";//  tegn            1       1        75        begge    transaktionsart
	private String svih_avkn = "";//  sonet        8  0       8        76        begge    avsändare, knr 
	private String svih_aveo = "";//  tegn           17      17        84        begge    avsändare, eori
	private String svih_avna = "";//  tegn           35      35       101        begge    avsändare, namn
	private String svih_ava1 = "";//  tegn           35      35       136        begge    avsändare, adr1  
	private String svih_ava2 = "";//  tegn           35      35       171        begge    avsändare, adr2  
	private String svih_avpn = "";//  tegn            9       9       206        begge    avsändare, p-nr  
	private String svih_avpa = "";//  tegn           35      35       215        begge    avsändare, p-adr 
	private String svih_avlk = "";//  tegn            2       2       250        begge    avsändare, l-kod 
	private String svih_kota = "";//  sonet        8  0       8       252        begge    kollital         
	private String svih_rfab = "";//  tegn           17      17       260        begge    ref.nr abe     
	private String svih_rfac = "";//  tegn           17      17       277        begge    ref.nr acd     
	private String svih_rfac2 = "";// tegn           17      17       294        begge    ref.nr acd     
	private String svih_mokn = "";//  sonet        8  0       8       311        begge    mottagare, knr 
	private String svih_moeo = "";//  tegn           17      17       319        begge    mottagare, eori
	private String svih_mona = "";//  tegn           35      35       336        begge    mottagare, namn
	private String svih_moa1 = "";//  tegn           35      35       371        begge    mottagare, adr1
	private String svih_moa2 = "";//  tegn           35      35       406        begge    mottagare, adr2   
	private String svih_mopn = "";//  tegn            9       9       441        begge    mottagare, p-nr   
	private String svih_mopa = "";//  tegn           35      35       450        begge    mottagare, p-adr  
	private String svih_molk = "";//  tegn            2       2       485        begge    mottagare, l-kod  
	private String svih_moha = "";//  tegn           35      35       487        begge    mottagare, handl. 
	private String svih_motl = "";//  tegn           25      25       522        begge    mottagare, telefon
	private String svih_vufo = "";//  sonet       11  3      11       547        begge    värdeupp, försäkring
	private String svih_vufr = "";//  sonet       11  3      11       558        begge    värdeupp, fraktkost 
	private String svih_vuva = "";//  tegn            3       3       569        begge    värdeupp, fraktvalut
	private String svih_vuku = "";//  sonet        7  4       7       572        begge    värdeupp, fraktkurs 
	private String svih_ovko = "";//  sonet       11  3      11       579        begge    övriga kostnader    
	private String svih_kara = "";//  sonet       11  3      11       590        begge    kassarabatt         
	private String svih_anra = "";//  sonet       11  3      11       601        begge    annan rabatt        
	private String svih_kvsa = "";//  tegn            1       1       612        begge    kvalitetssäkrad     
	private String svih_dkkn = "";//  sonet        8  0       8       613        begge    deklarant, knr      
	private String svih_dkeo = "";//  tegn           17      17       621        begge    deklarant, eori 
	private String svih_dklk = "";//  tegn            2       2       638        begge    deklarant, l-kod
	private String svih_dkna = "";//  tegn           35      35       640        begge    deklarant, namn 
	private String svih_dka1 = "";//  tegn           35      35       675        begge    deklarant, adr1 
	private String svih_dka2 = "";//  tegn           35      35       710        begge    deklarant, adr2 
	private String svih_dkpa = "";//  tegn           35      35       745        begge    deklarant, p-adr
	private String svih_dkpn = "";//  tegn            9       9       780        begge    deklarant, p-nr    
	private String svih_dkha = "";//  tegn           35      35       789        begge    deklarant, handlägg
	private String svih_dktl = "";//  tegn           25      25       824        begge    deklarant, telefon 
	private String svih_dkem = "";//  tegn           75      75       824        begge    deklarant, email
	private String svih_omeo = "";//  tegn           17      17       849        begge    ombud, eori        
	private String svih_omty = "";//  tegn            1       1       866        begge    ombud, typ av omb  
	private String svih_omha = "";//  tegn           35      35       867        begge    ombud, handlägg    
	private String svih_omtl = "";//  tegn           25      25       902        begge    ombud, telefon 
	private String svih_omem = "";//  tegn           75      75                 begge    ombud, email
	private String svih_avut = "";//  tegn            2       2       927        begge    avsl/utfl.kod  
	private String svih_aukd = "";//  tegn            2       2       929        begge    avsändningsland
	private String svih_trid = "";//  tegn           17      17       931        begge    transp. id     
	private String svih_trlk = "";//  tegn            2       2       948        begge    transp. l-kod  
	private String svih_cont = "";//  tegn            1       1       950        begge    cont.kod       
	private String svih_lekd = "";//  tegn            3       3       951        begge    lev.villkår kod
	private String svih_leor = "";//  tegn           25      25       954        begge    lev.villkår ort 
	private String svih_trai = "";//  tegn           17      17       979        begge    aktiva tr, id   
	private String svih_tral = "";//  tegn            2       2       996        begge    aktiva tr, l-kod
	private String svih_faty = "";//  tegn            4       4       998        begge    fakturatyp      
	private String svih_fatx = "";//  tegn           17      17      1002        begge    fakturanummer   
	private String svih_vakd = "";//  tegn            3       3      1019        begge    valutaslag, kod 
	private String svih_vaku = "";// sonet        7  4       7      1022        begge    valutaslag, kurs     
	private String svih_fabl = "";//  sonet       11  3      11      1029        begge    fakturabelopp        
	private String svih_trgr = "";//  tegn            1       1      1040        begge    transportsätt, gräns 
	private String svih_trin = "";//  tegn            1       1      1041        begge    transportsätt, inrik 
	private String svih_grkd = "";//  tegn           17      17      1042        begge    gränskod             
	private String svih_golk = "";//  tegn            4       4      1059        begge    godslokalkod         
	private String svih_brut = "";//  sonet       12  3      12      1063        begge    bruttovikt           
	private String svih_suti = "";//  tegn           17      17      1075        begge    särsk.uppl.tullid    
	private String svih_abub = "";//  tegn           35      35      1092        begge    avgber.upp.bet      
	private String svih_eup1 = "";//  tegn            4       4      1127        begge    r37-1               
	private String svih_eup2 = "";//  tegn            3       3      1131        begge    r37-2               
	private String svih_upps = "";//  tegn            1       1      1134        begge    uppskjuten betalning
	private String svih_kleo = "";//  tegn           17      17      1135        begge    klareringsbehörig   
	private String svih_trnr = "";//  tegn           20      20      1152        begge    transitnummer       
	private String svih_sgdk = "";//  tegn            6       6      1172        begge    dokumentsigill  
	private String svih_sgme = "";//  tegn            3       3      1178        begge    sigillmetod     
	private String svih_sgut = "";//  tegn            6       6      1181        begge    utställarsigill 
	private String svih_sgid = "";//  tegn           10      10      1187        begge    id på utsällare 
	private String svih_sgdt = "";//  tegn           12      12      1197        begge    datum för sigill
	private String svih_byte = "";//  sonet        7  0       7      1209        begge    antall byte edi 
	private String svih_kval = "";//  tegn            1       1      1216        begge    kvalitetssäkrad 
	private String svih_godn = "";//  tegn           15      15      1217        begge    godsnummer   
	private String svih_0035 = "";//  tegn            1       1      1232        begge    testindikator
	private String svih_mrn = "";//  tegn            22       22      1232        begge   MASTERREF TULLVERKET
	private String svih_lrn = "";//  tegn            22       22      1232        begge   Tullid om 5 år
	

	
	
}
