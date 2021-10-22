package edu.stulb.rentalcar.model.listing;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.Database;
import edu.stulb.rentalcar.model.user.User;

public class ListingHandler {
    private static ListingHandler instance = new ListingHandler();
    private ArrayList<Listing> listings = Database.getInstance().getListings();

    private ListingHandler(){
    }

    public static ListingHandler getInstance(){
        return instance;
    }

    public boolean createListing(Car car, int pricePerDay, Location location, String userEmail, Reservation reservation, String imagePath){
        //TODO kolla om den är med kanske?
        Listing listing = new Listing(car, pricePerDay, location, userEmail, reservation, imagePath);
        listings.add(listing);
        System.out.println("Listing created");
        return true;
    }

    public ArrayList<Listing> getListings(){
        return listings;
    }

    public ArrayList<Listing> getUserListings(User user){
        ArrayList<Listing> usersListing = new ArrayList<>();
        //Nullcatch
        if (user.getEmail() == null)
            return usersListing;
        for (Listing listing: listings) {
            if (listing.getUserEmail().equalsIgnoreCase(user.getEmail())){
                usersListing.add(listing);
            }
        }
        return usersListing;
    }
}
