package no.systema.jservices.model.dao.mapper;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.EdisssDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 19, 2016
 * 
 */
public class EdisssMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(EdisssMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	EdisssDao dao = new EdisssDao();
    
    	dao.setSsssn(rs.getString("ssssn"));
    	dao.setSssdt(rs.getString("sssdt"));
    	dao.setSsstm(rs.getString("ssstm"));
    	dao.setSssdata(rs.getString("sssdata"));
    	
        return dao;
    }

}


