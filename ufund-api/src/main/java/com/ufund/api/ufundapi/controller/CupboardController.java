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
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.CupboardDAO;

/**
 * Handles REST API requests for the Cupboard
 * 
 * @author Cheyenne Zhang, Carla Lopez, Rachel Atkins, Garrett Geyer, Michael Dibiase
 */
@RestController
@RequestMapping("/cupboard")
public class CupboardController {
    private static final Logger LOG = Logger.getLogger(CupboardController.class.getName());
    private CupboardDAO cupboardDao;

    /**
     * Create a REST API controller object to handle requests
     * 
     * @param cupboardDAO the Cupboard Data Access Object to perform CRUD operations
     * 
     * This dependency is added using the Spring Framework
     */
    public CupboardController(CupboardDAO cupboardDAO){
        this.cupboardDao = cupboardDAO;
    }

    /**
     * Responds to the GET request for a single need 
     * 
     * @param need the need used to access the id in order to get a single need
     * 
     * @return ResponseEntity with need object and HTTP status of CREATED if found
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/singleNeed")
    public ResponseEntity<Need> getSingleNeed(@RequestBody Need need) {
        int id = need.getId();
        LOG.info("GET /cupboard/singleNeed");
        try {
            Need foundNeed = cupboardDao.getSingleNeed(id);
            if(foundNeed != null){
                return new ResponseEntity<Need>(foundNeed,HttpStatus.OK);
            }else{
                 return new ResponseEntity<Need>(foundNeed,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
}

    /**
     * Creates a need with a need object
     * 
     * @param need the need to be created
     * 
     * @return ResponseEntity with created need object and HTTP status of CREATED
     * ResponseEntity with HTTP status of CONFLICT if need object already exists
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Need> createNeed(@RequestBody Need need) {
        LOG.info("POST /cupboard " + need);
        try {
            
            Need need1 = cupboardDao.createNeed(need);
            if (need1 != null)
                return new ResponseEntity<Need>(need1,HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Deletes a need with the provided id
     * 
     * @param id The id of the need to delete
     * 
     * @return ResponseEntity HTTP status of OK if deleted
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Need> deleteNeed(@PathVariable int id) {
        LOG.info("DELETE /cupboard/" + id);

        try {
            boolean didit = cupboardDao.deleteNeed(id);
            if (didit){
                
                return new ResponseEntity<Need>(HttpStatus.OK); 
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
     * Responds to the GET request for all needs in the entire Cupboard
     * 
     * @return ResponseEntity with array of need objects (can be empty) and
     * HTTP status of OK
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping(" ")
    public ResponseEntity<List<Need>> getEntireCupboard(){
        LOG.info("GET /cupbaord/" );
       List<Need> responseEntity = new ArrayList<>();

        try {
            responseEntity = cupboardDao.getEntireCupboard();
            if(!responseEntity.isEmpty()){
                return new ResponseEntity<List<Need>>(responseEntity,HttpStatus.OK);
            }else{
                return new ResponseEntity<List<Need>>(responseEntity,HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Updates the need with the provided need object, if it exists
     * 
     * @param need The need to be updated
     * 
     * @return ResponseEntity with updated need object and HTTP status of OK if updated
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Need> updateNeed(@RequestBody Need need) {
        LOG.info("PUT /need " + need);

        try {
            Need checkNeed = cupboardDao.updateNeed(need);
            
            if (checkNeed != null){
                return new ResponseEntity<Need>(checkNeed,HttpStatus.OK);
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
     * Responds to the GET request for all needs whose name contains
     * the text in the name parameter
     * 
     * @param name The name parameter which contains the text used to find the needs
     * 
     * @return ResponseEntity with array of need objects (can be empty) and
     * HTTP status of OK
     * ResponseEntity with HTTP status of NOT_FOUND otherwise
     */
    @GetMapping("/needs")
    public ResponseEntity<Need[]> searchCupboard(@RequestParam String name){
        LOG.info("GET /cupboard/?name="+name);

        Need[] need;

        try {
            need = cupboardDao.getNeedbyName(name);
            if(need.length == 0){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<Need[]>(need, HttpStatus.OK);
            }
            
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
}
