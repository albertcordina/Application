package application;

import java.util.*;

public class Methods { // Contains almost all methods of the project
	
	private static Scanner scanner = new Scanner (System.in);

	
	// Method 'getAllLetters': Takes only the letters from the Applicant's input
	public static String getAllLetters() {

		while (true) {
			System.out.print("Please, enter: ");
 
			try {
				/*
				 * the input logic to use scanner.next() instead of scanner.nextLine(): i.e. to
				 * ensure that the newline character does not interfere with subsequent inputs.
				 */
				String input = scanner.next();
				if (isAllLetters(input.trim())) {
					// trim method is used to remove leading and trailing whitespace from the input.
					return input.trim();
				} else {
					System.out.println("Invalid input. Please enter only letters.");
				}
			} catch (Exception e) {
				System.out.println("An error occurred. Please try again.");
			} finally {
				scanner.nextLine(); // Clear the buffer
			}
		}
	}
	/*
	 * the isAllLetters method checks whether the input consists only of letters
	 * (affiliated with the 'getAllLetters' above).
	 */
	private static boolean isAllLetters(String input) {
		return !input.isEmpty() && input.chars().allMatch(Character::isLetter);
	}
//---------------------------------------------------------------------------------
	
	// Collects a positive number from the Applicant
	public static int getPositiveInt() {

		int userInput = 0;

		do {
			try {
				System.out.print("Please, enter: ");
				userInput = scanner.nextInt();

				if (userInput <= 0) {
					System.out.println("Please, enter a positive number greater than 0.");
				}
			} catch (java.util.InputMismatchException e) { // Handle non-integer input
				System.out.println("Invalid input.");
				scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
				userInput = 0; // Set userInput to 0 to continue the loop
			}
		} while (userInput <= 0);
		// At this point, userInput contains a valid positive integer
		return userInput;
	}
//----------------------------------------------------------------------------------
	
	// Takes 'yes' or 'not' response from the applicant
	public static String yesOrNo() {

		System.out.print("Please, enter 'yes' or 'no': ");
		String input = scanner.next().toLowerCase();

		while (!input.equals("yes") && !input.equals("no")) {
			System.out.print("Please, enter 'yes' or 'no': ");
			input = scanner.next().toLowerCase();
		}
		return input;
	}
//-----------------------------------------------------------------------------------
	
