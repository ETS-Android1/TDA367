package edu.stulb.rentalcar.model;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import edu.stulb.rentalcar.model.listing.Car;
import edu.stulb.rentalcar.model.listing.CarManufacturer;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.Location;
import edu.stulb.rentalcar.model.listing.Reservation;
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

    public void publishListing(Listing listing) {
        firestore.collection("listings").document(listing.getUid()).set(listing.toHashMap());
    }

    public void publishUser(User user) {
        firestore.collection("users").document(user.getEmail()).set(user.toHashMap());
    }

    private void updateListingsList() {
        firestore.collection("listings").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    CarManufacturer carManufacturer = new CarManufacturer(documentSnapshot.getString("CarManufacturer"));
                    Car car = new Car(documentSnapshot.getString("CarModel"), carManufacturer, documentSnapshot.getString("CarYear"));
                    int pricePerDay = (int) documentSnapshot.get("ListingPricePerDay");
                    String userEmail = documentSnapshot.getString("UserEmail");
                    Location location = new Location(documentSnapshot.getString("ListingLocation"));
                    Reservation reservation = new Reservation((List<Long>) documentSnapshot.get("ReservationBookedDates"));
                    String imagePath = documentSnapshot.getString("ImagePath");
                    Listing listing = new Listing(car, pricePerDay, location, userEmail, reservation, imagePath);
                    listings.add(listing);

                }
            }
        });
    }
}
