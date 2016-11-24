package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KofastDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.GenericObjectMapper;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.util.DbErrorMessageManager;

/**
 * This Dao manage generic funktionskoder for KOFAST
 * 
 * @author Fredrik Möller
 * @date Nov 21, 2016
 * 
 * 
 */
public class KofastDaoServicesImpl implements KofastDaoServices {
	private static Logger logger = Logger.getLogger(KofastDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	@Override
	public List getList(StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

	@Override
	public List findById(String id, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<KofastDao> findById(FasteKoder kftyp, String kfkod, StringBuffer errorStackTrace) {
		List<KofastDao> retval = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" WHERE kftyp = ?  ");
			sql.append(" AND kfkod = ?  ");

			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { kftyp.toString(), kfkod }, new GenericObjectMapper(new KofastDao()));

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));

		}
		return retval;

	}
	
	
	@Override
	public List<KofastDao> findById(FasteKoder kftyp, StringBuffer errorStackTrace) {
		List<KofastDao> retval = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" WHERE kftyp = ?  ");
			
			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { kftyp.toString()}, new GenericObjectMapper(new KofastDao()));

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));

		}
		return retval;	}
	
	
	//Behöcs denna?
	@Override
	public String getHeaderLabel(FasteKoder kftyp, StringBuffer errorStackTrace) {
		List<KofastDao> retval = null;
		String headerLabel = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" WHERE kftyp = ?  ");
			sql.append(" AND kfkod = ' DEFN' ");

			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { kftyp.toString() }, new GenericObjectMapper(new KofastDao()));
			
			logger.info("retval.size"+retval.size());
			
			for (KofastDao kofastDao : retval) {
				headerLabel = kofastDao.getKftxt();
			}
			

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));

		}
		return headerLabel;

	}
	

	@Override
	public int insert(Object dao, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

	@Override
	public int update(Object dao, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

	@Override
	public int delete(Object dao, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}
	

	private String getSELECT_FROM_CLAUSE(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from kofast ");
	
		return sql.toString();
	}

	private String wildcard(String criteria) {
		if (criteria == null){
			return "%";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("%");
		sb.append(criteria.toUpperCase());
		sb.append("%");
		return sb.toString();
	}
	

	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}



}
