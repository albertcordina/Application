package application;

public class Employed extends Info {

	// The method 'eligibility': Checks if the Applicant is eligible for the application
	static void eligibility() {

		// Ask the Applicant about the monthly salary
		System.out.println("What is your monthly salary?");
		monthlySalaryIncome = getPositiveInt();
 
		/*
		 * Check an extra income and determine if the Applicant has a sufficient overallIncome.
		 *   For those who have the sufficient income: education, and send back to the main menu.
		 *   For those who don't have the sufficient income: send to the registration 
		 *      (see the bottom of the 'checkExtraAndOverallIncome' method in the class 'Methods')
		 */
		checkExtraAndOverallIncome();

		// Place the 'occupation' of the Applicant in the already created profile via
		// the 'createAccount' method
		//profiles.put(phoneNumber, new Info(name, surname, age, occupation, overallIncome, password, statusOfApplication));
		
		// Set the 'occupation' as a Student
		Info.occupation = "Employed";
		
		// call the static method 'saveToDatabase' in the class 'JDBC' to save the info into the table
		JDBC.saveToDatabase();
		
		Main.Menu(); // After gathering the info and creating the profile, send the Applicant to the main Menu

	}
}
