package com.example.smartchain.service;

import com.example.smartchain.entity.HandoverLog;
import com.example.smartchain.entity.Shipment;
import com.example.smartchain.entity.User;
import com.example.smartchain.repository.HandoverLogRepository;
import com.example.smartchain.repository.ShipmentRepository;
import com.example.smartchain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HandoverService {

    @Autowired
    private HandoverLogRepository handoverRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    public HandoverLog recordHandover(Long shipmentId, Long fromUserId, Long toUserId, String condition) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow();
        User fromUser = userRepository.findById(fromUserId).orElse(null);
        User toUser = userRepository.findById(toUserId).orElseThrow();

        HandoverLog log = HandoverLog.builder()
                .shipment(shipment)
                .fromUser(fromUser)
                .toUser(toUser)
                .timestamp(LocalDateTime.now())
                .condition(condition)
                .build();
        
        return handoverRepository.save(log);
    }

    public HandoverLog logHandover(HandoverLog log) {
        log.setTimestamp(LocalDateTime.now());
        return handoverRepository.save(log);
    }

    public java.util.List<HandoverLog> getHandoversByShipment(Long shipmentId) {
        return handoverRepository.findByShipmentId(shipmentId);
    }
}
