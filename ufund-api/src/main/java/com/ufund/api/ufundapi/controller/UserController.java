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

import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.FundingBasketDAO;
import com.ufund.api.ufundapi.persistence.UserDAO;

@RestController
@RequestMapping("/user")
public class UserController {

     private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    private UserDAO userDAO;

    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    
       private boolean validateHelperLogin(String value) {
       
        if (value == null) {
            return false;
        }
        
        if(value.equalsIgnoreCase("admin")) {
            return false;
        }
        
        return true;
    }
    
    @PostMapping(" ")
    public ResponseEntity<User> createUser(@RequestBody User user){
        LOG.info("POST /user " + user);

        // if(!validateHelperLogin(fundingBasket.getfundingBasketName())){
        //     return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        // }

        try {
            
            User newUser = userDAO.createUser(user);
            return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
            

        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("userName/{username}")
    public ResponseEntity<String> getUsername(@PathVariable("username") String username){

        try {

            String user = userDAO.getUserName(username);
            if(user != null){
                return new ResponseEntity<String>(user,HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("No username available",HttpStatus.OK);
            }
            
        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
