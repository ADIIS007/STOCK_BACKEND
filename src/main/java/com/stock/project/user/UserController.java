package com.stock.project.user;

import com.stock.project.constants.Stock;
import com.stock.project.dto.SettingsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }

    @PostMapping(path = "dailyLogin")
    public ResponseEntity<Boolean> dailyLogin() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.dailyLogin(user));
    }

    @PostMapping(path = "requestWork")
    public ResponseEntity<Boolean> reqWork() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.canDoHardWork(user));
    }

    @PostMapping(path = "doWork")
    public ResponseEntity<Boolean> doWork() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.doHardWork(user));
    }

    @PostMapping(path = "settings")
    public ResponseEntity<SettingsPage> getSettings() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.getUserInfo(user));
    }

    @PostMapping(path = "listStock")
    public ResponseEntity<Stock[]> printAllStockItems() {
        return ResponseEntity.ok(Stock.values());
    }
}