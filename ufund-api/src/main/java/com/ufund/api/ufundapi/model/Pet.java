package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a pet entity
 * 
 * @author Garrett Geyer, Cheyenne Zhang, Carla Lopez, Rachel Atkins, Michael Dibiase
 */
public class Pet {

    static final String STRING_FORMAT = "Pet [id=%d,name =%s, description = %s, available = %b]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("description") private String description;
    @JsonProperty("cost") private int cost;
    @JsonProperty("available") private boolean available;

    
    /**
     * Create a pet with the given id, name quantity, description, availability
     * @param id The id of the pet
     * @param name The name of the prt
     * @param description The description of the pet
     * @param available is the pet available to adobt
     */
    public Pet(@JsonProperty("id")int id , @JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("available") boolean available ){
        this.id=id;
        this.name = name;
        this.description = description;
        this.available = available;
    }
    
    /**
     * Retrieves the id of the pet
     * @return the id of the pet
     */ 
    public int getId(){
        return id;
    }

    /**
     * Retrieves the name of the pet
     * @return The name of the pet
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pet - necessary for Java object deserialization
     * @param name The name of the pet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the description of the pet
     * @return The description of the pet
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the pet - necessary for Java object deserialization
     * @param description The description of the pet
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns if the pet is available for adoptiob
     * @return availability of the pet
     */
    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,description,available);
    }

}
