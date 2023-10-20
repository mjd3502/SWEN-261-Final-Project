package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.User;

public interface UserDAO {

    User createUser() throws IOException;

    User addNeedToFundingBasket() throws IOException;
    
    
}
