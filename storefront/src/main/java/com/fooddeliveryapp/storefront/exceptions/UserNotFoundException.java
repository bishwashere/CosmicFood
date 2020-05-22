package com.fooddeliveryapp.storefront.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {
        super("User not found with id " + id);
    }

    public UserNotFoundException(String username) {
        super("User not found with username " + username);
    }
}
