package com.ejaisoft.model;

public class Bed {
    private  int bedId;
    private Student studentId;
    private Room roomId;
    private String bedNumber;
    private BedStatus status;

    public Bed(int bedId, Student studentId, Room roomId, String bedNumber, BedStatus status) {
        this.bedId = bedId;
        this.studentId = studentId;
        this.roomId = roomId;
        this.bedNumber = bedNumber;
        this.status = status;
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

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
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
