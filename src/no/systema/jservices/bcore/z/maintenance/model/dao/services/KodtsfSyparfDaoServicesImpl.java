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
 * @date Sep 21, 2016
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
		String WILDCARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where a.kosfsi like ?  ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { WILDCARD + id + WILDCARD }, new KodtsfSyparfMapper());
			
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
		
		sql.append(" select a.*, b.* ");
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
		/*
		try{
			Syparl3KodtsfDao dao = (Syparl3KodtsfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> 
			logger.info("Inside insert");
			sql.append(" INSERT INTO kodta ( koauni, koaavd, koanvn, koafir, koaknr, KOABÆR, koakon, koaiat, koaia2, koapos, koaie, koalk ) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKoauni(), dao.getKoaavd(), dao.getKoanvn(), dao.getKoafir(), dao.getKoaknr(), 
					dao.getKoabaer(), dao.getKoakon(), dao.getKoaiat(), dao.getKoaia2(), dao.getKoapos(), dao.getKoaie(), dao.getKoalk() } );
			
		
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
	}
	
	
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		/*
		try{
			Syparl3KodtsfDao dao = (Syparl3KodtsfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE kodta SET koanvn = ?, koafir = ?, koaknr = ?, KOABÆR = ?, koakon = ?, koaiat = ?, koaia2 = ?, ");
			sql.append(" koapos = ?, koaie = ?, koalk = ? ");
			sql.append(" WHERE koaavd = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKoanvn(), dao.getKoafir(), dao.getKoaknr(), dao.getKoabaer(),  
				dao.getKoakon(), dao.getKoaiat(), dao.getKoaia2(), dao.getKoapos(), dao.getKoaie(), dao.getKoalk(), 
				//WHERE
				dao.getKoaavd() } );
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
	}
	
	/**
	 * 
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		/*
		try{
			Syparl3KodtsfDao dao = (Syparl3KodtsfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodtv ");
			sql.append(" WHERE kovavd = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKovavd() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
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
