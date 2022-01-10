package no.systema.cw1.jservices.services;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.cw1.jservices.dao.SadfDao;
import no.systema.cw1.jservices.mapper.SadfMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 * 
 */
public class SadfDaoServicesImpl implements SadfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	public List getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	public List getList(String avd, String opd, StringBuffer errorStackTrace){
		List<SadfDao> retval = new ArrayList<SadfDao>();
		
		try{
				
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where sfavd = ? ");
			sql.append(" and sfopdn = ? ");
			sql.append(" FETCH FIRST 1000 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd  },  new SadfMapper());
			
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
	public List findById (String avd, String opd, String datum, StringBuffer errorStackTrace ){
		List<SadfDao> retval = new ArrayList<SadfDao>();
		//String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where sfavd = ? ");
			sql.append(" and sfopdn = ? ");
			sql.append(" and sfdt = ? ");
			
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd, datum }, new SadfMapper());
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
			SadfDao dao = (SadfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from sadf ");
			sql.append(" WHERE sfavd = ? ");
			sql.append(" AND sfopdn = ? ");
			sql.append(" AND sfdt = ? ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSfavd(), dao.getSfopdn() } );
			
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
		sql.append(" SELECT * from sadf ");
		
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
