package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVToDatabase {

	// Establish JDBC connection:

	// private static final String url = "jdbc:mysql://localhost:3306/application";
	// // for mysql (port:3306).
	// private static final String username = "albertcordina";
	 private static final String csvFilePath = "The list of the Applicants mySQL.csv";

	private static final String url = "jdbc:postgresql://localhost:5432/application"; // for postgresql (port:5432).
	private static final String username = "postgres";
	//private static final String csvFilePath = "The list of the Applicants PostgreSQL.csv";

	private static final String passwordJDBC = "abcABC123";
	// Specify the path to the CSV file and the target MySQL table
	private static final String tableName = "application2";

	
	
	/*
	 *  Method saves CSV data to a SQL/PostgreSQL database table, 
	 *   or creates and saves data in a new table if NOT EXIST.
	 */
	public static void saveCSVToDatabase() throws Exception {
		
		try (Connection connection = DriverManager.getConnection(url, username, passwordJDBC);
				BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

			String line;
			String[] headers = null;

			// Read the CSV file headers
			if ((line = reader.readLine()) != null) {
				headers = line.split(",");
			}

			// Create the PostgreSQL table if it doesn't exist
			createTableIfNotExists(connection, headers);

			// Prepare the SQL INSERT statement
			String insertSQL = "INSERT INTO " + tableName +
			                    " (phone_number, name, surname, age, occupation, overallincome, password, statusofapplication) " +
			                    "VALUES (CAST(? AS INT), ?, ?, CAST(? AS INT), ?, CAST(? AS DOUBLE PRECISION), ?, ?)";

			try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

				// Read and insert data into PostgreSQL table
				while ((line = reader.readLine()) != null) {
					String[] data = line.split(",");
					for (int i = 0; i < data.length; i++) {
						preparedStatement.setString(i + 1, data[i]);
					}
					preparedStatement.executeUpdate();
				}
			}

		} catch (SQLException e) {
			throw new Exception("Error saving CSV to PostgreSQL: " + e.getMessage(), e);
		}
	}

	// Helper method to create the PostgreSQL table if it doesn't exist
	private static void createTableIfNotExists(Connection connection, String[] headers) throws SQLException {
		
		StringBuilder createTableSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");

		// Generate SQL for columns using headers from CSV
		createTableSQL.append("phone_number INT, ");
		createTableSQL.append("name VARCHAR(30), ");
		createTableSQL.append("surname VARCHAR(30), ");
		createTableSQL.append("age INT, ");
		createTableSQL.append("occupation VARCHAR(255), ");
		createTableSQL.append("overallincome DOUBLE PRECISION, ");
		createTableSQL.append("password VARCHAR(30), ");
		createTableSQL.append("statusofapplication TEXT");

		// Complete the SQL statement
		createTableSQL.append(")");

		// Execute the SQL statement to create the table
		try (PreparedStatement createTableStatement = connection.prepareStatement(createTableSQL.toString())) {
			createTableStatement.executeUpdate();
		}
	}
}
