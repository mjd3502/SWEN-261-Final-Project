package com.ufund.api.ufundapi.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public class UserTest {
    
    @Test
    public void testUserToString(){

        int id = 1;
        String userName = "carla";
        List<Need> list = List.of(new Need(1, "carla", 10, "null", 10, "null"));

        String expectedString = String.format(User.STRING_FORMAT_USER,id,userName,list);

        User user = new User(1, "carla",List.of(new Need(1, "carla", 10, "null", 10, "null")));

        String actual = user.toString();

        assertEquals(expectedString,actual);


    }
}
