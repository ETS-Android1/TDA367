package edu.stulb.rentalcar.controller;

import androidx.lifecycle.ViewModel;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;

public class CarDetailViewModel extends ViewModel {
    ListingHandler listingHandler = ListingHandler.getInstance();

    public Listing getClickedListing(String uid){
        return listingHandler.getListingFromUid(uid);
    }

}
