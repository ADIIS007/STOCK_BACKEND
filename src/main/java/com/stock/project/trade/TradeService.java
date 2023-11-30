package com.stock.project.trade;

import com.stock.project.uplink_util.PriceAPI;
import com.upstox.api.GetMarketQuoteLastTradedPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    PriceAPI priceAPI;

    @Autowired
    public TradeService(PriceAPI priceAPI) {
        this.priceAPI = priceAPI;
    }

    public GetMarketQuoteLastTradedPriceResponse testingApi(String org){
        return priceAPI.ltp(org);
    }
}
