package com.ejaisoft.model;

import java.time.LocalDateTime;

public class Request {
    private int requestId;
    private Student studentId;
    private RequestStatus status;
    private LocalDateTime requestedAt;
    private RequestType requestType;

    public Request(Student studentId, RequestType requestType) {
        this.studentId = studentId;
        this.requestType = requestType;
        this.status = RequestStatus.PENDING;
        this.requestedAt = LocalDateTime.now();
    }

    public Request(int requestId, Student studentId, RequestStatus status,
                   LocalDateTime requestedAt, RequestType requestType) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.status = status;
        this.requestedAt = requestedAt;
        this.requestType = requestType;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}
