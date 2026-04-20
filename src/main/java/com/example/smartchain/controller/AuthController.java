package com.example.smartchain.controller;

import com.example.smartchain.entity.User;
import com.example.smartchain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        return authService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
