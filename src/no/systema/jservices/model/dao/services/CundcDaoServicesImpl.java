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
import no.systema.jservices.model.dao.entities.CundcDto;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.util.DbErrorMessageManager;


public class CundcDaoServicesImpl implements CundcDaoServices {
	private static Logger logger = Logger.getLogger(CundcDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	@Override
	public List<CundcDao> getList(StringBuffer errorStackTrace){
		
		String sql = this.getSELECT_FROM_CLAUSE();
		return this.jdbcTemplate.query( sql, new GenericObjectMapper(new CundcDao()));
	}
	
	@Override
	public List<CundcDao> findById(String ccompn, String cfirma, StringBuffer errorStackTrace) {
		List<CundcDao> retval = new ArrayList<CundcDao>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and ccompn = ? ");
			sql.append(" and cfirma = ? ");

			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { ccompn, cfirma }, new GenericObjectMapper(new CundcDao()));

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}

	
	@Override
	public IDao get(Object qDao, StringBuffer errorStackTrace) {
		CundcDao queryDao = (CundcDao) qDao;
		CundcDao dao = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and rrn(c) = ? ");
			sql.append(" and ccompn = ? ");
			sql.append(" and cfirma = ? ");
			sql.append(" and cconta = ? ");
			
			if ("".equals(queryDao.getCtype())) {
				queryDao.setCtype(null);
				sql.append(" and NULLIF(ctype, '') IS NULL ");			
				dao = (CundcDao) this.jdbcTemplate.queryForObject(sql.toString(),
						new Object[] { queryDao.getRownum(), queryDao.getCcompn(), queryDao.getCfirma(), queryDao.getCconta() },
						new GenericObjectMapper(new CundcDao()));
			
			} else {
				sql.append(" and ctype = ? ");
				dao = (CundcDao) this.jdbcTemplate.queryForObject(sql.toString(),
						new Object[] { queryDao.getRownum(), queryDao.getCcompn(), queryDao.getCfirma(), queryDao.getCconta(), queryDao.getCtype() },
						new GenericObjectMapper(new CundcDao()));
			
			} 


			logger.info("sql="+sql);
			logger.info("queryDao="+ReflectionToStringBuilder.toString(queryDao));

			// If exist in Kofast, it means NOT normal Kontaktperson, the other
			// function, with prefix *
			// The * i also stored in ctype, values could be 'Kalle Anka' or
			// '*SAMLEFAKTURA SP '
			String cleanedCtype = dao.getCtype().substring(1); // Stored with * in CUNDC, without in KOFAST and ARKTXT
			if (existInKofast(cleanedCtype, errorStackTrace)) {
				ArktxtDao arktxtQueryDao = new ArktxtDao();
				arktxtQueryDao.setArtxt(cleanedCtype);
				ArktxtDao arktxtDao = (ArktxtDao) arktxtDaoServices.get(arktxtQueryDao, errorStackTrace);
				if (arktxtDao != null) {
					ArkvedkDao arkvedkQueryDao = new ArkvedkDao();
					arkvedkQueryDao.setAvkund(dao.getCcompn());
					arkvedkQueryDao.setAvfirm(dao.getCfirma());
					arkvedkQueryDao.setAvtype(arktxtDao.getArtype());
					ArkvedkDao arkvedkDao = (ArkvedkDao) arkvedkDaoServices.get(arkvedkQueryDao, errorStackTrace);

					dao.setArkvedkDao(arkvedkDao);
				}
			}

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(e);
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}

