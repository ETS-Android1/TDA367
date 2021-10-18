package com.example.tda367.controller;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

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

public class DateSelectorViewModel extends ViewModel {
    ArrayList<Long> clickedDatesList = new ArrayList<>();
    ArrayList<Long> bookedFromFirebaseList = new ArrayList<>();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public String setTextView() {
        StringBuilder clickedText = new StringBuilder();
        Collections.sort(clickedDatesList);
        for (int i = 0; i < clickedDatesList.size(); i++) {
            clickedText.append(convertToDate(clickedDatesList.get(i)));
            clickedText.append(", ");
        }

        return clickedText.toString();
    }

    //Converts Millil to date
    public String convertToDate(Long timeInMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        return simpleDateFormat.format(new Date(timeInMillis));
    }

    //Gör en hashmap av de nya datumen
    public void sendToFirebase(String carId, ArrayList<Long> newBookedDates) {
        DocumentReference bookingRef = firestore.collection("cars").document(carId);
        bookingRef.update("CarBookedDates", newBookedDates);
    }


    //hämtar listan där bilen är upptagen och slår ihop den med den valda listan
    public void combineDateLists(String carId) {
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

    public void getBookedListFirebase(String carId) {
        firestore.collection("cars").document(carId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                ArrayList<Long> returnedDates = (ArrayList<Long>) documentSnapshot.get("CarBookedDates");
                bookedFromFirebaseList.addAll(returnedDates);
            }
        });

    }

    public void addToDateList(long clickedDate) {

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

    public long convertToMillis(String clickedDate) {
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

    public String formatDate(int year, int month, int dayOfMonth) {
        @SuppressLint("DefaultLocale") String clickedYear = String.format("%04d", year);
        @SuppressLint("DefaultLocale") String clickedMonth = String.format("%02d", month + 1);
        @SuppressLint("DefaultLocale") String clickedDayOfMonth = String.format("%02d", dayOfMonth);
        return clickedYear + "/" + clickedMonth + "/" + clickedDayOfMonth;
    }
}
