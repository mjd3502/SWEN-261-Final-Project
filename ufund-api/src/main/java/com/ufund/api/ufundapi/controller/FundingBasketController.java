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
import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.persistence.fundingBasketDAO;

@RestController
@RequestMapping("/fundingBasket")
public class FundingBasketController {
     private static final Logger LOG = Logger.getLogger(FundingBasketController.class.getName());
    
    private fundingBasketDAO fundingBasketDao;

    public FundingBasketController(fundingBasketDAO fundingBasketDao){
        this.fundingBasketDao = fundingBasketDao;
    }

    private boolean validateHelperLogin(String value) {
       
        if (value == null) {
            return false;
        }
        
        if(value.equalsIgnoreCase("admin")) {
            return false;
        }
        
        return true;
    }
    
    @PostMapping(" ")
    public ResponseEntity<fundingBasket> createfundingBasket(@RequestBody fundingBasket fundingBasket){
        LOG.info("POST /fundingBasket " + fundingBasket);

        if(!validateHelperLogin(fundingBasket.getfundingBasketName())){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            
            fundingBasket newfundingBasket = fundingBasketDao.createfundingBasket(fundingBasket);
            return new ResponseEntity<fundingBasket>(newfundingBasket,HttpStatus.CREATED);
            

        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<fundingBasket> getfundingBasketbyId(@PathVariable("id") String name ){

        try {
            
            fundingBasket fundingBasket = fundingBasketDao.getfundingBasketbyName(name);
            if(fundingBasket != null){
                return new ResponseEntity<fundingBasket>(fundingBasket,HttpStatus.ACCEPTED);
            }else{
                 return new ResponseEntity<fundingBasket>(fundingBasket,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/addNeed/{name}")
    public  ResponseEntity<fundingBasket> addNeedToBasket(@PathVariable("name")String name, @RequestBody Need need){
        LOG.info("PUT /fundingBasket/addNeed/" + name);
        try {
            fundingBasket fundingBasket = fundingBasketDao.addNeedToFundingBasket(name, need);

            if(fundingBasket != null){
                return new ResponseEntity<fundingBasket>(fundingBasket,HttpStatus.ACCEPTED);
            }else{
                 return new ResponseEntity<fundingBasket>(fundingBasket,HttpStatus.NOT_ACCEPTABLE);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @DeleteMapping("/{name}/needId/{needId}")
    public ResponseEntity<fundingBasket> removeNeedfromBasket(@PathVariable("name")String name, @PathVariable("needId") int needId){

        try {
            boolean deleted = fundingBasketDao.removeNeedFromFundingBasket(name, needId);

            if(deleted){
                 LOG.info("deleteeeeeeeeeed");
                return new ResponseEntity<fundingBasket>(HttpStatus.OK);
            }else{
                 LOG.info("nooooooooo");
                 return new ResponseEntity<fundingBasket>(HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @GetMapping("/fundingBasket/{name}")
    public ResponseEntity<List<Need>> getFundingBasket(@PathVariable("name") String name){

        try {
            List<Need> fundingBasket = fundingBasketDao.getFundinBasket(name);
            return new ResponseEntity<List<Need>>(fundingBasket,HttpStatus.OK);

        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

}
