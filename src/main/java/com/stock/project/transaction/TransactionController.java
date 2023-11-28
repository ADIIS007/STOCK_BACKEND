package com.stock.project.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/transaction")
public class TransactionController {
    @GetMapping(path = "test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test");
    }
}
