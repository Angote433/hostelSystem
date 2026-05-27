package com.ejaisoft.model;

public class Bed {
    private  int bedId;
    private Student studentId;
    private Room room;
    private String bedNumber;
    private BedStatus status;

    public Bed(int bedId, Student studentId, Room room, String bedNumber, BedStatus status) {
        this.bedId = bedId;
        this.studentId = studentId;
        this.room = room;
        this.bedNumber = bedNumber;
        this.status = status;
    }
    public Bed(String bedNumber){
        this.bedNumber = bedNumber;
    }

    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public BedStatus getStatus() {
        return status;
    }

    public void setStatus(BedStatus status) {
        this.status = status;
    }
}
