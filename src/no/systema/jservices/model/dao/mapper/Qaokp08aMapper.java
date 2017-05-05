package no.systema.jservices.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import no.systema.jservices.model.dao.entities.Qaokp08Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 05, 2017
 * 
 */
public class Qaokp08aMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(Qaokp08aMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Qaokp08Dao dao = new Qaokp08Dao();
    	//TODO
    	
        return dao;
    }

}


