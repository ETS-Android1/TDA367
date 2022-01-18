package edu.stulb.rentalcar.database;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.user.User;

public interface IDatabase {

    void createUser(User user);

    void createListing(Listing listing);

    void deleteUser(User user);

    void deleteListing(Listing listing);

    ArrayList<User> getUsers();

    ArrayList<Listing> getListings();

}
