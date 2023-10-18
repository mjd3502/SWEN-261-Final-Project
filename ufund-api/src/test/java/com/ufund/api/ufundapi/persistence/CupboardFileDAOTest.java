package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.controller.CupboardController;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.CupboardDAO;

@Tag("Persistence-tier")
public class CupboardFileDAOTest {
    private CupboardFileDAO cupboardFileDAO;
    Need[] testNeeds;
    ObjectMapper mockObjectMapper;

    /*Runs beofre each test to create a model cupboard */
    @BeforeEach
    public void setup() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        testNeeds = new Need[3];
        testNeeds[0] = new Need(99,"food",1,"a thing",20,"good");
        testNeeds[1] = new Need(100,"f",1,"a thing",20,"good");
        testNeeds[2] = new Need(99,"Something",1,"a different thing",20,"good");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Need[].class))
                .thenReturn(testNeeds);
        cupboardFileDAO = new CupboardFileDAO("doesnt_matter.txt",mockObjectMapper);
    }


    /*---------------------------------TESTS----------------------- 
     * make sure to Tag tests with @Test
    */
























































/*---------------------------------TESTS----------------------- 
     * @author Michael DiBiase mjd3502@rit.edu
    */
    
    @Test
    public void testCreateValidNeed(){
        Need need = new Need(73,"Hello!!", 1, "Lorem Ipsum", 1, "good");
        

        // Invoke
        Need result;
        try {
            result = cupboardFileDAO.createNeed(need);

            // Analyze, result will be true if the need created matches criteria of uploaded need
            assertEquals(result.getId(),need.getId());
            assertEquals(result.getName(),need.getName());
            assertEquals(result.getDescription(),need.getDescription());


        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }

    @Test
    public void testCreateNullNeed(){
        Need need = null;
        // Invoke
        Need result;
        try {
            result = cupboardFileDAO.createNeed(need);

            assertFalse(true);

        } catch (IOException | NullPointerException e) {
            //should throw error becuase unexceptable value
            assertTrue(true);
        } 
    }


    @Test
    public void testDeleteExistingNeed(){
        // Setup
        Need need = new Need(1000,"Delete me!", 1, "Lorem Ipsum", 1, "good");
        
        try {
            cupboardFileDAO.createNeed(need);

            // Invoke
            boolean result;
            try {
                result = cupboardFileDAO.deleteNeed(1000);

                // Analyze, result will equal true if the need was deleted
                assertTrue(result);


            } catch (IOException e) {
                //if error was thrown assert false, test failed
                assertFalse(true);
        }
        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }

    }

    @Test
    public void testDeleteFakeNeed(){
        //Deleting a need which does not exist

        // Invoke
        boolean result;
        try {
            result = cupboardFileDAO.deleteNeed(10000);

            // Analyze, result will equal true if deleting the need returned false
            assertTrue(!result);


        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }


    @Test
    public void testGetSingleNeed() throws IOException{
        // Setup
        Need need = new Need(20,"Get this need by ID", 1, "Lorem Ipsum", 1, "good");

        cupboardFileDAO.createNeed(need);

        // Invoke
        Need result;
        try {
            result = cupboardFileDAO.getSingleNeedById(20);

            //make sure the need is the same as the one we are trying to get
            assertEquals(result.getId(),need.getId());
            assertEquals(result.getName(),need.getName());
            assertEquals(result.getDescription(),need.getDescription());

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }


    @Test
    public void testGetInvalidSingleNeed() throws IOException{
        // Invoke
        Need result;
        try {
            result = cupboardFileDAO.getSingleNeedById(20);

            //make sure the return is null as there is no need with said id
            assertNull(result);

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }


    @Test
    public void getOneNeedbyName() throws IOException{
        // Setup
        Need need = new Need(999,"Get this need!", 1, "Lorem Ipsum", 1, "good");

        cupboardFileDAO.createNeed(need);

        // Invoke
        Need[] search_result;
        try {
            search_result = cupboardFileDAO.getNeedbyName("Get this need!");

            // Analyze
            assertEquals(1,search_result.length);

            int result_id = search_result[0].getId();

            assertEquals(result_id,999);

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }

    @Test
    public void get2NeedsByName() throws IOException{
        // Setup
        Need need1 = new Need(124,"Dog food!", 1, "Lorem Ipsum", 1, "good");
        Need need2 = new Need(123,"Dog walking", 1, "Lorem Ipsum", 1, "volunteer");

        cupboardFileDAO.createNeed(need1);
        cupboardFileDAO.createNeed(need2);

        // Invoke
        Need[] search_result;
        try {
            search_result = cupboardFileDAO.getNeedbyName("Dog");

            // Analyze
            assertEquals(search_result.length, 2);
            
            int result_id1 = search_result[0].getId();
            int result_id2 = search_result[1].getId();

            assertEquals(124, result_id2);
            assertEquals(123, result_id1);

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }








}
