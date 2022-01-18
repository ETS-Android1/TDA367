package edu.stulb.rentalcar.database;




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
 * Firebase class to interact with the Firebase service
 * Singleton class
 */
public class Firebase implements IDatabase{

    private static final Firebase instance = new Firebase();
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Listing> listings = new ArrayList<>();

    /**
     * Private constructor to limit instances of this class.
     * Initiates listeners and initiates users and listings arrays.
     */
    private Firebase() {
        fetchUsers();
        fetchListings();
        initListeners();
    }

    /**
     * Getter for the single instance of Database
     * @return Database instance
     */
    public static Firebase getInstance() {
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
    public void createListing(Listing listing) {
        firestore.collection("listings").document(listing.getUid()).set(listing.toHashMap());
    }

    /**
     * Sends a User to firebase
     * @param user Given User
     */
    public void createUser(User user) {
        firestore.collection("users").document(user.getEmail()).set(user.toHashMap());
    }

    /**
     * Async function to fill listings variable with Listings from firebase
     */
    private void fetchListings() {
        firestore.collection("listings").get().addOnCompleteListener(this::onListingsComplete);
    }

    private void onListingsComplete(Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            ArrayList<Listing> newlistings = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                Listing listing = fillListing(documentSnapshot);
                newlistings.add(listing);
            }
            listings = newlistings;
        }

        //ListingHandler.getInstance().setListings(listings);
    }

    public void deleteUser(User user) {
        /*Not implemented*/
    }

    public void deleteListing(Listing listing) {
        /*Not implemented*/
    }

    /**
     * Async function to fill user variable with Users from firebase
     * Only used on application start
     */
    private void fetchUsers(){
        firestore.collection("users").get().addOnCompleteListener(this::onFetchUsersComplete);
    }

    private void onFetchUsersComplete(Task<QuerySnapshot> task){
        if (task.isSuccessful()) {
            ArrayList<User> newusers = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                newusers.add(fillUser(documentSnapshot));
            }
            users = newusers;
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

    private Listing fillListing(QueryDocumentSnapshot documentSnapshot){
        CarManufacturer carManufacturer = new CarManufacturer(documentSnapshot.getString("CarManufacturer"));
        Car car = new Car(documentSnapshot.getString("CarModel"), carManufacturer, documentSnapshot.getString("CarYear"));
        int pricePerDay = (int) (long) documentSnapshot.get("ListingPricePerDay");
        String userEmail = documentSnapshot.getString("UserEmail");
        Location location = new Location(documentSnapshot.getString("ListingLocation"));
        Reservation reservation = new Reservation((List<Long>) documentSnapshot.get("ReservationBookedDates"));
        String imagePath = documentSnapshot.getString("ImagePath");
        String uid = documentSnapshot.getString("ListingId");
        return new Listing(car, pricePerDay, location, userEmail, reservation, imagePath, uid);
    }


    private void initListeners(){
        firestore.collection("listings").addSnapshotListener((value, e) -> {
            ArrayList<Listing> newlistings = new ArrayList<>();
            for (QueryDocumentSnapshot doc : value){
                Listing listing = fillListing(doc);
                newlistings.add(listing);
            }
            listings = newlistings;
        });

        firestore.collection("users").addSnapshotListener((value, e) -> {
            ArrayList<User> newusers = new ArrayList<>();
            for (QueryDocumentSnapshot doc : value){
                User user = fillUser(doc);
                newusers.add(user);
            }
            users = newusers;
        });
    }
}
