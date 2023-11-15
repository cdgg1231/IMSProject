
package IMSProject.Database;

import java.sql.*;

public class dbConnection {

	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/sprint1";
		String username = "root";
		String password = "David3341";
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to connect to the database!");
		}

		return connection;
	}

	public boolean isConnected() {
		String url = "jdbc:mysql://localhost:3306/sprint1";
		String username = "root";
		String password = "David3341";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}