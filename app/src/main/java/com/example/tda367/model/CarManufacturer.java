package com.example.tda367.model;

/**
 *  CarManufacturer is a class to represent the manufacturer of a Car.
 */
public class CarManufacturer {
    String manafacturer;

    /**
     * Constructor to create a manafacturer
     * @param manafacturer - Name of the manufacturer
     */
    public CarManufacturer(String manafacturer) {
        this.manafacturer = manafacturer;
    }

    /**
     * @return String with the name of the manufacturer
     */
    public String getManufacturer() {
        return manafacturer;
    }
}
