package no.systema.cw1.jservices.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.cw1.jservices.dao.SadhDao;
import no.systema.cw1.jservices.mapper.SadhMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 * 
 */
public class SadhDaoServicesImpl implements SadhDaoServices {
	private static Logger logger = Logger.getLogger(SadhDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	public List getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	public List getList(String status, StringBuffer errorStackTrace){
		List<SadhDao> retval = new ArrayList<SadhDao>();
		
		try{
				
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where sist = ? ");
			sql.append(" FETCH FIRST 1000 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { status  },  new SadhMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	 
	public List findById (String id, StringBuffer errorStackTrace ){
		// N/A
		return null;
	}
	/**
	 * 
	 */
	public List findById (String avd, String opd, StringBuffer errorStackTrace ){
		List<SadhDao> retval = new ArrayList<SadhDao>();
		//String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where siavd = ? ");
			sql.append(" and sitdn = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd }, new SadhMapper());
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
	 * @param id
	 * @param errorStackTrace
	 * @return
	 */
	
	
	
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		//N/A
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		//N/A
		return retval;
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			SadhDao dao = (SadhDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from sadh ");
			sql.append(" WHERE siavd = ? ");
			sql.append(" AND sitdn = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSiavd(), dao.getSitdn() } );
			
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
	private StringBuffer getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * from sadh ");
		
		return sql;
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
