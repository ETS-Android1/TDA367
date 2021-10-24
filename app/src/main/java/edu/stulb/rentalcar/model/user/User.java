package edu.stulb.rentalcar.model.user;

import java.util.HashMap;

/**
 * User is a class to represent a user
 * @author Johan Sandberg
 */
public class User {
    private final String name;
    private final String email;
    private final String password;
    private final Card card;

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

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> userHashMap = new HashMap<>();

        //KEYS gives String to field inside document
        userHashMap.put("Name", this.getName());
        userHashMap.put("Email", this.getEmail());
        userHashMap.put("Password", this.getPassword());
        userHashMap.put("CardName", this.getCard().getCardName());
        userHashMap.put("CardNumber", this.getCard().getCardNumber());
        userHashMap.put("CardDate", this.getCard().getCardDate());
        userHashMap.put("CardCVV", this.getCard().getCardCvv());
        return userHashMap;
    }
}
