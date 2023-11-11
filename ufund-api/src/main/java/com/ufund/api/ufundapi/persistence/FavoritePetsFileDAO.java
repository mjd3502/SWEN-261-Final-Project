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


    private FavoritePets[] getFavoritePetssArray(){
        ArrayList<FavoritePets> favoritePetsList = new ArrayList<>();
        for(FavoritePets FavoritePets: favoritePetss.values()){
            favoritePetsList.add(FavoritePets);
        }

        FavoritePets[] favoritePetsArray = new FavoritePets[favoritePetsList.size()];
        favoritePetsList.toArray(favoritePetsArray);
        return favoritePetsArray;

    }

   

    private boolean save() throws IOException{
        FavoritePets[] favoritePetsArray = getFavoritePetssArray();
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
            List<Pet> listOFPets = new ArrayList<>();
            FavoritePets newFavoritePets =  new FavoritePets(favoritePets.getUsername(),listOFPets);
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
            List<Pet> basket = favoritePets.getFavoritePets();
            for(Pet pet: basket){
                if(pet.getId() == id){
                    basket.remove(pet);
                    LOG.info("delted");
                    return save();
                }
            }
        }
        LOG.info("not deletad");
        return false;
       }
    }


    @Override
    public List<Pet> getFavoritePets(String name) throws IOException {
        synchronized(favoritePetss){
            if(favoritePetss.containsKey(name)){
                FavoritePets favoritePets = favoritePetss.get(name);
                return favoritePets.getFavoritePets();
            }
        }
        return null;
    }
}
