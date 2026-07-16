package com.ejaisoft.service;

import com.ejaisoft.dao.HostelDao;
import com.ejaisoft.dao.RoomDao;
import com.ejaisoft.model.Hostel;
import com.ejaisoft.model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomService {
    RoomDao rd = new RoomDao();
    HostelDao hd = new HostelDao();

    public List<Room> addRoomsToHostel(int hostelId, int count) {
        Hostel hostel = hd.getHostelByID(hostelId);
        if (hostel == null) {
            return Collections.emptyList();
        }
        int existing = rd.getHostelRooms(hostelId).size();
        List<Room> added = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Room room = new Room(hostel, "R" + (existing + i));
            rd.addRoom(room);
            added.add(room);
        }
        return added;
    }

    public Room addRoom(Room room,int hosId){
        //Pick a hostel first.
        Hostel hostel = hd.getHostelByID(hosId);
        if(hostel == null){
            System.out.println("Hostel not found");
            return null;
        }
        //room already exists?
        if(rd.checkIfRoomExists(room.getRoomNumber(), hostel.getHostelId())){
            return null;
        }
        room.setHostelId(hostel);
        rd.addRoom(room);

        return room;
    }

    public List<Room>listRooms(){return rd.viewAllRooms();}
    public List<Room>listRoomsInAHostel(int hostelId){
        return rd.getHostelRooms(hostelId);
    }
    public Room getRoomById(int roomId){
        return rd.getRoomById(roomId);
    }


}
