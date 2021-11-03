package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.model.dao.entities.EdimDao;
import no.systema.jservices.model.dao.entities.SvivRflnDao;
import no.systema.jservices.model.dao.entities.Sviv_aggDao;
import no.systema.jservices.model.dao.entities.Sviva_aggDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * Sep 2021
 * 
 */
public class Sviv_aggDaoServicesImpl implements Sviv_aggDaoServices {
	private static Logger logger = Logger.getLogger(Sviv_aggDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	@Override
	public List<EdimDao> getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		/*
		List<EdimDao> list = new ArrayList<EdimDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from edim ");
			sql.append(" where msn = ? ");
			list = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new EdimMapper());
			
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			list = null;
		}
		return list;
		*/
		return null;
	}
	
	
	
	
	/**
	 * UPDATE
	 */
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		/*
		try{
			
			EdimDao dao = (EdimDao)daoObj;
			String today = new DateTimeManager().getCurrentDate_ISO("yyyMMdd");
			String now = new DateTimeManager().getCurrentDate_ISO("HHmmss");
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE edim SET msn = ?, mst = ?, mdt = ?, mtm = ?   ");
			sql.append(" WHERE  mavd = ? AND mtdn = ? AND msr = ? AND mdt = ? AND mtm = ? ");
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getMsn(), dao.getMst(), today, now,  
					//WHERE
					dao.getMavd(), dao.getMtdn(), dao.getMsr(), dao.getMdt(), dao.getMtm() } );
			
			//adjust edic.cmn counter only when it has been incremented
			if(retval>=0){
				if(StringUtils.isNotEmpty(dao.getMsn())) {
					retval = this.updateCounterEdicCsn(dao.getMsn(), errorStackTrace);
				}
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
	}
	
	
	/** TEST for method test = OK
	 * 
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		/* reserv UC
		try {
			
			final Sviv_aggDao dao = (Sviv_aggDao) daoObj;
			
			final StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO SVIV_AGG(sviv_syav, sviv_syop, sviv_syli, sviv_vata )");
			sql.append(" VALUES (? ,?, ?, ?)");
		
		
			//logger.warn(dao.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getSviv_syav(), dao.getSviv_syop(), dao.getSviv_syli(), dao.getSviv_vata()
					});
			
				
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.info(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
			}				
			*/
		return retval;
	}
	
	/**
	 * INSERT 
	 * TEST = OK with only 4 columns.
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insertSviv_agg(List<Sviv_aggDao> items, List<Sviva_aggDao> avgifter, StringBuffer errorStackTrace) {
		int retval = 0;
		
		
		StringBuilder sql = new StringBuilder();
		try {
			//(1)delete all previous data related to syav and syop
			Sviv_aggDao deleteDao = items.stream().findFirst().get();
			int retvalDelete = this.delete(deleteDao, errorStackTrace); //includes SVIV_AGG and SVIVA_AGG
			
			
			//(2)insert 
			if(retvalDelete>=0) {
				sql.append(" INSERT INTO SVIV_AGG( ");
				sql.append(" sviv_syav, sviv_syop, sviv_syli, sviv_kota, sviv_kot2, sviv_kot3, sviv_kot4, sviv_kot5, sviv_kosl, sviv_kos2  "); 
				sql.append(" ,sviv_kos3, sviv_kos4, sviv_kos5, sviv_godm, sviv_god2, sviv_god3, sviv_god4, sviv_god5, sviv_vasl, sviv_vas2  ");
				sql.append(" ,sviv_vas3, sviv_vas4, sviv_vano, sviv_vata, sviv_vati, sviv_vat4, sviv_vat5, sviv_ulkd, sviv_brut, sviv_fokd  ");
				sql.append(" ,sviv_eup1, sviv_eup2, sviv_neto, sviv_kvot, sviv_kono, sviv_ankv, sviv_suko, sviv_suk6, sviv_sukb, sviv_sutx  ");
				sql.append(" ,sviv_sut2, sviv_sut3, sviv_sut4, sviv_sut5, sviv_sut6, sviv_sut7, sviv_sut8, sviv_sut9, sviv_suta, sviv_sutb  ");
				
				sql.append(" ,sviv_sutc, sviv_sutd, sviv_sute, sviv_sutf, sviv_suok, sviv_sukr, sviv_suar, sviv_atin, sviv_stva, sviv_stva2  ");
				sql.append(" ,sviv_fabl, sviv_bit1, sviv_bit2, sviv_bit3, sviv_bit4, sviv_bit5, sviv_bit6, sviv_bit7, sviv_bit8, sviv_bit9  ");
				sql.append(" ,sviv_bii1, sviv_bii2, sviv_bii3, sviv_bii4, sviv_bii5, sviv_bii6, sviv_bii7, sviv_bii8, sviv_bii9, sviv_co01  ");
				sql.append(" ,sviv_co02, sviv_co03, sviv_co04, sviv_co05, sviv_co06, sviv_co07, sviv_co08, sviv_co09, sviv_co10, sviv_co11  ");
				sql.append(" ,sviv_co12, sviv_co13, sviv_co14, sviv_co15, sviv_co16, sviv_co17, sviv_co18, sviv_co19, sviv_co20, sviv_tik1  ");
				
				sql.append(" ,sviv_tik2, sviv_tik3, sviv_tik4, sviv_tik5, sviv_tik6, sviv_tik7, sviv_tik8, sviv_tik9, sviv_tit1, sviv_tit2  ");
				sql.append(" ,sviv_tit3, sviv_tit4, sviv_tit5, sviv_tit6, sviv_tit7, sviv_tit8, sviv_tit9, sviv_tix1, sviv_tix2, sviv_tix3  ");
				sql.append(" ,sviv_tix4, sviv_tix5, sviv_tix6, sviv_tix7, sviv_tix8, sviv_tix9, sviv_lagi, sviv_lagt, sviv_lagl, sviv_call  ");
				sql.append(" ,sviv_err, sviv_folk " );
				
				sql.append(" )");
				
				sql.append(" VALUES (");
				sql.append(" ? ,?, ?, ? ,?, ?, ? ,?, ?, ?  "); 
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				
				
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?, ?, ? ,?, ?, ? ,?, ?, ?  ");
				sql.append(" ,? ,?  ");
				
				
				sql.append(" )");
				
				this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
					
					@Override
				      public void setValues(PreparedStatement ps, int i) throws SQLException {
						Sviv_aggDao item = items.get(i);
				        ps.setString(1, item.getSviv_syav());
				        ps.setString(2, item.getSviv_syop());
				        ps.setString(3, item.getSviv_syli());
				        ps.setBigDecimal(4, new BigDecimal(adjustSonet(item.getSviv_kota()))); //sonet        5  0       5        17        begge    kollital         
						ps.setBigDecimal(5, new BigDecimal(adjustSonet(item.getSviv_kot2())));  //sonet        5  0       5        22        begge    kollital         
						ps.setBigDecimal(6, new BigDecimal(adjustSonet(item.getSviv_kot3())));  //sonet        5  0       5        27        begge    kollital         
						ps.setBigDecimal(7, new BigDecimal(adjustSonet(item.getSviv_kot4())));  //sonet        5  0       5        32        begge    kollital         
						ps.setBigDecimal(8, new BigDecimal(adjustSonet(item.getSviv_kot5())));  //sonet        5  0       5        37        begge    kollital         
						ps.setString(9, adjustTegn(item.getSviv_kosl()));  //tegn            4       4        42        begge    kollislag        
						ps.setString(10, adjustTegn(item.getSviv_kos2 ()));  //tegn            4       4        46        begge    kollislag
						
						ps.setString(11, adjustTegn(item.getSviv_kos3 ()));  //tegn            4       4        50        begge    kollislag   
						ps.setString(12, adjustTegn(item.getSviv_kos4 ()));  //tegn            4       4        54        begge    kollislag   
						ps.setString(13, adjustTegn(item.getSviv_kos5 ()));  //tegn            4       4        58        begge    kollislag   
						ps.setString(14, adjustTegn(item.getSviv_godm ()));  //tegn           42      42        62        begge    godsmærkning
						ps.setString(15, adjustTegn(item.getSviv_god2 ()));  //tegn           42      42       104        begge    godsmærkning
						ps.setString(16, adjustTegn(item.getSviv_god3 ()));  //tegn           42      42       146        begge    godsmærkning   
						ps.setString(17, adjustTegn(item.getSviv_god4 ()));  //tegn           42      42       188        begge    godsmærkning   
						ps.setString(18, adjustTegn(item.getSviv_god5 ()));  //tegn           42      42       230        begge    godsmærkning   
						ps.setString(19, adjustTegn(item.getSviv_vasl ()));  //tegn           70      70       272        begge    varubeskrivning
						ps.setString(20, adjustTegn(item.getSviv_vas2 ()));  //tegn           70      70       342        begge    varubeskrivning
	
						
						ps.setString(21, adjustTegn(item.getSviv_vas3 ()));  //tegn           70      70       412        begge    varubeskrivning
						ps.setString(22, adjustTegn(item.getSviv_vas4 ()));  //tegn           70      70       482        begge    varubeskrivning   
						ps.setBigDecimal(23, new BigDecimal(adjustSonet(item.getSviv_vano ())));  //sonet        5  0       5       552        begge    varupost nr.      
						ps.setString(24, adjustTegn(item.getSviv_vata ()));  //tegn           10      10       557        begge    varukod taric nr  
						ps.setString(25, adjustTegn(item.getSviv_vati ()));  //tegn            4       4       567        begge    varukod 33:3      
						ps.setString(26, adjustTegn(item.getSviv_vat4 ()));  //tegn            4       4       571        begge    varukod 33:4      
						ps.setString(27, adjustTegn(item.getSviv_vat5 ()));  //tegn            4       4       575        begge    varukod 33:5      
						ps.setString(28, adjustTegn(item.getSviv_ulkd ()));  //tegn            2       2       579        begge    ursprungsland, kod
						ps.setBigDecimal(29, new BigDecimal(adjustSonet(item.getSviv_brut ())));  //sonet       14  3      14       581        begge    bruttovikt     
						ps.setString(30, adjustTegn(item.getSviv_fokd ()));  //tegn            3       3       595        begge    førmåner, kod
						
						
						ps.setString(31, adjustTegn(item.getSviv_eup1 ()));  //tegn            4       4       598        begge    eu-procedurkod 
						ps.setString(32, adjustTegn(item.getSviv_eup2 ()));  //tegn            3       3       602        begge    r37-2          
						ps.setBigDecimal(33, new BigDecimal(adjustSonet(item.getSviv_neto ())));  //sonet       14  3      14       605        begge    nettovikt      
						ps.setString(34, adjustTegn(item.getSviv_kvot ()));  //tegn            6       6       619        begge    kvoter         
						ps.setString(35, adjustTegn(item.getSviv_kono ()));  //tegn            3       3       625        begge    kontigentnummer
						ps.setBigDecimal(36, new BigDecimal(adjustSonet(item.getSviv_ankv ())));  //sonet        9  0       9       628        begge    annan kvantitet
						ps.setString(37, adjustTegn(item.getSviv_suko ()));  //tegn            5       5       637        begge    særsk.uppl.kod 
						ps.setString(38, adjustTegn(item.getSviv_suk6 ()));  //tegn            5       5       642        begge    særsk.uppl.kod 
						ps.setString(39, adjustTegn(item.getSviv_sukb ()));  //tegn            5       5       647        begge    særsk.uppl.kod 
						ps.setString(40, adjustTegn(item.getSviv_sutx ()));  //tegn           70      70       652        begge    særsk.uppl.text
						
						
						ps.setString(41, adjustTegn(item.getSviv_sut2 ()));  //tegn           70      70       722        begge    særsk.uppl.text
						ps.setString(42, adjustTegn(item.getSviv_sut3 ()));  //tegn           70      70       792        begge    særsk.uppl.text
						ps.setString(43, adjustTegn(item.getSviv_sut4 ()));  //tegn           70      70       862        begge    særsk.uppl.text
						ps.setString(44, adjustTegn(item.getSviv_sut5 ()));  //tegn           70      70       932        begge    særsk.uppl.text
						ps.setString(45, adjustTegn(item.getSviv_sut6 ()));  //tegn           70      70      1002        begge    særsk.uppl.text
						ps.setString(46, adjustTegn(item.getSviv_sut7 ()));  //tegn           70      70      1072        begge    særsk.uppl.text
						ps.setString(47, adjustTegn(item.getSviv_sut8 ()));  //tegn           70      70      1142        begge    særsk.uppl.text
						ps.setString(48, adjustTegn(item.getSviv_sut9 ()));  //tegn           70      70      1212        begge    særsk.uppl.text
						ps.setString(49, adjustTegn(item.getSviv_suta ()));  //tegn           70      70      1282        begge    særsk.uppl.text
						ps.setString(50, adjustTegn(item.getSviv_sutb ()));  //tegn           70      70      1352        begge    særsk.uppl.text
						
						ps.setString(51, adjustTegn(item.getSviv_sutc ()));  //tegn           70      70      1422        begge    særsk.uppl.text
						ps.setString(52, adjustTegn(item.getSviv_sutd ()));  //tegn           70      70      1492        begge    særsk.uppl.text
						ps.setString(53, adjustTegn(item.getSviv_sute ()));  //tegn           70      70      1562        begge    særsk.uppl.text
						ps.setString(54, adjustTegn(item.getSviv_sutf ()));  //tegn           70      70      1632        begge    særsk.uppl.text
						ps.setBigDecimal(55, new BigDecimal(adjustSonet(item.getSviv_suok ())));  //sonet       11  0      11      1702        begge    særsk.uppl.ø.kost  
						ps.setBigDecimal(56, new BigDecimal(adjustSonet(item.getSviv_sukr ())));  //sonet       11  0      11      1713        begge    særsk.uppl.kassa.r 
						ps.setBigDecimal(57, new BigDecimal(adjustSonet(item.getSviv_suar ())));  //sonet       11  0      11      1724        begge    særsk.uppl.annan.r 
						ps.setString(58, adjustTegn(item.getSviv_atin ()));  //tegn            3       3      1735        begge    åtgærdsindikator   
						ps.setBigDecimal(59, new BigDecimal(adjustSonet(item.getSviv_stva ())));  //sonet       11  0      11      1738        begge    stat.værde  
						ps.setBigDecimal(60, new BigDecimal(adjustSonet(item.getSviv_stva2())));  //sonet       11  0      11      1749        begge    tullværde          
						
						ps.setBigDecimal(61, new BigDecimal(adjustSonet(item.getSviv_fabl ())));  //sonet       11  3      11      1760        begge    fakturabelopp      
						ps.setString(62, adjustTegn(item.getSviv_bit1 ()));  //tegn            4       4      1771        begge    bil.handl, typ     
						ps.setString(63, adjustTegn(item.getSviv_bit2 ()));  //tegn            4       4      1775        begge    bil.handl, typ     
						ps.setString(64, adjustTegn(item.getSviv_bit3 ()));  //tegn            4       4      1779        begge    bil.handl, typ
						ps.setString(65, adjustTegn(item.getSviv_bit4 ()));  //tegn            4       4      1783        begge    bil.handl, typ
						ps.setString(66, adjustTegn(item.getSviv_bit5 ()));  //tegn            4       4      1787        begge    bil.handl, typ
						ps.setString(67, adjustTegn(item.getSviv_bit6 ()));  //tegn            4       4      1791        begge    bil.handl, typ
						ps.setString(68, adjustTegn(item.getSviv_bit7 ()));  //tegn            4       4      1795        begge    bil.handl, typ
						ps.setString(69, adjustTegn(item.getSviv_bit8 ()));  //tegn            4       4      1799        begge    bil.handl, typ
						ps.setString(70, adjustTegn(item.getSviv_bit9 ()));  //tegn            4       4      1803        begge    bil.handl, typ
						
						ps.setString(71, adjustTegn(item.getSviv_bii1 ()));  //tegn           35      35      1807        begge    bil.handl, id 
						ps.setString(72, adjustTegn(item.getSviv_bii2 ()));  //tegn           35      35      1842        begge    bil.handl, id 
						ps.setString(73, adjustTegn(item.getSviv_bii3 ()));  //tegn           35      35      1877        begge    bil.handl, id 
						ps.setString(74, adjustTegn(item.getSviv_bii4 ()));  //tegn           35      35      1912        begge    bil.handl, id 
						ps.setString(75, adjustTegn(item.getSviv_bii5 ()));  //tegn           35      35      1947        begge    bil.handl, id 
						ps.setString(76, adjustTegn(item.getSviv_bii6 ()));  //tegn           35      35      1982        begge    bil.handl, id   
						ps.setString(77, adjustTegn(item.getSviv_bii7 ()));  //tegn           35      35      2017        begge    bil.handl, id   
						ps.setString(78, adjustTegn(item.getSviv_bii8 ()));  //tegn           35      35      2052        begge    bil.handl, id   
						ps.setString(79, adjustTegn(item.getSviv_bii9 ()));  //tegn           35      35      2087        begge    bil.handl, id   
						ps.setString(80, adjustTegn(item.getSviv_co01 ()));  //tegn           17      17      2122        begge    containernummer
						
						
						ps.setString(81, adjustTegn(item.getSviv_co02 ()));  //tegn           17      17      2139        begge    containernummer 
						ps.setString(82, adjustTegn(item.getSviv_co03 ()));  //tegn           17      17      2156        begge    containernummer 
						ps.setString(83, adjustTegn(item.getSviv_co04 ()));  //tegn           17      17      2173        begge    containernummer
						ps.setString(84, adjustTegn(item.getSviv_co05 ()));  //tegn           17      17      2190        begge    containernummer
						ps.setString(85, adjustTegn(item.getSviv_co06 ()));  //tegn           17      17      2207        begge    containernummer
						ps.setString(86, adjustTegn(item.getSviv_co07 ()));  //tegn           17      17      2224        begge    containernummer
						ps.setString(87, adjustTegn(item.getSviv_co08 ()));  //tegn           17      17      2241        begge    containernummer
						ps.setString(88, adjustTegn(item.getSviv_co09 ()));  //tegn           17      17      2258        begge    containernummer
						ps.setString(89, adjustTegn(item.getSviv_co10 ()));  //tegn           17      17      2275        begge    containernummer
						ps.setString(90, adjustTegn(item.getSviv_co11 ()));  //tegn           17      17      2292        begge    containernummer
						
						ps.setString(91, adjustTegn(item.getSviv_co12 ()));  //tegn           17      17      2309        begge    containernummer
						ps.setString(92, adjustTegn(item.getSviv_co13 ()));  //tegn           17      17      2326        begge    containernummer
						ps.setString(93, adjustTegn(item.getSviv_co14 ()));  //tegn           17      17      2343        begge    containernummer
						ps.setString(94, adjustTegn(item.getSviv_co15 ()));  //tegn           17      17      2360        begge    containernummer
						ps.setString(95, adjustTegn(item.getSviv_co16 ()));  //tegn           17      17      2377        begge    containernummer
						ps.setString(96, adjustTegn(item.getSviv_co17 ()));  //tegn           17      17      2394        begge    containernummer
						ps.setString(97, adjustTegn(item.getSviv_co18 ()));  //tegn           17      17      2411        begge    containernummer
						ps.setString(98, adjustTegn(item.getSviv_co19 ()));  //tegn           17      17      2428        begge    containernummer
						ps.setString(99, adjustTegn(item.getSviv_co20 ()));  //tegn           17      17      2445        begge    containernummer
						ps.setString(100, adjustTegn(item.getSviv_tik1 ()));  //tegn            1       1      2462        begge    tid.handl. kat
						
						ps.setString(101, adjustTegn(item.getSviv_tik2 ()));  //tegn            1       1      2463        begge    tid.handl. kat 
						ps.setString(102, adjustTegn(item.getSviv_tik3 ()));  //tegn            1       1      2464        begge    tid.handl. kat
						ps.setString(103, adjustTegn(item.getSviv_tik4 ()));  //tegn            1       1      2465        begge    tid.handl. kat
						ps.setString(104, adjustTegn(item.getSviv_tik5 ()));  //tegn            1       1      2466        begge    tid.handl. kat
						ps.setString(105, adjustTegn(item.getSviv_tik6 ()));  //tegn            1       1      2467        begge    tid.handl. kat
						ps.setString(106, adjustTegn(item.getSviv_tik7 ()));  //tegn            1       1      2468        begge    tid.handl. kat
						ps.setString(107, adjustTegn(item.getSviv_tik8 ()));  //tegn            1       1      2469        begge    tid.handl. kat
						ps.setString(108, adjustTegn(item.getSviv_tik9 ()));  //tegn            1       1      2470        begge    tid.handl. kat
						ps.setString(109, adjustTegn(item.getSviv_tit1 ()));  //tegn            3       3      2471        begge    tid.handl. typ
						ps.setString(110, adjustTegn(item.getSviv_tit2 ()));  //tegn            3       3      2474        begge    tid.handl. typ
						
						ps.setString(111, adjustTegn(item.getSviv_tit3 ()));  //tegn            3       3      2477        begge    tid.handl. typ
						ps.setString(112, adjustTegn(item.getSviv_tit4 ()));  //tegn            3       3      2480        begge    tid.handl. typ
						ps.setString(113, adjustTegn(item.getSviv_tit5 ()));  //tegn            3       3      2483        begge    tid.handl. typ
						ps.setString(114, adjustTegn(item.getSviv_tit6 ()));  //tegn            3       3      2486        begge    tid.handl. typ 
						ps.setString(115, adjustTegn(item.getSviv_tit7 ()));  //tegn            3       3      2489        begge    tid.handl. typ 
						ps.setString(116, adjustTegn(item.getSviv_tit8 ()));  //tegn            3       3      2492        begge    tid.handl. typ 
						ps.setString(117, adjustTegn(item.getSviv_tit9 ()));  //tegn            3       3      2495        begge    tid.handl. typ 
						ps.setString(118, adjustTegn(item.getSviv_tix1 ()));  //tegn           35      35      2498        begge    tid.handl. txt 
						ps.setString(119, adjustTegn(item.getSviv_tix2 ()));  //tegn           35      35      2533        begge    tid.handl. txt 
						ps.setString(120, adjustTegn(item.getSviv_tix3 ()));  //tegn           35      35      2568        begge    tid.handl. txt
						
						ps.setString(121, adjustTegn(item.getSviv_tix4 ()));  //tegn           35      35      2603        begge    tid.handl. txt
						ps.setString(122, adjustTegn(item.getSviv_tix5 ()));  //tegn           35      35      2638        begge    tid.handl. txt
						ps.setString(123, adjustTegn(item.getSviv_tix6 ()));  //tegn           35      35      2673        begge    tid.handl. txt
						ps.setString(124, adjustTegn(item.getSviv_tix7 ()));  //tegn           35      35      2708        begge    tid.handl. txt
						ps.setString(125, adjustTegn(item.getSviv_tix8 ()));  //tegn           35      35      2743        begge    tid.handl. txt
						ps.setString(126, adjustTegn(item.getSviv_tix9 ()));  //tegn           35      35      2778        begge    tid.handl. txt
						ps.setString(127, adjustTegn(item.getSviv_lagi ()));  //tegn           14      14      2813        begge    49.lager id         
						ps.setString(128, adjustTegn(item.getSviv_lagt ()));  //tegn            1       1      2827        begge    49.lager typ        
						ps.setString(129, adjustTegn(item.getSviv_lagl ()));  //tegn            2       2      2828        begge    49.lager lk         
						ps.setString(130, adjustTegn(item.getSviv_call ()));  //tegn            2       2      2830        begge    call me
						           
						ps.setString(131, adjustTegn(item.getSviv_err  ()));  //tegn            1       1      2832        begge    error flag          
						ps.setString(132, adjustTegn(item.getSviv_folk ()));  //tegn            2       2      2833        begge    förmånsursprungsland
				         
				      }
				      @Override
				      public int getBatchSize() {
				        return items.size();
				      }
				    });
				}
				//(3)insert into sviva_agg
				if(retvalDelete>=0 && retval>=0) {
					if(avgifter!=null && avgifter.size()>0) {
						retval = this.insertSviva_agg(avgifter, errorStackTrace);
					}
				}
			
			
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.warn(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
				//clean up
				Sviv_aggDao deleteDao = items.stream().findFirst().get();
				this.delete(deleteDao, errorStackTrace); //includes SVIV_AGG and SVIVA_AGG
				
			}				
			return retval;
	}
	
	
	/**
	 * We use a manual CASCADE since the tables lack foreign keys to do that automatically via the database...
	 */
	@Override
	public int delete(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try{
			Sviv_aggDao dao = (Sviv_aggDao)daoObj;
			//(1)delete child table first (SVIVA_AGG)
			int tmp = this.deleteSviva_agg(dao.getSviv_syav(), dao.getSviv_syop(), errorStackTrace);
			
			if(tmp>=0) {
				StringBuffer sql = new StringBuffer();
				//(2)delete main table);
				sql.append(" DELETE from sviv_agg ");
				sql.append(" WHERE sviv_syav = ? ");
				sql.append(" AND sviv_syop = ? ");
				
				//params
				retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSviv_syav(), dao.getSviv_syop() } );
			}
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.warn(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;

	}
	
	

	/**
	 * Child table
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	private int deleteSviva_agg(String avd, String opd, StringBuffer errorStackTrace) {
		int retval = 0;
		try{
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from sviva_agg ");
			sql.append(" WHERE sviva_sav = ? ");
			sql.append(" AND sviva_sop = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { avd, opd } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.warn(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;

	}                                  

	
	/**
	 * 
	 * @param itemListSviv
	 * @param errorStackTrace
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateVanoSviv(List<SvivRflnDao> itemListSviv, StringBuffer errorStackTrace) {
		int retval = 0;
		
		logger.info(itemListSviv);
		StringBuilder sql = new StringBuilder();
		try {
			
			//(1) 
			sql.append(" update sviv set sviv_vano = ? ");
			sql.append(" where sviv_syav = ? and sviv_syop = ? and sviv_syli = ? ");
			
			
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				
				@Override
			      public void setValues(PreparedStatement ps, int i) throws SQLException {
					SvivRflnDao item = itemListSviv.get(i);
					ps.setString(1,adjustTegn(item.getSviv_vano()));
			        ps.setString(2,adjustTegn(item.getSviv_syav()));
			        ps.setString(3,adjustTegn(item.getSviv_syop()));
			        ps.setString(4,adjustTegn(item.getSviv_syli()));
			        
					
			      }
			      @Override
			      public int getBatchSize() {
			        return itemListSviv.size();
			      }
			    });
			
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.warn(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
			}				
			return retval;
	}
	
	/**
	 * 
	 */
	public int updateRflnSviv(List<SvivRflnDao> itemListRfln, StringBuffer errorStackTrace) {
		int retval = 0;
		
		logger.info(itemListRfln);
		StringBuilder sql = new StringBuilder();
		try {
			
			//(1) 
			sql.append(" update sviv set sviv_rfln = ? ");
			sql.append(" where sviv_syav = ? and sviv_syop = ? and sviv_syli = ? ");
			
			
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				
				@Override
			      public void setValues(PreparedStatement ps, int i) throws SQLException {
					SvivRflnDao item = itemListRfln.get(i);
					ps.setString(1,adjustTegn(item.getSviv_rfln()));
			        ps.setString(2,adjustTegn(item.getSviv_syav()));
			        ps.setString(3,adjustTegn(item.getSviv_syop()));
			        ps.setString(4,adjustTegn(item.getSviv_syli()));
			        
					
			      }
			      @Override
			      public int getBatchSize() {
			        return itemListRfln.size();
			      }
			    });
			
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.warn(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
			}				
			return retval;
	}
	
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int blankRfln(String avd, String opd, StringBuffer errorStackTrace) {
		int retval = 0;
		
		try{
			
			StringBuilder sql = new StringBuilder();
			sql.append(" update sviv set sviv_rfln = ? ");
			sql.append(" where sviv_syav = ? and sviv_syop = ? ");
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { "0", avd, opd } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.warn(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insertSviva_agg(List<Sviva_aggDao> avgiftList, StringBuffer errorStackTrace) {
		int retval = 0;
		
		logger.info(avgiftList);
		StringBuilder sql = new StringBuilder();
		try {
			
			//(1) 
			sql.append(" INSERT INTO SVIVA_AGG( ");
			sql.append(" sviva_sav, sviva_sop, sviva_sli, sviva_abk, sviva_abg, sviva_abs, sviva_abx, sviva_abb  "); 
			sql.append(" )");
			
			sql.append(" VALUES (");
			sql.append(" ? ,?, ?, ? ,?, ?, ? ,?  "); 
			
			sql.append(" )");
			
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				
				@Override
			      public void setValues(PreparedStatement ps, int i) throws SQLException {
					Sviva_aggDao item = avgiftList.get(i);
					//keys
					ps.setString(1,adjustTegn(item.getSviva_sav())); // sonet        4  0       4         1        Begge    SYSPED Avdeling g  
			        ps.setString(2,adjustTegn(item.getSviva_sop())); //sonet        7  0       7         5        Begge    SYSPED Oppdragsn  
			        ps.setString(3,adjustTegn(item.getSviva_sli())); //sonet        5  0       5        12        Begge    SYSPED Linjenr
			        //rest
					ps.setString(4,adjustTegn(item.getSviva_abk())); //tegn            3       3        17        Begge    Avgber.avgkod
			        ps.setBigDecimal(5,new BigDecimal(adjustSonet(item.getSviva_abg()))); // sonet       11  3      11        20        Begge    Avgber.besk.gr  
			        ps.setBigDecimal(6,new BigDecimal(adjustSonet(item.getSviva_abs()))); // sonet        9  4       9        31        Begge    Avgber.avg.sats
			        ps.setString(7,adjustTegn(item.getSviva_abx())); 					  // tegn            4       4        40        Begge    Avgber.x.menhet
			        ps.setBigDecimal(8,new BigDecimal(adjustSonet(item.getSviva_abb()))); // sonet        9  0       9        44        Begge    Belopp
			        
			      }
			      @Override
			      public int getBatchSize() {
			        return avgiftList.size();
			      }
			    });
			
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.warn(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
				//celan up
				Sviva_aggDao avgDao = avgiftList.stream().findFirst().get();
				int tmp = this.deleteSviva_agg(avgDao.getSviva_sav(), avgDao.getSviva_sop(), errorStackTrace);
			}				
			return retval;
	}
	
	/**
	 * Cleans up any reference on rfln
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	/*
	public int blankRflnSviv(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try{
			Sviv_aggDao dao = (Sviv_aggDao)daoObj;
			
			StringBuffer sql = new StringBuffer();
			//(2)delete main table);
			sql.append(" UPDATE sviv SET sviv_rfln = ? ");
			sql.append(" WHERE sviv_syav = ? ");
			sql.append(" AND sviv_syop = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { "0", dao.getSviv_syav(), dao.getSviv_syop() } );
		
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}
	*/
	
	private String adjustSonet(String value) {
		String retval = value;
		if(StringUtils.isEmpty(value)) {
			retval = "0";
		}else {
			retval = value.replace(",", ".");
		}
		return retval;
	}
	private String adjustTegn(String value) {
		return StringUtils.defaultIfEmpty(value,"");
		
	}
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

	


}
