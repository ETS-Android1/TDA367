package edu.stulb.rentalcar.model;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.user.User;

/**
 * Singleton Database class
 */
public class Database {

    private static Database instance = new Database();

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Listing> listings = new ArrayList<>();

    private Database(){

    }

    public static Database getInstance(){
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }
}
