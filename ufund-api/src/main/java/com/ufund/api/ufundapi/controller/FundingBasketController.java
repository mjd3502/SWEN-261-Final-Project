package com.ufund.api.ufundapi.controller;


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

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.FundingBasketDAO;
import com.ufund.api.ufundapi.model.FundingBasket;

@RestController
@RequestMapping("/fundingBasket")
public class FundingBasketController {
     private static final Logger LOG = Logger.getLogger(FundingBasketController.class.getName());
    
    private FundingBasketDAO fundingBasketDao;

    public FundingBasketController(FundingBasketDAO fundingBasketDao){
        this.fundingBasketDao = fundingBasketDao;
    }
    
    @PostMapping(" ")
    public ResponseEntity<FundingBasket> createFundingBasket(@RequestBody FundingBasket fundingBasket){
        LOG.info("POST /fundingBasket " + fundingBasket);

        try {
            
            FundingBasket newfundingBasket = fundingBasketDao.createFundingBasket(fundingBasket);
            return new ResponseEntity<FundingBasket>(newfundingBasket,HttpStatus.CREATED);
            

        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/addNeed/{name}")
    public  ResponseEntity<FundingBasket> addNeedToBasket(@PathVariable("name")String name, @RequestBody Need need){
        LOG.info("PUT /fundingBasket/addNeed/" + name);
        try {
            FundingBasket fundingBasket = fundingBasketDao.addNeedToFundingBasket(name,need);

            if(fundingBasket != null){
                return new ResponseEntity<FundingBasket>(fundingBasket,HttpStatus.ACCEPTED);
            }else{
                 return new ResponseEntity<FundingBasket>(fundingBasket,HttpStatus.NOT_ACCEPTABLE);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @DeleteMapping("/{name}/needId/{needId}")
    public ResponseEntity<Map<Integer,Need>> removeNeedFromBasket(@PathVariable("name")String name, @PathVariable("needId") int needId){

        try {
            boolean deleted = fundingBasketDao.removeNeedFromFundingBasket(name, needId);
            Map<Integer,Need> fundingBasket = fundingBasketDao.getFundingBasket(name);

            if(deleted){
                LOG.info("deleteeeeeeeeeed " );
                
                return new ResponseEntity<Map<Integer,Need>>(fundingBasket,HttpStatus.OK);
            }else{
                 LOG.info("nooooooooo");
                 return new ResponseEntity<Map<Integer,Need>>(fundingBasket,HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    } 

    @GetMapping("/{name}")
    public ResponseEntity<Map<Integer,Need>> getFundingBasket(@PathVariable("name") String name){

        try {
            Map<Integer,Need> fundingBasket = fundingBasketDao.getFundingBasket(name);
            return new ResponseEntity<Map<Integer,Need>>(fundingBasket,HttpStatus.OK);

        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @DeleteMapping("/clearFundingBasket/{userName}")
    public ResponseEntity<Map<Integer,Need>> clearFundingBasket(@PathVariable("userName") String username){


        try {
            
            boolean clearbasket = fundingBasketDao.clearFundingBasket(username);
            if(clearbasket){
                
                 Map<Integer,Need> basket = fundingBasketDao.getFundingBasket(username);
                return new ResponseEntity<Map<Integer,Need>>(basket,HttpStatus.OK);
            }else{
                
                 return new ResponseEntity<Map<Integer,Need>>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }
}
