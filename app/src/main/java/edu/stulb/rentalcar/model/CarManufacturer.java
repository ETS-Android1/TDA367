package edu.stulb.rentalcar.model;

/**
 *  CarManufacturer is a class to represent the manufacturer of a Car
 *  @author Josef Ngo
 */
public class CarManufacturer {
    private String manufacturer;

    /**
     * Constructor to create a manafacturer
     * @param manafacturer - Name of the manufacturer
     */
    public CarManufacturer(String manafacturer) {
        this.manufacturer = manafacturer;
    }

    /**
     * @return String with the name of the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }
}
