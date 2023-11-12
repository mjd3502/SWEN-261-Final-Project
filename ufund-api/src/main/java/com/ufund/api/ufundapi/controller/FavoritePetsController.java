package com.ufund.api.ufundapi.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.Pet;
import com.ufund.api.ufundapi.persistence.FavoritePetsDAO;
import com.ufund.api.ufundapi.model.FavoritePets;
// import com.ufund.api.ufundapi.persistence.favoritePetsDAO;
import com.ufund.api.ufundapi.model.Need;

@RestController
@RequestMapping("/favoritePets")
public class FavoritePetsController {
     private static final Logger LOG = Logger.getLogger(FavoritePetsController.class.getName());
    
    private FavoritePetsDAO favoritePetsDao;

    public FavoritePetsController(FavoritePetsDAO favoritePetsDao){
        this.favoritePetsDao = favoritePetsDao;
    }
    
    @PostMapping(" ")
    public ResponseEntity<FavoritePets> createFavoritePets(@RequestBody FavoritePets favoritePets){
        LOG.info("POST /favoritePets " + favoritePets);

        try {
            
            FavoritePets newfavoritePets = favoritePetsDao.createFavoritePets(favoritePets);
            return new ResponseEntity<FavoritePets>(newfavoritePets,HttpStatus.CREATED);
            

        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/addPet/{name}")
    public  ResponseEntity<FavoritePets> addPetToList(@PathVariable("name")String name, @RequestBody Pet pet){
        LOG.info("PUT /favoritePets/addPet/" + name);
        try {
            FavoritePets favoritePets = favoritePetsDao.addPetToFavoritePets(name,pet);

            if(favoritePets != null){
                return new ResponseEntity<FavoritePets>(favoritePets,HttpStatus.ACCEPTED);
            }else{
                 return new ResponseEntity<FavoritePets>(favoritePets,HttpStatus.NOT_ACCEPTABLE);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @DeleteMapping("/{name}/petId/{petId}")
    public ResponseEntity<Map<Integer,Pet>> removePetFromBasket(@PathVariable("name")String name, @PathVariable("petId") int petId){

        try {
            boolean deleted = favoritePetsDao.removePetFromFavoritePets(name, petId);
            Map<Integer,Pet> favoritePets = favoritePetsDao.getFavoritePets(name);

            if(deleted){
                LOG.info("deleteeeeeeeeeed " );
                
                return new ResponseEntity<Map<Integer,Pet>>(favoritePets,HttpStatus.OK);
            }else{
                 LOG.info("nooooooooo");
                 return new ResponseEntity<Map<Integer,Pet>>(favoritePets,HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    } 

    @GetMapping("/{name}")
    public ResponseEntity<Map<Integer,Pet>> getFavoritePets(@PathVariable("name") String name){

        try {
            Map<Integer,Pet> favoritePets = favoritePetsDao.getFavoritePets(name);
            return new ResponseEntity<Map<Integer,Pet>>(favoritePets,HttpStatus.OK);

        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }


}
