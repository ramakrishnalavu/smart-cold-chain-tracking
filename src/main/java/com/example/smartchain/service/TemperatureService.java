package com.example.smartchain.service;

import com.example.smartchain.entity.Alert;
import com.example.smartchain.entity.Shipment;
import com.example.smartchain.entity.TemperatureReading;
import com.example.smartchain.model.AlertSeverity;
import com.example.smartchain.model.ShipmentStatus;
import com.example.smartchain.repository.AlertRepository;
import com.example.smartchain.repository.ShipmentRepository;
import com.example.smartchain.repository.TemperatureReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TemperatureService {

    @Autowired
    private TemperatureReadingRepository readingRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private AlertRepository alertRepository;

    public TemperatureReading addReading(Long shipmentId, Double temperature) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow();
        
        boolean isCompliant = temperature >= shipment.getMinTemp() && temperature <= shipment.getMaxTemp();
        
        TemperatureReading reading = TemperatureReading.builder()
                .shipment(shipment)
                .temperature(temperature)
                .timestamp(LocalDateTime.now())
                .isCompliant(isCompliant)
                .build();
        
        readingRepository.save(reading);

        if (!isCompliant) {
            generateAlert(shipment, temperature);
            shipment.setStatus(ShipmentStatus.EXCURSION);
            shipmentRepository.save(shipment);
        }

        return reading;
    }

    private void generateAlert(Shipment shipment, Double temperature) {
        AlertSeverity severity = AlertSeverity.LOW;
        double deviation = Math.abs(temperature - (temperature < shipment.getMinTemp() ? shipment.getMinTemp() : shipment.getMaxTemp()));
        
        if (deviation > 5.0) severity = AlertSeverity.CRITICAL;
        else if (deviation > 2.0) severity = AlertSeverity.HIGH;

        Alert alert = Alert.builder()
                .shipment(shipment)
                .severity(severity)
                .message("Temperature excursion detected: " + temperature + "°C (Range: " + shipment.getMinTemp() + " to " + shipment.getMaxTemp() + ")")
                .timestamp(LocalDateTime.now())
                .resolved(false)
                .build();
        
        alertRepository.save(alert);
    }

    public List<TemperatureReading> getReadingsByShipment(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow();
        return readingRepository.findByShipment(shipment);
    }
}
