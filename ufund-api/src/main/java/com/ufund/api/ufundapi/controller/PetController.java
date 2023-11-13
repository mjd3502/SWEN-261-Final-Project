package com.ufund.api.ufundapi.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PutMapping;
import com.ufund.api.ufundapi.model.Pet;
import com.ufund.api.ufundapi.persistence.PetDAO;

/**
 * Handles REST API requests for the Pets
 * 
 * @author Garrett Geyer, Cheyenne Zhang, Carla Lopez, Rachel Atkins,  Michael Dibiase
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private static final Logger LOG = Logger.getLogger(PetController.class.getName());
    private PetDAO petDao;

    /**
     * Create a REST API controller object to handle requests
     * 
     * @param petDAO the Pet Data Access Object to perform CRUD operations
     * 
     * This dependency is added using the Spring Framework
     */
    public PetController(PetDAO petDAO){
        this.petDao = petDAO;
    }

    /**
     * Responds to the GET request for a single pet when given a pet object as a request body
     * 
     * @param pet The pet used to access the id in order to get a single pet
     * 
     * @return ResponseEntity with pet object and HTTP status of OK if found
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     */


@GetMapping("/{id}")
    public ResponseEntity<Pet> getSinglePetbyId(@PathVariable int id) {
        LOG.info("GET /pet/{id}");
        try {
            Pet foundPet = petDao.getSinglePetById(id);
            if(foundPet != null){
                return new ResponseEntity<Pet>(foundPet,HttpStatus.OK);
            }else{
                 return new ResponseEntity<Pet>(foundPet,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
}

    /**
     * Creates a pet with a pet object
     * 
     * @param pet the pet to be created
     * 
     * @return ResponseEntity with created pet object and HTTP status of CREATED
     * ResponseEntity with HTTP status of CONFLICT if pet object already exists
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     *  ResponseEntity with HTTP status of BAD_REQUEST if fields are null or zero( for attributes with an integer value)
     */



     /**
         * Private methods to validate the values in the pet object 
    */
    private boolean validateStringFields(String  value){

        return value == null || value.isEmpty();

    }

    private boolean validateIntegerFields(int  value){
        return value <= 0;
    }

    @PostMapping("")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        LOG.info("POST /pet " + pet);

        /**
         * Pets will only be created if all fields have a value
         */
        if(validateStringFields(pet.getName()) || validateStringFields(pet.getDescription()) ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

            Pet pet1 = petDao.createPet(pet);

            if (pet1 != null)
                return new ResponseEntity<Pet>(pet1,HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    /**
     * Deletes a pet with the provided id
     * 
     * @param id The id of the pet to delete
     * 
     * @return ResponseEntity HTTP status of OK if deleted
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable int id) {
        LOG.info("DELETE /pet/" + id);

        try {
            boolean didit = petDao.deletePet(id);
            if (didit){
                
                return new ResponseEntity<Pet>(HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Deletes a pet with the provided name
     * 
     * @param name The name of the pet to delete
     * 
     * @return ResponseEntity HTTP status of OK if deleted
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/petName/{name}")
    public ResponseEntity<Pet> deletePetbyName(@PathVariable String name) {
        LOG.info("DELETE /pet/" + name);

        try {
            boolean didit = petDao.deletePetbyName(name);
            if (didit){
                return new ResponseEntity<Pet>(HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Responds to the GET request for all pets
     * 
     * @return ResponseEntity with array of pet objects (can be empty) and
     * HTTP status of OK
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping(" ")
    public ResponseEntity<List<Pet>> getPetList(){
        LOG.info("GET /pet/" );
       List<Pet> responseEntity = new ArrayList<>();

        try {
            responseEntity = petDao.getAllPets();
            if(!responseEntity.isEmpty()){
                return new ResponseEntity<List<Pet>>(responseEntity,HttpStatus.OK);
            }else{
                return new ResponseEntity<List<Pet>>(responseEntity,HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Updates the pet with the provided pet object, if it exists
     * 
     * @param pet The pet to be updated
     * 
     * @return ResponseEntity with updated pet object and HTTP status of OK if updated
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {
        LOG.info("PUT /pet " + pet);

        try {
            Pet checkPet = petDao.updatePet(pet);
            if (checkPet != null){
                return new ResponseEntity<Pet>(checkPet,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all pets whose name contains
     * the text in the name parameter
     * 
     * @param name The name parameter which contains the text used to find the pets
     * 
     * @return ResponseEntity with array of pet objects (can be empty) and
     * HTTP status of OK
     * ResponseEntity with HTTP status of NOT_FOUND otherwise
     */
    @GetMapping("/pets")
    public ResponseEntity<Pet[]> searchPet(@RequestParam String name){
        LOG.info("GET /pet/?name="+name);

        Pet[] pet;

        try {
            pet = petDao.getPetbyName(name);
            if(pet.length == 0){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<Pet[]>(pet, HttpStatus.OK);
            }
            
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}
