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
import com.ufund.api.ufundapi.persistence.FundingBasketDAO;
import com.ufund.api.ufundapi.model.FundingBasket;
// import com.ufund.api.ufundapi.persistence.fundingBasketDAO;

@RestController
@RequestMapping("/fundingBasket")
public class FundingBasketController {
     private static final Logger LOG = Logger.getLogger(FundingBasketController.class.getName());
    
    private FundingBasketDAO fundingBasketDao;

    public FundingBasketController(FundingBasketDAO fundingBasketDao){
        this.fundingBasketDao = fundingBasketDao;
    }
    
    @PostMapping(" ")
    public ResponseEntity<FundingBasket> createfundingBasket(@RequestBody FundingBasket fundingBasket){
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
    public ResponseEntity<FundingBasket> removeNeedFromBasket(@PathVariable("name")String name, @PathVariable("needId") int needId){

        try {
            boolean deleted = fundingBasketDao.removeNeedFromFundingBasket(name, needId);

            if(deleted){
                 LOG.info("deleteeeeeeeeeed");
                return new ResponseEntity<FundingBasket>(HttpStatus.OK);
            }else{
                 LOG.info("nooooooooo");
                 return new ResponseEntity<FundingBasket>(HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Need>> getFundingBasket(@PathVariable("name") String name){

        try {
            List<Need> fundingBasket = fundingBasketDao.getFundingBasket(name);
            return new ResponseEntity<List<Need>>(fundingBasket,HttpStatus.OK);

        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }


}
