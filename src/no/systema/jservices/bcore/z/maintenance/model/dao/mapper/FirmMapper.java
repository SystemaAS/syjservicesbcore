package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.FirmDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Aug 4, 2016
 * 
 */
public class FirmMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(FirmMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	FirmDao dao = new FirmDao();
    	dao.setFifirm(rs.getString("fifirm"));
    	dao.setFift(rs.getString("fift"));
    	
        return dao;
    }

}


