package no.systema.jservices.model.dao.services;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import no.systema.jservices.model.dao.mapper.FirmMapper;
import no.systema.jservices.model.dao.entities.FirmDao;

/**
 * 
 * @author oscardelatorre
 * @date Feb 11, 2016
 * 
 */
public class FirmLoginDaoServicesImpl implements FirmLoginDaoServices {
	private static Logger logger = LoggerFactory.getLogger(FirmLoginDaoServicesImpl.class.getName());
	
	/**
	 * @param userName
	 * @return
	 */
	public List<FirmDao> getList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from firm ");
		
		return this.jdbcTemplate.query( sql.toString(),  new FirmMapper());
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public String getCompanyCode(){
		String sql = "SELECT fifirm FROM firm ";
		String name = null;
		try{
			name = (String)jdbcTemplate.queryForObject( sql, String.class);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		return name;
	}
	/**
	 * 
	 * @return
	 */
	public String getTradevisionFlag(){
		String sql = "SELECT fitdvi FROM firm ";
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
