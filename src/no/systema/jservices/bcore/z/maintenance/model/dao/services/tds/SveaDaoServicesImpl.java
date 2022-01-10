package no.systema.jservices.bcore.z.maintenance.model.dao.services.tds;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.tds.SveaMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.tds.SveaDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Jun 08, 2017
 * 
 * 
 */
public class SveaDaoServicesImpl implements SveaDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SveaDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SveaDao> retval = new ArrayList<SveaDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new SveaMapper());
			
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
		List<SveaDao> retval = new ArrayList<SveaDao>();
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
		List<SveaDao> retval = new ArrayList<SveaDao>();
		try{
			SveaDao dao = (SveaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where svea_syav = ?  ");
			sql.append(" and svea_syop = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { dao.getSvea_syav(), dao.getSvea_syop() }, new SveaMapper());
			
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
			
			SveaDao dao = (SveaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO svea ( svea_syav, svea_syop, ");
			sql.append(" svea_omeo, svea_omty, svea_0035, svea_omha, svea_omtl ");
			sql.append(" )" );
			sql.append(" VALUES ( ?, ?, ");
			sql.append(" ?, ?, ?, ?, ? ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSvea_syav(), dao.getSvea_syop(), 
			dao.getSvea_omeo(), dao.getSvea_omty(), dao.getSvea_0035(), dao.getSvea_omha(), dao.getSvea_omtl() } );

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
			SveaDao dao = (SveaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE svea SET svea_omeo = ?, svea_omty = ?, svea_0035 = ?, svea_omha = ?, svea_omtl = ? ");
			//WHERE
			sql.append(" WHERE svea_syav = ? ");
			sql.append(" AND svea_syop = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getSvea_omeo(), dao.getSvea_omty(), dao.getSvea_0035(), dao.getSvea_omha(), dao.getSvea_omtl(), 
				//WHERE condition
				dao.getSvea_syav(), dao.getSvea_syop() } );

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
		
			SveaDao dao = (SveaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from svea ");
			sql.append(" WHERE svea_syav = ? ");
			sql.append(" AND svea_syop = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSvea_syav(), dao.getSvea_syop() } );
			
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
		sql.append(" from svea ");
		
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