		return dao;
	}	
	
	
	@Override
	public CundcDao getLastRegisteredEmmaXmlInfo(String cfirma, StringBuffer errorStackTrace) {
		CundcDao dao = null;		
		StringBuilder sql = new StringBuilder();
		sql.append(this.getSELECT_FROM_CLAUSE());
		sql.append(" and ccompn = (select max(ccompn) from cundc where cconta = 'EMMA-XML' and cfirma = ? ) ");
		sql.append(" and cfirma = ? ");
		sql.append(" and cconta = 'EMMA-XML' ");
		
		try {
			dao = (CundcDao) this.jdbcTemplate.queryForObject(sql.toString(), new Object[] { cfirma, cfirma}, new GenericObjectMapper(new CundcDao()));
		} 
		catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(e);
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}

		return dao;
	}	
	
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		logger.info("::update::");
		int retval = 0;
		try {

			CundcDto dto = (CundcDto) daoObj;
			logger.info("dto.getCtypeorg()="+dto.getCtypeorg());
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE cundc SET cconta= ?, ctype = ?, cphone = ?, cmobil = ?, cemail = ?, clive = ?, ");
			sql.append(" cprint = ?, sonavn= ?, cemne = ?, cavd = ?, cavdio = ?, copd = ?, copdio = ?, cmerge = ?, cfax = ?");
			sql.append(" WHERE ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");
			sql.append(" AND   rrn(cundc) = ?");
			if ("".equals(dto.getCtype())) {
				dto.setCtype(null);
				sql.append(" and NULLIF(ctype, '') IS NULL ");			
				retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dto.getCconta(), dto.getCtype(), dto.getCphone(), dto.getCmobil(), dto.getCemail(), dto.getClive(), 
						dto.getCprint(), dto.getSonavn(), dto.getCemne(), dto.getCavd(), dto.getCavdio(), dto.getCopd(),
						dto.getCopdio(), dto.getCmerge(),dto.getCfax(),
						dto.getCcompn(),dto.getCfirma(), dto.getCcontaorg(), dto.getRownum()
						} );
			
			} else {
				sql.append(" and ctype = ? ");
				retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dto.getCconta(), dto.getCtype(), dto.getCphone(), dto.getCmobil(), dto.getCemail(), dto.getClive(), 
						dto.getCprint(), dto.getSonavn(), dto.getCemne(), dto.getCavd(), dto.getCavdio(), dto.getCopd(),
						dto.getCopdio(), dto.getCmerge(),dto.getCfax(),
						//id's
						dto.getCcompn(),dto.getCfirma(), dto.getCcontaorg(), dto.getRownum(), dto.getCtypeorg() 
						} );
			
			} 
			
			logger.info("dto="+ReflectionToStringBuilder.toString(dto));
			logger.warn("update::sql="+sql.toString());
			
			if (retval>=0) {
				ArkvedkDao arkvedkDao = createArkvedkDao(dto, errorStackTrace);
				if (arkvedkDao != null) {
					arkvedkDaoServices.update(arkvedkDao, errorStackTrace);
				}
			}
			
			
		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString()); // Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}

	@Override
	public int insert(Object dtoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		
		try {
			CundcDto dto = (CundcDto) dtoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO cundc (ccompn, cfirma, cconta, ctype, cphone, cmobil, cemail, clive, ");
			sql.append(" cprint, sonavn, cemne, cavd, cavdio, copd, copdio, cmerge, cfax ) ");

			sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ? ) ");

			logger.info("dto="+ReflectionToStringBuilder.toString(dto));
			logger.info("insert::sql="+sql.toString());

			StringBuilder copd = new StringBuilder();
			copd.append(spaceFiller(dto.getCopd1()));
			copd.append(spaceFiller(dto.getCopd2()));
			copd.append(spaceFiller(dto.getCopd3()));
			copd.append(spaceFiller(dto.getCopd4()));
			copd.append(spaceFiller(dto.getCopd5()));
			
			retval = this.jdbcTemplate.update(sql.toString(),
					new Object[] { dto.getCcompn(), dto.getCfirma(), dto.getCconta(), dto.getCtype(), dto.getCphone(), dto.getCmobil(),
							dto.getCemail(), dto.getClive(), dto.getCprint(), dto.getSonavn(), dto.getCemne(), dto.getCavd(), dto.getCavdio(), copd,
							dto.getCopdio(), dto.getCmerge(), dto.getCfax() });
			
			if (retval>=0) {
				ArkvedkDao arkvedkDao = createArkvedkDao(dto, errorStackTrace);
				boolean exist = arkvedkDaoServices.exists(arkvedkDao, errorStackTrace);
				if (arkvedkDao != null && !exist) {
					arkvedkDaoServices.insert(arkvedkDao, errorStackTrace);
				}
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
	public int delete(Object dtoObj, StringBuffer errorStackTrace) {
		logger.info("::delete::");
		int retval = 0;
		try {

			CundcDto dto = (CundcDto) dtoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE from cundc ");
			sql.append(" WHERE   ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");
			sql.append(" AND   rrn(cundc) = ?");
			
			if ("".equals(dto.getCtype())) {
				sql.append(" and NULLIF(ctype, '') IS NULL ");			
				dto.setCtype(null);
				retval = this.jdbcTemplate.update(sql.toString(), new Object[] { dto.getCcompn(), dto.getCfirma(), dto.getCconta(), dto.getRownum() });
			} else {
				sql.append(" and ctype = ? ");
				retval = this.jdbcTemplate.update(sql.toString(), new Object[] { dto.getCcompn(), dto.getCfirma(), dto.getCconta(), dto.getRownum(), dto.getCtype() });
			} 
			
			logger.info("dao="+ReflectionToStringBuilder.toString(dto));
			logger.info("sql="+sql.toString());
			

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			logger.error("::ERROR::", e);
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}


	@Override
	public void deleteAll(String cfirma, String ccompn, StringBuffer errorStackTrace) {
		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE from cundc ");
			sql.append(" WHERE  ccompn = ? ");
			sql.append(" AND   cfirma = ? ");

			logger.info("sql=" + sql.toString());

			jdbcTemplate.update(sql.toString(), new Object[] { ccompn, cfirma });

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}
	}
	
	@Override
	public boolean exists(String cfirma, String ccompn, String cconta, String ctype, StringBuffer errorStackTrace) {
		try {
			List<CundcDao> retval = new ArrayList<CundcDao>();
			StringBuilder sql = new StringBuilder();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" AND   ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");
			if ("".equals(ctype)) {
				sql.append(" and NULLIF(ctype, '') IS NULL ");			
				retval = this.jdbcTemplate.query(sql.toString(), new Object[] { ccompn, cfirma, cconta }, new GenericObjectMapper(new CundcDao()));
			} else {
				sql.append(" and ctype = ? ");
				retval = this.jdbcTemplate.query(sql.toString(), new Object[] { ccompn, cfirma, cconta, ctype }, new GenericObjectMapper(new CundcDao()));
			} 
			
			logger.info("sql=" + sql.toString());
			logger.info("ccompn="+ccompn+", cfirma="+cfirma+",cconta="+cconta+",ctype="+ctype);
			
			if (retval.size() == 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			logger.error(e);
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			return false;
		}
	}

	
	@Override
	public List findById(String id, StringBuffer errorStackTrace) {
		throw new IllegalArgumentException("Not implemented!");
	}                                    


	private ArkvedkDao createArkvedkDao(CundcDto dto, StringBuffer errorStackTrace) {
		ArkvedkDao arkvedkDao = new ArkvedkDao();
		arkvedkDao.setAvfirm(dto.getCfirma());
		arkvedkDao.setAvkund(dto.getCcompn());
		arkvedkDao.setAvkved(concatAvkved(dto));

		String avtype = "";
		String cleanedCtype = dto.getCtype().substring(1);

		if (existInKofast(cleanedCtype, errorStackTrace)) {
			ArktxtDao arktxtQueryDao = new ArktxtDao();
			arktxtQueryDao.setArtxt(cleanedCtype);
			ArktxtDao arktxtDao = (ArktxtDao) arktxtDaoServices.get(arktxtQueryDao, errorStackTrace);
			if (arktxtDao != null) {
				avtype = arktxtDao.getArtype();
			} else {
				logger.info("Could not find " + cleanedCtype + " in ARKTXT. Can't get avtype when create or update of  ARKVEDK. ");
				return null;
			}
		} else {
			logger.info(cleanedCtype + " don't exist in KOFAST. Can't get avtype when create or update of  ARKVEDK");
			return null;
		}

		arkvedkDao.setAvtype(avtype);
		
		return arkvedkDao;
	
	}

	private String concatAvkved(CundcDto dto) {
		StringBuilder avkved = new StringBuilder();
		
		avkved.append(spaceFiller(dto.getAvkved1()));
		avkved.append(spaceFiller(dto.getAvkved2()));
		avkved.append(spaceFiller(dto.getAvkved3()));
		avkved.append(spaceFiller(dto.getAvkved4()));
		avkved.append(spaceFiller(dto.getAvkved5()));
		avkved.append(spaceFiller(dto.getAvkved6()));
		avkved.append(spaceFiller(dto.getAvkved7()));
		avkved.append(spaceFiller(dto.getAvkved8()));
		avkved.append(spaceFiller(dto.getAvkved9()));
		avkved.append(spaceFiller(dto.getAvkved10()));
		avkved.append(spaceFiller(dto.getAvkved11()));
		avkved.append(spaceFiller(dto.getAvkved12()));
		avkved.append(spaceFiller(dto.getAvkved13()));
		avkved.append(spaceFiller(dto.getAvkved14()));
		avkved.append(spaceFiller(dto.getAvkved15()));
		avkved.append(spaceFiller(dto.getAvkved16()));
		avkved.append(spaceFiller(dto.getAvkved17()));
		avkved.append(spaceFiller(dto.getAvkved18()));
		avkved.append(spaceFiller(dto.getAvkved19()));
		avkved.append(spaceFiller(dto.getAvkved20()));
		avkved.append(spaceFiller(dto.getAvkved21()));
		avkved.append(spaceFiller(dto.getAvkved22()));
		avkved.append(spaceFiller(dto.getAvkved23()));
		avkved.append(spaceFiller(dto.getAvkved24()));
		avkved.append(spaceFiller(dto.getAvkved25()));
		avkved.append(spaceFiller(dto.getAvkved26()));
		avkved.append(spaceFiller(dto.getAvkved27()));
		avkved.append(spaceFiller(dto.getAvkved28()));
		avkved.append(spaceFiller(dto.getAvkved29()));
		avkved.append(spaceFiller(dto.getAvkved30()));
		
		return avkved.toString();
	}


	private String spaceFiller(String toFill) {
		String filled = null;
		String TWO_SPACES = "  ";
		
		if (toFill != null && !"".equals(toFill)) {
			filled = toFill;
		} else {
			filled = TWO_SPACES;
		}
		
		return filled;
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

		sql.append(" select rrn(c) rownum, ccompn, cfirma, cconta, cconta ccontaOrg, ctype, ctype ctypeOrg, cphone, cmobil, cfax, cemail, clive, ");
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
