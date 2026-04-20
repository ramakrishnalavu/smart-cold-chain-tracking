package com.example.smartchain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartchain.entity.Shipment;
import com.example.smartchain.entity.User;
import com.example.smartchain.model.ShipmentStatus;
import com.example.smartchain.repository.ShipmentRepository;
import com.example.smartchain.repository.UserRepository;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    public Shipment createShipment(Shipment shipment, String creatorRole) {
        shipment.setStatus(ShipmentStatus.CREATED);
        return shipmentRepository.save(shipment);
    }

    public Shipment assignShipment(Long shipmentId, Long driverId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow();
        User driver = userRepository.findById(driverId).orElseThrow();
        shipment.setAssignedDriver(driver);
        shipment.setStatus(ShipmentStatus.ASSIGNED);
        return shipmentRepository.save(shipment);
    }

    public Shipment updateStatus(Long shipmentId, ShipmentStatus status) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow();
        shipment.setStatus(status);
        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public List<Shipment> getShipmentsByDriver(String username) {
        User driver = userRepository.findByUsername(username).orElseThrow();
        return shipmentRepository.findByAssignedDriver(driver);
    }

    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id).orElseThrow();
    }
}
