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
import com.ufund.api.ufundapi.model.FavoritePets;
import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.Pet;


@Component
public class FavoritePetsFileDAO implements FavoritePetsDAO{
     private static final Logger LOG = Logger.getLogger(FavoritePetsFileDAO.class.getName());

    Map<String,FavoritePets> favoritePetss;

    private ObjectMapper objectMapper;


    private  String filename;


    public FavoritePetsFileDAO(@Value("${favoritePets.file}") String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();

    }


    private FavoritePets[] getFavoritePetsArray(){
        ArrayList<FavoritePets> favoritePetsList = new ArrayList<>();
        for(FavoritePets FavoritePets: favoritePetss.values()){
            favoritePetsList.add(FavoritePets);
        }

        FavoritePets[] favoritePetsArray = new FavoritePets[favoritePetsList.size()];
        favoritePetsList.toArray(favoritePetsArray);
        return favoritePetsArray;

    }

   

    private boolean save() throws IOException{
        FavoritePets[] favoritePetsArray = getFavoritePetsArray();
        objectMapper.writeValue(new File(filename),favoritePetsArray);
        return true;
        
    }

    private boolean load() throws IOException{
        favoritePetss = new TreeMap<>();

        FavoritePets[] favoritePetsArray = objectMapper.readValue(new File(filename),FavoritePets[].class);
        for(FavoritePets favoritePets: favoritePetsArray){
            favoritePetss.put(favoritePets.getUsername(),favoritePets);
        }
        return true;

    }

    @Override
    public FavoritePets createFavoritePets(FavoritePets favoritePets) throws IOException {
        synchronized(favoritePetss){
            Map<Integer,Pet> favoritePets2 = new HashMap<>();
            FavoritePets newFavoritePets =  new FavoritePets(favoritePets.getUsername(),favoritePets2);
            favoritePetss.put(favoritePets.getUsername(), newFavoritePets);
            save();
            return newFavoritePets;
        }
       
    }

    @Override
    public FavoritePets addPetToFavoritePets(String userName,Pet pet) throws IOException {

        synchronized(favoritePetss){
            FavoritePets favoritePets = favoritePetss.get(userName);
            if(favoritePets != null){
                favoritePets.setFavoritePets(pet);
                save();
                LOG.info("added to file");
            }else{
                 LOG.info("not added >:( ");
            }
            return favoritePets;
        }
       
    }
    

    @Override
    public boolean removePetFromFavoritePets(String userName, int id) throws IOException {
       synchronized(favoritePetss){
        FavoritePets favoritePets = favoritePetss.get(userName);
        if(favoritePets != null){
            LOG.info("user is not null");
            Map<Integer,Pet> basket = favoritePets.getFavoritePets();
            for(Pet pet: basket.values()){
                if(pet.getId() == id){
                    basket.remove(pet.getId());
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
    public Map<Integer,Pet> getFavoritePets(String name) throws IOException {
        synchronized(favoritePetss){
            if(favoritePetss.containsKey(name)){
                FavoritePets favoritePets = favoritePetss.get(name);
                return favoritePets.getFavoritePets();
            }
        }
        return new HashMap<>();
    }
}
