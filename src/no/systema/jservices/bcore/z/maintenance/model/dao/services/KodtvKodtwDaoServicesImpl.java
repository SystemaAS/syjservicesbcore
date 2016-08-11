package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.KodtvKodtwMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtvKodtwDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 5, 2016
 * 
 */
public class KodtvKodtwDaoServicesImpl implements KodtvKodtwDaoServices {
	private static Logger logger = Logger.getLogger(KodtvKodtwDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtvKodtwDao> retval = new ArrayList<KodtvKodtwDao>();
		/* N/A TODO when needed
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new KodtvKodtwMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		*/
		return retval;
	}
	/**
	 * 
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		List<KodtvKodtwDao> retval = new ArrayList<KodtvKodtwDao>();
		try{
			StringBuffer sql = new StringBuffer();
			logger.info(retval);
			sql.append(this.getSELECT_CLAUSE());
			//WHERE
			sql.append(" where a.kovavd = ?  ");
			sql.append(" and a.kovavd = b.kowavd  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtvKodtwMapper());
			
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
	private String getSELECT_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.kovuni kovuni, CHAR(a.kovavd) kovavd, CHAR(a.kovlkg) kovlkg, CHAR(a.kovkkg) kovkkg, CHAR(a.kovk1) kovk1, CHAR(a.kovk2) kovk2, CHAR(a.kovk3) kovk3, ");
		sql.append(" CHAR(a.kovk4) kovk4, CHAR(a.kovk5) kovk5, CHAR(a.kovk6) kovk6, CHAR(a.kovk7) kovk7, CHAR(a.kovk8) kovk8, CHAR(a.kovk9) kovk9, ");
		sql.append(" CHAR(a.kovk10) kovk10, CHAR(a.kovk11) kovk11, CHAR(a.kovomr) kovomr, a.kovpro kovpro, a.kovfir kovfir, a.kovavr kovavr, a.kovxxx kovxxx,  ");
		sql.append(" CHAR(a.avutpr) avutpr, CHAR(a.avutmi) avutmi, ");
		sql.append(" b.kowuni kowuni, CHAR(b.kowavd) kowavd, b.kowf1 kowf1, b.kowf2 kowf2, b.kowf3 kowf3, b.kowf4 kowf4, b.kowf5 kowf5, b.kowf6 kowf6, b.kowf7 kowf7, ");
		sql.append(" b.kowf8 kowf8, b.kowf9 kowf9, b.kowf10 kowf10, b.kowf11 kowf11, b.kowf12 kowf12, CHAR(b.kowmm) kowmm, b.kowlas kowlas, b.kowawb kowawb, b.kowhod kowhod, ");
		sql.append(" b.kowbbs kowbbs, b.kowxxx kowxxx, b.kowkom kowkom ");
		
		sql.append(" from kodtv a, kodtw b ");
		
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
			KodtaDao dao = (KodtaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO firm ( koaavd, navsg ) ");
			sql.append(" VALUES ( ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKoaavd(), dao.getNavsg() } );
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
		
		try{
			KodtvKodtwDao dao = (KodtvKodtwDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE kodtv SET kovpro = ?, kovlkg = ?, kovkkg = ?, kovavr = ?, avutpr = ?, avutmi = ?, kovomr = ?, ");
			sql.append(" kovk1 = ?, kovk2 = ?, kovk3 = ?, kovk4 = ?, kovk5 = ?, kovk6 = ?, kovk7 = ?, kovk8 = ?, kovk9 = ?, kovk10 = ?, kovk11 = ?   ");
			sql.append(" WHERE kovavd = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKovpro(), dao.getKovlkg(), dao.getKovkkg(), dao.getKovavr(), dao.getAvutpr(), 
				dao.getAvutmi(), dao.getKovomr(), 
				dao.getKovk1(), dao.getKovk2(), dao.getKovk3(), dao.getKovk4(), dao.getKovk5(), dao.getKovk6(), dao.getKovk7(), dao.getKovk8(), dao.getKovk9(), 
				dao.getKovk10(), dao.getKovk11(), 
				//WHERE
				dao.getKovavd() } );
		
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
