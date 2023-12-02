package com.stock.project.auth;

import com.stock.project.config.JwtUtils;
import com.stock.project.dto.LoginDTO;
import com.stock.project.dto.UserData;
import com.stock.project.transaction.TransactionService;
import com.stock.project.user.User;
import com.stock.project.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TransactionService transactionService;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TransactionService transactionService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.transactionService = transactionService;
    }

    public String saveUser(UserData userData) {
        try {
            User user = new User();
            user.setEmail(userData.getEmail());
            user.setPassword(userData.getPassword());
            user.setName(userData.getName());
            user.setPhoneNumber(userData.getPhoneNumber());
            user.setLastBonus(LocalDate.now());
            user.setBalance(1500.0);
            user.setWorkDays(0);
            userRepository.save(user);
            transactionService.addJoiningBonus(user);
            return JwtUtils.generateToken(user.getEmail());
        } catch (Exception e) {
            if (e.toString().toLowerCase().contains("email")) {
                return "Email is already taken";
            } else if (e.toString().toLowerCase().contains("phone_number")) {
                return "Phone number is already taken";
            } else if (e.toString().toLowerCase().contains("name")) {
                return "User Name is already taken";
            }
            return "Unique constraint violation: " + e.getMessage();
        }
    }

    public String loginUser(LoginDTO loginDTO) {
        User user = userRepository.findUserByEmail(loginDTO.getEmail()).orElse(null);
        if (user == null) return "This is not correct Username";
        else if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            return JwtUtils.generateToken(user.getEmail());
        else return "This is not correct password";
    }
}
