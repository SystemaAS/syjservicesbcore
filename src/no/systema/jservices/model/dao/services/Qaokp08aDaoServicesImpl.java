package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import no.systema.jservices.model.dao.mapper.Qaokp08aMapper;
import no.systema.main.util.DbErrorMessageManager;
import no.systema.jservices.model.dao.entities.Qaokp08Dao;

/**
 * 
 * @author oscardelatorre
 * @date May 05, 2017
 * 
 */
public class Qaokp08aDaoServicesImpl implements Qaokp08aDaoServices {
	private static Logger logger = Logger.getLogger(Qaokp08aDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param userName
	 * @return
	 */
	public List<Qaokp08Dao> getList(StringBuffer errorStackTrace){
		StringBuffer sql = new StringBuffer();
		sql.append("select wos8dden, wos8ddgn, wos8desc ");
		sql.append("from qusrsys.qaokp08a ");
		
		return this.jdbcTemplate.query( sql.toString(), new Qaokp08aMapper());
	}
	
	/**
	 * 
	 * @param id
	 * @param errorStackTrace
	 * @return
	 */
	public List findById(String id, StringBuffer errorStackTrace){
		List<Qaokp08Dao> retval = new ArrayList<Qaokp08Dao>();
		try{
		StringBuffer sql = new StringBuffer();
		sql.append("select wos8dden, wos8ddgn, wos8desc ");
		sql.append("from qusrsys.qaokp08a  ");
		sql.append("where wos8dden = ? ");
		
		retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new Qaokp08aMapper() );
		
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
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
