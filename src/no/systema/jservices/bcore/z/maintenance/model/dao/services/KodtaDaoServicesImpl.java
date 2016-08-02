package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.KodtaMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 1, 2016
 * 
 */
public class KodtaDaoServicesImpl implements KodtaDaoServices {
	private static Logger logger = Logger.getLogger(KodtaDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtaDao> retval = new ArrayList<KodtaDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select CHAR(a.koaavd) koaavd, CHAR(a.koaknr) koaknr, CHAR(a.KOABÆR) koabaer, b.navsg navsg ");
			sql.append(" from kodta a, navavd b ");
			sql.append(" where a.koaavd = b.koaavd (+) ");
			
			
			retval = this.jdbcTemplate.query( sql.toString(), new KodtaMapper());
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
		List<KodtaDao> retval = new ArrayList<KodtaDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select CHAR(a.koaavd) koaavd, CHAR(a.koaknr) koaknr, CHAR(a.KOABÆR) koabaer, b.navsg navsg ");
			sql.append(" from kodta a, navavd b ");
			sql.append(" where a.koaavd = ? ");
			sql.append(" and a.koaavd = b.koaavd ");
			
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtaMapper());
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
		/*
		try{
			KodtlikDao dao = (KodtlikDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodtlik (klista, kliuni, klikod, klinav, klisto, klixxx) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKlista(), dao.getKliuni(), dao.getKlikod(), dao.getKlinav(), 
					dao.getKlisto(), dao.getKlixxx() } );
			
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
	
	
	/**
	 * UPDATE
	 */
	
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		/*
		try{
			KodtlikDao dao = (KodtlikDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE kodtlik SET klinav = ?, klisto = ?, klixxx = ? ");
			sql.append(" WHERE klikod = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKlinav(), dao.getKlisto(), dao.getKlixxx(), dao.getKlikod() } );
			
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
	
	
	/**
	 * DELETE
	 */
	
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		/*
		try{
			KodtlikDao dao = (KodtlikDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodtlik ");
			sql.append(" WHERE klikod = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKlikod() } );
			
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
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
