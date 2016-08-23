package no.systema.jservices.bcore.z.maintenance.model.dao.mapper.sadimport;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.sadimport.StandiDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Aug 23, 2016
 * 
 */
public class StandiMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(StandiMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	StandiDao dao = new StandiDao();
    	dao.setSiavd(rs.getString("siavd"));
    	dao.setSitdn(rs.getString("sitdn"));
    	
        return dao;
    }

}


