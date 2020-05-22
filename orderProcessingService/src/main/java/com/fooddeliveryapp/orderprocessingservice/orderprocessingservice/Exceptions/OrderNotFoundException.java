package com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.Exceptions;

public class OrderNotFoundException extends RuntimeException {
    String message;

    public OrderNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
