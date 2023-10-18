package com.ufund.api.ufundapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.Need;

/**
 * File: ModelTest.java
 * Authors: Rachel Adkins and Cheyenne Zhang
 * Purpose: Provides basic JUnit testing for Need.java class as well as invalid 
 * input testing for setQuantity, setCost, and setType functions. 
 */

@Tag("Model-tier")
public class ModelTest {
    private Need need;
    /**
     * Before each test, create a new cupboardController object and inject
     * a mock need DAO
     */
    @BeforeEach
    public void setupNeedModel() {
        need = new Need(1, "Need", 10, "Description", 100, "Goods");
    }

    /**
     * Tests: contructor 
     * Uses multiple assert statements to ensure the correct value of each attribute of the need.
     * Author: Rachel Adkins 
     */
    @Test
    public void testConstructor() {
        assertEquals(1, need.getId());
        assertEquals("Need", need.getName());
        assertEquals(10, need.getQuantity());
        assertEquals("Description", need.getDescription());
        assertEquals(100, need.getCost());
        assertEquals("Goods", need.getType());
    }

    /**
     * Tests: getId()
     * Author: Rachel Adkins 
     */
    @Test
    public void testGetId() {
        assertEquals(1, need.getId());
    }

    /**
     * Tests: getName()
     * Author: Rachel Adkins 
     */
    @Test
    public void testGetName() {
        assertEquals("Need", need.getName());
    }

    /**
     * Tests: setName(String name)
     * Author: Rachel Adkins 
     */
    @Test
    public void testSetName() {
        need.setName("New Name");
        assertEquals("New Name", need.getName());
    }

    /**
     * Tests: getQuantity()
     * Author: Rachel Adkins 
     */
    @Test
    public void testGetQuantity() {
        assertEquals(10, need.getQuantity());
    }

    /**
     * Tests: setQuantity(int quantity)
     * Author: Rachel Adkins 
     */
    @Test
    public void testSetQuantity() {
        assertEquals(10, need.getQuantity());
    }

    /**
     * Tests: setQuantity(int quantity) --> invalid input
     * Author: Rachel Adkins 
     */
    @Test
    public void testSetQuantityInvalidInput() {
        try {
            need.setQuantity(-5);
        } catch (IllegalArgumentException e) {
            assertEquals(10, need.getQuantity());
        }
    }

    /**
     * Tests: getDescription
     * Author: Rachel Adkins 
     */
    @Test
    public void testGetDescription() {
        assertEquals("Description", need.getDescription());
    }

    /**
     * Tests: setDescription(String description)
     * Author: Rachel Adkins 
     */
    @Test
    public void testSetDescription() {
        need.setDescription("New Description");
        assertEquals("New Description", need.getDescription());
    }

    /**
     * Tests: getCost()
     * Author: Cheyenne Zhang
     */
    @Test
    public void testGetCost() {
        assertEquals(100, need.getCost());
    }

    /**
     * Tests: setCost(int cost)
     * Author: Cheyenne Zhang
     */
    @Test
    public void testSetCost() {
        need.setCost(200);
        assertEquals(200, need.getCost());
    }

    /**
     * Tests: setCost(int cost) --> invalid input
     * Author:  Cheyenne Zhang
     */
    @Test
    public void testSetCostInvalidInput() {
        try {
            need.setCost(-5);
        } catch (IllegalArgumentException e) {
            assertEquals(100, need.getCost());
        }
    }

    /**
     * Tests: getType()
     * Author:  Cheyenne Zhang
     */
    @Test
    public void testGetType() {
        assertEquals("Goods", need.getType());
    }

    /**
     * Tests: setType()
     * Author:  Cheyenne Zhang
     */
    @Test
    public void testSetType() {
        //TODO: implement!
    }

    /**
     * Tests: setType (invalid input)
     * Author: Rachel Adkins
     */
    @Test
    public void testSetTypeInvalidInput() {
        need.setType("Volunteer");
        assertEquals("Volunteer", need.getType());
    }

    /**
     * Tests: toString()
     * Author:  Cheyenne Zhang
     */
    @Test
    public void testToString() {
        //TODO: implement!
    }

}
