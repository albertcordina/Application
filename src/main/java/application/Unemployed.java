package application;

public class Unemployed extends Info {
 
	// The method 'eligibility': Checks if the Applicant is eligible for the application
	static void eligibility() {

		// Ask the Applicant about the monthly unemployment benefits
		System.out.println("\nAre you getting the monthly unemployement benefits?");
		yesOrNo = yesOrNo();

		// Collect the amount of the monthly unemployment benefits
		if (yesOrNo.equals("yes")) {
			System.out.println("\nPlease, enter the amount of the monthly unemployment benefits.");
			monthlyUnemployementBenefits = getPositiveInt();
		}

		/*
		 * If the Applicant has not applied for the unemplyment benefits yet, reffer the Applicant
		 * to the appropriate Social Services, ask about an Extra income and determine the eligibility,
		 * and place a special Note: "Unemployed (needs info from the Unemployment Service.)" 
		 * in the profile, i.e. the 'occupation'.
		 */
		if (yesOrNo.equals("no")) {
			System.out.println("\n" + name + ", according to the regulation of the social service support you are "
					+ "eligible for the unemployment benefits. \nTherefore, we advise you also to contact your local unemployement"
					+ " servise for clarification of this subject and then inform us about the outcome.");
			
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
			Info.overallIncome = Info.monthlySupport + Info.monthlyExtraIncome + Info.monthlyUnemployementBenefits;
			if (Info.overallIncome >= 2000) { // we check the applicant's income altogether
				System.out.println("\n\tDear " + Info.name + ",\nThe amount of your income is considered as sufficient and "
						+ "it fully covers the amount which is stipulated within this support program.\nThank you for visiting us!");
				System.exit(0);
			}

			// Call the method 'createAccount' If the Applicant is eligible reffer to 'createAccount'
			createAccount();
			
			// Set the 'occupation' as a Unemployed with the special mark
			Info.occupation = "Unemployed (needs info from the Unemployment Service.)";
			
			// call the static method 'saveToDatabase' in the class 'JDBC' to save the info into the table
			JDBC.saveToDatabase();
			
			// Place the 'occupation' of the Applicant with the special NOTE () in the already created profile via the 'createAccount' method
			//profiles.put(phoneNumber, new Info(name, surname,age, occupation, overallIncome, password, statusOfApplication));
			
			Main.Menu(); // After gathering the info and creating the profile, send the Applicant to the main Menu
			
		}

		/*
		 * For those who get the unempoyment benefits:
		 * Check an extra income and determine if the Applicant has a sufficient overallIncome.
		 *   For those who have the sufficient income: education, and send back to the main menu.
		 *   For those who don't have the sufficient income: send to the registration 
		 *      (see the bottom of the 'checkExtraAndOverallIncome' mehtod in the class 'Methods')
		 */
		checkExtraAndOverallIncome();
		
		// Set the 'occupation' as a Unemployed
		Info.occupation = "Unemployed";
		
		// call the static method 'saveToDatabase' in the class 'JDBC' to save the info into the table
		JDBC.saveToDatabase();
		
		// Place the 'occupation' of the Applicant in the already created profile via the 'createAccount' method
	//	profiles.put(phoneNumber, new Info(name, surname, age, occupation, overallIncome, password, statusOfApplication));
			
		Main.Menu(); // After gathering the info and creating the profile, send the Applicant to the main Menu

	}
}
