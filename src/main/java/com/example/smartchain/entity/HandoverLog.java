package com.example.smartchain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "handover_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HandoverLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String condition;
}
