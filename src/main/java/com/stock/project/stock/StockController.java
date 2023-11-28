package com.stock.project.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/stock")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(path = "test")
    public ResponseEntity<String> Test(){
        return ResponseEntity.ok("Successful");
    }

    @GetMapping(path = "list")
    public ResponseEntity<List<Stock>> list(){
        return ResponseEntity.ok(stockService.listStock());
    }
}
