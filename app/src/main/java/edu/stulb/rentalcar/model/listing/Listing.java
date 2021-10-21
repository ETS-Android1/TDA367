package edu.stulb.rentalcar.model.listing;

import java.util.HashMap;

/**
 *  Listing is a class to represent a listing of a Car.
 *  @author Josef Ngo
 */
public class Listing {
    private Car car;
    private int pricePerDay;
    private Location location;
    private String userEmail;
    private final String uid;
    private Reservation reservation;
    private String imagePath;

    public Listing(Car car, int pricePerDay, Location location, String userEmail,Reservation reservation, String imagePath) {
        this.car = car;
        this.pricePerDay = pricePerDay;
        this.location = location;
        this.userEmail = userEmail;
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
    public String getUserEmail() {
        return userEmail;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public String getImagePath() {
        return imagePath;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> listingHashMap = new HashMap<>();

        //KEYS gives String to field inside document
        listingHashMap.put("ListingId", this.getUid());
        listingHashMap.put("CarManufacturer", this.getCar().getCarManufacturer().getManufacturer());
        listingHashMap.put("CarModel", this.getCar().getCarModel());
        listingHashMap.put("CarYear", this.getCar().getCarYear());
        listingHashMap.put("ListingPricePerDay", this.getPricePerDay());
        listingHashMap.put("ListingLocation", this.getLocation().getCity());
        listingHashMap.put("UserEmail", this.getUserEmail());
        listingHashMap.put("ReservationBookedDates", this.getReservation().getReservationsDatesList());
        listingHashMap.put("ImagePath", this.getImagePath());
        return listingHashMap;
    }
}
