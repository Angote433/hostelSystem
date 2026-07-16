package com.ejaisoft.menu;

import com.ejaisoft.Utils.Validator;
import com.ejaisoft.model.Gender;
import com.ejaisoft.model.Student;
import com.ejaisoft.service.StudentService;

public class MainMenu {
    private static StudentService service = new StudentService();
    private static LoginMenu loginMenu = new LoginMenu();

    public void runHostel() {
        boolean state = true;

        while (state) {
            System.out.println("******* WELCOME TO THE HOSTEL  SYSTEM *******");
            System.out.println("1.Create Account (For students)");
            System.out.println("2.Login(Student/Admin)");
            System.out.println("3.Exit");
            int userChoice = Validator.readInt("Enter your choice: ");

            switch (userChoice) {
                case 1:
                    createAccountMenu();
                    break;
                case 2:
                    loginMenu.login();
                    break;
                case 3:
                    System.out.println("Thank you for visiting us,Goodbye!");
                    state = false;
                    break;
                default:
                    System.out.println("Invalid input!Enter  the right choice.");


            }

        }
    }

    private static void createAccountMenu() {
        System.out.println("====================================");
        System.out.println("   CREATE STUDENT ACCOUNT");
        System.out.println("====================================");
        String studentName = Validator.readNonBlank("Enter your full name: ");
        String regNumber = Validator.readNonBlank("Registration Number: ");
        String userName = Validator.readNonBlank("Username: ");

        String email;
        while (true) {
            email = Validator.readNonBlank("Email: ");
            if (Validator.isValidEmail(email)) {
                break;
            }
            System.out.println("Please enter a valid email address.");
        }

        String mobileNumber = Validator.readNonBlank("Mobile number: ");

        Gender gender;
        while (true) {
            String genderInput = Validator.readNonBlank("Gender(Male/Female): ");
            try {
                gender = Gender.valueOf(genderInput.trim().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter Male or Female.");
            }
        }

        boolean passStatus = true;

        while (passStatus) {
            System.out.println("Set up your password");
            System.out.print("Enter the password you will use: ");
            String firstAttempt = Validator.SCANNER.nextLine();
            System.out.print("Confirm your password: ");
            String secondPassAttempt = Validator.SCANNER.nextLine();

            if (firstAttempt.equals(secondPassAttempt)) {
                Student student = new Student(regNumber, studentName, userName, firstAttempt, email, mobileNumber, gender);
                try {
                    service.onboardStudent(student);
                    System.out.println("Account created successfully Proceed to login");
                } catch (RuntimeException e) {
                    System.out.println("Could not create account: " + e.getMessage());
                }
                passStatus = false;
            } else {
                System.out.println("Password mismatch!! ");
            }
        }


    }
}