	/*
	 * Takes only the existed phone numbers which contain from 7 to 15 digits
	 * (including a country code)
	 */
	public static int getValidPhoneNumber() {

		int validPhoneNumber;

		do {
			System.out.print("Enter a number with 7 to 15 digits: ");

			try {
				/*
				 * the input logic to use scanner.next() instead of scanner.nextLine(): i.e. to
				 * ensure that the newline character does not interfere with subsequent inputs.
				 */
				validPhoneNumber = Integer.parseInt(scanner.next());
				// Check if the number of digits is between 7 and 15 (inclusive)
				if (String.valueOf(validPhoneNumber).length() >= 7 && String.valueOf(validPhoneNumber).length() <= 15) {
					break; // Exit the loop if the input is valid
				} else {
					System.out.println("Number must have 7 to 15 digits. Try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
				// Clear the buffer
				scanner.nextLine();
			}
		} while (true);

		return validPhoneNumber;
	}
//------------------------------------------------------------------------------------------------------
	
	/*
	 * Takes the password from the Applicant corresponding to the requirments (i.e.
	 * at least 12 characters long and has the combination of at least one uppercase
	 * letter, lowercase letter, number, and a symbol.
	 */
	public static String getValidPassword() { // Repeatedly prompts the user until a valid password is
																// entered.

		while (true) {
			System.out.println(
					"Create a password with at least 12 characters, including:\n- At least one uppercase letter"
							+ "\n- At least one lowercase letter\n- At least one number\n- At least one symbol");

			System.out.print("Enter your password: ");
			/*
			 * the password input logic to use scanner.next() instead of scanner.nextLine():
			 * i.e. to ensure that the newline character does not interfere with subsequent
			 * inputs.
			 */
			try {
				String userPassword = scanner.next();
				if (isValidPassword(userPassword)) {
					return userPassword;
				} else {
					System.out.println("Invalid password. Please try again.\n");
				}
			} catch (Exception e) {
				System.out.println("An error occurred. Please try again.");
			} finally {
				scanner.nextLine(); // Clear the buffer
			}
		}
	}

	/*
	 * Checks the validation of the entered password (affiliated with the
	 * 'getValidPassword' above)
	 */
	public static boolean isValidPassword(String password) {

		// Check if the password is at least 12 characters long
		if (password.length() < 12) {
			return false;
		}

		// Check for at least one uppercase letter, lowercase letter, number, and symbol
		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasNumber = false;
		boolean hasSymbol = false;

		for (char ch : password.toCharArray()) {
			if (Character.isUpperCase(ch)) { // Check if the password contains an UpperCase
				hasUppercase = true;
			} else if (Character.isLowerCase(ch)) { // Check if the password contains a LowerCase
				hasLowercase = true;
			} else if (Character.isDigit(ch)) { // Check if the password contains a Digit
				hasNumber = true;
			} else {
				// You can customize the symbols according to your requirements
				String symbols = "!@#$%^&*()-_+=<>?";
				if (symbols.indexOf(ch) != -1) {
					hasSymbol = true;
				}
			}
		}
		// Return true if all criteria are met
		return hasUppercase && hasLowercase && hasNumber && hasSymbol;
	}
	
//------------------------------------------------------------------------------------------------------------

	/*
	 * The method 'createAccount': Ask the Applicant for the contact phone number and the password
	 * to create the profile (contains the two above methods, i.e. 'getValidPhoneNumber' and 'getValidPassword')
	 */
	public static void createAccount() {

		// Collect the contact phone number of the Applicant
		System.out.println("\nDear " + Info.name
				+ ",\nWe are happy to inform you that your application will be reviewed by our specialized team.\n"
				+ "In order to create your personal account, we need to have your personal contact phone number.");
		Info.phoneNumber = getValidPhoneNumber();

		/*
		 * // If the phone number is in the list, send the Applicant to the Class
		 * 'Registered' if (Info.profiles.containsKey(Info.phoneNumber)) {
		 * System.out.println("It looks that you are already registered.");
		 * Registered.accessAccount(); }
		 */

		if (JDBC.isPhoneNumberExists(Info.phoneNumber)) {
			System.out.println("It looks that you are already registered.");
			Registered.accessAccount();
		}

		// Create the password
		System.out.println("\nThank you!\nPlease, create your password.");
		Info.password = getValidPassword();

		// Place the collected info into the 'profiles'
		Info.profiles.put(Info.phoneNumber, new Info(Info.name, Info.surname, Info.age, Info.occupation,
				Info.overallIncome, Info.password, Info.statusOfApplication));

		System.out.println("\n\nDear " + Info.name
				+ ",\n\nThank you for entering your personal details. Your account with us has been successfully created."
				+ "\nPlease, use your phone number as the login to your personal profile. We also advise you to check the status of "
				+ "your application and add any required info\n(e.g. the results of Social Security department regarding your "
				+ "unemployment benefits/ pension, e.t.c.) in the section: 'The status of the application'.\n\nThe Team of the 'Support Program'.");
	}
//-------------------------------------------------------------------------------------------------
	
// The method 'enterPasswordToAccess': Requests the matching password from the Applicant within the 3 attempts
	public static boolean enterPasswordToAccess (String password) {

		int maxAttempts = 3; // the set 3 attempts

		System.out.println("You have 3 attempts:");

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			System.out.print("Attempt " + attempt + ": Enter your password: ");

			try {
				String userGuess = scanner.next();
				if (userGuess.equals(password)) {
					System.out.println("Thank you!");
					return true; // Password entered successfully
				} else {
					System.out.println("Incorrect input. Try again.");
				}
			} catch (Exception e) {
				System.out.println("An error occurred. Please try again.");
			} finally {
				scanner.nextLine(); // Clear the buffer
			}
		}

		System.out.println("Sorry, you've run out of attempts.");
		return false; // Password not entered successfully within the allowed attempts
	}
//-----------------------------------------------------------------------------------------------------
	
// The method 'checkExtraAndOverallIncome' determines if the Applicant has a sufficient income
	public static void checkExtraAndOverallIncome() {

		// Check if the Applicant is having an extra income
		System.out.println("\nAre you having an extra monthly income?");
		Info.yesOrNo = yesOrNo();

		// If yes, Collect the amount of the exta monthly income
		if (Info.yesOrNo.equals("yes")) {
			System.out.println("\nPlease, enter the amount of your extra income.");
			Info.monthlyExtraIncome = getPositiveInt();
		}
		/*
		 * Check if the applicant's overall income reaches the 2000 Euros: if the amount
		 * exceeds the 2000 Euros -> Inform the Applicant about it; if the amount is
		 * below the 2000 Euros -> Call the method 'createAccount' in the superclass
		 * 'Methods'
		 */
		Info.overallIncome = Info.monthlySupport + Info.monthlyPension + Info.monthlyExtraIncome
				+ Info.monthlyScholarshipIncome + Info.monthlySalaryIncome + Info.monthlyUnemployementBenefits;
		if (Info.overallIncome >= 2000) { // we check the applicant's income altogether
			System.out.println("\n\tDear " + Info.name + ",\nThe amount of your income is considered as sufficient and "
					+ "it fully covers the amount which is stipulated within this support program.\nThank you for visiting us!");
			System.exit(0);
		}

		// Call the method 'createAccount' If the Applicant is eligible reffer to 'createAccount'
		createAccount();
	}
}
