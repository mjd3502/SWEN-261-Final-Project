package com.ufund.api.ufundapi.controller;
import java.io.File;
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


@RestController
@RequestMapping("/upload")
public class FileUploadController {
    private static final Logger LOG = Logger.getLogger(FileUploadController.class.getName());
    
    //Create API controller to handle requests for files
    public FileUploadController(){

    }

    //post requests for when a file is sent to the server to be uploaded
    @PostMapping("")
    public ResponseEntity<File> addFile(@RequestBody File file){
        LOG.info("POST /upload " + file.getName());

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
