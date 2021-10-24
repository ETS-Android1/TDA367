package edu.stulb.rentalcar.controller.Profile;

import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.listing.Car;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.model.listing.Location;
import edu.stulb.rentalcar.model.listing.Reservation;
import edu.stulb.rentalcar.model.user.UserHandler;
import edu.stulb.rentalcar.view.Browse.AddListingFragment;
import edu.stulb.rentalcar.view.EditProfileFragment;
import edu.stulb.rentalcar.view.Profile.SignInFragment;

public class ProfileViewModel extends ViewModel {

    UserHandler userHandler = UserHandler.getInstance();
    ListingHandler listingHandler = ListingHandler.getInstance();

    public ProfileViewModel() {

    }

    public ArrayList<Listing> getUsersListings(){
        return listingHandler.getUserListings(userHandler.getCurrentUser().getEmail());
    }

    public void createListing(String carBrand, String carModel, String carYear, int carPrice, String carLocation, Uri selectedImage){
        Car car = Car.createCar(carModel, carBrand, carYear);
        Location location = new Location(carLocation);
        String email = userHandler.getCurrentUser().getEmail();
        listingHandler.createListing(car, carPrice, location, email, new Reservation(), selectedImage.toString());
    }

    public void loadSignInFragment(FragmentManager fragmentManager) {
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }

    public void loadAddCarAdFragment(FragmentManager fragmentManager) {
        Fragment addCarAdFragment = new AddListingFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarAdFragment).commit();
    }

    public void loadEditProfileFragment(FragmentManager fragmentManager) {
        Fragment editProfileFragment = new EditProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, editProfileFragment).commit();
    }

    public void signOut(){
        userHandler.signOut();
    }

}