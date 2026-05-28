package com.ejaisoft.menu;

import com.ejaisoft.model.Admin;

import java.util.Scanner;

public class AdminMenu {
    private static Admin admin  = new Admin();
    private Scanner userInput = new Scanner(System.in);


    static void main() {
        admin.setUserName("Angote433");
        System.out.println("=======================================");
        System.out.println("     HELLO "+ admin.getUserName().toUpperCase());
        System.out.println("========================================");
        System.out.println("1.Manage student  allocations");
        System.out.println("2.Manage hostels");
        System.out.println("3.Manage roomsandbeds");
        System.out.println("4.Logout");

    }

    private void manageStudentsMenu(){
        System.out.println("====================================");
        System.out.println("   MANAGE STUDENTS AND ROOMS");
        System.out.println("====================================");
        System.out.println("1.View and manage students");
        System.out.println("2.View requests and validate");
        System.out.println("3.Allocate Beds");
        System.out.print("Enter your choice: ");
        int choice= userInput.nextInt();
        userInput.nextLine();

        switch(choice){
            case 1:
                System.out.println("Students coming");
                break;
            case 2:
                System.out.println("requests coming soon: ");
                break;
            case 3:
                System.out.println("Allocation management coming soon");
                break;
            default:
                System.out.println("Invalid choice.Enter a valid input(1,2,3");

        }


    }
}
