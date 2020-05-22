package com.fooddeliveryapp.storefront.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Order {
    @JsonIgnore
    private String id;
    private String customerId;
    private String restaurantId;
    private List<OrderEntity> foods;
    private String driverId;
    private OrderState orderState;
    private String modifiedBy;
    private double totalPrice;

    public Order(){}

    public Order(String id, String customerId, String restaurantId, List<OrderEntity> foods, String driverId, OrderState orderState, String modifiedBy, Double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.foods = foods;
        this.driverId = driverId;
        this.orderState = orderState;
        this.modifiedBy = modifiedBy;
        this.totalPrice = totalPrice;
    }

    public List<OrderEntity> getFoods() {
        return foods;
    }

    public void setFoods(List<OrderEntity> foods) {
        this.foods = foods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
