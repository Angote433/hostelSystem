package com.ejaisoft.dao;

import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.model.Gender;
import com.ejaisoft.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentDao {

    /*
    -List students
    -Save students
    -Update students information
    - students with and without allocations
     */

    public void addStudent(Student student) {
        String sql = "insert into student(reg_number,full_name,user_name,password_hash,email,phone_number,gender)"
                + "values(?,?,?,?,?,?,?)";

        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getRegNumber());
            pstmt.setString(2, student.getFullName());
            pstmt.setString(3, student.getUserName());
            pstmt.setString(4, student.getPasswordHash());
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getPhoneNumber());
            pstmt.setString(7, String.valueOf(student.getGender().name().charAt(0)));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Failed to add student");
            e.printStackTrace();
        }

    }

    public void updateStudInfo(Student student) {
        String sql = "update student set reg_number = ?,full_name = ?,user_name=?,password_hash=?" +
                ",email=?,phone_number=?,gender=? where student_id = ?";

        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getRegNumber());
            pstmt.setString(2, student.getFullName());
            pstmt.setString(3, student.getUserName());
            pstmt.setString(4, student.getPasswordHash());
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getPhoneNumber());
            pstmt.setString(7, String.valueOf(student.getGender().name().charAt(0)));

            pstmt.setInt(8, student.getStudentId());

            pstmt.executeUpdate();

            System.out.println("Student updated successfully");


        } catch (SQLException e) {
            System.err.println("Failed to update student info");
            e.printStackTrace();
        }

    }

    public List<Student> getAllStudents() {
        String sql = "select * from student";
        List<Student> students = new ArrayList<>();
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                students.add(mapRow(rs));
            }

            return students;
        } catch (SQLException e) {
            System.err.println("Failed to get students");
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Student getStudentById(int studentId) {
        String sql = "select * from student where student_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Failed to get the student");
            e.printStackTrace();
        }
        return null;
    }

    public Student findByUsernameOrEmail(String identifier) {
        String sql = "select * from student where user_name = ? or email = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, identifier);
            pstmt.setString(2, identifier);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Failed to look up the student");
            e.printStackTrace();
        }
        return null;
    }

    public boolean existsByAdmissionNumber(String admNo) {
        String sql = "select * from student where reg_number = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, admNo);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Failed to get the student");
            e.printStackTrace();
        }

        return false;
    }


    public boolean existsByEmail(String email) {
        String sql = "select * from student where email = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Failed to get the student");
            e.printStackTrace();
        }

        return false;
    }

    public boolean existsByUserName(String userName) {
        String sql = "select * from student where user_name = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Failed to get the student");
            e.printStackTrace();
        }

        return false;
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        int studentId = rs.getInt(1);
        String regNo = rs.getString(2);
        String fullName = rs.getString(3);
        String userName = rs.getString(4);
        String passwordHash = rs.getString(5);
        String email = rs.getString(6);
        String phoneNumber = rs.getString(7);
        Gender gender = "F".equalsIgnoreCase(rs.getString(8)) ? Gender.FEMALE : Gender.MALE;

        Student student = new Student(studentId, regNo, fullName, userName, email, phoneNumber, gender);
        student.setPasswordHash(passwordHash);
        return student;
    }
}
