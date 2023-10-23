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
import com.ufund.api.ufundapi.controller.UserController;
import com.ufund.api.ufundapi.model.Need;
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
        // load();

    }


    @Override
    public User createUser(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
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
            // users.put(user.getUserName(),user);
        }
        return true;

    }


    @Override
    public User getUserByName(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByName'");
    }


    @Override
    public User getUsernamePassword(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsernamePassword'");
    }

    // @Override
    // public User getUserbyName(String name){
    //     synchronized(users){
    //         if(users.containsKey(name)){
    //             return users.get(name);
    //         }else{
    //             return null;
    //         }
    //     }
    // }

    // @Override
    // public User createUser(User user) throws IOException {
    //     synchronized(users){
    //         List<Need> listOFNeeds = new ArrayList<>();
    //         User newUser =  new User(user.getUserName(),listOFNeeds);
    //         users.put(user.getUserName(), newUser);
    //         save();
    //         return user;
    //     }
       
    // }

    // @Override
    // public User addNeedToFundingBasket(String userName,Need need) throws IOException {
    //     User user = this.getUserbyName(userName);
    //     synchronized(users){
    //         if(user != null){
    //             user.setFundingBasket(need);
    //             save();
    //             LOG.info("added to file");
    //         }else{
    //              LOG.info("not added :( ");
    //         }
    //     }
    //     return user;
    // }



    // @Override
    // public boolean removeNeedFromFundingBasket(String userName, int id) throws IOException {
    //    synchronized(users){
    //     User user = this.getUserbyName(userName);
    //     if(user != null){
    //         LOG.info("user is not null");
    //         List<Need> basket = user.getFundingBasket();
    //         for(Need need: basket){
    //             if(need.getId() == id){
    //                 basket.remove(need);
    //                 LOG.info("delteeeeeeed");
    //                 return save();
    //             }
    //         }
    //     }
    //     LOG.info("not deleteeed");
    //     return false;
    //    }
    // }


    // @Override
    // public List<Need> getFundingBasket(String name) throws IOException {
    //     User user = this.getUserbyName(name);
    //     return user.getFundingBasket();
    // }
}
