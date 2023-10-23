package com.ufund.api.ufundapi.controller;

import java.util.logging.Level;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ufund.api.ufundapi.model.FundingBasket;

public class UserController {
    
    //    private boolean validateHelperLogin(String value) {
       
    //     if (value == null) {
    //         return false;
    //     }
        
    //     if(value.equalsIgnoreCase("admin")) {
    //         return false;
    //     }
        
    //     return true;
    // }
    
    // @PostMapping(" ")
    // public ResponseEntity<FundingBasket> createfundingBasket(@RequestBody FundingBasket fundingBasket){
    //     LOG.info("POST /fundingBasket " + fundingBasket);

    //     if(!validateHelperLogin(fundingBasket.getfundingBasketName())){
    //         return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    //     }

    //     try {
            
    //         FundingBasket newfundingBasket = fundingBasketDao.createfundingBasket(fundingBasket);
    //         return new ResponseEntity<FundingBasket>(newfundingBasket,HttpStatus.CREATED);
            

    //     } catch (Exception e) {
    //         LOG.log(Level.SEVERE,e.getLocalizedMessage());
    //        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

}
