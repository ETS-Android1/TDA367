package edu.stulb.rentalcar.controller.Browse;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;

public class DashboardViewModel extends ViewModel {

    public ArrayList<Listing> getListings(){
        return ListingHandler.getInstance().getListings();
    }

}