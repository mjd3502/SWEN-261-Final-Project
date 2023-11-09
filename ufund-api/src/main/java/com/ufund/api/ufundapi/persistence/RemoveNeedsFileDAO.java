package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;

/**
 * Defines the functionality for a Remove Needs File Data Access Object
 * 
 * Implements the interface for a Remove Needs object persistence
 * 
 * @author Cheyenne Zhang
 */
@Component
public class RemoveNeedsFileDAO implements RemoveNeedsDAO{

    Map<String,Need> removedNeeds;
    private ObjectMapper objectMapper;
    private  String filename;

    
    public RemoveNeedsFileDAO(@Value("${removedNeeds.file}")String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private Need[] getRemovedNeedsArray(){
        ArrayList<Need> removedNeedsList = new ArrayList<>();
        for(Need need: removedNeeds.values()){
            removedNeedsList.add(need);
        }

        Need[] removedNeedsArray = new Need[removedNeedsList.size()];
        removedNeedsList.toArray(removedNeedsArray);
        return removedNeedsArray;
    }

    private boolean save() throws IOException{
        Need[] removedNeedsArray = getRemovedNeedsArray();
        objectMapper.writeValue(new File(filename),removedNeedsArray);
        return true;
    }

    private boolean load() throws IOException{
        removedNeeds = new TreeMap<>();
        Need[] removedNeedsArray = objectMapper.readValue(new File(filename),Need[].class);
        for(Need need: removedNeedsArray){
            removedNeeds.put(need.getName(), need);
        }
        return true;
    }

    /**
     * Stores the removed needs in a file
     */
    @Override
    public boolean storeRemovedNeed(Need need) throws IOException {
        synchronized(removedNeeds){
            removedNeeds.put(need.getName(),need);
            return save();
            // if (need.getQuantity() == 0) {
            //     removedNeeds.put(need.getName(),need);
            //     return save();
            // } else {
            //     return false;
            // }
        }
    }

    
}
