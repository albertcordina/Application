package application;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC extends Info {

	// Establish JDBC connection:
  //  private static final String url = "jdbc:mysql://localhost:3306/application"; // for mysql (port:3306).
  //  private static final String username = "albertcordina";

	private static final String url = "jdbc:postgresql://localhost:5432/application"; // for postgresql (port:5432).
	private static final String username = "postgres";

	private static final String passwordJDBC = "abcABC123";

	
	// The method 'isPhoneNumberExists' checks if the phone number is already in the list
	public static boolean isPhoneNumberExists(int phoneNumber) {
		
		try (Connection connection = DriverManager.getConnection(url, username, passwordJDBC)) {
			
			// Check if the phone number exists in the 'application' table
			String sql = "SELECT COUNT(*) FROM application WHERE phone_number = ?";
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, phoneNumber);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0; // If count is greater than 0, the phone number exists
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // Return false in case of an exception or if the phone number doesn't exist
	}
//-------------------------------------------------------------------------------------------------
	
	// The method gets a certain value from the database for review or to compare/check for matching 
	public static String getValueByPhoneNumber(String columnName) {
		
        // SQL query to retrieve the specified column's value based on the phone_number
        String sql = "SELECT " + columnName + " FROM application WHERE phone_number = ?";

        try (
                // Establishing a connection to the SQL/PostgreSQL database
                Connection connection = DriverManager.getConnection(url, username, passwordJDBC);

                // Creating a PreparedStatement to execute the select query
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            // Setting the phone_number as a parameter
            preparedStatement.setInt(1, phoneNumber);

            // Executing the select query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Checking if a matching record was found
            if (resultSet.next()) {
                // Retrieving the value of the specified column from the result set
                return resultSet.getString(columnName);
            } else {
                System.out.println("No record found for the provided phone number.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	
	// The method 'saveToDatabase' saves the new, or updated by the user, personal info
	public static void saveToDatabase () {
		
		// Establishing a connection to the PostgreSQL database
		try (Connection connection = DriverManager.getConnection(url, username, passwordJDBC)) {
			
			String sql = "INSERT INTO application (phone_number, name, surname, age, occupation, overallIncome, password, statusOfApplication) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, phoneNumber);
				statement.setString(2, name);
				statement.setString(3, surname);
				statement.setInt(4, age);
				statement.setString(5, occupation);
				statement.setInt(6, overallIncome);
				statement.setString(7, password);
				statement.setString(8, statusOfApplication);

				// Execute the insert statement
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//--------------------------------------------------------------------------------------------	
	
	public static void updateValueString (String columnName, String newValue) {
        // SQL query to update the value
        String sql = "UPDATE application SET " + columnName + " = ? WHERE phone_number = ?";

        try (
                // Establishing a connection to the PostgreSQL database
                Connection connection = DriverManager.getConnection(url, username, passwordJDBC);

                // Creating a PreparedStatement to execute the update query
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            // Setting the new value and condition value as parameters
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, phoneNumber);

            // Executing the update query
            int rowsAffected = preparedStatement.executeUpdate();

            // Checking if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Update successful. Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows updated. Check your condition.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static void updateValueInteger (String columnName, int newValue) {
        // SQL query to update the value
        String sql = "UPDATE application SET " + columnName + " = ? WHERE phone_number = ?";

        try (
                // Establishing a connection to the PostgreSQL database
                Connection connection = DriverManager.getConnection(url, username, passwordJDBC);

                // Creating a PreparedStatement to execute the update query
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            // Setting the new value and condition value as parameters
            preparedStatement.setInt(1, newValue);
            preparedStatement.setInt(2, phoneNumber);

            // Executing the update query
            int rowsAffected = preparedStatement.executeUpdate();

            // Checking if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Update successful. Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows updated. Check your condition.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	public static void deleteRow () {
        // SQL query to delete the row
        String sql = "DELETE FROM application WHERE phone_number = ?";

        try (
                // Establishing a connection to the PostgreSQL database
                Connection connection = DriverManager.getConnection(url, username, passwordJDBC);

                // Creating a PreparedStatement to execute the delete query
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            // Setting the condition value as a parameter
            preparedStatement.setInt(1, phoneNumber);

            // Executing the delete query
            int rowsAffected = preparedStatement.executeUpdate();

            // Checking if the delete was successful
            if (rowsAffected > 0) {
                System.out.println("Delete successful. Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows deleted. Check your condition.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
