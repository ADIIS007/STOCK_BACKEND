package com.stock.project.auth;

import com.stock.project.dto.LoginDTO;
import com.stock.project.dto.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }

    @PostMapping(path = "signup")
    public ResponseEntity<String> signup(@RequestBody UserData userData) {
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        String res = authService.saveUser(userData);
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String res = authService.loginUser(loginDTO);
        return ResponseEntity.ok(res == null ? "NotSuccessful" : res);
    }
}
