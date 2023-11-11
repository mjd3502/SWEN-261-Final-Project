package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoritePets {
 
    public static final String STRING_FORMAT_FavoritePets = "FavoritePets [username=%s, favoritePets=%s ]  ";

 
    @JsonProperty("username") private String username;
    @JsonProperty("favoritePets") private List<Need> favoritePets;
    /**
     * class to 
     * @param username
     * @param favoritePets
     */

    public FavoritePets(@JsonProperty("username") String username, @JsonProperty("favoritePets") List<Need> favoritePets){
        this.username = username;
        this.favoritePets = this.favoritePets = (favoritePets != null) ? favoritePets : new ArrayList<>();
    }

    
    /**
     * Retrieves the username
     * @return The username
     */
    public String getUsername() {
        return username;
    }
    
    public List<Need> getFavoritePets() {
        return favoritePets;
    }
    public void setFavoritePets(Need need) {
        this.favoritePets.add(need);
    }

    @Override
    public String toString() {
        StringBuilder needs = new StringBuilder();
        
        if (!favoritePets.isEmpty()) {
            for (int i = 0; i < favoritePets.size(); i++) {
                Need item = favoritePets.get(i);
                needs.append(item.toString());
                if (i < favoritePets.size() - 1) {
                    needs.append(", ");
                }
            }
        } else {
            needs.append("No items in the funding basket");
        }
        
        return String.format(STRING_FORMAT_FavoritePets,username, needs.toString());
    }


}
