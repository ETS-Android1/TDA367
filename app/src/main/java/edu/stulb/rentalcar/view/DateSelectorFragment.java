package edu.stulb.rentalcar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tda367.R;
import edu.stulb.rentalcar.controller.DateSelectorViewModel;

public class DateSelectorFragment extends Fragment {
    DateSelectorViewModel dateSelectorViewModel = new DateSelectorViewModel();

    TextView selectedDatesTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_selector, container, false);
        Button confirmDateBtn = (Button) view.findViewById(R.id.confirmDateBtn);

        TextView selectedDatesTextView = (TextView) view.findViewById(R.id.selectedDatesTextView);

        dateSelectorViewModel.getBookedListFirebase("4D3wxMpargBqR3VmQ61c");

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        calendarView.setMinDate(System.currentTimeMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String clickedDate = dateSelectorViewModel.formatDate(year, month, dayOfMonth);
                long convertedLong = dateSelectorViewModel.convertToMillis(clickedDate);
                //Adds the clicked date to clickedDatesList
                dateSelectorViewModel.addToDateList(convertedLong);
                System.out.println(convertedLong);
                //Sets textview to view the clicked dates
                selectedDatesTextView.setText(dateSelectorViewModel.setTextView());
            }

        });
        confirmDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateSelectorViewModel.combineDateLists("4D3wxMpargBqR3VmQ61c");
            }
        });
        return view;
    }


}
