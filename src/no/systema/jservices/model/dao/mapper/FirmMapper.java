package no.systema.jservices.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.FirmDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Jan 26, 2017
 * 
 */
public class FirmMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(FirmMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	FirmDao dao = new FirmDao();
    	//dao.setSlalfa(rs.getString("slalfa"));
    	//TODO
    	
        return dao;
    }

}


