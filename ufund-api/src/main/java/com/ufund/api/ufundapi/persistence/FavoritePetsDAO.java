package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.List;

import com.ufund.api.ufundapi.model.FavoritePets;
import com.ufund.api.ufundapi.model.Pet;
import com.ufund.api.ufundapi.model.User;

public interface FavoritePetsDAO {
    /*
     * TODO: docstrings for following functions
     */

    FavoritePets createFavoritePets(FavoritePets FavoritePets) throws IOException;

    FavoritePets addPetToFavoritePets(String userName, Pet pet) throws IOException;

    boolean removePetFromFavoritePets(String userName,int pet) throws  IOException;

    List<Pet> getFavoritePets(String userName) throws IOException;


    
}
