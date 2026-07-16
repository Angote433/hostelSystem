package com.ejaisoft.menu;

import com.ejaisoft.Utils.Validator;
import com.ejaisoft.model.Admin;
import com.ejaisoft.model.Bed;
import com.ejaisoft.model.Gender;
import com.ejaisoft.model.Hostel;
import com.ejaisoft.model.Request;
import com.ejaisoft.model.Room;
import com.ejaisoft.service.BedService;
import com.ejaisoft.service.HostelService;
import com.ejaisoft.service.RequestService;
import com.ejaisoft.service.RoomService;

import java.util.List;

public class AdminMenu {
    private static HostelService hs = new HostelService();
    private static RoomService rs = new RoomService();
    private static BedService bedService = new BedService();
    private static RequestService requestService = new RequestService();


    public void showAdminMenu(Admin admin) {
        boolean runStatus = true;
        while (runStatus) {
            System.out.println("=======================================");
            System.out.println("     HELLO " + admin.getUserName().toUpperCase());
            System.out.println("========================================");
            System.out.println("1.Hostel registration");
            System.out.println("2.Room registration.");
            System.out.println("3.Bed management.");
            System.out.println("4.Student requests management.");
            System.out.println("5.Handle allocations.");
            System.out.println("6.Logout");

            int choice = Validator.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    manageHostelsMenu();
                    break;
                case 2:
                    roomRegisterMenu();
                    break;
                case 3:
                    manageBedsMenu();
                    break;
                case 4:
                    studentRequestsMenu();
                    break;
                case 5:
                    allocationsMenu();
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

    private static void manageHostelsMenu() {
        System.out.println("====================================");
        System.out.println("   ADD HOSTELS TO THE SYSTEM");
        System.out.println("====================================");
        System.out.print("Hostel name: ");
        String hostelName = Validator.SCANNER.nextLine();
        System.out.print("Gender type(Male/Female): ");
        String hostelGender = Validator.SCANNER.nextLine();

        Gender genderType;
        try {
            genderType = Gender.valueOf(hostelGender.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender type. Please enter Male or Female. Cancelling.");
            return;
        }

        System.out.println("Proceed to save hostel " + hostelName + "?(Y/N)");
        String confirmINput = Validator.SCANNER.nextLine();
        if (confirmINput.equalsIgnoreCase("y") || confirmINput.equalsIgnoreCase("yes")) {
            Hostel added = hs.addHostel(new Hostel(hostelName, genderType));
            if (added != null) {
                System.out.println("Hostel added successfully");
            } else {
                System.out.println("Failed to add hostel which could be existing");
            }
        } else {
            System.out.println("Saving cancelled");
        }

    }

    private static void roomRegisterMenu() {
        System.out.println("====================================");
        System.out.println("   ADD ROOMS TO A HOSTEL");
        System.out.println("====================================");
        List<Hostel> hostels = hs.viewALlHostels();
        if (hostels.isEmpty()) {
            System.out.println("No hostels registered yet. Add a hostel first.");
            return;
        }
        for (Hostel h : hostels) {
            System.out.println(h.getHostelId() + " - " + h.getHostelName() + " (" + h.getGenderType() + ")");
        }
        int hostelId = Validator.readInt("Choose a hostel ID: ");
        int count = Validator.readInt("How many rooms to add: ");

        List<Room> added = rs.addRoomsToHostel(hostelId, count);
        if (added.isEmpty()) {
            System.out.println("Failed to add rooms - check the hostel ID.");
        } else {
            System.out.println("Added " + added.size() + " room(s):");
            for (Room room : added) {
                System.out.println("  " + room.getRoomNumber());
            }
        }
    }

    private static void manageBedsMenu() {
        System.out.println("======================================");
        System.out.println("   ADD BEDS TO A ROOM");
        System.out.println("======================================");
        List<Hostel> hostels = hs.viewALlHostels();
        if (hostels.isEmpty()) {
            System.out.println("No hostels registered yet.");
            return;
        }
        for (Hostel h : hostels) {
            System.out.println(h.getHostelId() + " - " + h.getHostelName());
        }
        int hostelId = Validator.readInt("Choose a hostel ID: ");

        List<Room> rooms = rs.listRoomsInAHostel(hostelId);
        if (rooms.isEmpty()) {
            System.out.println("No rooms in that hostel yet. Add rooms first.");
            return;
        }
        for (Room room : rooms) {
            System.out.println(room.getRoomId() + " - " + room.getRoomNumber());
        }
        int roomId = Validator.readInt("Choose a room ID: ");
        int bedCount = Validator.readInt("How many beds to add: ");

        List<Bed> added = bedService.addBedsToRoom(roomId, bedCount);
        if (added.isEmpty()) {
            System.out.println("Failed to add beds - check the room ID.");
        } else {
            System.out.println("Added " + added.size() + " bed(s):");
            for (Bed bed : added) {
                System.out.println("  " + bed.getBedNumber());
            }
        }
    }

    private static void studentRequestsMenu() {
        System.out.println("====================================");
        System.out.println("   PENDING STUDENT REQUESTS");
        System.out.println("====================================");
        List<Request> pending = requestService.listPending();
        if (pending.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        for (Request r : pending) {
            System.out.println(r.getRequestId() + " | " + r.getStudentId().getFullName()
                    + " | " + r.getRequestType() + " | requested " + r.getRequestedAt());
        }

        int requestId = Validator.readInt("Enter request ID to act on (0 to cancel): ");
        if (requestId == 0) {
            return;
        }
        int action = Validator.readInt("1.Approve  2.Reject: ");
        if (action == 1) {
            requestService.approve(requestId);
            System.out.println("Request approved. Use 'Handle allocations' to assign a bed.");
        } else if (action == 2) {
            requestService.reject(requestId);
            System.out.println("Request rejected.");
        } else {
            System.out.println("Invalid action.");
        }
    }

    private static void allocationsMenu() {
        System.out.println("====================================");
        System.out.println("   APPROVED REQUESTS AWAITING ALLOCATION");
        System.out.println("====================================");
        List<Request> approved = requestService.listApproved();
        if (approved.isEmpty()) {
            System.out.println("No approved requests awaiting allocation.");
            return;
        }
        for (Request r : approved) {
            System.out.println(r.getRequestId() + " | " + r.getStudentId().getFullName()
                    + " (" + r.getStudentId().getGender() + ") | " + r.getRequestType());
        }

        int requestId = Validator.readInt("Enter request ID to allocate (0 to cancel): ");
        if (requestId == 0) {
            return;
        }
        Request request = requestService.getById(requestId);
        if (request == null) {
            System.out.println("Request not found.");
            return;
        }

        List<Hostel> hostels = hs.listHostelsForGender(request.getStudentId().getGender());
        if (hostels.isEmpty()) {
            System.out.println("No hostels available for this student's gender.");
            return;
        }
        for (Hostel h : hostels) {
            System.out.println(h.getHostelId() + " - " + h.getHostelName());
        }
        int hostelId = Validator.readInt("Choose a hostel ID: ");

        List<Bed> availableBeds = bedService.listAvailableBedsInHostel(hostelId);
        if (availableBeds.isEmpty()) {
            System.out.println("No available beds in that hostel.");
            return;
        }
        for (Bed b : availableBeds) {
            System.out.println(b.getBedId() + " - Room " + b.getRoom().getRoomNumber() + ", Bed " + b.getBedNumber());
        }
        int bedId = Validator.readInt("Choose a bed ID: ");

        try {
            requestService.completeAllocation(requestId, bedId);
            System.out.println("Allocation completed.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}
