package com.ejaisoft.service;

import com.ejaisoft.dao.BedDao;
import com.ejaisoft.model.Bed;
import com.ejaisoft.model.BedStatus;
import com.ejaisoft.model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BedService {
    BedDao bd = new BedDao();
    RoomService rs = new RoomService();


    public Bed addBed(Bed bed,int roomId) {
        //get the room
        //create the bed ,if it dores not exist in the room.
        Room room = rs.getRoomById(roomId);
        if (room == null) {
            return null;
        }
        if (bd.checkIfBedExists(bed.getBedNumber(), room.getRoomId())) {
            return null;
        }

        bed.setRoom(room);
        bed.setStatus(BedStatus.AVAILABLE);
        bd.addBed(bed);

        return bed;
    }

    public List<Bed> addBedsToRoom(int roomId, int count) {
        Room room = rs.getRoomById(roomId);
        if (room == null) {
            return Collections.emptyList();
        }
        int existing = bd.countBedsInRoom(roomId);
        List<Bed> added = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Bed bed = new Bed("B" + (existing + i));
            bed.setRoom(room);
            bd.addBed(bed);
            added.add(bed);
        }
        return added;
    }

    public List<Bed>viewAllBeds(){
        return bd.viewAllBeds();
    }

    public List<Bed> listAvailableBedsInHostel(int hostelId) {
        return bd.getAvailableBedsInHostel(hostelId);
    }

    public Bed getCurrentBedForStudent(int studentId) {
        return bd.getBedByStudentId(studentId);
    }

    public void allocateBed(int bedId, int studentId){
        if (bd.getBedByStudentId(studentId) != null) {
            bd.freeBedByStudentId(studentId);
        }
        bd.assignBedToStudent(bedId, studentId);
    }




}
