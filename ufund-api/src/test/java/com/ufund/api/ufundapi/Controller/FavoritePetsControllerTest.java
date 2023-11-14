package com.ufund.api.ufundapi.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.controller.FavoritePetsController;
import com.ufund.api.ufundapi.model.FavoritePets;
import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.Pet;
import com.ufund.api.ufundapi.persistence.FavoritePetsDAO;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests for the Funding Basket Controller Class
 * 
 * @author Cheyenne Xinyin Zhang @xz3322
 */

@Tag("Controller-tier")
public class FavoritePetsControllerTest {
    private FavoritePetsController favoritePetsController;
    private FavoritePetsDAO mockFavoritePetsDAO;

    /**
     * Before each test, create a new userController object and inject
     * a mock pet DAO
     */
    
    @BeforeEach
    public void setupFavoritePetsController() {
        mockFavoritePetsDAO = mock(FavoritePetsDAO.class);
        favoritePetsController = new FavoritePetsController(mockFavoritePetsDAO);
    }
    
    @Test
    public void createFavoritePets() throws IOException{
        // Setup
        Pet pet = new Pet(0, "bucky", "good dog", true);
        Pet pet1 = new Pet(1, "buckster", "goodish dog", true);
        HashMap<Integer,Pet> listOfPets = new HashMap<Integer,Pet>();
        listOfPets.put(pet.getId(), pet);
        listOfPets.put(pet1.getId(), pet1);
        FavoritePets favoritePets = new FavoritePets("user", listOfPets);
        when(mockFavoritePetsDAO.createFavoritePets(favoritePets)).thenReturn(favoritePets);

        // Invoke
        ResponseEntity<FavoritePets> response = favoritePetsController.createFavoritePets(favoritePets);
    
        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(favoritePets,response.getBody());
    }

