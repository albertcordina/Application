1. The aim of the program:
To assist the applicants in applying for an extra monetary support from the government according to the specific requirements.

2. Component Design:

 - The program consists of the following 8 classes:
  the 'Main' class (from where the code gets run);
  the parent superclass 'Methods' and 'Info';
  the 5 children subclasses to Class 'Info':

   Student;
   Employed;
   Unemployed;
   Retired;
   Registered;

 -   The following Menu(s) with the options for the users:

  1. The main Menu:
    1. Start the application;
    2. Visit your personal profile;
    3. Exit the program;

  2. The Menu of the occupational status of the applicant:
    1. Student;
    2. Employed;
    3. Unemployed;
    4. Retired;
    5. Back to the main Menu;

  3. The Menu of the profile:
    1. See the current status of the application;
    2. Amend the name;
    3. Amend the surname;
    4. Amend the age;
    5. Amend the contact phone number;
    6. Amend the password;
    7. Delete the profile;
    8. Back to the main Menu;
    9. Exit the program; 

 - The HashMap with the custom objects for:
  1. generating a virtual profile of the customer (their unique personal ID as their personal contact phone number 
    to access their personal data);
  2. store the private information of the customer (name, age, 
    occupational status, phone number, password);
  3. store the info affiliated with the application (status).

  It should help the applicant to:

  1. access their personal profile;
  3. track the current status of their application;
  4. enter an additional required information (e.g. missing info from the 
     previous session);
  5. amend the current personal information; 
  6. remove their personal profile, i.e. all their personal info;

3. Error and Exception Handling:

 The errors and exceptions are affiliated with cases of entering by the customer a non-requested information during the session, such as the following; 

 the figures or other characters instead of the letters:
    an invalid name or the surname of the applicant;

 the letters or other characters instead of the figures:
    an invalid age;
    an invalid option of the Menu(s) during the whole session (i.e. the 
  letters or any other character instead of the figures);
    an invalid contact phone number of the customer (restriction: 
   according to the existed numbers which contain from 7 to 15 digits  
   (including a country code));

 and also all other neccessary Exceptions (if there is any).

4. Security measures:

Before accessing a profile the Applicant is requested to enter its personal phone number and the password.
And also all other neccessary Security measures to improve the security of the private information of the users.

-------------------------------------------------------------------
Here is the list of the functions inside of each class:

Class Main:
 (runs the whole program)

 the program is starting with the Menu of the 3 options:

    1. Start the application;
    2. Visit your personal profile;
    3. Exit the program;

the explanation of the options of the Menu:

 1- “Start the application” should lead to the superlass “Info” for collection of the basic info (i.e. applicant’s name, age, e.c.t.) and it opens another Menu to select the occupational status of the applicant ( i.e. "Student", "Employed", "Unemployed", "Retired") with also the possiblility to exit the program;
 2- “Visit your personal profile” should lead to to the subclass “Registered”. It is the option to let the applicant visit its personal profile (i.e. the applicant has already visited us before and would like to update their profile, or to track the current status of their application).;
 3- “Exit the program” should exit the program;

Class Info:
 (superclass)

Starts with the collection of the basic info (i.e. applicant’s name, age, e.c.t.) and it opens another Menu to select the occupational status of the applicant ( i.e. "Student", "Employed", "Unemployed", "Retired") 
If the applicant is above the age of retirement 67, we ask the applicant about if they have already applied for the retirement pension.

  If the applicant did not apply for the pension yet:

    1. reffer the applicant to the pension department;
    2. request the applicant's contact phone number (as a 'username'). If the phone number is already stored, inform the applicant about it and send the applicant back to the main Menu. If the phone number is not in the list of the collector, store the phone number;                                                                                            
    3. request the applicant to enter/create their own password for a future visit of their personal profile. Store the password in the collector with the all other values (i.e. name, surname, e.t.c.);                                                                                                
    4. create a personal applicant's profile with a unique ID. Educate the applicant about the ID, and advise to track the status of the appication;                         
    5. send the applicant back to the main Menu;

  if the applicant receives the retirment pention: treat the user as the "Retired" (i.e. call the Class Retired).


