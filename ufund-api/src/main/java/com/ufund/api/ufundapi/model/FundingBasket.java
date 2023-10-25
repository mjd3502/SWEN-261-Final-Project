package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FundingBasket {
 
    public static final String STRING_FORMAT_FundingBasket = "FundingBasket [userName=%s, fundingBasket=%s ]  ";

 
    @JsonProperty("userName") private String userName;
    @JsonProperty("fundingBasket") private List<Need> fundingBasket;

    public FundingBasket(@JsonProperty("userName") String userName, @JsonProperty("fundingBasket") List<Need> fundingBasket){
        this.userName = userName;
        this.fundingBasket = this.fundingBasket = (fundingBasket != null) ? fundingBasket : new ArrayList<>();
    }

    
    /**
     * Retrieves the userName
     * @return The username
     */
    public String getUserName() {
        return userName;
    }
    
    public List<Need> getFundingBasket() {
        return fundingBasket;
    }
    public void setFundingBasket(Need need) {
        if(!this.fundingBasket.contains(need)){
             this.fundingBasket.add(need);
        }
    }

    @Override
    public String toString() {
        StringBuilder needs = new StringBuilder();
        
        if (!fundingBasket.isEmpty()) {
            for (int i = 0; i < fundingBasket.size(); i++) {
                Need item = fundingBasket.get(i);
                needs.append(item.toString());
                if (i < fundingBasket.size() - 1) {
                    needs.append(", ");
                }
            }
        } else {
            needs.append("No items in the funding basket");
        }
        
        return String.format(STRING_FORMAT_FundingBasket,userName, needs.toString());
    }


}
