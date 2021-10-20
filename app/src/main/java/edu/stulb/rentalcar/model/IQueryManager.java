package edu.stulb.rentalcar.model;

public interface IQueryManager {

    public User getUser();

    public void createUser();

    public void createListing();

    public boolean isUserSignedIn();

    public void signOut();

    public Listing getListing();
}
