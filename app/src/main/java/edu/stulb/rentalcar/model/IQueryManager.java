package edu.stulb.rentalcar.model;

import edu.stulb.rentalcar.model.user.User;

public interface IQueryManager {

    User getUser();

    void createUser();

    void createListing();

    boolean isUserSignedIn();

    void signOut();

    Listing getListing();
}
