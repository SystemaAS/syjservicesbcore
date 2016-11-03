package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.model.dao.entities.CundcDao;
import no.systema.jservices.model.dao.mapper.CundcMapper;
import no.systema.main.util.DbErrorMessageManager;


public class CundcDaoServicesImpl implements CundcDaoServices {
	private static Logger logger = Logger.getLogger(CundcDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	@Override
	public List<CundcDao> getList(StringBuffer errorStackTrace){
		
		String sql = this.getSELECT_FROM_CLAUSE();
		return this.jdbcTemplate.query( sql, new CundcMapper());
	}
	
	@Override
	public List<CundcDao> findById(String ccompn, String cfirma, StringBuffer errorStackTrace) {
		List<CundcDao> retval = new ArrayList<CundcDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and ccompn = ? ");
			sql.append(" and cfirma = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { ccompn, cfirma }, new CundcMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}

	@Override
	public List<CundcDao> findById(String ccompn, String cfirma, String ccconta, StringBuffer errorStackTrace) {
		List<CundcDao> retval = new ArrayList<CundcDao>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and ccompn = ? ");
			sql.append(" and cfirma = ? ");
			sql.append(" ans ccconta = ? ");

			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { ccompn, cfirma, ccconta }, new CundcMapper());

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {

			CundcDao dao = (CundcDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE cundc SET ctype = ?, cphone = ?, cmobil = ?, cfax = ?, cemail = ?, clive = ?, ");
			sql.append(" cprint = ?, sonavn= ?, cemne = ?, cavd = ?, cavdio = ?, copd = ?, copdio = ?, cmerge = ?");
			sql.append(" WHERE ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");

			logger.info("sql="+sql.toString());
			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getCtype(), dao.getCphone(), dao.getCmobil(), dao.getCfax(), dao.getCemail(), dao.getClive(), 
						dao.getCprint(), dao.getSonavn(), dao.getCemne(), dao.getCavd(), dao.getCavdio(), dao.getCopd(),
						dao.getCopdio(), dao.getCmerge(),
						//id's
						dao.getCcompn(),dao.getCfirma(), dao.getCconta()
						} );
			
		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString()); // Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}

	@Override
	public int insert(Object dao, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(Object dao, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List findById(String id, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		return null;
	}                                    
	
	
	private String getSELECT_FROM_CLAUSE(){
		StringBuilder sql = new StringBuilder();

		sql.append(" select ccompn, cfirma, cconta, ctype, cphone, cmobil, cfax, cemail, clive, ");
		sql.append(" cprint, sonavn, cemne, cavd, cavdio, copd, copdio, cmerge ");
		sql.append(" FROM cundc c, firm f ");
		sql.append(" WHERE c.cfirma = f.fifirm ");
		
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
