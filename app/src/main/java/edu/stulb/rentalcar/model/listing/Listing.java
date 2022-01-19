package edu.stulb.rentalcar.model.listing;

import java.util.HashMap;

/**
 *  Listing is a class to represent a listing of a Car.
 *  @author Josef Ngo
 */
public class Listing {
    private final String userEmail;
    private final String uid;
    private final Reservation reservation;
    private final Product product;

    /**
     * Constructor to create a Listing
     * @param car a Car object
     * @param userEmail listing creator's email
     * @param reservation a Reservation object
     */
    public Listing(Product product, String userEmail, Reservation reservation) {
        this.product = product;
        this.userEmail = userEmail;
        this.uid = java.util.UUID.randomUUID().toString();
        this.reservation = reservation;
    }

    /**
     * Constructor to recreate a listing that already exists (therefore takes a unique identifier String)
     */
    public Listing(Product product, String userEmail, Reservation reservation, String uid) {
        this.product = product;
        this.userEmail = userEmail;
        this.uid = uid;
        this.reservation = reservation;
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
    public Product getProduct() {
        return product;
    }

    /**
     * Getter for listings pricePerDay
     * @return an int for pricePerDay
     */

    /**
     * Getter for listings location
     * @return a Location that represent location for listing.
     */


    /**
     * Getter for listings user
     * @return the User that created the listing.
     */
    public String getUserEmail() {
        return userEmail;
    }


    /**
     * Getter for listing reservation
     * @return a Reservation for the listing.
     */
    public Reservation getReservation() {
        return reservation;
    }


    /**
     * Getter for path to listings image
     * @return a String with the path to listings image
     */


    /**
     * Creates a HashMap of a Listing object for uploading to firebase.
     * @return A HashMap of the Listing
     */
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> listingHashMap = new HashMap<>();
        listingHashMap.put("ListingId", this.getUid());
        listingHashMap.put("ProductName", this.getProduct().getName());
        listingHashMap.put("ExtraInfo", this.getProduct().getExtraInfo());
        listingHashMap.put("ProductDescription", this.getProduct().getDescription());
        listingHashMap.put("ProductPricePerDay", this.getProduct().getPricePerDay());
        listingHashMap.put("ProductLocation", this.getProduct().getLocation().getCity());
        listingHashMap.put("UserEmail", this.getUserEmail());
        listingHashMap.put("ReservationBookedDates", this.getReservation().getReservationsDatesList());
        listingHashMap.put("ImagePath", this.getProduct().getImagePath());
        return listingHashMap;
    }
}
