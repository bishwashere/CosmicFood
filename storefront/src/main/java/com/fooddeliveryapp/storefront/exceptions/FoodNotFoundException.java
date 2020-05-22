package com.fooddeliveryapp.storefront.exceptions;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(Long id) {
        super("Food not found with id " + id);
    }
}

