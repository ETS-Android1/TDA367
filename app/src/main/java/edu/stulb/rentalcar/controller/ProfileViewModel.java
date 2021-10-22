package edu.stulb.rentalcar.controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.model.user.UserHandler;
import edu.stulb.rentalcar.view.AddCarAdFragment;
import edu.stulb.rentalcar.view.SignInFragment;

public class ProfileViewModel extends ViewModel {

    UserHandler userHandler = UserHandler.getInstance();
    ListingHandler listingHandler = ListingHandler.getInstance();

    public ProfileViewModel() {

    }

    public ArrayList<Listing> getUsersListings(){
        return listingHandler.getUserListings(userHandler.getCurrentUser().getEmail());
    }

    /*
        public void addAd(String carTitle, String carBrand, String carModel, String carYear, int carPrice, String carLocation, Uri selectedImage){
            firebaseHandler.addAd(carTitle, carBrand, carModel, carYear, carPrice, carLocation, selectedImage);
        }

        public String getUserEmail(){
            return firebaseHandler.getCurrentUser().getEmail().toString();
        }


     */
    public void loadSignInFragment(FragmentManager fragmentManager) {
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }

    public void loadAddCarAdFragment(FragmentManager fragmentManager) {
        Fragment addCarAdFragment = new AddCarAdFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarAdFragment).commit();
    }
/*
    public void signOut(){
        firebaseHandler.signOut();
    }

 */

}