package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;



public class User {
    static final String STRING_FORMAT = "User [name=%s]";

    @JsonProperty("name") private String name;
    @JsonProperty("loggedIn") private boolean loggedIn;

    public User(@JsonProperty("name") String name){
        this.name = name;
        this.loggedIn = false;
    }
    /**
     * Retrieves the name of the user
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    
}
