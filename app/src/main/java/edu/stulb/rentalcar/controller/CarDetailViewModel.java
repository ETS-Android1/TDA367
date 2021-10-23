package edu.stulb.rentalcar.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.view.DateSelectorFragment;

public class CarDetailViewModel extends ViewModel {
    ListingHandler listingHandler = ListingHandler.getInstance();

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


}
