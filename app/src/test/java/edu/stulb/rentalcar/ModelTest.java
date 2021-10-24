package edu.stulb.rentalcar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.stulb.rentalcar.model.listing.Car;
import edu.stulb.rentalcar.model.listing.CarManufacturer;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.model.listing.Location;
import edu.stulb.rentalcar.model.listing.Reservation;
import edu.stulb.rentalcar.model.user.Card;
import edu.stulb.rentalcar.model.user.User;
import edu.stulb.rentalcar.model.user.UserHandler;

public class ModelTest {

    @Test
    public void carCreationIsCorrect(){
        CarManufacturer carManufacturer = new CarManufacturer("Volvo");
        Car car = new Car("V70", carManufacturer, "2021");
        assertEquals(car.getCarModel(), "V70");
        assertEquals(car.getCarYear(), "2021");
        assertEquals(car.getCarManufacturer().getManufacturer(), "volvo");
    }
    @Test
    public void locationIsCorrect(){
        Location location = new Location("Göteborg");
        assertEquals(location.getCity(), "Göteborg");
    }
    @Test
    public void reservationIsCorrect() {
        Reservation reservation = new Reservation();
        List<Long> testList = new ArrayList<>();
        assertEquals(reservation.getReservationsDatesList(), testList);
    }

    @Test
    public void listingIsCorrect(){
        CarManufacturer carManufacturer = new CarManufacturer("Volvo");
        Car car = new Car("v90", carManufacturer, "2005");
        Location location = new Location("Göteborg");
        Reservation reservation = new Reservation();
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        User user = new User("Hannes", "Hannes@gmail.com","Stulb123", card);
        Listing listing = new Listing(car, 200, location, user.getEmail(), reservation, "PathentillBilden");
        assertEquals(listing.getCar().getCarModel(), "v90");
        assertEquals(listing.getLocation().getCity(), "Göteborg");
        assertEquals(listing.getPricePerDay(), 200);
        assertEquals(listing.getUserEmail(), "Hannes@gmail.com");
        assertEquals(listing.getUid(), listing.getUid());
    }

    @Test
    public void signInAndCarCreationIsCorrect(){
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        UserHandler.getInstance().createUser("Hannes", "hannes@gmail.com", "Stulb123", card);
        UserHandler.getInstance().signIn("Hannes@gmail.com", "Stulb123");

        String userEmail = UserHandler.getInstance().getCurrentUser().getEmail();

        CarManufacturer carManufacturer = new CarManufacturer("Volvo");
        Car car = new Car("v90", carManufacturer, "2005");
        Location location = new Location("Göteborg");
        Reservation reservation = new Reservation();
        ListingHandler.getInstance().createListing(car, 200, location, userEmail, reservation, "PathTillBilden");
        ArrayList<Listing> tempList = ListingHandler.getInstance().getListings();
        System.out.println(tempList.get(0).getCar().getCarModel());
        System.out.println();
    }



}
