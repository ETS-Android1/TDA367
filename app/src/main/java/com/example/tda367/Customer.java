package com.example.tda367;

public class Customer {
    private Long customerId;
    private String username;
    private String email;
    private String adress;
    private String phoneNumber;
    private int age;

    public Customer(Long customerId, String username, String email, String adress, String phoneNumber, int age) {
        this.customerId = customerId;
        this.username = username;
        this.email = email;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}