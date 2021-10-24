package edu.stulb.rentalcar.model;

import java.util.List;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.user.User;

public interface IQueryManager {

    void publishUser(User user);

    void publishListing(Listing listing);

    List<Listing> getListings();

    List<User> getUsers();
}
