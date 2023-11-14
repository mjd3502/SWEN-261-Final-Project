package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public class RemoveNeedsFileDAOTest {
    private RemoveNeedsFileDAO removeNeedsFileDAO;
    Need[] testNeeds;
    ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setUpRemoveNeedsFileDAO() throws IOException {
        testNeeds = new Need[1];
        testNeeds[0] = new Need(99,"food but better", 1, 0,"even more food", 10, "goods");

        mockObjectMapper = mock(ObjectMapper.class);
        
        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Need[].class))
                .thenReturn(testNeeds);
        removeNeedsFileDAO = new RemoveNeedsFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void storeRemovedNeed(){
        Need need = new Need(99,"food but better", 1, 0,"even more food", 10, "goods");

        try {
            boolean result = removeNeedsFileDAO.storeRemovedNeed(need);
            assertEquals(true, result);

        } catch (IOException e) {
            //test fails if an error is thrown
            assertFalse(true);
        }
    }


}
