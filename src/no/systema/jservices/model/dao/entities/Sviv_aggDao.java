package no.systema.jservices.model.dao.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class Sviv_aggDao implements Serializable, IDao  {

	private String sviv_syav = "0";          
	private String sviv_syop = "0";        
	private String sviv_syli = "0";                      
	     
	private String sviv_kota  = "0"; //sonet        5  0       5        17        begge    kollital         
	 private String sviv_kot2  = "0";  //sonet        5  0       5        22        begge    kollital         
	  private String sviv_kot3  = "0";  //sonet        5  0       5        27        begge    kollital         
	 private String sviv_kot4  = "0";  //sonet        5  0       5        32        begge    kollital         
	 private String sviv_kot5  = "0";  //sonet        5  0       5        37        begge    kollital         
	 private String sviv_kosl  = "";  //tegn            4       4        42        begge    kollislag        
	 private String sviv_kos2  = "";  //tegn            4       4        46        begge    kollislag   
	 private String sviv_kos3  = "";  //tegn            4       4        50        begge    kollislag   
	                         
	private String sviv_kos4  = "";  //tegn            4       4        54        begge    kollislag   
	private String sviv_kos5  = "";  //tegn            4       4        58        begge    kollislag   
	private String sviv_godm  = "";  //tegn           42      42        62        begge    godsmærkning
	private String sviv_god2  = "";  //tegn           42      42       104        begge    godsmærkning
	private String sviv_god3  = "";  //tegn           42      42       146        begge    godsmærkning   
	private String sviv_god4  = "";  //tegn           42      42       188        begge    godsmærkning   
	private String sviv_god5  = "";  //tegn           42      42       230        begge    godsmærkning   
	private String sviv_vasl  = "";  //tegn           70      70       272        begge    varubeskrivning
	private String sviv_vas2  = "";  //tegn           70      70       342        begge    varubeskrivning
	private String sviv_vas3  = "";  //tegn           70      70       412        begge    varubeskrivning
	private String sviv_vas4  = "";  //tegn           70      70       482        begge    varubeskrivning   
	private String sviv_vano  = "0";  //sonet        5  0       5       552        begge    varupost nr.      
	private String sviv_vata  = "";  //tegn           10      10       557        begge    varukod taric nr  
	private String sviv_vati  = "";  //tegn            4       4       567        begge    varukod 33:3      
	private String sviv_vat4  = "";  //tegn            4       4       571        begge    varukod 33:4      
	private String sviv_vat5  = "";  //tegn            4       4       575        begge    varukod 33:5      
	private String sviv_ulkd  = "";  //tegn            2       2       579        begge    ursprungsland, kod
	private String sviv_brut  = "0";  //sonet       14  3      14       581        begge    bruttovikt     
	private String sviv_fokd  = "";  //tegn            3       3       595        begge    førmåner, kod  
	private String sviv_eup1  = "";  //tegn            4       4       598        begge    eu-procedurkod 
	private String sviv_eup2  = "";  //tegn            3       3       602        begge    r37-2          
	private String sviv_neto  = "0";  //sonet       14  3      14       605        begge    nettovikt      
	private String sviv_kvot  = "";  //tegn            6       6       619        begge    kvoter         
	private String sviv_kono  = "";  //tegn            3       3       625        begge    kontigentnummer
	private String sviv_ankv  = "0";  //sonet        9  0       9       628        begge    annan kvantitet
	private String sviv_suko  = "";  //tegn            5       5       637        begge    særsk.uppl.kod 
	private String sviv_suk6  = "";  //tegn            5       5       642        begge    særsk.uppl.kod 
	private String sviv_sukb  = "";  //tegn            5       5       647        begge    særsk.uppl.kod 
	private String sviv_sutx  = "";  //tegn           70      70       652        begge    særsk.uppl.text
	private String sviv_sut2  = "";  //tegn           70      70       722        begge    særsk.uppl.text
	private String sviv_sut3  = "";  //tegn           70      70       792        begge    særsk.uppl.text
	private String sviv_sut4  = "";  //tegn           70      70       862        begge    særsk.uppl.text
	private String sviv_sut5  = "";  //tegn           70      70       932        begge    særsk.uppl.text
	private String sviv_sut6  = "";  //tegn           70      70      1002        begge    særsk.uppl.text
	private String sviv_sut7  = "";  //tegn           70      70      1072        begge    særsk.uppl.text
	private String sviv_sut8  = "";  //tegn           70      70      1142        begge    særsk.uppl.text
	private String sviv_sut9  = "";  //tegn           70      70      1212        begge    særsk.uppl.text
	private String sviv_suta  = "";  //tegn           70      70      1282        begge    særsk.uppl.text
	private String sviv_sutb  = "";  //tegn           70      70      1352        begge    særsk.uppl.text
	private String sviv_sutc  = "";  //tegn           70      70      1422        begge    særsk.uppl.text
	private String sviv_sutd  = "";  //tegn           70      70      1492        begge    særsk.uppl.text
	private String sviv_sute  = "";  //tegn           70      70      1562        begge    særsk.uppl.text
	private String sviv_sutf  = "";  //tegn           70      70      1632        begge    særsk.uppl.text
	private String sviv_suok  = "0";  //sonet       11  0      11      1702        begge    særsk.uppl.ø.kost  
	private String sviv_sukr  = "0";  //sonet       11  0      11      1713        begge    særsk.uppl.kassa.r 
	private String sviv_suar  = "0";  //sonet       11  0      11      1724        begge    særsk.uppl.annan.r 
	private String sviv_atin  = "";  //tegn            3       3      1735        begge    åtgærdsindikator   
	private String sviv_stva  = "0";  //sonet       11  0      11      1738        begge    stat.værde         
	private String sviv_stva2 = "0";  //sonet       11  0      11      1749        begge    tullværde          
	private String sviv_fabl  = "0";  //sonet       11  3      11      1760        begge    fakturabelopp      
	private String sviv_bit1  = "";  //tegn            4       4      1771        begge    bil.handl, typ     
	private String sviv_bit2  = "";  //tegn            4       4      1775        begge    bil.handl, typ     
	 private String sviv_bit3  = "";  //tegn            4       4      1779        begge    bil.handl, typ
	 private String sviv_bit4  = "";  //tegn            4       4      1783        begge    bil.handl, typ
	 private String sviv_bit5  = "";  //tegn            4       4      1787        begge    bil.handl, typ
	 private String sviv_bit6  = "";  //tegn            4       4      1791        begge    bil.handl, typ
	 private String sviv_bit7  = "";  //tegn            4       4      1795        begge    bil.handl, typ
	 private String sviv_bit8  = "";  //tegn            4       4      1799        begge    bil.handl, typ
	private String sviv_bit9  = "";  //tegn            4       4      1803        begge    bil.handl, typ
	private String sviv_bii1  = "";  //tegn           35      35      1807        begge    bil.handl, id 
	private String sviv_bii2  = "";  //tegn           35      35      1842        begge    bil.handl, id 
	private String sviv_bii3  = "";  //tegn           35      35      1877        begge    bil.handl, id 
	private String sviv_bii4  = "";  //tegn           35      35      1912        begge    bil.handl, id 
	private String sviv_bii5  = "";  //tegn           35      35      1947        begge    bil.handl, id 
	private String sviv_bii6  = "";  //tegn           35      35      1982        begge    bil.handl, id   
	private String sviv_bii7  = "";  //tegn           35      35      2017        begge    bil.handl, id   
	private String sviv_bii8  = "";  //tegn           35      35      2052        begge    bil.handl, id   
	private String sviv_bii9  = "";  //tegn           35      35      2087        begge    bil.handl, id   
	private String sviv_co01  = "";  //tegn           17      17      2122        begge    containernummer 
	private String sviv_co02  = "";  //tegn           17      17      2139        begge    containernummer 
	private String sviv_co03  = "";  //tegn           17      17      2156        begge    containernummer 
	private String sviv_co04  = "";  //tegn           17      17      2173        begge    containernummer
	private String sviv_co05  = "";  //tegn           17      17      2190        begge    containernummer
	private String sviv_co06  = "";  //tegn           17      17      2207        begge    containernummer
	private String sviv_co07  = "";  //tegn           17      17      2224        begge    containernummer
	private String sviv_co08  = "";  //tegn           17      17      2241        begge    containernummer
	private String sviv_co09  = "";  //tegn           17      17      2258        begge    containernummer
	private String sviv_co10  = "";  //tegn           17      17      2275        begge    containernummer
	private String sviv_co11  = "";  //tegn           17      17      2292        begge    containernummer
	private String sviv_co12  = "";  //tegn           17      17      2309        begge    containernummer
	private String sviv_co13  = "";  //tegn           17      17      2326        begge    containernummer
	private String sviv_co14  = "";  //tegn           17      17      2343        begge    containernummer
	private String sviv_co15  = "";  //tegn           17      17      2360        begge    containernummer
	private String sviv_co16  = "";  //tegn           17      17      2377        begge    containernummer

	private String sviv_co17  = "";  //tegn           17      17      2394        begge    containernummer
	private String sviv_co18  = "";  //tegn           17      17      2411        begge    containernummer
	private String sviv_co19  = "";  //tegn           17      17      2428        begge    containernummer
	private String sviv_co20  = "";  //tegn           17      17      2445        begge    containernummer
	private String sviv_tik1  = "";  //tegn            1       1      2462        begge    tid.handl. kat 
	private String sviv_tik2  = "";  //tegn            1       1      2463        begge    tid.handl. kat 
	private String sviv_tik3  = "";  //tegn            1       1      2464        begge    tid.handl. kat
	private String sviv_tik4  = "";  //tegn            1       1      2465        begge    tid.handl. kat
	private String sviv_tik5  = "";  //tegn            1       1      2466        begge    tid.handl. kat
	private String sviv_tik6  = "";  //tegn            1       1      2467        begge    tid.handl. kat
	private String sviv_tik7  = "";  //tegn            1       1      2468        begge    tid.handl. kat
	private String sviv_tik8  = "";  //tegn            1       1      2469        begge    tid.handl. kat
	private String sviv_tik9  = "";  //tegn            1       1      2470        begge    tid.handl. kat
	private String sviv_tit1  = "";  //tegn            3       3      2471        begge    tid.handl. typ
	private String sviv_tit2  = "";  //tegn            3       3      2474        begge    tid.handl. typ
	private String sviv_tit3  = "";  //tegn            3       3      2477        begge    tid.handl. typ
	private String sviv_tit4  = "";  //tegn            3       3      2480        begge    tid.handl. typ
	private String sviv_tit5  = "";  //tegn            3       3      2483        begge    tid.handl. typ
	private String sviv_tit6  = "";  //tegn            3       3      2486        begge    tid.handl. typ 
	private String sviv_tit7  = "";  //tegn            3       3      2489        begge    tid.handl. typ 
	private String sviv_tit8  = "";  //tegn            3       3      2492        begge    tid.handl. typ 
	private String sviv_tit9  = "";  //tegn            3       3      2495        begge    tid.handl. typ 
	private String sviv_tix1  = "";  //tegn           35      35      2498        begge    tid.handl. txt 
	private String sviv_tix2  = "";  //tegn           35      35      2533        begge    tid.handl. txt 
	private String sviv_tix3  = "";  //tegn           35      35      2568        begge    tid.handl. txt 
	private String sviv_tix4  = "";  //tegn           35      35      2603        begge    tid.handl. txt
	private String sviv_tix5  = "";  //tegn           35      35      2638        begge    tid.handl. txt
	private String sviv_tix6  = "";  //tegn           35      35      2673        begge    tid.handl. txt
	private String sviv_tix7  = "";  //tegn           35      35      2708        begge    tid.handl. txt
	private String sviv_tix8  = "";  //tegn           35      35      2743        begge    tid.handl. txt
	private String sviv_tix9  = "";  //tegn           35      35      2778        begge    tid.handl. txt
	private String sviv_lagi  = "";  //tegn           14      14      2813        begge    49.lager id         
	private String sviv_lagt  = "";  //tegn            1       1      2827        begge    49.lager typ        
	private String sviv_lagl  = "";  //tegn            2       2      2828        begge    49.lager lk         
	private String sviv_call  = "";  //tegn            2       2      2830        begge    call me             
	private String sviv_err   = "";  //tegn            1       1      2832        begge    error flag          
	private String sviv_folk  = "";  //tegn            2       2      2833        begge    förmånsursprungsland

	
	
	
}
