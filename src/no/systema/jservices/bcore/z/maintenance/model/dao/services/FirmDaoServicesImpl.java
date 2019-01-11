


package no.systema.jservices.bcore.z.maintenance.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import no.systema.jservices.bcore.z.maintenance.model.dao.mapper.FirmMapper;
import no.systema.jservices.bcore.z.maintenance.model.dao.entities.FirmDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 4, 2016
 * 
 */
public class FirmDaoServicesImpl implements FirmDaoServices {
	private static Logger logger = Logger.getLogger(FirmDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<FirmDao> retval = new ArrayList<FirmDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" fetch first 1 rows only");
			
			retval = this.jdbcTemplate.query( sql.toString(), new FirmMapper());
			
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
		List<FirmDao> retval = new ArrayList<FirmDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			//WHERE
			sql.append(" where a.fifirm = ?  ");
			sql.append(" fetch first 1 rows only");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new FirmMapper());
			
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
		sql.append(" select a.*, b.*, c.*, d.*, e.*, f.*, g.*, z.* ");
		sql.append(" from FIRM AS a ");
		sql.append(" full outer join FIRFB AS b ");
		sql.append(" on a.fifirm = b.fifirm ");
		sql.append(" full outer join FIRMKOS AS c ");
		sql.append(" on a.fifirm = c.fifirm ");
		sql.append(" full outer join FIRKU AS d ");
		sql.append(" on a.fifirm = d.fifirm ");
		sql.append(" full outer join FIRSTA AS e ");
		sql.append(" on a.fifirm = e.fifirm ");
		sql.append(" full outer join FIRTR AS f ");
		sql.append(" on a.fifirm = f.fifirm ");
		sql.append(" left outer join FIRML1 AS g ");
		sql.append(" on a.fifirm = g.l1firm ");
		// no join ?
		sql.append(" inner join KODTV AS z "); //no key in this table
		sql.append(" on z.kovuni = 'V' ");
		//NOT TRUSTABLE --> sql.append(" and z.kovavd = '0'");
		//sql.append(" fetch first 1 rows only");
		
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
			/* TODO
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
	public int update(final Object daoObj, final StringBuffer errorStackTrace){
		int retval = 0;
		try{
			//DEBUG logger.info("transactionTemplate:"  + transactionTemplate);
			retval = transactionTemplate.execute(new TransactionCallback<Integer>() {
				@Override
				public Integer doInTransaction(TransactionStatus transactionStatus) {
					int retvalue = 0;
					try{
						updateFirm(daoObj, errorStackTrace);
						updateFirfb(daoObj, errorStackTrace);
						updateFirmkos(daoObj, errorStackTrace);
						updateFirku(daoObj, errorStackTrace);
						updateFirsta(daoObj, errorStackTrace);
						updateKodtv(daoObj, errorStackTrace);
						updateFirml1(daoObj, errorStackTrace);
						updateFirtr(daoObj, errorStackTrace);
						
					}catch(Exception e){
						logger.info("Setting update to rollback only.");
						transactionStatus.setRollbackOnly();
						//
						Writer writer = dbErrorMessageMgr.getPrintWriter(e);
						//logger.info(writer.toString());
						//Chop the message to comply to JSON-validation
						errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
						retvalue = -1;
					}
					return retvalue;
				}
			});
			
		}catch(Exception e){
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		return retval;
	}
	
	
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 * @throws Exception (Important Note: this throws MUST be in place in order to obey the transaction manager caller method). Hence the ability to Rollback  
	 */
	private int updateFirm(Object daoObj, StringBuffer errorStackTrace) throws Exception{
		int retval = 0;
		try{
			FirmDao dao = (FirmDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug fikdul: " + dao.getFikdul() + " fifirm:" + dao.getFifirm());
			
			sql.append(" UPDATE firm SET fift = ?, fikdul = ?, filtb = ?, filfb = ?, fiups = ?, fiupm = ?, ");
			sql.append(" fikdt = ?, fiatk = ?, fiurli = ?, fiurle = ?, fiurfi = ?, fiurfe = ?, fiurfl = ?, " );
			sql.append(" fiurbi = ?, fiurbe = ?, fiurbl = ?, fiursi = ?, fiurse = ?, fimvas = ?, fivalk = ?, ficurr = ?, fitax = ?, fidtfm = ?, fideci = ?, fiecon = ?, fiavte = ?, ");
			sql.append(" fikont = ?, filand = ?, fitdvi = ?, fistfn = ?, fistfe = ?, fistft = ?, file12 = ?, file22 = ?, file11 = ?, file21 = ?, file31 = ?, file41 = ?, fitran = ?, ");
			sql.append(" fikrtn = ?, fitax2 = ?, fitaxd = ?, fisadk = ?, filibf = ?, filibo = ?, innutl = ?, zipcod = ? ");
			//WHERE
			sql.append(" WHERE fifirm = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getFift(), dao.getFikdul(), dao.getFiltb(), dao.getFilfb(), dao.getFiups(), dao.getFiupm(), 
				dao.getFikdt(), dao.getFiatk(), dao.getFiurli(), dao.getFiurle(), dao.getFiurfi(), dao.getFiurfe(), dao.getFiurfl(), 
				dao.getFiurbi(), dao.getFiurbe(), dao.getFiurbl(), dao.getFiursi(), dao.getFiurse(), dao.getFimvas(), dao.getFivalk(), dao.getFicurr(), dao.getFitax(), dao.getFidtfm(), dao.getFideci(), dao.getFiecon(), dao.getFiavte(), 
				dao.getFikont(), dao.getFiland(), dao.getFitdvi(), dao.getFistfn(), dao.getFistfe(), dao.getFistft(), dao.getFile12(), dao.getFile22(), dao.getFile11(), dao.getFile21(), dao.getFile31(), dao.getFile41(), dao.getFitran(), 
				dao.getFikrtn(), dao.getFitax2(), dao.getFitaxd(), dao.getFisadk(), dao.getFilibf(), dao.getFilibo(), dao.getInnutl(), dao.getZipcod(),  
				
				//WHERE
				dao.getFifirm() } );
			
		}catch(Exception e){
			logger.info(e.toString());
			//Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			throw e;
		}	
		return retval;
	}
	/**
	 * FIRFB
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 * 
	 */
	private int updateFirfb(Object daoObj, StringBuffer errorStackTrace) throws Exception{
		int retval = 0;
		try{
			FirmDao dao = (FirmDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE firfb SET fifbnr = ?, fitpnr = ?, firecn = ?, firecm = ?, fisnla = ?, fisnle = ?, fiidla = ?, fiidle = ?, fiidnr = ?, fiidmx = ?  ");
			sql.append(" WHERE fifirm = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getFifbnr(), dao.getFitpnr(), dao.getFirecn(), dao.getFirecm(), 
				dao.getFisnla(), dao.getFisnle(), dao.getFiidla(), dao.getFiidle(), dao.getFiidnr(), dao.getFiidmx(), 
				//WHERE
				dao.getFifirm() } );
		}catch(Exception e){
			logger.info(e.toString());
			//Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			throw e;
		}
		return retval;
	}
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 * @throws Exception
	 */
	public int updateFirfbCounter(Object daoObj, StringBuffer errorStackTrace) throws Exception{
		int retval = 0;
		try{
			FirmDao dao = (FirmDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			Integer counter = Integer.parseInt(dao.getFirecn());
			counter++;
			sql.append(" UPDATE firfb SET firecn = ?  ");
			sql.append(" WHERE fifirm = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { counter, dao.getFifirm() } );
				
			dao.setFirecn(String.valueOf(counter));
		}catch(Exception e){
			logger.info(e.toString());
			//Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			throw e;
		}
		return retval;
	}
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 * @throws Exception
	 */
	private int updateFirmkos(Object daoObj, StringBuffer errorStackTrace) throws Exception{
		int retval = 0;
		try{
			FirmDao dao = (FirmDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE firmkos SET tillat = ?, interr = ?  ");
			sql.append(" WHERE fifirm = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTillat(), dao.getInterr(), 
				//WHERE
				dao.getFifirm() } );
		}catch(Exception e){
			logger.info(e.toString());
			//Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			throw e;
		}
		return retval;
	}
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 * @throws Exception
	 */
	private int updateFirku(Object daoObj, StringBuffer errorStackTrace) throws Exception{
		int retval = 0;
		try{
			FirmDao dao = (FirmDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE firku SET fikufr = ?, fikuti = ?, fikune = ?, fikufn = ?  ");
			sql.append(" WHERE fifirm = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getFikufr(), dao.getFikuti(), dao.getFikune(),dao.getFikufn(),
				//WHERE
				dao.getFifirm() } );
		}catch(Exception e){
			logger.info(e.toString());
			//Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			throw e;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 * @throws Exception
	 */
	private int updateFirsta(Object daoObj, StringBuffer errorStackTrace) throws Exception{
		int retval = 0;
		try{
			FirmDao dao = (FirmDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE firsta SET fista = ?, fistb = ?, fistc = ?, fistd = ?, fiste = ?, fistf = ?,  ");
			sql.append(" fistg = ?, fisth = ?, fisti = ?, fistj = ?, fistk = ?, fistl = ?,  ");
			sql.append(" fistm = ?, fistn = ?, fisto = ?, fistp = ?, fistq = ?, fistr = ?,  ");
			sql.append(" fists = ?, fistt = ?, fistu = ?, fistv = ?, fistw = ?, fistx = ?,  ");
			sql.append(" fisty = ?, fistz = ?, fistnr = ?  ");
			
			sql.append(" WHERE fifirm = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getFista(), dao.getFistb(), dao.getFistc(), dao.getFistd(), dao.getFiste(), dao.getFistf(),
				dao.getFistg(), dao.getFisth(), dao.getFisti(), dao.getFistj(), dao.getFistk(), dao.getFistl(),
				dao.getFistm(), dao.getFistn(), dao.getFisto(), dao.getFistp(), dao.getFistq(), dao.getFistr(),
				dao.getFists(), dao.getFistt(), dao.getFistu(), dao.getFistv(), dao.getFistw(), dao.getFistx(),
				dao.getFisty(), dao.getFistz(), dao.getFistnr(),
				
				//WHERE
				dao.getFifirm() } );
		}catch(Exception e){
			logger.info(e.toString());
			//Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			throw e;
		}
		return retval;
	}
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 * @throws Exception
	 */
	private int updateKodtv(Object daoObj, StringBuffer errorStackTrace) throws Exception{
		int retval = 0;
		try{
			FirmDao dao = (FirmDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE kodtv SET kovpro = ?  ");
			sql.append(" WHERE kovavd = 0 ");
			sql.append(" AND kovuni = 'V' ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKovpro() } );
			
		}catch(Exception e){
			logger.info(e.toString());
			//Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			throw e;
		}
		return retval;
	}
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 * @throws Exception
	 */
	private int updateFirml1(Object daoObj, StringBuffer errorStackTrace) throws Exception{
		int retval = 0;
		try{
			FirmDao dao = (FirmDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE firml1 SET l1kjor = ?, l1stdn = ?, l1datn = ?  ");
			sql.append(" WHERE l1firm = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getL1kjor(), dao.getL1stdn(), dao.getL1datn(),
				//WHERE
				dao.getFifirm() } );
			
			
		}catch(Exception e){
			logger.info(e.toString());
			//Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			throw e;
		}
		return retval;
	}
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 * @throws Exception
	 */
	private int updateFirtr(Object daoObj, StringBuffer errorStackTrace) throws Exception{
		int retval = 0;
		try{
			FirmDao dao = (FirmDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug"); 
			sql.append(" UPDATE firtr SET favreg = ?, fibise = ?, fferk = ?, fibrut = ?, fikonv = ?    ");
			sql.append(" WHERE fifirm = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getFavreg(), dao.getFibise(), dao.getFferk(),
				dao.getFibrut(), dao.getFikonv(),
				//WHERE
				dao.getFifirm() } );
			
			
		}catch(Exception e){
			logger.info(e.toString());
			//Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			throw e;
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

	private TransactionTemplate transactionTemplate = null;                                                            
	public void setTransactionTemplate( TransactionTemplate transactionTemplate) {this.transactionTemplate = transactionTemplate;}          
	public TransactionTemplate getTransactionTemplate() {return this.transactionTemplate;}
	
}
