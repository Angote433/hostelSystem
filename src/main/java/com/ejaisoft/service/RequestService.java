package com.ejaisoft.service;

import com.ejaisoft.dao.RequestDao;
import com.ejaisoft.model.Request;
import com.ejaisoft.model.RequestStatus;
import com.ejaisoft.model.RequestType;
import com.ejaisoft.model.Student;

import java.util.List;

public class RequestService {
    private final RequestDao rd = new RequestDao();
    private final BedService bedService = new BedService();

    public void applyForHostel(Student student) {
        if (bedService.getCurrentBedForStudent(student.getStudentId()) != null) {
            throw new RuntimeException("You already have a bed allocated. Use room transfer instead.");
        }
        if (rd.hasPendingRequest(student.getStudentId())) {
            throw new RuntimeException("You already have a pending request.");
        }
        rd.createRequest(new Request(student, RequestType.BED_ALLOCATION));
    }

    public void requestTransfer(Student student) {
        if (bedService.getCurrentBedForStudent(student.getStudentId()) == null) {
            throw new RuntimeException("You have no current allocation. Use 'Apply for a hostel/room' instead.");
        }
        if (rd.hasPendingRequest(student.getStudentId())) {
            throw new RuntimeException("You already have a pending request.");
        }
        rd.createRequest(new Request(student, RequestType.ROOM_CHANGE));
    }

    public List<Request> listPending() {
        return rd.getRequestsByStatus(RequestStatus.PENDING);
    }

    public List<Request> listApproved() {
        return rd.getRequestsByStatus(RequestStatus.APPROVED);
    }

    public void approve(int requestId) {
        rd.updateStatus(requestId, RequestStatus.APPROVED);
    }

    public void reject(int requestId) {
        rd.updateStatus(requestId, RequestStatus.REJECTED);
    }

    public Request getById(int requestId) {
        return rd.getRequestById(requestId);
    }

    public void completeAllocation(int requestId, int bedId) {
        Request request = rd.getRequestById(requestId);
        if (request == null || request.getStatus() != RequestStatus.APPROVED) {
            throw new RuntimeException("Request is not approved or does not exist.");
        }
        bedService.allocateBed(bedId, request.getStudentId().getStudentId());
        rd.updateStatus(requestId, RequestStatus.COMPLETED);
    }
}
