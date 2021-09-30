package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.model.dao.entities.EdimDao;
import no.systema.jservices.model.dao.entities.SvihDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * Sep 2021
 * 
 */
public class SvihDaoServicesImpl implements SvihDaoServices {
	private static Logger logger = Logger.getLogger(SvihDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	@Override
	public List<SvihDao> getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		
		List<SvihDao> list = new ArrayList<SvihDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from svih ");
			sql.append(" where bla bla = ? ");
			//TODO list = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new EdisMapper());
			
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			list = null;
		}
		return list;
	}
	
	/**
	 * UPDATE
	 */
	@Override
	public int updateStatus(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			SvihDao dao = (SvihDao)daoObj;
			
			final StringBuilder sql = new StringBuilder();
			
			sql.append(" UPDATE svih SET svih_syst = ?   ");
			sql.append(" WHERE  svih_syav = ? AND svih_syop = ? AND svih_sysg = ? AND svih_tuid = ? ");
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSvih_syst(),  
					//WHERE
					dao.getSvih_syav(), dao.getSvih_syop(), dao.getSvih_sysg(), dao.getSvih_tuid() } );
			logger.warn(sql.toString());
			logger.warn("syst:" + dao.getSvih_syst());
			logger.warn("syav:" + dao.getSvih_syav());
			logger.warn("syop:" + dao.getSvih_syop());
			logger.warn("sysg:" + dao.getSvih_sysg());
			logger.warn("tuid:" + dao.getSvih_tuid());
				
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.info(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
			}				
			
		
		return retval;
	}
	
	public int updateLight(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			SvihDao dao = (SvihDao)daoObj;
			
			final StringBuilder sql = new StringBuilder();
			
			sql.append(" UPDATE svih SET svih_syst = ?, svih_syst2 = ?, svih_mrn = ? , svih_lrn = ?   ");
			sql.append(" WHERE  svih_syav = ? AND svih_syop = ? AND svih_sysg = ? AND svih_tuid = ? ");
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSvih_syst(), dao.getSvih_syst2(), dao.getSvih_mrn(), dao.getSvih_lrn(),
					//WHERE
					dao.getSvih_syav(), dao.getSvih_syop(), dao.getSvih_sysg(), dao.getSvih_tuid() } );
			logger.warn(sql.toString());
			logger.warn("syst:" + dao.getSvih_syst());
			logger.warn("syav:" + dao.getSvih_syav());
			logger.warn("syop:" + dao.getSvih_syop());
			logger.warn("sysg:" + dao.getSvih_sysg());
			logger.warn("tuid:" + dao.getSvih_tuid());
			logger.warn("syst2:" + dao.getSvih_syst2());
			logger.warn("mrn:" + dao.getSvih_mrn());
			logger.warn("lrn:" + dao.getSvih_lrn());
			
				
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
	 */
	@Override
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		//N/A	
		return 0;
	}
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		//N/A	
		return 0;
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
	 * Extra last step related to EDIS
	 * @param id
	 * @param errorStackTrace
	 * @return
	 */
	private int createRecordEDISX(String id, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			final StringBuilder sql = new StringBuilder();
			
			sql.append(" INSERT INTO edisx (SXPRODM,SXIFSOBJ ) ");
			sql.append(" VALUES(?,? ) "); 
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { id, "*IFS" });
			
				
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.info(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
			}				
			
		return retval;
	}
	
	private String getFilibfFromFirm() {
		
		String filibf = null;
		try{
			//cmn contains the LAST_USED så we have to increase it before we use it
			StringBuffer sql = new StringBuffer();
			sql.append("Select filibf from firm");
			filibf = (String)this.jdbcTemplate.queryForObject( sql.toString(), String.class);
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			
		}
		return filibf;

	}
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

	


}
