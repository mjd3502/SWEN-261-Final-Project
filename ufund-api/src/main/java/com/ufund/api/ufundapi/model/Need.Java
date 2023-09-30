package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Need {
    @JsonProperty("name") private String name;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("description") private String description;
    @JsonProperty("cost") private int cost;

    //represents the type of need, voluteer opprotunity or good
    @JsonProperty("type")private String type;

    public Need(@JsonProperty("name") String name, @JsonProperty("quantity") int quantity, @JsonProperty("description") String description, @JsonProperty("cost") int cost, @JsonProperty("type") String type ){
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.cost = cost;
        this.type = type;
    }


    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public int getCost() {
        return cost;
    }


    public void setCost(int cost) {
        this.cost = cost;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

}
