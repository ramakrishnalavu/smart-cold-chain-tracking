package com.example.smartchain.service;

import com.example.smartchain.entity.ComplianceReport;
import com.example.smartchain.entity.Shipment;
import com.example.smartchain.entity.TemperatureReading;
import com.example.smartchain.repository.ComplianceReportRepository;
import com.example.smartchain.repository.ShipmentRepository;
import com.example.smartchain.repository.TemperatureReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceService {

    @Autowired
    private ComplianceReportRepository reportRepository;

    @Autowired
    private TemperatureReadingRepository readingRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    public ComplianceReport generateReport(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow();
        List<TemperatureReading> readings = readingRepository.findByShipment(shipment);

        if (readings.isEmpty()) {
            return reportRepository.save(new ComplianceReport(null, shipment, 100.0, LocalDateTime.now()));
        }

        long compliantCount = readings.stream().filter(TemperatureReading::getIsCompliant).count();
        double percentage = (double) compliantCount / readings.size() * 100;

        ComplianceReport report = reportRepository.findByShipment(shipment)
                .orElse(new ComplianceReport());
        
        report.setShipment(shipment);
        report.setCompliancePercentage(percentage);
        report.setGeneratedAt(LocalDateTime.now());

        return reportRepository.save(report);
    }
}
