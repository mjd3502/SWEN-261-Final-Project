package com.ufund.api.ufundapi.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.User;

import com.ufund.api.ufundapi.persistence.UserDAO;

@RestController
@RequestMapping("/user")
public class UserController {

     private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    private UserDAO userDAO;

    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    
    public static boolean checkLettersOrNumbers(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    @PostMapping(" ")
    public ResponseEntity<User> createUser(@RequestBody User user){
        

    
        boolean containsOnlyLettersOrNumbers = checkLettersOrNumbers(user.getUsername());

    
        
        if(!containsOnlyLettersOrNumbers){
            return new ResponseEntity<>(user,HttpStatus.NOT_ACCEPTABLE);
        }

        try {
          
            User newUser = userDAO.createUser(user);
            return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        try{
            User user = userDAO.getUserByName(username);
            if (user != null){
                return new ResponseEntity<User>(user,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
        
    }
    @GetMapping("/exists/{username}")
    public ResponseEntity<Boolean> doesUserExist(@PathVariable String username){
        try{
            Boolean exists = userDAO.doesUserExist(username);
            
            
            return new ResponseEntity<Boolean>(exists,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }
    
}
