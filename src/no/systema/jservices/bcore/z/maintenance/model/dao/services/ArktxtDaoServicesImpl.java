package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.ArktxtDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.GenericObjectMapper;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author Fredrik Möller
 * @date Dec 6, 2016
 * 
 * 
 */
public class ArktxtDaoServicesImpl implements ArktxtDaoServices {
	private static Logger logger = Logger.getLogger(ArktxtDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	@Override
	public List getList(StringBuffer errorStackTrace) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public List findById(String id, StringBuffer errorStackTrace) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public IDao get(Object qDao, StringBuffer errorStackTrace) {
		List<ArktxtDao> retval = null;
		IDao dao = null;
		try {
			ArktxtDao queryDao = (ArktxtDao) qDao;
			StringBuilder sql = new StringBuilder();

			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" WHERE artxt = ?  ");

			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { queryDao.getArtxt()}, new GenericObjectMapper(new ArktxtDao()));

			dao = getIDao(retval);

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));

		}
		
		return dao;
	}
	

	private IDao getIDao(List<ArktxtDao> retval) {
		ArktxtDao dao = null;
		if (retval.size() > 1) {  //Sanity check
			throw new IllegalArgumentException("Query resulted in more than one row. retval="+retval.size());
		}
		if (retval.size() == 1) {
			dao = retval.get(0);
		} else {
			//returning null
		}
		
		return dao;
	}

	@Override
	public int insert(Object dao, StringBuffer errorStackTrace) {
		throw new UnsupportedOperationException("Not implemented");
	}


	
	@Override
	public int update(Object dao, StringBuffer errorStackTrace) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public int delete(Object dao, StringBuffer errorStackTrace) {
		throw new UnsupportedOperationException("Not implemented");
	}
	

	private String getSELECT_FROM_CLAUSE(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from arktxt ");
	
		return sql.toString();
	}

	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}


	private DataSourceTransactionManager txManager = null;
	public void setDataSourceTransactionManager( DataSourceTransactionManager txManager) {this.txManager = txManager;}          
	public DataSourceTransactionManager getDataSourceTransactionManager() {return this.txManager;}
	
}
