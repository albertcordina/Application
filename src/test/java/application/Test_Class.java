package application;

import org.junit.Test;

public class Test_Class {
	  
	@Test
	 public void testing_methods_class_Methods () {
		
		System.out.println("Testing the methods of the class 'Methods'\n\n");
		
		System.out.println("The method 'getAllLetters' for collecting only the letters:\n");
		Methods.getAllLetters();
		
		System.out.println("\nThe method 'getPositiveInt' for collecting only a positive number:\n");
		Methods.getPositiveInt();
		
		System.out.println("\nThe method 'yesOrNo' for collecting only the two options,\n"
				+ " i.e. either 'yes' or 'no' regardless the size of the letters:\n");
		Methods.yesOrNo();
		
		System.out.println("\nThe method 'getValidPhoneNumber' for collecting the only possibly existed\n"
				+ "phone numbers which contain from 7 to 15 digits (including a country code):\n");
		Methods.getValidPhoneNumber(); 
		
		System.out.println("\nThe method 'getValidPassword' for collecting the password corresponding to the requirements, i.e. at least\n"
				+ "12 characters long and has the combination of at least one uppercase letter, lowercase letter, number, and a symbol:\n");
		Methods.getValidPassword();
		
		/*
		 * The method 'createAccount' consists of the two above methods, i.e. 'getValidPhoneNumber' and 'getValidPassword'.
		 * The method 'enterPasswordToAccess' requests the matching password from the Applicant within the 3 attempts.
		 * The method 'checkExtraAndOverallIncome' determines if the Applicant has a sufficient income, i.e. > 2000 Euros.
		 *                                      It also calculates all the previously (out of the method) collected income.
		 */
		 
	 }
}
