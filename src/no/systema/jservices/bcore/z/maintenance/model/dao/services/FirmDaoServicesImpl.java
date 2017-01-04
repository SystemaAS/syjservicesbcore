


package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.FirmMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.FirmDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 4, 2016
 * 
 */
public class FirmDaoServicesImpl implements FirmDaoServices {
	private static Logger logger = Logger.getLogger(FirmDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<FirmDao> retval = new ArrayList<FirmDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			
			
			retval = this.jdbcTemplate.query( sql.toString(), new FirmMapper());
			
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
		List<FirmDao> retval = new ArrayList<FirmDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			//WHERE
			sql.append(" where a.fifirm = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new FirmMapper());
			
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
		sql.append(" select a.*, b.*, c.*, d.*, e.*, f.*, g.*, z.* ");
		sql.append(" from FIRM AS a ");
		sql.append(" full outer join FIRFB AS b ");
		sql.append(" on a.fifirm = b.fifirm ");
		sql.append(" full outer join FIRMKOS AS c ");
		sql.append(" on a.fifirm = c.fifirm ");
		sql.append(" full outer join FIRKU AS d ");
		sql.append(" on a.fifirm = d.fifirm ");
		sql.append(" full outer join FIRSTA AS e ");
		sql.append(" on a.fifirm = e.fifirm ");
		sql.append(" full outer join FIRTR AS f ");
		sql.append(" on a.fifirm = f.fifirm ");
		sql.append(" left outer join FIRML1 AS g ");
		sql.append(" on a.fifirm = g.l1firm ");
		// no join ?
		sql.append(" inner join KODTV AS z "); //no key in this table
		sql.append(" on z.kovavd = 0 ");
		sql.append(" and z.kovuni = 'V' ");
		
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
			/* TODO
			KodtaDao dao = (KodtaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE firm SET xxx = ? ");
			sql.append(" WHERE xxx = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getXXX(), 
				//WHERE
				dao.getKoaavd() } );
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

	private TransactionTemplate transactionTemplate = null;                                                            
	public void setTransactionTemplate( TransactionTemplate transactionTemplate) {this.transactionTemplate = transactionTemplate;}          
	public TransactionTemplate getTransactionTemplate() {return this.transactionTemplate;}
	
}
