package com.ufund.api.ufundapi.model;

import java.util.HashMap;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoritePets {
 
    public static final String STRING_FORMAT_FavoritePets = "FavoritePets [username=%s, favoritePets=%s ]  ";

 
    @JsonProperty("username") private String username;
    @JsonProperty("favoritePets") private Map<Integer,Pet> favPets;
    /**
     * class to track a list of favorite pets for a user
     * @author Garrett Geyer, Carla Lopez, Cheyyene, Rachel, Micheal
     * @param username name of user
     * @param favoritePets favorite pets for user 
     */

    public FavoritePets(@JsonProperty("username") String username, @JsonProperty("favoritePets") Map<Integer,Pet> favoritePets){
        this.username = username;
        this.favPets = (favoritePets != null) ? favoritePets : new HashMap<>();
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
        return this.favPets;
    }
    public void setFavoritePets(Pet pet) {
        this.favPets.put(pet.getId(),pet);
    }

    @Override
    public String toString() {
        StringBuilder pets = new StringBuilder();
        
        if (!favPets.isEmpty()) {
            for (int i = 0; i < favPets.size(); i++) {
                Pet item = favPets.get(i);
                pets.append(item.toString());
                if (i < favPets.size() - 1) {
                    pets.append(", ");
                }
            }
        } else {
            pets.append("No favorite pets");
        }
        
        return String.format(STRING_FORMAT_FavoritePets,username, pets.toString());
    }


}
