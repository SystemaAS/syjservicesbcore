package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.UtskrsMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtpUtskrsDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 12, 2016
 * 
 */
public class UtskrsDaoServicesImpl implements UtskrsDaoServices {
	private static Logger logger = LoggerFactory.getLogger(UtskrsDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtpUtskrsDao> retval = new ArrayList<KodtpUtskrsDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			//logger.info("AAA");
			retval = this.jdbcTemplate.query( sql.toString(), new UtskrsMapper());
			
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
		List<KodtpUtskrsDao> retval = new ArrayList<KodtpUtskrsDao>();
		/* N/A 
		try{
			StringBuffer sql = new StringBuffer();
			logger.info(retval);
			sql.append(this.getSELECT_CLAUSE());
			//WHERE
			sql.append(" where b.utpnr = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtpUtskrsMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}*/
		return retval;
	}
	

	/**
	 * 
	 * @return
	 */
	private String getSELECT_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select b.utptxt utptxt, b.utpty utpty, CHAR(b.utpnr) utpnr, CHAR(b.utpl) utpl, CHAR(b.utpk) utpk, b.uthead uthead, b.utpfm utpfm, b.utplas utplas, CHAR(b.utpcpi) utpcpi, ");
		sql.append(" CHAR(b.utplpi) utplpi, CHAR(b.utplpp) utplpp, CHAR(b.utpcpl) utpcpl, b.utpdraw utpdraw, b.utpoutb utpoutb, b.utpfor1 utpfor1, b.utpfor2 utpfor2, b.utpdupl utpdupl, b.utpfov1 utpfov1, ");
		sql.append(" b.utpfov2 utpfov2, b.utpfov3 utpfov3, b.utpfov4 utpfov4, b.utpbov1 utpbov1, b.utpbov2 utpbov2, b.utpbov3 utpbov3, b.utpbov4 utpbov4, b.utpbov5 utpbov5, ");
		sql.append(" b.utpcopi utpcopi, b.utphold utphold, b.utpsave utpsave, b.utpfrmp utpfrmp, b.utpbamp utpbamp, b.utpfrmf utpfrmf, b.utpbamf utpbamf ");
		
		sql.append(" from utskrs b ");
		return sql.toString();
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
			/* N/A
			KodtaDao dao = (KodtaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO firm ( koaavd, navsg ) ");
			sql.append(" VALUES ( ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKoaavd(), dao.getNavsg() } );
			*/
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
			/* N/A
			KodtpUtskrsDao dao = (KodtpUtskrsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE kodtp SET kopty = ?, kopnvn = ?, kophea = ?, koplas = ?, koplpi = ?, kopfm = ?, kopdraw = ?, kopoutb = ? ");
			sql.append(" WHERE kopavd = ? ");
			sql.append(" AND koplnr = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKopty(), dao.getKopnvn(), dao.getKophea(), dao.getKoplas(), dao.getKoplpi(), dao.getKopfm(), 
					dao.getKopdraw(), dao.getKopoutb(), 
					//WHERE
					dao.getKopavd(), dao.getKoplnr() } );
			
			*/
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
			/* N/A
			KodtaDao dao = (KodtaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from navavd ");
			sql.append(" WHERE koaavd = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKoaavd() } );
			*/
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
