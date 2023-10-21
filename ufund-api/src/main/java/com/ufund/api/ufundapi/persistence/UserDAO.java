package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.List;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public interface UserDAO {

    User createUser(User user) throws IOException;

    User getUserbyId(int id) throws IOException;

    User addNeedToFundingBasket(int userId, Need need) throws IOException;

    boolean removeNeedFromFundingBasket(int userId,int need) throws  IOException;

    List<Need> getFundinBasket(int id) throws IOException;




    
}
