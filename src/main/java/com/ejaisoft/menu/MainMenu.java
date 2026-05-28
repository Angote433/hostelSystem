package com.ejaisoft.menu;

import com.ejaisoft.model.Gender;
import com.ejaisoft.model.Student;
import com.ejaisoft.service.StudentService;

import java.util.Scanner;

public class MainMenu {
    private static Scanner userInput = new Scanner(System.in);
    private static StudentService service= new StudentService();
    private static LoginMenu loginMenu = new LoginMenu();
    static void main() {
        boolean state = true;

        while (state) {
            System.out.println("******* WELCOME TO THE HOSTEL  SYSTEM *******");
            System.out.println("1.Create Account (For students)");
            System.out.println("2.Login(Student/Admin)");
            System.out.println("3.Exit");
            System.out.print("Enter your choice: ");
            int userChoice = userInput.nextInt();

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

    private static void createAccountMenu(){
        System.out.println("====================================");
        System.out.println("   CREATE STUDENT ACCOUNT");
        System.out.println("====================================");
        userInput.nextLine();
        System.out.print("Enter your full name: ");
        String studentName = userInput.nextLine();
        System.out.print("Registration Number: ");
        String regNumber = userInput.nextLine();
        System.out.print("Username: ");
        String userName = userInput.nextLine();
        System.out.print("Email: ");
        String email = userInput.nextLine();
        System.out.print("Mobile number: ");
        String mobileNumber = userInput.nextLine();
        System.out.print("Gender(Male/Female): ");
        String gender = userInput.nextLine();

        boolean passStatus = true;

        while(passStatus) {
            System.out.println("Set up your password");
            System.out.print("Enter the password you will use: ");
            String firstAttempt = userInput.nextLine();
            System.out.print("Confirm your password: ");
            String secondPassAttempt = userInput.nextLine();

            if(firstAttempt.equals(secondPassAttempt)){
                Student student = new Student(regNumber,studentName,userName,firstAttempt,email,mobileNumber, Gender.valueOf(gender.toUpperCase()));
                service.onboardStudent(student);
                System.out.println("Account created successfully Proceed to login");
                passStatus = false;
            }else{
                System.out.println("Password mismatch!! ");
            }
        }



    }
}
