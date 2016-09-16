package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.KodtaMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 1, 2016
 * 
 */
public class KodtaDaoServicesImpl implements KodtaDaoServices {
	private static Logger logger = Logger.getLogger(KodtaDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtaDao> retval = new ArrayList<KodtaDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" order by a.koaavd " );
			
			retval = this.jdbcTemplate.query( sql.toString(), new KodtaMapper());
			
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
	 * This method is used to show an end-user the valid choices and not all invalid available choices.
	 * 
	 */
	public List getListForAvailableAvdTvinnSadImport(StringBuffer errorStackTrace){
		List<KodtaDao> retval = new ArrayList<KodtaDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.koaavd, a.koafir, a.koanvn, a.koaknr ");
			sql.append(" from kodta AS a ");
			sql.append(" left outer join standi AS b ");
			sql.append(" on a.koaavd = b.siavd ");
			sql.append(" where b.siavd is NULL ");
			sql.append(" order by a.koaavd ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new KodtaMapper());
			
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
	public List getListForAvailableAvdTvinnSadExport(StringBuffer errorStackTrace){
		List<KodtaDao> retval = new ArrayList<KodtaDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.koaavd, a.koafir, a.koanvn, a.koaknr ");
			sql.append(" from kodta AS a ");
			sql.append(" left outer join stande AS b ");
			sql.append(" on a.koaavd = b.seavd ");
			sql.append(" where b.seavd is NULL ");
			sql.append(" order by a.koaavd ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new KodtaMapper());
			
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
		List<KodtaDao> retval = new ArrayList<KodtaDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			//WHERE
			sql.append(" where a.koaavd = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtaMapper());
			
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
		
		sql.append(" select a.koaavd, CHAR(a.koaknr) koaknr, CHAR(a.KOABÆR) koabaer, CHAR(a.koakon) koakon, ");
		sql.append(" a.koafir, a.koanvn, CHAR(a.koaiat) koaiat, CHAR(a.koaia2) koaia2, a.koaie, a.koapos, a.koalk, ");
		sql.append(" coalesce(b.navsg,'') navsg, coalesce(c.ksidnr,'') ksidnr, coalesce(d.kodus1,'') kodus1, coalesce(d.kodus2,'') kodus2,  ");
		sql.append(" coalesce(d.kodus3,'') kodus3, coalesce(d.kodus4,'') kodus4, coalesce(d.kodus5,'') kodus5, coalesce(d.kodus6,'') kodus6 ");
		 
		sql.append(" from kodta AS a ");
		sql.append(" full outer join navavd AS b ");
		sql.append(" on a.koaavd = b.koaavd  ");
		sql.append(" full outer join kodtasid AS c ");
		sql.append(" on a.koaavd = c.ksavd ");
		sql.append(" full outer join kodtd AS d ");
		sql.append(" on a.koaavd = d.kodavd ");
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
			KodtaDao dao = (KodtaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> 
			logger.info("Inside insert");
			sql.append(" INSERT INTO kodta ( koauni, koaavd, koanvn, koafir, koaknr, KOABÆR, koakon, koaiat, koaia2, koapos, koaie, koalk ) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKoauni(), dao.getKoaavd(), dao.getKoanvn(), dao.getKoafir(), dao.getKoaknr(), 
					dao.getKoabaer(), dao.getKoakon(), dao.getKoaiat(), dao.getKoaia2(), dao.getKoapos(), dao.getKoaie(), dao.getKoalk() } );
			
			if(retval>=0){
				this.navavdDaoServices.insert(dao, errorStackTrace);
				this.kodtasidDaoServices.insert(dao, errorStackTrace);
				this.kodtdDaoServices.insert(dao, errorStackTrace);
				
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
			KodtaDao dao = (KodtaDao)daoObj;
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
			
			if(retval>=0){
				this.updateChildNavAvd(daoObj, errorStackTrace);
				this.updateChildKodtasid(daoObj, errorStackTrace);
				this.updateChildKodtd(daoObj, errorStackTrace);
				
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
	 * Child Update on: NAVAVD
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	
	private int updateChildNavAvd(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			KodtaDao dao = (KodtaDao)daoObj;
			int childRecord = this.navavdDaoServices.findById(dao.getKoaavd(), errorStackTrace);
			//logger.info("EXISTS:" + childRecord);
			
			if(childRecord>0){
				logger.info("Navavd child update...");
				retval = this.navavdDaoServices.update(dao, errorStackTrace);
				
			}else{
				logger.info("Navavd child insert...");
				retval = this.navavdDaoServices.insert(dao, errorStackTrace);
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
	 * Child Update on: KODTASID
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	private int updateChildKodtasid(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			KodtaDao dao = (KodtaDao)daoObj;
			int childRecord = this.kodtasidDaoServices.findById(dao.getKoaavd(), errorStackTrace);
			//logger.info("EXISTS:" + childRecord);
			
			if(childRecord>0){
				logger.info("Kodtasid child update...");
				retval = this.kodtasidDaoServices.update(dao, errorStackTrace);
				
			}else{
				logger.info("Kodtasid child insert...");
				retval = this.kodtasidDaoServices.insert(dao, errorStackTrace);
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
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	private int updateChildKodtd(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			KodtaDao dao = (KodtaDao)daoObj;
			int childRecord = this.kodtdDaoServices.findById(dao.getKoaavd(), errorStackTrace);
			//logger.info("EXISTS:" + childRecord);
			
			if(childRecord>0){
				logger.info("Kodtd child update...");
				retval = this.kodtdDaoServices.update(dao, errorStackTrace);
				
			}else{
				logger.info("kodtd child insert...");
				retval = this.kodtdDaoServices.insert(dao, errorStackTrace);
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
	 * DELETE
	 */
	
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			KodtaDao dao = (KodtaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodta ");
			sql.append(" WHERE koaavd = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKoaavd() } );
			
			//Delete children
			if(retval>=0){
				this.navavdDaoServices.delete(dao, errorStackTrace);
				this.kodtasidDaoServices.delete(dao, errorStackTrace);
				this.kodtdDaoServices.delete(dao, errorStackTrace);
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

	private NavavdDaoServices navavdDaoServices = null;                                                            
	public void setNavavdDaoServices( NavavdDaoServices navavdDaoServices) {this.navavdDaoServices = navavdDaoServices;}          
	public NavavdDaoServices getNavavdDaoServices() {return this.navavdDaoServices;}                                    
	
	private KodtasidDaoServices kodtasidDaoServices = null;                                                            
	public void setKodtasidDaoServices( KodtasidDaoServices kodtasidDaoServices) {this.kodtasidDaoServices = kodtasidDaoServices;}          
	public KodtasidDaoServices getKodtasidDaoServices() {return this.kodtasidDaoServices;}                                    
	
	private KodtdDaoServices kodtdDaoServices = null;                                                            
	public void setKodtdDaoServices( KodtdDaoServices kodtdDaoServices) {this.kodtdDaoServices = kodtdDaoServices;}          
	public KodtdDaoServices getKodtdDaoServices() {return this.kodtdDaoServices;}                                    
	
	
	
}
