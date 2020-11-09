package no.systema.jservices.model.dao.services;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import no.systema.jservices.model.dao.mapper.FirmMapper;
import no.systema.jservices.model.dao.entities.FirmArcDao;
import no.systema.jservices.model.dao.entities.FirmDao;

/**
 * 
 * @author oscardelatorre
 * @date Nov 2020
 * 
 */
public class FirmArcDaoServicesImpl implements FirmArcDaoServices {
	private static Logger logger = Logger.getLogger(FirmArcDaoServicesImpl.class.getName());
	
	/**
	 * @param userName
	 * @return
	 */
	public List<FirmArcDao> getList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from firmarc ");
		
		return this.jdbcTemplate.query( sql.toString(),  new FirmMapper());
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public String getArchiveBasePath(){
		String sql = "SELECT arcane FROM firmarc ";
		String name = null;
		try{
			name = (String)jdbcTemplate.queryForObject( sql, String.class);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		return name;
	}
	
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
