package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import no.systema.jservices.common.dao.SvtfiDao;
import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.model.dao.entities.EdimDao;
import no.systema.jservices.model.dao.entities.Sviv_aggDao;
import no.systema.jservices.model.dao.mapper.EdimMapper;
import no.systema.jservices.model.dao.mapper.SvtfiMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * Sep 2021
 * 
 */
public class Sviv_aggDaoServicesImpl implements Sviv_aggDaoServices {
	private static Logger logger = Logger.getLogger(Sviv_aggDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	@Override
	public List<EdimDao> getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		/*
		List<EdimDao> list = new ArrayList<EdimDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from edim ");
			sql.append(" where msn = ? ");
			list = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new EdimMapper());
			
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			list = null;
		}
		return list;
		*/
		return null;
	}
	
	
	
	
	/**
	 * UPDATE
	 */
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		/*
		try{
			
			EdimDao dao = (EdimDao)daoObj;
			String today = new DateTimeManager().getCurrentDate_ISO("yyyMMdd");
			String now = new DateTimeManager().getCurrentDate_ISO("HHmmss");
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE edim SET msn = ?, mst = ?, mdt = ?, mtm = ?   ");
			sql.append(" WHERE  mavd = ? AND mtdn = ? AND msr = ? AND mdt = ? AND mtm = ? ");
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getMsn(), dao.getMst(), today, now,  
					//WHERE
					dao.getMavd(), dao.getMtdn(), dao.getMsr(), dao.getMdt(), dao.getMtm() } );
			
			//adjust edic.cmn counter only when it has been incremented
			if(retval>=0){
				if(StringUtils.isNotEmpty(dao.getMsn())) {
					retval = this.updateCounterEdicCsn(dao.getMsn(), errorStackTrace);
				}
			}
			
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
	
	
	/** TEST for method test = OK
	 * 
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			
			final Sviv_aggDao dao = (Sviv_aggDao) daoObj;
			
			final StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO SVIV_AGG(sviv_syav, sviv_syop, sviv_syli, sviv_vata )");
			sql.append(" VALUES (? ,?, ?, ?)");
		
		
			//logger.warn(dao.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getSviv_syav(), dao.getSviv_syop(), dao.getSviv_syli(), dao.getSviv_vata()
					});
			
				
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.info(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
			}				
			
		return retval;
	}
	
	/**
	 * INSERT 
	 * TEST = OK with only 4 columns.
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insert(List<Sviv_aggDao> items, StringBuffer errorStackTrace) {
		int retval = 0;
		StringBuilder sql = new StringBuilder();
		try {
			
			sql.append(" INSERT INTO SVIV_AGG(sviv_syav, sviv_syop, sviv_syli )");
			sql.append(" VALUES (? ,?, ?)");
			
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				
				@Override
			      public void setValues(PreparedStatement ps, int i) throws SQLException {
					Sviv_aggDao item = items.get(i);
			        ps.setString(1, item.getSviv_syav());
			        ps.setString(2, item.getSviv_syop());
			        ps.setString(3, item.getSviv_syli());
			        //TODO
			      }
			      @Override
			      public int getBatchSize() {
			        return items.size();
			      }
			    });
			
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.info(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
			}				
			return retval;
	}
	
		/**
	 * 
	 * Note: This method must NOT be called as public, use cascadeDelete instead
	 * 
	 */
	@Override
	public int delete(Object daoObj, StringBuffer errorStackTrace) {
		//N/A
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
