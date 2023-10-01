package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.List;

import com.ufund.api.ufundapi.model.Need;

public interface CupboardDAO {


    Need createNeed(Need need) throws IOException;

    Need getSingleNeed() throws IOException;

    Need getNeedbyName(String name) throws IOException;

    boolean deleteNeed(int Id) throws IOException;

    Need updateNeed(Need need) throws IOException;

    List<Need> getEntireCupboard() throws IOException;



}
