package com.example.smartchain.entity;

import com.example.smartchain.model.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productType;

    @Column(nullable = false)
    private Double minTemp;

    @Column(nullable = false)
    private Double maxTemp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;
}
