package com.ufund.api.ufundapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;


@Tag("Model-tier")
public class UserTest {
    
    private User user;


    @BeforeEach
    public void setup(){

        user = new User("carlitaxmessi");


    }

    @Test
    public void testUsername(){
        
       assertEquals("carlitaxmessi",user.getUsername());
    }
    

    @Test
    public void testSetUsername() {
        User user = new User("originalUsername");
        String expectedUsername = "testUsername";

        user.setUsername(expectedUsername);

        String actualUsername = user.getUsername();
        assertEquals(expectedUsername, actualUsername, "Username should be set correctly");
    }

    @Test
    public void usernameToString(){
        assertEquals("User [userName=carlitaxmessi ]",user.toString());
    }
    
}
