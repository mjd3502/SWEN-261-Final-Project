package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FundingBasket {
 
    public static final String STRING_FORMAT_FundingBasket = "FundingBasket [userName=%s, fundingBasket=%s ]  ";

 
    @JsonProperty("userName") private String userName;
    @JsonProperty("fundingBasket") private Map<Integer,Need> listOfNeeds;

    public FundingBasket(@JsonProperty("userName") String userName, @JsonProperty("fundingBasket") Map<Integer,Need> listOfNeeds){
        this.userName = userName;
        this.listOfNeeds = this.listOfNeeds = (listOfNeeds != null) ? listOfNeeds : new HashMap<>();
    }

    
    /**
     * Retrieves the userName
     * @return The username
     */
    public String getUserName() {
        return userName;
    }
    
    public Map<Integer,Need> getFundingBasket() {
        return this.listOfNeeds;
    }
    public void setFundingBasket(Need need) {
        this.listOfNeeds.put(need.getId(), need);
    }

    @Override
    public String toString() {
        StringBuilder needs = new StringBuilder();
        
        if (!listOfNeeds.isEmpty()) {

            int count =0;
            for(Need need: listOfNeeds.values()){
                needs.append(need.toString());
                if (count < listOfNeeds.size() - 1) {
                    needs.append(", ");
                }
                count ++;
            }
        } else {
            needs.append("No items in the funding basket");
        }
        
        return String.format(STRING_FORMAT_FundingBasket,userName, needs.toString());
    }


}
