package no.systema.jservices.bcore.z.maintenance.model.dao.services.sad;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.sad.TrustdfvMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.TrustdfvDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Oct 3, 2016
 * 
 * 
 */
public class TrustdfvDaoServicesImpl implements TrustdfvDaoServices {
	private static Logger logger = Logger.getLogger(TrustdfvDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<TrustdfvDao> retval = new ArrayList<TrustdfvDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new TrustdfvMapper());
			
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
		List<TrustdfvDao> retval = new ArrayList<TrustdfvDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" and d.thavd = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new TrustdfvMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
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
			
			TrustdfvDao dao = (TrustdfvDao)daoObj;
			StringBuffer sql = new StringBuffer();
			/*
			sql.append(" INSERT INTO trustd ( tiavd, tienkl, titdn, s0004, s0010, s0026, s0035, ");
			sql.append(" tikn, titin, tina, tiad1, tisk, tips, tipn, tilk, tign, tignsk, titrnr, tialk, titsb, tiskb, ");
			sql.append(" tialsk, tialss, tials, tiglsk, tiacts  ");
			sql.append(" )" );
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ? ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTiavd(),
				dao.getTienkl(), dao.getTitdn(), dao.getS0004(), dao.getS0010(), dao.getS0026(), dao.getS0035(),
				dao.getTikn(), dao.getTitin(), dao.getTina(), dao.getTiad1(), dao.getTisk(), dao.getTips(), dao.getTipn(), dao.getTilk(),
				dao.getTign(), dao.getTignsk(), dao.getTitrnr(),dao.getTialk(), dao.getTitsb(), dao.getTiskb(), 
				dao.getTialsk(), dao.getTialss(), dao.getTials(), dao.getTiglsk(), dao.getTiacts()

				 } );
			*/
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
			
			TrustdfvDao dao = (TrustdfvDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			/*
			sql.append(" UPDATE trustd SET tienkl = ?, titdn = ?, s0004 = ?, s0010 = ?, s0026 = ?, s0035 = ?,  ");
			sql.append(" tikn = ?, titin = ?, tina = ?, tiad1 = ?, tisk = ?, tips = ?, tipn = ?, tilk = ?, ");
			sql.append(" tign = ?, tignsk = ?, titrnr = ?, tialk = ?, titsb = ?, tiskb = ?, ");
			sql.append(" tialsk = ?, tialss = ?, tials = ?, tiglsk = ?, tiacts = ? ");
			sql.append(" WHERE tiavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getTienkl(), dao.getTitdn(), dao.getS0004(), dao.getS0010(), dao.getS0026(), dao.getS0035(),
				dao.getTikn(), dao.getTitin(), dao.getTina(), dao.getTiad1(), dao.getTisk(), dao.getTips(), dao.getTipn(), dao.getTilk(),
				dao.getTign(), dao.getTignsk(), dao.getTitrnr(),dao.getTialk(), dao.getTitsb(), dao.getTiskb(), 
				dao.getTialsk(), dao.getTialss(), dao.getTials(), dao.getTiglsk(), dao.getTiacts(),
				//WHERE condition
				dao.getTiavd() } );
			*/
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
		
			TrustdfvDao dao = (TrustdfvDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from trustdfv ");
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
		
		sql.append(" select d.* ");
		sql.append(" from trustd a, kodta b, cundf c, trustdfv d  ");
		sql.append(" where a.thavd = b.koaavd ");
		sql.append(" and b.koaknr = c.kundnr ");
		sql.append(" and b.koafir = c.firma ");
		sql.append(" and a.thavd = d.thavd ");
		
		
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
