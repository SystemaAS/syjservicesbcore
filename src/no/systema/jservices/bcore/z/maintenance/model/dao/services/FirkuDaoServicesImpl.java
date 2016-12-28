package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.FirkuDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.GenericObjectMapper;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author Fredrik Möller
 * @date Dec 27, 2016
 * 
 * 
 */
public class FirkuDaoServicesImpl implements FirkuDaoServices {
	private static Logger logger = Logger.getLogger(FirkuDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	@Override
	public List getList(StringBuffer errorStackTrace) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public List findById(String id, StringBuffer errorStackTrace) {
		throw new RuntimeException("Not implemented");
	}

	
	@Override
	public IDao get(StringBuffer errorStackTrace) {
		IDao dao = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(this.getSELECT_FROM_CLAUSE());

			dao = this.jdbcTemplate.queryForObject(sql.toString(), new GenericObjectMapper(new FirkuDao()));

			logger.info("dao=" + ReflectionToStringBuilder.toString(dao));

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));

		}

		return dao;
	}
	
	@Override
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		throw new RuntimeException("Not implemented");
	}	
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			FirkuDao dao = (FirkuDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE firku SET fikune = ? ");

			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			logger.info("update::sql="+sql.toString());

			retval = this.jdbcTemplate.update(sql.toString(),new Object[] { dao.getFikune(), dao.getFifirm()});

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}
	

	@Override
	public int delete(Object dao, StringBuffer errorStackTrace) {
		throw new RuntimeException("Not implemented");
	}
	

	@Override
	public String getNextFikune(StringBuffer errorStackTrace) {
		int nextFikune;
		FirkuDao dao = (FirkuDao) get(errorStackTrace);
		nextFikune = new Integer(dao.getFikune()) + 1;
		
		FirkuDao newDao = SerializationUtils.clone(dao);
		newDao.setFikune(Integer.toString(nextFikune));
		
		update(dao, errorStackTrace);

		return Integer.toString(nextFikune);
	}
	
	private String getSELECT_FROM_CLAUSE(){
		StringBuilder sql = new StringBuilder();

		sql.append(" select fifirm, fikufr, fikuti, fikune ");
		sql.append(" FROM firku fu, firm f ");
		sql.append(" WHERE fu.fifirm = f.fifirm ");
		
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
