package edu.stulb.rentalcar.view.Browse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tda367.R;

import edu.stulb.rentalcar.controller.Browse.BookingConfirmationViewModel;

public class BookingConfirmationFragment extends Fragment {
    BookingConfirmationViewModel bookingConfirmationViewModel = new BookingConfirmationViewModel();
    String receivedUidString;
    String listingBookedDays;
    int receivedNumDaysBooked;

    EditText listingBookedDates, carTitleBookingConfirmation, carPricePerDay, totalPrice;
    Button homeButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookingconfirmation, container, false);
        Bundle bundle = getArguments();
        if (bundle != null){
            receivedUidString = bundle.getString("listingId");
            listingBookedDays = bundle.getString("listingBookedDays");
            receivedNumDaysBooked = bundle.getInt("numDaysBooked");
        }
        bookingConfirmationViewModel.getBookedListing(receivedUidString, listingBookedDays);

        listingBookedDates = view.findViewById(R.id.listingBookedDates);
        carTitleBookingConfirmation = view.findViewById(R.id.carTitleBookingConfirmation);
        carPricePerDay = view.findViewById(R.id.carPricePerDay);
        totalPrice = view.findViewById(R.id.totalPrice);
        homeButton = view.findViewById(R.id.homeButton);

        listingBookedDates.setText(bookingConfirmationViewModel.displayBookedDates());
        carTitleBookingConfirmation.setText(bookingConfirmationViewModel.displayCarTitle());
        carPricePerDay.setText(bookingConfirmationViewModel.displayPricePerDay());
        totalPrice.setText(bookingConfirmationViewModel.displayTotalPrice(receivedNumDaysBooked));

        homeButton.setOnClickListener(v -> bookingConfirmationViewModel.loadHomeFragment(getParentFragmentManager()));

        return view;
    }
}