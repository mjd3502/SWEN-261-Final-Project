package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Need;

/**
 * Defines the interface for a Remove Needs object persistence
 * 
 * @author Cheyenne Zhang
 */

public interface RemoveNeedsDAO {
    /**
     * Removes a need with the given id (when
     * the quantity is less than zero)
     * and stores it in a deleted needs log
     * 
     * @param id The id of the need
     * 
     * @return true if the need was removed and stored
     * 
     * false if the need with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean storeRemovedNeed(Need need) throws IOException;

    // /**
    //  * Retrieves a single need in the 
    //  * Removed Needs file
    //  * 
    //  * @param id The id of the need
    //  * 
    //  * @return 
    //  * 
    //  * @throws IOException
    //  */
    // Need getSingleRemovedNeed(int id) throws IOException;

    // /**
    //  * Retrieves all of the needs in the 
    //  * Removed Needs file
    //  * 
    //  * @return
    //  * @throws IOException
    //  */
    // List<Need> getRemovedNeeds() throws IOException;
}
