package com.ejaisoft.menu;

import com.ejaisoft.model.Admin;
import com.ejaisoft.model.Gender;
import com.ejaisoft.model.Hostel;
import com.ejaisoft.service.HostelService;

import java.sql.SQLOutput;
import java.util.Scanner;

public class AdminMenu {
    private static Admin admin  = new Admin();
    private static Scanner userInput = new Scanner(System.in);
    private static HostelService hs = new HostelService();


    static void main() {
        admin.setUserName("Angote433");
        boolean runStatus = true;
        while(runStatus) {
            System.out.println("=======================================");
            System.out.println("     HELLO " + admin.getUserName().toUpperCase());
            System.out.println("========================================");
            System.out.println("1.Hostel registration");
            System.out.println("2.Room registration.");
            System.out.println("3.Bed management.");
            System.out.println("4.Student requests management.");
            System.out.println("5.Handle allocations.");
            System.out.println("6.Logout");

            System.out.print("Enter your choice: ");
            int choice = userInput.nextInt();
            userInput.nextLine();

            switch (choice) {
                case 1:
                    manageHostelsMenu();
                    break;
                case 2:
                    System.out.println("room choice coming soon ");
                    break;
                case 3:
                    //statement
                    break;
                case 4:
                    System.out.println("Requests management coming soon.");
                    break;
                case 5:
                    System.out.println("Handle allocations to come soon.");
                    break;
                case 6:
                    System.out.println("Thank you for visiting us,goodbyee");
                    runStatus = false;
                    break;
                default:
                    System.out.println("Invalid choice.Enter a valid input(1,2,3");
            }

        }
    }

        private static void manageHostelsMenu () {
            System.out.println("====================================");
            System.out.println("   ADD HOSTELS TO THE SYSTEM");
            System.out.println("====================================");
            System.out.print("Hostel name: ");
            String hostelName = userInput.nextLine();
            System.out.print("Gender type: ");
            String hostelGender = userInput.nextLine();
            System.out.println("Proceed to save hostel " + hostelName +"?(Y/N)");
            String confirmINput =userInput.nextLine();
            if(confirmINput.equals("y")||confirmINput.equals("Y")||confirmINput.equals("Yes")){
                Hostel added = hs.addHostel(new Hostel(hostelName,Gender.valueOf(hostelGender.toUpperCase())));
                if(added != null){
                    System.out.println("Hostel added successfully");
                }else{
                    System.out.println("Failed to add hostel which could be existing");
                }
            }else{
                System.out.println("Saving cancelled");
            }

        }

        private static void roomRegisterMenu(){
            System.out.println("====================================");
            System.out.println("   ADD ROOMS TO A HOSTEL");
            System.out.println("====================================");
            //We need to add multiple rooms where room number shall begenerated automatically

        }

        private static void manageBedsMenu(){
            System.out.println("======================================");
            System.out.println("   MULTIPLE BEDS TO  BE ADDED");
            System.out.println("======================================");
            //romms get listed -> user choses a room toadd beds ->
            System.out.println("To add multiple beds,enter the number of beds you wish to add: ");
            int bedNumber = userInput.nextInt();
            userInput.nextLine();

            System.out.println("Proceed to add "+ bedNumber + "beds in room ?");


            //ADMIN COUNTS THE NUMBER OF BEDS TO ADD AND ADDS TO A ROOM
            //THE BEDNUMBERGETS GENERATED AUTOMATICALLY
            //TO IMPLEMENT THIS IN THE SERVICE LAYER

        }
        private static void studentRequestsMenu(){

        }
        private static void allocationsMenu(){

        }

    }

