package edu.stulb.rentalcar;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.stulb.rentalcar.model.*;

public class ModelTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void carCreationIsCorrect(){
        CarManufacturer carManufacturer = new CarManufacturer("Volvo");
        Car car = new Car("V70", carManufacturer, 2021);
        assertEquals(car.getCarModel(), "V70");
        assertEquals(car.getCarYear(), 2021);
        assertEquals(car.getCarManufacturer().getManufacturer(), "volvo");
    }
}
