package com.example.smartchain.repository;

import com.example.smartchain.entity.Shipment;
import com.example.smartchain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByAssignedDriver(User driver);
}