Class Student:
 (subclass)

 Ask the applicant about the total income incuding a possible income besides the scoolarship;
 If the total income exides the 2000 Euros, educate the applicant about the policy;
 if the total income is below the 2000 Euros:                                                                                          
    1. request the applicant's contact phone number (as a 'username'). If the phone number is already stored, inform the applicant about it and send the applicant back to the main Menu. If the phone number is not in the list of the collector, store the phone number;                                                                                            
    2. request the applicant to enter/create their own password for a future visit of their personal profile. Store the password in the collector with the all other values (i.e. name, surname, e.t.c.);                                                                                                
    3. create a personal applicant's profile with a unique ID. Educate the applicant about the ID, and advise to track the status of the appication;                         
    4. send the applicant back to the main Menu;

Class Employed:
 (subclass)

 Ask the customer about the income incuding a possible income besides the salary; 
 If the pention and a possible additional income exides the 2000 Euros, educate the user about the policy;
if the salary with a possible additional income is below the 2000 Euros:                                                                                               
    1. request the applicant's contact phone number (as a 'username'). If the phone number is already stored, inform the applicant about it and send the applicant back to the main Menu. If the phone number is not in the list of the collector, store the phone number;                                                                                            
   
    2. request the applicant to enter/create their own password for a future visit of their personal profile. Store the password in the collector with the all other values (i.e. name, surname, e.t.c.);               
    3. create a personal applicant's profile with a unique ID. Educate the applicant about the ID, and advise to track the status of the appication;                         
    4. send the applicant back to the main Menu;

Class Unemployed:
 (subclass)

 Ask the applicant if they have already applied for the unemplyement benefits.

  If the customer did not apply for the benefits yet:

     1. reffer the applicant to the unemployment department; 
     2. request the applicant's contact phone number (as a 'username'). If the phone number is already stored, inform the applicant about it and send the applicant back to the main Menu. If the phone number is not in the list of the collector, store the phone number;                                                                                            
     3. request the applicant to enter/create their own password for a future visit of their personal profile. Store the password in the collector with the all other values (i.e. name, surname, e.t.c.);                                                                                                
     4. create a personal applicant's profile with a unique ID. Educate the applicant about the ID, and advise to track the status of the appication;                         
     5. send the applicant back to the main Menu;

  If the customer is having the unemployment benefits:

 Ask the applicant about the amount of their monthly unemployment benefits;
 Ask the customer if there is an additional income besides the benefits (e.g. a part or full time job, e.t.c.);
 If the benefits and a possible additional income exides the 2000 Euros, educate the user about the policy;
 if the benefits and a possible additional income is below the 2000 Euros:                                                                                 
    1. request the applicant's contact phone number (as a 'username'). If the phone number is already stored, inform the applicant about it and send the applicant back to the main Menu. If the phone number is not in the list of the collector, store the phone number;                                                                                            
     2. request the applicant to enter/create their own password for a future visit of their personal profile. Store the password in the collector with the all other values (i.e. name, surname, e.t.c.);                                                                                                
     3. create a personal applicant's profile with a unique ID. Educate the applicant about the ID, and advise to track the status of the appication;                         
4. send the applicant back to the main Menu;

Class Retired:
(subclass)

  Ask the applicant about the amount of their pention;
  Ask if the applicant has an additional income besides their pention (e.g. a part or full time job, e.t.c.);
  If the pention and a possible additional income exides the 2000 Euros, educate the user about the policy (i.e. that they are not intiteled for an additional support due to the regulations);
  If the pention and a possible additional income is less the 2000 Euros:                                                                                 
    1. request the applicant's contact phone number (as a 'username'). If the phone number is already stored, inform the applicant about it and send the applicant back to the main Menu. If the phone number is not in the list of the collector, store the phone number;                                                                                            
     2. request the applicant to enter/create their own password for a future visit of their personal profile. Store the password in the collector with the all other values (i.e. name, surname, e.t.c.);                                                                                                
     3. create a personal applicant's profile with a unique ID. Educate the applicant about the ID, and advise to track the status of the appication;                         
4. send the applicant back to the main Menu;

Class Registered:
(subclass)
i.e. the customer has the profile

1. request the applicant to enter their phone number as a username;
2. request the applicant to enter their password:
  if after 3 attempts of entering, either the phone number or password do not match or absent in the list of the collector, inform the applicant about the wrong input and send the applicant back to the main Menu;
 if within the 3 attempts, the phone number and password match, let 
 the applicant to access their profile and have the following options/possibilities:

  1. see the actual status of their application (if it has been already submitted);
  2. continue their application (if it was not submitted yet);
  3. amend the personal info of their profile;
  4. delete their profile;
  5. to go back to the main Menu;

Class Methods:
(superclass of Class Info and all other subclasses)

contains most of the methods of the projest (see all the methods inside the Class "Methods" with the explaination of their functions).
