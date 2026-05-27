package com.ejaisoft.service;

import com.ejaisoft.dao.BedDao;
import com.ejaisoft.model.Bed;
import com.ejaisoft.model.BedStatus;
import com.ejaisoft.model.Room;

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

    public List<Bed>viewAllBeds(){
        return bd.viewAllBeds();
    }

    public void allocateBed(){

    }





}
