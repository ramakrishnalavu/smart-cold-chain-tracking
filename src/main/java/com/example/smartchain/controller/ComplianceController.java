package com.example.smartchain.controller;

import com.example.smartchain.entity.ComplianceReport;
import com.example.smartchain.service.ComplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compliance")
@CrossOrigin(origins = "*")
public class ComplianceController {

    @Autowired
    private ComplianceService complianceService;

    @PostMapping("/generate/{shipmentId}")
    public ComplianceReport generateReport(@PathVariable Long shipmentId) {
        return complianceService.generateReport(shipmentId);
    }
}
