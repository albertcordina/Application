package application;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDB_Exporter {
	
	// Establish JDBC connection:
	
	// private static final String url = "jdbc:mysql://localhost:3306/application"; // for mysql (port:3306).
	// private static final String username = "albertcordina";
	// private static final String outputFile = "The list of the Applicants mySQL.csv";

		private static final String url = "jdbc:postgresql://localhost:5432/application"; // for postgresql (port:5432).
		private static final String username = "postgres";
	    private static final String outputFile = "The list of the Applicants PostgreSQL.csv";

		private static final String passwordJDBC = "abcABC123";		
	    	
    
    public static void displayTable () {
    	
        try {
            /*
             *  Load the JDBC driver. Returns the Class object associated with 
             *               the class or interface with the given string name.
             */
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database.
            try (Connection connection = DriverManager.getConnection(url, username, passwordJDBC)) {

                // Query to select all data from the table
                String query = "SELECT * FROM application";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {

                    // Print column headers
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getMetaData().getColumnName(i));
                        if (i < columnCount) {
                            System.out.print("\t");
                        }
                    }
                    System.out.println();

                    // Print data
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(resultSet.getString(i));
                            if (i < columnCount) {
                                System.out.print("\t");
                            }
                        }
                        System.out.println();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
//------------------------------------------------------------------------------------------------    
    

    public static void exportTableToFile () {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            try (Connection connection = DriverManager.getConnection(url, username, passwordJDBC)) {

                // Query to select all data from the table
                String query = "SELECT * FROM application";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {

                    // Write the data to a CSV file
                    writeToFile(resultSet);
                    System.out.println("Data exported successfully to " + outputFile);
                }
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile (ResultSet resultSet) throws SQLException, IOException {
    	
        try (FileWriter writer = new FileWriter(outputFile)) {

            // Write column headers
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                writer.append(resultSet.getMetaData().getColumnName(i));
                if (i < columnCount) {
                    writer.append(",");
                }
            }
            writer.append("\n");

            // Write data
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    writer.append(resultSet.getString(i));
                    if (i < columnCount) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
        }
    }

}
