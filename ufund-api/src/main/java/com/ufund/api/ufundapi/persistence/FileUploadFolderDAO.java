package com.ufund.api.ufundapi.persistence;

import java.io.BufferedInputStream;


import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;



import org.springframework.stereotype.Component;



import static java.nio.file.StandardCopyOption.*;

@Component
public class FileUploadFolderDAO implements FileUploadDAO {

    public FileUploadFolderDAO(){
        
    }

    //image passed for a pet, should be saved to the folder for pets
    //file name will be based on the ID of the associated pet
    @Override
    public boolean createPetImage(MultipartFile file, String id) throws IOException {
        try {
            InputStream input = new BufferedInputStream(file.getInputStream());

           
            
            Path folderpath = Path.of("../ufund-ui/ufund/src/assets/images/pets", id+".jpg");
            
            Files.copy(input, folderpath, REPLACE_EXISTING);
            
           
            return true;
        }
        catch (Exception e){
            
            return false;
        }
        
    }


    //Downloads image for a need to needs folder under angular assests
    //saves file with a name based on the ID of the associated need.
    @Override
    public boolean createNeedImage(MultipartFile file, String id) throws IOException {
        try {
            InputStream input = new BufferedInputStream(file.getInputStream());

            
            Path folderpath = Path.of("../ufund-ui/ufund/src/assets/images/needs", id+".jpg");
            
            Files.copy(input, folderpath, REPLACE_EXISTING);
            
            
            return true;
        }
        catch (Exception e){
            
            return false;
        }
    }

    @Override
    public boolean deleteImage(String location, String id){

        Path folderpath = Path.of("../ufund-ui/ufund/src/assets/images/"+location +"/" + (id)+".jpg");
       
        
        try {
            Files.delete(folderpath);
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
