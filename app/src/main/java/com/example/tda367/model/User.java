package com.example.tda367.model;

/**
 * User is a class to represent a user
 * @author Johan Sandberg
 */
public class User {
    private String name;
    private String email;
    private Card card;

    /**
     * Constructor to create a new user
     * @param name
     * @param email
     * @param card
     */
    public User(String name, String email, Card card) {
        this.name = name;
        this.email = email;
        this.card = card;
    }

    /**
     * Getter for users name
     * @return String of users name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for users email
     * @return String of users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for users Card
     * @return Card as users card
     */
    public Card getCard() {
        return card;
    }
}
