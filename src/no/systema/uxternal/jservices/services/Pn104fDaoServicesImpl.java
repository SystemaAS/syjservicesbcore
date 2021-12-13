package no.systema.uxternal.jservices.services;
import java.io.Writer;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.uxternal.jservices.dao.Pn104fDao;
import no.systema.uxternal.jservices.mapper.Pn104fMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Apr, 2021
 * 
 */
public class Pn104fDaoServicesImpl implements Pn104fDaoServices {
	private static Logger logger = LogManager.getLogger(Pn104fDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	public List getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	public List getList(String avd, String opd, StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	 
	public List findById (String id, StringBuffer errorStackTrace ){
		// N/A
		return null;
	}
	/**
	 * 
	 */
	public List findById (String avd, String opd, String datum, StringBuffer errorStackTrace ){
		//N/A
		return null;
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
		logger.warn("A");
		try{
			Pn104fDao dao = (Pn104fDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//cltdn must be negativt according to YBC
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO pn104f (pnkn, pnefn, pnon, pnan, pnevn, pnent, pnetn) ");
			sql.append(" VALUES(?,?,?,?,?,?,? ) ");
			logger.warn(sql.toString());
			logger.warn(dao.toString());
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getPnkn(),dao.getPnefn(), dao.getPnon(), dao.getPnan(), dao.getPnevn(), dao.getPnent(), dao.getPnetn()
					} );
			
			
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
		//N/A
		return retval;
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		//N/A
		return 0;
	}
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
