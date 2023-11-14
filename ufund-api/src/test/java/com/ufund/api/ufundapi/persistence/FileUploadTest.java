package com.ufund.api.ufundapi.persistence;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.apache.tomcat.jni.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

@Tag("Persistence-tier")
public class FileUploadTest {
    private FileUploadDAO filedao;
    //private InputStream img = Files.newInputStream("../../../../../../../../../ufund-api/data/images/Dog.jpg", null);

    @Test
    public void faulty_need(){
        try {     
            filedao.createNeedImage(null, "null");
            
            assertTrue(false);
        } catch(NullPointerException n){
            
            assertTrue(true);
        }catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void faulty_pet(){
        try {     
            filedao.createNeedImage(null, "null");
            
            assertTrue(false);
        } catch(NullPointerException n){
            
            assertTrue(true);
        }catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void bad_delete(){
        try {     
            filedao.deleteImage(null, "null");
            
            assertTrue(false);
        } catch(NullPointerException n){
            
            assertTrue(true);
        }catch (IOException e) {
            assertTrue(true);
        }
    }

}
