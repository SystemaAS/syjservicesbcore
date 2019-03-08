package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.GenericObjectMapper;
import no.systema.jservices.common.dao.Cum3lmDao;
import no.systema.jservices.common.dao.services.Cum3lmDaoService;
import no.systema.jservices.common.dao.services.FratxtDaoService;
import no.systema.jservices.common.dao.services.SadvareDaoService;
import no.systema.jservices.common.dao.services.SyparfDaoService;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.main.util.DbErrorMessageManager;


public class CundfDaoServicesImpl implements CundfDaoServices {
	private static Logger logger = Logger.getLogger(CundfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	@Override
	public List<CundfDao> getList(){
		throw new UnsupportedOperationException("getList needs some filter");
	}
	
	@Override
	public List<CundfDao> getList(StringBuffer errorStackTrace){
		throw new UnsupportedOperationException("getList needs some filter");
	}
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		List<CundfDao> cundfDaoList = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and kundnr = ? ");
			cundfDaoList = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new GenericObjectMapper(new CundfDao()));
			
			if (cundfDaoList.size() > 0) {
				for (CundfDao cundfDao : cundfDaoList) {
					cundfDao.setCum3lmDao(getCum3lmDao(cundfDao));
				}
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			cundfDaoList = null;
		}
		return cundfDaoList;
	}
	
	@Override
	public List findById(String id, String firm, StringBuffer errorStackTrace){
		List<CundfDao> cundfDaoList = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and kundnr = ? ");
			sql.append(" and firma = ? ");
			cundfDaoList = this.jdbcTemplate.query( sql.toString(), new Object[] { id, firm }, new GenericObjectMapper(new CundfDao()));
			
			if (cundfDaoList.size() > 0) {
				for (CundfDao cundfDao : cundfDaoList) {
					cundfDao.setCum3lmDao(getCum3lmDao(cundfDao));
				}
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			cundfDaoList = null;
		}
		return cundfDaoList;
	}
	
	@Override
	public List findByName(String name, String firm, StringBuffer errorStackTrace){
		String WILDCARD = "%";
		String nameStr = "";
		if(name!=null && !"".equals(name)){ nameStr = name.toUpperCase(); }
		
		List<CundfDao> cundfDaoList = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and UPPER(knavn) LIKE ? ");
			sql.append(" and firma = ? ");
			cundfDaoList = this.jdbcTemplate.query( sql.toString(), new Object[] { WILDCARD + nameStr + WILDCARD, firm }, new GenericObjectMapper(new CundfDao()));

			logger.debug("findbyName(String name, String firm), sqlString="+sql.toString());
			
			if (cundfDaoList.size() > 0) {
				for (CundfDao cundfDao : cundfDaoList) {
					cundfDao.setCum3lmDao(getCum3lmDao(cundfDao));
				}
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			cundfDaoList = null;
		}
		return cundfDaoList;
	}
	

	@Override
	public List findByName(String name, StringBuffer errorStackTrace){
		String WILDCARD = "%";
		String nameStr = "";
		if(name!=null && !"".equals(name)){ nameStr = name.toUpperCase(); }
		
		List<CundfDao> cundfDaoList = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and UPPER(knavn) LIKE ? ");
			cundfDaoList = this.jdbcTemplate.query( sql.toString(), new Object[] { WILDCARD + nameStr + WILDCARD }, new GenericObjectMapper(new CundfDao()));
			
			logger.debug("findbyName(String name), sqlString="+sql.toString());
			
			if (cundfDaoList.size() > 0) {
				for (CundfDao cundfDao : cundfDaoList) {
					cundfDao.setCum3lmDao(getCum3lmDao(cundfDao));
				}
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			cundfDaoList = null;
		}
		return cundfDaoList;
	}
	
	@Override
	public List findByOrgnr(String orgnr, StringBuffer errorStackTrace){
		String WILDCARD = "%";
		String orgnrStr = "";
		if(orgnr!=null && !"".equals(orgnr)){ orgnrStr = orgnr; }
		
		List<CundfDao> cundfDaoList = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and syrg LIKE ? ");
			//logger.info(sql.toString());
			cundfDaoList = this.jdbcTemplate.query( sql.toString(), new Object[] { WILDCARD + orgnrStr + WILDCARD }, new GenericObjectMapper(new CundfDao()));
			
			logger.debug("findbyOrgnr(String orgnr), sqlString="+sql.toString());
			
			if (cundfDaoList.size() > 0) {
				
				for (CundfDao cundfDao : cundfDaoList) {
					cundfDao.setCum3lmDao(getCum3lmDao(cundfDao));
				}
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			cundfDaoList = null;
		}
		return cundfDaoList;
	}
	
	@Override
	public List findByOrgnr(String orgnr, String firm, StringBuffer errorStackTrace){
		String WILDCARD = "%";
		String orgnrStr = "";
		if(orgnr!=null && !"".equals(orgnr)){ orgnrStr = orgnr; }
		
		List<CundfDao> cundfDaoList = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and syrg LIKE ? ");
			sql.append(" and firma = ? ");
			cundfDaoList = this.jdbcTemplate.query( sql.toString(), new Object[] { WILDCARD + orgnrStr + WILDCARD, firm }, new GenericObjectMapper(new CundfDao()));

			logger.debug("findbyOrgnr(String orgnr, String firm), sqlString="+sql.toString());
			
			if (cundfDaoList.size() > 0) {
				for (CundfDao cundfDao : cundfDaoList) {
					cundfDao.setCum3lmDao(getCum3lmDao(cundfDao));
				}
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			cundfDaoList = null;
		}
		return cundfDaoList;
	}


	@Override
	public List findFetchFirstRowsOnly(int nrOfRows, StringBuffer errorStackTrace) {
		List<CundfDao> cundfDaoList = new ArrayList<CundfDao>();

		StringBuffer sql = new StringBuffer();
		sql.append(this.getSELECT_FROM_CLAUSE());
		sql.append(" ORDER BY kundnr ");
		sql.append(" FETCH FIRST "+nrOfRows+" ROWS ONLY ");
		cundfDaoList = this.jdbcTemplate.query( sql.toString(), new GenericObjectMapper(new CundfDao()));
		
		return cundfDaoList;
	}		
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		final CundfDao dao = (CundfDao) daoObj;
		final StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE cundf SET knavn = ?, syrg = ?, adr1 = ?, adr2 = ?, adr3 = ?, postnr = ?, syland = ?, ");
		sql.append(" dkund = ?,  kpers = ?, sonavn = ?, valkod = ?, spraak = ?, bankg = ?, postg = ?, fmot = ?, betbet = ?, ");
		sql.append(" betmat = ?, sfakt = ?, kgrens = ?, tfaxnr = ?, syregn = ?, sykont = ?, sylikv = ?, syopdt = ?, syminu = ?, ");
		sql.append(" syutlp = ?, sypoge = ?, systat = ?, syselg = ?, syiat1 = ?, syiat2 = ?, sycoty = ?, syfr01 = ?, syfr02 = ?, ");
		sql.append(" syfr03 = ?, syfr04 = ?, syfr05 = ?, syfr06 = ?, sysalu = ?, syepos = ?, aknrku = ?, vatkku = ?, xxbre = ?, ");
		sql.append(" xxlen = ?, xxinm3 = ?, xxinlm = ?, rnraku = ?, golk = ?, kundgr = ?, pnpbku = ?, adr21 = ?, eori = ?, aktkod = ?, tlf = ?, symvjn = ?, symvsp = ? ");
		sql.append(" WHERE kundnr = ? ");
		sql.append(" AND firma = ? ");
		sql.append(" WITH NONE ");

		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus ts) {
					try {
						//CUNDF
						jdbcTemplate.update( sql.toString(), new Object[] { 
								dao.getKnavn(), dao.getSyrg(), dao.getAdr1(), dao.getAdr2() , dao.getAdr3(), dao.getPostnr(), dao.getSyland(), 
								dao.getDkund(), dao.getKpers(), dao.getSonavn(), dao.getValkod(), dao.getSpraak(), dao.getBankg(), dao.getPostg(), dao.getFmot(), dao.getBetbet(),
								dao.getBetmat(), dao.getSfakt(), dao.getKgrens(), dao.getTfaxnr(), dao.getSyregn(), dao.getSykont(), dao.getSylikv(), dao.getSyopdt(), dao.getSyminu(),
								dao.getSyutlp(), dao.getSypoge(), dao.getSystat(), dao.getSyselg(), dao.getSyiat1(), dao.getSyiat2(), dao.getSycoty(), dao.getSyfr01(), dao.getSyfr02(),
								dao.getSyfr03(), dao.getSyfr04(), dao.getSyfr05(), dao.getSyfr06(), dao.getSysalu(), dao.getSyepos(), dao.getAknrku(), dao.getVatkku(), dao.getXxbre(),
								dao.getXxlen(), dao.getXxinm3(), dao.getXxinlm(), dao.getRnraku(), dao.getGolk(), dao.getKundgr(), dao.getPnpbku(), dao.getAdr21(), dao.getEori(),dao.getAktkod(),dao.getTlf(),
								dao.getSymvjn(),dao.getSymvsp(),
								//id's
								dao.getKundnr(),dao.getFirma()
								} );
						
						logger.info("jdbcTemplate.update("+ sql.toString()+" executed");
						//CUM3LM
						if (dao.getCum3lmDao() != null) {
							boolean exist = cum3lmDaoService.exist(dao.getCum3lmDao());
							if (exist) {
								cum3lmDaoService.update(dao.getCum3lmDao());
								logger.info("cum3lmDaoService.update() executed");
							} else {
								cum3lmDaoService.create(dao.getCum3lmDao());
								logger.info("cum3lmDaoService.create() executed");
							}
						}

					} catch (Exception e) {
						logger.info("Exception: setting update() to rollback only.");
						logger.error("ERROR:"+e);
						ts.setRollbackOnly();
					}
				}
			});
		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			e.printStackTrace();
			logger.error(":::ERROR:::"+e);
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}	
			
		return retval;
	}

	
	@Override
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		
			final CundfDao dao = (CundfDao) daoObj;
			final StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO cundf (firma, kundnr, knavn, syrg, adr1, adr2, adr3, postnr, syland, ");  
			sql.append(" dkund ,  kpers , sonavn , valkod , spraak , bankg , postg , fmot , betbet , "); 
			sql.append(" betmat , sfakt , kgrens , tfaxnr , syregn , sykont , sylikv, syopdt , syminu , "); 
			sql.append(" syutlp , sypoge , systat , syselg , syiat1 , syiat2 , sycoty , syfr01, syfr02 , "); 
			sql.append(" syfr03 , syfr04 , syfr05 , syfr06 , sysalu , syepos , aknrku, vatkku , xxbre, "); 
			sql.append(" xxlen , xxinm3 , xxinlm , rnraku , golk , kundgr , pnpbku, adr21 , eori, aktkod, tlf,symvjn,symvsp  ) "); 

			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ? ,?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ? , "); 
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ? , ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ? , "); 
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ? , "); 
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ? ) "); 
			sql.append(" WITH NONE ");

			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus ts) {
						try {
							//CUNDF
							jdbcTemplate.update( sql.toString(), new Object[] { 
									dao.getFirma(), dao.getKundnr(), dao.getKnavn(), dao.getSyrg(), dao.getAdr1(),dao.getAdr2(), dao.getAdr3(), dao.getPostnr(), dao.getSyland(), 
									dao.getDkund(), dao.getKpers(), dao.getSonavn(), dao.getValkod(), dao.getSpraak(), dao.getBankg(), dao.getPostg(), dao.getFmot(), dao.getBetbet(),
									dao.getBetmat(), dao.getSfakt(), dao.getKgrens(), dao.getTfaxnr(), dao.getSyregn(), dao.getSykont(), dao.getSylikv(), dao.getSyopdt(), dao.getSyminu(),
									dao.getSyutlp(), dao.getSypoge(), dao.getSystat(), dao.getSyselg(), dao.getSyiat1(), dao.getSyiat2(), dao.getSycoty(), dao.getSyfr01(), dao.getSyfr02(),
									dao.getSyfr03(), dao.getSyfr04(), dao.getSyfr05(), dao.getSyfr06(), dao.getSysalu(), dao.getSyepos(), dao.getAknrku(), dao.getVatkku(), dao.getXxbre(),
									dao.getXxlen(), dao.getXxinm3(), dao.getXxinlm(), dao.getRnraku(), dao.getGolk(), dao.getKundgr(), dao.getPnpbku(), dao.getAdr21(), dao.getEori(), dao.getAktkod(),(dao.getTlf()),
									dao.getSymvjn(),dao.getSymvsp()
							});
					
							//CUM3LM
							if (dao.getCum3lmDao() != null) {
								cum3lmDaoService.create(dao.getCum3lmDao());
							}
							
						} catch (Exception e) {
							logger.error("Error: setting update() to rollback only.", e);
							ts.setRollbackOnly();
						}
					}
				});
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.info(e);
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
			}				
			
		return retval;
	}
	
	/**
	 * 
	 * Note: This method must NOT be called as public, use cascadeDelete instead
	 * 
	 */
	@Override
	public int delete(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;

		CundfDao dao = (CundfDao) daoObj;
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE from cundf ");
		sql.append(" WHERE kundnr = ? ");
		sql.append(" AND firma = ? ");

		retval = this.jdbcTemplate.update(sql.toString(), new Object[] { dao.getKundnr(), dao.getFirma() });

		return retval;

	}                                  


