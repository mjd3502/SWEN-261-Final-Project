package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;

/*Interface for file upload persistence layer
 * files will be sent here to be be stored where they
 * need to be, in this case the files should be images
 */
public interface FileUploadDAO {
    /*
     * A passed image is downloaded to our repo
     * @param file object to be saved
     * @return a true for success or false otherwise
     * @throws IOException if theres an error.
     */

    boolean createImage(File file) throws IOException;



}
