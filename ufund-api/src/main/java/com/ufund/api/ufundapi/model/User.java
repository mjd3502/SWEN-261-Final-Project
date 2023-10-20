package com.ufund.api.ufundapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class User {
    public static final String STRING_FORMAT_USER = "User [id=%d, userName=%s, fundingBasket=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("userName") private String userName;
    @JsonProperty("fundingBasket") private List<Need> fundingBasket;

    public User(@JsonProperty("id") int id, @JsonProperty("name") String userName, @JsonProperty("fundingBasket") List<Need> fundingBasket){
        this.userName = userName;
        this.id=id;
        this.fundingBasket = fundingBasket;
    }
    /**
     * Retrieves the name of the user
     * @return The name of the user
     */
    public String getName() {
        return userName;
    }
    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Need> getFundingBasket() {
        return fundingBasket;
    }
    public void setFundingBasket(List<Need> fundingBasket) {
        this.fundingBasket = fundingBasket;
    }

    @Override
    public String toString() {
        StringBuilder needs = new StringBuilder();
        for (Need item: fundingBasket) {
            needs.append(item.toString()).append(", ");
        }
        return String.format(STRING_FORMAT, id, userName, needs.toString());
    }
    
}
