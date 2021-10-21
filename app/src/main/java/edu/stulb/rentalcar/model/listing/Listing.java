package edu.stulb.rentalcar.model.listing;

import edu.stulb.rentalcar.model.user.User;

/**
 *  Listing is a class to represent a listing of a Car.
 *  @author Josef Ngo
 */
public class Listing {
    private Car car;
    private int pricePerDay;
    private Location location;
    private User user;
    private final String uid;
    private Reservation reservation;
    private String imagePath;

    public Listing(Car car, int pricePerDay, Location location, User user,Reservation reservation, String imagePath) {
        this.car = car;
        this.pricePerDay = pricePerDay;
        this.location = location;
        this.user = user;
        this.uid = java.util.UUID.randomUUID().toString();
        this.reservation = reservation;
        this.imagePath = imagePath;
    }

    /**
     * Returns uid for listing.
     * @return String of unique identifier for listing.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Getter for Listings car.
     * @return the Car in the listing.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Getter for listings pricePerDay
     * @return an int for pricePerDay
     */
    public int getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Getter for listings location
     * @return a Location that represent location for listing.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter for listings user
     * @return the User that created the listing.
     */
    public User getUser() {
        return user;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public String getImagePath() {
        return imagePath;
    }
}
