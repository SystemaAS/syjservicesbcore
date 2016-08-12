package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtpUtskrsDao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date  Aug 9, 2016
 * 
 */
public class KodtpUtskrsMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtpUtskrsMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	KodtpUtskrsDao dao = new KodtpUtskrsDao();
    	//We use reflection since there are many fields. We could have written all fields manually without reflection. Refer to other daos.
    	try{
    		dao.setUtpnr(rs.getString("utpnr"));
    		dao.setUtpty(rs.getString("utpty"));
    		dao.setUtpcpi(rs.getString("utpcpi"));
    		dao.setUtplpi(rs.getString("utplpi"));
    		dao.setUtplpp(rs.getString("utplpp"));
    		dao.setUtpcpl(rs.getString("utpcpl"));
    		
    		
    	}catch(Exception e){
    		e.toString();
    	}
    	
        return dao;
    }

}


