package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.mapper.CundfMapper;
import no.systema.main.util.DbErrorMessageManager;


public class CundfDaoServicesImpl implements CundfDaoServices {
	private static Logger logger = Logger.getLogger(CundfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 * @return
	 */
	public List<CundfDao> getList(){
		/*String sql = "select knavn, adr1, adr2, postnr, adr3 from syspedf/cundf  where knavn like ?";
		String paramKnavn = "B%";
		final Object[] params = new Object[]{ paramKnavn }; 
        return this.jdbcTemplate.query( sql, params, new CundfMapper());
        */
		String sql = this.getSELECT_FROM_CLAUSE();
		return this.jdbcTemplate.query( sql, new CundfMapper());
	}
	
	/**
	 * 
	 */
	public List<CundfDao> getList(StringBuffer errorStackTrace){
		
		String sql = this.getSELECT_FROM_CLAUSE();
		return this.jdbcTemplate.query( sql, new CundfMapper());
	}
	
	/**
	 * 
	 */
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
	/**
	 * 
	 */
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
	/**
	 * 
	 */
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
	/**
	 * 
	 * @param name
	 * @param errorStackTrace
	 * @return
	 */
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
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	
	@Override
	//TODO: Under prototyping mode, more fields to be included!
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {

			CundfDao dao = (CundfDao) daoObj;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE cundf SET knavn = ?, syrg = ?, adr1 = ?, adr3 = ?, postnr = ?, syland = ? ");
			sql.append(" WHERE kundnr = ? ");
			sql.append(" AND firma = ? ");

			logger.info("sql="+sql.toString());
			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getKnavn(), dao.getSyrg(), dao.getAdr1(), dao.getAdr3(), dao.getPostnr(), dao.getSyland(), 
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
	
	private String getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select kundnr, knavn, adr1, adr2, postnr, adr3, firma, syrg, syland, ");
		//all columns...map to  CundfMapper as needed
		sql.append(" dkund, kpers, sonavn, valkod, spraak, bankg, postg, fmot, betbet, ");
		sql.append(" betmat, sfakt, kgrens, tfaxnr, syregn, sykont, sylikv, syopdt, syminu, ");
		sql.append(" syutlp, sypoge, systat, syselg, syiat1, syiat2, sycoty, syfr01, syfr02, ");
		sql.append(" syfr03, syfr04, syfr05, syfr06, sysalu, syepos, aknrku, vatkku, xxbre, ");
		sql.append(" xxlen, xxinm3, xxinlm, rnraku, golk, kundgr, pnpbku, adr21, eori ");
		
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

}
