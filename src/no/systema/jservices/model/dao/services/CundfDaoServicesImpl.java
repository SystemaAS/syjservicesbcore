package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.GenericObjectMapper;
import no.systema.jservices.common.dao.services.FratxtDaoService;
import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.mapper.CundfMapper;
import no.systema.main.util.DbErrorMessageManager;


public class CundfDaoServicesImpl implements CundfDaoServices {
	private static Logger logger = Logger.getLogger(CundfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	@Override
	public List<CundfDao> getList(){
		/*String sql = "select knavn, adr1, adr2, postnr, adr3 from syspedf/cundf  where knavn like ?";
		String paramKnavn = "B%";
		final Object[] params = new Object[]{ paramKnavn }; 
        return this.jdbcTemplate.query( sql, params, new CundfMapper());
        */
		//String sql = this.getSELECT_FROM_CLAUSE();
		//return this.jdbcTemplate.query( sql, new CundfMapper());
		return new ArrayList<CundfDao>();
	}
	
	@Override
	public List<CundfDao> getList(StringBuffer errorStackTrace){
		//Too much data. Must be at least one filter condition
		//OBSOLETE-->String sql = this.getSELECT_FROM_CLAUSE();
		//OBSOLETE-->return this.jdbcTemplate.query( sql, new CundfMapper());
		return new ArrayList<CundfDao>();
	}
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		List<CundfDao> retval = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and kundnr = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new CundfMapper());
			
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
	public List findById(String id, String firm, StringBuffer errorStackTrace){
		List<CundfDao> retval = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and kundnr = ? ");
			sql.append(" and firma = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id, firm }, new CundfMapper());
			
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
	public List findByName(String name, String firm, StringBuffer errorStackTrace){
		String WILDCARD = "%";
		String nameStr = "";
		if(name!=null && !"".equals(name)){ nameStr = name.toUpperCase(); }
		
		List<CundfDao> retval = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and UPPER(knavn) LIKE ? ");
			sql.append(" and firma = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { WILDCARD + nameStr + WILDCARD, firm }, new CundfMapper());
			
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
	public List findByName(String name, StringBuffer errorStackTrace){
		String WILDCARD = "%";
		String nameStr = "";
		if(name!=null && !"".equals(name)){ nameStr = name.toUpperCase(); }
		
		List<CundfDao> retval = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and UPPER(knavn) LIKE ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { WILDCARD + nameStr + WILDCARD }, new CundfMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {

			CundfDao dao = (CundfDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE cundf SET knavn = ?, syrg = ?, adr1 = ?, adr3 = ?, postnr = ?, syland = ?, ");
			sql.append(" dkund = ?,  kpers = ?, sonavn = ?, valkod = ?, spraak = ?, bankg = ?, postg = ?, fmot = ?, betbet = ?, ");
			sql.append(" betmat = ?, sfakt = ?, kgrens = ?, tfaxnr = ?, syregn = ?, sykont = ?, sylikv = ?, syopdt = ?, syminu = ?, ");
			sql.append(" syutlp = ?, sypoge = ?, systat = ?, syselg = ?, syiat1 = ?, syiat2 = ?, sycoty = ?, syfr01 = ?, syfr02 = ?, ");
			sql.append(" syfr03 = ?, syfr04 = ?, syfr05 = ?, syfr06 = ?, sysalu = ?, syepos = ?, aknrku = ?, vatkku = ?, xxbre = ?, ");
			sql.append(" xxlen = ?, xxinm3 = ?, xxinlm = ?, rnraku = ?, golk = ?, kundgr = ?, pnpbku = ?, adr21 = ?, eori = ?, aktkod = ? ");

			sql.append(" WHERE kundnr = ? ");
			sql.append(" AND firma = ? ");

			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getKnavn(), dao.getSyrg(), dao.getAdr1(), dao.getAdr3(), dao.getPostnr(), dao.getSyland(), 
						dao.getDkund(), dao.getKpers(), dao.getSonavn(), dao.getValkod(), dao.getSpraak(), dao.getBankg(), dao.getPostg(), dao.getFmot(), dao.getBetbet(),
						dao.getBetmat(), dao.getSfakt(), dao.getKgrens(), dao.getTfaxnr(), dao.getSyregn(), dao.getSykont(), dao.getSylikv(), dao.getSyopdt(), dao.getSyminu(),
						dao.getSyutlp(), dao.getSypoge(), dao.getSystat(), dao.getSyselg(), dao.getSyiat1(), dao.getSyiat2(), dao.getSycoty(), dao.getSyfr01(), dao.getSyfr02(),
						dao.getSyfr03(), dao.getSyfr04(), dao.getSyfr05(), dao.getSyfr06(), dao.getSysalu(), dao.getSyepos(), dao.getAknrku(), dao.getVatkku(), dao.getXxbre(),
						dao.getXxlen(), dao.getXxinm3(), dao.getXxinlm(), dao.getRnraku(), dao.getGolk(), dao.getKundgr(), dao.getPnpbku(), dao.getAdr21(), dao.getEori(),dao.getAktkod(),
						//id's
						dao.getKundnr(),dao.getFirma()
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
			CundfDao dao = (CundfDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO cundf (firma, kundnr, knavn, syrg, adr1, adr3, postnr, syland, ");  //8
			sql.append(" dkund ,  kpers , sonavn , valkod , spraak , bankg , postg , fmot , betbet , "); //9
			sql.append(" betmat , sfakt , kgrens , tfaxnr , syregn , sykont , sylikv, syopdt , syminu , "); //9
			sql.append(" syutlp , sypoge , systat , syselg , syiat1 , syiat2 , sycoty , syfr01, syfr02 , "); //9
			sql.append(" syfr03 , syfr04 , syfr05 , syfr06 , sysalu , syepos , aknrku, vatkku , xxbre, "); //9
			sql.append(" xxlen , xxinm3 , xxinlm , rnraku , golk , kundgr , pnpbku, adr21 , eori, aktkod ) "); //9

			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, "); //8
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ? , "); //9
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ? , "); //9
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ? , "); //9
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ? , "); //9
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) "); //10

/*			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			logger.info("insert::sql="+sql.toString());
*/			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getFirma(), dao.getKundnr(), dao.getKnavn(), dao.getSyrg(), dao.getAdr1(), dao.getAdr3(), dao.getPostnr(), dao.getSyland(),  //8
					dao.getDkund(), dao.getKpers(), dao.getSonavn(), dao.getValkod(), dao.getSpraak(), dao.getBankg(), dao.getPostg(), dao.getFmot(), dao.getBetbet(), //9
					dao.getBetmat(), dao.getSfakt(), dao.getKgrens(), dao.getTfaxnr(), dao.getSyregn(), dao.getSykont(), dao.getSylikv(), dao.getSyopdt(), dao.getSyminu(), //9
					dao.getSyutlp(), dao.getSypoge(), dao.getSystat(), dao.getSyselg(), dao.getSyiat1(), dao.getSyiat2(), dao.getSycoty(), dao.getSyfr01(), dao.getSyfr02(), //9
					dao.getSyfr03(), dao.getSyfr04(), dao.getSyfr05(), dao.getSyfr06(), dao.getSysalu(), dao.getSyepos(), dao.getAknrku(), dao.getVatkku(), dao.getXxbre(), //9
					dao.getXxlen(), dao.getXxinm3(), dao.getXxinlm(), dao.getRnraku(), dao.getGolk(), dao.getKundgr(), dao.getPnpbku(), dao.getAdr21(), dao.getEori(), dao.getAktkod()}); //10
	

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
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

	
	private void deleteCundc(CundfDao cundfDao, StringBuffer errorStackTrace) {
		cundcDaoServices.deleteAll(cundfDao.getFirma(), cundfDao.getKundnr(), errorStackTrace);
	}
	
	private void deleteFratxt(CundfDao cundfDao, StringBuffer errorStackTrace) {
		try {
			fratxtDaoService.deleteAll(cundfDao.getKundnr());
		} catch (Exception e) {
			logger.info("Error:", e);
			errorStackTrace.append(e.getMessage());
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
	
	
	private String getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select kundnr, knavn, adr1, adr2, postnr, adr3, firma, syrg, syland, ");
		sql.append(" dkund, kpers, sonavn, valkod, spraak, bankg, postg, fmot, betbet, ");
		sql.append(" betmat, sfakt, kgrens, tfaxnr, syregn, sykont, sylikv, syopdt, syminu, ");
		sql.append(" syutlp, sypoge, systat, syselg, syiat1, syiat2, sycoty, syfr01, syfr02, ");
		sql.append(" syfr03, syfr04, syfr05, syfr06, sysalu, syepos, aknrku, vatkku, xxbre, ");
		sql.append(" xxlen, xxinm3, xxinlm, rnraku, golk, kundgr, pnpbku, adr21, eori, aktkod ");
		
		sql.append(" FROM cundf a, firm b ");
		sql.append(" WHERE a.firma = b.fifirm ");
		
		return sql.toString();
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
	
	//TODO: Add more children...



}
