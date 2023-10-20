package com.ufund.api.ufundapi.persistence;

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

@Tag("Persistence-tier")
public class CupboardFileDAOTest {
    private CupboardFileDAO cupboard;

    /*Runs beofre each test to create a model cupboard */
    @BeforeEach
    public void setup(){
        cupboard = mock(CupboardFileDAO.class);
    }


    /*---------------------------------TESTS----------------------- 
     * make sure to Tag tests with @Test
    */






}
