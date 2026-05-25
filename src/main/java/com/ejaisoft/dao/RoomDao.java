package com.ejaisoft.dao;

import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.model.Hostel;
import com.ejaisoft.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    /*
    -List all rooms
    -List rooms in a hostel
    -create roooms
    -update info
     */
    private String url = "jdbc:mysql://localhost:3306/hostel_system";
    private String username = "root";
    private String pass = "A#1rnold233";
    HostelDao hd = new HostelDao();

    public List<Room>viewAllRooms(){
        String sql = "select * from room";
        Connection conn = SystemSettings.dbConnection(url,username,pass);
        List<Room>roomList = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int roomId = rs.getInt(1);
                Hostel hostelId = hd.getHostelByID(rs.getInt(2)) ;
                String rooomNo = rs.getString(3);
                roomList.add(new Room(roomId,hostelId,rooomNo));
            }
            return roomList;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean addRoom(int hostelId,String roomNumber){

        String sql = "insert into room(hostel_id,room_number)values(?,?)";

        try(
                Connection conn = SystemSettings.dbConnection(url,username,pass);
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {

            pstmt.setInt(1,hostelId);
            pstmt.setString(2,roomNumber);

            pstmt.executeUpdate();
            System.out.println("Room inserted successfully");
            return true;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Room>getHostelRooms(int hostelId){
        List<Room>room = new ArrayList<>();
        return null;
    }
}
