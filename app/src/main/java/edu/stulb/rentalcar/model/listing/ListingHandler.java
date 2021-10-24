package edu.stulb.rentalcar.model.listing;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.Database;

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

    public boolean updateListingReservation(Listing listing, Reservation reservation){
        listings.remove(listing);
        Listing updatedListing = new Listing(listing.getCar(), listing.getPricePerDay(), listing.getLocation(), listing.getUserEmail(),reservation ,listing.getImagePath(), listing.getUid());
        listings.add(updatedListing);
        System.out.println("Listing created");
        return true;
    }

    public ArrayList<Long> getListingReservation(String listingId){
        for (Listing listing: listings) {
            if (listing.getUid().equals(listingId)){
                System.out.println("Hittade rätt id");
                return (ArrayList<Long>) listing.getReservation().getReservationsDatesList();
            }
        }
        return new ArrayList<>();
    }

    public int getListingPricePerDay(String listingId){
        for (Listing listing: listings) {
            if (listing.getUid().equals(listingId)){
                return listing.getPricePerDay();
            }
        }
        return 0;
    }

    public String getCarTitle(String listingId){
        for (Listing listing: listings) {
            if (listing.getUid().equals(listingId)){
                return listing.getCar().getCarManufacturer().getManufacturer()+" "
                        +listing.getCar().getCarModel();
            }
        }
        return "";
    }

    public Listing getListingFromUid(String uid){
        for (Listing listing:listings) {
            if (listing.getUid().equals(uid)){
                return listing;
            }
        }
        return null;
    }

    public ArrayList<Listing> getUserListings(String userEmail){
        ArrayList<Listing> usersListing = new ArrayList<>();
        //Nullcatch
        if (userEmail == null)
            return usersListing;
        for (Listing listing: listings) {
            if (listing.getUserEmail().equalsIgnoreCase(userEmail)){
                usersListing.add(listing);
            }
        }
        return usersListing;
    }
}
