package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.ArkvedkDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.GenericObjectMapper;
import no.systema.jservices.model.dao.entities.CundcDao;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author Fredrik Möller
 * @date Dec 6, 2016
 * 
 * 
 */
public class ArkvedkDaoServicesImpl implements ArkvedkDaoServices {
	private static Logger logger = LoggerFactory.getLogger(ArkvedkDaoServicesImpl.class.getName());
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
		List<ArkvedkDao> retval = null;
		IDao dao = null;
		try {
			ArkvedkDao queryDao = (ArkvedkDao) qDao;
			StringBuilder sql = new StringBuilder();

			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" WHERE  avfirm = ?  ");
			sql.append(" AND    avkund = ? ");
			sql.append(" AND    avtype = ? ");

/*			logger.info("sql="+sql.toString());
			logger.info("queryDao="+ReflectionToStringBuilder.toString(queryDao));
*/			
			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { queryDao.getAvfirm(), queryDao.getAvkund(), queryDao.getAvtype()}, new GenericObjectMapper(new ArkvedkDao()));

			dao = getIDao(retval);

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));

		}
		
		return dao;
	}
	

	private IDao getIDao(List<ArkvedkDao> retval) {
		ArkvedkDao dao = null;
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
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		
		try {
			ArkvedkDao dao = (ArkvedkDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO arkvedk (avfirm, avkund, avtype, avkved )");
			sql.append(" VALUES( ?, ?, ?, ? ) ");

			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			logger.info("insert::sql="+sql.toString());

			retval = this.jdbcTemplate.update(sql.toString(), new Object[] { dao.getAvfirm(), dao.getAvkund(), dao.getAvtype(), dao.getAvkved()});
			
			
		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;	
		
	}	
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {

			ArkvedkDao dao = (ArkvedkDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE arkvedk SET avkved = ? ");
			sql.append(" WHERE  avfirm = ?  ");
			sql.append(" AND    avkund = ? ");
			sql.append(" AND    avtype = ? ");

/*			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			logger.info("update::sql="+sql.toString());
*/
			retval = this.jdbcTemplate.update(sql.toString(),
					new Object[] { dao.getAvkved(),
							// id's
							dao.getAvfirm(), dao.getAvkund(), dao.getAvtype() });

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString()); // Chop the message to comply to
											// JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}
	

	@Override
	public boolean exists(ArkvedkDao arkvedkDao, StringBuffer errorStackTrace) {
		try {
			List<CundcDao> retval = new ArrayList<CundcDao>();
			StringBuilder sql = new StringBuilder();

			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" WHERE  avfirm = ?  ");
			sql.append(" AND    avkund = ? ");
			sql.append(" AND    avtype = ? ");

			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { arkvedkDao.getAvfirm(), arkvedkDao.getAvkund(), arkvedkDao.getAvtype()}, new GenericObjectMapper(new ArkvedkDao()));

			if (retval.size() == 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			return false;
		}
	}	
	
	@Override
	public int delete(Object dao, StringBuffer errorStackTrace) {
		throw new UnsupportedOperationException("Not implemented");
	}
	

	private String getSELECT_FROM_CLAUSE(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from arkvedk ");
	
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
