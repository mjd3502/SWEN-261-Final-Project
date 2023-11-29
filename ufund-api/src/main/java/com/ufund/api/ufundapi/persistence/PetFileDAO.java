package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Pet;

@Component
public class PetFileDAO implements PetDAO{

    private static final Logger LOG = Logger.getLogger(PetFileDAO.class.getName());

    Map<Integer,Pet> petList;   
    private ObjectMapper objectMapper;  
    private static int nextId;  
    private String filename;   

    /**
     * Creates a Pet File Data Access Object
     *
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     *
     * @throws IOException when file cannot be accessed or read from
     */
    public PetFileDAO(@Value("${pet.file}")String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generates the next id for a new pet
     *
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of pets from the tree map
     *
     * @return  The array of pets, may be empty
     */
    private Pet[] getPetArray() {
        return getPetArray(null);
    }

    /**
     * Generates an array of pets  from the tree map for any
     * pet that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the pets
     * in the tree map
     *
     * @return  The array of pets, may be empty
     */
    private Pet[] getPetArray(String containsText) { // if containsText == null, no filter
        ArrayList<Pet> petArrayList = new ArrayList<>();

        for (Pet pet : petList.values()) {
            if (containsText == null || pet.getName().contains(containsText)) {
                petArrayList.add(pet);
            }
        }

        Pet[] petArray = new Pet[petArrayList.size()];
        petArrayList.toArray(petArray);
        return petArray;
    }

    /**
     * Saves the pets from the map into the file as an array of JSON objects
     *
     * @return true if the pets were written successfully
     *
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Pet[] petArray = getPetArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),petArray);
        return true;
    }

    /**
     * Loads pets from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     *
     * @return true if the file was read successfully
     *
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        petList = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of pets
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Pet[] petArray = objectMapper.readValue(new File(filename),Pet[].class);

        // Add each pet to the tree map and keep track of the greatest id
        for (Pet pet : petArray) {
            petList.put(pet.getId(),pet);
            if (pet.getId() > nextId)
                nextId = pet.getId();
        }
        
        ++nextId;
        return true;

    }



    /*
     * CRUD METHODS for U-Fund Website:
     */

    /**
    ** {@inheritDoc}
     */

    @Override
    public Pet createPet(Pet pet) throws IOException {
        synchronized(petList){
            Pet newPet = new Pet(nextId(), pet.getName(), pet.getDescription(), pet.getAvailable());
            petList.put(newPet.getId(),newPet);
            save(); // may throw an IOException
            return newPet;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Pet getSinglePetById(int id) throws IOException {
        synchronized(petList) {
            if (petList.containsKey(id))
                return petList.get(id);
            else
                return null;
        }
    }



    /**
    ** {@inheritDoc}
     */
    @Override
    public Pet[] getPetbyName(String name) throws IOException {
        synchronized(petList) {
            return getPetArray(name);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deletePet(int Id) throws IOException {
        if(petList.containsKey(Id)){
            petList.remove(Id);
            return save();
        }else {
            return false;
        }
    }

     /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deletePetbyName(String name) throws IOException {
        for(Pet pet: petList.values()){
            if(pet.getName().equals(name)){
                petList.remove(pet.getId());
                return save();
            }
        }
        return false;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Pet updatePet(Pet pet) throws IOException {
        synchronized(petList) {
            if (petList.containsKey(pet.getId()) == false)
                return null;  // pet does not exist
            petList.put(pet.getId(),pet);
            save(); // may throw an IOException
            return pet;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public List<Pet> getAllPets() throws IOException {
       synchronized(petList){
        return  Arrays.asList(getPetArray());
       }
    }


}
