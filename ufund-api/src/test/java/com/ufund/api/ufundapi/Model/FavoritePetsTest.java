package com.ufund.api.ufundapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.FavoritePets;
import com.ufund.api.ufundapi.model.Pet;
import java.util.Map;
import java.util.HashMap;

@Tag("Model-tier")
public class FavoritePetsTest {
    private FavoritePets favoritePets;
    
    @BeforeEach
    public void setUp(){
        Map<Integer, Pet> map = new HashMap<Integer, Pet>();
        favoritePets = new FavoritePets("carlitaxmessi",map);
    }

    @Test
    public void testConstructor(){
        assertEquals("carlitaxmessi",favoritePets.getUsername());
        assertEquals(new HashMap<>(),favoritePets.getFavoritePets());

    }


    @Test
    public void getUsername(){

        assertEquals("carlitaxmessi",favoritePets.getUsername());
    }

    @Test
    public void getFavoritePets(){
        assertEquals(new HashMap<>(),favoritePets.getFavoritePets());
    }


    @Test
    public void setFavoritePets(){
        Pet pet = new Pet(0, "bucky", "a dog", true);
        favoritePets.setFavoritePets(pet);
        assertEquals(favoritePets.getFavoritePets().get(pet.getId()),pet);
    }


    @Test
    public void favoritePetstoString(){
        String stirng = "FavoritePets [username=carlitaxmessi, favoritePets=No favorite pets ]  ";
        assertEquals(stirng,favoritePets.toString());
    }

    @Test 
    public void FavoritePetswithItems(){
         Pet pet = new Pet(0, "bucky", "a dog", true);
        favoritePets.setFavoritePets(pet);
        String string ="FavoritePets [username=carlitaxmessi, favoritePets=Pet [id=0,name =bucky, description = a dog, available = true] ]  ";
         assertEquals(string,favoritePets.toString());
    }

}
