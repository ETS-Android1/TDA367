package edu.stulb.rentalcar.model.listing;

abstract public class Vehicle extends Product {
    private final String vehicleYear;
    /**
     * Constructor to create a Vehicle
     * @param vehicleYear year the vehicle was manufactured
     */
    public Vehicle(String name, String imagePath, String description, Location location, int pricePerDay, String vehicleYear) {
        super(name, imagePath, description, location, pricePerDay);
        this.vehicleYear = vehicleYear;
    }

    /**
     * getter for what year the vehicle was manufactured
     * @return A String with the year of manufacturing
     */
    public String getVehicleYear() {
        return vehicleYear;
    }
}
