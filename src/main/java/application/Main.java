package application;

import java.util.InputMismatchException;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
 
		// The Welcome message
		System.out.println("Dear applicant, tank you contacting us,\nwe are happy to help you with your application!");
		Menu(); // Call the method 'Menu' below
	}

	public static void Menu() { // Starts and Runs the whole project
 
		Scanner scanner = new Scanner(System.in);

		int choice;

		do {
			System.out.println(
					"\n\tMenu:\n1. Start the application;\n2. Visit your personal profile;\n3. Enterance for admins;\n0. Exit the program;");

			try {
				System.out.print("Enter your choice: ");

				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					Info.collection(); // Calls the method 'collection' in the Class 'Info'
					break;
				case 2: // For those, who are already registered
					Registered.accessAccount(); // Call the method 'accessAccount' in the Class 'Registered'
					break;
				case 3:
					System.out.println("\nIn order to access the data as Administriator you need to enter the password.");
					if (Methods.enterPasswordToAccess("123")) {

						do {
							System.out.println(
									"\n\tMenu:\n1. See the whole list of the Applicants;\n2. Save the list of the Applicants from database into the file;\n3. Save the list of the Applicants from csv file to database;\n4. Back to the Menu;\n0. Exit");

							try {
								System.out.print("Enter your choice: ");

								choice = scanner.nextInt();

								switch (choice) {
								case 1:
									System.out.println("\nThe list of the Applicants:\n");
									JDB_Exporter.displayTable();
									break;
								case 2:
									JDB_Exporter.exportTableToFile();
									break;
								case 3:
							        try {
							        	CSVToDatabase.saveCSVToDatabase(); // Call the method to save CSV to MySQL
							            System.out.println("CSV data successfully saved to MySQL table.");
							        } catch (Exception e) {
							            e.printStackTrace();
							        }
							        break;
								case 4:
									Menu();
									break;
								case 0:
									System.out.println("Thank you for your session!");
									break;
								default:
									System.out.println("Invalid choice. Please enter a valid option.");
								}
							} catch (InputMismatchException e) {
								// Handle the exception when the user enters a non-integer value
								System.out.println("Invalid input. Please enter a valid option.");
								// Clear the scanner buffer
								scanner.next();
								// Set choice to a value that will loop again
								choice = -1;
							}
						} while (choice != 0);
						break;
					} else {
						Menu();
						break;
					}
				case 0:
					System.out.println("\nThank you for visiting us!");
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
				}
			} catch (InputMismatchException e) {
				// Handle the exception when the user enters a non-integer value
				System.out.println("Invalid input. Please enter a valid option.");
				// Clear the scanner buffer
				scanner.next();
				// Set choice to a value that will loop again
				choice = -1;
			}
		} while (choice != 0);
		scanner.close();
		System.exit(0);
	}
}
