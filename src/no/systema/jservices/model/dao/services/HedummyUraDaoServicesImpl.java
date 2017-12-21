package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import no.systema.jservices.model.dao.mapper.HedummyMapper;
import no.systema.jservices.model.dao.entities.HedummyDao;

import no.systema.main.util.DbErrorMessageManager;
import no.systema.main.util.StringManager;
import no.systema.jservices.common.dao.services.util.Db2MemberAliasManager;


public class HedummyUraDaoServicesImpl implements HedummyUraDaoServices {
	private static Logger logger = Logger.getLogger(HedummyUraDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private StringManager strMgr = new StringManager();
	private Db2MemberAliasManager db2MemberAliasMgr = new Db2MemberAliasManager();
	
	//START - Attributes for lib.member db2-special CREATE/DROP ALIAS 
	//Example: Create alias syspedf.ura_alias for syspedf.hedummy(ura) / Drop alias ura_alias
	private String library = null;
	public void setLibrary(String value){ this.library = value; }
	
	private String member = null;
	public void setMember(String value){ this.member = value; }
	
	private String alias = null;
	public void setAlias(String value){ this.alias = value; }
	
	private String memberTable = null;
	public void setMemberTable(String value){ this.memberTable = value; }
	//END -Attributes for lib.member db2-special CREATE/DROP ALIAS 
	
	//Mandatory
	private boolean isValidAlias(){
		boolean retval = false;
		if( strMgr.isNotNull(this.library) && strMgr.isNotNull(this.member) && strMgr.isNotNull(this.memberTable) && strMgr.isNotNull(this.alias)) {
			retval = true;
		}
		
		return retval;
	}
	/**
	 * 
	 * @return
	 */
	public List getList(){
		String sql = this.getSELECT_FROM_CLAUSE();
		return this.jdbcTemplate.query( sql, new HedummyMapper());
	}
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<HedummyDao> retval = new ArrayList<HedummyDao>();
		try{
			if( this.isValidAlias()){
				
				//create alias for sub-member
				db2MemberAliasMgr.createAlias(this.jdbcTemplate, this.library, this.alias, this.memberTable, this.member);
				
				//do it
				String sql = this.getSELECT_FROM_CLAUSE();
				retval =  this.jdbcTemplate.query( sql, new HedummyMapper());
				
				//drop alias
				db2MemberAliasMgr.dropAlias(this.jdbcTemplate, this.library, this.alias);
			}	
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
			try{
				//drop alias
				db2MemberAliasMgr.dropAlias(this.jdbcTemplate, this.library, this.alias);
			}catch(Exception dbe){
				logger.info(dbe.toString());
			}
			
		}
		return retval;
	}
	
	/**
	 * 
	 */
	public List findById(String id, StringBuffer errorStackTrace){
		List<HedummyDao> retval = new ArrayList<HedummyDao>();
		try{
			
			if( this.isValidAlias()){
				//create alias for sub-member
				db2MemberAliasMgr.createAlias(this.jdbcTemplate, this.library, this.alias, this.memberTable, this.member);
				
				//do the thing
				StringBuffer sql = new StringBuffer();
				sql.append(this.getSELECT_FROM_CLAUSE());
				sql.append(" where heavd = ? ");
				//logger.info(sql.toString() + " -->id:" + id);
				retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new HedummyMapper());
				
				//drop alias
				db2MemberAliasMgr.dropAlias(this.jdbcTemplate, this.library, this.alias);
			}
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
			
			try{
				//drop alias
				db2MemberAliasMgr.dropAlias(this.jdbcTemplate, this.library, this.alias);
			}catch(Exception dbe){
				logger.info(dbe.toString());
			}

			
		}
		return retval;
	}
	/**
	 * 
	 * @param id
	 * @param errorStackTrace
	 * @return
	 */
	public List findById(Integer id, StringBuffer errorStackTrace){
		List<HedummyDao> retval = new ArrayList<HedummyDao>();
		try{
			
			if( this.isValidAlias()){
				//create alias for sub-member
				this.createAlias();
				//do the thing
				StringBuffer sql = new StringBuffer();
				sql.append(this.getSELECT_FROM_CLAUSE());
				sql.append(" where heavd = ? ");
				//logger.info(sql.toString() + " -->id:" + id);
				retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new HedummyMapper());
				//drop alias
				this.dropAlias();
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			//logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	private void createAlias(){
		String createAlias = "Create alias " + this.library + "." + this.alias + " for " + this.library + "." + this.memberTable + "(" + this.member + ")";
		this.jdbcTemplate.execute(createAlias);
	}
	private void dropAlias(){
		String createAlias = "drop alias " + this.library + "." + this.alias;
		this.jdbcTemplate.execute(createAlias);
	}
	
	/**
	 * 
	 * @return
	 */
	private String getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from " + this.library + "." + this.alias );
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
