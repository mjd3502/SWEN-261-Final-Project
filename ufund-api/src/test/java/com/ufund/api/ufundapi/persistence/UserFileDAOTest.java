package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

@Tag("Persistence-tier")
public class UserFileDAOTest {
    private UserFileDAO userFileDAO;

    User [] testUsers;
    ObjectMapper mockObjectMapper;

    // /**
    //  * Before each test, we will create and inject a Mock Object Mapper to
    //  * isolate the tests from the underlying file
    //  * @throws IOException
    //  */
    @BeforeEach
    public void setUpUserFileDAO() throws IOException {
        testUsers = new User[1];
       
        testUsers[0] = new User("Michael");


        mockObjectMapper = mock(ObjectMapper.class);
        
        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),User[].class))
                .thenReturn(testUsers);
        userFileDAO = new UserFileDAO("doesnt_matter.txt",mockObjectMapper);
    }


    @Test 
    public void test_createNewUser(){
        User user = new User("Carla");

        try {
            User result = userFileDAO.createUser(user);
            assertEquals(result.getUsername(), "Carla");
            assertEquals(user.toString(),"User [userName=Carla ]");

        } catch (IOException e) {
            //test fails if an error is thrown
            assertFalse(true);
        }

    }


    @Test
    public void test_getUserName(){
        try {
            //gets username from already established list
            String name = userFileDAO.getUserName("Michael");
            assertEquals(name,"Michael");
        } catch (IOException e) {
            //test fails if an error is thrown
            assertFalse(true);
        }

    }

    @Test
    public void test_getBADUserName(){
        try {
            //gets bad username, should give back null value
            String name = userFileDAO.getUserName("THE DESTROYER OF WORLDS");
            
            assertEquals(null,name);
            
        } catch (IOException e) {
            //test fails if an error is thrown
            assertFalse(true);
        }

    }


}