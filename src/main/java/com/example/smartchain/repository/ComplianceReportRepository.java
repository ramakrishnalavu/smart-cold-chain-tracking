package com.example.smartchain.repository;

import com.example.smartchain.entity.ComplianceReport;
import com.example.smartchain.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ComplianceReportRepository extends JpaRepository<ComplianceReport, Long> {
    Optional<ComplianceReport> findByShipment(Shipment shipment);
}
