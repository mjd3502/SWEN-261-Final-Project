package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public class UserFileDAOTest {
    // UserFileDAO mockUserFileDao;

    // User [] testUsers;
    // ObjectMapper mockObjectMapper;

    // /**
    //  * Before each test, we will create and inject a Mock Object Mapper to
    //  * isolate the tests from the underlying file
    //  * @throws IOException
    //  */
    // @BeforeEach
    // public void setUpUserFileDAO() throws IOException {
    //     mockObjectMapper = mock(ObjectMapper.class);
    //     testUsers = new User[3];
    //     testUsers[0] = new User(1,"Wi-Fire", new ArrayList<>());
    //     testUsers[1] = new User(2,"Galactic Agent", new ArrayList<>());
    //     testUsers[2] = new User(3,"Ice Gladiator", new ArrayList<>());

    //     // When the object mapper is supposed to read from the file
    //     // the mock object mapper will return the hero array above
    //     when(mockObjectMapper
    //         .readValue(new File("doesnt_matter.txt"),User[].class))
    //             .thenReturn(testUsers);
    //     mockUserFileDao = new UserFileDAO("doesnt_matter.txt",mockObjectMapper);
    // }

    // @Test 
    // public void getNeedById(){
    //     User user = mockUserFileDao.getUserbyId(1);

    //     // Analzye
    //     assertEquals(user,testUsers[0]);
    // }

    // @Test 
    // public void createNewUser(){
    //     User user = new User(4,"Carla", new ArrayList<>());

    //     User result = assertDoesNotThrow(() -> mockUserFileDao.createUser(user),
    //                             "Unexpected exception thrown");

    //     assertNotNull(result);
    //     User actual = mockUserFileDao.getUserbyId(user.getId());
    //     assertEquals(actual.getId(),user.getId());
    //     assertEquals(actual.getUserName(),user.getUserName());
    // }

    // @Test
    // public void addNeedToFundingBasket(){
    //     Need need = new Need(0, "Donate food", 10, "donate dog fod", 0, "goods");

    //     User result = assertDoesNotThrow(() -> mockUserFileDao.addNeedToFundingBasket(1,need),
    //     "Unexpected exception thrown");

    //     User actual = mockUserFileDao.getUserbyId(1);
    //     assertEquals(result,actual);
    // }


    // @Test
    // public void RemoveNeedFromFundingBasket(){
    //     Need need = new Need(0, "Donate food", 10, "donate dog fod", 0, "goods");

    //     User result = assertDoesNotThrow(() -> mockUserFileDao.removeNeedFromFundingBasket(1,need),
    //     "Unexpected exception thrown");

    //     User actual = mockUserFileDao.getUserbyId(1);
    //     assertEquals(result,actual);
    //     assertEquals(result.getFundingBasket(),actual.getFundingBasket());
    // }

    
    
}
