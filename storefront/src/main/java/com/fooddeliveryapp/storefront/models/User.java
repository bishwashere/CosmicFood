package com.fooddeliveryapp.storefront.models;


import javax.validation.constraints.NotBlank;

public class User{

    private String id;

    @NotBlank(message = "Username is mandatory.")
    private String username;

    @NotBlank(message = "Password is mandatory.")
    private String password;

    @NotBlank(message ="First Name is mandatory.")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory.")
    private String lastName;

    @NotBlank(message = "Email is mandatory.")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;
    private String address;
    private UserRole role;

    private double lat = 0.00;
    private double lng = 0.00;


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int rating;

    public User() {
    }

    public User(String id, String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, UserRole role, int rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.rating = rating;
    }

    public User(String id, String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, UserRole role, int rating,double lat,double lng) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
