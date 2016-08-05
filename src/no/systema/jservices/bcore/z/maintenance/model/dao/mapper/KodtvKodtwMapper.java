package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtvKodtwDao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    	//We use reflection since there are many fields. We could have write all fields manually without relfection. Refer to other daos.
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


