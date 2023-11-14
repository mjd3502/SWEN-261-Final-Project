package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.List;

import com.ufund.api.ufundapi.model.Pet;

/**
 * Defines the interface for pet persistance
 * 
 * @author Garrett Geyer, Cheyenne Zhang, Carla Lopez, Rachel Atkins, Michael Dibiase
 */
public interface PetDAO {
    /**
     * Creates a pet
     * 
     * @param pet pet object to create and save
     * 
     * @return new pet if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Pet createPet(Pet pet) throws IOException;

    /**
     * GETS a single pet with the given id
     * 
     * @param id The id of the pet to retrieve
     * 
     * @return a single pet object with the matching id
     * 
     * null if no pet with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Pet getSinglePetById(int id) throws IOException;


    /**
     * GETS a pet with the given name
     * 
     * @param name The name of the pet to be retrieved
     * 
     * @return a pet with the matching name
     * 
     * null if no pet with a matching name is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Pet[] getPetbyName(String name) throws IOException;

    boolean deletePetbyName(String name) throws IOException;

    /**
     * Deletes a pet with the given id
     * 
     * @param id The id of the pet
     * 
     * @return true if the pet was deleted
     * 
     * false if the pet with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deletePet(int Id) throws IOException;

    /**
     * Updates a pet
     * 
     * @param pet pet object to update and save
     * 
     * @return updated pet if successful, null if
     * pet could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Pet updatePet(Pet pet) throws IOException;

    /**
     * Retrieves list of all pets
     * 
     * @return A list of pet objects (can be empty)
     * 
     * @throws IOException if an issue with underlying storage
     */
    List<Pet> getAllPets() throws IOException;

    


}
