package edu.stulb.rentalcar.model.listing;

abstract public class Vehicle extends Product {
    private final String vehicleYear;
    /**
     * Constructor to create a CarModel
     * @param vehicleYear year the car was manufactured
     */
    public Vehicle(String name, String imagePath, String description, Location location, int pricePerDay, String vehicleYear) {
        super(name, imagePath, description, location, pricePerDay);
        this.vehicleYear = vehicleYear;
    }

    /**
     * getter for what year the car was manufactured
     * @return An int with the year of manufacturing
     */
    public String getVehicleYear() {
        return vehicleYear;
    }
}
