package com.example.smartchain.controller;

import com.example.smartchain.entity.TemperatureReading;
import com.example.smartchain.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temperature")
@CrossOrigin(origins = "*")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @PostMapping("/add")
    public TemperatureReading addReading(@RequestParam Long shipmentId, @RequestParam Double temperature) {
        return temperatureService.addReading(shipmentId, temperature);
    }

    @GetMapping("/shipment/{shipmentId}")
    public List<TemperatureReading> getReadings(@PathVariable Long shipmentId) {
        return temperatureService.getReadingsByShipment(shipmentId);
    }
}
