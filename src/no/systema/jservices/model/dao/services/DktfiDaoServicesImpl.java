package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.model.dao.entities.DktfiDao;
import no.systema.jservices.model.dao.mapper.DktfiMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * Mar 2023
 * 
 */
public class DktfiDaoServicesImpl implements DktfiDaoServices {
	private static Logger logger = LoggerFactory.getLogger(DktfiDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	@Override
	public List<DktfiDao> getList(StringBuffer errorStackTrace){
		List<DktfiDao> list = new ArrayList<DktfiDao>();
		try{
			String sql = " select * from dktfi ";
			list = jdbcTemplate.query( sql, new DktfiMapper());
			logger.warn("SVTFI list size:" + list.size());
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			
		}
		return list;
	}
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
		
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

	


}
