package com.ufund.api.ufundapi.persistence;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.nio.file.StandardCopyOption.*;

@Component
public class FileUploadFolderDAO implements FileUploadDAO {
    private static final Logger LOG = Logger.getLogger(FileUploadDAO.class.getName());
    private Path folderPath;

    public FileUploadFolderDAO(){
        
    }

    //image passed for a pet, should be saved to the folder for pets
    //file name will be based on the ID of the associated pet
    @Override
    public boolean createPetImage(MultipartFile file, String id) throws IOException {
        try {
            InputStream input = new BufferedInputStream(file.getInputStream());

            //Path folderpath = Path.of("./data/images", file.getOriginalFilename());
            
            Path folderpath = Path.of("../ufund-ui/ufund/src/assets/images/pets", id+".jpg");
            
            Files.copy(input, folderpath, REPLACE_EXISTING);
            
            System.out.println("YIPPEEEEE");
            return true;
        }
        catch (Exception e){
            System.out.println("Error :(");
            return false;
        }
        
    }


    //Downloads image for a need to needs folder under angular assests
    //saves file with a name based on the ID of the associated need.
    @Override
    public boolean createNeedImage(MultipartFile file, String id) throws IOException {
        try {
            InputStream input = new BufferedInputStream(file.getInputStream());

            //Path folderpath = Path.of("./data/images", file.getOriginalFilename());
            //file.getOriginalFilename()
            Path folderpath = Path.of("../ufund-ui/ufund/src/assets/images/needs", id+".jpg");
            
            Files.copy(input, folderpath, REPLACE_EXISTING);
            
            System.out.println("YIPPEEEEE");
            return true;
        }
        catch (Exception e){
            System.out.println("Error :(");
            return false;
        }
    }

    @Override
    public boolean deleteImage(String location, String id){

        Path folderpath = Path.of("../ufund-ui/ufund/src/assets/images/"+location +"/" + (id)+".jpg");
        System.out.println(folderpath);
        
        try {
            Files.delete(folderpath);
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
