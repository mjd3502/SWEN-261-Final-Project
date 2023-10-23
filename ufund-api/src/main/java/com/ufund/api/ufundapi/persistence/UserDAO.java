package com.ufund.api.ufundapi.persistence;

import com.ufund.api.ufundapi.model.User;

public interface UserDAO {
 
    
    User createUser(String username,String password);


    User getUserByName(String username);

    User getUsernamePassword(String username);
}
