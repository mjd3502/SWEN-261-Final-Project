package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class User {
    
    @JsonProperty("username") String username;
    @JsonProperty("password") String password;


}
