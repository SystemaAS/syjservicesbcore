package no.systema.jservices.jsonwriter.reflection;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.apache.logging.log4j.*;

import no.systema.jservices.common.dao.IDao;
import no.systema.main.util.JsonSpecialCharactersManager;
import no.systema.main.util.constants.JsonConstants; 

public class JsonWriterReflectionManagerAux {
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	private static Logger logger = LogManager.getLogger(JsonWriterReflectionManagerAux.class.getName());
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	public String getGettersFromRecord(IDao record){
		StringBuffer jsonReflectionOutput = new StringBuffer();
		try{
			Class<?> recordClazz = record.getClass();
			Method  theMethod = null;
			Class<?> returnType = null;
			int counter = 1;
			
			for(Method method : recordClazz.getDeclaredMethods()){
				//logger.info("MTEHOD:" + method);
				//only getters
				String getter = method.getName();
				if(getter.startsWith("get") && !getter.endsWith("PropertyName")){
					theMethod= recordClazz.getDeclaredMethod(method.getName());
					returnType = theMethod.getReturnType();
					if(returnType.equals(String.class)){
						String field = theMethod.getName().replace("get", "").toLowerCase();
						String value = (String)theMethod.invoke(record);
						//logger.info("FIELD:" + field + " " + value);
						if(counter>1){ jsonReflectionOutput.append(JsonConstants.JSON_FIELD_SEPARATOR ); }
						//trim when not null
						if(value!=null){ value = value.trim(); }
						//append json string
						jsonReflectionOutput.append(JsonConstants.JSON_QUOTES + field + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(value) + JsonConstants.JSON_QUOTES);
						counter ++;
						
					}else if(returnType.equals(int.class)){
						String field = theMethod.getName().replace("get", "").toLowerCase();
						int value = (Integer)theMethod.invoke(record);
						//logger.info("FIELD:" + field + " " + value);
						if(counter>1){ jsonReflectionOutput.append(JsonConstants.JSON_FIELD_SEPARATOR ); }
						//append json string
						jsonReflectionOutput.append(JsonConstants.JSON_QUOTES + field + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + value + JsonConstants.JSON_QUOTES);
						counter ++;
					}
				}
				
			}
			
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return jsonReflectionOutput.toString();
	}

	/**
	 * This method is enhanced with the capability to include one-to-one child dao
	 * 
	 * @Example
	 * class myDao implements IDao
	 * ...
	 * private String xx
	 * private String yy 
	 * private <b>ZzzDao</b> zzzDao 
	 * ..
	 * 
	 * @param record
	 * @return a JSON structure as String
	 */
	public String getGettersFromRecordExtended(IDao record){
		StringBuilder jsonReflectionOutput = new StringBuilder();
		try{
			Class<?> recordClazz = record.getClass();
			Method  theMethod = null;
			Method  theChildMethod = null;
			Class<?> returnType = null;
			Class<?> childReturnType = null;
			int counter = 1;
			
			for (Method method : recordClazz.getDeclaredMethods()) {
				// only getters
				String methodName = method.getName();
				if (methodName.startsWith("get") && !methodName.endsWith("PropertyName")) {
					theMethod = recordClazz.getDeclaredMethod(method.getName());
					returnType = theMethod.getReturnType();
					if (returnType.equals(String.class)) {
						String field = theMethod.getName().replace("get", "").toLowerCase();
						String value = (String) theMethod.invoke(record);
						//logger.info("["+field+"]:["+value+"]");
						if (counter > 1) {
							jsonReflectionOutput.append(JsonConstants.JSON_FIELD_SEPARATOR);
						}
						if (value != null) {
							value = value.trim();
						}

						jsonReflectionOutput.append(JsonConstants.JSON_QUOTES + field + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(value) + JsonConstants.JSON_QUOTES);
						counter ++;
					} else {
						if (methodName.endsWith("Dao")) {
							Object childDao = null;
							try {
								childDao = (IDao) theMethod.invoke(record);
							} catch (Exception e) {
								if (e instanceof ClassCastException) {
									childDao = (no.systema.jservices.common.dao.IDao) theMethod.invoke(record);	
								} else {
									logger.info("Error;", e);
									throw e;
								}
							}
							if (childDao != null) {
								Class<?> childClazz = childDao.getClass();
								for (Method childMethod : childClazz.getDeclaredMethods()) {
									String childMethodName = childMethod.getName();
									if (childMethodName.startsWith("get")) {
										theChildMethod = childClazz.getDeclaredMethod(childMethod.getName());
										childReturnType = theChildMethod.getReturnType();
										if (childReturnType.equals(String.class)) {
											String field = theChildMethod.getName().replace("get", "").toLowerCase();
											String value = (String) theChildMethod.invoke(childDao);
											if (counter > 1) {
												jsonReflectionOutput.append(JsonConstants.JSON_FIELD_SEPARATOR);
											}
											if (value != null) {
												value = value.trim();
											}
											jsonReflectionOutput.append(JsonConstants.JSON_QUOTES + field + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(value) + JsonConstants.JSON_QUOTES);
											counter ++;
										} else if(childReturnType.equals(int.class)){
											String field = theChildMethod.getName().replace("get", "").toLowerCase();
											int value = (Integer) theChildMethod.invoke(childDao);
											if (counter > 1) {
												jsonReflectionOutput.append(JsonConstants.JSON_FIELD_SEPARATOR);
											}
											jsonReflectionOutput.append(JsonConstants.JSON_QUOTES + field + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + value + JsonConstants.JSON_QUOTES);
											counter ++;
											
										}
										
									}
								}
							}
						}
					}
				}
			}
			
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}

		return jsonReflectionOutput.toString();
	}

}
