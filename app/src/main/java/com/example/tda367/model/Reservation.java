package com.example.tda367.model;

import java.util.List;

/**
 * Reservation is a class to represent the dates the car is booked
 * @author Johan Sandberg
 */
public class Reservation {
    private List<Long> reservationsDatesList;

    /**
     * Constructor to create a list to store dates
     * Uses Long to store dates @see <a href="https://docs.oracle.com/javase/8/docs/api/?java/util/Date.html">https://docs.oracle.com/javase/8/docs/api/?java/util/Date.html</a>
     * @param reservationsDatesList
     */
    public Reservation(List<Long> reservationsDatesList) {
        this.reservationsDatesList = reservationsDatesList;
    }

    /**
     * Getter for reservationsDatesList
     * @return a list of reservationsDatesList
     */
    public List<Long> getReservationsDatesList() {
        return reservationsDatesList;
    }
}
