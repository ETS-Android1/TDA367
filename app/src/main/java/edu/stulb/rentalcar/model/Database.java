package edu.stulb.rentalcar.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.stulb.rentalcar.model.listing.Car;
import edu.stulb.rentalcar.model.listing.CarManufacturer;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.Location;
import edu.stulb.rentalcar.model.listing.Reservation;
import edu.stulb.rentalcar.model.user.Card;
import edu.stulb.rentalcar.model.user.User;

/**
 * Singleton Database class
 */
public class Database {

    private static Database instance = new Database();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

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

    public void fetchListings() { //kanske kan va privat senare
        firestore.collection("listings").get().addOnCompleteListener(this::onListingsComplete);
    }

    private void onListingsComplete(Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                CarManufacturer carManufacturer = new CarManufacturer(documentSnapshot.getString("CarManufacturer"));
                Car car = new Car(documentSnapshot.getString("CarModel"), carManufacturer, documentSnapshot.getString("CarYear"));
                int pricePerDay = (int) (long) documentSnapshot.get("ListingPricePerDay");
                String userEmail = documentSnapshot.getString("UserEmail");
                Location location = new Location(documentSnapshot.getString("ListingLocation"));
                Reservation reservation = new Reservation((List<Long>) documentSnapshot.get("ReservationBookedDates"));
                String imagePath = documentSnapshot.getString("ImagePath");
                String uid = documentSnapshot.getString("ListingId");
                Listing listing = new Listing(car, pricePerDay, location, userEmail, reservation, imagePath, uid);
                listings.add(listing);
            }
        }
    }

    public void fetchUsers(){
        firestore.collection("users").get().addOnCompleteListener(this::onUsersComplete);
    }

    private void onUsersComplete(Task<QuerySnapshot> task){
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                users.add(fillUser(documentSnapshot));
            }
        }
    }

    private User fillUser(QueryDocumentSnapshot documentSnapshot){
        String name = documentSnapshot.getString("Name");
        String email = documentSnapshot.getString("Email");
        String password = documentSnapshot.getString("Password");
        String cardCVV = documentSnapshot.getString("CardCVV");
        String cardDate = documentSnapshot.getString("CardDate");
        String cardName = documentSnapshot.getString("CardName");
        String cardNumber = documentSnapshot.getString("CardNumber");
        Card card = new Card(cardName, cardNumber, cardDate, cardCVV);
        return new User(name, email, password, card);
    }
}
