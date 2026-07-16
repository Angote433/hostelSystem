package com.ejaisoft.menu;

import com.ejaisoft.Utils.Validator;
import com.ejaisoft.model.Student;
import com.ejaisoft.service.RequestService;

public class StudentMenu {
    private static RequestService requestService = new RequestService();


    public void showStudentMenu(Student student) {
        System.out.println("=======================================");
        System.out.println("   WELCOME BACK " + student.getUserName().toUpperCase());
        System.out.println("=======================================");
        System.out.println("1.Request room transfer.");
        System.out.println("2.Apply for a hostel/room.");
        System.out.println("3.Logout");
        int studentChoice = Validator.readInt("Enter your choice to proceed: ");

        switch (studentChoice) {
            case 1:
                requestTransfer(student);
                break;
            case 2:
                applyForHostel(student);
                break;
            case 3:
                System.out.println("Logging out....");
                break;
            default:
                System.out.println("Enter the right choice!");
        }
    }

    private static void requestTransfer(Student student) {
        try {
            requestService.requestTransfer(student);
            System.out.println("Transfer request submitted. Await admin approval.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void applyForHostel(Student student) {
        try {
            requestService.applyForHostel(student);
            System.out.println("Application submitted. Await admin approval.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
