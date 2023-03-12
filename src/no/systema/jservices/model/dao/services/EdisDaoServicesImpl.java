package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.common.dao.SvtfiDao;
import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.model.dao.entities.EdimEdisAs4SenderDao;
import no.systema.jservices.model.dao.entities.EdisDao;
import no.systema.jservices.model.dao.mapper.EdimEdisAs4SenderMapper;
import no.systema.jservices.model.dao.mapper.EdisMapper;
import no.systema.jservices.model.dao.mapper.SvtfiMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * Sep 2021
 * 
 */
public class EdisDaoServicesImpl implements EdisDaoServices {
	private static Logger logger = LoggerFactory.getLogger(EdisDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	@Override
	public List<EdisDao> getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		
		List<EdisDao> list = new ArrayList<EdisDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from edis ");
			sql.append(" where bla bla = ? ");
			list = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new EdisMapper());
			
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			list = null;
		}
		return list;
	}
	
	/**
	 * This method is used in the edi-dms-as4-sender application (SKAT Connectivity module)
	 * 
	 */
	@Override
	public List<EdimEdisAs4SenderDao> findFilePathByOpp(String avd, String opd, String path, StringBuffer errorStackTrace){
		
		List<EdimEdisAs4SenderDao> list = new ArrayList<EdimEdisAs4SenderDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select max(a.msn)msn, max(b.sifs) sifs ");
			sql.append(" from edim a, edis b ");
			sql.append(" where a.msn = b.ssn ");
			sql.append(" and a.mavd = ? ");
			sql.append(" and a.mtdn = ? ");
			
			sql.append(" and b.sifs LIKE ? ");
			sql.append(" and a.msn > 0 ");
			sql.append(" order by max(a.msn) desc ");
			
			
			list = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd, "%" + path + "%" }, new EdimEdisAs4SenderMapper());
			

			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			list = null;
		}
		return list;
	}
	
	/**
	 * UPDATE
	 */
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		
		//N/A
		return retval;
	}
	
	/**
	 * INSERT 
	 */
	@Override
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			
			final EdisDao dao = (EdisDao) daoObj;
			final StringBuilder sql = new StringBuilder();
			if(StringUtils.isNotEmpty(dao.getSlib()) && dao.getSlib().equals("TODO")) {
				dao.setSlib(this.getFilibfFromFirm());
			}
			sql.append(" INSERT INTO edis (s0004,s0010,s0020,s0026,s0035,s0036,ssn,ssr,sst,sdt, ");
			sql.append(" stm,slib,sfile,smbr,smbrs,sifs ) ");
			
		
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,? ) "); 
			
			logger.warn(dao.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getS0004(),dao.getS0010(),dao.getS0020(),dao.getS0026(),dao.getS0035(),dao.getS0036(),dao.getSsn(),dao.getSsr(),dao.getSst(),dao.getSdt(),
					dao.getStm(),dao.getSlib(),dao.getSfile(),dao.getSmbr(),dao.getSmbrs(), dao.getSifs()
					});
			
			//adjust edic.cmn counter
			if(retval>=0){
				retval = this.createRecordEDISX(dao, errorStackTrace);
			}
			
				
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.info(e.toString());
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
		//N/A
		return 0;

	}                                  

	/**
	 * Extra last step related to EDIS
	 * @param id
	 * @param errorStackTrace
	 * @return
	 */
	private int createRecordEDISX(EdisDao dao, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			final StringBuilder sql = new StringBuilder();
			
			sql.append(" INSERT INTO edisx (SXPRODM,SXBACKM,SXWORKM,SXIFSOBJ ) ");
			sql.append(" VALUES(?,?,?,? ) ");
			//outbound files
			String sxIfsObj = "*IFS";
			//inbound files
			if(dao.getSmbr().startsWith("R")) {
				sxIfsObj = dao.getSifs();
			}
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSmbr(),"","", sxIfsObj });
			
				
			} catch (Exception e) {
				Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
				e.printStackTrace();
				logger.info(e.toString());
				errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
				retval = -1;
			}				
			
		return retval;
	}
	
	private String getFilibfFromFirm() {
		
		String filibf = null;
		try{
			//cmn contains the LAST_USED så we have to increase it before we use it
			StringBuffer sql = new StringBuffer();
			sql.append("Select filibf from firm");
			filibf = (String)this.jdbcTemplate.queryForObject( sql.toString(), String.class);
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			
		}
		return filibf;

	}
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

	


}
