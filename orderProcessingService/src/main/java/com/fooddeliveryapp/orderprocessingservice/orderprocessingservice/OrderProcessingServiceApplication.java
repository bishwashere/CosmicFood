package com.fooddeliveryapp.orderprocessingservice.orderprocessingservice;

import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class OrderProcessingServiceApplication {

    @Autowired
    OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessingServiceApplication.class, args);
    }

    @PostConstruct
    public void constructDb() {
        orderRepository.deleteAll();
    }
}
