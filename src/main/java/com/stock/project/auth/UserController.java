package com.stock.project.auth;

import com.stock.project.dto.LoginDTO;
import com.stock.project.dto.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test");
    }
    @PostMapping(path = "signup")
    public ResponseEntity<String> signup(@RequestBody UserData userData){
        String res = userService.saveUser(userData);
        return ResponseEntity.ok(res);
    }
    @PostMapping(path = "login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        String res = userService.loginUser(loginDTO);
        return ResponseEntity.ok(res==null?"NotSuccessful":res);
    }
}