    @Test
    public void createFavoritePetsInternalServerError() throws IOException {
        //Setup
        Pet pet = new Pet(0, "bucky", "good dog", true);
        Pet pet1 = new Pet(1, "buckster", "goodish dog", true);
        HashMap<Integer,Pet> listOfPets = new HashMap<Integer,Pet>();
        listOfPets.put(pet.getId(), pet);
        listOfPets.put(pet1.getId(), pet1);
        FavoritePets favoritePets = new FavoritePets("user", listOfPets);
        when(mockFavoritePetsDAO.createFavoritePets(favoritePets)).thenThrow(new RuntimeException("Internal Server Error"));

        //Invoke
        ResponseEntity<FavoritePets> responseEntity = favoritePetsController.createFavoritePets(favoritePets);

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void addPetToBasket() throws IOException{
        //Setup
        Pet pet = new Pet(0, "bucky", "good dog", true);
        Pet pet1 = new Pet(1, "buckster", "goodish dog", true);
        HashMap<Integer,Pet> listOfPets = new HashMap<Integer,Pet>();
        listOfPets.put(pet.getId(), pet);
        listOfPets.put(pet1.getId(), pet1);
        FavoritePets favoritePets = new FavoritePets("helper", listOfPets);
        when(mockFavoritePetsDAO.addPetToFavoritePets("helper", pet1)).thenReturn(favoritePets);

        //Invoke
        ResponseEntity<FavoritePets> response = favoritePetsController.addPetToList("helper", pet1);

        //Analyze
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        assertEquals(favoritePets,response.getBody());
    }

    @Test
    public void addPetToBasketInvalid() throws IOException{
        //Setup
        Pet pet = new Pet(0, "bucky", "good dog", true);
        FavoritePets favoritePets = null;
        when(mockFavoritePetsDAO.addPetToFavoritePets(null, pet)).thenReturn(favoritePets);

        //Invoke
        ResponseEntity<FavoritePets> response = favoritePetsController.addPetToList(null, pet);

        //Analyze
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
        assertEquals(null,response.getBody());
    }

    @Test
    public void addPetToBasketInternalServerError() throws IOException {
        //Setup
        Pet pet = new Pet(0, "bucky", "good dog", true);
        Pet pet1 = new Pet(1, "buckster", "goodish dog", true);
        HashMap<Integer,Pet> listOfPets = new HashMap<Integer,Pet>();
        listOfPets.put(pet.getId(), pet);
        listOfPets.put(pet1.getId(), pet1);
        FavoritePets favoritePets = new FavoritePets("user", listOfPets);
        when(mockFavoritePetsDAO.addPetToFavoritePets("user",pet1)).thenThrow(new RuntimeException("Internal Server Error"));

        //Invoke
        ResponseEntity<FavoritePets> responseEntity = favoritePetsController.addPetToList("user",pet1);

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void removePetFromBasket() throws IOException{
        //Setup
       Pet pet = new Pet(0, "bucky", "good dog", true);
        HashMap<Integer,Pet> listOfPets = new HashMap<Integer,Pet>();
        listOfPets.put(pet.getId(), pet);
        FavoritePets favoritePets = new FavoritePets("helper", listOfPets);
        when(mockFavoritePetsDAO.removePetFromFavoritePets("helper", 0)).thenReturn(true);

        //Invoke
        ResponseEntity<Map<Integer,Pet>> response = favoritePetsController.removePetFromBasket("helper", 0);

        //Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(new HashMap<Integer,Pet>(),response.getBody());
    }

    @Test
    public void removePetFromBasketInvalid() throws IOException{
        //Setup
        Pet pet = new Pet(0, "bucky", "good dog", true);
        Pet pet1 = new Pet(1, "buckster", "goodish dog", true);
        HashMap<Integer,Pet> listOfPets = new HashMap<Integer,Pet>();
        listOfPets.put(pet.getId(), pet);
        listOfPets.put(pet1.getId(), pet1);
        FavoritePets favoritePet1 = new FavoritePets("helper", listOfPets);
        when(mockFavoritePetsDAO.removePetFromFavoritePets("helper", 2)).thenReturn(false);

        //Invoke
       ResponseEntity<Map<Integer,Pet>> response = favoritePetsController.removePetFromBasket("helper", 2);

        //Analyze
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    @Test
    public void removePetFromBasketEmpty() throws IOException{
        //Setup
        Map<Integer,Pet> mapOfNeeds = new HashMap<>();
        FavoritePets favoritePets = new FavoritePets("helper", mapOfNeeds);
        when(mockFavoritePetsDAO.removePetFromFavoritePets("helper", 3)).thenReturn(false);
        //Invoke
        ResponseEntity<Map<Integer,Pet>> response = favoritePetsController.removePetFromBasket("helper", 2);

        //Analyze
        Map<Integer,Pet> expected = new HashMap<>();
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void removePetFromBasketInternalServerError() throws IOException {
        //Setup
        Pet pet = new Pet(0, "bucky", "good dog", true);
        Pet pet1 = new Pet(1, "buckster", "goodish dog", false);
        HashMap<Integer,Pet> listOfPets = new HashMap<Integer,Pet>();
        listOfPets.put(pet.getId(), pet);
        listOfPets.put(pet1.getId(), pet1);
        FavoritePets favoritePets = new FavoritePets("user", listOfPets);
        when(mockFavoritePetsDAO.removePetFromFavoritePets("user",1)).thenThrow(new RuntimeException("Internal Server Error"));

        //Invoke
       ResponseEntity<Map<Integer,Pet>> response = favoritePetsController.removePetFromBasket("user",1);

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test 
    public void getFavoritePets() throws IOException{
        //Setup
        Pet pet = new Pet(0, "bucky", "good dog", true);
        Pet pet1 = new Pet(1, "buckster", "goodish dog", false);
        HashMap<Integer,Pet> listOfPets = new HashMap<Integer,Pet>();
        listOfPets.put(pet.getId(), pet);
        listOfPets.put(pet1.getId(), pet1);
        FavoritePets favoritePets = new FavoritePets("user", listOfPets);
        when(mockFavoritePetsDAO.getFavoritePets("user")).thenReturn(favoritePets.getFavoritePets());

        //Invoke
        ResponseEntity<Map<Integer,Pet>> response = favoritePetsController.getFavoritePets("user");

        //Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(listOfPets,response.getBody());
    }

    @Test
    public void getFavoritePetsInternalServerError() throws IOException {
        //Setup
       Pet pet = new Pet(0, "bucky", "good dog", true);
        Pet pet1 = new Pet(1, "buckster", "goodish dog", false);
        HashMap<Integer,Pet> listOfPets = new HashMap<Integer,Pet>();
        listOfPets.put(pet.getId(), pet);
        listOfPets.put(pet1.getId(), pet1);
        FavoritePets favoritePets = new FavoritePets("user", listOfPets);
        when(mockFavoritePetsDAO.getFavoritePets("user")).thenThrow(new RuntimeException("Internal Server Error"));

        //Invoke
        ResponseEntity<Map<Integer,Pet>> responseEntity = favoritePetsController.getFavoritePets("user");

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

}
