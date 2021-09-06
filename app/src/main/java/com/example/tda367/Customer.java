package com.example.tda367;

public class Customer {
    private String customerId;
    private String username;
    private String email;
    private String adress;
    private int phoneNumber;
    private int age;

    public Customer(String id, String username, String email, String adress, int phoneNumber, int age) {
        this.customerId = id;
        this.username = username;
        this.email = email;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getId() {
        return customerId;
    }

    public void setId(String id) {
        this.customerId = id;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}