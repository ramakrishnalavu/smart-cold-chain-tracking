package com.example.smartchain.controller;

import com.example.smartchain.entity.Alert;
import com.example.smartchain.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    @Autowired
    private AlertRepository alertRepository;

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @GetMapping("/active")
    public List<Alert> getActiveAlerts() {
        return alertRepository.findByResolvedFalse();
    }

    @PostMapping("/resolve/{id}")
    public Alert resolveAlert(@PathVariable Long id) {
        Alert alert = alertRepository.findById(id).orElseThrow();
        alert.setResolved(true);
        return alertRepository.save(alert);
    }
}
