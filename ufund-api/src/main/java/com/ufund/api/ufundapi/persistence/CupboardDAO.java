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
     * GETS a single need with the given id
     * 
     * @param id The id of the need to retrieve
     * 
     * @return a single need object with the matching id
     * 
     * null if no need with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need getSingleNeedById(int id) throws IOException;


    /**
     * GETS a need with the given name
     * 
     * @param name The name of the need to be retrieved
     * 
     * @return a need with the matching name
     * 
     * null if no need with a matching name is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Need[] getNeedbyName(String name) throws IOException;

    boolean deleteNeedbyName(String name) throws IOException;

    /**
     * Deletes a need with the given id
     * 
     * @param id The id of the need
     * 
     * @return true if the need was deleted
     * 
     * false if the need with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteNeed(int Id) throws IOException;

    /**
     * Updates a need
     * 
     * @param need need object to update and save
     * 
     * @return updated need if successful, null if
     * need could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Need updateNeed (Need need) throws IOException;

    /**
     * Retrieves entire Cupboard of needs
     * 
     * @return A list of need objects (can be empty)
     * 
     * @throws IOException if an issue with underlying storage
     */
    List<Need> getEntireCupboard() throws IOException;

    


}
