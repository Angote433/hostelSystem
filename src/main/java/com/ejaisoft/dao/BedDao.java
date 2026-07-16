package com.ejaisoft.dao;

import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.model.Bed;
import com.ejaisoft.model.BedStatus;
import com.ejaisoft.model.Room;
import com.ejaisoft.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BedDao {
    /*
    Required to
    -Add bed to rooms(save)
    -List beds
    -Update info
    -Available beds

     */
    RoomDao rd = new RoomDao();
    StudentDao sd = new StudentDao();

    public List<Bed> viewAllBeds() {
        String sql = "select * from bed";
        List<Bed> bedList = new ArrayList<>();

        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bedList.add(mapRow(rs));
            }
            return bedList;
        } catch (SQLException e) {
            System.err.println("Failed to fetch data");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void addBed(Bed bed) {
        String sql = "insert into bed(room_id,bed_number)values(?,?)";

        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bed.getRoom().getRoomId());
            pstmt.setString(2, bed.getBedNumber());

            pstmt.executeUpdate();
            System.out.println("Bed added successfully");

        } catch (SQLException e) {
            System.err.println("Failed to add bed");
            e.printStackTrace();
        }

    }


    public boolean checkIfBedExists(String bedNumber, int roomId) {
        String sql = "select * from bed where bed_number = ? AND room_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bedNumber);
            pstmt.setInt(2, roomId);
            ResultSet result = pstmt.executeQuery();

            return result.next();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int countBedsInRoom(int roomId) {
        String sql = "select count(*) from bed where room_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roomId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Failed to count beds");
            e.printStackTrace();
        }
        return 0;
    }

    public List<Bed> getAvailableBedsInHostel(int hostelId) {
        String sql = "select b.* from bed b join room r on b.room_id = r.room_id " +
                "where r.hostel_id = ? and b.status = 'available'";
        List<Bed> beds = new ArrayList<>();
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hostelId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                beds.add(mapRow(rs));
            }
            return beds;
        } catch (SQLException e) {
            System.err.println("Failed to fetch available beds");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Bed getBedByStudentId(int studentId) {
        String sql = "select * from bed where student_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch the student's bed");
            e.printStackTrace();
        }
        return null;
    }

    public void assignBedToStudent(int bedId, int studentId) {
        String sql = "update bed set student_id = ?, status = 'occupied' where bed_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, bedId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to assign bed");
            e.printStackTrace();
        }
    }

    public void freeBedByStudentId(int studentId) {
        String sql = "update bed set student_id = NULL, status = 'available' where student_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to free bed");
            e.printStackTrace();
        }
    }

    private Bed mapRow(ResultSet rs) throws SQLException {
        int bedId = rs.getInt(1);
        int studentId = rs.getInt(2);
        int roomId = rs.getInt(3);
        String bedNumber = rs.getString(4);
        BedStatus status = BedStatus.valueOf(rs.getString(5).toUpperCase());

        Room room = rd.getRoomById(roomId);
        Student student = studentId > 0 ? sd.getStudentById(studentId) : null;

        return new Bed(bedId, student, room, bedNumber, status);
    }

}
