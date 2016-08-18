package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;
import java.sql.*;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.KodtaHodeMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaHodeDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 17, 2016
 * 
 */
public class KodtaHodeDaoServicesImpl implements KodtaHodeDaoServices {
	private static Logger logger = Logger.getLogger(KodtaHodeDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtaHodeDao> retval = new ArrayList<KodtaHodeDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new KodtaHodeMapper());
			
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
		List<KodtaHodeDao> retval = new ArrayList<KodtaHodeDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where a.koaavd = ?");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtaHodeMapper());
			
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
	private String getSELECT_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.koaavd koaavd, a.koanvn koanvn, coalesce(b.honet,'') honet, coalesce(b.hostfr,'') hostfr ");
		sql.append(" from kodta AS a ");
		sql.append(" full outer join hode AS b ");
		sql.append(" on a.koaavd = b.hoavd ");
		
		return sql.toString();
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
			/* TODO
			KodtpUtskrsDao dao = (KodtpUtskrsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodtp ( kopuni, kopavd, koplnr, kopty, kopnvn, kopcpi, koplpi, koplpp, kopcpl ) ");
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKopuni(), dao.getKopavd(), 
								dao.getUtpnr().trim(), dao.getUtpty(), dao.getKopnvn(),
								dao.getUtpcpi().trim(), dao.getUtplpi().trim(), dao.getUtplpp().trim(), dao.getUtpcpl().trim()  } );
			*/
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
		int retval = 0;
		try{
			/* TODO
			KodtaDao dao = (KodtaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from navavd ");
			sql.append(" WHERE koaavd = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKoaavd() } );
			*/
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
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
