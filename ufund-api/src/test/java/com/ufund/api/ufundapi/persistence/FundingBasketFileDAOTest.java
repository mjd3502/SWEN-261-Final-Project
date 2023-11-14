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
public class FundingBasketFileDAOTest {
    private FundingBasketFileDAO fundingBasketFileDAO;
    FundingBasket[] testBasket;
    ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setUpFundingBasketDAO() throws IOException{
        HashMap<Integer,Need> needs = new HashMap<Integer, Need>();
        needs.put(1,new Need(1,"f",1,0,"a thing",20,"goods"));

        testBasket = new FundingBasket[1];
        testBasket[0] = new FundingBasket("Cheyenne", needs);


        mockObjectMapper = mock(ObjectMapper.class);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),FundingBasket[].class))
                .thenReturn(testBasket);
        fundingBasketFileDAO = new FundingBasketFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void createBasket(){
        //setup
        HashMap<Integer,Need> needs = new HashMap<Integer, Need>();
        FundingBasket fb = new FundingBasket("Me", needs);
        FundingBasket result;
        try {
            result = fundingBasketFileDAO.createFundingBasket(fb);

            //assert
            assertEquals(result.getUserName(), "Me");
            assertEquals(result.toString(),"FundingBasket [userName=Me, fundingBasket=No items in the funding basket ]  ");
        } catch (IOException e) {
            //should not throw an error
            assertFalse(true);
        }


    }

    @Test
    public void getBasketByName(){

        //invoke
        try {
            Map<Integer,Need> result = fundingBasketFileDAO.getFundingBasket("Cheyenne");
            Need need = result.get(1);

            assertEquals(need.getName(),"f");

        } catch (IOException e) {
            //test fails if error thrown
            assertFalse(true);
        }
    }

    @Test
    public void getBasketByBadName(){

        //invoke
        try {
            Map<Integer,Need> result = fundingBasketFileDAO.getFundingBasket("Person");
            // Need need = result.get(1);

            assertEquals(result,new HashMap<>());

        } catch (IOException e) {
            //test fails if error thrown
            assertFalse(true);
        }
    }

    @Test
    public void addNeedToFundingBasket(){
        //setup
        Need need = new Need(99,"food",1,0,"a thing",20,"goods");

        try {
            FundingBasket result = fundingBasketFileDAO.addNeedToFundingBasket("Cheyenne",need);
            Map<Integer,Need> needs = result.getFundingBasket();

            assertEquals(needs.get(99).getName(), "food");

        } catch (IOException e) {
            assertFalse(true);
        }
    }

    @Test
    public void deleteFromBasket(){
        //setup
        Need need = new Need(99,"food",1,0,"a thing",20,"goods");

        try {
            fundingBasketFileDAO.addNeedToFundingBasket("Cheyenne",need);
            Boolean result = fundingBasketFileDAO.removeNeedFromFundingBasket("Cheyenne", need.getId());

            //result should return true
            assertTrue(result);

        } catch (IOException e) {
            assertFalse(true);
        }
    }

    @Test
    public void clearFundingBasket() throws IOException{
        boolean result = fundingBasketFileDAO.clearFundingBasket("Cheyenne");

        assertTrue(result);
    }

    @Test
    public void clearFundingBasketFalse() throws IOException{
        boolean result = fundingBasketFileDAO.clearFundingBasket("this is not a user");

        assertFalse(result);
    }
}
