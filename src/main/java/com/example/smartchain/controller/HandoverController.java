package com.example.smartchain.controller;

import com.example.smartchain.entity.HandoverLog;
import com.example.smartchain.service.HandoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/handovers")
@CrossOrigin(origins = "*")
public class HandoverController {

    @Autowired
    private HandoverService handoverService;

    @PostMapping("/log")
    public HandoverLog logHandover(@RequestBody HandoverLog log) {
        return handoverService.logHandover(log);
    }

    @GetMapping("/shipment/{shipmentId}")
    public List<HandoverLog> getShipmentHandovers(@PathVariable Long shipmentId) {
        return handoverService.getHandoversByShipment(shipmentId);
    }
}
