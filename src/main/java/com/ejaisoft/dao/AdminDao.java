package com.ejaisoft.dao;

import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    public void createAdmin(Admin admin) {
        String sql = "insert into admin(full_name,user_name,password_hash,email,phone_number) values(?,?,?,?,?)";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, admin.getFullName());
            pstmt.setString(2, admin.getUserName());
            pstmt.setString(3, admin.getPasswordHash());
            pstmt.setString(4, admin.getEmail());
            pstmt.setString(5, admin.getPhoneNumber());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to add admin");
            e.printStackTrace();
        }
    }

    public Admin findByUsernameOrEmail(String identifier) {
        String sql = "select * from admin where user_name = ? or email = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, identifier);
            pstmt.setString(2, identifier);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt(1));
                admin.setFullName(rs.getString(2));
                admin.setUserName(rs.getString(3));
                admin.setPasswordHash(rs.getString(4));
                admin.setEmail(rs.getString(5));
                admin.setPhoneNumber(rs.getString(6));
                return admin;
            }
        } catch (SQLException e) {
            System.err.println("Failed to look up the admin");
            e.printStackTrace();
        }
        return null;
    }
}
