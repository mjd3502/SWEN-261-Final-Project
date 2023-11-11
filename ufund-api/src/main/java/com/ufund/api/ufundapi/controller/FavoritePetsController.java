package com.ufund.api.ufundapi.controller;

import java.util.List;
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

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.FavoritePetsDAO;
import com.ufund.api.ufundapi.model.FavoritePets;
// import com.ufund.api.ufundapi.persistence.favoritePetsDAO;

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


    @PutMapping("/addNeed/{name}")
    public  ResponseEntity<FavoritePets> addNeedToBasket(@PathVariable("name")String name, @RequestBody Need need){
        LOG.info("PUT /favoritePets/addNeed/" + name);
        try {
            FavoritePets favoritePets = favoritePetsDao.addNeedToFavoritePets(name,need);

            if(favoritePets != null){
                return new ResponseEntity<FavoritePets>(favoritePets,HttpStatus.ACCEPTED);
            }else{
                 return new ResponseEntity<FavoritePets>(favoritePets,HttpStatus.NOT_ACCEPTABLE);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @DeleteMapping("/{name}/needId/{needId}")
    public ResponseEntity<FavoritePets> removeNeedFromBasket(@PathVariable("name")String name, @PathVariable("needId") int needId){

        try {
            boolean deleted = favoritePetsDao.removeNeedFromFavoritePets(name, needId);

            if(deleted){
                 LOG.info("deleteeeeeeeeeed");
                return new ResponseEntity<FavoritePets>(HttpStatus.OK);
            }else{
                 LOG.info("nooooooooo");
                 return new ResponseEntity<FavoritePets>(HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Need>> getFavoritePets(@PathVariable("name") String name){

        try {
            List<Need> favoritePets = favoritePetsDao.getFavoritePets(name);
            return new ResponseEntity<List<Need>>(favoritePets,HttpStatus.OK);

        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }


}
