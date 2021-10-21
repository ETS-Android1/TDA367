package edu.stulb.rentalcar.model;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.user.User;

/**
 * Singleton Database class
 */
public class Database {

    private static Database instance = new Database();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

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


//TODO ändra från testUsers till den riktiga
    public void publishListing(Listing listing){
        firestore.collection("listings").document(listing.getUid()).set(listing.toHashMap());
    }

    public void publishUser(User user){
        firestore.collection("users").document(user.getEmail()).set(user.toHashMap());
    }
}
