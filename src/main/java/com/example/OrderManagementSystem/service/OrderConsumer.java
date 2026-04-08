package com.example.OrderManagementSystem.service;


import io.lettuce.core.output.ScanOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    @Autowired
    private EmailService emailService;
    private static Logger logger= LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "order-created", groupId = "order-group")
    public void consume(String message) {
        String[] parts=message.split("\\|");
        String email=parts[0];
        String orderId=parts[1];
        String subject="Order Confirmation";
        String body="Your order with this id " + orderId + "has been placed succesfully";
        emailService.sendEmail(email,subject,body);
        logger.info("Email sent to: {}", email);
    }}


        //future sends email/update invennav