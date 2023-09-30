package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Need;

public interface CupboardDAO {


    Need createNeed() throws IOException;

    Need getSingleNeed() throws IOException;

    Need getNeedbyName(String name) throws IOException;

    boolean deleteNeed() throws IOException;

    Need updateNeed(Need need) throws IOException;

    Need[] getEntireCupboard() throws IOException;



}
