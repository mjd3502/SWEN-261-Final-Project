package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.Map;

import com.ufund.api.ufundapi.model.FavoritePets;
import com.ufund.api.ufundapi.model.Pet;

/**
 * Defines the interface for Favorite pets object persistence
 * 
 * @author Garrett Geyer, Cheyenne Zhang, Carla Lopez, Rachel Atkins,  Michael Dibiase
 */
public interface FavoritePetsDAO {
    /**
     * creates a list of favorite pets
     * @param FavoritePetslist favoritePet object to create, save
     * @return created favorite pet
     * @throws IOException if issue in storage
     */
    FavoritePets createFavoritePets(FavoritePets favoritePets) throws IOException;

    /**
     * adds a pet to a user's list of favorite pets
     * @param username user to add pet to
     * @param pet pet to add to user's list
     * @return favorite pets object for user
     * @throws IOException if issue in storage
     */
    FavoritePets addPetToFavoritePets(String username, Pet pet) throws IOException;
    /**
     * removes a pet from a user's favorite pet list
     * @param username user to remove from
     * @param pet pet to remove
     * @return true if pet deleted
     * @throws IOException if issue in storage
     */
    boolean removePetFromFavoritePets(String username,int pet) throws  IOException;
    /**
     * returns the list of favorite pets for a user
     * @param userName user to retrieve favorite pets for
     * @return lit of pets user has favorited
     * @throws IOException if issue in storage
     */
    Map<Integer,Pet> getFavoritePets(String username) throws IOException;


    
}
