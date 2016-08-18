package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
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
	private static Logger logger = Logger.getLogger(KodtaHodeMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	KodtaHodeDao dao = new KodtaHodeDao();
    	//We use reflection since there are many fields. We could have written all fields manually without reflection. Refer to other daos.
		try{
	    	Class cl = Class.forName(dao.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();
			List<Field> list = Arrays.asList(fields);
			for(Field field : list){
				String name = (String)field.getName();
				if(name!=null && !"".equals(name)){
					//DEBUG --> System.out.println(field.getName() + " Name:" + name);
				}
				//here we put the value
				field.setAccessible(true);
				field.set(dao, rs.getString(name));
			}
    	}catch(Exception e){
    		e.toString();
    	}
        
        return dao;
    }

}


