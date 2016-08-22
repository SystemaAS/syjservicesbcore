package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaTellDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Aug 22, 2016
 * 
 */
public class TellMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(TellMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	KodtaTellDao dao = new KodtaTellDao();
    	dao.setTeavd(rs.getString("teavd"));
    	dao.setTeopdn(rs.getString("teopdn"));
    	dao.setTeturn(rs.getString("teturn"));
    	dao.setTetmin(rs.getString("tetmin"));
    	
        return dao;
    }

}


