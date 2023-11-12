package com.ufund.api.ufundapi.persistence;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ufund.api.ufundapi.model.FundingBasket;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;

public interface FundingBasketDAO {

    FundingBasket createFundingBasket(FundingBasket fundingBasket) throws IOException;

    FundingBasket addNeedToFundingBasket(String userName, Need need) throws IOException;

    boolean removeNeedFromFundingBasket(String userName,int need) throws  IOException;

    Map<Integer, Need> getFundingBasket(String userName) throws IOException;


    boolean clearFundingBasket(String userName) throws IOException;
    
}
