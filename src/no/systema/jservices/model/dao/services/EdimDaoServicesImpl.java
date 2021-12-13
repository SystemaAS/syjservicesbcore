package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import no.systema.jservices.common.dao.SvtfiDao;
import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.model.dao.entities.EdimDao;
import no.systema.jservices.model.dao.mapper.EdimMapper;
import no.systema.jservices.model.dao.mapper.SvtfiMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * Sep 2021
 * 
 */
public class EdimDaoServicesImpl implements EdimDaoServices {
	private static Logger logger = LogManager.getLogger(EdimDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	@Override
	public List<EdimDao> getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		
		List<EdimDao> list = new ArrayList<EdimDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from edim ");
			sql.append(" where msn = ? ");
			list = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new EdimMapper());
			
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			list = null;
		}
		return list;
	}
	
	public List findByUuid(String uuid, StringBuffer errorStackTrace) {
		List<EdimDao> list = new ArrayList<EdimDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from edim ");
			sql.append(" where muuid = ? ");
			list = this.jdbcTemplate.query( sql.toString(), new Object[] { uuid }, new EdimMapper());
			
			
			
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
	 * This method sorts in a desc order.
	 * @param tuid
	 * @param errorStackTrace
	 * @return
	 */
	public List findByTuid(String tuid, StringBuffer errorStackTrace) {
		List<EdimDao> list = new ArrayList<EdimDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from edim ");
			sql.append(" where m1004 = ? ");
			sql.append(" order by mdt desc, mtm desc ");
			
			list = this.jdbcTemplate.query( sql.toString(), new Object[] { tuid }, new EdimMapper());
			//we are interested only in the first available record with MUUID = not empty hence emulating a select as if there was a muuid
			for(EdimDao record: list) {
				if (StringUtils.isNotEmpty(record.getMuuid())) {
					list.clear();
					list.add(record);
					break;
				}
			}
			//logger.warn(list.toString());
			
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
	 * Same as findByTuid but with MRN-nr (that is found only in SVIH)
	 * @param mrn
	 * @param errorStackTrace
	 * @return
	 */
	public List findByMrn(String mrn, StringBuffer errorStackTrace) {
		List<EdimDao> list = new ArrayList<EdimDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.* from edim a, svih b ");
			sql.append(" where b.svih_mrn =  ? ");
			sql.append(" and a.m1004 = b.svih_tuid "); 
			sql.append(" order by a.mdt desc, a.mtm desc ");
			
			list = this.jdbcTemplate.query( sql.toString(), new Object[] { mrn }, new EdimMapper());
			//we are interested only in the first available record with MUUID = not empty hence emulating a select as if there was a muuid
			for(EdimDao record: list) {
				if (StringUtils.isNotEmpty(record.getMuuid())) {
					list.clear();
					list.add(record);
					break;
				}
			}
			//logger.warn(list.toString());
			
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
		
		try{
			
			EdimDao dao = (EdimDao)daoObj;
			String today = new DateTimeManager().getCurrentDate_ISO("yyyMMdd");
			String now = new DateTimeManager().getCurrentDate_ISO("HHmmss");
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE edim SET msn = ?, mst = ?, mdt = ?, mtm = ?   ");
			sql.append(" WHERE  mavd = ? AND mtdn = ? AND msr = ? AND mdt = ? AND mtm = ? ");
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getMsn(), dao.getMst(), today, now,  
					//WHERE
					dao.getMavd(), dao.getMtdn(), dao.getMsr(), dao.getMdt(), dao.getMtm() } );
			
			//adjust edic.cmn counter only when it has been incremented
			if(retval>=0){
				if(StringUtils.isNotEmpty(dao.getMsn())) {
					retval = this.updateCounterEdicCsn(dao.getMsn(), errorStackTrace);
				}
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	/**
	 * INSERT 
	 */
	@Override
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			SvtfiDao svtfiDao = this.getInterchangeIds();
			String counterEdiCmn = this.getCounterEdicCmn().toString();
			logger.warn("DEBUG svtfi-->:" + svtfiDao.getSvtf_0004() + "-xxx-" + svtfiDao.getSvtf_0010());
			logger.warn("DEBUG edic-->" + counterEdiCmn);
			
			final EdimDao dao = (EdimDao) daoObj;
			dao.setM0004(svtfiDao.getSvtf_0004());
			dao.setM0010(svtfiDao.getSvtf_0010());
			
			final StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO edim (m0004,m0010,m0035,m0062,m0065,m0068,m1001,m1004,m1225,msn, ");
			sql.append(" mmn,msr,mst,mdt,mtm,mven,m0068a,m0068b,m0068c,m0068d, ");
			sql.append(" m0068e,m0068f,m2005b,m3039d,m3039e,m5004d,m1n07,m1n08,m9n01,mavd, ");
			sql.append(" mtdn,mffbnr,muuid  ) "); 
			
		
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?, "); 
			sql.append(" ?,?,?,?,?,?,?,?,?,?, "); 
			sql.append(" ?,?,? ) "); 
			
			logger.warn(dao.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getM0004(), dao.getM0010(), dao.getM0035(), dao.getM0062(), dao.getM0065(),dao.getM0068(), dao.getM1001(), dao.getM1004(), dao.getM1225(),dao.getMsn(), 
					counterEdiCmn, dao.getMsr(), dao.getMst(), dao.getMdt(), dao.getMtm(), dao.getMven(), dao.getM0068a(), dao.getM0068b(), dao.getM0068c(), dao.getM0068d(),
					dao.getM0068e(), dao.getM0068f(), dao.getM2005b(), dao.getM3039d(), dao.getM3039e(), dao.getM5004d(), dao.getM1n07(), dao.getM1n08(), dao.getM9n01(), dao.getMavd(),
					dao.getMtdn(),dao.getMffbnr(),dao.getMuuid()
					});
			
			//adjust edic.cmn counter
			if(retval>=0){
				retval = this.updateCounterEdicCmn(counterEdiCmn, errorStackTrace);
			}
			
				
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
	 * INSERT 
	 */
	@Override
	public int insertWhenInboundFile(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			String counterEdiCmn = this.getCounterEdicCmn().toString();
			String counterEdiCsn   = this.getCounterEdicCsn().toString();
					
			final EdimDao dao = (EdimDao) daoObj;
			//adjust
			dao.setMsn(counterEdiCsn);
			dao.setMmn(counterEdiCmn);
			
			final StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO edim (m0004,m0010,m0035,m0062,m0065,m0068,m1001,m1004,m1225,msn, ");
			sql.append(" mmn,msr,mst,mdt,mtm,mven,m0068a,m0068b,m0068c,m0068d, ");
			sql.append(" m0068e,m0068f,m2005b,m3039d,m3039e,m5004d,m1n07,m1n08,m9n01,mavd, ");
			sql.append(" mtdn,mffbnr,muuid  ) "); 
			
		
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?, "); 
			sql.append(" ?,?,?,?,?,?,?,?,?,?, "); 
			sql.append(" ?,?,? ) "); 
			logger.warn("insertWhenIboundFile...");
			logger.warn(dao.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getM0004(), dao.getM0010(), dao.getM0035(), dao.getM0062(), dao.getM0065(),dao.getM0068(), dao.getM1001(), dao.getM1004(), dao.getM1225(),dao.getMsn(), 
					counterEdiCmn, dao.getMsr(), dao.getMst(), dao.getMdt(), dao.getMtm(), dao.getMven(), dao.getM0068a(), dao.getM0068b(), dao.getM0068c(), dao.getM0068d(),
					dao.getM0068e(), dao.getM0068f(), dao.getM2005b(), dao.getM3039d(), dao.getM3039e(), dao.getM5004d(), dao.getM1n07(), dao.getM1n08(), dao.getM9n01(), dao.getMavd(),
					dao.getMtdn(),dao.getMffbnr(),dao.getMuuid()
					});
			
			//adjust edic.cmn counter
			if(retval>=0){
				retval = this.updateCounterEdicCmn(counterEdiCmn, errorStackTrace);
				retval = this.updateCounterEdicCsn(counterEdiCsn, errorStackTrace);
			}
			
				
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
		//N/A
		return 0;

	}                                  


	
	
	/**
	 * 
	 * @return
	 */
	private SvtfiDao getInterchangeIds() {
		SvtfiDao dao = null;
		
		try{
			String sql = " select * from svtfi ";
			List<SvtfiDao> list = jdbcTemplate.query( sql, new SvtfiMapper());
			logger.warn("SVTFI list size:" + list.size());
			for (SvtfiDao record : list) {
				dao = record;
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			
		}
		return dao;
	}

	private Integer getCounterEdicCmn() {
		Integer cmn = null;
		try{
			//cmn contains the LAST_USED så we have to increase it before we use it
			StringBuffer sql = new StringBuffer();
			sql.append("Select cmn + 1 from edic");
			cmn = (Integer)this.jdbcTemplate.queryForObject( sql.toString(), Integer.class);
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			
		}
		return cmn;
	}

	private int updateCounterEdicCmn(String counter, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE edic SET CMN = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { counter } );
		
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		return retval;
	}
	
	public Integer getCounterEdicCsn() {
		Integer cmn = null;
		try{
			//cmn contains the LAST_USED så we have to increase it before we use it
			StringBuffer sql = new StringBuffer();
			sql.append("Select csn + 1 from edic");
			cmn = (Integer)this.jdbcTemplate.queryForObject( sql.toString(), Integer.class);
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			
		}
		return cmn;
	}

	private int updateCounterEdicCsn(String counter, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE edic SET CSN = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { counter } );
		
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		return retval;
	}
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

	


}
