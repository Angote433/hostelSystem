package com.ejaisoft.menu;

import java.util.Scanner;

public class LoginMenu {
    private static Scanner userInput = new Scanner(System.in);
    public void login(){
        System.out.println("======================================");
        System.out.println("    LOGIN TO ACCESS SERVICES");
        System.out.println("======================================");
        System.out.println("1.Log in as student");
        System.out.println("2.Log in as admin");
        System.out.print("Enter your choice: ");
        int choice = userInput.nextInt();
        userInput.nextLine();

        switch(choice){
            case 1:
                studentLoginDialogue();
                break;
            case 2:
                System.out.println("Admin login coming soon");
                break;
            default:
                System.out.println("Invalid input,enter a valid choice");
        }

    }

    private static void studentLoginDialogue(){
        System.out.println("========================================");
        System.out.println("   STUDENT'S CREDENTIALS TO LOGIN");
        System.out.println("========================================");
        System.out.print("Username/email: ");
        String input = userInput.nextLine();
        System.out.print("Enter your password: ");
        String passInput = userInput.nextLine();

        //check if the student exists by username and email
        //check if password matches stored password.
        //if yes - login,ifno throw wrongcredentials(invalid username/password

        System.out.println("validation coming soon");
    }

    private static void adminLogin(){
        System.out.println("========================================");
        System.out.println("   ADMIN'S CREDENTIALS TO LOGIN");
        System.out.println("========================================");
        System.out.print("Username/email: ");
        String input = userInput.nextLine();
        System.out.print("Enter your password: ");
        String passInput = userInput.nextLine();

        //admin exists by email/username?
        //password match
        //ifyes - login  if no -throw error

        System.out.println("Validation coming soon.");


    }
}
