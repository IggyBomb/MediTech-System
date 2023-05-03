package formulaires;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
	static Connection connection;

	// b. Private constructor with parameters url, login, and password
	private SingleConnection(String url, String login, String password) {
		try {
			// Load the driver (replace with the appropriate driver for your database)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(url, login, password);
			
			//connection check
			if(connection != null) {
				System.out.println("Connection established");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to get the unique instance of the connection
	public static Connection getInstance(String url, String login, String password) {
		if (connection == null) {
			new SingleConnection(url, login, password);
		}
		return connection;
	}
}




