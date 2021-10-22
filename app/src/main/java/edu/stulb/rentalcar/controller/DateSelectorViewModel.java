package edu.stulb.rentalcar.controller;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.listing.DateHandler;
import edu.stulb.rentalcar.model.listing.ListingHandler;

public class DateSelectorViewModel extends ViewModel {
    String inDataListingID = "1ed5733f-44e0-4345-9068-29f93d06c517";
    DateHandler dateHandler = new DateHandler();
    ArrayList<Long> clickedDatesList = new ArrayList<>();
    ArrayList<Long> listingsReservedDates = ListingHandler.getInstance().getListingReservation(inDataListingID);

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

}
