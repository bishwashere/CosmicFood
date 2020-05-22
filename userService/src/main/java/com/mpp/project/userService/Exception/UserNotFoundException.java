package com.mpp.project.userService.Exception;

public class UserNotFoundException extends RuntimeException {
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserNotFoundException(String s) {
        this.message = s;
    }
}
