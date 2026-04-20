package com.example.smartchain.repository;

import com.example.smartchain.entity.Alert;
import com.example.smartchain.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByShipment(Shipment shipment);
    List<Alert> findByResolvedFalse();
}
