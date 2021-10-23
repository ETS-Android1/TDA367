package edu.stulb.rentalcar.model.user;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.Database;

/**
 * Singleton UserHandler class to handle currentUser and user sign in
 */
public class UserHandler {
    private static final UserHandler instance = new UserHandler();
    private boolean isUserSignedIn = false;
    private User currentUser;
    private final ArrayList<User> users = Database.getInstance().getUsers();

    private UserHandler() {
    }

    public static UserHandler getInstance() {
        return instance;
    }

    public boolean createUser(String name, String email, String password, Card card){
        if(users.size() > 0) {
            for (User user : users) {
                if (user.getEmail().toLowerCase().equals(email)) {
                    System.out.println("This email already exists");
                    return false;
                }
            }
        }

        User user = new User(name, email, password, card);
        users.add(user);
        System.out.println("User created");
        return true;
    }

    public boolean signIn(String email, String password) {
        if (!isUserSignedIn) {
            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                    currentUser = user;
                    isUserSignedIn = true;
                    System.out.println("Sign in success");
                    return true;
                }
            }
        }
        System.out.println("Sign in failed");
        return false;
    }

    public void signOut(){
        if (isUserSignedIn){
            isUserSignedIn = false;
            currentUser = null;
        }
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public boolean isUserSignedIn() {
        return isUserSignedIn;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
