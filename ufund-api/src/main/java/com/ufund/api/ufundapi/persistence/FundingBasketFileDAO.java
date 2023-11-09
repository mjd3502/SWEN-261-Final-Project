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
import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;


@Component
public class FundingBasketFileDAO implements FundingBasketDAO{
     private static final Logger LOG = Logger.getLogger(FundingBasketFileDAO.class.getName());

    Map<String,FundingBasket> fundingBaskets;

    private ObjectMapper objectMapper;


    private  String filename;


    public FundingBasketFileDAO(@Value("${fundingBasket.file}") String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();

    }


    private FundingBasket[] getFundingBasketsArray(){
        ArrayList<FundingBasket> fundingBasketList = new ArrayList<>();
        for(FundingBasket FundingBasket: fundingBaskets.values()){
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
        fundingBaskets = new TreeMap<>();

        FundingBasket[] fundingBasketArray = objectMapper.readValue(new File(filename),FundingBasket[].class);
        for(FundingBasket fundingBasket: fundingBasketArray){
            fundingBaskets.put(fundingBasket.getUserName(),fundingBasket);
        }
        return true;

    }

    @Override
    public FundingBasket createFundingBasket(FundingBasket fundingBasket) throws IOException {
        synchronized(fundingBaskets){
            Map<Integer,Need> listOFNeeds = new HashMap<>();
            FundingBasket newFundingBasket =  new FundingBasket(fundingBasket.getUserName(),listOFNeeds);
            fundingBaskets.put(fundingBasket.getUserName(), newFundingBasket);
            save();
            return newFundingBasket;
        }
       
    }

    @Override
    public FundingBasket addNeedToFundingBasket(String userName,Need need) throws IOException {
        synchronized(fundingBaskets){
            FundingBasket fundingBasket = fundingBaskets.get(userName);
            if(fundingBasket != null){
                fundingBasket.setFundingBasket(need);
                save();
                LOG.info("added to file");
            }else{
                LOG.info("not added :( ");
            }
            return fundingBasket;
        }
       
    }
    

    @Override
    public boolean removeNeedFromFundingBasket(String userName, int id) throws IOException {
       synchronized(fundingBaskets){
        FundingBasket fundingBasket = fundingBaskets.get(userName);
        if(fundingBasket != null){
            LOG.info("user is not null");
            Map<Integer,Need> basket = fundingBasket.getFundingBasket();
            for(Need need: basket.values()){
                if(need.getId() == id){
                    basket.remove(need.getId());
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
    public Map<Integer,Need> getFundingBasket(String name) throws IOException {
        synchronized(fundingBaskets){
            if(fundingBaskets.containsKey(name)){
                FundingBasket fundingBasket = fundingBaskets.get(name);
                return fundingBasket.getFundingBasket();
            }
        }
        return new HashMap<>();
    }


    @Override
    public boolean clearFundingBasket(String userName) throws IOException {
      
        synchronized(fundingBaskets){
            if(fundingBaskets.containsKey(userName)){
               Map<Integer,Need> listOFNeeds = new HashMap<>();
                FundingBasket newFundingBasket =  new FundingBasket(userName,listOFNeeds);
                fundingBaskets.replace(userName, newFundingBasket);
                return save();
            }else{
                return false;
            }
        }

    }
    @Override
    public FundingBasket getFundingBasketObject(String name) throws IOException {
        synchronized(fundingBaskets){
            if(fundingBaskets.containsKey(name)){
                FundingBasket fundingBasket = fundingBaskets.get(name);
                return fundingBasket;
            }

        }
        return null;
    }
}
