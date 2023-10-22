package com.ufund.api.ufundapi.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.UserDAO;

@RestController
@RequestMapping("/user")
public class UserController {
     private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    
    private UserDAO userDao;

    public UserController(UserDAO userDao){
        this.userDao = userDao;
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

        if(!validateHelperLogin(user.getUserName())){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            
            User newUser = userDao.createUser(user);
            return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
            

        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserbyId(@PathVariable("id") String name ){

        try {
            
            User user = userDao.getUserbyName(name);
            if(user != null){
                return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
            }else{
                 return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/addNeed/{name}")
    public  ResponseEntity<User> addNeedToBasket(@PathVariable("name")String name, @RequestBody Need need){
        LOG.info("PUT /user/addNeed/" + name);
        try {
            User user = userDao.addNeedToFundingBasket(name, need);

            if(user != null){
                return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
            }else{
                 return new ResponseEntity<User>(user,HttpStatus.NOT_ACCEPTABLE);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @DeleteMapping("/{name}/needId/{needId}")
    public ResponseEntity<User> removeNeedfromBasket(@PathVariable("name")String name, @PathVariable("needId") int needId){

        try {
            boolean deleted = userDao.removeNeedFromFundingBasket(name, needId);

            if(deleted){
                 LOG.info("deleteeeeeeeeeed");
                return new ResponseEntity<User>(HttpStatus.OK);
            }else{
                 LOG.info("nooooooooo");
                 return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @GetMapping("/fundingBasket/{name}")
    public ResponseEntity<List<Need>> getFundingBasket(@PathVariable("name") String name){

        try {
            List<Need> fundingBasket = userDao.getFundinBasket(name);
            return new ResponseEntity<List<Need>>(fundingBasket,HttpStatus.OK);

        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

}
