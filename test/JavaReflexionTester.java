import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtvKodtwDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
/**
 * @author oscardelatorre
 *
 */
public class JavaReflexionTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaReflexionTester tester = new JavaReflexionTester();
		try{
		tester.run();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 oscardelatorre
	 Dec 1, 2012
	 */
	private void run() throws Exception{
		KodtvKodtwDao dao = new KodtvKodtwDao();
		Class cl = Class.forName(dao.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			String name = (String)field.getName();
			if(name!=null && !"".equals(name)){
				System.out.println(field.getName() + " Name:" + name);
			}
			field.set(dao, name);
			System.out.println("XX:" + field.get(dao));
		}
	}
}
