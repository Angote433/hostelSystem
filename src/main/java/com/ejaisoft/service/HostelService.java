package com.ejaisoft.service;

import com.ejaisoft.dao.HostelDao;
import com.ejaisoft.model.Hostel;

import java.util.List;

public class HostelService {

    HostelDao hd = new HostelDao();

    public Hostel addHostel(Hostel hostel){
        Hostel exists = hd.getHostelByName(hostel.getHostelName());
        if(exists != null){
            return null;
        }
        hd.createHostel(hostel.getHostelName(),hostel.getGenderType());
        return hostel;
    }

    public List<Hostel> viewALlHostels(){return hd.getHostels();}


}
