package com.ufund.api.ufundapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.UserDAO;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserDAO userDao;

    public UserController(UserDAO userDao){

    }


}
