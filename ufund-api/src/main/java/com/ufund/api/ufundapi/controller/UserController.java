package com.ufund.api.ufundapi.controller;

import java.util.List;

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

        if(!validateHelperLogin(user.getUserName())){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            User new_user = userDao.createUser(user);
            return new ResponseEntity<User>(new_user,HttpStatus.CREATED);

        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserbyId(@PathVariable("id") int id ){

        try {
            
            User user = userDao.getUserbyId(id);
            if(user != null){
                return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
            }else{
                 return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/addNeed/{id}")
    public  ResponseEntity<User> addNeedToBasket(@PathVariable("id")int id, @RequestBody Need need){

        try {
            User user = userDao.addNeedToFundingBasket(id, need);

            if(user != null){
                return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
            }else{
                 return new ResponseEntity<User>(user,HttpStatus.NOT_ACCEPTABLE);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @DeleteMapping("/removeNeed/{id}")
    public ResponseEntity<User> removeNeedfromBasket(@PathVariable("id")int id, @RequestBody Need need){

        try {
            User user = userDao.removeNeedFromFundingBasket(id, need);

            if(user != null){
                return new ResponseEntity<User>(user,HttpStatus.OK);
            }else{
                 return new ResponseEntity<User>(user,HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @GetMapping("/fundingBasket/{id}")
    public ResponseEntity<List<Need>> getFundingBasket(@PathVariable("id") int id){

        try {
            List<Need> fundingBasket = userDao.getFundinBasket(id);
            return new ResponseEntity<List<Need>>(fundingBasket,HttpStatus.OK);

        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

}
