package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.User;

public interface UserDAO {
 
    
    User createUser(User user)  throws IOException;

    
    String getUserName(String username)  throws IOException;

    String getUsernamePassword(String username)  throws IOException;
}
