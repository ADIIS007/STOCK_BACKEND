package com.stock.project.transaction;

import com.stock.project.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }

    @GetMapping(path = "list")
    public ResponseEntity<List<Transaction>> list() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(transactionService.list(user));
    }
}
