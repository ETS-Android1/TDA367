package edu.stulb.rentalcar.model.listing;

abstract public class Product {
    String name;
    String imagePath;
    int pricePerDay;

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
