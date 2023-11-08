package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;

/**
 * Defines the functionality for a Remove Needs File Data Access Object
 * 
 * Implements the interface for a Remove Needs object persistence
 * 
 * @author Cheyenne Zhang
 */

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

    @Override
    public boolean removeNeed(Need need) throws IOException { //change to String name (of need) or id
        synchronized(removedNeeds){
            if (need.getQuantity() == 0) {
                // cupboard.remove(id);
                //fix
                Need newRemovedNeed = new Need(need.getId(), need.getName(), need.getQuantity(), need.getDescription(), need.getCost(), need.getType());
                removedNeeds.put(newRemovedNeed.getName(),need);
                return save();
            } else {
                return false;
            }
        }
    }

    
}
