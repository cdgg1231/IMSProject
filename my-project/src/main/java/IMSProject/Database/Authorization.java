package IMSProject.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Authorization {

	Scanner in = new Scanner(System.in);
	private String permissions;
	ArrayList<String> userPermission = new ArrayList<String>();
	private String answer;
	private String username;
	private String q;
	PreparedStatement statement = null;
	ResultSet rs = null;
	Connection connection = new dbConnection().getConnection();// open connection to mySql database
	private String authorization;

	public void newAuthorization() {
		username = in.next();
		getPermission();
		q = "UPDATE userInfo SET authorization = '" + userPermission + "' WHERE userName = '" + username + "'";
		try {
			statement = connection.prepareStatement(q);
			statement.executeUpdate();

			statement.close();
		}

		catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}
	}

	public String getPermission() {
		username = in.next();
		q = "select Authorization from userInfo where WHERE userName = '" + username + "'";
		try {
			statement = connection.prepareStatement(q);

			while (rs.next()) {
				String authorization = rs.getString("Authorization");

				System.out.println("Authorization" + authorization);

				statement.close();
			}
		} catch (SQLException e) { // to check if the db connection was successful or not
			System.out.println("Oops, error!");
			e.printStackTrace();
		}

		return authorization;
	}

	public void addPermission() {

		answer = in.next();

		if (answer == "yes") {
			userPermission.add("1");
		} else {
			userPermission.add("0");
		}
	}
}
