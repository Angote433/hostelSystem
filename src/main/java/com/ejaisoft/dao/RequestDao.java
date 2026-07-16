package com.ejaisoft.dao;

import com.ejaisoft.Utils.SystemSettings;
import com.ejaisoft.model.Request;
import com.ejaisoft.model.RequestStatus;
import com.ejaisoft.model.RequestType;
import com.ejaisoft.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestDao {
    private final StudentDao studentDao = new StudentDao();

    public void createRequest(Request request) {
        String sql = "insert into requests(student_id, status, request_type) values (?,?,?)";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, request.getStudentId().getStudentId());
            pstmt.setString(2, request.getStatus().name().toLowerCase());
            pstmt.setString(3, request.getRequestType().name().toLowerCase());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to submit request");
            e.printStackTrace();
        }
    }

    public List<Request> getRequestsByStatus(RequestStatus status) {
        String sql = "select * from requests where status = ?";
        List<Request> requests = new ArrayList<>();
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status.name().toLowerCase());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                requests.add(mapRow(rs));
            }
            return requests;
        } catch (SQLException e) {
            System.err.println("Failed to fetch requests");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public boolean hasPendingRequest(int studentId) {
        String sql = "select * from requests where student_id = ? and status = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, RequestStatus.PENDING.name().toLowerCase());
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Failed to check pending requests");
            e.printStackTrace();
        }
        return false;
    }

    public void updateStatus(int requestId, RequestStatus status) {
        String sql = "update requests set status = ? where request_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status.name().toLowerCase());
            pstmt.setInt(2, requestId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to update request status");
            e.printStackTrace();
        }
    }

    public Request getRequestById(int requestId) {
        String sql = "select * from requests where request_id = ?";
        try (Connection conn = SystemSettings.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, requestId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch the request");
            e.printStackTrace();
        }
        return null;
    }

    private Request mapRow(ResultSet rs) throws SQLException {
        int requestId = rs.getInt(1);
        Student student = studentDao.getStudentById(rs.getInt(2));
        RequestStatus status = RequestStatus.valueOf(rs.getString(3).toUpperCase());
        RequestType requestType = RequestType.valueOf(rs.getString(4).toUpperCase());
        var requestedAt = rs.getTimestamp(5).toLocalDateTime();

        return new Request(requestId, student, status, requestedAt, requestType);
    }
}
