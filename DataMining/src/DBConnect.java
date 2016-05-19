import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	public static void connectToDB(String str) {
		String con = "jdbc:sqlserver://DESKTOP-H5LDL12\\MSSQLSERVER1A;databaseName=DataMining;integratedSecurity=true;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection cn = DriverManager.getConnection(con);
			Statement s = cn.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM " + str);
			while (r.next()) {
				AprioriAlgorithm.map.put(Integer.parseInt(r.getString(1)), r.getString(2));
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getClass().getName());
		}
	}
}
