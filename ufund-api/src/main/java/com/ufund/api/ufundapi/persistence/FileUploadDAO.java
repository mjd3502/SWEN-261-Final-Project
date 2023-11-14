package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/*Interface for file upload persistence layer
 * files will be sent here to be be stored where they
 * need to be, in this case the files should be images
 */
public interface FileUploadDAO {
    /*
     * A passed image is downloaded to our repo under pets
     * @param file object to be saved
     * @return a true for success or false otherwise
     * @throws IOException if theres an error.
     */

    boolean createPetImage(MultipartFile file, String id) throws IOException;


    /*
     * A passed image is downloaded to our repo under needs
     * @param file object to be saved
     * @return a true for success or false otherwise
     * @throws IOException if theres an error.
     */
    boolean createNeedImage(MultipartFile file, String id) throws IOException;

    
    /*
     * A passed image is downloaded to our repo under needs
     * @param file object to be saved
     * @return a true for success or false otherwise
     * @throws IOException if theres an error.
     */
    boolean deleteImage(String location, String id) throws IOException;



}
