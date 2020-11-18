package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.common.dao.ArkivpDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2020
 * 
 */
public class ArkivpDaoServicesImpl implements ArkivpDaoServices {
	private static Logger logger = Logger.getLogger(ArkivpDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<ArkivpDao> retval = new ArrayList<ArkivpDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//sql.append(" select * from sadeff order by rrn(sadeff) desc");
			sql.append(" select * from arkivp where artype = ? order by ardate desc");
			sql.append(" FETCH FIRST 1000 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { "TL" }, new BeanPropertyRowMapper(ArkivpDao.class));
			
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
		List<ArkivpDao> retval = new ArrayList<ArkivpDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from arkivp where arlink = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(ArkivpDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	public List find(Object obj,StringBuffer errorStackTrace){
		ArkivpDao dao = (ArkivpDao)obj;
		List<ArkivpDao> retval = new ArrayList<ArkivpDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from arkivp where artype = ?" ); params.add(dao.getArtype());
			//walk through the filter fields
			if(StringUtils.isNotEmpty(dao.getArrfk())){ sql.append(" and arrfk = ? "); params.add(dao.getArrfk()); }
			if(StringUtils.isNotEmpty(dao.getAruser())){ sql.append(" and aruser = ? "); params.add(dao.getAruser()); }
			if(!"0".equals(dao.getArdate())){ sql.append(" and ardate = ? "); params.add(dao.getArdate()); }
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(ArkivpDao.class));
			
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
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;

		try{
			ArkivpDao dao = (ArkivpDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO arkivp (arstat, artype, arfirm, aravd, aropd, arunde, aruser, ");
			sql.append(" ardate, artime, arkund, arlink, arrfk, arfri, arbhis ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getArstat(),dao.getArtype(), dao.getArfirm(), dao.getAravd(), dao.getAropd(), dao.getArunde(), dao.getAruser(),
					dao.getArdate(), dao.getArtime(), dao.getArkund(), dao.getArlink(), dao.getArrfk(), dao.getArfri(), dao.getArbhis(),
					
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
		//NA --> refer to update status. There is never a true DELETE
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
