package edu.stulb.rentalcar.model;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.user.User;

/**
 * Singleton Database class
 */
public class Database {

    private static Database instance = new Database();
    private FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Listing> listings = new ArrayList<>();

    private Database() {

    }

    public static Database getInstance() {
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }

    private HashMap<String, Object> generateListingHashMap(Listing listing) {
        HashMap<String, Object> listingHashMap = new HashMap<>();

        //KEYS gives String to field inside document
        listingHashMap.put("ListingId", listing.getUid());
        listingHashMap.put("CarManufacturer", listing.getCar().getCarManufacturer().getManufacturer());
        listingHashMap.put("CarModel", listing.getCar().getCarModel());
        listingHashMap.put("CarYear", listing.getCar().getCarYear());
        listingHashMap.put("ListingPricePerDay", listing.getPricePerDay());
        listingHashMap.put("ListingLocation", listing.getLocation().getCity());
        listingHashMap.put("UserEmail", listing.getUser().getEmail());
        listingHashMap.put("ReservationBookedDates", listing.getReservation());
        return listingHashMap;
    }

    public void publishListing(Listing listing){
        HashMap<String, Object> tempMap = generateListingHashMap(listing);
        FirebaseFirestore.getInstance().collection("testUsers").document("test").set(tempMap);
    }
}
