package com.example.smartchain.controller;

import com.example.smartchain.entity.Shipment;
import com.example.smartchain.model.ShipmentStatus;
import com.example.smartchain.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_LOGISTICS_MANAGER')")
    public Shipment createShipment(@RequestBody Shipment shipment) {
        return shipmentService.createShipment(shipment, "LOGISTICS_MANAGER");
    }

    @PostMapping("/assign")
    @PreAuthorize("hasAuthority('ROLE_LOGISTICS_MANAGER')")
    public Shipment assignShipment(@RequestParam Long shipmentId, @RequestParam Long driverId) {
        return shipmentService.assignShipment(shipmentId, driverId);
    }

    @PostMapping("/status")
    @PreAuthorize("hasAnyAuthority('ROLE_WAREHOUSE_STAFF', 'ROLE_DRIVER')")
    public Shipment updateStatus(@RequestParam Long shipmentId, @RequestParam ShipmentStatus status) {
        return shipmentService.updateStatus(shipmentId, status);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_LOGISTICS_MANAGER')")
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/driver/{username}")
    @PreAuthorize("hasAnyAuthority('ROLE_DRIVER', 'ROLE_ADMIN', 'ROLE_LOGISTICS_MANAGER')")
    public List<Shipment> getShipmentsByDriver(@PathVariable String username) {
        return shipmentService.getShipmentsByDriver(username);
    }

    @GetMapping("/{id}")
    public Shipment getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id);
    }
}
