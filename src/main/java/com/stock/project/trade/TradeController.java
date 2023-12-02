package com.stock.project.trade;

import com.stock.project.dto.StockBuyDto;
import com.stock.project.user.User;
import com.upstox.api.GetMarketQuoteLastTradedPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GetMarketQuoteLastTradedPriceResponse> testingApi(@RequestBody String s){
        return ResponseEntity.ok(tradeService.testingApi(s));
    }
    @PostMapping(path = "buy")
    public ResponseEntity<Double> buyStock(@RequestBody StockBuyDto s){
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        GetMarketQuoteLastTradedPriceResponse x = tradeService.testingApi(s.getCode());
        double last_price = x.getData().values().stream().toList().get(0).getLastPrice();
        double total = last_price*s.getQuantity();
        if(total<=user.getBalance()){
            tradeService.addTrade(s.getCode(),s.getQuantity(),last_price,user);
            return ResponseEntity.ok(last_price);
        }
        else{
            return ResponseEntity.ok(-1.0);
        }
    }
    @PostMapping(path = "sell")
    public ResponseEntity<Boolean> sellStock(@RequestBody StockBuyDto s){
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        GetMarketQuoteLastTradedPriceResponse x = tradeService.testingApi(s.getCode());
        double last_price = x.getData().values().stream().toList().get(0).getLastPrice();
        if(tradeService.checkQuantityAvailable(user,s.getCode())>=s.getQuantity()){
            //proceed to sell
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.ok(false);
        }
    }
}
