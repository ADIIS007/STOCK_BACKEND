package com.stock.project.auth;

import com.stock.project.dto.LoginDTO;
import com.stock.project.dto.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(UserData userData) {
        try {
            User user = new User();
            user.setEmail(userData.getEmail());
            user.setPassword(userData.getPassword());
            user.setName(userData.getName());
            user.setPhoneNumber(userData.getPhoneNumber());
            userRepository.save(user);
            return "Successful";
        } catch (Exception e) {

            if (e.toString().toLowerCase().contains("email")) {
                return "Email is already taken";
            } else if (e.toString().toLowerCase().contains("phone_number")) {
                return "Phone number is already taken";
            }else if (e.toString().toLowerCase().contains("name")) {
                return "User Name is already taken";
            }
            return "Unique constraint violation: " + e.getMessage();
        }
    }

    public String loginUser(LoginDTO loginDTO){
        User user = userRepository.findUserByEmail(loginDTO.getEmail()).orElse(null);
        if(user==null) return null;
        else if(user.getPassword()==loginDTO.getPassword()) return user.getName();
        else return null;
    }
}
