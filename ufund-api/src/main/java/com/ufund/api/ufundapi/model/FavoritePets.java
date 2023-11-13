package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoritePets {
 
    public static final String STRING_FORMAT_FavoritePets = "FavoritePets [username=%s, favoritePets=%s ]  ";

 
    @JsonProperty("username") private String username;
    @JsonProperty("favoritePets") private Map<Integer,Pet> favoritePets;
    /**
     * class to track a list of favorite pets for a user
     * @author Garrett Geyer, Carla Lopez, Cheyyene, Rachel, Micheal
     * @param username name of user
     * @param favoritePets favorite pets for user 
     */

    public FavoritePets(@JsonProperty("username") String username, @JsonProperty("favoritePets") Map<Integer,Pet> favoritePets){
        this.username = username;
        this.favoritePets = this.favoritePets = (favoritePets != null) ? favoritePets : new HashMap<>();
    }

    
    /**
     * Retrieves the username
     * @return The username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @return list of favorite pets
     */
    public Map<Integer,Pet> getFavoritePets() {
        return this.favoritePets;
    }
    public void setFavoritePets(Pet pet) {
        this.favoritePets.put(pet.getId(),pet);
    }

    @Override
    public String toString() {
        StringBuilder pets = new StringBuilder();
        
        if (!favoritePets.isEmpty()) {
            for (int i = 0; i < favoritePets.size(); i++) {
                Pet item = favoritePets.get(i);
                pets.append(item.toString());
                if (i < favoritePets.size() - 1) {
                    pets.append(", ");
                }
            }
        } else {
            pets.append("No favorite pets");
        }
        
        return String.format(STRING_FORMAT_FavoritePets,username, pets.toString());
    }


}
