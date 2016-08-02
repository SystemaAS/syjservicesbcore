
import java.sql.*;


public class SqlTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SqlTester main = new SqlTester();
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
				 sql.append(" select CHAR(a.koaavd) koaavd, CHAR(a.koaknr) koaknr, CHAR(a.KOABÆR) koabaer, CHAR(a.koakon) koakon, ");
				 sql.append(" a.koafir, a.koanvn, CHAR(a.koaiat) koaiat, a.koaie, a.koapos, a.koalk, ");
				 sql.append(" coalesce(b.navsg,'') navsg, coalesce(c.ksidnr,'') ksidnr ");
				 
				 sql.append(" from kodta AS a ");
				 sql.append(" full outer join navavd AS b ");
				 sql.append(" on a.koaavd = b.koaavd  ");
				 sql.append(" full outer join kodtasid AS c ");
				 sql.append(" on a.koaavd = c.ksavd ");
				 //sql.append(" where a.koaavd = 1 ");
				 	
				 PreparedStatement stmt = conn.prepareStatement(sql.toString());
				 
				 ResultSet rs = stmt.executeQuery();
				 while (rs.next()) {
					 String field1 = rs.getString("koaavd");
					 //String field2 = rs.getString("koaknr");
					 //String field3 = rs.getString("navsg");
					 //String field2 = rs.getString("fieldname");
					 System.out.println(rs.getString("koaavd") + "x" + rs.getString("koaknr") + "x" + rs.getString("navsg") + "x" + rs.getString("ksidnr") );
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
