package edu.stulb.rentalcar.model;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.user.User;

public interface IQueryManager {

    void publishUser();

    void publishListing();

    Listing getListings();

    User getUsers();
}
