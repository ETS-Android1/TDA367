package edu.stulb.rentalcar.model.listing;

abstract public class Product {
    String name;
    String imagePath;
    String description;
    Location location;
    int pricePerDay;

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

    public String getLocation() {
        return location;
    }

    String location;

}
