package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.ArktxtDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.ArkvedkDao;
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
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

	@Override
	public List findById(String id, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

	
	
	//TODO, Hook från Controller till get
	
	
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

	//TODO, Hook från Controller till insert, med transaktion
	
	@Override
	public int insert(Object dao, StringBuffer errorStackTrace) {

		// :::Alt 1::://

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// explicitly setting the transaction name is something that can only be
		// done programmatically
		def.setName("SomeTxName");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = txManager.getTransaction(def);
		try {
			// execute your business logic here
		} catch (Exception ex) {
			txManager.rollback(status);
			throw ex;
		}
		txManager.commit(status);

		// :::Alt 2:::// Recommended by Spring
		//No return
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// updateOperation1();
				// updateOperation2();
			}
		});

		//With return
/*        return transactionTemplate.execute(new TransactionCallback() {
            // the code in this method executes in a transactional context
            public Object doInTransaction(TransactionStatus status) {
                updateOperation1();
                return resultOfUpdateOperation2();
            }
        });		
		
*/		
		
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
	
	private TransactionTemplate transactionTemplate  = null;
	public void setTransactionTemplate( TransactionTemplate txManager) {this.transactionTemplate = transactionTemplate;}          
	public TransactionTemplate getTransactionTemplate() {return this.transactionTemplate;}	
	
/*	<bean id="sharedTransactionTemplate"
			class="org.springframework.transaction.support.TransactionTemplate">
			<property name="isolationLevelName" value="ISOLATION_READ_UNCOMMITTED" />
			<property name="timeout" value="30" />
		</bean>*/
	
	
/*	   <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
       <!-- (this dependency is defined somewhere else) -->
       <property name="dataSource" ref="dataSource"/>
   </bean>
*/	
	
	

}
