package com.example.tda367.model;


/**
 *  CarModel is a class to represent the model (model name, manufacturer and manufacturing year) of a Car
 *  @author Josef Ngo
 */
public class CarModel {
    private final String carModelName;
    private final CarManufacturer carManufacturer;
    private final int carYear;

    /**
     * Constructor to create a CarModel
     * @param carModelName name of the car model, example V70
     * @param carManufacturer CarManufacturer
     * @param carYear year the car was manufactured
     */
    public CarModel(String carModelName, CarManufacturer carManufacturer, int carYear) {
        this.carModelName = carModelName;
        this.carManufacturer = carManufacturer;
        this.carYear = carYear;
    }

    /**
     * getter for carModelName
     * @return the name of the carModel
     */
    public String getCarModelName() {
        return carModelName;
    }

    /**
     * getter for carManufacturer
     * @return a CarManufacturer for the specific carModel
     */
    public CarManufacturer getCarManufacturer() {
        return carManufacturer;
    }


    /**
     * getter for what year the car was manufactured
     * @return An int with the year of manufacturing
     */
    public int getCarYear() {
        return carYear;
    }
}
