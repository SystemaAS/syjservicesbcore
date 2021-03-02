package no.systema.cw1.jservices.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.cw1.jservices.dao.SadwDao;
import no.systema.cw1.jservices.mapper.SadwMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date March, 2021
 * 
 */
public class SadwDaoServicesImpl implements SadwDaoServices {
	private static Logger logger = Logger.getLogger(SadwDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	public List getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	public List getList(String avd, String opd, StringBuffer errorStackTrace){
		List<SadwDao> retval = new ArrayList<SadwDao>();
		
		try{
				
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where swavd = ? ");
			sql.append(" and swtdn = ? ");
			sql.append(" FETCH FIRST 1000 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd  },  new SadwMapper());
			
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
	public List findById (String avd, String opd, String lin, StringBuffer errorStackTrace ){
		List<SadwDao> retval = new ArrayList<SadwDao>();
		//String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where swavd = ? ");
			sql.append(" and swtdn = ? ");
			sql.append(" and swli = ? ");
			
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd, lin }, new SadwMapper());
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
			SadwDao dao = (SadwDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from sadw ");
			sql.append(" WHERE swavd = ? ");
			sql.append(" AND swtdn = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSwavd(), dao.getSwtdn() } );
			
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
		sql.append(" SELECT * from sadw ");
		
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
