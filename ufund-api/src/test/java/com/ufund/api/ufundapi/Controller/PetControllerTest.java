package com.ufund.api.ufundapi.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.controller.PetController;
import com.ufund.api.ufundapi.model.Pet;
import com.ufund.api.ufundapi.persistence.PetDAO;

/*
 * Author: Carla Lopez @csl5623
 * Secondary Tests: Rachel Adkins @raa5749
 */

@Tag("Controller-tier")
public class PetControllerTest {
    private PetController petController;
    private PetDAO mockpetDAO;

    /**
     * Before each test, create a new petController object and inject
     * a mock pet DAO
     */
    @BeforeEach
    public void setuppetController() {
        mockpetDAO = mock(PetDAO.class);
        petController = new PetController(mockpetDAO);
    }

    /**
     * Tests: getSinglePet (success case)
     * 
     * Attempts to get a single pet. Checks if pet exists and that resulting status code is
     * OK because pet was successfully found and retrieved. 
     * @throws IOException
     */
    @Test 
    public void getASinglePetFoundTest() throws IOException{

        Pet pet = new Pet(0, "Bucky", "goodest dog", true);
        when(mockpetDAO.getSinglePetById(pet.getId())).thenReturn(pet);

        ResponseEntity<Pet> response = petController.getSinglePetbyId(pet.getId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(pet,response.getBody());
    }

    /**
     * Tests: getSinglePet (fail case)
     * 
     * Attempts to get a single pet. Checks if pet exists and that resulting status code is
     * NOT FOUND because pet does not exist.
     * @throws IOException
     */
    @Test 
    public void getSinglePetNotFound() throws IOException{

        Pet pet = new Pet(0, "Bucky", "goodest dog", true);
        when(mockpetDAO.getSinglePetById(pet.getId())).thenReturn(null);

        ResponseEntity<Pet> response = petController.getSinglePetbyId(pet.getId());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void getSinglePetInternalServerError() throws IOException {

        Pet pet = new Pet(0, "Bucky", "goodest dog", true);
        when(mockpetDAO.getSinglePetById(anyInt())).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<Pet> responseEntity = petController.getSinglePetbyId(pet.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: createPet (success case)
     * 
     * Attempts to create a new pet. Checks that resulting status code is CREATED because pet 
     * creation was successful.
     * @throws IOException
     */
    @Test
    public void createPet() throws IOException{

        Pet pet = new Pet(0, "Bucky", "goodest dog", true);
        when(mockpetDAO.createPet(pet)).thenReturn(pet);
    
        ResponseEntity<Pet> response = petController.createPet(pet);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(pet,response.getBody());
    }

    /**
     * Tests: createPet (fail case)
     * 
     * Attempts to create a new pet. Checks if pet exists and that resulting status code is
     * CONFLICT because pet already exists. 
     * @throws IOException
     */
    @Test
    public void createPetFailed() throws IOException{

        Pet pet = new Pet(0, "Bucky", "goodest dog", true);
        when(mockpetDAO.createPet(pet)).thenReturn(null);
    
        ResponseEntity<Pet> response = petController.createPet(pet);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    /**
     *Tests: createPet (fail case)
     * 
     * Attempts to create a new pet. Checks pet name and that resulting satus code is BAD
     * REQUEST because pet name cannot be empty.
     * @throws IOException
     */
    @Test
    public void createPetEmptyName() throws IOException{

        Pet pet = new Pet(0, "", "dig", true);
        when(mockpetDAO.createPet(pet)).thenReturn(null);
    
        ResponseEntity<Pet> response = petController.createPet(pet);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    /**
     * Tests: createPet (fail case)
     * 
     * Attempts to create a new pet. Checks pet type and that resulting status code is BAD
     * REQUEST because pet type cannot be zero.
     * @throws IOException
     */
    /* 
    @Test
    public void createPetEmptyType() throws IOException{

        Pet pet = new Pet(0, "Carla", 10, "donate dog food", 10, "");
        when(mockpetDAO.createPet(pet)).thenReturn(null);

        ResponseEntity<Pet> response = petController.createPet(pet);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    */
    /**
     * Tests: createPet (fail case)
     * 
     * Attempts to create a new pet. Checks pet quantity and that resulting status code is 
     * BAD REQUEST because quantity cannot be zero.
     * @throws IOException
     */
    /* 
    @Test
    public void createPetQuantityNegative() throws IOException{

        Pet pet = new Pet(0, "Volunteer to pet a dog", -1, "donate dog food", 10, "volunteer");
        when(mockpetDAO.createPet(pet)).thenReturn(null);

        ResponseEntity<Pet> response = petController.createPet(pet);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    */
    /**
     * Tests: createPet (fail case)
     * 
     * Attempts to create a new pet. Checks pet quantity and that resulting status code is 
     * BAD REQUEST because quantity is zero.
     * @throws IOException
     */
    /* 
    @Test
    public void createPetQuantityZero() throws IOException{

        Pet pet = new Pet(0, "Volunteer to pet a dog", 0, "donate dog food", 10, "volunteer");
        when(mockpetDAO.createPet(pet)).thenReturn(null);

        ResponseEntity<Pet> response = petController.createPet(pet);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    */
    /**
     * Tests: createPet (fail case)
     * 
     * Attempts to create a new pet. Checks pet cost and that resulting status code is BAD 
     * REQUEST because cost cannot be zero. 
     * @throws IOException
     */
    /*
    @Test
    public void createPetCostZero() throws IOException{

        Pet pet = new Pet(0, "Volunteer to pet a dog", 10, "donate dog food", 0, "volunteer");
        when(mockpetDAO.createPet(pet)).thenReturn(null);

        ResponseEntity<Pet> response = petController.createPet(pet);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    
    @Test
    public void createPetCostNegative() throws IOException{

        Pet pet = new Pet(0, "Volunteer to pet a dog", 10, "donate dog food", -54965, "volunteer");
        when(mockpetDAO.createPet(pet)).thenReturn(null);

        ResponseEntity<Pet> response = petController.createPet(pet);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    */
    @Test
    public void createPetInternalServerError() throws IOException{

        Pet pet = new Pet(00, "Charles", "not goodest dog", true);
        when(mockpetDAO.createPet(pet)).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Pet> responseEntity = petController.createPet(pet);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: getEntirePet
     * 
     * Attempts to get the entire pet of pets. Checks if pet exists, and that resulting 
     * status code is OK because the pet was successfully found and retrieved. 
     * @throws IOException
     */
    @Test
    public void getEntirePetTest() throws IOException{

        Pet pet = new Pet(0, "bucko", "god", true);
        Pet pet1 = new Pet(0, "bucky", "dog", true);
        Pet pet2 = new Pet(0, "bucker", "cat", true);

        List<Pet> listOfPets = List.of(pet,pet1,pet2);
        when(mockpetDAO.getAllPets()).thenReturn(listOfPets);

        ResponseEntity<List<Pet>> responseEntity = petController.getPetList();
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getEntirePetEmpty() throws IOException {
        when(mockpetDAO.getAllPets()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Pet>> responseEntity = petController.getPetList();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.emptyList(), responseEntity.getBody());
    }

    @Test
    public void getEntirePetInternalServerError() throws IOException {

        when(mockpetDAO.getAllPets()).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<List<Pet>> responseEntity = petController.getPetList();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: searchPet
     * 
     * Attempts to search a single pet by name. Checks if pet exists, and that resulting status
     * code is OK because the pet was successfully found and retrieved. 
     * @throws IOException
     */
    @Test
    public void getPetbyName() throws IOException{

        Pet pet = new Pet(0, "Carla", "new pet ", true);
        Pet[] petArray = new Pet[1];
        petArray[0] = pet;
        when(mockpetDAO.getPetbyName(pet.getName())).thenReturn(petArray);

        ResponseEntity<Pet[]> responseEntity = petController.searchPet(pet.getName());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getPetByNameLengthZero()throws IOException {

        String searchName = "nonExistentPet";
        when(mockpetDAO.getPetbyName(searchName)).thenReturn(new Pet[0]);

        ResponseEntity<Pet[]> responseEntity = petController.searchPet(searchName);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getPetByNameInternalServerError() throws IOException {

        Pet pet = new Pet(0, "george", "new pet ", true);
        when(mockpetDAO.getPetbyName(anyString())).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Pet[]> responseEntity = petController.searchPet(pet.getName());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: deletePet (success case)
     * 
     * Attempts to delete a pet by ID. Checks if pet exists, and that the resulting status
     * code is OK because the pet was successfully found and deleted.
     * @throws IOException
     */
    @Test
    public void deletePetbyId() throws IOException{

        int petId = 99;
        when(mockpetDAO.deletePet(petId)).thenReturn(true);
        
        ResponseEntity<Pet> responseEntity = petController.deletePet(petId);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    /**
     * Tests: deletePet (fail case)
     * 
     * Attempts to delete a pet by ID. Checks if pet exists, and that resulting status code is 
     * NOT FOUND because the pet to delete does not exist. 
     * @throws IOException
     */
    @Test
    public void deletePetbyIdNotFound() throws IOException{

        int petId = 20;
        when(mockpetDAO.deletePet(petId)).thenReturn(false);
        
        ResponseEntity<Pet> responseEntity = petController.deletePet(petId);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    @Test
    public void deletePetInternalServerError() throws IOException {
        
        int petId = 77;
        when(mockpetDAO.deletePet(petId)).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Pet> responseEntity = petController.deletePet(petId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: deletePetbyName (success case)
     * 
     * Attempts to delete a pet by name. Checks if pet exists, and that the resulting status
     * code is OK because the pet was successfully found and deleted.
     * @throws IOException
     */
    @Test
    public void deletePetbyName() throws IOException{

        String name = "Dog volunteering";
        when(mockpetDAO.deletePetbyName(name)).thenReturn(true);
        
        ResponseEntity<Pet> responseEntity = petController.deletePetbyName(name);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    /**
     * Tests: deletePetbyName function (fail case)
     * 
     * Attempts to delete a pet by name. Checks if pet exists, and that resulting status code is 
     * NOT FOUND because the pet to delete does not exist. 
     */
    @Test
    public void deletePetbyNameNotFound() throws IOException{

        String name = "Dog volunteering";
        when(mockpetDAO.deletePetbyName(name)).thenReturn(false);
        
        ResponseEntity<Pet> responseEntity = petController.deletePetbyName(name);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    @Test
    public void deletePetbyNameInternalServerError() throws IOException {

        String name = "Dog volunteering";
        when(mockpetDAO.deletePetbyName(name)).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Pet> responseEntity = petController.deletePetbyName(name);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: updatePet function (success case)
     * 
     * Attepmts to create a new pet, update it, and set a new name. Checks to make sure the pet 
     * exists, the name has been changed, and that resulting status code is OK because the pet has been 
     * successfully updated.
     * @throws IOException
     */
    @Test
    public void updatePet() throws IOException{

        Pet pet = new Pet(0, "Messi", "ballin pet", true);
        when(mockpetDAO.updatePet(pet)).thenReturn(pet);
        ResponseEntity<Pet> responseEntity = petController.updatePet(pet);
        pet.setName("Pedri");

        responseEntity = petController.updatePet(pet);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    /**
     * Tests: updatePet function (fail case)
     * 
     * Attempts to create a new pet and update it. Checks to make sure pet exists and that resulting
     * status code is NOT FOUND because originial pet does not exist.
     * @throws IOException
     */
    @Test
    public void updatePetFailed() throws IOException{

        Pet pet = new Pet(0, "Messi", "ballin pet", true);
        when(mockpetDAO.updatePet(pet)).thenReturn(null);
        
        ResponseEntity<Pet> responseEntity = petController.updatePet(pet);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    
    }

    @Test
    public void updatePetInternalServerError() throws IOException{
        Pet pet = new Pet(0, "Messi", "ballin pet", true);
        when(mockpetDAO.updatePet(pet)).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Pet> responseEntity = petController.updatePet(pet);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
