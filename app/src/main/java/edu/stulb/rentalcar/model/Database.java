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
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.model.listing.Location;
import edu.stulb.rentalcar.model.listing.Reservation;
import edu.stulb.rentalcar.model.user.Card;
import edu.stulb.rentalcar.model.user.User;
import edu.stulb.rentalcar.model.user.UserHandler;

/**
 * Singleton Database class
 * This class acts as a local database in the memory before
 * publishing the Listings or Users to firebase
 */
public class Database implements IQueryManager{

    private static final Database instance = new Database();
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Listing> listings = new ArrayList<>();

    /**
     * Private constructor to limit instances of this class.
     */
    private Database() {

    }

    /**
     * Getter for the single instance of Database
     * @return Database instance
     */
    public static Database getInstance() {
        return instance;
    }

    /**
     * @return ArrayList of the current Users in Database
     */
    public ArrayList<User> getUsers() {
        return users;
    }


    /**
     * @return ArrayList of the current Listings in Database
     */
    public ArrayList<Listing> getListings() {
        return listings;
    }


    /**
     * Sends a Listing to firebase
     * @param listing Given Listing
     */
    public void publishListing(Listing listing) {
        firestore.collection("listings").document(listing.getUid()).set(listing.toHashMap());
    }

    /**
     * Sends a User to firebase
     * @param user Given User
     */
    public void publishUser(User user) {
        firestore.collection("users").document(user.getEmail()).set(user.toHashMap());
    }

    /**
     * Async function to fill listings variable with Listings from firebase
     */
    public void fetchListings() {
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
        ListingHandler.getInstance().setListings(listings);
    }

    /**
     * Async function to fill user variable with Users from firebase
     */
    public void fetchUsers(){
        firestore.collection("users").get().addOnCompleteListener(this::onFetchUsersComplete);
    }

    private void onFetchUsersComplete(Task<QuerySnapshot> task){
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                users.add(fillUser(documentSnapshot));
            }
        }
        UserHandler.getInstance().setUsers(users);
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

    private void writeUsers() {
        for (User user: users) {
            publishUser(user);
        }
    }

    private void writeListings() {
        for (Listing listing: listings) {
            publishListing(listing);
        }
    }

    public void writeToFirebase(){
        writeUsers();
        writeListings();
    }
}
