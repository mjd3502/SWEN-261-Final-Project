package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.CupboardDAO;

@RestController
@RequestMapping("/cupboard")
public class CupboardController {
    
    private CupboardDAO cupbaordDAO;

    public CupboardController(CupboardDAO cupboardDAO){
        this.cupbaordDAO = cupboardDAO;
    }


    @GetMapping("")
    public ResponseEntity<List<Need>> getEntireCupboard(){
        
        List<Need> response = new ArrayList<>();

        try {
            response = cupbaordDAO.getEntireCupboard();

            if(!response.isEmpty()){
                return new ResponseEntity<List<Need>>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<List<Need>>(response, HttpStatus.OK);
            }
        } catch (IOException e) {
            return null;
        }
    }
    @PostMapping("")
    public ResponseEntity<Need> createNeed(@RequestBody Need need) {
        //LOG.info("POST /needs " + need);

        // Replace below with your implementation
        try {
            
            Need need1 = cupbaordDAO.createNeed(need);
            if (need1 != null)
                return new ResponseEntity<Need>(need1,HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch(IOException e) {
            //LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Need> deleteNeed(@PathVariable int id) {
        //LOG.info("DELETE /heroes/" + id);

        // Replace below with your implementation
        try {
            boolean didit = cupbaordDAO.deleteNeed(id);
            if (didit){
                
                return new ResponseEntity<Need>(HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            //LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
