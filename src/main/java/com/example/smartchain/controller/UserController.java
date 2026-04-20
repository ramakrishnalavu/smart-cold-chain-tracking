package com.example.smartchain.controller;

import com.example.smartchain.entity.User;
import com.example.smartchain.model.Role;
import com.example.smartchain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_WAREHOUSE_STAFF')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable Role role) {
        return userRepository.findByRole(role);
    }

    @GetMapping("/drivers")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_LOGISTICS_MANAGER')")
    public List<User> getDrivers() {
        return userRepository.findByRole(Role.DRIVER);
    }
}
