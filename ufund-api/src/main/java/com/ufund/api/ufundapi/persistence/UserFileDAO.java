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

    Map<Integer,User> users;

    private ObjectMapper objectMapper;

    private static int nextId;

    private  String filename;


    public UserFileDAO(@Value("${users.file}")String filename, ObjectMapper objectMapper) throws IOException{
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
        User[] userArray = getUsersArray();
        objectMapper.writeValue(new File(filename),userArray);
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
            List<Need> listOFNeeds = new ArrayList<>();
            User newUser =  new User(nextId(), user.getUserName(),listOFNeeds);
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
                LOG.info("added to file");
            }else{
                 LOG.info("not added :( ");
            }
        }
        // LOG.info("");
        return user;
    }

    private Map<Integer,Need> getFundingBasketMap(int userId){
        synchronized(users){
            Map<Integer,Need> fundingBasketMap = new HashMap<>();
            List<Need> fundingBasket = users.get(userId).getFundingBasket();
            for(Need need: fundingBasket){
                fundingBasketMap.put(need.getId(),need);
            }
             return fundingBasketMap;


        }
        
    }



    @Override
    public boolean removeNeedFromFundingBasket(int userId, int id) throws IOException {
       synchronized(users){
        User user = this.getUserbyId(userId);
        if(user != null){
            LOG.info("user is not null");
            List<Need> basket = user.getFundingBasket();
            for(Need need: basket){
                if(need.getId() == id){
                    basket.remove(need);
                    LOG.info("delteeeeeeed");
                    return save();
                }
            }
        }
        LOG.info("not deleteeed");
        return false;
       }
    }


    @Override
    public List<Need> getFundinBasket(int id) throws IOException {
        User user = this.getUserbyId(id);
        return user.getFundingBasket();
    }


}
