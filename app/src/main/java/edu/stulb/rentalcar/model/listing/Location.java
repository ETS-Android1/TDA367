package edu.stulb.rentalcar.model.listing;

/**
 * Location is a class to represent the location of a car
 * @author Johan Sandberg
 */
public class Location {
    private final String city;

    /**
     * Constructor to create a mew location
     * @param city a city
     */
    public Location(String city) {
        this.city = city;
    }

    /**
     * @return String of name of city
     */
    public String getCity() {
        return city;
    }
}