/*	@Override
	public int delete(Object daoObj, StringBuffer errorStackTrace) {
		throw new RuntimeException("For testing, to force rollback in cascadeDelete");

	}   
*/	
	@Override
	public int cascadeDelete(final Object daoObj, final StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus ts) {
					try {
						deleteCum3lm((CundfDao) daoObj, errorStackTrace);
						deleteSadvare((CundfDao) daoObj, errorStackTrace);
						deleteSyparf((CundfDao) daoObj, errorStackTrace);
						deleteFratxt((CundfDao) daoObj, errorStackTrace);
						deleteCundc((CundfDao) daoObj, errorStackTrace);
						delete(daoObj, errorStackTrace);
					} catch (Exception e) {
						logger.info("Setting cascadeDelete() to rollback only.");
						ts.setRollbackOnly();
						//throw e;
					}
				}
			});
		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			e.printStackTrace();
			logger.info(e);
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}

	private void deleteCum3lm(CundfDao cundfDao, StringBuffer errorStackTrace) throws Exception {
		try {
			cum3lmDaoService.deleteAll(cundfDao.getFirma(),cundfDao.getKundnr());
			logger.debug("cum3lmDaoService.deleteAll on:"+cundfDao.getFirma()+","+cundfDao.getKundnr());
		} catch (Exception e) {
			logger.error("Error:", e);
			errorStackTrace.append(e.getMessage());
			throw e;
		}
	}	
	
	private void deleteSadvare(CundfDao cundfDao, StringBuffer errorStackTrace) throws Exception {
		try {
			sadvareDaoService.deleteAll(cundfDao.getKundnr());
			logger.debug("sadvareDaoService.deleteAll on:"+cundfDao.getKundnr());
		} catch (Exception e) {
			logger.error("Error:", e);
			errorStackTrace.append(e.getMessage());
			throw e;
		}
	}

	private void deleteCundc(CundfDao cundfDao, StringBuffer errorStackTrace) throws Exception {
		try {
			cundcDaoServices.deleteAll(cundfDao.getFirma(), cundfDao.getKundnr(), errorStackTrace);
			logger.debug("cundcDaoServices.deleteAll on:"+cundfDao.getFirma()+","+cundfDao.getKundnr());
		} catch (Exception e) {
			logger.error("Error:", e);
			errorStackTrace.append(e.getMessage());
			throw e;
		}
	}
	
	private void deleteFratxt(CundfDao cundfDao, StringBuffer errorStackTrace) throws Exception {
		try {
			fratxtDaoService.deleteAll(cundfDao.getKundnr());
			logger.debug("fratxtDaoService.deleteAll on:"+cundfDao.getKundnr());
		} catch (Exception e) {
			logger.error("Error:", e);
			errorStackTrace.append(e.getMessage());
			throw e;
		}
	}
	
	private void deleteSyparf(CundfDao cundfDao, StringBuffer errorStackTrace) throws Exception {
		try {
			syparfDaoService.deleteAll(cundfDao.getKundnr());
			logger.debug("syparfDaoService.deleteAll on:"+cundfDao.getKundnr());
		} catch (Exception e) {
			logger.error("Error:", e);
			errorStackTrace.append(e.getMessage());
			throw e;
		}
	}	

	@Override
	public boolean exists(String kundNr, StringBuffer errorStackTrace) {
		try {
			List<CundfDao> retval = new ArrayList<CundfDao>();
			StringBuilder sql = new StringBuilder();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" AND  kundnr = ? ");

			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { kundNr }, new GenericObjectMapper(new CundfDao()));

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
	public boolean orgNrExist(String orgnr, String kundnr, StringBuffer errorStackTrace) {
		try {
			List<CundfDao> retval = new ArrayList<CundfDao>();
			StringBuilder sql = new StringBuilder();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" AND  syrg = ? ");
			if (!StringUtils.hasValue(kundnr)) {
				retval = this.jdbcTemplate.query(sql.toString(), new Object[] { orgnr }, new GenericObjectMapper(new CundfDao()));
			} else {
				sql.append(" AND  kundnr <> ? ");
				retval = this.jdbcTemplate.query(sql.toString(), new Object[] { orgnr, Integer.parseInt(kundnr) }, new GenericObjectMapper(new CundfDao()));
				
			}

//			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { orgnr, kundnr }, new GenericObjectMapper(new CundfDao()));
			
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
	public int orgNrCount(String orgnr, StringBuffer errorStackTrace) {
		int count = 0;
		try {
			List<CundfDao> retval = new ArrayList<CundfDao>();
			StringBuilder sql = new StringBuilder();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" AND  syrg = ? ");

			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { orgnr }, new GenericObjectMapper(new CundfDao()));

			count =  retval.size();
			
		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}
		
		return count;

	}	
	
	
	private String getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select kundnr, knavn, adr1, adr2, postnr, adr3, firma, syrg, syland, ");
		sql.append(" dkund, kpers, sonavn, valkod, spraak, bankg, postg, fmot, betbet, ");
		sql.append(" betmat, sfakt, kgrens, tfaxnr, syregn, sykont, sylikv, syopdt, syminu, ");
		sql.append(" syutlp, sypoge, systat, syselg, syiat1, syiat2, sycoty, syfr01, syfr02, ");
		sql.append(" syfr03, syfr04, syfr05, syfr06, sysalu, syepos, aknrku, vatkku, xxbre, ");
		sql.append(" xxlen, xxinm3, xxinlm, rnraku, golk, kundgr, pnpbku, adr21, eori, aktkod, tlf,symvjn,symvsp ");
		
		sql.append(" FROM cundf a, firm b ");
		sql.append(" WHERE a.firma = b.fifirm ");
		
		return sql.toString();
	}
	
	private Cum3lmDao getCum3lmDao(CundfDao cundfDao) {
		Cum3lmDao qDao = new Cum3lmDao();
		qDao.setM3kund(Integer.parseInt(cundfDao.getKundnr()));
		qDao.setM3firm(cundfDao.getFirma());
		Cum3lmDao cum3lmResultDao = cum3lmDaoService.find(qDao);
		return cum3lmResultDao;
	}
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

	private TransactionTemplate transactionTemplate = null;                                                            
	public void setTransactionTemplate( TransactionTemplate transactionTemplate) {this.transactionTemplate = transactionTemplate;}          
	public TransactionTemplate getTransactionTemplate() {return this.transactionTemplate;}
	
	private CundcDaoServices cundcDaoServices = null;                                                            
	public void setCundcDaoServices( CundcDaoServices cundcDaoServices) {this.cundcDaoServices = cundcDaoServices;}          
	public CundcDaoServices getCundcDaoServices() {return this.cundcDaoServices;}

	private FratxtDaoService fratxtDaoService = null;                                                            
	public void setFratxtDaoService( FratxtDaoService fratxtDaoService) {this.fratxtDaoService = fratxtDaoService;}          
	public FratxtDaoService getFratxtDaoService() {return this.fratxtDaoService;}
	
	private SyparfDaoService syparfDaoService = null;                                                            
	public void setSyparfDaoService( SyparfDaoService syparfDaoService) {this.syparfDaoService = syparfDaoService;}          
	public SyparfDaoService getSyparfDaoService() {return this.syparfDaoService;}	
	
	private SadvareDaoService sadvareDaoService = null;                                                            
	public void setSadvareDaoService( SadvareDaoService sadvareDaoService) {this.sadvareDaoService = sadvareDaoService;}          
	public SadvareDaoService getSadvareDaoService() {return this.sadvareDaoService;}	
	
	private Cum3lmDaoService cum3lmDaoService = null;                                                            
	public void setCum3lmDaoService( Cum3lmDaoService cum3lmDaoService) {this.cum3lmDaoService = cum3lmDaoService;}          
	public Cum3lmDaoService getCum3lmDaoService() {return this.cum3lmDaoService;}


	//TODO: Add more children...



}
