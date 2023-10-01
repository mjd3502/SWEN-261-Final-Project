package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;

@Component
public class CupboardFileDAO implements CupboardDAO{
    private static final Logger LOG = Logger.getLogger(CupboardFileDAO.class.getName());
    Map<Integer,Need> cupboard;   // Provides a local cache of the Cupbaord objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between NEED
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new NEED
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Cupboard File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public CupboardFileDAO(@Value("${cupboard.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the needs from the file
    }

    /**
     * Generates the next id for a new need
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of needs from the tree map
     * 
     * @return  The array of needs, may be empty
     */
    private Need[] getcupboardArray() {
        return getcupboardArray(null);
    }

    /**
     * Generates an array of needs  from the tree map for any
     * need that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the needs
     * in the tree map
     * 
     * @return  The array of needs, may be empty
     */
    private Need[] getcupboardArray(String containsText) { // if containsText == null, no filter
        ArrayList<Need> cupboardArrayList = new ArrayList<>();

        for (Need need : cupboard.values()) {
            if (containsText == null || need.getName().contains(containsText)) {
                cupboardArrayList.add(need);
            }
        }

        Need[] needArray = new Need[cupboardArrayList.size()];
        cupboardArrayList.toArray(needArray);
        return needArray;
    }

    /**
     * Saves the needs from the map into the file as an array of JSON objects
     * 
     * @return true if the needs were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Need[] needArray = getcupboardArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file

        objectMapper.writeValue(new File(filename),needArray);
        return true;
    }

    /**
     * Loads needs from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        cupboard = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of needs
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Need[] needArray = objectMapper.readValue(new File(filename),Need[].class);

        // Add each need to the tree map and keep track of the greatest id
        for (Need need : needArray) {
            cupboard.put(need.getId(),need);
            if (need.getId() > nextId)
                nextId = need.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }



    /*
     * CRUD METHODS for U Fund Website:
     */


    @Override
    public Need createNeed() throws IOException {
        // add implementation
        throw new UnsupportedOperationException("Unimplemented method 'createNeed'");
    }

    @Override
    public Need getSingleNeed() throws IOException {
        // add implementation
        throw new UnsupportedOperationException("Unimplemented method 'getSingleNeed'");
    }

    @Override
    public Need getNeedbyName(String name) throws IOException {
         // add implementation
        throw new UnsupportedOperationException("Unimplemented method 'getNeedbyName'");
    }

    @Override
    public boolean deleteNeed() throws IOException {
        // add implementation
        throw new UnsupportedOperationException("Unimplemented method 'deleteneed'");
    }

    @Override
    public Need updateNeed(Need need) throws IOException {
        synchronized(cupboard){
            return updateNeed(need);
        }
    }

    @Override
    public List<Need> getEntireCupboard() throws IOException {
        // add implementation
        throw new UnsupportedOperationException("Unimplemented method 'getEntireCupboard'");
    }
    
    
}
