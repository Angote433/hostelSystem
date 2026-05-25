package com.ejaisoft.model;

public class Room {
    private int roomId;
    private Hostel hostelId;
    private String roomNumber;

    public Room(int roomId, Hostel hostelId, String roomNumber) {
        this.roomId = roomId;
        this.hostelId = hostelId;
        this.roomNumber = roomNumber;
    }

    public Room(Hostel hostelId, String roomNumber) {
        this.hostelId = hostelId;
        this.roomNumber = roomNumber;
    }

    public int getRoomId() {return roomId;}

    public void setRoomId(int roomId) {this.roomId = roomId;}

    public Hostel getHostelId() {return hostelId;}

    public void setHostelId(Hostel hostelId) {this.hostelId = hostelId;}

    public String getRoomNumber() {return roomNumber;}
    public void setRoomNumber(String roomNumber) {this.roomNumber = roomNumber;}
}
