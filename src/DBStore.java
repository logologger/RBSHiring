import java.sql.*;




public class DBStore {

	/**
	 * @param args
	 */
	
	static Connection con=null;
	static Statement stmt=null;
	DBStore(){
		try{
		Class.forName("com.mysql.jdbc.Driver");
		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rbs_ch","root","root");
		
		 stmt=con.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}
	
	public static void ExecuteQuery(String query){
		try{
			
			stmt.executeUpdate(query);
			//both for insert and select Query it should run
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
