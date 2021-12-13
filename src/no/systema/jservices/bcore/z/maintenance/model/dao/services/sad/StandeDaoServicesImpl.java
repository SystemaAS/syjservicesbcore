package no.systema.jservices.bcore.z.maintenance.model.dao.services.sad;
import java.io.Writer;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.sad.StandeMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.StandeDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Sep 16, 2016
 * 
 * 
 */
public class StandeDaoServicesImpl implements StandeDaoServices {
	private static Logger logger = LogManager.getLogger(StandeDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<StandeDao> retval = new ArrayList<StandeDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new StandeMapper());
			
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
		List<StandeDao> retval = new ArrayList<StandeDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" and a.seavd = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new StandeMapper());
			
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
			
			StandeDao dao = (StandeDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO stande ( seavd, sedl, sea4, setdn, seekst, setolk, s3039e, s3039eo1, s3039eo2, s0026, ");
			sql.append(" s0035, s0004, s0010, selv2, sedty, sedp, seski, sekddk, sekns, senas, seads1, seads2, seads3, seknk, senak, serg, seadk1, seadk2, seadk3, ");
			sql.append(" senad, setlf, sedst, sedt, seval1, setst, sebel1, sevku, seft1, seft2, seft3, ");
			sql.append(" selka, selkb, sekdc, setrid, selkt, setrm, segn, sepos, sekdh, sekdft, selv, selvt, ");
			sql.append(" sekdls, sels, sevkb, sentk ");
			sql.append(" )" );
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ? ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSeavd(), dao.getSedl(), dao.getSea4(), dao.getSetdn(), dao.getSeekst(), dao.getSetolk(),
				dao.getS3039e(), dao.getS3039eo1(), dao.getS3039eo2(), dao.getS0026(),  dao.getS0035(), dao.getS0004(), dao.getS0010(), 
				dao.getSelv2(), dao.getSedty(), dao.getSedp(), dao.getSeski(), dao.getSekddk(), dao.getSekns(), dao.getSenas(), dao.getSeads1(), 
				dao.getSeads2(), dao.getSeads3(), dao.getSeknk(), dao.getSenak(), dao.getSerg(), dao.getSeadk1(), dao.getSeadk2(), dao.getSeadk3(),
				dao.getSenad(), dao.getSetlf(), dao.getSedst(), dao.getSedt(), dao.getSeval1(), dao.getSetst(), dao.getSebel1(), 
				dao.getSevku(), dao.getSeft1(), dao.getSeft2(), dao.getSeft3(),dao.getSelka(), dao.getSelkb(), dao.getSekdc(), dao.getSetrid(), dao.getSelkt(), 
				dao.getSetrm(), dao.getSegn(), dao.getSepos(), dao.getSekdh(), dao.getSekdft(), dao.getSelv(), dao.getSelvt(), 
				dao.getSekdls(), dao.getSels(), dao.getSevkb(), dao.getSentk() 
				 
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
			
			StandeDao dao = (StandeDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE stande SET sedl = ?, sea4 = ?, setdn = ?, seekst = ?, setolk = ?, s3039e = ?, s3039eo1 = ?, s3039eo2 = ?, s0026 = ?, " );
			sql.append(" s0035 = ?, s0004 = ?, s0010 = ?, selv2 = ?, sedty = ?, sedp = ?, seski = ?, sekddk = ?, sekns = ?, senas = ?, seads1 = ?, ");
			sql.append(" seads2 = ?, seads3 = ?, seknk = ?, senak = ?, serg = ?, seadk1 = ?, seadk2 = ?, seadk3 = ?,  ");
			sql.append(" senad = ?, setlf = ?, sedst = ?, sedt = ?, seval1 = ?, setst = ?, sebel1 = ?, sevku = ?, seft1 = ?, seft2 = ?, seft3 = ?, ");
			sql.append(" selka = ?, selkb = ?, sekdc = ?, setrid = ?, selkt = ?, setrm = ?, segn = ?, sepos = ?, sekdh = ?, sekdft = ?, selv = ?, selvt = ?, ");
			sql.append(" sekdls = ?, sels = ?, sevkb = ?, sentk = ? ");
			sql.append(" WHERE seavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSedl(), dao.getSea4(), dao.getSetdn(), dao.getSeekst(), dao.getSetolk(),
				dao.getS3039e(), dao.getS3039eo1(), dao.getS3039eo2(), dao.getS0026(),  dao.getS0035(), dao.getS0004(), dao.getS0010(), 
				dao.getSelv2(), dao.getSedty(), dao.getSedp(), dao.getSeski(), dao.getSekddk(), dao.getSekns(), dao.getSenas(), dao.getSeads1(), 
				dao.getSeads2(), dao.getSeads3(), dao.getSeknk(), dao.getSenak(), dao.getSerg(), dao.getSeadk1(), dao.getSeadk2(), dao.getSeadk3(),
				dao.getSenad(), dao.getSetlf(), dao.getSedst(), dao.getSedt(), dao.getSeval1(), dao.getSetst(), dao.getSebel1(), 
				dao.getSevku(), dao.getSeft1(), dao.getSeft2(), dao.getSeft3(),dao.getSelka(), dao.getSelkb(), dao.getSekdc(), dao.getSetrid(), dao.getSelkt(), 
				dao.getSetrm(), dao.getSegn(), dao.getSepos(), dao.getSekdh(), dao.getSekdft(), dao.getSelv(), dao.getSelvt(), 
				dao.getSekdls(), dao.getSels(), dao.getSevkb(), dao.getSentk(), 
				//WHERE condition
				dao.getSeavd() } );
			
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
		
			StandeDao dao = (StandeDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from stande ");
			sql.append(" WHERE seavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSeavd() } );
			
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
		
		sql.append(" select a.*, b.koanvn, b.koaknr, c.syrg ");
		sql.append(" from stande a, kodta b, cundf c  ");
		sql.append(" where a.seavd = b.koaavd ");
		sql.append(" and b.koaknr = c.kundnr ");
		sql.append(" and b.koafir = c.firma ");
		
		
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
