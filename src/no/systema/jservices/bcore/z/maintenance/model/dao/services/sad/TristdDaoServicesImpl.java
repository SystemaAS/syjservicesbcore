package no.systema.jservices.bcore.z.maintenance.model.dao.services.sad;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.sad.TristdMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.TristdDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Sep 20, 2016
 * 
 * 
 */
public class TristdDaoServicesImpl implements TristdDaoServices {
	private static Logger logger = Logger.getLogger(TristdDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<TristdDao> retval = new ArrayList<TristdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new TristdMapper());
			
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
		List<TristdDao> retval = new ArrayList<TristdDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where tiavd = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new TristdMapper());
			
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
			
			TristdDao dao = (TristdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO tristd ( tiavd ");
			
			//sql.append(" s0035, s0004, s0010, selv2, sedty, sedp, seski, sekddk, sekns, senas, seads1, seads2, seads3, seknk, senak, serg, seadk1, seadk2, seadk3, ");
			//sql.append(" senad, setlf, sedst, sedt, seval1, setst, sebel1, sevku, seft1, seft2, seft3, ");
			//sql.append(" selka, selkb, sekdc, setrid, selkt, setrm, segn, sepos, sekdh, sekdft, selv, selvt, ");
			//sql.append(" sekdls, sels, sevkb, sentk ");
			sql.append(" )" );
			sql.append(" VALUES ( ? ");
			//sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			//sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			//sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			//sql.append(" ?, ?, ?, ? ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTiavd()
				 
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
		
		try{
			
			TristdDao dao = (TristdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			
			sql.append(" UPDATE tristd SET tign = ? ");
			//sql.append(" sekdls = ?, sels = ?, sevkb = ?, sentk = ? ");
			sql.append(" WHERE tiavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTign(), 
				//WHERE condition
				dao.getTiavd() } );
			
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
		
			TristdDao dao = (TristdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from tristd ");
			sql.append(" WHERE tiavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTiavd() } );
			
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
		
		sql.append(" select a.* ");
		sql.append(" from tristd a  ");
		
		
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
