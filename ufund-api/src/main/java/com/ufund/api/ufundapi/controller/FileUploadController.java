package com.ufund.api.ufundapi.controller;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.persistence.FileUploadDAO;


import java.util.logging.Logger;



@RestController
@RequestMapping("")
public class FileUploadController {
    private static final Logger LOG = Logger.getLogger(FileUploadController.class.getName());
    private FileUploadDAO fileDao;

    //Create API controller to handle requests for files
    public FileUploadController(FileUploadDAO fileDao){
        this.fileDao = fileDao;
    }

    //post requests for when a file is sent to the server to be uploaded
    //This one is specific for a pet being uploaded
    @PostMapping("/upload-pet")
    public ResponseEntity<File> addPetFile(
        @RequestPart(name="image", required = false) MultipartFile file,
        @RequestPart(name="name", required = false) String id){
            
       
        try{
            fileDao.createPetImage(file,id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    //post requests for when a file is sent to the server to be uploaded
    //This one is specific for an image of a need being uploaded
    @PostMapping("/upload-need")
    public ResponseEntity<File> addNeedFile(
        @RequestPart(name="image", required = false) MultipartFile file,
        @RequestPart(name="name", required = false) String id){
            
        
       
        try{
            fileDao.createNeedImage(file,id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @DeleteMapping("/imageDelete/needs/{id}")
    public ResponseEntity<Boolean> deleteImage(@PathVariable String id){
        
        try {
            fileDao.deleteImage("needs",id);
           
            
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (IOException e) {
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @DeleteMapping("/imageDelete/pets/{id}")
    public ResponseEntity<Boolean> deletePetImage(@PathVariable String id){
        
       
        try {
            fileDao.deleteImage("pets",id);
            
            
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (IOException e) {
           
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}
