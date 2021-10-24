package edu.stulb.rentalcar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tda367.R;

import edu.stulb.rentalcar.controller.DateSelectorViewModel;

public class DateSelectorFragment extends Fragment {
    DateSelectorViewModel dateSelectorViewModel = new DateSelectorViewModel();
    String receivedUidString;

    TextView selectedDatesTextView;
    TextView carTitleListing;
    TextView totalCostBooking;

    Button confirmDateBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_selector, container, false);

        Bundle bundle = getArguments();
        if (bundle != null){
            receivedUidString = bundle.getString("listingId");
        }
        dateSelectorViewModel.getCurrentUid(receivedUidString);

        confirmDateBtn = view.findViewById(R.id.confirmDateBtn);
        selectedDatesTextView = view.findViewById(R.id.selectedDatesTextView);
        carTitleListing = view.findViewById(R.id.carTitleListing);
        totalCostBooking = view.findViewById(R.id.totalCostBooking);

        carTitleListing.setText(dateSelectorViewModel.getCarTitle());

        CalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setMinDate(System.currentTimeMillis());
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            //Adds the clicked date to clickedDatesList
            if (dateSelectorViewModel.clickedDate(year, month, dayOfMonth)){
                makeToast("Added date");
            } else {
                makeToast("Date unavailable");
            }
            //Sets textview to view the clicked dates
            selectedDatesTextView.setText(dateSelectorViewModel.displayClickedDates());
            totalCostBooking.setText(dateSelectorViewModel.getTotalCost());

        });
        confirmDateBtn.setOnClickListener(v -> {
            if (!dateSelectorViewModel.isListEmpty()){
                dateSelectorViewModel.confirmBooking();
                dateSelectorViewModel.loadBookingConfirmationFragment(getParentFragmentManager(), receivedUidString, dateSelectorViewModel.displayClickedDates());
            }if (dateSelectorViewModel.isListEmpty()){
                makeToast("You need to select dates");
            }

        });

        return view;
    }


    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }


}
