package com.ufund.api.ufundapi.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.controller.UserController;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.UserDAO;
/**
 * Tests for the User Controller Class
 * 
 * @author Cheyenne Xinyin Zhang @xz3322
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
    public void setupUserController() {
        mockUserDAO = mock(UserDAO.class);
        userController = new UserController(mockUserDAO);
    }

    @Test
    public void createUser() throws IOException{
        //Setup
        User user = new User("admin");
        when(mockUserDAO.createUser(user)).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = userController.createUser(user);
    
        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void createUserInvalid() throws IOException{
        //Setup
        User user = new User("%(*#&)@/");
        when(mockUserDAO.createUser(user)).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = userController.createUser(user);
    
        // Analyze
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void createUserInternalServerError() throws IOException {
        //Setup
        User user = new User("admin");
        when(mockUserDAO.createUser(user)).thenThrow(new RuntimeException("Internal Server Error"));
        //Invoke
        ResponseEntity<User> responseEntity = userController.createUser(user);
        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void getUser() throws IOException {
        //Setup
        User user = new User("admin");
        when(mockUserDAO.getUserByName("admin")).thenReturn(user);

        //Invoke
        ResponseEntity<User> response = userController.getUser("admin");

        //Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void getUserNotFound() throws IOException {
        //Setup
        when(mockUserDAO.getUserByName("nimda")).thenReturn(null);

        //Invoke
        ResponseEntity<User> response = userController.getUser("nimda");

        //Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(null,response.getBody());
    }

    @Test
    public void getUserInternalServerError() throws IOException {
        //Setup
        User user = new User("admin");
        when(mockUserDAO.getUserByName("admin")).thenThrow(new RuntimeException("Internal Server Error"));

        //Invoke
        ResponseEntity<User> response = userController.getUser("admin");

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void doesUserExist() throws IOException {
        //Setup
        User user = new User("admin");
        when(mockUserDAO.doesUserExist("admin")).thenReturn(true);

        //Invoke
        ResponseEntity<Boolean> response = userController.doesUserExist("admin");

        //Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(true,response.getBody());
    }


    @Test
    public void doesUserExistInternalServerError() throws IOException {
        //Setup
        User user = new User("admin");
        when(mockUserDAO.doesUserExist("admin")).thenThrow(new RuntimeException("Internal Server Error"));

        //Invoke
        ResponseEntity<Boolean> response = userController.doesUserExist("admin");

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
}