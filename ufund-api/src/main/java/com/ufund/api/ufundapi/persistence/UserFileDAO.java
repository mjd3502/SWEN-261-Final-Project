package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.User;


@Component
public class UserFileDAO implements UserDAO{
     private static final Logger LOG = Logger.getLogger(UserFileDAO.class.getName());

    Map<String,User> users;

    private ObjectMapper objectMapper;

    private static String nextUserName;

    private  String filename;


    public UserFileDAO(@Value("${users.file}")String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();

    }


    private User[] getUsersArray(){
        ArrayList<User> userList = new ArrayList<>();
        for(User user: users.values()){
            userList.add(user);
        }

        User[] userArray = new User[userList.size()];
        userList.toArray(userArray);
        return userArray;

    }

   

    private boolean save() throws IOException{
        User[] userArray = getUsersArray();
        objectMapper.writeValue(new File(filename),userArray);
        return true;
        
    }

    private boolean load() throws IOException{
        users = new TreeMap<>();

        User[] userArray = objectMapper.readValue(new File(filename),User[].class);
        for(User user: userArray){
            users.put(user.getUsername(),user);
        }
        return true;

    }

    @Override
    public User createUser(User user) throws IOException {
        synchronized(users){
            User newUser = new User(user.getUsername());
            users.put(newUser.getUsername(),user);
            save();
            return newUser;
        }
    }




    @Override
    public String getUserName(String username) throws IOException{
        synchronized(users){
            if(users.containsKey(username)){
                User user = users.get(username);
                return user.getUsername();
            }
            return null;
        }
    }



}
