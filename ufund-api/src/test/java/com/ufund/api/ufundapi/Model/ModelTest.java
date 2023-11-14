package com.ufund.api.ufundapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    /**
     * Tests: contructor 
     * Uses multiple assert statements to ensure the correct value of each attribute of the need.
     */
    @Test
    public void testConstructor() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        assertEquals(1, need.getId());
        assertEquals("testNeed", need.getName());
        assertEquals(10, need.getQuantity());
        assertEquals(5, need.getSurplus());
        assertEquals("example description", need.getDescription());
        assertEquals(17, need.getCost());
        assertEquals("goods", need.getType());
    }


    /** Tests: getId() */
    @Test
    public void testGetId() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        assertEquals(1, need.getId());
    }

    /** Tests: getName() */
    @Test
    public void testGetName() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        assertEquals("testNeed", need.getName());
    }

    /** Tests: setName(String name) */
    @Test
    public void testSetName() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        need.setName("New Name");
        assertEquals("New Name", need.getName());
    }
    
    /** Tests: getQuantity() */
    @Test
    public void testGetQuantity() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        assertEquals(10, need.getQuantity());
    }

    /** Tests: setQuantity(int quantity) */
    @Test
    public void testSetQuantity() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        need.setQuantity(30);
        assertEquals(30, need.getQuantity());
    }

    /** Tests: setQuantity(int quantity) --> invalid input */
    @Test
    public void testSetQuantityInvalid() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        try {
            need.setQuantity(-5);
        } catch (IllegalArgumentException e) {
            assertEquals(10, need.getQuantity());
        }
    }

    /** Tests: getSurplus */
    @Test
    public void testGetSurplus() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        assertEquals(5, need.getSurplus());
    }

    /** Tests: setSurplus */
    @Test
    public void testSetSurplus() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        need.setSurplus(10);
        assertEquals(10, need.getSurplus());
    }

    /** Tests: setSurplus --> invalid input*/
    @Test
    public void testSetSurplusInvalid() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        need.setSurplus(-30);
        assertEquals(5, need.getSurplus());
    }

    /** Tests: getDescription */
    @Test
    public void testGetDescription() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        assertEquals("example description", need.getDescription());
    }

    /** Tests: setDescription(String description) */
    @Test
    public void testSetDescription() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        need.setDescription("test description");
        assertEquals("test description", need.getDescription());
    }

    /** Tests: getCost() */
    @Test
    public void testGetCost() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        assertEquals(17, need.getCost());
    }

    /** Tests: setCost(int cost) */
    @Test
    public void testSetCost() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        need.setCost(20);
        assertEquals(20, need.getCost());
    }

    /** Tests: setCost(int cost) --> invalid input */
    @Test
    public void testSetCostInvalid() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        try {
            need.setCost(-5);
        } catch (IllegalArgumentException e) {
            assertEquals(17, need.getCost());
        }
    }
    
    /** Tests: getType() */
    @Test
    public void testGetType() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        assertEquals("goods", need.getType());
    }

    /** Tests: setType(String type) to volunteer */
    @Test
    public void testSetTypeVolunteer() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        need.setType("volunteer");
        assertEquals("volunteer", need.getType());
    }

    /** Tests: setType(String type) to goods */
    @Test
    public void testSetTypeGoods() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        need.setType("Goods");
        assertEquals("Goods", need.getType());
    }

    /** Tests: setType (invalid input) */
    @Test
    public void testSetTypeInvalid() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        need.setType("Dog Food");
        assertEquals("goods", need.getType());
    }

    /** Tests: toString() */
    @Test
    public void testToString() {
        Need need = new Need(1, "testNeed", 10, 5, "example description", 17, "goods"); 
        assertEquals("Need [id=1,name =testNeed, quantity = 10, surplus = 5, description = example description, cost = 17, type = goods]", need.toString());
    }
}