package edu.stulb.rentalcar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.stulb.rentalcar.model.listing.Car;
import edu.stulb.rentalcar.model.listing.CarManufacturer;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.model.user.Card;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.Location;
import edu.stulb.rentalcar.model.listing.Reservation;
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
    public void userIsCorrect() {
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        User user = new User("Hannes", "Hannes@gmail.com", "Stulb123", card);
        assertEquals(user.getName(), "Hannes");
        assertEquals(user.getEmail(), "Hannes@gmail.com");
        assertEquals(user.getCard().getCardName(), "Hannes Thörn");
        assertEquals(user.getCard().getCardCvv(), "111");
        assertEquals(user.getCard().getCardDate(), "11/25");
        assertEquals(user.getCard().getCardNumber(), "5355830012341234");
    }
    @Test
    public void listingIsCorrect(){
        CarManufacturer carManufacturer = new CarManufacturer("Volvo");
        Car car = new Car("v90", carManufacturer, "2005");
        Location location = new Location("Göteborg");
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        User user = new User("Hannes", "Hannes@gmail.com","Stulb123", card);
        Listing listing = new Listing(car, 200, location, user, "PathentillBilden");
        assertEquals(listing.getCar().getCarModel(), "v90");
        assertEquals(listing.getLocation().getCity(), "Göteborg");
        assertEquals(listing.getPricePerDay(), 200);
        assertEquals(listing.getUser().getName(), "Hannes");
        assertEquals(listing.getUid(), listing.getUid());
    }

    @Test
    public void signInIsCorrect(){
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        UserHandler.getInstance().createUser("Hannes", "hannes@gmail.com", "Stulb123", card);
        UserHandler.getInstance().signIn("Hannes@gmail.com", "Stulb123");
    }

    @Test
    public void listingCreationIsCorrect(){
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        UserHandler.getInstance().createUser("Hannes", "hannes@gmail.com", "Stulb123", card);
        UserHandler.getInstance().signIn("Hannes@gmail.com", "Stulb123");
        User user = UserHandler.getInstance().getCurrentUser();


        CarManufacturer carManufacturer = new CarManufacturer("Volvo");
        Car car = new Car("v90", carManufacturer, "2005");
        Location location = new Location("Göteborg");

        ListingHandler.getInstance().createListing(car, 200, location, user, "PathTillBilden");
        ArrayList<Listing> tempList = ListingHandler.getInstance().getListings();
        System.out.println(tempList.get(0).getCar().getCarModel());
    }


}
