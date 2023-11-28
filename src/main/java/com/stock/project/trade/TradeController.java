package com.stock.project.trade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/trade")
public class TradeController {
    @GetMapping(path = "test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test");
    }
}
