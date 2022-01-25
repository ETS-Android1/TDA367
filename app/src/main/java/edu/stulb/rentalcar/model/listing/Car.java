package edu.stulb.rentalcar.model.listing;


import java.util.HashMap;

/**
 *  CarModel is a class to represent the model (model name and manufacturer) of a Car
 *  @author Josef Ngo
 */
public class Car extends Vehicle {
    private final String carModel;
    private final CarManufacturer carManufacturer;
    /**
     * Constructor to create a CarModel
     * @param carModel name of the car model, example V70
     * @param carManufacturer CarManufacturer
     */
    public Car(String name, String imagePath, String description, Location location, int pricePerDay, String vehicleYear, String carModel, CarManufacturer carManufacturer) {
        super(name, imagePath, description, location, pricePerDay, vehicleYear);
        this.carModel = carModel;
        this.carManufacturer = carManufacturer;
    }

    /**
     * getter for carModelName
     * @return the name of the carModel
     */
    public String getCarModel() {
        return carModel;
    }
    /**
     * getter for carManufacturer
     * @return a CarManufacturer for the specific carModel
     */
    public CarManufacturer getCarManufacturer() {
        return carManufacturer;
    }
    /**
     * getter for extraInfo
     * @return all the extra info associated with a Car
     */
    @Override
    public HashMap<String, Object> getExtraInfo() {
        HashMap<String, Object> extraInfo = new HashMap<>();
        extraInfo.put("CarModel", carModel);
        extraInfo.put("CarManufacturer", getCarManufacturer().getManufacturer());
        extraInfo.put("CarYear", this.getVehicleYear());
        return extraInfo;
    }
}
