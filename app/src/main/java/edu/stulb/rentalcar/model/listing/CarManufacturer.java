package edu.stulb.rentalcar.model.listing;

/**
 * CarManufacturer is a class to represent the manufacturer of a Car
 *
 * @author Josef Ngo
 */
public class CarManufacturer {
    private final String manufacturer;

    /**
     * Constructor to create a manafacturer
     *
     * @param manufacturer - manufacturers index in our predetermined array.
     */
    public CarManufacturer(String manufacturer) {
        if (checkValidManufacturer(manufacturer)) {
            this.manufacturer = manufacturer.toLowerCase();
        } else {
            this.manufacturer = "N/A";
        }

    }

    /**
     * @return String with the name of the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    private boolean checkValidManufacturer(String manufacturer){
        String[] validManufacturers = getManufacturers();
        for (String validManufacturer : validManufacturers) {
            if (validManufacturer.equalsIgnoreCase(manufacturer)) {
                return true;
            }
        }
        return false;
    }


    /**
     * @return Array of strings containing valid manufacturers
     */
    private String[] getManufacturers() {

        return new String[]{
                "Volvo",
                "Volkswagen",
                "Kia",
                "Toyota",
                "BMW",
                "Audi",
                "Mercedes",
                "Skoda",
                "Renault",
                "Peugeot",
                "Nissan",
                "Seat",
                "Ford"
        };
    }
}
