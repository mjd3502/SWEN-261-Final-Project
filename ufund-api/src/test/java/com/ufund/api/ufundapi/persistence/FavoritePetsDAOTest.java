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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.FavoritePets;
import com.ufund.api.ufundapi.model.Pet;


@Tag("Persistence-tier")
public class FavoritePetsDAOTest {
    private FavoritePetsFileDAO favoritePets;

    FavoritePets[] baskets;
    ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setUpFavoritePetsDAO() throws IOException{
        HashMap<Integer,Pet> pets = new HashMap<Integer, Pet>();
        pets.put(1,new Pet(1,"Random Pet", "lorem ipsum", true));
        
        baskets = new FavoritePets[1];
        baskets[0] = new FavoritePets("William", pets);
        

        mockObjectMapper = mock(ObjectMapper.class);
        
        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),FavoritePets[].class))
                .thenReturn(baskets);
        favoritePets = new FavoritePetsFileDAO("doesnt_matter.txt",mockObjectMapper);
    }


    @Test
    public void test_createBasket(){
        //setup
        HashMap<Integer,Pet> pets = new HashMap<Integer, Pet>();
        FavoritePets fb = new FavoritePets("Jessica", pets);
        FavoritePets result;
        try {
            result = favoritePets.createFavoritePets(fb);
            
            //assert
            assertEquals(result.getUsername(), "Jessica");
            assertEquals(result.toString(),"FavoritePets [username=Jessica, favoritePets=No favorite pets ]  ");
        } catch (IOException e) {
            //should not throw an error
            assertFalse(true);
        }

        
    }


    @Test
    public void test_create_Bad_Basket(){
       
        //invoke
        FavoritePets result = new FavoritePets(null,null);
        
        assertEquals(result.getUsername(), null);
        
    }

    @Test
    public void test_get_basket_by_name(){
       
        //invoke
        try {
            Map<Integer,Pet> result = favoritePets.getFavoritePets("William");
            Pet pet = result.get(1);
            
            assertEquals(pet.getName(),"Random Pet");
        
        } catch (IOException e) {
            //test fails if error thrown
            assertFalse(true);
        }
    }

    @Test
    public void test_get_basket_by_Bad_name(){
        //invoke
        try {
            Map<Integer,Pet> result = favoritePets.getFavoritePets("Jackson");
           
            assertEquals(result, new HashMap());
        
        } catch (IOException e) {
            //test fails if error thrown
            assertFalse(true);
        }

        //assert
    }

    @Test
    public void test_add_pet(){
        //setup
        Pet pet = new Pet(2,"Fake pet", "lorem ipsum", true);
        
        try {
            FavoritePets result = favoritePets.addPetToFavoritePets("William",pet);
            Map<Integer,Pet> pets = result.getFavoritePets();

            assertEquals(pets.get(2).getName(), "Fake pet");
        
        } catch (IOException e) {
            assertFalse(true);
        }
    }

    @Test
    public void test_delete_from_basket(){
        //setup
        Pet pet = new Pet(2,"Fake pet", "lorem ipsum", true);
        
        try {
            favoritePets.addPetToFavoritePets("William",pet);
            Boolean result = favoritePets.removePetFromFavoritePets("William", 2);

            //result should return true
            assertTrue(result);

        } catch (IOException e) {
            assertFalse(true);
        }
    }

    @Test
    public void test_delete_Bad_from_basket(){
        //setup
        Pet pet = new Pet(2,"Fake pet", "lorem ipsum", true);
        
        try {
            favoritePets.addPetToFavoritePets("William",pet);
            Boolean result = favoritePets.removePetFromFavoritePets("William", 3);

            //should return false
            assertTrue(!result);
            
        } catch (IOException e) {
            assertFalse(true);
        }
    }

   


    
}
