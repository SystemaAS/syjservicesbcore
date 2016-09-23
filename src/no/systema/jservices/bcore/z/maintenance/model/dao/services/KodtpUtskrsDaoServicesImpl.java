package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;
import java.sql.*;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.KodtpUtskrsMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtpUtskrsDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 9, 2016
 * 
 */
public class KodtpUtskrsDaoServicesImpl implements KodtpUtskrsDaoServices {
	private static Logger logger = Logger.getLogger(KodtpUtskrsDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtpUtskrsDao> retval = new ArrayList<KodtpUtskrsDao>();
		// N/A
		return retval;
	}
	/**
	 * 
	 */
	public List getList(String id, StringBuffer errorStackTrace){
		List<KodtpUtskrsDao> retval = new ArrayList<KodtpUtskrsDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where a.kopavd = ?  ");
			sql.append(" and a.koplnr = b.utpnr  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtpUtskrsMapper());
			/* DEBUG
			for(KodtpUtskrsDao record: retval){
				logger.info(record.getKoplnr());
			}*/
			
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
		//N/A 
		
		return retval;
	}
	/**
	 * 
	 */
	public List findById (String id, String lnr, StringBuffer errorStackTrace ){
		List<KodtpUtskrsDao> retval = new ArrayList<KodtpUtskrsDao>();
		try{
			StringBuffer sql = new StringBuffer();
			logger.info(retval);
			sql.append(this.getSELECT_CLAUSE());
			//WHERE
			sql.append(" where a.kopavd = ?  ");
			sql.append(" and a.koplnr = ?  ");
			sql.append(" and a.koplnr = b.utpnr  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id, lnr }, new KodtpUtskrsMapper());
			
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
	 * @return
	 */
	private String getSELECT_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.kopuni kopuni, CHAR(a.kopavd) kopavd, CHAR(a.koplnr) koplnr, CHAR(a.kopcpl) kopcpl, CHAR(a.kopcpi) kopcpi, CHAR(a.koplpp) koplpp, CHAR(a.koplpi) koplpi, ");
		sql.append(" a.kopnvn kopnvn, a.kopty kopty, a.kophea kophea, a.kopfm kopfm, a.koplas koplas, a.kopdraw kopdraw, a.kopoutb kopoutb, a.kopfor1 kopfor1, a.kopfor2 kopfor2, ");
		sql.append(" a.kopdupl kopdupl, a.kopfov1 kopfov1, a.kopfov2 kopfov2, a.kopfov3 kopfov3, a.kopfov4 kopfov4, a.kopbov1 kopbov1, a.kopbov2 kopbov2, a.kopbov3 kopbov3,  ");
		sql.append(" a.kopbov4 kopbov4, a.kopbov5 kopbov5, a.kopcopi kopcopi, a.kophold kophold, a.kopsave kopsave, a.kopfrmp kopfrmp, a.kopbamp kopbamp, a.kopfrmf kopfrmf, ");
		sql.append(" a.kopbamf kopbamf, ");
		sql.append(" b.utptxt utptxt, b.utpty utpty, CHAR(b.utpnr) utpnr, CHAR(b.utpl) utpl, CHAR(b.utpk) utpk, b.uthead uthead, b.utpfm utpfm, b.utplas utplas, CHAR(b.utpcpi) utpcpi, ");
		sql.append(" CHAR(b.utplpi) utplpi, CHAR(b.utplpp) utplpp, CHAR(b.utpcpl) utpcpl, b.utpdraw utpdraw, b.utpoutb utpoutb, b.utpfor1 utpfor1, b.utpfor2 utpfor2, b.utpdupl utpdupl, b.utpfov1 utpfov1, ");
		sql.append(" b.utpfov2 utpfov2, b.utpfov3 utpfov3, b.utpfov4 utpfov4, b.utpbov1 utpbov1, b.utpbov2 utpbov2, b.utpbov3 utpbov3, b.utpbov4 utpbov4, b.utpbov5 utpbov5, ");
		sql.append(" b.utpcopi utpcopi, b.utphold utphold, b.utpsave utpsave, b.utpfrmp utpfrmp, b.utpbamp utpbamp, b.utpfrmf utpfrmf, b.utpbamf utpbamf ");
		
		sql.append(" from kodtp a, utskrs b ");
		
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
			
			KodtpUtskrsDao dao = (KodtpUtskrsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodtp ( kopuni, kopavd, koplnr, kopty, kopnvn, kopcpi, koplpi, koplpp, kopcpl ) ");
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKopuni(), dao.getKopavd(), 
								dao.getUtpnr().trim(), dao.getUtpty(), dao.getKopnvn(),
								dao.getUtpcpi().trim(), dao.getUtplpi().trim(), dao.getUtplpp().trim(), dao.getUtpcpl().trim()  } );
			
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
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int insertBatch(final List<KodtpUtskrsDao> utskrsListTarget, StringBuffer errorStackTrace){
		int retval = 0; 
		try{
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodtp ( kopuni, kopavd, koplnr, kopty, kopnvn, kopcpi, koplpi, koplpp, kopcpl ) ");
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			
			
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {

		        @Override
		        public void setValues(PreparedStatement ps, int i)
		            throws SQLException {

		        	KodtpUtskrsDao dao = utskrsListTarget.get(i);
		            ps.setString(1, dao.getKopuni());
		            ps.setString(2, dao.getKopavd());
		            ps.setString(3, dao.getUtpnr().trim());
		            ps.setString(4, dao.getUtpty());
		            ps.setString(5, dao.getKopnvn());
		            ps.setString(6, dao.getUtpcpi().trim());
		            ps.setString(7, dao.getUtplpi().trim());
		            ps.setString(8, dao.getUtplpp().trim());
		            ps.setString(9, dao.getUtpcpl().trim());
		            
		        }

		        @Override
		        public int getBatchSize() {
		            return utskrsListTarget.size();
		        }
		    });
			
			
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
		//N/A
		
		return retval;
	}
	
	/**
	 * Updates KODTP (faste data (Dokumenter - Del 2)
	 */
	public int updateChild(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			KodtpUtskrsDao dao = (KodtpUtskrsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE kodtp SET kopty = ?, kopnvn = ?, kophea = ?, koplas = ?, koplpi = ?, kopfm = ?, kopdraw = ?, kopoutb = ?, ");
			sql.append(" kopcopi = ?, kopfov1 = ? ");
			
			sql.append(" WHERE kopavd = ? ");
			sql.append(" AND koplnr = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] {
					dao.getKopty(), dao.getKopnvn(), dao.getKophea(), dao.getKoplas(), dao.getKoplpi(), dao.getKopfm(), 
					dao.getKopdraw(), dao.getKopoutb(), dao.getKopcopi(), dao.getKopfov1(), 
					//WHERE
					dao.getKopavd(), dao.getKoplnr() 
					
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
	 * DELETE
	 */
	
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			/* TODO
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
