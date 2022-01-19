package edu.stulb.rentalcar.model.listing;

import java.util.HashMap;

abstract public class Product {
    String name;
    String imagePath;
    String description;
    Location location;
    int pricePerDay;


    public Product(String name, String imagePath, String description, Location location, int pricePerDay) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.location = location;
        this.pricePerDay = pricePerDay;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public Location getLocation() {
        return location;
    }

    public HashMap<String, Object> getExtraInfo(){
        return new HashMap<>();
    }
}
