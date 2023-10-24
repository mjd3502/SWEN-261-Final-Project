package com.ufund.api.ufundapi.Controller;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.controller.UserController;
import com.ufund.api.ufundapi.persistence.UserDAO;
/*
 * Author: Cheyenne Xinyin Zhang @xz3322
 */

@Tag("Controller-tier")
public class UserControllerTest {
    private UserController userController;
    private UserDAO mockUserDAO;

    /**
     * Before each test, create a new userController object and inject
     * a mock need DAO
     */
    @BeforeEach
    public void setupcupboardController() {
        mockUserDAO = mock(UserDAO.class);
        userController = new UserController(mockUserDAO);
    }

    @Test
    public void createUser(){

    }

    @Test
    public void getUsername(){

    }

}