package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import no.systema.jservices.model.dao.mapper.CundfMapper;
import no.systema.jservices.model.dao.entities.CusdfDao;
import no.systema.main.util.DbErrorMessageManager;


public class CundfDaoServicesImpl implements CundfDaoServices {
	private static Logger logger = Logger.getLogger(CundfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 * @return
	 */
	public List<CusdfDao> getList(){
		/*String sql = "select knavn, adr1, adr2, postnr, adr3 from syspedf/cundf  where knavn like ?";
		String paramKnavn = "B%";
		final Object[] params = new Object[]{ paramKnavn }; 
        return this.jdbcTemplate.query( sql, params, new CundfMapper());
        */
		String sql = this.getSELECT_CLAUSE();
		return this.jdbcTemplate.query( sql, new CundfMapper());
	}
	
	/**
	 * 
	 */
	public List<CusdfDao> getList(StringBuffer errorStackTrace){
		
		String sql = this.getSELECT_CLAUSE();
		return this.jdbcTemplate.query( sql, new CundfMapper());
	}
	
	/**
	 * 
	 */
	public List findById(String id, StringBuffer errorStackTrace){
		List<CusdfDao> retval = new ArrayList<CusdfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where kundnr = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new CundfMapper());
			
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
	public List findById(String id, String firm, StringBuffer errorStackTrace){
		String WILDCARD = "%";
		List<CusdfDao> retval = new ArrayList<CusdfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where kundnr = ? ");
			sql.append(" and firma = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id, firm }, new CundfMapper());
			
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
	public List findByName(String name, String firm, StringBuffer errorStackTrace){
		String WILDCARD = "%";
		List<CusdfDao> retval = new ArrayList<CusdfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where knavn LIKE ? ");
			sql.append(" and firma = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { name + WILDCARD, firm }, new CundfMapper());
			
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
		sql.append(" select kundnr, knavn, adr1, adr2, postnr, adr3, firma, syrg, syland ");
		sql.append(" FROM cundf");
		return sql.toString();
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
