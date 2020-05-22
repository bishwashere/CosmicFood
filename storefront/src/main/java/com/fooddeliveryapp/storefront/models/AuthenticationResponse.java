package com.fooddeliveryapp.storefront.models;

public class AuthenticationResponse {
    boolean Success;
    String jwt;
    String msg;

    public AuthenticationResponse(){

    }

    public AuthenticationResponse(boolean success, String token, String msg) {
        Success = success;
        this.jwt = token;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String token) {
        this.jwt = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
