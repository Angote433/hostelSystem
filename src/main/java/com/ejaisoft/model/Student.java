package com.ejaisoft.model;

public class Student {
    private int studentId;
    private String regNumber;
    private String fullName;
    private String userName;
    private String passwordHash;
    private String email;
    private String phoneNumber;
    private Gender gender;

    public Student(String regNumber, String fullName, String userName,
                   String passwordHash, String email,String phoneNumber,
                   Gender gender) {
        this.regNumber = regNumber;
        this.fullName = fullName;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Student(int studentId, String regNumber, String fullName, String userName, String email, String phoneNumber, Gender gender) {
        this.studentId=studentId;
        this.regNumber=regNumber;
        this.fullName=fullName;
        this.userName=userName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.gender=gender;

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
