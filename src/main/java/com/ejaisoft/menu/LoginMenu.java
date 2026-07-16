package com.ejaisoft.menu;

import com.ejaisoft.Utils.Validator;
import com.ejaisoft.model.Admin;
import com.ejaisoft.model.Student;
import com.ejaisoft.service.AdminService;
import com.ejaisoft.service.StudentService;

public class LoginMenu {
    private static StudentService ss;
    private static AdminService as;
    private static StudentMenu sm;
    private static AdminMenu am;

    public LoginMenu() {
        ss = new StudentService();
        as = new AdminService();
        sm = new StudentMenu();
        am = new AdminMenu();
    }

    public void login() {
        System.out.println("======================================");
        System.out.println("    LOGIN TO ACCESS SERVICES");
        System.out.println("======================================");
        System.out.println("1.Log in as student");
        System.out.println("2.Log in as admin");
        int choice = Validator.readInt("Enter your choice: ");

        switch (choice) {
            case 1:
                studentLoginDialogue();
                break;
            case 2:
                adminLogin();
                break;
            default:
                System.out.println("Invalid input,enter a valid choice");
        }

    }

    private static void studentLoginDialogue() {
        boolean status = true;

        System.out.println("========================================");
        System.out.println("   STUDENT'S CREDENTIALS TO LOGIN");
        System.out.println("========================================");
        while (status) {
            System.out.print("Username/email: ");
            String input = Validator.SCANNER.nextLine();
            System.out.println("Enter the password: ");
            String pass = Validator.SCANNER.nextLine();
            Student student = ss.authenticate(input, pass);
            if (student == null) {
                System.out.println("Invalid username/Password");
            } else {
                status = false;
                sm.showStudentMenu(student);
            }


        }
    }

    private static void adminLogin() {
        System.out.println("========================================");
        System.out.println("   ADMIN'S CREDENTIALS TO LOGIN");
        System.out.println("========================================");
        boolean status = true;
        while (status) {
            System.out.print("Username/email: ");
            String input = Validator.SCANNER.nextLine();
            System.out.println("Enter the password: ");
            String pass = Validator.SCANNER.nextLine();
            Admin admin = as.authenticate(input, pass);
            if (admin == null) {
                System.out.println("Invalid username/Password");
            } else {
                status = false;
                am.showAdminMenu(admin);
            }

        }
    }
}
