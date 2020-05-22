package com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.repository;

import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "Order", path = "order")
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByCustomerId(String customerId);
    List<Order> findByRestaurantId(String restaurantId);
    List<Order> findByDriverId(String driverId);
}
