package no.systema.jservices.model.dao.entities;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Mar 2023
 * 
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DktfiDao implements Serializable, IDao {
	
	private String dktf_0004t = "";                                
	private String dktf_0010t = "";                                
	private String dktf_0022t = "";                                
	private String dktf_0031t = "";                                
	private String dktf_0004p = "";                                
	private String dktf_0010p = "";                                
	private String dktf_0022p = "";                                
	private String dktf_0031p = "";                                
	
	/**
	 * Required for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
