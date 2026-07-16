package com.ejaisoft.dao;

import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.model.Hostel;
import com.ejaisoft.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomDao {
    /*
    -List all rooms
    -List rooms in a hostel
    -Get roombyid
    -create roooms
    -update info
     */
    HostelDao hd = new HostelDao();

    public List<Room> viewAllRooms() {
        String sql = "select * from room";
        List<Room> roomList = new ArrayList<>();
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                roomList.add(mapRow(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
        return roomList;
    }

    public void addRoom(Room room) {

        String sql = "insert into room(hostel_id,room_number)values(?,?)";

        try (
                Connection conn = SystemSettings.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, room.getHostelId().getHostelId());
            pstmt.setString(2, room.getRoomNumber());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Failed to add room");
            e.printStackTrace();
        }
    }

    public List<Room> getHostelRooms(int hostelId) {
        List<Room> rooms = new ArrayList<>();
        String sql = "select * from room where hostel_id = ?";

        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hostelId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                rooms.add(mapRow(rs));
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return rooms;

    }

    public Room getRoomById(int roomId) {
        String sql = "select * from room where room_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roomId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public boolean checkIfRoomExists(String roomNumber, int hostelId) {
        String sql = "select * from room where room_number = ? AND hostel_id =?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomNumber);
            pstmt.setInt(2, hostelId);
            ResultSet result = pstmt.executeQuery();

            return result.next();


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return false;
    }

    private Room mapRow(ResultSet rs) throws SQLException {
        int roomId = rs.getInt(1);
        Hostel hostel = hd.getHostelByID(rs.getInt(2));
        String roomNo = rs.getString(3);
        return new Room(roomId, hostel, roomNo);
    }

}
