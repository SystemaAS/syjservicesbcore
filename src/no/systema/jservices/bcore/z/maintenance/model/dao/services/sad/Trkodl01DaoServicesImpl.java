package no.systema.jservices.bcore.z.maintenance.model.dao.services.sad;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.sad.Trkodl01Mapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.Trkodl01Dao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Sep 20, 2016
 * 
 * 
 */
public class Trkodl01DaoServicesImpl implements Trkodl01DaoServices {
	private static Logger logger = Logger.getLogger(Trkodl01DaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	/**
	 * 
	 * @param code
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(String code, StringBuffer errorStackTrace){
		List<Trkodl01Dao> retval = new ArrayList<Trkodl01Dao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where tkunik = ? ");
			retval = this.jdbcTemplate.query( sql.toString() , new Object[] { code }, new Trkodl01Mapper());
			
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
	public List findById (String code, String id, StringBuffer errorStackTrace ){
		List<Trkodl01Dao> retval = new ArrayList<Trkodl01Dao>();
		String WILDCARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where tkunik = ?  ");
			sql.append(" and tkkode like ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { code, WILDCARD + id + WILDCARD }, new Trkodl01Mapper());
			
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
	 */
	public List getList(StringBuffer errorStackTrace){
		List retval = new ArrayList();
		// N/A
		return retval;
	}
	/**
	 * 
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		List retval = new ArrayList();
		// N/A
		return  retval;
	}
	
	
	/**
	 * 
	 * @return
	 */
	private String getSELECT_FROM_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * ");
		sql.append(" from trkodl01  ");
	
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