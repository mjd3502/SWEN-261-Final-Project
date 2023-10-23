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
import com.ufund.api.ufundapi.controller.FundingBasketController;
import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

@Component
public class FundingBasketFileDAO implements FundingBasketDAO{
     private static final Logger LOG = Logger.getLogger(FundingBasketFileDAO.class.getName());

    Map<String,FundingBasket> fundingBasket;

    private ObjectMapper objectMapper;

    private static String nextUserName;

    private  String filename;


    public FundingBasketFileDAO(@Value("${fundingBasket.file}") String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();

    }


    private FundingBasket[] getFundingBasketsArray(){
        ArrayList<FundingBasket> fundingBasketList = new ArrayList<>();
        for(FundingBasket FundingBasket: FundingBaskets.values()){
            fundingBasketList.add(FundingBasket);
        }

        FundingBasket[] fundingBasketArray = new FundingBasket[fundingBasketList.size()];
        fundingBasketList.toArray(fundingBasketArray);
        return fundingBasketArray;

    }

   

    private boolean save() throws IOException{
        FundingBasket[] fundingBasketArray = getFundingBasketsArray();
        objectMapper.writeValue(new File(filename),fundingBasketArray);
        return true;
        
    }

    private boolean load() throws IOException{
        FundingBaskets = new TreeMap<>();

        FundingBasket[] fundingBasketArray = objectMapper.readValue(new File(filename),FundingBasket[].class);
        for(FundingBasket fundingBasket: fundingBasketArray){
            FundingBaskets.put(fundingBasket.ge,user);
        }
        return true;

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



    @Override
    public FundingBasket createFundingBasket(User user) throws IOException {
        synchronized(users){
            List<Need> listOFNeeds = new ArrayList<>();
            User newUser =  new User(user.getUserName(),listOFNeeds);
            users.put(user.getUserName(), newUser);
            save();
            return user;
        }
       
    }

    @Override
    public FundingBasket addNeedToFundingBasket(String userName,Need need) throws IOException {
        User user = this.getUserbyName(userName);
        synchronized(users){
            if(user != null){
                user.setFundingBasket(need);
                save();
                LOG.info("added to file");
            }else{
                 LOG.info("not added :( ");
            }
        }
        return user;
    }



    @Override
    public boolean removeNeedFromFundingBasket(String userName, int id) throws IOException {
       synchronized(users){
        FundingBasket user = this.getUserbyName(userName);
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
    public List<Need> getFundingBasket(String name) throws IOException {
        synchronized(fundingBasket){
            if(fundingBasket.containsKey(name)){
                return fundingBasket.get(name);
            }
        }
    }
}
