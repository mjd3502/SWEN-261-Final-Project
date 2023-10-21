package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

@Component
public class UserFileDAO implements UserDAO{

    Map<Integer,User> users;

    private ObjectMapper objectMapper;

    private static int nextId;

    private  String filename;


    public UserFileDAO(@Value("$users.file")String filename,ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();

    }


    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
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
        User[] users = getUsersArray();
        objectMapper.writeValue(new File(filename),users);
        return true;

    }

    private boolean load() throws IOException{
        users = new TreeMap<>();
        nextId = 0;

        User[] userArray = objectMapper.readValue(new File(filename),User[].class);

        for(User user: userArray){
            users.put(user.getId(),user);
            if (user.getId() > nextId)
                nextId = user.getId();
        }
        ++nextId;
        return true;

    }

    @Override
    public User getUserbyId(int id){
        synchronized(users){
            if(users.containsKey(id)){
                return users.get(id);
            }else{
                return null;
            }
        }

    }


    @Override
    public User createUser(User user) throws IOException {
        synchronized(users){
            User newUser =  new User(nextId(), user.getName());
            users.put(user.getId(), newUser);
            save();
            return user;
        }
       
    }

    @Override
    public User addNeedToFundingBasket(int userId,Need need) throws IOException {
        User user = this.getUserbyId(userId);
        synchronized(users){
            if(user != null){
                user.setFundingBasket(need);
                save();
            }
        }
        return user;
    }


    @Override
    public User removeNeedFromFundingBasket(int userId, Need need) throws IOException {
       User user = this.getUserbyId(userId);
       synchronized(users){
        if(user != null){
            user.getFundingBasket().remove(need);
            save();
        }
       }
       return user;
    }
    
}
