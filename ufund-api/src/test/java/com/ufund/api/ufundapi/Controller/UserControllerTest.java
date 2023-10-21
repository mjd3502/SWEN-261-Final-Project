package com.ufund.api.ufundapi.Controller;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.controller.UserController;
import com.ufund.api.ufundapi.persistence.UserDAO;

@Tag("Controller-tier")
public class UserControllerTest {
    private UserController userController;
    private UserDAO mockUserDAO;

    /**
     * Before each test, create a new cupboardController object and inject
     * a mock need DAO
     */
    @BeforeEach
    public void setupcupboardController() {
        mockUserDAO = mock(UserDAO.class);
        userController = new UserController(mockUserDAO);
    }

    @Test
    public void createUsr(){

    }

    @Test
    public void getUserbyId(){



    }

    @Test 
    public void addNeedToBasket(){

    }

    @Test
    public void removeNeedFromBasket(){

    }

    @Test
    public void getFundingBasket(){
        
    }


}