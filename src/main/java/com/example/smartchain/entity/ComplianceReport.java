package com.example.smartchain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplianceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @Column(nullable = false)
    private Double compliancePercentage;

    @Column(nullable = false)
    private LocalDateTime generatedAt;
}
