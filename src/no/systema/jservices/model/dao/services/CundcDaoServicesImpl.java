package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.ArktxtDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.ArkvedkDao;
import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.GenericObjectMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.ArktxtDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.ArkvedkDaoServices;
import no.systema.jservices.bcore.z.maintenance.model.dao.services.KofastDaoServices;
import no.systema.jservices.common.values.FasteKoder;
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
		List<CundcDao> simpleRetval = new ArrayList<CundcDao>();
		List<CundcDao> compositeRetval = new ArrayList<CundcDao>();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and ccompn = ? ");
			sql.append(" and cfirma = ? ");
			sql.append(" and cconta = ? ");

/*			logger.info("findById::sql="+sql.toString());
			logger.info("ccompn="+ccompn+", cfirma="+cfirma+", ccconta="+ccconta);
*/			
			simpleRetval = this.jdbcTemplate.query(sql.toString(), new Object[] { ccompn, cfirma, ccconta }, new GenericObjectMapper(new CundcDao()));

			for (CundcDao dao : simpleRetval) {
				// If exist in Kofast, it means NOT normal Kontaktperson, the other function, with prefix *
				// The * i also stored in ctype, values could be 'Kalle Anka' or '*SAMLEFAKTURA SP '
				if (dao.getCtype() != null) {
					String cleanedCtype = dao.getCtype().substring(1); //Stored with * in CUNDC, without in KOFAST and ARKTXT
					if (existInKofast(cleanedCtype, errorStackTrace)) {
						
						ArktxtDao arktxtQueryDao = new ArktxtDao();
						arktxtQueryDao.setArtxt(cleanedCtype);
						ArktxtDao arktxtDao = (ArktxtDao) arktxtDaoServices.get(arktxtQueryDao, errorStackTrace);

						ArkvedkDao arkvedkQueryDao = new ArkvedkDao();
						arkvedkQueryDao.setAvkund(ccompn);
						arkvedkQueryDao.setAvfirm(cfirma);
						arkvedkQueryDao.setAvtype(arktxtDao.getArtype());
						ArkvedkDao arkvedkDao = (ArkvedkDao) arkvedkDaoServices.get(arkvedkQueryDao, errorStackTrace);
						
						dao.setArkvedkDao(arkvedkDao);
					}
				}
				
				compositeRetval.add(dao);
			}

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			simpleRetval = null;
			compositeRetval = null;
		}
		
		return compositeRetval;
	}
	
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {

			CundcDao dao = (CundcDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE cundc SET cconta= ?, ctype = ?, cphone = ?, cmobil = ?, cfax = ?, cemail = ?, clive = ?, ");
			sql.append(" cprint = ?, sonavn= ?, cemne = ?, cavd = ?, cavdio = ?, copd = ?, copdio = ?, cmerge = ?");
			sql.append(" WHERE ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");

/*			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			logger.info("update::sql="+sql.toString());
*/			
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getCconta(), dao.getCtype(), dao.getCphone(), dao.getCmobil(), dao.getCfax(), dao.getCemail(), dao.getClive(), 
						dao.getCprint(), dao.getSonavn(), dao.getCemne(), dao.getCavd(), dao.getCavdio(), dao.getCopd(),
						dao.getCopdio(), dao.getCmerge(),
						//id's
						dao.getCcompn(),dao.getCfirma(), dao.getCcontaorg()
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
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		
		try {
			CundcDao dao = (CundcDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO cundc (ccompn, cfirma, cconta, ctype, cphone, cmobil, cfax, cemail, clive, ");
			sql.append(" cprint, sonavn, cemne, cavd, cavdio, copd, copdio, cmerge ) ");

			sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ? ) ");

/*			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			logger.info("insert::sql="+sql.toString());
*/
			retval = this.jdbcTemplate.update(sql.toString(),
					new Object[] { dao.getCcompn(), dao.getCfirma(), dao.getCconta(), dao.getCtype(), dao.getCphone(), dao.getCmobil(), dao.getCfax(),
							dao.getCemail(), dao.getClive(), dao.getCprint(), dao.getSonavn(), dao.getCemne(), dao.getCavd(), dao.getCavdio(), dao.getCopd(),
							dao.getCopdio(), dao.getCmerge() });
	

			
			
			
			
			//TODO: hantera i transaktion

			if(retval>=0){
				arkvedkDaoServices.insert(dao, errorStackTrace);
			}			
			

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
	public int delete(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {

			CundcDao dao = (CundcDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE from cundc ");
			sql.append(" WHERE   ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");
			sql.append(" AND   ctype = ?");

			
/*			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			logger.info("sql="+sql.toString());
*/			
			retval = this.jdbcTemplate.update(sql.toString(), new Object[] { dao.getCcompn(), dao.getCfirma(), dao.getCconta(), dao.getCtype() });

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
	public boolean exists(String cfirma, String ccompn, String cconta, StringBuffer errorStackTrace) {
		try {
			List<CundcDao> retval = new ArrayList<CundcDao>();

			StringBuilder sql = new StringBuilder();

			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" AND   ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");

			/*
			 * logger.info("exists::sql=" + sql.toString()); logger.info(
			 * "sql params::ccompn=" + ccompn + ",cfirma=" + cfirma + ",cconta="
			 * + cconta);
			 */
			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { ccompn, cfirma, cconta }, new CundcMapper());

			if (retval.size() == 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			return false;
		}
	}

	
	@Override
	public List findById(String id, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		return null;
	}                                    

	
	private boolean existInKofast(String ctype,  StringBuffer errorStackTrace) {
		boolean exists = this.kofastDaoServices.exists(FasteKoder.FUNKSJON, ctype, errorStackTrace);
		if (!exists) {
			return false;
		} else {
			return true;
		}

	}	
	
	private String getSELECT_FROM_CLAUSE(){
		StringBuilder sql = new StringBuilder();

		sql.append(" select ccompn, cfirma, cconta, cconta ccontaOrg, ctype, cphone, cmobil, cfax, cemail, clive, ");
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

	private ArkvedkDaoServices arkvedkDaoServices = null;                                                            
	public void setArkvedkDaoServices( ArkvedkDaoServices arkvedkDaoServices) {this.arkvedkDaoServices = arkvedkDaoServices;}          
	public ArkvedkDaoServices getArkvedkDaoServices() {return this.arkvedkDaoServices;}       
	
	private ArktxtDaoServices arktxtDaoServices = null;                                                            
	public void setArktxtDaoServices( ArktxtDaoServices arktxtDaoServices) {this.arktxtDaoServices = arktxtDaoServices;}          
	public ArktxtDaoServices getArktxtDaoServices() {return this.arktxtDaoServices;}       	
	
	private KofastDaoServices kofastDaoServices = null;                                                            
	public void setKofastDaoServices( KofastDaoServices kofastDaoServices) {this.kofastDaoServices = kofastDaoServices;}          
	public KofastDaoServices getKofastDaoServices() {return this.kofastDaoServices;}      
	
	
	
}
