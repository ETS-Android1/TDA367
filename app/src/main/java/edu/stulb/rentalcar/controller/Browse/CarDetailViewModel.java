package edu.stulb.rentalcar.controller.Browse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.model.user.UserHandler;
import edu.stulb.rentalcar.view.Browse.DateSelectorFragment;
import edu.stulb.rentalcar.view.Profile.SignInFragment;

public class CarDetailViewModel extends ViewModel {
    ListingHandler listingHandler = ListingHandler.getInstance();
    private final UserHandler userHandler = UserHandler.getInstance();

    public Listing getClickedListing(String uid){
        return listingHandler.getListingFromUid(uid);
    }

    public void loadDateSelectorFragment(FragmentManager fragmentManager, String uid){
        Bundle args = new Bundle();
        args.putString("listingId", uid);
        Fragment dateSelectorFragment = new DateSelectorFragment();
        dateSelectorFragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.carDetailFragment, dateSelectorFragment).commit();
    }

    public boolean isUserSignedIn() {
        return userHandler.isUserSignedIn();
    }

    public void loadSignInFragment(FragmentManager fragmentManager) {
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }

}
