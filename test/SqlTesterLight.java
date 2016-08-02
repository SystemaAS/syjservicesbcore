
import java.sql.*;


public class SqlTesterLight {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SqlTesterLight main = new SqlTesterLight();
		main.runIt();
		
	}
	//sql compact execution
	private void runIt(){
		try{
			String IP_SYSTEMA_AS400 = "10.13.1.22"; 
			String userId = "systema";
			String pass = "straffe12";
			
			String DRIVER = "com.ibm.as400.access.AS400JDBCDriver"; 
			//String URL = "jdbc:as400://" + IP_SYSTEMA_AS400 + ";naming=system";
			String URL = "jdbc:as400://" + IP_SYSTEMA_AS400 + ";naming=system;libraries=syendre,syendptf,syspedf,sysped"; 
			
			Connection conn = null;
			 
			 //Connect to iSeries 
			 Class.forName(DRIVER); 
			 DriverManager.setLoginTimeout(2);
			 conn = DriverManager.getConnection(URL, userId, pass);
			 if(!conn.isClosed()){
				 System.out.println("<"+ conn + "> " + "Connection OK!");
				 //SQL-statements
				
				 //SQL 1
				 StringBuffer sql = new StringBuffer();
				 sql.append(" select count(1) counter ");
				 
				 sql.append(" from navavd ");
				 sql.append(" where koaavd = 5 ");
				 	
				 PreparedStatement stmt = conn.prepareStatement(sql.toString());
				 
				 ResultSet rs = stmt.executeQuery();
				 while (rs.next()) {
					 System.out.println(rs.getString("counter"));
				 }
				
				 
				 rs.close();
				 stmt.close();
				 conn.close();
				 
				
			 }else {
				 System.out.println("No connection...");
			 }
			 
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

}
