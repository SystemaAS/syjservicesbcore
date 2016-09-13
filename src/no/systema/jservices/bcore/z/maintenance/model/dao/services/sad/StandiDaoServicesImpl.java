package no.systema.jservices.bcore.z.maintenance.model.dao.services.sad;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.sad.StandiMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sad.StandiDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 23, 2016
 * 
 * 
 */
public class StandiDaoServicesImpl implements StandiDaoServices {
	private static Logger logger = Logger.getLogger(StandiDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<StandiDao> retval = new ArrayList<StandiDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			
			retval = this.jdbcTemplate.query( sql.toString(), new StandiMapper());
			
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
		List<StandiDao> retval = new ArrayList<StandiDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" and a.siavd = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new StandiMapper());
			
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
			
			StandiDao dao = (StandiDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO standi ( siavd, sidl, sia4, sitdn, siekst, sitolk, s3039e, s3039eo1, s3039eo2, s0026, s0035, s0004, s0010, silv2, ");
			sql.append(" sidty, sidp, siski, sikddk, sikns, sinas, siads1, siads2, siads3, siknk, sinak, sirg, siadk1, siadk2, siadk3, siktc, sikta, siktb, ");
			sql.append(" sival3, sitst, sibel3, sivku, sift1, sift2, sift3, sift4, silka, sikdc, sitrid, silkt, sitrm, sign, sipos, silv, silvt, ");
			sql.append(" sikdls, sils, siftg2, sibel1, sival1, sibel2, sival2, si07, sivkb, sintk, sikdh ");
			sql.append(" )" );
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,   ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");
			sql.append(" )" );
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSiavd(), dao.getSidl(), dao.getSia4(), dao.getSitdn(), dao.getSiekst(), dao.getSitolk(), 
					dao.getS3039e(), dao.getS3039eo1(), dao.getS3039eo2(), dao.getS0026(), dao.getS0035(), dao.getS0004(), dao.getS0010(), dao.getSilv2(),
					dao.getSidty(), dao.getSidp(), dao.getSiski(), dao.getSikddk(), dao.getSikns(), dao.getSinas(), dao.getSiads1(), dao.getSiads2(), dao.getSiads3(),
					dao.getSiknk(), dao.getSinak(), dao.getSirg(), dao.getSiadk1(), dao.getSiadk2(), dao.getSiadk3(), dao.getSiktc(), dao.getSikta(), dao.getSiktb(),
					dao.getSival3(), dao.getSitst(), dao.getSibel3(), dao.getSivku(), dao.getSift1(), dao.getSift2(), dao.getSift3(), dao.getSift4(),
					dao.getSilka(), dao.getSikdc(), dao.getSitrid(), dao.getSilkt(), dao.getSitrm(), dao.getSign(), dao.getSipos(), dao.getSilv(), dao.getSilvt(),
					dao.getSikdls(), dao.getSils(), dao.getSiftg2(), dao.getSibel1(), dao.getSival1(), dao.getSibel2(), dao.getSival2(), dao.getSi07(),
					dao.getSivkb(), dao.getSintk(), dao.getSikdh()
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
			
			StandiDao dao = (StandiDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			//sql.append(" UPDATE standi SET sidl = ? ");
			sql.append(" UPDATE standi SET sidl = ?, sia4 = ?, sitdn = ?, siekst = ?, sitolk = ?, s3039e = ?, s3039eo1 = ?, s3039eo2 = ?, s0026 = ?, " );
			sql.append(" s0035 = ?, s0004 = ?, s0010 = ?, silv2 = ?, sidty = ?, sidp = ?, siski = ?, sikddk = ?, sikns = ?, sinas = ?, siads1 = ?, ");
			sql.append(" siads2 = ?, siads3 = ?, siknk = ?, sinak = ?, sirg = ?, siadk1 = ?, siadk2 = ?, siadk3 = ?, siktc = ?, sikta = ?, siktb = ?,  ");
			sql.append(" sinad = ?, sitlf = ?, sidst = ?, sidt = ?, sival3 = ?, sitst = ?, sibel3 = ?, sivku = ?, sift1 = ?, sift2 = ?, sift3 = ?, sift4 = ?,  ");
			sql.append(" silka = ?, sikdc = ?, sitrid = ?, silkt = ?, sitrm = ?, sign = ?, sipos = ?, silv = ?, silvt = ?, sikdls = ?, sils = ?, siftg2 = ?, ");
			sql.append(" sibel1 = ?, sival1 = ? , sibel2 = ?, sival2 = ?, si07 = ?, sivkb = ?, sintk = ?, sikdh = ? ");
			sql.append(" WHERE siavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSidl(), dao.getSia4(), dao.getSitdn(), dao.getSiekst(), dao.getSitolk(),
				dao.getS3039e(), dao.getS3039eo1(), dao.getS3039eo2(), dao.getS0026(), dao.getS0035(), dao.getS0004(), dao.getS0010(), dao.getSilv2(), 
				dao.getSidty(), dao.getSidp(), dao.getSiski(), dao.getSikddk(), dao.getSikns(), dao.getSinas(), dao.getSiads1(), dao.getSiads2(), dao.getSiads3(),
				dao.getSiknk(), dao.getSinak(), dao.getSirg(), dao.getSiadk1(), dao.getSiadk2(), dao.getSiadk3(), dao.getSiktc(), dao.getSikta(), dao.getSiktb(),
				dao.getSinad(), dao.getSitlf(), dao.getSidst(), dao.getSidt(), dao.getSival3(), dao.getSitst(), dao.getSibel3(), dao.getSivku(),  
				dao.getSift1(), dao.getSift2(), dao.getSift3(), dao.getSift4(),
				dao.getSilka(), dao.getSikdc(), dao.getSitrid(), dao.getSilkt(), dao.getSitrm(), dao.getSign(), dao.getSipos(), dao.getSilv(), dao.getSilvt(),
				dao.getSikdls(), dao.getSils(), dao.getSiftg2(), dao.getSibel1(), dao.getSival1(), dao.getSibel2(), dao.getSival2(), dao.getSi07(),
				dao.getSivkb(), dao.getSintk(), dao.getSikdh(),
				//WHERE condition
				dao.getSiavd() } );
			
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
		
			StandiDao dao = (StandiDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from standi ");
			sql.append(" WHERE siavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSiavd() } );
			
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
		
		sql.append(" select a.*, a.SIDTØ sidto, b.koanvn, b.koaknr, c.syrg ");
		sql.append(" from standi a, kodta b, cundf c  ");
		sql.append(" where a.siavd = b.koaavd ");
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
