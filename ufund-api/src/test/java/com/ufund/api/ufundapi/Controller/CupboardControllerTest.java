package com.ufund.api.ufundapi.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.controller.CupboardController;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.CupboardDAO;

@Tag("Controller-tier")
public class CupboardControllerTest {
    private CupboardController cupboardController;
    private CupboardDAO mockcupboardDAO;

    /**
     * Before each test, create a new cupboardController object and inject
     * a mock need DAO
     */
    @BeforeEach
    public void setupcupboardController() {
       mockcupboardDAO = mock(CupboardDAO.class);
        cupboardController = new CupboardController(mockcupboardDAO);
    }


    /**
     * Method for 
     */
    @Test 
    public void getASingleNeedTest(){

    }

     /**
     * Method for 
     */
    @Test
    public void getEntireCupboardTest(){

    }


     /**
     * Method for 
     */
    @Test
    public void getNeedbyName(){

    }


     /**
     * Method for 
     */
    @Test
    public void deleteNeed(){

    }


     /**
     * Method for 
     */
    @Test
    public void updateNeed(){

    }


     /**
     * Testing method for creating 
     */ 
    @Test
    public void createNeed(){

    }
   
}
