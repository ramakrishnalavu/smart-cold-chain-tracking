package com.example.smartchain.repository;

import com.example.smartchain.entity.Shipment;
import com.example.smartchain.entity.TemperatureReading;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TemperatureReadingRepository extends JpaRepository<TemperatureReading, Long> {
    List<TemperatureReading> findByShipment(Shipment shipment);
}
