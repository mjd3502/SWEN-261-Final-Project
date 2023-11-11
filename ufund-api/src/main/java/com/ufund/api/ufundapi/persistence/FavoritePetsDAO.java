package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.List;

import com.ufund.api.ufundapi.model.FavoritePets;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public interface FavoritePetsDAO {

    FavoritePets createFavoritePets(FavoritePets FavoritePets) throws IOException;

    FavoritePets addNeedToFavoritePets(String userName, Need need) throws IOException;

    boolean removeNeedFromFavoritePets(String userName,int need) throws  IOException;

    List<Need> getFavoritePets(String userName) throws IOException;


    
}
