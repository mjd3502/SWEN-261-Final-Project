package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class User {

    static final String STRING_FORMAT = "User [userName=%s, pasword =%s]";
    
    @JsonProperty("username") String username;
    @JsonProperty("password") String password;


    public User(@JsonProperty("username") String username, @JsonProperty("password")String password ){
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return String.format(STRING_FORMAT, username,password);
    }




}
