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
        Need need1 = new Need(1, "donate toys", 1, "donate dog toys", 0, "goods");
        List<Need> listOfNeeds = List.of(need,need1);
        FundingBasket fundingBasket = new FundingBasket("user", listOfNeeds);
        when(mockFundingBasketDAO.createFundingBasket(fundingBasket)).thenReturn(fundingBasket);

        // Invoke
        ResponseEntity<FundingBasket> response = fundingBasketController.createFundingBasket(fundingBasket);
    
        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(fundingBasket,response.getBody());
    } 

    @Test
    public void addNeedToBasket() throws IOException{
        //Setup
        Need need = new Need(0, "donate toys", 10, "donate dog toys", 0, "goods");
        Need need1 = new Need(1, "donate food", 10, "donate dog food", 0, "goods");
        List<Need> listOfNeeds = List.of(need);
        FundingBasket fundingBasket = new FundingBasket("helper", listOfNeeds);
        when(mockFundingBasketDAO.addNeedToFundingBasket("helper", need1)).thenReturn(fundingBasket);

        //Invoke
        ResponseEntity<FundingBasket> response = fundingBasketController.addNeedToBasket("helper", need1);

        //Analyze
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        assertEquals(fundingBasket,response.getBody());
    }

    @Test
    public void addNeedToBasketInvalid() throws IOException{
        //Setup
        Need need = new Need(0, "donate toys", 10, "donate dog toys", 0, "goods");
        FundingBasket fundingBasket = null;
        when(mockFundingBasketDAO.addNeedToFundingBasket(null, need)).thenReturn(fundingBasket);

        //Invoke
        ResponseEntity<FundingBasket> response = fundingBasketController.addNeedToBasket(null, need);

        //Analyze
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
        assertEquals(null,response.getBody());
    }

    @Test
    public void removeNeedFromBasket() throws IOException{
        Need need = new Need(0, "donate toys", 10, "donate dog toys", 0, "goods");
        List<Need> listOfNeeds = List.of(need);
        FundingBasket fundingBasket = new FundingBasket("helper", listOfNeeds);
        when(mockFundingBasketDAO.removeNeedFromFundingBasket("helper", 0)).thenReturn(true);

        //Invoke
        ResponseEntity<FundingBasket> response = fundingBasketController.removeNeedFromBasket("helper", 0);

        //Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    public void removeNeedFromBasketInvalid(){
        //TODO: implement
    }

    public void getFundingBasket(){
        //TODO: implement
    }
}

