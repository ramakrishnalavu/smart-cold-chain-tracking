package com.example.smartchain.config;

import com.example.smartchain.entity.Shipment;
import com.example.smartchain.entity.TemperatureReading;
import com.example.smartchain.entity.User;
import com.example.smartchain.model.Role;
import com.example.smartchain.model.ShipmentStatus;
import com.example.smartchain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private TemperatureReadingRepository readingRepository;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private HandoverLogRepository handoverRepository;

    @Autowired
    private ComplianceReportRepository reportRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin12").isEmpty()) {
            seedUser("admin12", "admin@test1.com", Role.ADMIN);
            System.out.println("Primary Admin (admin12) seeded successfully");
        } else {
            // Ensure email matches user request
            User admin = userRepository.findByUsername("admin12").get();
            admin.setEmail("admin@test1.com");
            userRepository.save(admin);
        }
    }

    private User seedUser(String username, String email, Role role) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode("password123"))
                .role(role)
                .build();
        return userRepository.save(user);
    }

    private void seedReadings(Shipment shipment) {
        for (int i = 5; i >= 0; i--) {
            TemperatureReading reading = TemperatureReading.builder()
                    .shipment(shipment)
                    .temperature(3.5 + (Math.random() * 2))
                    .timestamp(LocalDateTime.now().minusHours(i))
                    .isCompliant(true)
                    .build();
            readingRepository.save(reading);
        }
    }
}
