package edu.stulb.rentalcar.model;

/**
 * CarManufacturer is a class to represent the manufacturer of a Car
 *
 * @author Josef Ngo
 */
public class CarManufacturer {
    private String manufacturer;

    /**
     * Constructor to create a manafacturer
     *
     * @param manufacturer - manufacturers index in our predetermined array.
     */
    public CarManufacturer(String manufacturer) {
        if (checkValidManufacturer(manufacturer)) {
            this.manufacturer = manufacturer.toLowerCase();
        } else {
            this.manufacturer = "Manufacturer not found";
            System.out.println("Manufacturer not found");
        }

    }

    /**
     * @return String with the name of the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    private boolean checkValidManufacturer(String manufacturer){
        String manufacturerL = manufacturer.toLowerCase();
        String[] validManufacturers = getManufacturers();
        for (String validManufacturer : validManufacturers) {
            if (validManufacturer.equals(manufacturerL)) {
                return true;
            }
        }
        return false;
    }

    private String[] getManufacturers() {

        return new String[]{
                "volvo",
                "volkswagen",
                "kia",
                "toyota",
                "bmw",
                "audi",
                "mercedes",
                "skoda",
                "renault",
                "peugeot",
                "nissan",
                "seat",
                "ford"
        };
    }
}
