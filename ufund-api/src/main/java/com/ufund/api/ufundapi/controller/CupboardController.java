package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.CupboardDAO;

@Controller
@RequestMapping("/cupboard")
public class CupboardController {
    
    private static final Logger LOG = Logger.getLogger(CupboardController.class.getName());
    private CupboardDAO cupboardDAO;

    public CupboardController(CupboardDAO cupboardDAO){
        this.cupboardDAO = cupboardDAO;
    }

    @GetMapping(" ")
    public ResponseEntity<List<Need>> getEntireCupboard(){
       List<Need> responseEntity = new ArrayList<>();

        try {
            responseEntity = cupboardDAO.getEntireCupboard();
            if(!responseEntity.isEmpty()){
                return new ResponseEntity<List<Need>>(responseEntity,HttpStatus.OK);
            }else{
                return new ResponseEntity<List<Need>>(responseEntity,HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/")
    public ResponseEntity<Need[]> searchCupboard(@RequestParam String name){
        LOG.info("GET /need/?name="+name);

        Need[] need;

        try {
            need = cupboardDAO.getNeedbyName(name);
            return new ResponseEntity<Need[]>(need, HttpStatus.OK);

        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}
