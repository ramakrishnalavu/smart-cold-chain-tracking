package com.example.smartchain.service;

import com.example.smartchain.entity.User;
import com.example.smartchain.model.LoginResponse;
import com.example.smartchain.repository.UserRepository;
import com.example.smartchain.security.JWTAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTAuth jwtAuth;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User register(User user) {
        System.out.println("Processing registration for: " + user.getUsername());
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("FAIL_USERNAME: Username '" + user.getUsername() + "' is already taken.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("FAIL_EMAIL: Email '" + user.getEmail() + "' is already registered.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public LoginResponse login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        User user = userRepository.findByUsername(username).orElseThrow();
        String token = jwtAuth.generateToken(user.getUsername(), user.getRole().name());
        
        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}
