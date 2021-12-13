package no.systema.jservices.bcore.z.maintenance.model.dao.services.sad;
import java.io.Writer;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.sad.TrustdMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.TrustdDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Sep 30, 2016
 * 
 * 
 */
public class TrustdDaoServicesImpl implements TrustdDaoServices {
	private static Logger logger = LogManager.getLogger(TrustdDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<TrustdDao> retval = new ArrayList<TrustdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new TrustdMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	/**
	 * 
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		List<TrustdDao> retval = new ArrayList<TrustdDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" and thavd = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new TrustdMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}
		return retval;
	}
	

	@Override
	public List getNctsExportList(StringBuffer errorStackTrace) {
		List<TrustdDao> retval = new ArrayList<TrustdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" and thdk NOT in ('ENTRY','EXIT','SS')  ");

			retval = this.jdbcTemplate.query( sql.toString(), new TrustdMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
		
	}
	@Override
	public List getNctsForhandsvarslingList(StringBuffer errorStackTrace) {
		List<TrustdDao> retval = new ArrayList<TrustdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" and thdk in ('ENTRY','EXIT','SS')  ");

			retval = this.jdbcTemplate.query( sql.toString(), new TrustdMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}                                    
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			
			TrustdDao dao = (TrustdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO trustd ( thavd, thnttd, thntll, thfmll, thenkl, thtdn, s0004, s0010, s0026, s0035, thekst, ");
			sql.append(" thkns, thnas, thtins, thads1, thsks, thpss, thpns, thlks, ");
			sql.append(" thknk, thnak, thtink, thadk1, thskk, thpsk, thpnk, thlkk, ");
			sql.append(" thtsd1, thtsd2, thtsd3, thtsd4, thtsd5, thtsd6, thtsd7, thtsd8, thtsb, ");
			sql.append(" thgkd, thgft1, thgadk, thgbgi, thgbgu, thgft2,  thgpr, thgbl, thgvk, ");
			sql.append(" thalk, thblk, thtaid, thtalk, thtask, thtgid, thtglk, thtgsk, ");
			sql.append(" thtrm, thkdc, thlsd, thcats, thskfd, thdst, thdsk, thdkr, thddt, ");
			
			sql.append(" thtina, thnaa, thada1, thska, thpsa, thpna, thlka, ");
			sql.append(" thdfkd, thdant, thdfsk, thdk ");
			sql.append(" )" );
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?  ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getThavd(), 
				dao.getThnttd(), dao.getThntll(), dao.getThfmll(), dao.getThenkl(), dao.getThtdn(), dao.getS0004(), dao.getS0010(), dao.getS0026(), dao.getS0035(), dao.getThekst(),
				dao.getThkns(), dao.getThnas(), dao.getThtins(), dao.getThads1(), dao.getThsks(), dao.getThpss(), dao.getThpns(), dao.getThlks(),
				dao.getThknk(), dao.getThnak(), dao.getThtink(), dao.getThadk1(), dao.getThskk(), dao.getThpsk(), dao.getThpnk(), dao.getThlkk(), 
				dao.getThtsd1(),dao.getThtsd2(),dao.getThtsd3(),dao.getThtsd4(),dao.getThtsd5(),dao.getThtsd6(),dao.getThtsd7(),dao.getThtsd8(), dao.getThtsb(),
				dao.getThgkd(), dao.getThgft1(), dao.getThgadk(), dao.getThgbgi(), dao.getThgbgu(), dao.getThgft2(), dao.getThgpr(), dao.getThgbl(), dao.getThgvk(), 
				dao.getThalk(), dao.getThblk(), dao.getThtaid(), dao.getThtalk(), dao.getThtask(), dao.getThtgid(), dao.getThtglk(), dao.getThtgsk(),
				dao.getThtrm(), dao.getThkdc(), dao.getThlsd(), dao.getThcats(), dao.getThskfd(), dao.getThdst(), dao.getThdsk(), dao.getThdkr(), dao.getThddt(),
				
				dao.getThtina(), dao.getThnaa(), dao.getThada1(), dao.getThska(), dao.getThpsa(), dao.getThpna(), dao.getThlka(),
				dao.getThdfkd(), dao.getThdant(), dao.getThdfsk(), dao.getThdk()
			    } );

		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		} 

		return retval;
	}
	
	
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			
			TrustdDao dao = (TrustdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			
			sql.append(" UPDATE trustd SET thnttd = ?, thntll = ?, thfmll = ?, thenkl = ?, thtdn = ?, s0004 = ?, s0010 = ?, s0026 = ?, s0035 = ?, thekst = ?, ");
			sql.append(" thkns = ?, thnas = ?, thtins = ?, thads1 = ?, thsks = ?, thpss = ?, thpns = ?, thlks = ?, ");
			sql.append(" thknk = ?, thnak = ?, thtink = ?, thadk1 = ?, thskk = ?, thpsk = ?, thpnk = ?, thlkk = ?, ");
			sql.append(" thtsd1 = ?, thtsd2 = ?, thtsd3 = ?, thtsd4 = ?, thtsd5 = ?, thtsd6 = ?, thtsd7 = ?, thtsd8 = ?, thtsb = ?, ");
			sql.append(" thgkd = ?, thgft1 = ?, thgadk = ?, thgbgi = ?, thgbgu = ?, thgft2 = ?, thgpr = ?, thgbl = ?, thgvk = ?, ");
			sql.append(" thalk = ?, thblk = ?, thtaid = ?, thtalk = ?, thtask = ?, thtgid = ?, thtglk = ?, thtgsk = ?, ");
			sql.append(" thtrm = ?, thkdc = ?, thlsd = ?, thcats = ?, thskfd = ?, thdst = ?, thdsk = ?, thdkr = ?, thddt = ?, ");
			
			sql.append(" thtina = ?, thnaa = ?, thada1 = ?, thska = ?, thpsa = ?, thpna = ?, thlka = ?, ");
			sql.append(" thdfkd = ?, thdant = ?, thdfsk = ?, thdk = ? ");
			sql.append(" WHERE thavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getThnttd(), dao.getThntll(), dao.getThfmll(), dao.getThenkl(), dao.getThtdn(), dao.getS0004(),dao.getS0010(), dao.getS0026(), dao.getS0035(), dao.getThekst(),
				dao.getThkns(), dao.getThnas(), dao.getThtins(), dao.getThads1(), dao.getThsks(), dao.getThpss(), dao.getThpns(), dao.getThlks(),
				dao.getThknk(), dao.getThnak(), dao.getThtink(), dao.getThadk1(), dao.getThskk(), dao.getThpsk(), dao.getThpnk(), dao.getThlkk(), 
				dao.getThtsd1(),dao.getThtsd2(),dao.getThtsd3(),dao.getThtsd4(),dao.getThtsd5(),dao.getThtsd6(),dao.getThtsd7(),dao.getThtsd8(), dao.getThtsb(),
				dao.getThgkd(), dao.getThgft1(), dao.getThgadk(), dao.getThgbgi(), dao.getThgbgu(), dao.getThgft2(), dao.getThgpr(), dao.getThgbl(), dao.getThgvk(),
				dao.getThalk(), dao.getThblk(), dao.getThtaid(), dao.getThtalk(), dao.getThtask(), dao.getThtgid(), dao.getThtglk(), dao.getThtgsk(),
				dao.getThtrm(), dao.getThkdc(), dao.getThlsd(), dao.getThcats(), dao.getThskfd(), dao.getThdst(), dao.getThdsk(), dao.getThdkr(), dao.getThddt(),
				
				dao.getThtina(), dao.getThnaa(), dao.getThada1(), dao.getThska(), dao.getThpsa(), dao.getThpna(), dao.getThlka(),
				dao.getThdfkd(), dao.getThdant(), dao.getThdfsk(), dao.getThdk(),
				//WHERE condition
				dao.getThavd() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	/**
	 * DELETE
	 */
	
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
		
			TrustdDao dao = (TrustdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from trustd ");
			sql.append(" WHERE thavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getThavd() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @return
	 */
	private String getSELECT_FROM_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.*, b.koanvn, b.koaknr, c.syrg ");
		sql.append(" from trustd a, kodta b, cundf c  ");
		sql.append(" where a.thavd = b.koaavd ");
		sql.append(" and b.koaknr = c.kundnr ");
		sql.append(" and b.koafir = c.firma ");
		
		
		return sql.toString();
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

}
