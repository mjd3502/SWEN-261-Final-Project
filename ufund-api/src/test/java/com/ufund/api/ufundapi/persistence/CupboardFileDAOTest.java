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
        testNeeds[0] = new Need(99,"food",1,0,"a thing",20,"goods");
        testNeeds[1] = new Need(100,"f",1,0,"a thing",20,"goods");
        testNeeds[2] = new Need(98,"Something",1,0,"a different thing",20,"goods");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Need[].class))
                .thenReturn(testNeeds);
        cupboardFileDAO = new CupboardFileDAO("doesnt_matter.txt",mockObjectMapper);
    }


    /*---------------------------------TESTS----------------------- 
     * @author garrett Geyer
    */

    @Test
    public void testUpdateNeed(){
        try{
        // Setup
        Need need = new Need(99,"food but better", 1, 0,"even more food", 10, "goods");

        // Invoke
        Need result = assertDoesNotThrow(() -> cupboardFileDAO.updateNeed(need),"Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Need actual = cupboardFileDAO.getSingleNeedById(need.getId());
        assertEquals(actual,need);
        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }
    


    @Test
    public void testCreateValidNeed(){
        Need need = new Need(101,"Hello!!", 1,0, "Lorem Ipsum", 1, "goods");
        

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
        Need need = new Need(101,"Delete me!", 1, 0,"Lorem Ipsum", 1, "goods");
        
        try {
            cupboardFileDAO.createNeed(need);

            // Invoke
            boolean result;
            try {
                result = cupboardFileDAO.deleteNeed(101);

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
        Need need = new Need(101,"Get this need by ID", 1,0, "Lorem Ipsum", 1, "goods");

        cupboardFileDAO.createNeed(need);

        // Invoke
        Need result;
        try {
            result = cupboardFileDAO.getSingleNeedById(101);

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
        Need need = new Need(101,"Get this need!", 1,0, "Lorem Ipsum", 1, "goods");

        cupboardFileDAO.createNeed(need);

        // Invoke
        Need[] search_result;
        try {
            search_result = cupboardFileDAO.getNeedbyName("Get this need!");

            // Analyze
            assertEquals(1,search_result.length);

            int result_id = search_result[0].getId();

            assertEquals(result_id,101);

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }

    @Test
    public void get2NeedsByName() throws IOException{
        // Setup
        Need need1 = new Need(124,"Dog food!", 1,0, "Lorem Ipsum", 1, "goods");
        Need need2 = new Need(123,"Dog walking", 1,0, "Lorem Ipsum", 1, "volunteer");

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

            assertEquals(102, result_id2);
            assertEquals(101, result_id1);

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }

    @Test
    public void test_deleteByName() throws IOException{
        Need deleteme = new Need(145, "Delete this!", 1,0, "Lorem Ipsum", 10, "goods");
        
        
        cupboardFileDAO.createNeed(deleteme);
        
        // Invoke
        try {
            boolean result = cupboardFileDAO.deleteNeedbyName("Delete this!");

            assertTrue(result);

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }

    @Test
    public void test_deleteInvalidByName() throws IOException{
        // Invoke
        try {
            boolean result = cupboardFileDAO.deleteNeedbyName("Delete this!");

            //result should be false
            assertTrue(!result);

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }
}
