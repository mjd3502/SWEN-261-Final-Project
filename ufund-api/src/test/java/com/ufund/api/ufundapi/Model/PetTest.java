package com.ufund.api.ufundapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.Pet;

/**
 * File: ModelTest.java
 * Authors: Garrett Geyer, Rachel Adkins and Cheyenne Zhang
 * Purpose: Provides basic JUnit testing for Pet.java class as well as invalid 
 * input testing for setQuantity, setCost, and setType functions. 
 */



@Tag("Model-tier")
public class PetTest {
    private Pet pet;
    /**
     * Before each test, create a new cupboardController object and inject
     * a mock pet DAO
     */
    @BeforeEach
    public void setupPetModel() {
        pet = new Pet(1, "Pet", "Description", true);
    }

    /**
     * Tests: contructor 
     * Uses multiple assert statements to ensure the correct value of each attribute of the pet.
     * Author: Garrett Geyer, Rachel Adkins 
     */
    @Test
    public void testConstructor() {
        assertEquals(1, pet.getId());
        assertEquals("Pet", pet.getName());
        assertEquals(true, pet.getAvailable());
        assertEquals("Description", pet.getDescription());
    }

    /**
     * Tests: getId()
     * Author: Garrett Geyer, Rachel Adkins 
     */
    @Test
    public void testGetId() {
        assertEquals(1, pet.getId());
    }

    /**
     * Tests: getName()
     * Author: Garrett Geyer, Rachel Adkins 
     */
    @Test
    public void testGetName() {
        assertEquals("Pet", pet.getName());
    }

    /**
     * Tests: setName(String name)
     * Author: Garrett Geyer, Rachel Adkins 
     */
    @Test
    public void testSetName() {
        pet.setName("New Name");
        assertEquals("New Name", pet.getName());
    }

    /**
     * Tests: getQuantity()
     * Author: Garrett Geyer, Rachel Adkins 
     */
    @Test
    public void testGetAvailable() {
        assertEquals(true, pet.getAvailable());
    }

    /**
     * Tests: setQuantity(int quantity)
     * Author: Garrett Geyer, Rachel Adkins 
     */
    @Test
    public void testSetAvailable() {
        pet.setAvailable(false);
        assertEquals(false, pet.getAvailable());
    }


    /**
     * Tests: getDescription
     * Author: Garrett Geyer, Rachel Adkins 
     */
    @Test
    public void testGetDescription() {
        assertEquals("Description", pet.getDescription());
    }

    /**
     * Tests: setDescription(String description)
     * Author: Garrett Geyer, Rachel Adkins 
     */
    @Test
    public void testSetDescription() {
        pet.setDescription("New Description");
        assertEquals("New Description", pet.getDescription());
    }

    /**
     * Tests: toString()
     * Author:  Cheyenne Zhang
     */
    @Test
    public void testToString() {
        assertEquals("Pet [id=1,name =Pet, description = Description, available = true]", pet.toString());
    }

}
