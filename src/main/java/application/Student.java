package application;

public class Student extends Info {

	// The method 'eligibility': Checks if the Applicant is eligible for the application
	static void eligibility() {
  
		// The collection of the info
		System.out.println("\nAre you getting your scholarship as a student?");
		yesOrNo = yesOrNo();

		// Check if the Applicant is getting the scoolarship
		if (yesOrNo.equals("yes")) {
			System.out.println("\nPlease, enter the monthly amount of your scholarship.");
			monthlyScholarshipIncome = getPositiveInt();
		}

		/*
		 * Check an extra income and determine if the Applicant has a sufficient overallIncome.
		 *   For those who have the sufficient income: education, and send back to the main menu.
		 *   For those who don't have the sufficient income: send to the registration 
		 *      (see the bottom of the 'checkExtraAndOverallIncome' mehtod in the class 'Methods')
		 */
		checkExtraAndOverallIncome();
		
		// Set the 'occupation' as a Student
		Info.occupation = "Student";
		
		// call the static method 'saveToDatabase' in the class 'JDBC' to save the info into the table
		JDBC.saveToDatabase();
		
		// Place the 'occupation' of the Applicant in the already created profile via the 'createAccount' method
		//profiles.put(phoneNumber, new Info(name, surname, age, "Student", overallIncome, password, statusOfApplication));
			
		Main.Menu(); // After gathering the info and creating the profile, send the Applicant to the main Menu
	}
}
