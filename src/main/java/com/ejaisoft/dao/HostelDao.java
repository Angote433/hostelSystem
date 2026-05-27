package com.ejaisoft.dao;

import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.model.Gender;
import com.ejaisoft.model.Hostel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HostelDao {
    /*
    WHat do we need?
    -List of all hostels
    -Hostel by Id
    -Search hostel by name
    -Delete hostel
    -save a new hostel
     */
    String url = "jdbc:mysql://localhost:3306/hostel_system";
    String userName = "root";
    String pass = "A#1rnold233";
    Connection conn = SystemSettings.dbConnection(url,userName,pass);

    static void main() {
        HostelDao hd = new HostelDao();
        Hostel h = new Hostel("Ruwenzori",Gender.MALE);

        if(hd.createHostel(h.getHostelName(),h.getGenderType())) {
            System.out.println("Hostel added");
        }else{
            System.out.println("Failed to add hostel");
        }

    }

    public boolean createHostel(String hostelName,Gender genderType){
        String sql = "insert into hostel(hostel_name,gender_type) values(?,?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,hostelName);
            pstmt.setString(2,genderType.name().toLowerCase());

            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Hostel> getHostels(){
        String sql = "select * from hostel";
        List<Hostel>hostelList = new ArrayList<>();

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int hostelId= rs.getInt(1);
                String hostelName = rs.getString(2);
                Gender genderType = Gender.valueOf(rs.getString(3).toUpperCase());

                hostelList.add(new Hostel(hostelId,hostelName,genderType));
            }

            return hostelList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Hostel getHostelByID(int hosId){
        String sql = "select * from hostel where hostel_id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,hosId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int hostelId= rs.getInt(1);
                String hostelName = rs.getString(2);
                Gender genderType = Gender.valueOf(rs.getString(3).toUpperCase());
                return new Hostel(hostelId,hostelName,genderType);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public boolean saveHostelInfo(Hostel hostel){

        return true;
    }


    public Hostel getHostelByName(String hostelName) {
        String sql = "select * from hostel where hostel_name = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,hostelName);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int hostelId= rs.getInt(1);
                String _hostelName = rs.getString(2);
                Gender genderType = Gender.valueOf(rs.getString(3).toUpperCase());
                return new Hostel(hostelId,_hostelName,genderType);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
