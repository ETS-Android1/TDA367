package edu.stulb.rentalcar.model.listing;


/**
 *  CarModel is a class to represent the model (model name, manufacturer and manufacturing year) of a Car
 *  @author Josef Ngo
 */
public class Car extends Product {
    private final String carModel;
    private final CarManufacturer carManufacturer;
    private final String carYear;

    /**
     * Constructor to create a CarModel
     * @param carModel name of the car model, example V70
     * @param carManufacturer CarManufacturer
     * @param carYear year the car was manufactured
     */

    public Car(String name, String imagePath, String description, Location location, int pricePerDay, String carModel, CarManufacturer carManufacturer, String carYear) {
        super(name, imagePath, description, location, pricePerDay);
        this.carModel = carModel;
        this.carManufacturer = carManufacturer;
        this.carYear = carYear;
    }

    @Override
    public String getDescription() {
        return carYear;
    }

    @Override
    public String getName() {
        return carManufacturer.getManufacturer()+" "+carModel;
    }

    /**
     * getter for carModelName
     * @return the name of the carModel
     */



    /**
     * getter for carManufacturer
     * @return a CarManufacturer for the specific carModel
     */


    /**
     * getter for what year the car was manufactured
     * @return An int with the year of manufacturing
     */

}
