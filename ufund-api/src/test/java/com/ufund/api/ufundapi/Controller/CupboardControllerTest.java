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
    private CupboardDAO mockCupboardDAO;
    
    @BeforeEach
    public void setupHeroController() {
        mockCupboardDAO = mock(CupboardDAO.class);
        cupboardController = new CupboardController(mockCupboardDAO);
    }

    @Test
    public void getEntireCupboard() throws IOException{
        List<Need> cupboard = List.of(
            new Need(1, "Pet dog", 10, "Opportunity to Pet a dog ", 10, "volunteering opportunity"),
            new Need(20, "Donate fod", 20, "Donate 10 bags of dog food", 50, "goods"));


        // thenReturn is a method in Mockito that allows you to define the return value of a mocked method.
        when(mockCupboardDAO.getEntireCupboard()).thenReturn(cupboard);

        ResponseEntity<List<Need>> responseEntity = cupboardController.getEntireCupboard();

        assertEquals(cupboard,responseEntity.getBody());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

}
