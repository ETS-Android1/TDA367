package edu.stulb.rentalcar.model.listing;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * DateHandler is a class to handle Dates
 */
public class DateHandler {

    /**
     * @param clickedDatesList Arraylist of clicked dates
     * @return String of formated Arraylist with all dates clicked
     */
    public String getClickedDatesString(ArrayList<Long> clickedDatesList) {
        StringBuilder clickedText = new StringBuilder();
        Collections.sort(clickedDatesList);
        for (int i = 0; i < clickedDatesList.size(); i++) {
            clickedText.append(convertToDate(clickedDatesList.get(i)));
            clickedText.append(", ");
        }
        return clickedText.toString();
    }

    /**
     * @param timeInMillis Date represented by Long,
     * @return Date in String-format
     */
    private String convertToDate(Long timeInMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        return simpleDateFormat.format(new Date(timeInMillis));
    }

    /**
     * @param clickedDate Date represented by Long
     * @return Date in Long-format
     */
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

    /**
     * @param year Year represented by int
     * @param month Month represented by int
     * @param dayOfMonth day of month represented by int
     * @return Date in String-format
     */
    public String formatDate(int year, int month, int dayOfMonth) {
        @SuppressLint("DefaultLocale") String clickedYear = String.format("%04d", year);
        @SuppressLint("DefaultLocale") String clickedMonth = String.format("%02d", month + 1);
        @SuppressLint("DefaultLocale") String clickedDayOfMonth = String.format("%02d", dayOfMonth);
        return clickedYear + "/" + clickedMonth + "/" + clickedDayOfMonth;
    }
}
