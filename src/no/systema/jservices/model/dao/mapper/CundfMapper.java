package no.systema.jservices.model.dao.mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.CundfDao;

/**
 * 
 * @author oscardelatorre
 * @date Nov 4, 2015
 * 
 */
public class CundfMapper implements RowMapper {
	private static Logger logger = LogManager.getLogger(CundfMapper.class.getName());

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		CundfDao dao = new CundfDao();
		try {
			Class cl = Class.forName(dao.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();
			List<Field> list = Arrays.asList(fields);
			for (Field field : list) {
				String name = (String) field.getName();
				// here we put the value
				field.setAccessible(true);
				field.set(dao, rs.getString(name));
			}
		} catch (Exception e) {
			logger.info("e=" + e.toString());
		}

		return dao;
	}

}
