package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaHodel1Dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date  Aug 17, 2016
 * 
 */
public class KodtaHodel1Mapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtaHodel1Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	KodtaHodel1Dao dao = new KodtaHodel1Dao();
    	
    	dao.setKoaavd(rs.getString("koaavd"));
    	dao.setKoanvn(rs.getString("koanvn"));
    	dao.setHonet(rs.getString("honet"));
    	dao.setHostfr(rs.getString("hostfr"));
    	
        
        return dao;
    }

}


