package com.stock.project.trade;

import com.upstox.api.GetMarketQuoteLastTradedPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/trade")
public class TradeController {
    TradeService tradeService;
    @Autowired
    TradeController(TradeService tradeService){
        this.tradeService=tradeService;
    }
    @GetMapping(path = "test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test");
    }
    @PostMapping(path = "testAPI")
    public ResponseEntity<GetMarketQuoteLastTradedPriceResponse> testingApi(){
        return ResponseEntity.ok(tradeService.testingApi("NSE_EQ"));
    }
}
