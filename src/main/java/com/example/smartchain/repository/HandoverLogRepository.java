package com.example.smartchain.repository;

import com.example.smartchain.entity.HandoverLog;
import com.example.smartchain.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HandoverLogRepository extends JpaRepository<HandoverLog, Long> {
    List<HandoverLog> findByShipment(Shipment shipment);
}
