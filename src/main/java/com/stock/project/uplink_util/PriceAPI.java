package com.stock.project.uplink_util;

import com.upstox.ApiClient;
import com.upstox.ApiException;
import com.upstox.Configuration;
import com.upstox.api.GetMarketQuoteLastTradedPriceResponse;
import com.upstox.auth.OAuth;
import io.swagger.client.api.MarketQuoteApi;
import org.springframework.stereotype.Service;

@Service
public class PriceAPI {
    ApiClient defaultClient = Configuration.getDefaultApiClient();

    public PriceAPI() {
        OAuth OAUTH2 = (OAuth) defaultClient.getAuthentication("OAUTH2");
        OAUTH2.setAccessToken("eyJ0eXAiOiJKV1QiLCJrZXlfaWQiOiJza192MS4wIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiI3TEJUN1QiLCJqdGkiOiI2NTY3NmM5MjA4M2NjODU3OGUwNDBjNDEiLCJpc011bHRpQ2xpZW50IjpmYWxzZSwiaXNBY3RpdmUiOnRydWUsInNjb3BlIjpbImludGVyYWN0aXZlIiwiaGlzdG9yaWNhbCJdLCJpYXQiOjE3MDEyNzY4MTgsImlzcyI6InVkYXBpLWdhdGV3YXktc2VydmljZSIsImV4cCI6MTcwMTI5NTIwMH0.w3lFpFY_tHkpKEViIFb02S0I0CaaBJRXu-RWKWc2fSA");
    }

    public GetMarketQuoteLastTradedPriceResponse ltp(String org){
        MarketQuoteApi apiInstance = new MarketQuoteApi();
        String symbol = org; // String | Comma separated list of symbols
        String apiVersion = "2.0"; // String | API Version Header
        try {
            GetMarketQuoteLastTradedPriceResponse result = apiInstance.ltp(symbol, apiVersion);
            System.out.println(result);
            return result;
        } catch (ApiException e) {
            System.err.println("Exception when calling MarketQuoteApi#ltp "+e.getResponseBody());
            e.printStackTrace();
        }
        return null;
    }
}
