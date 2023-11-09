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

@Component
public class FileUploadFolderDAO implements FileUploadDAO {
    private static final Logger LOG = Logger.getLogger(FileUploadDAO.class.getName());
    private Path folderPath;

    public FileUploadFolderDAO(){
        folderPath = (Path)Paths.get("./data/images");
    }

    @Override
    public boolean createImage(MultipartFile file) throws IOException {
        String userDirectory = Paths.get("").toAbsolutePath().toString();

        System.out.println(userDirectory);
        System.out.println(folderPath.toString());
        
        try {
            // Files.copy(file.getInputStream(), folderPath);
            // File x = new File("C:/Users/MJD09/Downloads/Dog.jpg");
            // InputStream i = new FileInputStream(x);

            InputStream input = new BufferedInputStream(file.getInputStream());

            Path folderpath = Path.of("./data/images", file.getOriginalFilename());
            
            Files.copy(input, folderpath);
            
            System.out.println("YIPPEEEEE");
            return true;
        }
        catch (Exception e){
            System.out.println("Error :(");
            return false;
        }
        
    }

    // private boolean createImage(File file) throws IOException {
    //     try(InputStream input = new FileInputStream(file)){
    //         Files.copy(input, "ufund-api/data/images");

    //         return true;
    //     }catch (Exception e) {
    //         return false;
    //     }

    //     return false;
    // };
}
