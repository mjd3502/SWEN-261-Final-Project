package com.ufund.api.ufundapi.model;



import com.fasterxml.jackson.annotation.JsonProperty;



public class User {

    static final String STRING_FORMAT = "User [userName=%s ]";
    
    @JsonProperty("username") String username;


    public User(@JsonProperty("username") String username){
        this.username = username;
        
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString(){
        return String.format(STRING_FORMAT, username);
    }




}
