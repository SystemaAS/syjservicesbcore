package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import no.systema.jservices.model.dao.mapper.KosttMapper;
import no.systema.jservices.model.dao.entities.KosttDao;
import no.systema.main.util.DbErrorMessageManager;


public class KosttDaoServicesImpl implements KosttDaoServices {
	private static Logger logger = Logger.getLogger(KosttDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 * @return
	 */
	public List<KosttDao> getList(){
		String sql = this.getSELECT_FROM_CLAUSE();
		return this.jdbcTemplate.query( sql, new KosttMapper());
	}
	
	/**
	 * 
	 */
	public List<KosttDao> getList(StringBuffer errorStackTrace){
		
		String sql = this.getSELECT_FROM_CLAUSE();
		return this.jdbcTemplate.query( sql, new KosttMapper());
	}
	
	/**
	 * 
	 */
	public List findById(String id, StringBuffer errorStackTrace){
		List<KosttDao> retval = new ArrayList<KosttDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where kttyp = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KosttMapper());
			
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
	public List findByName(String name, StringBuffer errorStackTrace){
		String WILDCARD = "%";
		String nameStr = "";
		if(name!=null && !"".equals(name)){ nameStr = name.toUpperCase(); }
		
		List<KosttDao> retval = new ArrayList<KosttDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where ktna LIKE ? ");
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { WILDCARD + nameStr + WILDCARD}, new KosttMapper());
			
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
	private String getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from kostt ");
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
