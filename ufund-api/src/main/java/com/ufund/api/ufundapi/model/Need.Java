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


    public String get_name(){
        return name;
    }

    public int get_quatity(){
        return quantity;
    }

    public String get_descripiton(){
        return description;
    }

    public int get_cost(){
        return cost;
    }

    public String get_type(){
        return type;
    }

}
