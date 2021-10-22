package edu.stulb.rentalcar.model.user;

/**
 * Card is a class to represent a users card used for payment
 * @author Johan Sandberg
 */
public class Card {
    private final String cardName;
    private final String cardNumber;
    private final String cardDate;
    private final String cardCvv;

    /**
     * Constructor to create a card
     * @param cardName cards name
     * @param cardNumber cards number
     * @param cardDate cards date
     * @param cardCvv cards Cvv
     */
    public Card(String cardName, String cardNumber, String cardDate, String cardCvv) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.cardCvv = cardCvv;
    }

    /**
     * @return String of name on card
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @return String of number on card
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @return String of date on card
     */
    public String getCardDate() {
        return cardDate;
    }

    /**
     * @return String of CVV on card
     */
    public String getCardCvv() {
        return cardCvv;
    }
}
