package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
