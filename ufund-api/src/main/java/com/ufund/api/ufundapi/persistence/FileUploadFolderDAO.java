package com.ufund.api.ufundapi.persistence;

import java.io.File;
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

    public FileUploadFolderDAO(){
    }

    @Override
    public boolean createImage(File file) throws IOException {
        return false;
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
