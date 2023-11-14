package com.ufund.api.ufundapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;

@Tag("Model-tier")
public class FundingBasketTest {
    private FundingBasket fundingBasket;
    
    @BeforeEach
    public void setUp(){
        fundingBasket = new FundingBasket("carlitaxmessi",new HashMap<>());
    }

    @Test
    public void testConstructor(){
        assertEquals("carlitaxmessi",fundingBasket.getUserName());
        assertEquals(new HashMap(),fundingBasket.getFundingBasket());

    }

    @Test
    public void getUsername(){

        assertEquals("carlitaxmessi",fundingBasket.getUserName());
    }

    @Test
    public void getFundingBasket(){
        assertEquals(new HashMap<>(),fundingBasket.getFundingBasket());
    }


    @Test
    public void setFundingBasket(){
        Need need = new Need(1, "carla", 10, 1, "hello", 10, "volunteer");
        fundingBasket.setFundingBasket(need);
        assertTrue(fundingBasket.getFundingBasket().containsValue(need));
    }


    @Test
    public void fundingBaskettoString(){
        String stirng = "FundingBasket [userName=carlitaxmessi, fundingBasket=No items in the funding basket ]  ";
        assertEquals(stirng,fundingBasket.toString());
    }

    @Test 
    public void FundingBasketwithItems(){
        Need need = new Need(1, "carla", 10, 1, "hello", 10, "volunteer");
        fundingBasket.setFundingBasket(need);
        String string ="FundingBasket [userName=carlitaxmessi, fundingBasket=Need [id=1,name =carla, quantity = 10, surplus = 1, description = hello, cost = 10, type = volunteer] ]  ";
        assertEquals(string,fundingBasket.toString());
    }

}
