package com.fooddeliveryapp.storefront.models;

public class OrderEntity {
    private String foodId;
    private int quantity;

    public OrderEntity() {
    }

    public OrderEntity(String foodId, int quantity) {
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
