package com.example.smartchain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "temperature_readings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemperatureReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @Column(nullable = false)
    private Double temperature;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private Boolean isCompliant;
}
