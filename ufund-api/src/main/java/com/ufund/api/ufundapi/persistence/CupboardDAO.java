package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.List;

import com.ufund.api.ufundapi.model.Need;

/**
 * Defines the interface for Cupboard object persistence
 * 
 * @author Cheyenne Zhang, Carla Lopez, Rachel Atkins, Garrett Geyer, Michael Dibiase
 */
public interface CupboardDAO {

    /**
     * Creates a need
     * 
     * @param need need object to create and save
     * 
     * @return new need if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need createNeed(Need need) throws IOException;

    /**
     * GETS a need with the given id
     * 
     * @param id The id of the need to retrieve
     * 
     * @return a need object with the matching id
     * 
     * null if no need with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need getSingleNeed(int id) throws IOException;

    Need[] getNeedbyName(String name) throws IOException;

    boolean deleteNeed(int Id) throws IOException;

    Need updateNeed(Need need) throws IOException;

    List<Need> getEntireCupboard() throws IOException;

    


}
