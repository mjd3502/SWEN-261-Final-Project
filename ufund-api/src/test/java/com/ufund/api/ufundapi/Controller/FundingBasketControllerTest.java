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

import com.ufund.api.ufundapi.controller.FundingBasketController;
import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.FundingBasketDAO;

/**
 * Tests for the Funding Basket Controller Class
 * 
 * @author Cheyenne Xinyin Zhang @xz3322
 */

@Tag("Controller-tier")
public class FundingBasketControllerTest {
    private FundingBasketController fundingBasketController;
    private FundingBasketDAO mockFundingBasketDAO;

    /**
     * Before each test, create a new userController object and inject
     * a mock need DAO
     */
    
    @BeforeEach
    public void setupFundingBasketController() {
        mockFundingBasketDAO = mock(FundingBasketDAO.class);
        fundingBasketController = new FundingBasketController(mockFundingBasketDAO);
    }
    
    @Test
    public void createFundingBasket() throws IOException{
        // Setup
        Need need = new Need(0, "donate food", 10, "donate dog food", 0, "goods");
        Need need1 = new Need(0, "donate toys", 1, "donate dog toys", 0, "goods");
        List<Need> listOfNeeds = List.of(need,need1);
        FundingBasket fb1 = new FundingBasket("user", listOfNeeds);
        when(mockFundingBasketDAO.createFundingBasket(fb1)).thenReturn(fb1);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingBasketController.createFundingBasket(fb1);
    
    
        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(fb1,response.getBody());
    } 

    @Test
    public void addNeedToBasket(){
        //TODO: implement
    }

    @Test
    public void addNeedToBasketInvalid(){
        //TODO: implement
    }

    @Test
    public void removeNeedFromBasket(){
        //TODO: implement
    }

    @Test
    public void removeNeedFromBasketInvalid(){
        //TODO: implement
    }

    @Test
    public void getFundingBasket(){
        //TODO: implement
    }
}

