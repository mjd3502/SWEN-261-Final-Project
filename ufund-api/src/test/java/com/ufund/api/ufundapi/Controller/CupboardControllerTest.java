package com.ufund.api.ufundapi.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.controller.CupboardController;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.CupboardDAO;
import com.ufund.api.ufundapi.persistence.RemoveNeedsDAO;

/*
 * Author: Cheyenne Zhang @xz3322 Carla Lopez @csl5623 Rachel Adkins @raa5749
 */

@Tag("Controller-tier")
public class CupboardControllerTest {
    private CupboardController cupboardController;
    private CupboardDAO mockcupboardDAO;
    private RemoveNeedsDAO mockRemoveNeedsDAO;


    /**
     * Before each test, create a new cupboardController object and inject
     * a mock need DAO
     */
    @BeforeEach
    public void setupcupboardController() {
        mockcupboardDAO = mock(CupboardDAO.class);
        mockRemoveNeedsDAO = mock(RemoveNeedsDAO.class);
        cupboardController = new CupboardController(mockcupboardDAO, mockRemoveNeedsDAO);
    }

    /**
     * Tests: getSingleNeed (success case)
     * 
     * Attempts to get a single need. Checks if need exists and that resulting status code is
     * OK because need was successfully found and retrieved. 
     * @throws IOException
     */
    @Test 
    public void getASingleNeedFoundTest() throws IOException{

        Need need = new Need(0, "Donate food", 10,0, "donate dog food", 0, "goods");
        when(mockcupboardDAO.getSingleNeedById(need.getId())).thenReturn(need);

        ResponseEntity<Need> response = cupboardController.getSingleNeedbyId(need.getId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(need,response.getBody());
    }

    /**
     * Tests: getSingleNeed (fail case)
     * 
     * Attempts to get a single need. Checks if need exists and that resulting status code is
     * NOT FOUND because need does not exist.
     * @throws IOException
     */
    @Test 
    public void getSingleNeedNotFound() throws IOException{

        Need need = new Need(0, "Donate food", 10,0, "donate dog food", 0, "goods");
        when(mockcupboardDAO.getSingleNeedById(need.getId())).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.getSingleNeedbyId(need.getId());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void getSingleNeedInternalServerError() throws IOException {

        Need need = new Need(0, "Donate food", 10,0, "donate dog food", 0, "goods");
        when(mockcupboardDAO.getSingleNeedById(anyInt())).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<Need> responseEntity = cupboardController.getSingleNeedbyId(need.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: createNeed (success case)
     * 
     * Attempts to create a new need. Checks that resulting status code is CREATED because need 
     * creation was successful.
     * @throws IOException
     */
    @Test
    public void createNeed() throws IOException{

        Need need = new Need(0, "Donate food", 10,0, "donate dog food", 10, "goods");
        when(mockcupboardDAO.createNeed(need)).thenReturn(need);

    
        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(need,response.getBody());
    }

    /**
     * Tests: createNeed (fail case)
     * 
     * Attempts to create a new need. Checks if need exists and that resulting status code is
     * CONFLICT because need already exists. 
     * @throws IOException
     */
    @Test
    public void createNeedFailed() throws IOException{

        Need need = new Need(0, "Donate food", 10,0, "donate dog food", 10, "goods");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);
    
        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    /**
     *Tests: createNeed (fail case)
     * 
     * Attempts to create a new need. Checks need name and that resulting satus code is BAD
     * REQUEST because need name cannot be empty.
     * @throws IOException
     */
    @Test
    public void createNeedEmptyName() throws IOException{

        Need need = new Need(0, "", 10,0, "donate dog food", 10, "goods");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);
    

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    /**
     * Tests: createNeed (fail case)
     * 
     * Attempts to create a new need. Checks need type and that resulting status code is BAD
     * REQUEST because need type cannot be zero.
     * @throws IOException
     */
    @Test
    public void createNeedEmptyType() throws IOException{

        Need need = new Need(0, "Carla", 10,0, "donate dog food", 10, "");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    /**
     * Tests: createNeed (fail case)
     * 
     * Attempts to create a new need. Checks need quantity and that resulting status code is 
     * BAD REQUEST because quantity cannot be zero.
     * @throws IOException
     */
    @Test
    public void createNeedQuantityNegative() throws IOException{

        Need need = new Need(0, "Volunteer to pet a dog", -1,0, "donate dog food", 10, "volunteer");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    /**
     * Tests: createNeed (fail case)
     * 
     * Attempts to create a new need. Checks need quantity and that resulting status code is 
     * BAD REQUEST because quantity is zero.
     * @throws IOException
     */
    @Test
    public void createNeedQuantityZero() throws IOException{

        Need need = new Need(0, "Volunteer to pet a dog", 0,0, "donate dog food", 10, "volunteer");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    /**
     * Tests: createNeed (fail case)
     * 
     * Attempts to create a new need. Checks need cost and that resulting status code is BAD 
     * REQUEST because cost cannot be zero. 
     * @throws IOException
     */
    @Test
    public void createNeedCostZero() throws IOException{

        Need need = new Need(0, "Volunteer to pet a dog", 10,0, "donate dog food", 0, "volunteer");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    @Test
    public void createNeedBadRequest() throws IOException{

        Need need = new Need(0, "Donate food", 10,0, "donate dog food", 10, "?");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);
    
        ResponseEntity<Need> response = cupboardController.createNeed(need);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(null,response.getBody());
    }

    @Test
    public void createNeedNull() throws IOException{

        Need need = new Need(0, null, 10,0, "donate dog food", 10, "goods");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);
    
        ResponseEntity<Need> response = cupboardController.createNeed(need);

        assertEquals(null,response.getBody());
    }

    @Test
    public void createNeedCostNegative() throws IOException{

        Need need = new Need(0, "Volunteer to pet a dog", 10,0, "donate dog food", -54965, "volunteer");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    @Test
    public void createNeedValidateStringFields() throws IOException{

        Need need = new Need(0, "", 10,0, "", -54965, "goods");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    @Test
    public void createNeedInternalServerError() throws IOException{

        Need need = new Need(0, "Volunteer to pet a dog", 10,0, "donate dog food", 10, "volunteer");
        when(mockcupboardDAO.createNeed(need)).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Need> responseEntity = cupboardController.createNeed(need);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: getEntireCupboard
     * 
     * Attempts to get the entire cupboard of needs. Checks if need exists, and that resulting 
     * status code is OK because the cupboard was successfully found and retrieved. 
     * @throws IOException
     */
    @Test
    public void getEntireCupboardTest() throws IOException{

        Need need = new Need(0, "Donate food", 10,0, "donate dog fod", 0, "goods");
        Need need1 = new Need(0, "Donate toys", 1, 0,"donate dog toys", 0, "goods");
        Need need2 = new Need(0, "Adopt a dog", 15, 0,"donate dog food", 0, "volunteer");

        List<Need> listOfNeeds = List.of(need,need1,need2);
        when(mockcupboardDAO.getEntireCupboard()).thenReturn(listOfNeeds);

        ResponseEntity<List<Need>> responseEntity = cupboardController.getEntireCupboard();
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getEntireCupboardEmpty() throws IOException {
        when(mockcupboardDAO.getEntireCupboard()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Need>> responseEntity = cupboardController.getEntireCupboard();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.emptyList(), responseEntity.getBody());
    }

    @Test
    public void getEntireCupboardInternalServerError() throws IOException {

        when(mockcupboardDAO.getEntireCupboard()).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<List<Need>> responseEntity = cupboardController.getEntireCupboard();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: searchCupboard
     * 
     * Attempts to search a single need by name. Checks if need exists, and that resulting status
     * code is OK because the need was successfully found and retrieved. 
     * @throws IOException
     */
    @Test
    public void getNeedbyName() throws IOException{

        Need need = new Need(0, "Carla", 0,0, "new need ", 10, "volunteer");
        Need[] needArray = new Need[1];
        needArray[0] = need;
        when(mockcupboardDAO.getNeedbyName(need.getName())).thenReturn(needArray);

        ResponseEntity<Need[]> responseEntity = cupboardController.searchCupboard(need.getName());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getNeedByNameLengthZero()throws IOException {

        String searchName = "nonExistentNeed";
        when(mockcupboardDAO.getNeedbyName(searchName)).thenReturn(new Need[0]);

        ResponseEntity<Need[]> responseEntity = cupboardController.searchCupboard(searchName);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getNeedByNameInternalServerError() throws IOException {

        Need need = new Need(0, "Carla", 0,0, "new need ", 10, "volunteer");
        when(mockcupboardDAO.getNeedbyName(anyString())).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Need[]> responseEntity = cupboardController.searchCupboard(need.getName());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
@Test
    public void deleteNeedbyId() throws IOException{

        int needId = 99;
        when(mockcupboardDAO.deleteNeed(needId)).thenReturn(true);
        
        ResponseEntity<Need> responseEntity = cupboardController.deleteNeed(needId);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
 }

//     /**
//      * Tests: deleteNeed (fail case)
//      * 
//      * Attempts to delete a need by ID. Checks if need exists, and that resulting status code is 
//      * NOT FOUND because the need to delete does not exist. 
//      * @throws IOException
//      */


    @Test
    public void deleteNeedbyIdNotFound() throws IOException{

        Need need = new Need(0, "Carla", 0,0, "new need ", 10, "volunteer");
        when(mockcupboardDAO.deleteNeed(need.getId())).thenReturn(true);
        when(mockRemoveNeedsDAO.storeRemovedNeed(need)).thenReturn(true);
        
        ResponseEntity<Need> responseEntity = cupboardController.deleteNeed(need.getId());

        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    /**
     * Tests: deleteNeed (fail case)
     * 
     * Attempts to delete a need by ID. Checks if need exists, and that resulting status code is 
     * NOT FOUND because the need to delete does not exist. 
     * @throws IOException
     */
    // @Test
    // public void deleteNeedbyIdNotFound() throws IOException{

    //     int needId = 20;
    //     when(mockcupboardDAO.deleteNeed(needId)).thenReturn(false);
        
    //     ResponseEntity<Need> responseEntity = cupboardController.deleteNeed(needId);
    //     assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    // }

    @Test
    public void deleteNeedInternalServerError() throws IOException {
        
        int needId = 77;
        when(mockcupboardDAO.deleteNeed(needId)).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Need> responseEntity = cupboardController.deleteNeed(needId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: deleteNeedbyName (success case)
     * 
     * Attempts to delete a need by name. Checks if need exists, and that the resulting status
     * code is OK because the need was successfully found and deleted.
     * @throws IOException
     */
    @Test
    public void deleteNeedbyName() throws IOException{

        String name = "Dog volunteering";
        when(mockcupboardDAO.deleteNeedbyName(name)).thenReturn(true);
        
        ResponseEntity<Need> responseEntity = cupboardController.deleteNeedbyName(name);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    /**
     * Tests: deleteNeedbyName function (fail case)
     * 
     * Attempts to delete a need by name. Checks if need exists, and that resulting status code is 
     * NOT FOUND because the need to delete does not exist. 
     */
    @Test
    public void deleteNeedbyNameNotFound() throws IOException{

        String name = "Dog volunteering";
        when(mockcupboardDAO.deleteNeedbyName(name)).thenReturn(false);
        
        ResponseEntity<Need> responseEntity = cupboardController.deleteNeedbyName(name);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    @Test
    public void deleteNeedbyNameInternalServerError() throws IOException {

        String name = "Dog volunteering";
        when(mockcupboardDAO.deleteNeedbyName(name)).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Need> responseEntity = cupboardController.deleteNeedbyName(name);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /**
     * Tests: updateNeed function (success case)
     * 
     * Attepmts to create a new need, update it, and set a new name. Checks to make sure the need 
     * exists, the name has been changed, and that resulting status code is OK because the need has been 
     * successfully updated.
     * @throws IOException
     */
    @Test
    public void updateNeed() throws IOException{

        Need need = new Need(0, "Messi", 10,0, "donate food", 20, "goods");
        when(mockcupboardDAO.updateNeed(need)).thenReturn(need);
        ResponseEntity<Need> responseEntity = cupboardController.updateNeed(need);
        need.setName("Pedri");

        responseEntity = cupboardController.updateNeed(need);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    // @Test
    // public void updateNeedValidateIntegerField() throws IOException{

    //     Need need = new Need(0, "Messi", 10,0, "donate food", 20, "goods");
    //     when(mockcupboardDAO.updateNeed(need)).thenReturn(need);
    //     ResponseEntity<Need> responseEntity = cupboardController.updateNeed(need);
    //     need.setCost(-100);
    //     need.setQuantity(-100);

    //     responseEntity = cupboardController.updateNeed(need);
    //     assertEquals(HttpStatus.NOT_ACCEPTABLE,responseEntity.getStatusCode());
    // }

    /**
     * Tests: updateNeed function (fail case)
     * 
     * Attempts to create a new need and update it. Checks to make sure need exists and that resulting
     * status code is NOT FOUND because originial need does not exist.
     * @throws IOException
     */
    @Test
    public void updateNeedFailed() throws IOException{

        Need need = new Need(0, "Messi", 10,0, "donate food", 20, "goods");
        when(mockcupboardDAO.updateNeed(need)).thenReturn(null);
        
        ResponseEntity<Need> responseEntity = cupboardController.updateNeed(need);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    
    }

    @Test
    public void updateNeedInternalServerError() throws IOException{
        Need need = new Need(0, "Messi", 10,0, "donate food", 20, "goods");
        when(mockcupboardDAO.updateNeed(need)).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Need> responseEntity = cupboardController.updateNeed(need);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void updateNeedEmptyName() throws IOException{

        Need need = new Need(0, "Carla", 10,0, "donate dog food", 10, "goods");
        when(mockcupboardDAO.updateNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.updateNeed(need);
        need.setName("");
        response = cupboardController.updateNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    @Test
    public void updateNeedType() throws IOException{
        Need need = new Need(0, "Carla", 10,0, "donate dog food", 10, "goods");
        when(mockcupboardDAO.updateNeed(need)).thenReturn(need);

        ResponseEntity<Need> response = cupboardController.updateNeed(need);
        need.setType("volunteer");
        response = cupboardController.updateNeed(need);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void updateNeedQuantityZero() throws IOException{

         Need need = new Need(0, "Carla", 10,0, "donate dog food", 10, "goods");
        when(mockcupboardDAO.updateNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.updateNeed(need);
        need.setQuantity(0);
        response = cupboardController.updateNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    // @Test
    // public void updateNeedQuantityZero() throws IOException{

    //     Need need = new Need(0, "Volunteer to pet a dog", 0,0, "donate dog food", 10, "volunteer");
    //     when(mockcupboardDAO.updateNeed(need)).thenReturn(null);

    //     ResponseEntity<Need> response = cupboardController.updateNeed(need);
    //     assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    // }

    @Test
    public void updateNeedCostZero() throws IOException{

         Need need = new Need(0, "Carla", 10,0, "donate dog food", 10, "goods");
        when(mockcupboardDAO.updateNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.updateNeed(need);
        need.setCost(0);
        response = cupboardController.updateNeed(need);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    // @Test
    // public void updateNeedBadRequest() throws IOException{

    //     Need need = new Need(0, "Carla", 10,0, "donate dog food", 10, "goods");
    //     when(mockcupboardDAO.updateNeed(need)).thenReturn(null);

    //     ResponseEntity<Need> response = cupboardController.updateNeed(need);
    //     need.setName("");
    //     response = cupboardController.updateNeed(need);
    //     assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    // }

    // @Test
    // public void updateNeedCostNegative() throws IOException{

    //     Need need = new Need(0, "Volunteer to pet a dog", 10,0, "donate dog food", -54965, "volunteer");
    //     when(mockcupboardDAO.updateNeed(need)).thenReturn(null);

    //     ResponseEntity<Need> response = cupboardController.updateNeed(need);
    //     assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    // }

    @Test
    public void helperDonation() throws IOException{
        Need need = new Need(0, "Messi", 10,0, "donate food", 20, "goods");
        when(mockcupboardDAO.helperSurplusUpdateNeed(0, 100)).thenReturn(need);

        ResponseEntity<Need> response = cupboardController.helperDonation(0, 100);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void helperDonationNotFound() throws IOException{
        Need need = null;
        when(mockcupboardDAO.helperSurplusUpdateNeed(2, 100)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.helperDonation(2, 100);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void helperDonationInternalServerError() throws IOException{
        Need need = new Need(0, "Messi", 10,0, "donate food", 20, "goods");
        when(mockcupboardDAO.helperSurplusUpdateNeed(0, 100)).thenThrow(new IOException("Internal Server Error"));

        ResponseEntity<Need> response = cupboardController.helperDonation(0, 100);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
