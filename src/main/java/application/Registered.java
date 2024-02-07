package application;

import java.util.InputMismatchException;

public class Registered extends Info {
	
	/*
	 * The method 'accessAccount': Ask for the contact phone number and the password
	 * of the already registered Applicant
	 */
	static void accessAccount() {
 
		System.out.println( // Ask the Applicant to enter the personal contact phone number, i.e. the 'key'
				"\nIn order to enter to your personal account we need your personal/ registered contact phone number.");
		phoneNumber = getPositiveInt();
 
	//	if (profiles.containsKey(phoneNumber)) { // for HashMap		
		
		if (JDBC.isPhoneNumberExists(phoneNumber)) {
			
			// get and assign the value of the password for the check if access is possible (with 3 attempts)
			String password = (JDBC.getValueByPhoneNumber("password"));
			
			// check if the access is granted
			System.out.println("\nPlease, enter your password.");
			if (enterPasswordToAccess(password)) {Registered.manageAccount();}
			
			// if run out of attempts send the Applicant back to the main Menue
			else {Main.Menu();}
		} else { // If the entered contact phone number is not in the list, send the Applicant to the main Menu
			System.out.println("It looks like you are not registered yet. You are back to the main Menu.\n");
			Main.Menu();
		}
	}

	/*
	 * The method 'manageAccount': The Menu of the profile to manage it (i.e. the
	 * amendment of the personal info and the status of the application)
	 */
	static void manageAccount() {

		System.out.println(JDBC.getValueByPhoneNumber("name") + ", welcome to your account!\n");
		int choice;

		do {
			System.out.println(
					"\n\tMenu:\n1. See and manage the current status of the application;\n2. Amend the name;\n3. Amend the surname;\n4. Amend the age;"
							+ "\n5. Amend the contact phone number;\n6. Amend the password;\n7. Delete the profile;\n8. Back to the main Menu;"
							+ "\n0. Exit the program;");

			try {
				System.out.print("Enter your choice: ");

				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					
					do {
						System.out.println(
								"\n\tMenu:\n1. See the current status of the application;\n2. Edit the status;"
								+ "\n3. Back to the account Menu;\n0. Exit the program;");

						try {
							System.out.print("Enter your choice: ");

							choice = scanner.nextInt();
							scanner.nextLine(); // Consume the newline character

							switch (choice) {
							case 1:		           
     							System.out.println("The current status:\n" + JDBC.getValueByPhoneNumber("statusofapplication"));
							//	System.out.println("The current status:\n" + profiles.get(phoneNumber).getStatus());
								
								break;
							case 2:
								System.out.print("Enter your text: ");
								String newStatus = scanner.nextLine();
								
								// save the updated status also in the database via the method 'updateValueString'
			                    JDBC.updateValueString("statusofapplication", newStatus);
			                    
								// save the updated status in the HashMap								
							/*	profiles.get(phoneNumber).setStatus(newStatus);
								System.out.println("\nThe status has been updated successfully!"); */
								
								break;
							case 3:
								manageAccount();
								break;
							case 0:
								System.out.println("Thank you for your session!");
								System.exit(0);
								break;
							default:
								System.out.println("Invalid choice. Please enter a valid option.");
								break;
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
				case 2:
					System.out.println("\nEnter your new name.");
					String newName = getAllLetters();
					
					// save the new name also in the database via the method 'updateValueString'
					JDBC.updateValueString("name", newName);
					
					// saving the new number in HashMap is not valid due to the absence of any initial record of the already registered users.
				/*	profiles.get(phoneNumber).setName(newName);
					System.out.println(newName + ", your profile has been successfully updated.");
					System.out.println("Your contact phone number: " + phoneNumber + "\n" + profiles.get(phoneNumber)); */
         
					break;
				case 3:
					System.out.println("\nEnter your new surname.");
					String newSurname = getAllLetters();
					
					// save the new surname in the database via the method 'updateValueString'
					JDBC.updateValueString ("surname", newSurname);	
					
				/*	profiles.get(phoneNumber).setName(newSurname);
					System.out.println(newSurname + ", your profile has been successfully updated.");
					System.out.println("Your contact phone number: " + phoneNumber + "\n" + profiles.get(phoneNumber)); */
										
					break;
				case 4:
					System.out.println("\nEnter your age.");
					int newAge = getPositiveInt();
					
					// save the updated age in the database via the method 'updateValueInt'
					JDBC.updateValueInteger ("age", newAge);	
					
				/*	profiles.get(phoneNumber).setAge(newAge);
					System.out.println(name + ", your profile has been successfully updated.");
					System.out.println("Your contact phone number: " + phoneNumber + "\n" + profiles.get(phoneNumber)); */
				
					break;
				case 5:
					System.out.println("\nEnter your new personal phone number.");
					int newPhoneNumber = getValidPhoneNumber();

				/*	if (profiles.containsKey(newPhoneNumber)) {
						System.out.println("The phone number you entered is not valid for the amendment.");
						manageAccount();
					} */
					
					// check if the new phone number is already in the database
					if (!JDBC.isPhoneNumberExists(phoneNumber)) {
						System.out.println("The phone number you entered is not valid for the amendment.");
						manageAccount(); // if the new phone number is in the database, send the user back to the menu
					}
                                     // if the new phone number is not yet in the database:
					
					// save the new phone number in the database via the method 'updateValueInteger'
					JDBC.updateValueInteger ("phone_number", newPhoneNumber);
					
					/* save the new phone number in HashMap (not valid due to the absence of a record 
					of the already registered user in HashMap, which works only for one session).
					
					profiles.put(newPhoneNumber,
							new Info(profiles.get(phoneNumber).getName(), 
									profiles.get(phoneNumber).getSurame(),
									profiles.get(phoneNumber).getAge(),
									profiles.get(phoneNumber).getOccupation(),
									profiles.get(phoneNumber).getOverallIncome(),
									profiles.get(phoneNumber).getPassword(), profiles.get(phoneNumber).getStatus()));

					profiles.remove(phoneNumber); // remove the profile with the old key, i.e., the old phone number
					System.out.println(name + ", your profile has been successfully updated.");
					System.out.println("\nYour contact phone number: " + newPhoneNumber + profiles.get(newPhoneNumber)); */
					
					break;
				case 6:
					System.out.println("\nEnter your new password.");
					String newPassword = getValidPassword();
										
					// save the new password also in the database via the method 'updateValueString'
					JDBC.updateValueString ("password", newPassword);
					
					// save the new password in the HashMap
				/*	profiles.get(phoneNumber).setPassword(newPassword);
					System.out.println(name + ", your profile has been successfully updated.");
					System.out.println(profiles.get(phoneNumber)); */
					
					break;
				case 7:
					System.out.println(name + ", your profile has been removed.");
					profiles.remove(phoneNumber); // remove the profile in the HashMap
					
					// delete the profile(row) also in the database via the method 'deleteRow'
					JDBC.deleteRow (); 
					Main.Menu();
					break;
				case 8:
					Main.Menu();
					break;
				case 0:
					System.out.println("Thank you for your session!");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
					break;
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
	}
}
