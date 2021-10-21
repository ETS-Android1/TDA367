package edu.stulb.rentalcar.model.user;

import edu.stulb.rentalcar.model.Card;

/**
 * User is a class to represent a user
 * @author Johan Sandberg
 */
public class User {
    private String name;
    private String email;
    private String password;
    private Card card;

    /**
     * Constructor to create a new user
     * @param name user name
     * @param email user email
     * @param card user Card
     */
    public User(String name, String email, String password, Card card) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.card = card;
    }

    /**
     * Getter for user name
     * @return String of users name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for user email
     * @return String of users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for user Card
     * @return Card as users card
     */
    public Card getCard() {
        return card;
    }


    /**
     * Getter for user password
     * @return String of users password
     */
    public String getPassword() {
        return password;
    }
}
