package com.example.smartchain.service;

import com.example.smartchain.entity.User;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        User user = userRepository.findByUsername(username).orElseThrow();
        return jwtAuth.generateToken(user.getUsername(), user.getRole().name());
    }
}
