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
 * @date Okt 17, 2016
 * 
 * 
 */
public class KodtsfSyparfDaoServicesImpl implements KodtsfSyparfDaoServices {
	private static Logger logger = Logger.getLogger(KodtsfSyparfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	/**
	 * 
	 * @param code
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtsfSyparfDao> retval = new ArrayList<KodtsfSyparfDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" order by a.kosfsi  ");
			retval = this.jdbcTemplate.query( sql.toString() , new KodtsfSyparfMapper());
			
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
		List<KodtsfSyparfDao> retval = new ArrayList<KodtsfSyparfDao>();
		//String WILDCARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where a.kosfsi = ?  ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtsfSyparfMapper());
			
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
	 * @return
	 */
	private String getSELECT_FROM_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.*, coalesce(b.syuser,'') syuser ");
		sql.append(" from kodtsf a ");
		sql.append(" left outer join syparf as b  ");
		sql.append(" on a.kosfsi = b.syvrda  ");
		
		
		return sql.toString();
	}
	
	/**
	 * INSERT
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;

		try{
			KodtsfSyparfDao dao = (KodtsfSyparfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> 
			logger.info("Inside insert");
			sql.append(" INSERT INTO kodtsf ( kosfsi, kosfun, kosfnv ) ");
			sql.append(" VALUES(?, ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKosfsi(), dao.getKosfun(), dao.getKosfnv() } );
			
			if(retval>=0){
				this.updateChildSyparf(daoObj, errorStackTrace);
			}
			
		
			
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
			sql.append(" UPDATE kodtsf SET kosfun = ?, kosfnv = ? ");
			sql.append(" WHERE kosfsi = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKosfun(), dao.getKosfnv(), 
				//WHERE
				dao.getKosfsi() } );
			
			if(retval>=0){
				this.updateChildSyparf(daoObj, errorStackTrace);
				
			}
			
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
	 */
	public int updateChildSyparf(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			KodtsfSyparfDao dao = (KodtsfSyparfDao)daoObj;
			int childRecord = this.syparfDaoServices.findById(dao.getKosfsi(), errorStackTrace);
			logger.info("EXISTS:" + childRecord);
			
			if(childRecord>0){
				logger.info("syparf child update...");
				retval = this.syparfDaoServices.update(dao, errorStackTrace);
				
			}else{
				logger.info("syparf child insert...");
				retval = this.syparfDaoServices.insert(dao, errorStackTrace);
			}
			
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
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			KodtsfSyparfDao dao = (KodtsfSyparfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodtsf ");
			sql.append(" WHERE kosfsi = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKosfsi() } );
			//Delete children
			if(retval>=0){
				this.syparfDaoServices.delete(dao, errorStackTrace);
			}
			
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

	private SyparfDaoServices syparfDaoServices = null;
	public void setSyparfDaoServices ( SyparfDaoServices syparfDaoServices) { this.syparfDaoServices = syparfDaoServices; }
	public SyparfDaoServices getSyparfDaoServices() { return this.syparfDaoServices; }
}
