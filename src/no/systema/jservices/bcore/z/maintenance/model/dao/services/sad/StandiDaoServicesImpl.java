package no.systema.jservices.bcore.z.maintenance.model.dao.services.sad;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.sad.StandiMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.StandiDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 23, 2016
 * 
 * 
 */
public class StandiDaoServicesImpl implements StandiDaoServices {
	private static Logger logger = Logger.getLogger(StandiDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<StandiDao> retval = new ArrayList<StandiDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where a.siavd = b.koaavd ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new StandiMapper());
			
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
		List<StandiDao> retval = new ArrayList<StandiDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where a.siavd = b.koaavd ");
			sql.append(" and a.siavd = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new StandiMapper());
			
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
			
			StandiDao dao = (StandiDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO standi ( siavd, sinas ) ");
			sql.append(" VALUES ( ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSiavd(), dao.getSinas() } );
			
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
			
			StandiDao dao = (StandiDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE standi SET sinas = ? ");
			sql.append(" WHERE siavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSinas(), 
				//WHERE
				dao.getSiavd() } );
			
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
		
			StandiDao dao = (StandiDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from standi ");
			sql.append(" WHERE siavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSiavd() } );
			
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
		
		sql.append(" select a.*, b.koanvn, a.SIDTØ sidto ");
		sql.append(" from standi a, kodta AS b  ");
		
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
