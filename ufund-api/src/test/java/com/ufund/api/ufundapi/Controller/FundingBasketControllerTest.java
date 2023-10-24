package com.ufund.api.ufundapi.Controller;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.controller.FundingBasketController;
import com.ufund.api.ufundapi.persistence.FundingBasketDAO;

/*
 * Author: Cheyenne Xinyin Zhang @xz3322
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
    public void setupcupboardController() {
        mockFundingBasketDAO = mock(FundingBasketDAO.class);
        fundingBasketController = new FundingBasketController(mockFundingBasketDAO);
    }
    
    @Test
    public void createFundingBasket(){

    }

    @Test
    public void addNeedToBasket(){
        
    }

    @Test
    public void addNeedToBasketInvalid(){
        
    }

    @Test
    public void removeNeedFromBasket(){
        
    }

    @Test
    public void removeNeedFromBasketInvalid(){
        
    }

    @Test
    public void getFundingBasket(){
        
    }
}

