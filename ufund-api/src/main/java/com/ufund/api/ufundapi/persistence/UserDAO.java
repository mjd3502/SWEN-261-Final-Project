package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.List;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public interface UserDAO {

    User createUser(User user) throws IOException;

    User getUserbyName(String name) throws IOException;

    User addNeedToFundingBasket(String name, Need need) throws IOException;

    boolean removeNeedFromFundingBasket(String name,int need) throws  IOException;

    List<Need> getFundinBasket(String name) throws IOException;


    
}
