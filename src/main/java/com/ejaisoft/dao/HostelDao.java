package com.ejaisoft.dao;

import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.model.Gender;
import com.ejaisoft.model.Hostel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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

    public boolean createHostel(String hostelName, Gender genderType) {
        String sql = "insert into hostel(hostel_name,gender_type) values(?,?)";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hostelName);
            pstmt.setString(2, genderType.name().toLowerCase());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Hostel> getHostels() {
        String sql = "select * from hostel";
        List<Hostel> hostelList = new ArrayList<>();

        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                hostelList.add(mapRow(rs));
            }

            return hostelList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public Hostel getHostelByID(int hosId) {
        String sql = "select * from hostel where hostel_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hosId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public boolean saveHostelInfo(Hostel hostel) {

        return true;
    }


    public Hostel getHostelByName(String hostelName) {
        String sql = "select * from hostel where hostel_name = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hostelName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Hostel mapRow(ResultSet rs) throws SQLException {
        int hostelId = rs.getInt(1);
        String hostelName = rs.getString(2);
        Gender genderType = Gender.valueOf(rs.getString(3).toUpperCase());
        return new Hostel(hostelId, hostelName, genderType);
    }
}
