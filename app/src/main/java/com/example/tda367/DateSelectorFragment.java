package com.example.tda367;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class DateSelectorFragment extends Fragment {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<Long> clickedDatesList = new ArrayList<>();
    ArrayList<Long> bookedFromFirebaseList = new ArrayList<>();

    TextView selectedDatesTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_selector, container, false);
        Button confirmDateBtn = (Button) view.findViewById(R.id.confirmDateBtn);

        TextView selectedDatesTextView = (TextView) view.findViewById(R.id.selectedDatesTextView);

        getBookedListFirebase("4D3wxMpargBqR3VmQ61c");

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        calendarView.setMinDate(System.currentTimeMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String clickedDate = formatDate(year, month, dayOfMonth);
                long convertedLong = convertToMillis(clickedDate);
                //Adds the clicked date to clickedDatesList
                addToDateList(convertedLong);
                //Sets textview to view the clicked dates
                selectedDatesTextView.setText(setTextView(clickedDatesList));
            }

        });
        confirmDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combineDateLists("4D3wxMpargBqR3VmQ61c");
            }
        });
        return view;
    }

    private String setTextView(ArrayList<Long> clickedDates) {
        StringBuilder clickedText = new StringBuilder();
        Collections.sort(clickedDates);
        for (int i = 0; i < clickedDates.size(); i++) {
            clickedText.append(convertToDate(clickedDates.get(i)));
            clickedText.append(", ");
        }

        return clickedText.toString();
    }

    //Converts Millil to date
    private String convertToDate(Long timeInMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        return simpleDateFormat.format(new Date(timeInMillis));
    }

    //Gör en hashmap av de nya datumen
    private void sendToFirebase(String carId, ArrayList<Long> newBookedDates) {
        DocumentReference bookingRef = firestore.collection("cars").document(carId);
        bookingRef.update("CarBookedDates", newBookedDates);
    }


    //hämtar listan där bilen är upptagen och slår ihop den med den valda listan
    private void combineDateLists(String carId) {
        firestore.collection("cars").document(carId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                ArrayList<Long> combinedList = new ArrayList<>();
                combinedList.addAll(bookedFromFirebaseList);
                combinedList.addAll(clickedDatesList);
                //checks so no dates are stored more than once
                sendToFirebase(carId, combinedList);
            }
        });
    }

    private void getBookedListFirebase(String carId) {
        firestore.collection("cars").document(carId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                ArrayList<Long> returnedDates = (ArrayList<Long>) documentSnapshot.get("CarBookedDates");
                bookedFromFirebaseList.addAll(returnedDates);
            }
        });

    }

    private void addToDateList(long clickedDate) {

        if (!bookedFromFirebaseList.contains(clickedDate)) {
            if (!clickedDatesList.contains(clickedDate)) {
                clickedDatesList.add(clickedDate);
            } else {
                clickedDatesList.remove(clickedDate);
            }
        }
        if (bookedFromFirebaseList.contains(clickedDate)) {
            System.out.println("Datumet är redan bokat");
        }
    }

    private long convertToMillis(String clickedDate) {
        long timeInMillis = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date date = simpleDateFormat.parse(clickedDate);
            timeInMillis = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMillis;
    }

    private String formatDate(int year, int month, int dayOfMonth) {
        @SuppressLint("DefaultLocale") String clickedYear = String.format("%04d", year);
        @SuppressLint("DefaultLocale") String clickedMonth = String.format("%02d", month + 1);
        @SuppressLint("DefaultLocale") String clickedDayOfMonth = String.format("%02d", dayOfMonth);
        return clickedYear + "/" + clickedMonth + "/" + clickedDayOfMonth;
    }
}
