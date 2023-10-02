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
     * Retrieves all needs
     * 
     * @return An array of need objects (can be empty)
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need createNeed(Need need) throws IOException;

    Need getSingleNeed(int id) throws IOException;

    Need[] getNeedbyName(String name) throws IOException;

    boolean deleteNeed(int Id) throws IOException;

    Need updateNeed(Need need) throws IOException;

    List<Need> getEntireCupboard() throws IOException;

    


}
