package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaHodeDao;

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
public class KodtaHodeMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(KodtaHodeMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	KodtaHodeDao dao = new KodtaHodeDao();
    	
    	dao.setKoaavd(rs.getString("koaavd"));
    	dao.setKoanvn(rs.getString("koanvn"));
    	dao.setHoavd(rs.getString("hoavd"));
    	dao.setHonet(rs.getString("honet"));
    	dao.setHostfr(rs.getString("hostfr"));
    	
    	dao.setHoht1(rs.getString("hoht1"));
    	dao.setHoht2(rs.getString("hoht2"));
    	dao.setHoht3(rs.getString("hoht3"));
    	dao.setHoht4(rs.getString("hoht4"));
    	dao.setHoht5(rs.getString("hoht5"));
    	dao.setHoht6(rs.getString("hoht6"));
    	dao.setHoht7(rs.getString("hoht7"));
    	dao.setHobt1(rs.getString("hobt1"));
    	dao.setHobt2(rs.getString("hobt2"));
    	dao.setHobt3(rs.getString("hobt3"));
    	
        return dao;
    }

}


