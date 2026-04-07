package com.example.OrderManagementSystem.KafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private static final String TOPIC ="order-created";
    public void sendOrderEvent(String message){
        kafkaTemplate.send(TOPIC,message);
    }
}
