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
import com.ufund.api.ufundapi.controller.PetController;
import com.ufund.api.ufundapi.model.Pet;
import com.ufund.api.ufundapi.persistence.PetDAO;

@Tag("Persistence-tier")
public class PetDAOTest {
    private PetFileDAO petFileDAO;
    Pet[] testPets;
    ObjectMapper mockObjectMapper;


    /*Runs beofre each test to create a model pet */
    @BeforeEach
    public void setup() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        testPets = new Pet[3];
        testPets[0] = new Pet(99,"dag","dog1",true);
        testPets[1] = new Pet(100,"dag2", "dog2", true);
        testPets[2] = new Pet(98,"dagIII", "dog 3", true);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Pet[].class))
                .thenReturn(testPets);
        petFileDAO = new PetFileDAO("doesnt_matter.txt",mockObjectMapper);
    }


    /*---------------------------------TESTS----------------------- 
     * @author garrett Geyer, micheal dibiase
    */

    @Test
    public void testUpdatePet(){
        try{
        // Setup
        Pet pet = new Pet(99,"dog1better","even more dog", true);

        // Invoke
        Pet result = assertDoesNotThrow(() -> petFileDAO.updatePet(pet),"Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Pet actual = petFileDAO.getSinglePetById(pet.getId());
        assertEquals(actual,pet);
        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }
    
    
    @Test
    public void testCreateValidPet(){
        Pet pet = new Pet(101,"Hello!!", "Lorem Ipsum", true);
        

        // Invoke
        Pet result;
        try {
            result = petFileDAO.createPet(pet);

            // Analyze, result will be true if the pet created matches criteria of uploaded pet
            assertEquals(result.getId(),pet.getId());
            assertEquals(result.getName(),pet.getName());
            assertEquals(result.getDescription(),pet.getDescription());


        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }

    @Test
    public void testCreateNullPet(){
        Pet pet = null;
        // Invoke
        Pet result;
        try {
            result = petFileDAO.createPet(pet);

            assertFalse(true);

        } catch (IOException | NullPointerException e) {
            //should throw error becuase unexceptable value
            assertTrue(true);
        } 
    }


    @Test
    public void testDeleteExistingPet(){
        // Setup
        Pet pet = new Pet(101,"Delete me!","Lorem Ipsum", true);
        
        try {
            petFileDAO.createPet(pet);

            // Invoke
            boolean result;
            try {
                result = petFileDAO.deletePet(101);

                // Analyze, result will equal true if the pet was deleted
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
    public void testDeleteFakePet(){
        //Deleting a pet which does not exist

        // Invoke
        boolean result;
        try {
            result = petFileDAO.deletePet(10000);

            // Analyze, result will equal true if deleting the pet returned false
            assertFalse(result);


        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }


    @Test
    public void testGetSinglePet() throws IOException{
        // Setup
        Pet pet = new Pet(101,"Get this pet by ID", "Lorem Ipsum", true);

        petFileDAO.createPet(pet);

        // Invoke
        Pet result;
        try {
            result = petFileDAO.getSinglePetById(101);

            //make sure the pet is the same as the one we are trying to get
            assertEquals(result.getId(),pet.getId());
            assertEquals(result.getName(),pet.getName());
            assertEquals(result.getDescription(),pet.getDescription());

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }


    @Test
    public void testGetInvalidSinglePet() throws IOException{
        // Invoke
        Pet result;
        try {
            result = petFileDAO.getSinglePetById(20);

            //make sure the return is null as there is no pet with said id
            assertNull(result);

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }


    @Test
    public void getOnePetbyName() throws IOException{
        // Setup
        Pet pet = new Pet(101,"Get this pet!", "Lorem Ipsum", true);

        petFileDAO.createPet(pet);

        // Invoke
        Pet[] search_result;
        try {
            search_result = petFileDAO.getPetbyName("Get this pet!");

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
    public void get2PetsByName() throws IOException{
        // Setup
        Pet pet1 = new Pet(124,"Dog!", "Lorem Ipsum", true);
        Pet pet2 = new Pet(123,"Dog2", "Lorem Ipsum", true);

        petFileDAO.createPet(pet1);
        petFileDAO.createPet(pet2);

        // Invoke
        Pet[] search_result;
        try {
            search_result = petFileDAO.getPetbyName("Dog");

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
        Pet deleteme = new Pet(145, "Delete this!", "Lorem Ipsum", true);
        
        
        petFileDAO.createPet(deleteme);
        
        // Invoke
        try {
            boolean result = petFileDAO.deletePetbyName("Delete this!");

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
            boolean result = petFileDAO.deletePetbyName("Delete this!");

            //result should be false
            assertTrue(!result);

        } catch (IOException e) {
            //if error was thrown assert false, test failed
            assertFalse(true);
        }
    }








}
