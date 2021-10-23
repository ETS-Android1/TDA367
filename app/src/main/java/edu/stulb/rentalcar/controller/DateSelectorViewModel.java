package edu.stulb.rentalcar.controller;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.listing.DateHandler;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.model.listing.Reservation;

public class DateSelectorViewModel extends ViewModel {
    DateHandler dateHandler = new DateHandler();
    ListingHandler listingHandler = ListingHandler.getInstance();
    ArrayList<Long> clickedDatesList = new ArrayList<>();
    String listingId;
    ArrayList<Long> listingsReservedDates;

    public boolean clickedDate(int year, int month, int dayOfMonth){
        Long clickedDate = dateHandler.convertToMillis(dateHandler.formatDate(year, month, dayOfMonth));
        System.out.println(clickedDate);
        if (!listingsReservedDates.contains(clickedDate)){
            if (clickedDatesList.contains(clickedDate)){
                clickedDatesList.remove(clickedDate);
            } else {
                clickedDatesList.add(clickedDate);
                return true;
            }

        }
        return false;
    }
    public String displayClickedDates(){
        return dateHandler.getClickedDatesString(clickedDatesList);
    }

    public void confirmBooking(){
        ArrayList<Long> updatedList= new ArrayList<>();
        updatedList.addAll(listingsReservedDates);
        updatedList.addAll(clickedDatesList);
        Reservation reservation = new Reservation(updatedList);
        listingHandler.updateListingReservation(getCurrentListing(), reservation);
    }
    private Listing getCurrentListing(){
        for (Listing listing:listingHandler.getListings()) {
            if (listing.getUid().equals(listingId)){
                return listing;
            }
        }
        return null;
    }

    public void getCurrentUid(String uid){
        listingId = uid;
        listingsReservedDates = ListingHandler.getInstance().getListingReservation(listingId);
    }
}
