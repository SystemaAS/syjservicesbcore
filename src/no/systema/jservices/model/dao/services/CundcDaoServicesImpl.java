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
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and ccompn = ? ");
			sql.append(" and cfirma = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { ccompn, cfirma }, new GenericObjectMapper(new CundcDao()));

			
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
	public IDao get(Object qDao, StringBuffer errorStackTrace) {
		CundcDao queryDao = (CundcDao) qDao;
		CundcDao dao = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and ccompn = ? ");
			sql.append(" and cfirma = ? ");
			sql.append(" and cconta = ? ");
			sql.append(" and ctype = ? ");

			logger.info("findById::sql=" + sql.toString());
			logger.info("ccompn=" + queryDao.getCcompn() + ", cfirma=" + queryDao.getCfirma() + ", ccconta=" + queryDao.getCconta() + ", ctype="
					+ queryDao.getCtype());

			dao = (CundcDao) this.jdbcTemplate.queryForObject(sql.toString(),
					new Object[] { queryDao.getCcompn(), queryDao.getCfirma(), queryDao.getCconta(), queryDao.getCtype() },
					new GenericObjectMapper(new CundcDao()));

			logger.info("dao=" + ReflectionToStringBuilder.toString(dao));

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
			logger.info(e);
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}

		return dao;
	}	
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {

			CundcDto dto = (CundcDto) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE cundc SET cconta= ?, ctype = ?, cphone = ?, cmobil = ?, cemail = ?, clive = ?, ");
			sql.append(" cprint = ?, sonavn= ?, cemne = ?, cavd = ?, cavdio = ?, copd = ?, copdio = ?, cmerge = ?");
			sql.append(" WHERE ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");

			logger.info("dto="+ReflectionToStringBuilder.toString(dto));
			logger.info("update::sql="+sql.toString());
			
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dto.getCconta(), dto.getCtype(), dto.getCphone(), dto.getCmobil(), dto.getCemail(), dto.getClive(), 
						dto.getCprint(), dto.getSonavn(), dto.getCemne(), dto.getCavd(), dto.getCavdio(), dto.getCopd(),
						dto.getCopdio(), dto.getCmerge(),
						//id's
						dto.getCcompn(),dto.getCfirma(), dto.getCcontaorg()
						} );
			
			
			//TODO: Lägg till transaktion
			if (retval>=0 && hasArkvedk(dto)) {
				ArkvedkDao arkvedkDao = createArkvedkDao(dto, errorStackTrace);
				if (arkvedkDao != null) {
					arkvedkDaoServices.update(arkvedkDao, errorStackTrace);
				}
			}
			
			
		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString()); // Chop the message to comply to JSON-validation
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
			sql.append(" cprint, sonavn, cemne, cavd, cavdio, copd, copdio, cmerge ) ");

			sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ? ) ");

			logger.info("dto="+ReflectionToStringBuilder.toString(dto));
			logger.info("insert::sql="+sql.toString());

			retval = this.jdbcTemplate.update(sql.toString(),
					new Object[] { dto.getCcompn(), dto.getCfirma(), dto.getCconta(), dto.getCtype(), dto.getCphone(), dto.getCmobil(),
							dto.getCemail(), dto.getClive(), dto.getCprint(), dto.getSonavn(), dto.getCemne(), dto.getCavd(), dto.getCavdio(), dto.getCopd(),
							dto.getCopdio(), dto.getCmerge() });
	
			
			
			//TODO: hantera i transaktion
			if (retval>=0 && hasArkvedk(dto)) {
				ArkvedkDao arkvedkDao = createArkvedkDao(dto, errorStackTrace);
				if (arkvedkDao != null) {
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
		int retval = 0;
		try {

			CundcDto dto = (CundcDto) dtoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE from cundc ");
			sql.append(" WHERE   ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");
			sql.append(" AND   ctype = ?");

			
/*			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			logger.info("sql="+sql.toString());
*/			
			retval = this.jdbcTemplate.update(sql.toString(), new Object[] { dto.getCcompn(), dto.getCfirma(), dto.getCconta(), dto.getCtype() });

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
	public boolean exists(String cfirma, String ccompn, String cconta, String ctype, StringBuffer errorStackTrace) {
		try {
			List<CundcDao> retval = new ArrayList<CundcDao>();

			StringBuilder sql = new StringBuilder();

			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" AND   ccompn = ? ");
			sql.append(" AND   cfirma = ? ");
			sql.append(" AND   cconta = ?");
			sql.append(" AND   ctype = ?");

			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { ccompn, cfirma, cconta, ctype }, new GenericObjectMapper(new CundcDao()));

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
	public List findById(String id, StringBuffer errorStackTrace) {
		throw new IllegalArgumentException("Not implemented!");
	}                                    


	private boolean hasArkvedk(CundcDto dto) {
		if (dto.getAvkved1() != null || dto.getAvkved2() != null || dto.getAvkved3() != null|| dto.getAvkved4() != null|| dto.getAvkved5() != null
		 || dto.getAvkved6() != null || dto.getAvkved7() != null || dto.getAvkved8() != null|| dto.getAvkved9() != null|| dto.getAvkved10() != null
		 || dto.getAvkved11() != null || dto.getAvkved12() != null || dto.getAvkved13() != null|| dto.getAvkved14() != null|| dto.getAvkved15() != null		
		 || dto.getAvkved16() != null || dto.getAvkved17() != null || dto.getAvkved18() != null|| dto.getAvkved19() != null|| dto.getAvkved20() != null		
		 || dto.getAvkved21() != null || dto.getAvkved22() != null || dto.getAvkved23() != null|| dto.getAvkved24() != null|| dto.getAvkved25() != null		
		 || dto.getAvkved26() != null || dto.getAvkved27() != null || dto.getAvkved28() != null|| dto.getAvkved29() != null|| dto.getAvkved30() != null	) {
			return true;
		} else {
			return false;
		}
		
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
		
		avkved.append(dto.getAvkved1());
		avkved.append(dto.getAvkved2());
		avkved.append(dto.getAvkved3());
		avkved.append(dto.getAvkved4());
		avkved.append(dto.getAvkved5());
		avkved.append(dto.getAvkved6());
		avkved.append(dto.getAvkved7());
		avkved.append(dto.getAvkved8());
		avkved.append(dto.getAvkved9());
		avkved.append(dto.getAvkved10());
		avkved.append(dto.getAvkved11());
		avkved.append(dto.getAvkved12());
		avkved.append(dto.getAvkved13());
		avkved.append(dto.getAvkved14());
		avkved.append(dto.getAvkved15());
		avkved.append(dto.getAvkved16());
		avkved.append(dto.getAvkved17());
		avkved.append(dto.getAvkved18());
		avkved.append(dto.getAvkved19());
		avkved.append(dto.getAvkved20());
		avkved.append(dto.getAvkved21());
		avkved.append(dto.getAvkved22());
		avkved.append(dto.getAvkved23());
		avkved.append(dto.getAvkved24());
		avkved.append(dto.getAvkved25());
		avkved.append(dto.getAvkved26());
		avkved.append(dto.getAvkved27());
		avkved.append(dto.getAvkved28());
		avkved.append(dto.getAvkved29());
		avkved.append(dto.getAvkved30());
		
		return avkved.toString();
	}

	
	
	private IDao getIDao(List<CundcDao> retval) {
		CundcDao dao = null;
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
