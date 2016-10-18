package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.KodtsfSyparfMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtvKodtwDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtsfSyparfDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Okt 18, 2016
 * 
 * 
 */
public class SyparfDaoServicesImpl implements SyparfDaoServices {
	private static Logger logger = Logger.getLogger(SyparfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public int findById (String id, StringBuffer errorStackTrace ){
		int retval = 0;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select count(syuser) ");
			sql.append(" from syparf");
			//WHERE
			sql.append(" where syvrda = ?  ");
			retval = this.jdbcTemplate.queryForInt( sql.toString(), new Object[] { id });
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}
		return retval;
	}

	/**
	 * INSERT
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			KodtsfSyparfDao dao = (KodtsfSyparfDao)daoObj;
			//get counter
			int syrecnCounter = this.getCounterFromTellge(errorStackTrace);
			String syrecn = String.valueOf(syrecnCounter);
			dao.setSyrecn(syrecn);
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> 
			logger.info("Inside insert");
			sql.append(" INSERT INTO syparf ( syrecn, syuser, syvrda, sypaid, sykunr, syavd, sysort, syvrdn ) ");
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSyrecn(), dao.getSyuser(), dao.getSyvrda(), dao.getSypaid(),
																dao.getSykunr(), dao.getSyavd(), dao.getSysort(), dao.getSyvrdn()  } );
			
		
			
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
			KodtsfSyparfDao dao = (KodtsfSyparfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE syparf SET syuser = ? ");
			sql.append(" WHERE syvrda = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSyuser(), 
				//WHERE
				dao.getKosfsi() } );
			
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
			KodtsfSyparfDao dao = (KodtsfSyparfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE from syparf ");
			sql.append(" WHERE syvrda = ? ");
			
			retval = this.jdbcTemplate.queryForInt( sql.toString(), new Object[] { dao.getKosfsi() });
			
			
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
	 * @param errorStackTrace
	 * @return
	 */
	private int getCounterFromTellge(StringBuffer errorStackTrace){
		int retval = 0;
		try{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT geno from tellge ");
		sql.append(" FROM tellge ");	
		sql.append(" WHERE geco = 'SYPAR' ");	
		
		retval = this.jdbcTemplate.queryForInt( sql.toString());
		
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
