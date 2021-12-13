package no.systema.jservices.bcore.z.maintenance.model.dao.services.skat;
import java.io.Writer;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.skat.DkeaMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.skat.DkeaDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Apr 26, 2017
 * 
 * 
 */
public class DkeaDaoServicesImpl implements DkeaDaoServices {
	private static Logger logger = LogManager.getLogger(DkeaDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<DkeaDao> retval = new ArrayList<DkeaDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new DkeaMapper());
			
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
		List<DkeaDao> retval = new ArrayList<DkeaDao>();
		try{
			//N/A
			
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
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public List findById (Object daoObj, StringBuffer errorStackTrace ){
		List<DkeaDao> retval = new ArrayList<DkeaDao>();
		try{
			DkeaDao dao = (DkeaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where dkea_syav = ?  ");
			sql.append(" and dkea_syop = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { dao.getDkea_syav(), dao.getDkea_syop() }, new DkeaMapper());
			
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
			
			DkeaDao dao = (DkeaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO dkea ( dkea_syav, dkea_syop, ");
			sql.append(" dkea_14a, dkea_14b, dkea_14c, dkea_14d, dkea_14e, dkea_14f,  ");
			sql.append(" dkea_0035, dkea_ftip, dkea_us, dkea_pw, dkea_prtf ");
			sql.append(" )" );
			sql.append(" VALUES ( ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ? ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getDkea_syav(), dao.getDkea_syop(), 
			dao.getDkea_14a(), dao.getDkea_14b(), dao.getDkea_14c(), dao.getDkea_14d(), dao.getDkea_14e(), dao.getDkea_14f(),
			dao.getDkea_0035(), dao.getDkea_ftip(), dao.getDkea_us(), dao.getDkea_pw(), dao.getDkea_prtf() } );

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
			
			DkeaDao dao = (DkeaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE dkea SET dkea_14a = ?, dkea_14b = ?, dkea_14c = ?, dkea_14d = ?, dkea_14e = ?, dkea_14f = ?, ");
			sql.append(" dkea_0035 = ?, dkea_ftip = ?, dkea_us = ?, dkea_pw = ?, dkea_prtf = ? ");
			//WHERE
			sql.append(" WHERE dkea_syav = ? ");
			sql.append(" AND dkea_syop = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getDkea_14a(), dao.getDkea_14b(), dao.getDkea_14c(), dao.getDkea_14d(), dao.getDkea_14e(), dao.getDkea_14f(),
				dao.getDkea_0035(), dao.getDkea_ftip(), dao.getDkea_us(), dao.getDkea_pw(), dao.getDkea_prtf(), 
				//WHERE condition
				dao.getDkea_syav(), dao.getDkea_syop() } );

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
		
			DkeaDao dao = (DkeaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from dkea ");
			sql.append(" WHERE dkea_syav = ? ");
			sql.append(" AND dkea_syop = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getDkea_syav(), dao.getDkea_syop() } );
			
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
		
		sql.append(" select * ");
		sql.append(" from dkea ");
		
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
