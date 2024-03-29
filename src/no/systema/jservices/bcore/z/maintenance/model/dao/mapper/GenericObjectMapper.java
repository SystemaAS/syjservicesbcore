package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.IDao;

/**
 * This class transform as ResultSet into IDao
 * 
 * @author Fredrik Möller
 * @date Nov 21, 2016
 * 
 */
@SuppressWarnings("rawtypes")
public class GenericObjectMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(GenericObjectMapper.class.getName());
	private IDao dao = null;
	private String className = null;

	/**
	 * This constructor passes the actual dao instanse to reflection-iteration
	 * on fields.
	 * 
	 * Note: Only simple structure is supported, no composite is managed. That must be done outside rowmapper
	 * 
	 * @param daoObj, the actual dao
	 */
	public GenericObjectMapper(IDao daoObj) {
		this.className = daoObj.getClass().getName();
	}

	public IDao mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			Class<?> clazz = Class.forName(className);
			dao = (IDao) clazz.newInstance();

			Class cl = Class.forName(dao.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();
			List<Field> list = Arrays.asList(fields);
			String name = null;
			for (Field field : list) {
				if (field.getType() != String.class) {
					//logger.info("Only type String.class is supported, type="+field.getType());
					break;
				}
				name = (String) field.getName();
				try {
					field.setAccessible(true);
					field.set(dao, rs.getString(name));
				} catch (Exception e) {
					// Usually when no column matches the JavaBean property...
					//logger.info(e.getMessage() + e.toString() + "name="+name);
					continue;
				}
			}
		} catch (Exception e) {
			e.toString();
			logger.info(e.getMessage() + e.toString());
		}

		return dao;
	}

}
