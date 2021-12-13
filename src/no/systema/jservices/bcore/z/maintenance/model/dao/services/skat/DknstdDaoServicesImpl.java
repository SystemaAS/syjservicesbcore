package no.systema.jservices.bcore.z.maintenance.model.dao.services.skat;
import java.io.Writer;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.skat.DknstdMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.skat.DknstdDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Apr 24, 2017
 * 
 * 
 */
public class DknstdDaoServicesImpl implements DknstdDaoServices {
	private static Logger logger = LogManager.getLogger(DknstdDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<DknstdDao> retval = new ArrayList<DknstdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new DknstdMapper());
			
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
		List<DknstdDao> retval = new ArrayList<DknstdDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" and tiavd = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new DknstdMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}
		return retval;
	}
	

	@Override
	public List getNctsImportList(StringBuffer errorStackTrace) {
		List<DknstdDao> retval = new ArrayList<DknstdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			//sql.append(" and thdk NOT in ('ENTRY','EXIT','SS')  ");

			retval = this.jdbcTemplate.query( sql.toString(), new DknstdMapper());
			
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
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			
			DknstdDao dao = (DknstdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO dknstd ( tiavd, tist, tisg, titdn, tidt, tienkl, titrnr, tign, tignsk,  ");
			sql.append(" tialsk, tials, tialss, tiglsk, tiacts, tiskb, titsb, titarf, tiws, ");
			sql.append(" tikn, tina, tiad1, tipn, tips, tilk, tisk, titin, s0004, s0010,");
			sql.append(" s0035, dkns_0035, s0026, tidk, tikna, tinaa, tiada1, tipna, tipsa, ");
			sql.append(" tilka, tiska, titina, tikns, tinas, tiads1, tipns, tipss, tilks, tisks,  ");
			sql.append(" titins, tiknk, tinak, tiadk1, tipnk, tipsk, tilkk, tiskk, titink, tiblk,  ");
			sql.append(" tialk, titaid, titask, titalk, tikdc, titrdt, tilstl, tivpos, tintk, tivkb,");
			sql.append(" ticats, tictl, tidant, tidfkd, tidfsk, tituid, tisgdk, tisgme, tisgut, tisgid,  ");
			sql.append(" tisgdt, tibyte ");
			sql.append(" )" );
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ? ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTiavd(), 
				dao.getTist(), dao.getTisg(), dao.getTitdn(), dao.getTidt(), dao.getTienkl(), dao.getTitrnr(), dao.getTign(), dao.getTignsk(),	
				dao.getTialsk(), dao.getTials(), dao.getTialss(), dao.getTiglsk(), dao.getTiacts(), dao.getTiskb(), dao.getTitsb(), dao.getTitarf(), dao.getTiws(), 
				dao.getTikn(), dao.getTina(), dao.getTiad1(), dao.getTipn(), dao.getTips(), dao.getTilk(), dao.getTisk(), dao.getTitin(), dao.getS0004(), dao.getS0010(),
				dao.getS0035(), dao.getDkns_0035(), dao.getS0026(), dao.getTidk(), dao.getTikna(), dao.getTinaa(), dao.getTiada1(), dao.getTipna(), dao.getTipsa(),
				dao.getTilka(),dao.getTiska(),dao.getTitina(),dao.getTikns(),dao.getTinas(),dao.getTiads1(),dao.getTipns(),dao.getTipss(), dao.getTilks(), dao.getTisks(),
				dao.getTitins(), dao.getTiknk(), dao.getTinak(), dao.getTiadk1(), dao.getTipnk(), dao.getTipsk(), dao.getTilkk(), dao.getTiskk(), dao.getTitink(), dao.getTiblk(), 
				dao.getTialk(), dao.getTitaid(), dao.getTitask(), dao.getTitalk(), dao.getTikdc(), dao.getTitrdt(), dao.getTilstl(), dao.getTivpos(), dao.getTintk(),  dao.getTivkb(), 
				dao.getTicats(), dao.getTictl(), dao.getTidant(), dao.getTidfkd(), dao.getTidfsk(), dao.getTituid(), dao.getTisgdk(), dao.getTisgme(), dao.getTisgut(), dao.getTisgid(),
				dao.getTisgdt(), dao.getTibyte()
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
			
			DknstdDao dao = (DknstdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE dknstd SET tist = ?, tisg = ?, titdn = ?, tidt = ?, tienkl = ?, titrnr = ?, tign = ?, tignsk = ?, ");
			sql.append(" tialsk = ?, tials = ?, tialss = ?, tiglsk = ?, tiacts = ?, tiskb = ?, titsb = ?, titarf = ?, tiws = ?, ");
			sql.append(" tikn = ?, tina = ?, tiad1 = ?, tipn = ?, tips = ?, tilk = ?, tisk = ?, titin = ?, s0004 = ?, s0010 = ?, ");
			sql.append(" s0035 = ?, dkns_0035 = ?, s0026 = ?, tidk = ?, tikna = ?, tinaa = ?, tiada1 = ?, tipna = ?, tipsa = ?, ");
			sql.append(" tilka = ?, tiska = ?, titina = ?, tikns = ?, tinas = ?, tiads1 = ?, tipns = ?, tipss = ?, tilks = ?, tisks = ?, ");
			sql.append(" titins = ?, tiknk = ?, tinak = ?, tiadk1 = ?, tipnk = ?, tipsk = ?, tilkk = ?, tiskk = ?, titink = ?, tiblk = ?, ");
			sql.append(" tialk = ?, titaid = ?, titask = ?, titalk = ?, tikdc = ?, titrdt = ?, tilstl = ?, tivpos = ?, tintk = ?, tivkb = ?, ");
			sql.append(" ticats = ?, tictl = ?, tidant = ?, tidfkd = ?, tidfsk = ?, tituid = ?, tisgdk = ?, tisgme = ?, tisgut = ?, tisgid = ?, ");
			sql.append(" tisgdt = ?, tibyte = ? ");
			//WHERE
			sql.append(" WHERE tiavd = ? ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getTist(), dao.getTisg(), dao.getTitdn(), dao.getTidt(), dao.getTienkl(), dao.getTitrnr(), dao.getTign(), dao.getTignsk(),
				dao.getTialsk(), dao.getTials(), dao.getTialss(), dao.getTiglsk(), dao.getTiacts(), dao.getTiskb(), dao.getTitsb(), dao.getTitarf(), dao.getTiws(),
				dao.getTikn(), dao.getTina(), dao.getTiad1(), dao.getTipn(), dao.getTips(), dao.getTilk(), dao.getTisk(), dao.getTitin(), 
				dao.getS0004(),dao.getS0010(),dao.getS0035(),dao.getDkns_0035(), dao.getS0026(), dao.getTidk(), dao.getTikna(), dao.getTinaa(), dao.getTiada1(), dao.getTipna(), dao.getTipsa(),
				dao.getTilka(), dao.getTiska(), dao.getTitina(), dao.getTikns(), dao.getTinas(), dao.getTiads1(), dao.getTipns(), dao.getTipss(), dao.getTilks(), dao.getTisks(), 
				dao.getTitins(), dao.getTiknk(), dao.getTinak(), dao.getTiadk1(), dao.getTipnk(), dao.getTipsk(), dao.getTilkk(), dao.getTiskk(), dao.getTitink(), dao.getTiblk(),
				dao.getTialk(), dao.getTitaid(), dao.getTitask(), dao.getTitalk(), dao.getTikdc(), dao.getTitrdt(), dao.getTilstl(), dao.getTivpos(), dao.getTintk(), dao.getTivkb(),
				dao.getTicats(), dao.getTictl(), dao.getTidant(), dao.getTidfkd(), dao.getTidfsk(), dao.getTituid(), dao.getTisgdk(), dao.getTisgme(), dao.getTisgut(), dao.getTisgid(),
				dao.getTisgdt(), dao.getTibyte(), 
				//WHERE condition
				dao.getTiavd() } );

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
		
			DknstdDao dao = (DknstdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from dknstd ");
			sql.append(" WHERE tiavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTiavd() } );
			
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
		sql.append(" from dknstd a, kodta b, cundf c  ");
		sql.append(" where a.tiavd = b.koaavd ");
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
