package no.systema.jservices.bcore.z.maintenance.model.dao.services.sad;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.sad.TrustdfvMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.TrustdfvDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Oct 3, 2016
 * 
 * 
 */
public class TrustdfvDaoServicesImpl implements TrustdfvDaoServices {
	private static Logger logger = LoggerFactory.getLogger(TrustdfvDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<TrustdfvDao> retval = new ArrayList<TrustdfvDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new TrustdfvMapper());
			
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
	public List findById (String id, StringBuffer errorStackTrace ){
		List<TrustdfvDao> retval = new ArrayList<TrustdfvDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" and d.thavd = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new TrustdfvMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}
		return retval;
	}
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			
			TrustdfvDao dao = (TrustdfvDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO trustdfv ( thavd, thsik, thdta, thtma, thkref, thtref, thspom, thtkbm, thlosd, thlosdsk, ");
			sql.append(" thlkr1, thlkr2, thlkr3, thlkr4, thlkr5, thlkr6, thlkr7, thlkr8, ");
			sql.append(" thknss, thnass, thtinss, thadss1, thskss, thpsss, thpnss, thlkss, ");
			sql.append(" thknks, thnaks, thtinks, thadks1, thskks, thpsks, thpnks, thlkks, ");
			sql.append(" thknt, thnat, thtint, thadt1, thskt, thpst, thpnt, thlkt ");
			sql.append(" )" );
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ");
			sql.append("  ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append("  ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append("  ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append("  ?, ?, ?, ?, ?, ?, ?, ? ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getThavd(),
				dao.getThsik(), dao.getThdta(), dao.getThtma(), dao.getThkref(), dao.getThtref(), dao.getThspom(), dao.getThtkbm(), dao.getThlosd(), dao.getThlosdsk(),
				dao.getThlkr1(), dao.getThlkr2(), dao.getThlkr3(), dao.getThlkr4(), dao.getThlkr5(), dao.getThlkr6(), dao.getThlkr7(), dao.getThlkr8(),
				dao.getThknss(), dao.getThnass(), dao.getThtinss(), dao.getThadss1(), dao.getThskss(), dao.getThpsss(), dao.getThpnss(), dao.getThlkss(),
				dao.getThknks(), dao.getThnaks(), dao.getThtinks(), dao.getThadks1(), dao.getThskks(), dao.getThpsks(), dao.getThpnks(), dao.getThlkks(),
				dao.getThknt(), dao.getThnat(), dao.getThtint(), dao.getThadt1(), dao.getThskt(), dao.getThpst(), dao.getThpnt(), dao.getThlkt()
				 } );
			
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
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			
			TrustdfvDao dao = (TrustdfvDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			
			sql.append(" UPDATE trustdfv SET thsik = ?, thdta = ?, thtma = ?, thkref = ?, thtref = ?, thspom = ?, thtkbm = ?, thlosd = ?, thlosdsk = ?,  ");
			sql.append(" thlkr1 = ?, thlkr2 = ?, thlkr3 = ?, thlkr4 = ?, thlkr5 = ?, thlkr6 = ?, thlkr7 = ?, thlkr8 = ?, ");
			sql.append(" thknss = ?, thnass = ?, thtinss = ?, thadss1 = ?, thskss = ?, thpsss = ?, thpnss = ?, thlkss = ?, ");
			sql.append(" thknks = ?, thnaks = ?, thtinks = ?, thadks1 = ?, thskks = ?, thpsks = ?, thpnks = ?, thlkks = ?, ");
			sql.append(" thknt = ?, thnat = ?, thtint = ?, thadt1 = ?, thskt = ?, thpst = ?, thpnt = ?, thlkt = ? ");
			//WHERE condition
			sql.append(" WHERE thavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getThsik(), dao.getThdta(), dao.getThtma(), dao.getThkref(), dao.getThtref(), dao.getThspom(), dao.getThtkbm(), dao.getThlosd(), dao.getThlosdsk(),
				dao.getThlkr1(), dao.getThlkr2(), dao.getThlkr3(), dao.getThlkr4(), dao.getThlkr5(), dao.getThlkr6(), dao.getThlkr7(), dao.getThlkr8(),
				dao.getThknss(), dao.getThnass(), dao.getThtinss(), dao.getThadss1(), dao.getThskss(), dao.getThpsss(), dao.getThpnss(), dao.getThlkss(),
				dao.getThknks(), dao.getThnaks(), dao.getThtinks(), dao.getThadks1(), dao.getThskks(), dao.getThpsks(), dao.getThpnks(), dao.getThlkks(),
				dao.getThknt(), dao.getThnat(), dao.getThtint(), dao.getThadt1(), dao.getThskt(), dao.getThpst(), dao.getThpnt(), dao.getThlkt(),
				//WHERE condition
				dao.getThavd() } );
			
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
	 * DELETE
	 */
	
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
		
			TrustdfvDao dao = (TrustdfvDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from trustdfv ");
			sql.append(" WHERE thavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getThavd() } );
			
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
	 * 
	 * @return
	 */
	private String getSELECT_FROM_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select d.* ");
		sql.append(" from trustd a, kodta b, cundf c, trustdfv d  ");
		sql.append(" where a.thavd = b.koaavd ");
		sql.append(" and b.koaknr = c.kundnr ");
		sql.append(" and b.koafir = c.firma ");
		sql.append(" and a.thavd = d.thavd ");
		
		
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
