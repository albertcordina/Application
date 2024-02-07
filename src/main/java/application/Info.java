package application;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Info extends Methods {

	// The personal information:
	protected static String name;
	protected static String surname;
	protected static int age;
	protected static String password;
	protected static String statusOfApplication;
	protected static String occupation;
	protected static int phoneNumber;
    
        // The Income information:
	protected static int monthlySupport;
	protected static int monthlyPension;
	protected static int monthlyExtraIncome;
	protected static int monthlyScholarshipIncome;
	protected static int monthlySalaryIncome;
	protected static int monthlyUnemployementBenefits;

	protected static int overallIncome;
	
	// The 'yes' or 'no' option
    protected static String yesOrNo;
 

	static Scanner scanner = new Scanner(System.in);
	// The connection to the subclass 'JDBC' for providing the access to 'JDBC' other subclasses.
	static Map<Integer, Info> profiles = new HashMap<>();

	// The constructor for the HashMap 'profiles'
	public Info(String name, String surname, int age, String occupation, int overallIncome, String password,
			String statusOfApplication) {
		Info.name = name;
		Info.surname = surname;
		Info.age = age;
		Info.occupation = occupation;
		Info.overallIncome = overallIncome;
		Info.password = password;
		Info.statusOfApplication = statusOfApplication;
	}

	public Info() {
	} // The constructor for sharing its Info with other Classes

	public String toString() { // toString method for printing out the profile(s) of the HashMap 'profiles'
		return "\nYour name: " + name + "\nYour surname: " + surname + "\nYour age: " + age + "\nYour current occupation: " + occupation
				+ "\nYour overall income: " + overallIncome + "\nThe password to your account: " + password
				+ "\nThe status of your application: " + statusOfApplication;
	}

	// Getters and Setters to operate with the values of the variables

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Info.name = name;
	}
	
	public String getSurame() {
		return surname;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		Info.occupation = occupation;
	}

	public int getOverallIncome() {
		return overallIncome;
	}

	public void setOverallIncome(int overallIncome) {
		Info.overallIncome = overallIncome;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		Info.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Info.password = password;
	}

	public String getStatus() {
		return statusOfApplication;
	}

	public void setStatus(String status) {
		Info.statusOfApplication = status;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	/*
	 * The method 'collection' collects the basic information from the Applicant:
	 * 
	 * 1. Name, Surname; 
	 * 2. Age; 
	 * 3. If the Applicant is getting a current support from the government; 
	 * 4. Asks about a government pention (if the Applicant is over 67 years old); 
	 * 5. Asks about the Occupational status of the Applicant (i.e.
	 *    student, employed, unemployed, retired) and calls the corresponding methods
	 *    of the corresponding Classes;
	 */
	public static void collection() {

		System.out.println("\nThank you!\nFor a further review, we need some information from you.\n");

		// collect the basic personal information from the Applicant (i.e. name, age)
		System.out.print("\nWhat is your name?\n");
		name = getAllLetters();
		System.out.println("\nHello, " + name + "!");
		
		System.out.print("\nWhat is your surname?\n");
		surname = getAllLetters();

		System.out.print("\nWhat is your age?\n");
		age = getPositiveInt();

		// Ask the Applicant about a current support
		System.out.println("\nAre you getting any other current support from the government?");
		yesOrNo = yesOrNo();

		if (yesOrNo.equals("yes")) {
			System.out
					.println("\nThank you!\nWhat is the monthly amount you are currently getting from the government?");
			monthlySupport = getPositiveInt();

			if (monthlySupport >= 2000) {
				System.out.println("\n\tDear " + name + ",\nThe amount you are currently getting from "
						+ "the governmen is sufficient and isn't applicable for the application.\nThank you for visiting us!");
				System.exit(0);
			}
		}

		// Ask the Applicant who are over 67 years old about the pension
		if (age >= 67) {
			System.out.println(
					"\nAccording to your age, you are eligible for getting a pension.\nHave you applied for your pension?");
			yesOrNo = yesOrNo();

			// If the Applicant is on pension, call the method 'eligibility' in the subclass
			// 'Retired'
			if (yesOrNo.equals("yes")) {
				Retired.eligibility();

				// If the Applicant has not applied for the pension, reffer the Applicant to the
				// appropriate Social Services
			} else {
				System.out.println("\n\tDear " + name
						+ ",\nAccording to your age and the regulation of the social service support you are "
						+ "eligible for the retirement pension. \nTherefore, we advise you to contact your local social servise for "
						+ "clarification of this subject and then inform us about the outcome.\n");

				// Check if the Applicant is having an extra income
				System.out.println(
						"Are you having an extra monthly income (e.g. from your part or full time job, e.t.c.)?");
				yesOrNo = yesOrNo();

				// If yes, Collect the amount of the exta monthly income
				if (yesOrNo.equals("yes")) {
					System.out.println("\nPlease, enter the amount of your extra income.");
					monthlyExtraIncome = getPositiveInt();
				}

				// Call the method 'createAccount' If the Applicant is eligible reffer to
				// 'createAccount'
				createAccount();
				
				// Set the 'occupation' as a Retired with the special Note
				Info.occupation = "Retired (needs more info from Social Service)";
				
				// call the static method 'saveToDatabase' in the class 'JDBC' to save the info into the table
				JDBC.saveToDatabase();
				
				// Place the 'occupation' of the Applicant in the already created profile via the 'createAccount' method
			/*	profiles.put(phoneNumber, new Info(name, surname, age, "Retired (needs outcome from Social Service)",
						overallIncome, password, statusOfApplication)); */
				
				Main.Menu(); // After gathering the info and creating the profile, send the Applicant to the
								// main Menu

			}
		}

		// The Menu of identifying the Occupational status of the Applicant
		int option;

		do {
			System.out.println("\nPlease, enter you current occupational status:"
					+ "\n1. Student;\n2. Employed;\n3. Unemployed;\n4. Retired;\n5. Back to the main Menu;\n0. Exit the program;");

			try {
				System.out.print("Enter your choice: ");

				option = scanner.nextInt();

				switch (option) {
				case 1:
					Student.eligibility();
					break;
				case 2:
					Employed.eligibility();
					break;
				case 3:
					Unemployed.eligibility();
					break;
				case 4:
					Retired.eligibility();
					break;
				case 5:
					Main.Menu();
					break;
				case 0:
					System.out.println("Thank you for visiting us!");
					System.exit(0);
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
				option = -1;
			}
		} while (option != 0);
	}
}
