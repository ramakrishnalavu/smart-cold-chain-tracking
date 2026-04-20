package com.example.smartchain.config;

import com.example.smartchain.entity.User;
import com.example.smartchain.model.Role;
import com.example.smartchain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            seedUser("admin", "admin@example.com", Role.ADMIN);
            seedUser("manager", "manager@example.com", Role.LOGISTICS_MANAGER);
            seedUser("staff", "staff@example.com", Role.WAREHOUSE_STAFF);
            seedUser("driver", "driver@example.com", Role.DRIVER);
            seedUser("officer", "officer@example.com", Role.COMPLIANCE_OFFICER);
            System.out.println("Initial users seeded successfully");
        }
    }

    private void seedUser(String username, String email, Role role) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode("password123"))
                .role(role)
                .build();
        userRepository.save(user);
    }
}
