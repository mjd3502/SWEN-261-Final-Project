package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;


@Tag("Persistence-tier")
public class FundingBasketDAOTest {
    private FundingBasketFileDAO fundingBasketFileDAO;
    FundingBasket[] testBasket;
    ObjectMapper mockObjectMapper;

    // @BeforeEach
    // public void setUpFundingBasketDAO() throws IOException{
    //     mockObjectMapper = mock(ObjectMapper.class);

    //     Map<Integer, Need> needs = new HashMap<>();
        
    //     Need need = new Need(1,"Random Need", 10,0 , "lorem ipsum", 10, "goods");
    //     needs.put(need.getId(),need);
        
    //     FundingBasket fBasket = new FundingBasket("William", needs);
        

    //     mockObjectMapper = mock(ObjectMapper.class);
        
    //     // When the object mapper is supposed to read from the file
    //     // the mock object mapper will return the hero array above
    //     when(mockObjectMapper
    //         .readValue(new File("doesnt_matter.txt"),FundingBasket.class))
    //             .thenReturn(fBasket);
    //     fundingBasketFileDAO = new FundingBasketFileDAO("doesnt_matter.txt",mockObjectMapper);
    // }


    // @Test
    // public void test_create_Bad_Basket(){
       
    //     //invoke
    //     FundingBasket result = new FundingBasket(null,null);
        
    //     assertEquals(result.getUserName(), null);
        
    // }

    // @Test
    // public void test_get_basket_by_name() throws IOException{
    //     Map<Integer, Need> mockFundingBaskets = new HashMap<>();
    //     FundingBasket mockFundingBasket = new FundingBasket("basket",mockFundingBaskets);
    //     // mockFundingBasket.setFundingBasket(); // You need to define createSampleNeeds method
    //     // mockFundingBaskets.put("basketName", mockFundingBasket);

    //     // Set up the test scenario
    //     fundingBasket.getFundingBasket("basket");

    //     // Call the method under test
    //     Map<Integer, Need> result = fundingBasket.getFundingBasket("basket");

    //     // Assert the expected result
    //     assertEquals(mockFundingBasket.getFundingBasket(), result);
    //     // try {
    //     //     Map<Integer,Need> result = fundingBasket.getFundingBasket("helper");
    //     //     Need need = result.get(0);
            
    //     //     assertEquals(need.getName(),"Random Need");
        
    //     // } catch (IOException e) {
    //     //     //test fails if error thrown
    //     //     assertFalse(true);
    //     // }
    // }

    // @Test
    // public void test_get_basket_by_Bad_name(){
        
    //     try {
    //         Map<Integer,Need> result = fundingBasket.getFundingBasket("Jackson");
           
    //         assertEquals(result, null);
        
    //     } catch (IOException e) {
    //         //test fails if error thrown
    //         assertFalse(true);
    //     }

        
    // }

    // @Test
    // public void test_add_need(){
        
    //     Need need = new Need(2,"Fake need", 10,0, "lorem ipsum", 10, "goods");
        
    //     try {
    //         FundingBasket result = fundingBasket.addNeedToFundingBasket("William",need);
    //         Map<Integer,Need> needs = result.getFundingBasket();

    //         assertEquals(needs.get(1).getName(), "Fake need");
        
    //     } catch (IOException e) {
    //         assertFalse(true);
    //     }
    // }

    // @Test
    // public void test_delete_from_basket() throws IOException{
    //     //setup
    //     Need need = new Need(2,"Fake need", 10,0, "lorem ipsum", 10, "goods");
    //     FundingBasket basket = fundingBasket.addNeedToFundingBasket("William",need);
    //     Map<Integer,Need> fundingBasket = basket.getFundingBasket();

    //     try {
    //         // fundingBasket.addNeedToFundingBasket("William",need);
    //         Boolean result = fundingBasket.removeNeedFromBasket("William", 2);

    //         //result should return true
    //         assertTrue(result);

    //     } catch (IOException e) {
    //         assertFalse(true);
    //     }
    // }

    // @Test
    // public void test_delete_Bad_from_basket(){
    //     //setup
    //     Need need = new Need(2,"Fake need", 10,0, "lorem ipsum", 10, "goods");
        
    //     try {
    //         fundingBasket.addNeedToFundingBasket("William",need);
    //         Boolean result = fundingBasket.removeNeedFromFundingBasket("William", 3);

    //         //should return false
    //         assertTrue(!result);
            
    //     } catch (IOException e) {
    //         assertFalse(true);
    //     }
    // }


    
}
