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
	 * @param id
	 * @param lang
	 * @param errorStackTrace
	 * @return
	 */
	public List findById (String id, String lang, StringBuffer errorStackTrace ){
		List<KodtaHodeDao> retval = new ArrayList<KodtaHodeDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where a.koaavd = ?");
			sql.append(" and b.honet = ?");
			//logger.info("honet:" + "X" + lang + "X");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id, lang }, new KodtaHodeMapper());
			
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
		
		sql.append(" select a.koaavd koaavd, a.koanvn koanvn, coalesce(b.hoavd,'') hoavd, coalesce(b.honet,'') honet, coalesce(b.hostfr,'') hostfr, ");
		sql.append(" b.hoht1 hoht1, b.hoht2 hoht2, b.hoht3 hoht3, b.hoht4 hoht4, b.hoht5 hoht5, b.hoht6 hoht6, b.hoht7 hoht7,  ");
		sql.append(" b.hobt1 hobt1, b.hobt2 hobt2, b.hobt3 hobt3 ");
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

			KodtaHodeDao dao = (KodtaHodeDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO hode ( hoavd, honet, hostfr, hoht1, hoht2, hoht3, hoht4, hoht5, hoht6, hoht7, hobt1, hobt2, hobt3 ) ");
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getHoavd(), dao.getHonet(), dao.getHostfr(), 
				dao.getHoht1(), dao.getHoht2(), dao.getHoht3(), dao.getHoht4(), dao.getHoht5(), dao.getHoht6(),  dao.getHoht7(), 
				dao.getHobt1(), dao.getHobt2(), dao.getHobt3()  } );

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
		
		KodtaHodeDao dao = (KodtaHodeDao)daoObj;
		StringBuffer sql = new StringBuffer();
		//DEBUG --> logger.info("mydebug");
		sql.append(" UPDATE hode SET hostfr = ?, hoht1 = ?, hoht2 = ?, hoht3 = ?, hoht4 = ?, hoht5 = ?, hoht6 = ?, hoht7 = ?, ");
		sql.append(" hobt1 = ?, hobt2 = ?, hobt3 = ? ");
		sql.append(" WHERE hoavd = ? ");
		sql.append(" AND honet = ? ");
		
		//params
		retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getHostfr(), dao.getHoht1(), dao.getHoht2(), dao.getHoht3(),
			dao.getHoht4(), dao.getHoht5(), dao.getHoht6(),  dao.getHoht7(), dao.getHobt1(), dao.getHobt2(), dao.getHobt3(), 
			//dao.getKodus5(), dao.getKodus6(),
			//WHERE
			dao.getHoavd(), dao.getHonet() } );
		
		
		return retval;
	}
	
	

	/**
	 * DELETE
	 */
	
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{

			KodtaHodeDao dao = (KodtaHodeDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from hode ");
			sql.append(" WHERE hoavd = ? ");
			sql.append(" AND honet = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getHoavd(), dao.getHonet() } );
			
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
