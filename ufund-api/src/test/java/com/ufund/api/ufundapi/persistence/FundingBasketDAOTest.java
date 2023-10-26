package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public class FundingBasketDAOTest {
    private FundingBasketFileDAO fundingBasket;

    FundingBasket[] baskets;
    ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setUpFundingBasketDAO() throws IOException{
        ArrayList<Need> needs = new ArrayList<>();
        needs.add(new Need(1,"Random Need", 10, "lorem ipsum", 10, "goods"));
        
        baskets = new FundingBasket[1];
        baskets[0] = new FundingBasket("William", needs);
        

        mockObjectMapper = mock(ObjectMapper.class);
        
        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),FundingBasket[].class))
                .thenReturn(baskets);
        fundingBasket = new FundingBasketFileDAO("doesnt_matter.txt",mockObjectMapper);
    }


    @Test
    public void test_createBasket(){
        //setup
        List<Need> needs = new ArrayList<>();
        FundingBasket fb = new FundingBasket("Jessica", needs);
        FundingBasket result;
        try {
            result = fundingBasket.createFundingBasket(fb);
            
            //assert
            assertEquals(result.getUserName(), "Jessica");
            assertEquals(result.toString(),"FundingBasket [userName=Jessica, fundingBasket=No items in the funding basket ]  ");
        } catch (IOException e) {
            //should not throw an error
            assertFalse(true);
        }

        
    }


    @Test
    public void test_create_Bad_Basket(){
       
        //invoke
        FundingBasket result = new FundingBasket(null,null);
        
        assertEquals(result.getUserName(), null);
        
    }

    @Test
    public void test_get_basket_by_name(){
       
        //invoke
        try {
            List<Need> result = fundingBasket.getFundingBasket("William");
            Need need = result.get(0);
            
            assertEquals(need.getName(),"Random Need");
        
        } catch (IOException e) {
            //test fails if error thrown
            assertFalse(true);
        }
    }

    @Test
    public void test_get_basket_by_Bad_name(){
        //invoke
        try {
            List<Need> result = fundingBasket.getFundingBasket("Jackson");
           
            assertEquals(result, null);
        
        } catch (IOException e) {
            //test fails if error thrown
            assertFalse(true);
        }

        //assert
    }

    @Test
    public void test_add_need(){
        //setup
        Need need = new Need(2,"Fake need", 10, "lorem ipsum", 10, "goods");
        
        try {
            FundingBasket result = fundingBasket.addNeedToFundingBasket("William",need);
            List<Need> needs = result.getFundingBasket();

            assertEquals(needs.get(1).getName(), "Fake need");
        
        } catch (IOException e) {
            assertFalse(true);
        }
    }

    @Test
    public void test_delete_from_basket(){
        //setup
        Need need = new Need(2,"Fake need", 10, "lorem ipsum", 10, "goods");
        
        try {
            fundingBasket.addNeedToFundingBasket("William",need);
            Boolean result = fundingBasket.removeNeedFromFundingBasket("William", 2);

            //result should return true
            assertTrue(result);

        } catch (IOException e) {
            assertFalse(true);
        }
    }

    @Test
    public void test_delete_Bad_from_basket(){
        //setup
        Need need = new Need(2,"Fake need", 10, "lorem ipsum", 10, "goods");
        
        try {
            fundingBasket.addNeedToFundingBasket("William",need);
            Boolean result = fundingBasket.removeNeedFromFundingBasket("William", 3);

            //should return false
            assertTrue(!result);
            
        } catch (IOException e) {
            assertFalse(true);
        }
    }


    
}
