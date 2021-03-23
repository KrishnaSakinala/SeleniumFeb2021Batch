package in.at.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class DBConnection {

	public static Connection connection = null;

	// ---------------------------------------

	@Test
	public void testDBConnection() throws SQLException, InterruptedException {
		Connection con = getdbConnection();
		ResultSet resultSet = getResultSet(con, "SELECT * FROM auth_user");
		while (resultSet.next()) {
			String fName = resultSet.getString("first_name");
			String lName = resultSet.getString("last_name");
			System.out.println(fName + "|" + lName);
		}
		closedbConnection();
	}

	// ---------------------------------------

	public static Connection getdbConnection() throws SQLException {

		String dbName = "jdbc:sqlite:/Z:/DemoApp/hospitalmanagement-qa/db.sqlite3";
		System.out.println("************" + dbName + "*************");

		connection = DriverManager.getConnection(dbName);
		connection.setAutoCommit(true);
		return connection;
	}

	public static void closedbConnection() throws SQLException {

		if(connection != null)
			connection.close();

	}

	public static ResultSet getResultSet(Connection connection, String query)
			throws SQLException, InterruptedException {

		// create the java statement
		Statement statement = connection.createStatement();

		// execute the query, and get a java result set
		System.out.println("Query : " + query);

		Thread.sleep(2);
		ResultSet resultSet = statement.executeQuery(query);

		Thread.sleep(4);
		return resultSet;

	}
}
