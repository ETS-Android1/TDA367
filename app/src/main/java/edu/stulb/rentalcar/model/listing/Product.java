package edu.stulb.rentalcar.model.listing;

import java.util.HashMap;
/**
 * abstract Product class
 *  @author Albert Wickman
 */

abstract public class Product {
    String name;
    String imagePath;
    String description;
    Location location;
    int pricePerDay;

    /**
     * Constructor for Product
     * @param name a Product's name
     * @param imagePath path to an image of a Product
     * @param description a description of a Product
     * @param location a location of a Product
     * @param pricePerDay the daily price for a Product
     *
     */
    public Product(String name, String imagePath, String description, Location location, int pricePerDay) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.location = location;
        this.pricePerDay = pricePerDay;
    }

    /**
     * Getter for description
     * @return a String of the description of a Product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for name
     * @return a String of the name of a Product
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for imagePath
     * @return a String of the image of a Product
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Getter for pricePerDay
     * @return an int of the daily price of a Product
     */
    public int getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Getter for location
     * @return the location of a Product of type Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter for extraInfo
     * @return creates a new HashMap
     */
    public HashMap<String, Object> getExtraInfo(){
        return new HashMap<>();
    }
}
