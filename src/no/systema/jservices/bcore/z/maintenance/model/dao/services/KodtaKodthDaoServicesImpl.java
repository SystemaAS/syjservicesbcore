package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;
import java.sql.*;
import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.KodtaKodthMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaKodthDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 17, 2016
 * 
 */
public class KodtaKodthDaoServicesImpl implements KodtaKodthDaoServices {
	private static Logger logger = LoggerFactory.getLogger(KodtaKodthDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtaKodthDao> retval = new ArrayList<KodtaKodthDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new KodtaKodthMapper());
			
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
		List<KodtaKodthDao> retval = new ArrayList<KodtaKodthDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where kohavd = ?");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtaKodthMapper());
			
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
	 * @return
	 */
	private String getSELECT_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select kohuni, CHAR(kohavd) kohavd, kohfak, kohlas, kohgod, kohbou, kohkk, kohlos, kohman, kohxx1, ");
		sql.append(" kohls1, koh421, kohls2, koh422, kohls3, koh423, kohxx2  ");
		sql.append(" from kodth  ");
		
		return sql.toString();
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

			KodtaKodthDao dao = (KodtaKodthDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodth ( kohuni, kohavd, kohfak, kohlas, kohgod, kohbou, kohkk, kohlos, kohman, kohxx1, ");
			sql.append(" kohls1, koh421, kohls2, koh422, kohls3, koh423, kohxx2 ) ");
			sql.append(" VALUES ( ?, ?, ?,  ?, ?, ?,  ?, ?, ?,  ?, ?, ?,  ?, ?, ?,  ?, ?  ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKohuni(), dao.getKohavd(), dao.getKohfak(), 
				dao.getKohlas(), dao.getKohgod(), dao.getKohbou(), dao.getKohkk(), dao.getKohlos(), dao.getKohman(),  dao.getKohxx1(), 
				dao.getKohls1(), dao.getKoh421(), dao.getKohls2(), dao.getKoh422(), dao.getKohls3(), dao.getKoh423(), dao.getKohxx2() } );

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
		
		KodtaKodthDao dao = (KodtaKodthDao)daoObj;
		StringBuffer sql = new StringBuffer();
		//DEBUG --> logger.info("mydebug");
		sql.append(" UPDATE kodth SET kohfak = ?, kohlas = ?, kohgod = ?, kohbou = ?, kohkk = ?, kohlos = ?, kohman = ?, kohxx1 = ?, ");
		sql.append(" kohls1 = ?, koh421 = ?, kohls2 = ?, koh422 = ?, kohls3 = ?, koh423 = ?, kohxx2 = ? ");
		sql.append(" WHERE kohavd = ? ");
		
		//params
		retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKohfak(), dao.getKohlas(), dao.getKohgod(), dao.getKohbou(),
			dao.getKohkk(), dao.getKohlos(), dao.getKohman(),  dao.getKohxx1(), dao.getKohls1(), dao.getKoh421(), dao.getKohls2(), dao.getKoh422(),
			dao.getKohls3(), dao.getKoh423(), dao.getKohxx2(),
			//WHERE
			dao.getKohavd() } );
		
		
		return retval;
	}
	
	

	/**
	 * DELETE
	 */
	
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{

			KodtaKodthDao dao = (KodtaKodthDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodth ");
			sql.append(" WHERE kohavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKohavd() } );
			
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
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
