package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtwDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Aug 5, 2016
 * 
 */
public class KodtwMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtwMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	KodtwDao dao = new KodtwDao();
    	dao.setKowavd(rs.getString("kowavd"));
    	dao.setKowlas(rs.getString("kowlas"));
    	dao.setKowawb(rs.getString("kowawb"));
    	dao.setKowbbs(rs.getString("kowbbs"));
    	dao.setKowxxx(rs.getString("kowxxx"));
    	
        return dao;
    }

}


