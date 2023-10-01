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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.CupboardDAO;

public class CupboardController {
    private static final Logger LOG = Logger.getLogger(CupboardController.class.getName());
    private CupboardDAO cupboardDao;

    @GetMapping("")
    public ResponseEntity<Need> getSingleNeed() {
        int id = Need.getId();
        LOG.info("GET /needs" + id);
        try {
            Need need = cupboardDao.getSingleNeed();
            if (need != null){
                return new ResponseEntity<Need>(need,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    @RestController
    @RequestMapping("/cupboard")
    public class CupboardController {

        private CupboardDAO cupbaordDAO;

        public CupboardController(CupboardDAO cupboardDAO){
            this.cupbaordDAO = cupboardDAO;
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
    
        /**
     * Updates the {@linkplain Need need} with the provided {@linkplain Need need} object, if it exists
     * 
     * @param need The {@link Need need} to update
     * 
     * @return ResponseEntity with updated {@link Need need} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Need> updateNeed(@RequestBody Need need) {
        LOG.info("PUT /need " + need);

        try {
            Need checkNeed = cupboardDAO.updateNeed(need);
            
            if (checkNeed != null){
                return new ResponseEntity<Need>(checkNeed,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
