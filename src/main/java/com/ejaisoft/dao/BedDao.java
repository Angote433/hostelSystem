package com.ejaisoft.dao;

import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.model.Bed;
import com.ejaisoft.model.BedStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BedDao {
    /*
    Required to
    -Add bed to rooms(save)
    -List beds
    -Update info
    -Available beds

     */
    private String url = "jdbc:mysql://localhost:3306/hostel_system";
    private String username = "root";
    private String pass = "A#1rnold233";
    RoomDao rd = new RoomDao();

    public List<Bed>viewAllBeds(){
        String sql = "select * from bed";
        List<Bed>bedList = new ArrayList<>();

        try(Connection conn = SystemSettings.dbConnection(url,username,pass);
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet rs= pstmt.executeQuery();

            while(rs.next()){
                int bedId = rs.getInt(1);
                int studentId = rs.getInt(2);
                int roomId = rs.getInt(3);
                String bedNumber = rs.getString(4);
                BedStatus status = BedStatus.valueOf(rs.getString(5).toUpperCase());

                //bedList.add(new Bed(bedId,studentId,rd.getRoomById(roomId),bedNumber,status));
            }
            return bedList;
        }catch(SQLException e){
            System.err.println("Failed to fetch data");
            e.printStackTrace();
        }
        return null;
    }

    public void addBed(Bed bed){
        String sql="insert into bed(room_id,bed_number)values(?,?)";

        try(Connection conn = SystemSettings.dbConnection(url,username,pass);
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,bed.getRoom().getRoomId());
            pstmt.setString(2,bed.getBedNumber());

            pstmt.executeUpdate();
            System.out.println("Bed added successfully");

        } catch (SQLException e) {
            System.err.println("Failed to add bed");
            e.printStackTrace();
        }

    }



    public  boolean checkIfBedExists(String bedNumber, int roomId) {
        String sql ="select * from bed where bed_number = ? AND room_id = ?";
        try(Connection conn = SystemSettings.dbConnection(url,username,pass);
            PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1,bedNumber);
            pstmt.setInt(2,roomId);
            ResultSet result = pstmt.executeQuery();

            return result.next();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }



}
