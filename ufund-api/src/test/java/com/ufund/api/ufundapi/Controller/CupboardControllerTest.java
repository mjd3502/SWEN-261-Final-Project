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

import com.ufund.api.ufundapi.controller.CupboardController;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.CupboardDAO;

@Tag("Controller-tier")
public class CupboardControllerTest {
    private CupboardController cupboardController;
    private CupboardDAO mockcupboardDAO;

    /**
     * Before each test, create a new cupboardController object and inject
     * a mock need DAO
     */
    @BeforeEach
    public void setupcupboardController() {
       mockcupboardDAO = mock(CupboardDAO.class);
        cupboardController = new CupboardController(mockcupboardDAO);
    }


    /**
     * Method for 
     * @throws IOException
     */
    @Test 
    public void getASingleNeedFoundTest() throws IOException{
        Need need = new Need(0, "Donate food", 10, "donate dog fod", 0, "goods");

        when(mockcupboardDAO.getSingleNeed(need.getId())).thenReturn(need);

        // Invoke
        ResponseEntity<Need> response = cupboardController.getSingleNeed(need);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(need,response.getBody());
    }


    @Test 
    public void getSingleNeedNotFound() throws IOException{
        //  int needId = 99;
        Need need = new Need(0, "Donate food", 10, "donate dog fod", 0, "goods");
          when(mockcupboardDAO.getSingleNeed(need.getId())).thenReturn(null);
 
         // Invoke
         ResponseEntity<Need> response = cupboardController.getSingleNeed(need);
 
         // Analyze
         assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }


    @Test
    public void createNeed() throws IOException{
        Need need = new Need(0, "Donate food", 10, "donate dog fod", 0, "goods");
        // when createneed is called, return true simulating successful
        // creation and save
        when(mockcupboardDAO.createNeed(need)).thenReturn(need);

        // Invoke
        ResponseEntity<Need> response = cupboardController.createNeed(need);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(need,response.getBody());
    }


    @Test
    public void createNeedFailed() throws IOException{
        
        Need need = new Need(0, "Donate food", 10, "donate dog fod", 0, "goods");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

     @Test
    public void createNeedEmptyName() throws IOException{
        
        Need need = new Need(0, "", 10, "donate dog fod", 0, "goods");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }


     @Test
    public void createNeedEmptyType() throws IOException{
        
        Need need = new Need(0, "Carla", 10, "donate dog fod", 0, "");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

     @Test
    public void createNeedQuantytZero() throws IOException{
        
        Need need = new Need(0, "Volunteer to pet a dog", 0, "donate dog fod", 10, "volunteerring ");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }


     @Test
    public void createNeedCosttZero() throws IOException{
        
        Need need = new Need(0, "Volunteer to pet a dog", 10, "donate dog fod", 0, "volunteerring ");
        when(mockcupboardDAO.createNeed(need)).thenReturn(null);

        ResponseEntity<Need> response = cupboardController.createNeed(need);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }



     /**
     * Method for getting entire cupboard
     * @throws IOException
     */
    @Test
    public void getEntireCupboardTest() throws IOException{

        Need need = new Need(0, "Donate food", 10, "donate dog fod", 0, "goods");
        Need need1 = new Need(0, "Donate food", 10, "donate dog fod", 0, "goods");
        Need need2 = new Need(0, "Donate food", 10, "donate dog fod", 0, "goods");

        List<Need> listOfNeeds = List.of(need,need1,need2);

        when(mockcupboardDAO.getEntireCupboard()).thenReturn(listOfNeeds);

        ResponseEntity<List<Need>> responseEntity = cupboardController.getEntireCupboard();

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

    }


     /**
     * Method for getting a need by name 
     * @throws IOException
     */
    @Test
    public void getNeedbyName() throws IOException{

        Need need = new Need(0, "Carla", 0, "new need ", 0, "volunteer opportunity");
        Need[] needArray = new Need[1];
        needArray[0] = need;


        when(mockcupboardDAO.getNeedbyName(need.getName())).thenReturn(needArray);

        ResponseEntity<Need[]> responseEntity = cupboardController.searchCupboard(need.getName());

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

    }


     /**
     * Method for deleting a need
     * @throws IOException
     */
    @Test
    public void deleteNeed() throws IOException{


        int needId = 99;
        when(mockcupboardDAO.deleteNeed(needId)).thenReturn(true);
        
        ResponseEntity<Need> responseEntity = cupboardController.deleteNeed(needId);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }


    @Test
    public void deleteNeedNotFound() throws IOException{


        int needId = 20;
        when(mockcupboardDAO.deleteNeed(needId)).thenReturn(false);
        
        ResponseEntity<Need> responseEntity = cupboardController.deleteNeed(needId);

        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }


     /**
     * Method for updating a need 
     * @throws IOException
     */
    @Test
    public void updateNeed() throws IOException{

        Need need = new Need(0, "Messi", 10, "donate food", 20, "goods");
        when(mockcupboardDAO.updateNeed(need)).thenReturn(need);
        ResponseEntity<Need> responseEntity = cupboardController.updateNeed(need);
        need.setName("Pedri");

        responseEntity = cupboardController.updateNeed(need);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void updateNeedFailed() throws IOException{

        Need need = new Need(0, "Messi", 10, "donate food", 20, "goods");
        when(mockcupboardDAO.updateNeed(need)).thenReturn(null);
        ResponseEntity<Need> responseEntity = cupboardController.updateNeed(need);

        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }


    
    
   
}
