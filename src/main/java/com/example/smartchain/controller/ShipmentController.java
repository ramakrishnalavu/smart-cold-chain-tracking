package com.example.smartchain.controller;

import com.example.smartchain.entity.Shipment;
import com.example.smartchain.model.ShipmentStatus;
import com.example.smartchain.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = "*")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/create")
    public Shipment createShipment(@RequestBody Shipment shipment) {
        return shipmentService.createShipment(shipment, "LOGISTICS_MANAGER");
    }

    @PostMapping("/assign")
    public Shipment assignShipment(@RequestParam Long shipmentId, @RequestParam Long driverId) {
        return shipmentService.assignShipment(shipmentId, driverId);
    }

    @PostMapping("/status")
    public Shipment updateStatus(@RequestParam Long shipmentId, @RequestParam ShipmentStatus status) {
        return shipmentService.updateStatus(shipmentId, status);
    }

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    public Shipment getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id);
    }
}
