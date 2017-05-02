package no.systema.jservices.bcore.z.maintenance.model.dao.services.skat;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.skat.DkiaMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.skat.DkiaDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Apr 26, 2017
 * 
 * 
 */
public class DkiaDaoServicesImpl implements DkiaDaoServices {
	private static Logger logger = Logger.getLogger(DkiaDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<DkiaDao> retval = new ArrayList<DkiaDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new DkiaMapper());
			
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
		List<DkiaDao> retval = new ArrayList<DkiaDao>();
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
		List<DkiaDao> retval = new ArrayList<DkiaDao>();
		try{
			DkiaDao dao = (DkiaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where dkia_syav = ?  ");
			sql.append(" and dkia_syop = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { dao.getDkia_syav(), dao.getDkia_syop() }, new DkiaMapper());
			
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
			
			DkiaDao dao = (DkiaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO dkia ( dkia_syav, dkia_syop, ");
			sql.append(" dkia_14a, dkia_14b, dkia_14c, dkia_14d, dkia_14e, dkia_14f,  ");
			sql.append(" dkia_0035 ");
			sql.append(" )" );
			sql.append(" VALUES ( ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ");
			sql.append(" ? ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getDkia_syav(), dao.getDkia_syop(), 
			dao.getDkia_14a(), dao.getDkia_14b(), dao.getDkia_14c(), dao.getDkia_14d(), dao.getDkia_14e(), dao.getDkia_14f(),
			dao.getDkia_0035()  } );

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
			
			DkiaDao dao = (DkiaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE dkia SET dkia_14a = ?, dkia_14b = ?, dkia_14c = ?, dkia_14d = ?, dkia_14e = ?, dkia_14f = ?, ");
			sql.append(" dkia_0035 = ? ");
			//WHERE
			sql.append(" WHERE dkia_syav = ? ");
			sql.append(" AND dkia_syop = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getDkia_14a(), dao.getDkia_14b(), dao.getDkia_14c(), dao.getDkia_14d(), dao.getDkia_14e(), dao.getDkia_14f(),
				dao.getDkia_0035(),  
				//WHERE condition
				dao.getDkia_syav(), dao.getDkia_syop() } );

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
		
			DkiaDao dao = (DkiaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from dkia ");
			sql.append(" WHERE dkia_syav = ? ");
			sql.append(" AND dkia_syop = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getDkia_syav(), dao.getDkia_syop() } );
			
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
		sql.append(" from dkia ");
		
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
