package com.ufund.api.ufundapi.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public class UserTest {
    
    @Test
    public void testUserToString(){

        int id = 1;
        String userName = "carla";
        List<Need> list = List.of();

        String expectedString = String.format(User.STRING_FORMAT_USER,id,userName,"No items in the funding basket");

        User user = new User(1, "carla",new ArrayList<>());

        String actual = user.toString();

        assertEquals(expectedString,actual);


    }

    @Test
    public void voidUserWithFundingBasket(){
    String expectedString = "User [ id=1, userName=carla, fundingBasket=Need [id=0,name =Item 1, quantity = 10, description = Description 1, cost = 10, type = hello] ]  ";

    User user = new User(1, "carla", List.of(new Need(0, "Item 1", 10, "Description 1", 10, "hello")));
    // user.setFundingBasket(); // Add the list of Needs to the user

    String actual = user.toString();

    assertEquals(expectedString, actual);


    }

    
}
