package com.ufund.api.ufundapi.Controller;

import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import com.ufund.api.ufundapi.controller.FileUploadController;
import com.ufund.api.ufundapi.controller.UserController;
import com.ufund.api.ufundapi.persistence.FileUploadDAO;
import com.ufund.api.ufundapi.persistence.UserDAO;

/**
 * Tests for the File Upload Controller Class
 * 
 * @author Cheyenne Xinyin Zhang @xz3322
 */
public class FileUploadControllerTest {
    private FileUploadController fileUploadController;
    private FileUploadDAO mockFileUploadDAO;

    @BeforeEach
    public void setupFileUploadController() {
        mockFileUploadDAO = mock(FileUploadDAO.class);
        fileUploadController = new FileUploadController(mockFileUploadDAO);
    }

    @Test
    public void addPetFile() throws IOException{
    
    }

    @Test
    public void addPetFileInternalServerError() throws IOException{

    }

    @Test
    public void addNeedFile() throws IOException{

    }

    @Test
    public void addNeedFileInternalServerError() throws IOException{

    }
}


