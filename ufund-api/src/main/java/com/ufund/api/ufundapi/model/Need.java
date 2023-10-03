package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Need entity
 * 
 * @author Cheyenne Zhang, Carla Lopez, Rachel Atkins, Garrett Geyer, Michael Dibiase
 */
public class Need {

    static final String STRING_FORMAT = "Need [id=%d,name =%s, quantity = %d, description = %s, cost = %d, type = %s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("description") private String description;
    @JsonProperty("cost") private int cost;

    //represents the type of need, voluteer opportunity or good
    @JsonProperty("type")private String type;
    
    /**
     * Create a hero with the given id, name quantity, description, cost, and type
     * @param id The id of the need
     * @param name The name of the need
     * @param quantity The quantity of the need
     * @param description The description of the need
     * @param cost The cost of the need
     * @param type The type of the need
     */
    public Need(@JsonProperty("id")int id , @JsonProperty("name") String name, @JsonProperty("quantity") int quantity, @JsonProperty("description") String description, @JsonProperty("cost") int cost, @JsonProperty("type") String type ){
        this.id=id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.cost = cost;
        this.type = type;

    }

    /**
     * Retrieves the id of the need
     * @return the id of the need
     */ 
    public int getId(){
        return id;
    }

    /**
     * Retrieves the name of the need
     * @return The name of the need
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the need - necessary for Java object deserialization
     * @param name The name of the need
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the quantity of the need
     * @return The quantity of the need
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the need - necessary for Java object deserialization
     * @param quantity The quantity of the need
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the description of the need
     * @return The description of the need
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the need - necessary for Java object deserialization
     * @param description The description of the need
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the cost of the need
     * @return The cost of the need
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the cost of the need - necessary for Java object deserialization
     * @param cost The cost of the need
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Retrieves the type of the need
     * @return The type of the need
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the need - necessary for Java object deserialization
     * @param type The type of the need
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,quantity,description,cost,type);
    }

}
