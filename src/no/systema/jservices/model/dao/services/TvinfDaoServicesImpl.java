package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.common.dao.SvtfiDao;
import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.model.dao.entities.TvinfDao;
import no.systema.jservices.model.dao.mapper.TvinfMapper;
import no.systema.jservices.model.dao.mapper.SvtfiMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * Sep 2021
 * 
 */
public class TvinfDaoServicesImpl implements TvinfDaoServices {
	private static Logger logger = Logger.getLogger(TvinfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	@Override
	public List<TvinfDao> getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		
		List<TvinfDao> list = new ArrayList<TvinfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from tvinf ");
			sql.append(" where fmn = ? ");
			list = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new TvinfMapper());
			
			
			
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
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		
		//N/A
		return retval;
	}
	
	/**
	 * INSERT 
	 */
	@Override
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			
			final TvinfDao dao = (TvinfDao) daoObj;
			final StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO tvinf (fmn,fln,f0077,f4815,f0078a,f0078b,f0078c,f0078d,f0078e ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,? ) ");
			
			logger.warn(dao.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getFmn(),dao.getFln(),dao.getF0077(),dao.getF4815(),dao.getF0078a(),dao.getF0078b(),dao.getF0078c(),dao.getF0078d(),dao.getF0078e()
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
