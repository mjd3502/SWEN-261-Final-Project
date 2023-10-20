package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public interface UserDAO {

    User createUser(User user) throws IOException;

    User getUserbyId(int id) throws IOException;

    User addNeedToFundingBasket(int userId, Need need) throws IOException;

    User removeNeedFromFundingBasket(int userId,Need need) throws  IOException;



    
}
