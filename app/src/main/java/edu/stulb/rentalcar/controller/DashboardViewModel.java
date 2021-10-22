package edu.stulb.rentalcar.controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.view.CarDetailFragment;

public class DashboardViewModel extends ViewModel {

    public ArrayList<Listing> getListings(){
        return ListingHandler.getInstance().getListings();
    }

    public void loadCarDetailFragment(FragmentManager fragmentManager){
        Fragment carDetailFragment = new CarDetailFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, carDetailFragment).commit();
    }

}