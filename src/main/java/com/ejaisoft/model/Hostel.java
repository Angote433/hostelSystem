package com.ejaisoft.model;

public class Hostel {
    private int hostelId;
    private String hostelName;
    private Gender genderType;

    public Hostel(String hostelName,Gender genderType) {
        this.hostelName = hostelName;
        this.genderType = genderType;
    }

    public Hostel(int hostelId, String hostelName, Gender genderType) {
        this.hostelId = hostelId;
        this.hostelName = hostelName;
        this.genderType = genderType;
    }

    public Gender getGenderType() {return genderType;}
    public void setGenderType(Gender genderType) {this.genderType = genderType;}
    public String getHostelName() {return hostelName;}
    public void setHostelName(String hostelName) {this.hostelName = hostelName;}

    public int getHostelId() {return hostelId;}
    public void setHostelId(int hostelId){this.hostelId= hostelId;}
}
