package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtvKodtwDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Aug 5, 2016
 * 
 */
public class KodtvKodtwMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtvKodtwMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	KodtvKodtwDao dao = new KodtvKodtwDao();
    	dao.setKowavd(rs.getString("kowavd"));
    	dao.setKowlas(rs.getString("kowlas"));
    	dao.setKowawb(rs.getString("kowawb"));
    	dao.setKowbbs(rs.getString("kowbbs"));
    	dao.setKowxxx(rs.getString("kowxxx"));
    	
        return dao;
    }

}


