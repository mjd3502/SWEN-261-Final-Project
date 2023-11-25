package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import java.util.Map;

import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;


public interface FundingBasketDAO {

    FundingBasket createFundingBasket(FundingBasket fundingBasket) throws IOException;

    FundingBasket addNeedToFundingBasket(String userName, Need need) throws IOException;

    boolean removeNeedFromFundingBasket(String userName,int need) throws  IOException;

    Map<Integer, Need> getFundingBasket(String userName) throws IOException;


    boolean clearFundingBasket(String userName) throws IOException;
    
}
